package ru.praktikumservices.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;
import io.qameta.allure.junit4.AllureJunit4;


public class CourierSteps {

    private static final String COURIER_ENDPOINT = "/api/v1/courier";


    @Step("Создание курьера с заданным телом запроса")
    public Response createCourier(String requestBody) {
        return given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post(COURIER_ENDPOINT);
    }

    @Step("Логин курьера с заданным телом запроса")
    public Response loginCourier(String loginRequestBody) {
        return given()
                .header("Content-type", "application/json")
                .body(loginRequestBody)
                .when()
                .post(COURIER_ENDPOINT+"/login");
    }

    @Step("Извлечение ID курьера из ответа логина")
    public Integer getCourierId(Response loginResponse) {
        return loginResponse.then()
                .statusCode(200)
                .extract()
                .path("id");
    }

    @Step("Удаление курьера с ID: {courierId}")
    public void deleteCourier(Integer courierId) {
        given()
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_ENDPOINT + "/" + courierId)
                .then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }
}

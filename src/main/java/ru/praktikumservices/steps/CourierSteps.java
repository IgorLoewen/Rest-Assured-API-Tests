package ru.praktikumservices.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;


public class CourierSteps {

    @Step("Создание курьера с заданным телом запроса")
    public Response createCourier(String requestBody) {
        return given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Логин курьера с заданным телом запроса")
    public Response loginCourier(String loginRequestBody) {
        return given()
                .header("Content-type", "application/json")
                .body(loginRequestBody)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Извлечение ID курьера из ответа логина")
    public Integer extractCourierId(Response loginResponse) {
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
                .delete("/api/v1/courier/" + courierId)
                .then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }
}

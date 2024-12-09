package ru.praktikumservices.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.praktikumservices.data.Data.*;


public class CourierSteps {

    @Step("Логин курьера с дефолтными данными")
    public Response loginCourier() {
        String loginRequestBody = "{ \"login\": \"" + login + "\", \"password\": \"" + password + "\" }";

        return given()
                .header("Content-type", "application/json")
                .body(loginRequestBody)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Создание курьера с дефолтными данными")
    public Response createCourier() {
        String createRequestBody = "{ \"login\": \"" + login + "\", \"password\": \"" + password + "\", \"firstName\": \"" + firstName + "\" }";

        return given()
                .header("Content-type", "application/json")
                .body(createRequestBody)
                .when()
                .post("/api/v1/courier");
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

    @Step("Извлечение ID курьера из ответа логина")
    public Integer extractCourierId(Response loginResponse) {
        return loginResponse.then()
                .statusCode(200)
                .extract()
                .path("id");
    }
}


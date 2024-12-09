package ru.praktikumservices.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ru.praktikumservices.data.Data.*;


public class CourierSteps {

    @Step("Создание курьера с данными по умолчанию")
    public void createCourier() {
        // Подготовка тела запроса для создания курьера
        String createRequestBody = "{ \"login\": \"" + login + "\", \"password\": \"" + password + "\", \"firstName\": \"" + firstName + "\" }";

        // Отправка запроса
        Response createResponse = given()
                .header("Content-type", "application/json")
                .body(createRequestBody)
                .when()
                .post("/api/v1/courier");

        // Проверка, что курьер успешно создан
        createResponse.then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

}

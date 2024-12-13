package ru.praktikumservices.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class OrderSteps {





    @Step("Создание заказа с заданным телом запроса")
    public Response createOrder(String requestBody) {
        return given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Получить список всех заказов в системе")
    public Response getOrdersList() {
        return given()
                .when()
                .get("/api/v1/orders");
    }

}

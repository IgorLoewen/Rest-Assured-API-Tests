package ru.praktikumservices.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class OrderSteps {

    private static final String ORDERS_ENDPOINT = "/api/v1/orders";


    @Step("Создание заказа с заданным телом запроса")
    public Response createOrder(String requestBody) {
        return given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post(ORDERS_ENDPOINT );
    }

    @Step("Получить список всех заказов в системе")
    public Response getOrdersList() {
        return given()
                .when()
                .get(ORDERS_ENDPOINT );
    }
}

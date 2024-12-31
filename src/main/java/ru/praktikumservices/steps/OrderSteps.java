package ru.praktikumservices.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikumservices.models.OrderModel;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    private static final String ORDERS_ENDPOINT = "/api/v1/orders";

    @Step("Создание заказа с заданным объектом OrderModel")
    public Response createOrder(OrderModel order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(ORDERS_ENDPOINT);
    }

    @Step("Получить список всех заказов в системе")
    public Response getOrdersList() {
        return given()
                .when()
                .get(ORDERS_ENDPOINT);
    }
}

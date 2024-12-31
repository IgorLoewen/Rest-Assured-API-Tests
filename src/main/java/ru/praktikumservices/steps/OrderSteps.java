package ru.praktikumservices.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikumservices.models.OrderModel;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrderSteps {

    private static final String ORDERS_ENDPOINT = "/orders";

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
                .header("Content-type", "application/json")
                .when()
                .get(ORDERS_ENDPOINT);
    }

    @Step("Извлечение track из ответа заказа")
    public Integer getOrderId(Response response) {
        return response.then()
                .statusCode(201)
                .extract()
                .path("track");
    }

    @Step("Отмена заказа с номером track: {track}")
    public void cancelOrder(Integer track) {
        if (track == null) {
            throw new IllegalArgumentException("Track ID cannot be null");
        }
        given()
                .header("Content-type", "application/json")
                .queryParam("track", track)
                .when()
                .put(ORDERS_ENDPOINT + "/cancel")
                .then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }
}

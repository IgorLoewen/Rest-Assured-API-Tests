package ru.praktikumservices.tests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;
import ru.praktikumservices.steps.CourierSteps;
import ru.praktikumservices.steps.OrderSteps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.praktikumservices.data.Data.*;


public class CreateOrderTest extends TestsSetUp{

    private final OrderSteps orderSteps = new OrderSteps();


    @Test // курьера можно создать;
    public void courierCanBeCreated() {
        orderSteps.createOrder(CREATE_ORDER_BODY)
                .then().statusCode(201).body("track", notNullValue());
    }


}

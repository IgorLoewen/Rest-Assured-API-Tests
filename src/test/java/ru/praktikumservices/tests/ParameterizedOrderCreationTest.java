package ru.praktikumservices.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikumservices.data.OrderTestData;
import ru.praktikumservices.models.OrderModel;
import ru.praktikumservices.steps.OrderSteps;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.instanceOf;

@Epic("Создание заказов с различными цветами")
@RunWith(Parameterized.class)
public class ParameterizedOrderCreationTest {

    private final OrderModel order;
    private OrderSteps orderSteps;

    public ParameterizedOrderCreationTest(OrderModel order) {
        this.order = order;
    }

    @Parameterized.Parameters(name = "Тест {index}: Цвета {0}")
    public static Collection<OrderModel> testData() {
        return ru.praktikumservices.data.OrderTestData.getOrdersWithOptionalColors();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = OrderTestData.BASE_URL;
        orderSteps = new OrderSteps();
    }

    @Test
    @DisplayName("Создание заказа с различными цветами")
    public void shouldCreateOrderWithDifferentColors() {

        Response response = orderSteps.createOrder(order);
        response.then()
                .statusCode(201)
                .body("track", instanceOf(Integer.class));

        Integer orderId = orderSteps.getOrderId(response);

        orderSteps.cancelOrder(orderId);
    }
}

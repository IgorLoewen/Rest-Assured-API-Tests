package ru.praktikumservices.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
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

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.instanceOf;

@Epic("Создание заказов с различными цветами")
@RunWith(Parameterized.class)
public class ParameterizedOrderDifferentColorsCreationTest {

    private final OrderModel order;
    private OrderSteps orderSteps;

    public ParameterizedOrderDifferentColorsCreationTest(OrderModel order) {
        this.order = order;
    }

    @Parameterized.Parameters(name = "Тест {index}: {0}")
    public static Collection<OrderModel> testData() {
        return ru.praktikumservices.data.OrderTestData.getOrdersWithOptionalColors();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = OrderTestData.BASE_URL;
        orderSteps = new OrderSteps();
    }

    @Test
    @Description("Тест проверяет создание заказа с разными комбинациями.")
    public void shouldCreateOrderWithDifferentColors() {

        Response response = orderSteps.createOrder(order);
        response.then()
                .statusCode(SC_CREATED)
                .body("track", instanceOf(Integer.class));

        Integer orderId = orderSteps.getOrderId(response);

        orderSteps.cancelOrder(orderId);
    }
}

package ru.praktikumservices.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
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

@Epic("Order Creation with Different Colors")
@RunWith(Parameterized.class)
public class ParameterizedOrderDifferentColorsCreationTest {

    private final OrderModel order;
    private OrderSteps orderSteps;
    private Integer orderId;

    public ParameterizedOrderDifferentColorsCreationTest(OrderModel order) {
        this.order = order;
    }

    @Parameterized.Parameters(name = "Test {index}: {0}")
    public static Collection<OrderModel> testData() {
        return ru.praktikumservices.data.OrderTestData.getOrdersWithOptionalColors();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = OrderTestData.BASE_URL;
        orderSteps = new OrderSteps();
    }

    @Test
    @Description("This test verifies the creation of orders with different color combinations.")
    public void shouldCreateOrderWithDifferentColors() {

        Response response = orderSteps.createOrder(order);
        response.then()
                .statusCode(SC_CREATED)
                .body("track", instanceOf(Integer.class));

        orderId = orderSteps.getOrderId(response);
    }

    @After
    public void tearDown() {
        orderSteps.cancelOrder(orderId);
    }
}

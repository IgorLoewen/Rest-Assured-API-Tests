package ru.praktikumservices.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import ru.praktikumservices.data.OrderTestData;
import ru.praktikumservices.steps.OrderSteps;

import static io.qameta.allure.internal.shadowed.jackson.core.JsonPointer.empty;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.everyItem;

@Epic("Order Management")
public class GetOrderListTest {

    private OrderSteps orderSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = OrderTestData.BASE_URL;
        orderSteps = new OrderSteps();
    }

    @Test
    @DisplayName("Verify that the response body contains a list of orders.")
    @Description("This test checks that the response to the request contains a correct list of orders. It is expected that the field containing orders is present and contains data in the correct format.")
    public void getOrdersList() {

        orderSteps.getOrdersList()

                .then().statusCode(SC_OK).body("orders", not(empty())).body("orders.id", everyItem(instanceOf(Integer.class)));

    }

}

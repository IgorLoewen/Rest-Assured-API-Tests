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

@Epic("Работа с заказами")
public class GetOrderListTest {

    private OrderSteps orderSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = OrderTestData.BASE_URL;
        orderSteps = new OrderSteps();
    }


    /// !!! Ручка слетает переодически Ответ 504 !!! Но тест рабочий.  HTTP чаще ответ возвращает, чем HTTPS
    @Test
    @DisplayName("Проверь, что в тело ответа возвращается список заказов.")
    @Description("Этот тест проверяет, что в ответе на запрос возвращается корректный список заказов. Ожидается, что поле, содержащее заказы, присутствует и содержит данные в правильном формате.")
    public void getOrdersList() {

        orderSteps.getOrdersList()

                .then()
                .statusCode(SC_OK)
                .body("orders", not(empty()))
                .body("orders.id", everyItem(instanceOf(Integer.class)));

    }


}

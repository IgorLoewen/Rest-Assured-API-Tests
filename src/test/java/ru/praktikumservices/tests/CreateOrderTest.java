package ru.praktikumservices.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import ru.praktikumservices.data.OrderTestData;
import ru.praktikumservices.steps.CourierSteps;
import ru.praktikumservices.steps.OrderSteps;

import static io.qameta.allure.internal.shadowed.jackson.databind.introspect.ObjectIdInfo.empty;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.not;

@Epic("Работа с заказами")
public class CreateOrderTest {

    private OrderSteps orderSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = OrderTestData.BASE_URL;
        orderSteps = new OrderSteps();
    }


//    /// !!! Ручка слетает переодически Ответ 504 !!! Но тест рабочий.  HTTP чаще ответ возвращает, чем HTTPS
//    @Test
//    @DisplayName("Проверь, что в тело ответа возвращается список заказов.")
//    @Description("Этот тест проверяет, что в ответе на запрос возвращается корректный список заказов. Ожидается, что поле, содержащее заказы, присутствует и содержит данные в правильном формате.")
//    public void getOrdersList() {
//
//        orderSteps.getOrdersList()
//
//                .then()
//                .statusCode(200)
//                .body("orders", not(empty()))
//                .body("orders.id", everyItem(instanceOf(Integer.class)));
//
//    }


}

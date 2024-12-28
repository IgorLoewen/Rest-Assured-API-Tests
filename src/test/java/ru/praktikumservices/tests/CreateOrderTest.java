package ru.praktikumservices.tests;


import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.praktikumservices.steps.OrderSteps;


import static org.hamcrest.CoreMatchers.*;
import static ru.praktikumservices.data.Data.*;

@Feature("Работа с заказами")
public class CreateOrderTest extends TestsSetUp{

    private final OrderSteps orderSteps = new OrderSteps();



   @Test
   @DisplayName("можно указать один из цветов — BLACK или GREY;\n" +
           "     можно указать оба цвета;\n" +
           "     можно совсем не указывать цвет;\n" +
           "     тело ответа содержит track.")
    public void shouldHandleAllColorCombinationsAndContainTrack(){

        for (String body : CREATE_ORDER_WITH_OPTIONAL_DIFFERENT_COLORS) {
            orderSteps.createOrder(body)

                    .then()
                    .statusCode(201)
                    .body("track", instanceOf(Integer.class));
        }
    }

///!!! Ручка слетает переодически Ответ 504 !!! Но тест рабочий.  HTTP чаще ответ возвращает, чем HTTPS
   /*@Test
   @DisplayName("Проверь, что в тело ответа возвращается список заказов.")
    public void getOrdersList() {

        orderSteps.getOrdersList()

                .then()
                .statusCode(200)
                .body("orders", not(empty()));
//                решил так проверить.    Почитал, вроде такое решение норм...
//                Проверяет что массив orders содержит какие то данные и не равен null и так же не пустой

    }*/


}

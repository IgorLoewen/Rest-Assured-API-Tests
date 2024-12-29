package ru.praktikumservices.tests;


import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
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
   @Description("Этот тест проверяет возможность указания цвета при создании заказа. Можно выбрать один из цветов (BLACK или GREY), оба цвета, либо вовсе не указывать цвет. В любом случае успешный запрос должен возвращать поле 'track' в ответе.")
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
   @Description("Этот тест проверяет, что в ответе на запрос возвращается корректный список заказов. Ожидается, что поле, содержащее заказы, присутствует и содержит данные в правильном формате.")
    public void getOrdersList() {

        orderSteps.getOrdersList()

                .then()
                .statusCode(200)
                .body("orders", not(empty()))
                .body("orders.id", everyItem(instanceOf(Integer.class)));

//         проверяем, что массив orders содержит данные и не равен null
//         и дополнительно убеждаемся, что каждое значение в поле id является числом

    }*/





}

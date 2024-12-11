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
    private final CourierSteps courierSteps = new CourierSteps();

//В общем надо уточнить!!! Можно ли сделать один тест на все сценарии выбора цветов или
//    же по одному, как указано по пунктам в задании тренажёра. А так всё готово, можно быстро переделать!
//    @Test // заказ можно создать;
//    public void courierCanBeCreated() {
//        orderSteps.createOrder(CREATE_ORDER_BODY)
//                .then().statusCode(201).body("track", notNullValue());
//    }

    @Test // можно указать один из цветов — BLACK или GREY;
          //    можно указать оба цвета;
          //    можно совсем не указывать цвет;
          //    тело ответа содержит track.
    public void shouldHandleAllColorCombinationsAndContainTrack(){
        for (String body : CREATE_ORDER_WITH_OPTIONAL_DIFFERENT_COLORS) {
            orderSteps.createOrder(body).then().statusCode(201).body("track", notNullValue());
        }
    }


    @Test // Проверь, что в тело ответа возвращается список заказов.
    public void getOrdersList() {
        orderSteps.getOrdersList()
                .then().statusCode(200).body("orders", notNullValue());
    }



}

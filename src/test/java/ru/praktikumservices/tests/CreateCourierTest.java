package ru.praktikumservices.tests;

import static ru.praktikumservices.data.Data.*;

import io.restassured.response.Response;
import org.junit.Test;
import ru.praktikumservices.steps.CourierSteps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class CreateCourierTest extends TestsSetUp {

    private final CourierSteps courierSteps = new CourierSteps();


    @Test
    public void courierCanBeCreatedLoggedAndDeleted() {
        // Создание курьера
        Response createResponse = courierSteps.createCourier();
        createResponse.then().statusCode(201).body("ok", equalTo(true));

        // Логин курьера
        Response loginResponse = courierSteps.loginCourier();

        // Получение ID курьера
        Integer courierId = courierSteps.extractCourierId(loginResponse);

        // Удаление курьера
        courierSteps.deleteCourier(courierId);
    }


}



   /*  @Test
    public void courierCanNotBeCreatedTwice() {
        // Подготовка тела запроса
        String requestBody = "{ \"login\": \"ыалолто\", \"password\": \"2234\", \"firstName\": \"Test\" }";

        // Отправка POST-запроса
        Response response = given()
                .header("Content-type", "application/json") // Указываем заголовок
                .and()
                .body(requestBody) // Тело запроса
                .when()
                .post("/api/v1/courier"); // Эндпоинт для создания курьера

        // Проверка результата
        response.then()
                .statusCode(409) // Проверяем код ответа
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой.")); // Проверяем тело ответа
                                          //баг в документации ЯП там ожидается "Этот логин уже используется"
    }*/

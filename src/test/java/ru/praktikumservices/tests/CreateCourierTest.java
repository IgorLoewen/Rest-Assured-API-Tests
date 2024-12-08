package ru.praktikumservices.tests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest extends TestsSetUp {

   /* @Test
    public void courierCanBeCreated() {
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
                .statusCode(201) // Проверяем код ответа
                .and()
                .body("ok", equalTo(true)); // Проверяем тело ответа
    }*/

    @Test
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
    }
}

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
    public void courierCanBeCreatedAndDeleted() {

        courierSteps.createCourier();

        // 2. Логин курьера
        String loginRequestBody = "{ \"login\": \"" + login + "\", \"password\": \"" + password + "\" }";

        Response loginResponse = given()
                .header("Content-type", "application/json")
                .body(loginRequestBody)
                .when()
                .post("/api/v1/courier/login");

        // Исправление: используем Integer для ID
        Integer courierId = loginResponse.then()
                .statusCode(200)
                .extract()
                .path("id");

        // 3. Удаление курьера
        String deleteRequestBody = "{ \"id\": " + courierId + " }"; // ID передаётся как число

        Response deleteResponse = given()
                .header("Content-type", "application/json")
                .body(deleteRequestBody)
                .when()
                .delete("/api/v1/courier/" + courierId);

        deleteResponse.then()
                .statusCode(200)
                .body("ok", equalTo(true));
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

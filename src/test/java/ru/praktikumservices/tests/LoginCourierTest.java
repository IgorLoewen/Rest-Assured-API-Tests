package ru.praktikumservices.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikumservices.data.CourierTestData;
import ru.praktikumservices.models.CourierModel;
import ru.praktikumservices.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;

@Feature("Авторизация курьеров")
public class LoginCourierTest {

    private CourierSteps courierSteps;
    private CourierModel courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTestData.BASE_URL;
        courierSteps = new CourierSteps();

        // Создание курьера перед тестами
        courier = CourierTestData.getValidCourier();
        courierSteps.createCourier(courier)
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("курьер может авторизоваться;")
    public void courierCanLoginSuccessfully() {
        courierSteps.loginCourier(courier)
                .then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }

    @After
    public void tearDown() {
        // Удаление созданного курьера после тестов
        courierSteps.deleteCourier(courier);
    }

    // Закомментированные тесты

//    @Test
//    @DisplayName("для авторизации нужно передать все обязательные поля;\n" +
//            "     если какого-то поля нет, запрос возвращает ошибку;")
//    public void loginRequiresAllRequiredFields() {
//
//        courierSteps.createCourier(LOGIN_REQUEST_BODY);
//
//        for (String body : EMPTY_LOGIN_BODIES) {
//            courierSteps.loginCourier(body)
//
//                    .then()
//                    .statusCode(400)
//                    .body("message", equalTo("Недостаточно данных для входа"));
//        }
//    }

//    @Test
//    @DisplayName("система вернёт ошибку, если неправильно указать логин или пароль;")
//    public void loginFailsWithInvalidCredentials() {
//
//        courierSteps.createCourier(LOGIN_REQUEST_BODY);
//
//        for (String body : INVALID_CREDENTIALS_BODIES) {
//            courierSteps.loginCourier(body)
//
//                    .then()
//                    .statusCode(404)
//                    .body("message", equalTo("Учетная запись не найдена"));
//        }
//    }

//    @Test
//    @DisplayName("если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;")
//    public void loginFailsWithNonExistentUser() {
//
//        courierSteps.loginCourier(LOGIN_REQUEST_BODY)
//
//                .then().statusCode(404)
//                .body("message", equalTo("Учетная запись не найдена"));
//    }

//    @Test
//    @DisplayName("успешный запрос возвращает id.")
//    public void successfulRequestReturnsId() {
//
//        courierSteps.createCourier(LOGIN_REQUEST_BODY);
//
//        courierSteps.loginCourier(LOGIN_REQUEST_BODY)
//
//                .then()
//                .body("id", instanceOf(Integer.class));
//    }
}

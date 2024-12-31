package ru.praktikumservices.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikumservices.models.CourierModel;
import ru.praktikumservices.steps.CourierSteps;
import ru.praktikumservices.data.CourierTestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@Feature("Управление курьерами")
public class CreateCourierTest {

    private CourierSteps courierSteps;
    private Response loginResponse;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierSteps = new CourierSteps();
    }

    @Test
    @DisplayName("Курьера можно создать")
    @Description("Этот тест проверяет возможность создания курьера с валидными данными.")
    public void courierCanBeCreated() {
        CourierModel courier = CourierTestData.getValidCourier();

        courierSteps.createCourier(courier)
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

//    @Test
//    @DisplayName("Нельзя создать двух одинаковых курьеров")
//    @Description("Этот тест проверяет, что невозможно создать двух курьеров с одинаковыми данными. Ожидается, что при попытке создания второго курьера будет возвращён соответствующий код ошибки.")
//    public void courierCanNotBeCreatedTwice() {
//        CourierModel courier = CourierTestData.getValidCourier();
//
//        courierSteps.createCourier(courier)
//                .then()
//                .statusCode(201);
//
//        courierSteps.createCourier(courier)
//                .then()
//                .statusCode(409)
//                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
//    }
//
//    @Test
//    @DisplayName("Чтобы создать курьера, нужно передать в ручку все обязательные поля")
//    @Description("Этот тест проверяет, что для создания курьера необходимо указать все обязательные поля. Если какое-либо из обязательных полей отсутствует, запрос должен вернуть ошибку.")
//    public void allRequiredFieldsNeedToBeFilled() {
//        for (CourierModel courier : CourierTestData.getInvalidCourierBodies()) {
//            courierSteps.createCourier(courier)
//                    .then()
//                    .statusCode(400)
//                    .body("message", equalTo("Недостаточно данных для создания учетной записи"));
//        }
//    }
//
//    @Test
//    @DisplayName("Если одного из полей нет, запрос возвращает ошибку")
//    @Description("Этот тест проверяет, что запрос на создание курьера возвращает ошибку, если отсутствует одно из обязательных полей. Ожидается соответствующий код ошибки и сообщение об ошибке в ответе.")
//    public void requestReturnsErrorIfFieldIsMissing() {
//        for (CourierModel courier : CourierTestData.getMissingRequiredFields()) {
//            courierSteps.createCourier(courier)
//                    .then()
//                    .statusCode(400)
//                    .body("message", equalTo("Недостаточно данных для создания учетной записи"));
//        }
//    }
//
//    @Test
//    @DisplayName("Нельзя создать двух одинаковых курьеров с одним логином, но разными данными")
//    @Description("Этот тест проверяет, что невозможно создать двух курьеров с одинаковым логином, но разными остальными данными. Ожидается, что запрос на создание второго курьера вернёт ошибку, так как логин должен быть уникальным.")
//    public void courierCanNotBeCreatedWithDuplicateLogin() {
//        CourierModel baseCourier = CourierTestData.getValidCourier();
//
//        courierSteps.createCourier(baseCourier)
//                .then()
//                .statusCode(201);
//
//        for (CourierModel courier : CourierTestData.getDuplicateLoginCouriers()) {
//            courierSteps.createCourier(courier)
//                    .then()
//                    .statusCode(409)
//                    .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
//        }
//    }

    @After
    public void tearDown() {
        CourierModel loginCourier = CourierTestData.getValidLoginBody();
        loginResponse = CourierSteps.loginCourier(loginCourier);
        Integer courierId = courierSteps.getCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);

        loginResponse = null;
    }

}

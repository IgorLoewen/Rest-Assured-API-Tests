package ru.praktikumservices.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.Test;
import ru.praktikumservices.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static ru.praktikumservices.data.Data.*;

@Feature("Управление курьерами")
public class CreateCourierTest extends TestsSetUp {

    private final CourierSteps courierSteps = new CourierSteps();


    @Test
    @DisplayName ("курьера можно создать;")
    @Description("Этот тест проверяет возможность создания курьера с валидными данными.")
    public void courierCanBeCreated() {

        courierSteps.createCourier(LOGIN_REQUEST_BODY)

                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName ("нельзя создать двух одинаковых курьеров;")
    @Description("Этот тест проверяет, что невозможно создать двух курьеров с одинаковыми данными. Ожидается, что при попытке создания второго курьера будет возвращён соответствующий код ошибки.")
    public void courierCanNotBeCreatedTwice() {

        courierSteps.createCourier(LOGIN_REQUEST_BODY);
        courierSteps.createCourier(LOGIN_REQUEST_BODY)

                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName ("чтобы создать курьера, нужно передать в ручку все обязательные поля;")
    @Description("Этот тест проверяет, что для создания курьера необходимо указать все обязательные поля. Если какое-либо из обязательных полей отсутствует, запрос должен вернуть ошибку.")
    public void allRequiredFieldsNeedToBeFilled() {

        for (String body : INVALID_COURIER_REQUEST_BODIES) {
            courierSteps.createCourier(body)

                    .then()
                    .statusCode(400)
                    .body("message", equalTo("Недостаточно данных для создания учетной записи"));
        }
    }



    @Test
    @DisplayName ("если одного из полей нет, запрос возвращает ошибку;")
    @Description("Этот тест проверяет, что запрос на создание курьера возвращает ошибку, если отсутствует одно из обязательных полей. Ожидается соответствующий код ошибки и сообщение об ошибке в ответе.")
    public void requestReturnsErrorIfFieldIsMissing() {

        for (String body : MISSING_REQUIRED_FIELDS_REQUEST_BODIES) {
            courierSteps.createCourier(body)

                    .then()
                    .statusCode(400)
                    .body("message", equalTo("Недостаточно данных для создания учетной записи"));
        }
    }


    @Test
    @DisplayName ("нельзя создать двух одинаковых курьеров с одним логином, но разными данными")
    @Description("Этот тест проверяет, что невозможно создать двух курьеров с одинаковым логином, но разными остальными данными. Ожидается, что запрос на создание второго курьера вернёт ошибку, так как логин должен быть уникальным.")
    public void courierCanNotBeCreatedWithDuplicateLogin() {

        courierSteps.createCourier(LOGIN_REQUEST_BODY);

        for (String body : DUPLICATE_LOGIN_REQUEST_BODIES) {
            courierSteps.createCourier(body)

                    .then()
                    .statusCode(409)
                    .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
        }
    }
}
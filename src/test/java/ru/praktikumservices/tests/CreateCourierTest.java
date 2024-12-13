package ru.praktikumservices.tests;

import org.junit.Test;
import ru.praktikumservices.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static ru.praktikumservices.data.Data.*;


public class CreateCourierTest extends TestsSetUp {

    private final CourierSteps courierSteps = new CourierSteps();


    @Test // курьера можно создать;
    public void courierCanBeCreated() {
        courierSteps.createCourier(LOGIN_REQUEST_BODY)
                .then().statusCode(201).body("ok", equalTo(true));
    }

    @Test // нельзя создать двух одинаковых курьеров;
    public void courierCanNotBeCreatedTwice() {
        courierSteps.createCourier(LOGIN_REQUEST_BODY);
        courierSteps.createCourier(LOGIN_REQUEST_BODY)
                .then().statusCode(409).body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test // чтобы создать курьера, нужно передать в ручку все обязательные поля;
    public void allRequiredFieldsNeedToBeFilled() {
        for (String body : INVALID_COURIER_REQUEST_BODIES) {
            courierSteps.createCourier(body)
                    .then().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
        }
    }

    @Test  // запрос возвращает правильный код ответа;
    public void requestReturnsCorrectResponseCode() {
        courierSteps.createCourier(LOGIN_REQUEST_BODY)
                .then().statusCode(201).body("ok", equalTo(true));
    }

    @Test  // успешный запрос возвращает ok: true;
    public void successfulRequestReturnsOkTrue() {
        courierSteps.createCourier(LOGIN_REQUEST_BODY)
                .then().statusCode(201).body("ok", equalTo(true));
    }

    @Test // если одного из полей нет, запрос возвращает ошибку;
    public void requestReturnsErrorIfFieldIsMissing() {
        for (String body : MISSING_REQUIRED_FIELDS_REQUEST_BODIES) {
            courierSteps.createCourier(body)
                    .then().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
        }
    }


    @Test // нельзя создать двух одинаковых курьеров с одним логином, но разными данными
    public void courierCanNotBeCreatedWithDuplicateLogin() {
        courierSteps.createCourier(LOGIN_REQUEST_BODY);
        for (String body : DUPLICATE_LOGIN_REQUEST_BODIES) {
            courierSteps.createCourier(body).then()
                    .statusCode(409).body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
        }
    }
}
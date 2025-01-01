package ru.praktikumservices.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikumservices.data.CourierTestData;
import ru.praktikumservices.models.CourierModel;
import ru.praktikumservices.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.apache.http.HttpStatus.*;

@Epic("Управление курьерами")
public class CreateCourierTest {

    private CourierSteps courierSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTestData.BASE_URL;
        courierSteps = new CourierSteps();
    }

    @Test
    @DisplayName("Курьера можно создать")
    @Description("Этот тест проверяет возможность создания курьера с валидными данными.")
    public void courierCanBeCreated() {
        CourierModel courier = CourierTestData.getValidCourier();

        courierSteps.createCourier(courier)
                .then()
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    @Description("Этот тест проверяет, что невозможно создать двух курьеров с одинаковыми данными. Ожидается, что при попытке создания второго курьера будет возвращён соответствующий код ошибки.")
    public void courierCanNotBeCreatedTwice() {
        CourierModel courier = CourierTestData.getValidCourier();

        courierSteps.createCourier(courier);
        courierSteps.createCourier(courier)
                .then()
                .statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }


    @After
    public void tearDown() {
        CourierModel loginCourier = CourierTestData.getValidLoginBody();
        Response loginResponse = CourierSteps.loginCourier(loginCourier);
        Integer courierId = courierSteps.getCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);

    }

}

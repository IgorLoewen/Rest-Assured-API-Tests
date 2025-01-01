package ru.praktikumservices.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;

import io.qameta.allure.Feature;
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

import static com.fasterxml.jackson.databind.cfg.ConfigOverride.empty;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.not;

@Feature("Авторизация курьеров")
public class LoginCourierTest {

    private CourierSteps courierSteps;
    private CourierModel courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTestData.BASE_URL;
        courierSteps = new CourierSteps();
        courier = CourierTestData.getValidCourier();
        courierSteps.createCourier(courier);
    }

@Test
@DisplayName("Курьер может авторизоваться")
@Description("Этот тест проверяет возможность логина курьера с валидными данными.")
public void courierCanBeCreated() {

        CourierSteps.loginCourier(courier)

                .then()
                .statusCode(200)
                .body("id", instanceOf(Integer.class));
}


    @After
    public void tearDown() {
        CourierModel loginCourier = CourierTestData.getValidLoginBody();
        Response loginResponse = CourierSteps.loginCourier(loginCourier);
        Integer courierId = courierSteps.getCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);

    }

}




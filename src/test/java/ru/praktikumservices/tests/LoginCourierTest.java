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

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;


@Epic("Авторизация курьеров")
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
    public void courierCanLogin() {

        CourierSteps.loginCourier(courier)

                .then()
                .statusCode(SC_OK)
                .body("id", instanceOf(Integer.class));
    }

    @Test
    @DisplayName("если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;")
    @Description("Этот тест проверяет ошибку логина курьера с несуществующими пользователем.")
    public void courierCanNotLoginWithoutRegistration() {

        CourierModel notRegisteredCourier = CourierTestData.getNotRegisteredCourier();

        CourierSteps.loginCourier(notRegisteredCourier)

                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }


    @After
    public void tearDown() {
        CourierModel loginCourier = CourierTestData.getValidLoginBody();
        Response loginResponse = CourierSteps.loginCourier(loginCourier);
        Integer courierId = courierSteps.getCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);

    }

}




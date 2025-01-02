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

@Epic("Courier Authorization")
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
    @DisplayName("Courier can log in")
    @Description("This test verifies the ability of a courier to log in with valid credentials.")
    public void courierCanLogin() {

        CourierSteps.loginCourier(courier)
                .then()
                .statusCode(SC_OK)
                .body("id", instanceOf(Integer.class));
    }

    @Test
    @DisplayName("If logging in with a non-existent user, the request returns an error.")
    @Description("This test verifies the error when a courier logs in with a non-existent user.")
    public void courierCanNotLoginWithoutRegistration() {

        CourierModel notRegisteredCourier = CourierTestData.getCourierWithInvalidLogin();

        CourierSteps.loginCourier(notRegisteredCourier)
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("If logging in with an incorrect password, the request returns an error.")
    @Description("This test verifies the error when a courier logs in with an incorrect password.")
    public void courierCanNotLoginWithInvalidPassword() {

        CourierModel courierWithInvalidPassword = CourierTestData.getCourierWithInvalidPassword();

        CourierSteps.loginCourier(courierWithInvalidPassword)
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

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

import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.equalTo;

@Epic("Courier Management")
public class CreateCourierTest {

    private CourierSteps courierSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTestData.BASE_URL;
        courierSteps = new CourierSteps();
    }

    @Test
    @DisplayName("Courier can be created")
    @Description("This test checks the possibility of creating a courier with valid data.")
    public void courierCanBeCreated() {
        CourierModel courier = CourierTestData.getValidCourier();

        courierSteps.createCourier(courier).then().statusCode(SC_CREATED).body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Cannot create duplicate couriers")
    @Description("This test checks that it is not possible to create two couriers with the same data. When attempting to create a second courier, the expected error code is returned.")
    public void courierCanNotBeCreatedTwice() {
        CourierModel courier = CourierTestData.getValidCourier();

        courierSteps.createCourier(courier);
        courierSteps.createCourier(courier).then().statusCode(SC_CONFLICT).body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void tearDown() {
        CourierModel loginCourier = CourierTestData.getValidLoginBody();
        Response loginResponse = CourierSteps.loginCourier(loginCourier);
        Integer courierId = courierSteps.getCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);
    }
}

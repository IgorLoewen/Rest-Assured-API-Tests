package ru.praktikumservices.tests;

import io.restassured.response.Response;
import org.junit.Test;
import ru.praktikumservices.steps.CourierSteps;

import static org.hamcrest.Matchers.equalTo;


public class CreateCourierTest extends TestsSetUp {

    private final CourierSteps courierSteps = new CourierSteps();
    private Integer courierId;



    @Test //  Курьера можно создать;
    public void courierCanBeCreatedLoggedAndDeleted() {
        Response createResponse = courierSteps.createCourier();
        createResponse.then().statusCode(201).body("ok", equalTo(true));
        Response loginResponse = courierSteps.loginCourier();
        Integer courierId = courierSteps.extractCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);
    }

    @Test //
    public void courierCannotBeCreatedTwice() {
        Response firstCreateResponse = courierSteps.createCourier();
        firstCreateResponse.then().statusCode(201).body("ok", equalTo(true));
        Response secondCreateResponse = courierSteps.createCourier();
        secondCreateResponse.then().statusCode(409).body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
        Response loginResponse = courierSteps.loginCourier();
        Integer courierId = courierSteps.extractCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);
    }



}




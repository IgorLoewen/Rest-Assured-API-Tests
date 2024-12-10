package ru.praktikumservices.tests;

import io.restassured.response.Response;
import org.junit.Test;
import ru.praktikumservices.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.praktikumservices.data.Data.*;

public class LoginCourierTest extends TestsSetUp{

    private final CourierSteps courierSteps = new CourierSteps();


       @Test // курьер может авторизоваться;
    public void courierCanLoginSuccessfully(){
           courierSteps.createCourier(LOGIN_REQUEST_BODY);
           courierSteps.loginCourier(LOGIN_REQUEST_BODY)
                   .then().statusCode(200).body("id", notNullValue());
       }







}

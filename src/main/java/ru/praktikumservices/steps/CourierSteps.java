package ru.praktikumservices.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikumservices.models.CourierModel;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierSteps {

    private static final String COURIER_ENDPOINT = "/api/v1/courier";

    @Step("Creating a courier with parameters: {courier}")
    public Response createCourier(CourierModel courier) {
        return given().header("Content-type", "application/json").body(courier).when().post(COURIER_ENDPOINT);
    }

    @Step("Logging in the courier with the given CourierModel object")
    public static Response loginCourier(CourierModel courier) {
        return given().header("Content-type", "application/json").body(courier).when().post(COURIER_ENDPOINT + "/login");
    }

    @Step("Extracting the courier ID from the login response")
    public Integer getCourierId(Response loginResponse) {
        return loginResponse.then().statusCode(200).extract().path("id");
    }

    @Step("Deleting the courier with ID: {courierId}")
    public void deleteCourier(Integer courierId) {
        given().header("Content-type", "application/json").when().delete(COURIER_ENDPOINT + "/" + courierId).then().statusCode(200).body("ok", equalTo(true));
    }
}

package ru.praktikumservices.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import ru.praktikumservices.steps.CourierSteps;

import static ru.praktikumservices.data.Data.LOGIN_REQUEST_BODY;



public class TestsSetUp {

    private final CourierSteps courierSteps = new CourierSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";

        // Сбрасываем состояние login курьера перед каждым тестом
        try {
            Response loginResponse = courierSteps.loginCourier(LOGIN_REQUEST_BODY);

            // Проверяем, был ли успешный логин (код 200)
            if (loginResponse.getStatusCode() == 200) {
                Integer courierId = courierSteps.extractCourierId(loginResponse);
                courierSteps.deleteCourier(courierId);
            }
        } catch (Exception e) {
            // Игнорируем, если что-то пошло не так (например, курьера нет)
        }
    }


}

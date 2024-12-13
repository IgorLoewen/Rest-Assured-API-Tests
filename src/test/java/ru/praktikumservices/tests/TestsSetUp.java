package ru.praktikumservices.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import ru.praktikumservices.steps.CourierSteps;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.notification.RunNotifier;

import static ru.praktikumservices.data.Data.LOGIN_REQUEST_BODY;





public class TestsSetUp {

    private final CourierSteps courierSteps = new CourierSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";


        // Регистрируем Listener для Allure
        RunNotifier notifier = new RunNotifier();
        AllureJunit4 allureListener = new AllureJunit4();
        //org.junit.runner.notification.RunNotifier notifier = new org.junit.runner.notification.RunNotifier();
        notifier.addListener(allureListener);
        // Сбрасываем состояние login курьера перед каждым тестом
        try {
            Response loginResponse = courierSteps.loginCourier(LOGIN_REQUEST_BODY);

            // Проверяем, был ли успешный логин (код 200)
            if (loginResponse.getStatusCode() == 200) {
                Integer courierId = courierSteps.getCourierId(loginResponse);
                courierSteps.deleteCourier(courierId);
            }
        } catch (Exception e) {
            // Игнорируем, если например, курьера нет
        }
    }


}

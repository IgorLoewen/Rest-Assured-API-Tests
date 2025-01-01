package ru.praktikumservices.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikumservices.data.CourierTestData;
import ru.praktikumservices.models.CourierModel;
import ru.praktikumservices.steps.CourierSteps;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;

@Epic("Параметризованные тесты: Логин курьера")
@RunWith(Parameterized.class)
public class ParameterizedLoginInvalidDataTest {

    private final CourierModel invalidCourier;
    private CourierSteps courierSteps;

    public ParameterizedLoginInvalidDataTest(CourierModel invalidCourier) {
        this.invalidCourier = invalidCourier;
    }

    @Parameterized.Parameters(name = "Тест {index}: {0}")
    public static Collection<CourierModel> testData() {

        return CourierTestData.getInvalidCourierBodies();

    }

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTestData.BASE_URL;
        courierSteps = new CourierSteps();
        CourierModel courier = CourierTestData.getValidCourier();
        courierSteps.createCourier(courier);

    }

    @Test
    @Description("Тест проверяет логин курьера с различными комбинациями обязательных полей.")
    public void validateCourierFields() {

        Response response = courierSteps.loginCourier(invalidCourier);
        response.then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @After
    public void tearDown() {
        CourierModel loginCourier = CourierTestData.getValidLoginBody();
        Response loginResponse = CourierSteps.loginCourier(loginCourier);
        Integer courierId = courierSteps.getCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);

    }
}
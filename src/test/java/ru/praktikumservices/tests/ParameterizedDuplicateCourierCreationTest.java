package ru.praktikumservices.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
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

@Feature("Параметризованные тесты: Дублирующий логин")
@RunWith(Parameterized.class)
public class ParameterizedDuplicateCourierCreationTest {

    private final CourierModel duplicateCourier;
    private CourierSteps courierSteps;

    public ParameterizedDuplicateCourierCreationTest(CourierModel duplicateCourier) {
        this.duplicateCourier = duplicateCourier;
    }

    @Parameterized.Parameters(name = "Тест {index}: {0}")
    public static Collection<CourierModel> testData() {
        return CourierTestData.getDuplicateLoginCouriers();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTestData.BASE_URL;
        courierSteps = new CourierSteps();
        CourierModel courier = CourierTestData.getValidCourier();
        courierSteps.createCourier(courier);
    }

    @Test
    @Description("Этот тест проверяет, что невозможно создать двух курьеров с одинаковым логином, но разными остальными данными.")
    public void validateDuplicateLogin() {
        courierSteps.createCourier(duplicateCourier)
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void tearDown() {
        CourierModel loginCourier = CourierTestData.getValidLoginBody();
        Response loginResponse = CourierSteps.loginCourier(loginCourier);
        Integer courierId = courierSteps.getCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);
    }
}

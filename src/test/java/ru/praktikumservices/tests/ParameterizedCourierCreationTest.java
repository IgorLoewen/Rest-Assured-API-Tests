package ru.praktikumservices.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikumservices.data.CourierTestData;
import ru.praktikumservices.models.CourierModel;
import ru.praktikumservices.steps.CourierSteps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

@Feature("Параметризованные тесты: Создание курьера")
@RunWith(Parameterized.class)
public class ParameterizedCourierCreationTest {

    private final CourierModel courier;
    private CourierSteps courierSteps;

    public ParameterizedCourierCreationTest(CourierModel courier) {
        this.courier = courier;
    }

    @Parameterized.Parameters(name = "Тест {index}: {0}")
    public static Collection<CourierModel> testData() {

        List<CourierModel> testData = new ArrayList<>();
        testData.addAll(CourierTestData.getInvalidCourierBodies());
        testData.addAll(CourierTestData.getMissingRequiredFields());
        return testData;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTestData.BASE_URL;
        courierSteps = new CourierSteps();
    }

    @Test
    @Description("Тест проверяет создание курьера с различными комбинациями обязательных полей.")
    public void validateCourierFields() {

        Response response = courierSteps.createCourier(courier);
        response.then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
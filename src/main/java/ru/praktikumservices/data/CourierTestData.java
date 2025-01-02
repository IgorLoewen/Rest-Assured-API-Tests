package ru.praktikumservices.data;

import ru.praktikumservices.models.CourierModel;

import java.util.Arrays;
import java.util.List;

public class CourierTestData {

    // Main URL
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    // Main data for the courier
    public static final String LOGIN = "iloewen79@gmx.de";
    public static final String PASSWORD = "1234";
    public static final String FIRST_NAME = "Igor";

    // Valid body for creating a courier
    public static CourierModel getValidCourier() {
        return new CourierModel(LOGIN, PASSWORD, FIRST_NAME);
    }

    // Empty bodies for creating a courier. Various combinations for testing
    public static List<CourierModel> getInvalidCourierBodies() {
        return Arrays.asList(
                new CourierModel("", PASSWORD, FIRST_NAME),
                new CourierModel(LOGIN, "", FIRST_NAME),
                new CourierModel("", "", "")
        );
    }

    // Valid body for courier login
    public static CourierModel getValidLoginBody() {
        return new CourierModel(LOGIN, PASSWORD, null);
    }

    // Missing required fields for testing
    public static List<CourierModel> getMissingRequiredFields() {
        return Arrays.asList(
                new CourierModel(null, PASSWORD, FIRST_NAME),
                new CourierModel(LOGIN, null, FIRST_NAME),
                new CourierModel(null, null, null)
        );
    }

    // Dataset with the same login but different field values
    public static List<CourierModel> getDuplicateLoginCouriers() {
        return Arrays.asList(
                new CourierModel(LOGIN, "12345", "Test1"),
                new CourierModel(LOGIN, "123456", "Test2"),
                new CourierModel(LOGIN, PASSWORD, "Test3")
        );
    }

    // Body for login with an invalid login
    public static CourierModel getCourierWithInvalidLogin() {
        return new CourierModel("InvalidLogin", PASSWORD, FIRST_NAME);
    }

    // Body for login with an invalid password
    public static CourierModel getCourierWithInvalidPassword() {
        return new CourierModel(LOGIN, "InvalidPassword", FIRST_NAME);
    }
}

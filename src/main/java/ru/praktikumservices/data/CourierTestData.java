package ru.praktikumservices.data;

import ru.praktikumservices.models.CourierModel;

import java.util.Arrays;
import java.util.List;

public class CourierTestData {

    // Главный url
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    // Основные данные для курьера
    public static final String LOGIN = "п53ывап";
    public static final String PASSWORD = "1234";
    public static final String FIRST_NAME = "Игорь";

    // Валидное тело для создания курьера
    public static CourierModel getValidCourier() {
        return new CourierModel(LOGIN, PASSWORD, FIRST_NAME);
    }

    // Пустые тела для создания курьера. Разные варианты наборов для теста
    public static List<CourierModel> getInvalidCourierBodies() {
        return Arrays.asList(
                new CourierModel(null, PASSWORD, FIRST_NAME),
                new CourierModel(LOGIN, null, FIRST_NAME),
                new CourierModel("", "", "")
        );
    }

    // Валидное тело для логина курьера
    public static CourierModel getValidLoginBody() {
        return new CourierModel(LOGIN, PASSWORD, null);
    }

    // Отсутствие обязательных полей для тестирования
    public static List<CourierModel> getMissingRequiredFields() {
        return Arrays.asList(
                new CourierModel(null, PASSWORD, FIRST_NAME),
                new CourierModel(LOGIN, null, FIRST_NAME),
                new CourierModel(null, null, null)
        );
    }

    // Набор данных полей с одинаковым логином и разные данные в полях
    public static List<CourierModel> getDuplicateLoginCouriers() {
        return Arrays.asList(
                new CourierModel(LOGIN, "12345", "Test1"),
                new CourierModel(LOGIN, "123456", "Test2"),
                new CourierModel(LOGIN, PASSWORD, "Test3")
        );
    }
}

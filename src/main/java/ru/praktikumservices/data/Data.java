package ru.praktikumservices.data;

public class Data {

    // Основные данные для курьера
    public static final String login = "фывапловап";
    public static final String password = "1234";
    public static final String firstName = "Test";

    // Валидное тело для создания курьера
    public static final String VALID_COURIER_REQUEST_BODY = String.format(
            "{ \"login\": \"%s\", \"password\": \"%s\", \"firstName\": \"%s\" }",
            login, password, firstName
    );

    // Невалидные тела для создания курьера (автоматически генерируются)
    public static final String[] INVALID_COURIER_REQUEST_BODIES = {
            String.format("{ \"password\": \"%s\", \"firstName\": \"%s\" }", password, firstName), // Нет "login"
            String.format("{ \"login\": \"%s\", \"firstName\": \"%s\" }", login, firstName),       // Нет "password"
            "{ \"login\": \"\", \"password\": \"\" }"                                            // Пустые поля
    };

    // Валидное тело для логина курьера
    public static final String LOGIN_REQUEST_BODY = String.format(
            "{ \"login\": \"%s\", \"password\": \"%s\" }",
            login, password
    );
}

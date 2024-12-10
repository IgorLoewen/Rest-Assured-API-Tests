package ru.praktikumservices.data;

public class Data {

    // Основные данные для курьера
    public static final String login = "Игопоорлрь";
    public static final String password = "1234";
    public static final String firstName = "Test";

    // Валидное тело для создания курьера
    public static final String VALID_COURIER_REQUEST_BODY = String.format(
            "{ \"login\": \"%s\", \"password\": \"%s\", \"firstName\": \"%s\" }",
            login, password, firstName
    );

    // Невалидные тела для создания курьера (автоматически генерируются)
    public static final String[] INVALID_COURIER_REQUEST_BODIES = {
            String.format("{ \"password\": \"%s\", \"firstName\": \"%s\" }", password, firstName), // Пустой логин
            String.format("{ \"login\": \"%s\", \"firstName\": \"%s\" }", login, firstName),       // Путой пароль
            "{ \"login\": \"\", \"password\": \"\" }"                                            // Пустые поля
    };


    // Валидное тело для логина курьера
    public static final String LOGIN_REQUEST_BODY = String.format(
            "{ \"login\": \"%s\", \"password\": \"%s\" }",
            login, password
    );

    // Отсутствие обязательнех полей для создания курьера
    public static final String[] MISSING_REQUIRED_FIELDS_REQUEST_BODIES = {
            String.format("{ \"password\": \"%s\", \"firstName\": \"%s\" }", password, firstName), // Нет поля "login"
            String.format("{ \"login\": \"%s\", \"firstName\": \"%s\" }", login, firstName),       // Нет поля "password"
            String.format("{ \"firstName\": \"%s\" }", firstName)                                  // Нет полей "login" и "password"
    };

    public static final String[] DUPLICATE_LOGIN_REQUEST_BODIES = {
            String.format("{ \"login\": \"%s\", \"password\": \"12345\", \"firstName\": \"Test1\" }", login),
            String.format("{ \"login\": \"%s\", \"password\": \"123456\", \"firstName\": \"Test2\" }", login),
            String.format("{ \"login\": \"%s\", \"password\": \"1234\", \"firstName\": \"Test3\" }", login)
    };


}

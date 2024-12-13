package ru.praktikumservices.data;

public class Data {

    // Основные данные для курьера
    public static final String login = "lognап1234";
    public static final String password = "1234";
    public static final String firstName = "Игорь";
    public static final String lastName = "Лёвэн";
    public static final String address = "пер. Сивцев Вражек 3/18";
    public static final int metroStation = 4;
    public static final String phone = "+7 800 355 35 35";
    public static final int rentTime = 5;
    public static final String deliveryDate = "2020-06-06";
    public static final String comment = "I´ll be back.";
    public static final String[] color = {"BLACK", "GREY"};



    // Валидное тело для создания курьера
    public static final String VALID_COURIER_REQUEST_BODY = String.format(
            "{ \"login\": \"%s\", \"password\": \"%s\", \"firstName\": \"%s\" }",
            login, password, firstName
    );

    // Пустые тела для создания курьера. Разные варианты наборов для теста
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

    // Отсутствие обязательнех полей для тестирования
    public static final String[] MISSING_REQUIRED_FIELDS_REQUEST_BODIES = {
            String.format("{ \"password\": \"%s\", \"firstName\": \"%s\" }", password, firstName), // Нет поля "login"
            String.format("{ \"login\": \"%s\", \"firstName\": \"%s\" }", login, firstName),       // Нет поля "password"
            String.format("{ \"firstName\": \"%s\" }", firstName)                                  // Нет полей "login" и "password"
    };

    // Набор данных полей с одинаковым логином и другие данные в полях разные
    public static final String[] DUPLICATE_LOGIN_REQUEST_BODIES = {
            String.format("{ \"login\": \"%s\", \"password\": \"12345\", \"firstName\": \"Test1\" }", login),
            String.format("{ \"login\": \"%s\", \"password\": \"123456\", \"firstName\": \"Test2\" }", login),
            String.format("{ \"login\": \"%s\", \"password\": \"1234\", \"firstName\": \"Test3\" }", login)
    };

    // набор параметров с пустыми полями для логина
    public static final String[] EMPTY_LOGIN_BODIES = {
            String.format("{ \"password\": \"%s\", \"login\": \"\" }", password), // Пустой логин
            String.format("{ \"login\": \"%s\", \"password\": \"\" }", login),   // Пустой пароль
            "{ \"login\": \"\", \"password\": \"\" }"                           // Оба поля пустые
    };

    // Тела запросов с правильным логином и неправильным паролем, и наоборот
    public static final String[] INVALID_CREDENTIALS_BODIES = {
            String.format("{ \"login\": \"%s\", \"password\": \"wrongPassword\" }", login), // Правильный логин, но неправильный пароль
            String.format("{ \"login\": \"wrongLogin\", \"password\": \"%s\" }", password)  // Неправильный логин, но правильный пароль
    };

    // Валидное тело для заказа
    public static final String CREATE_ORDER_BODY = String.format(
            "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": %d, \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\", \"color\": [\"%s\", \"%s\"] }",
            firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color[0], color[1]
    );

    // Валидные тела для создания заказа с различными вариантами цвета
    public static final String[] CREATE_ORDER_WITH_OPTIONAL_DIFFERENT_COLORS = {
            // С одним цветом - BLACK
            String.format(
                    "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": %d, \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\", \"color\": [\"%s\"] }",
                    firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color[0]
            ),
            // С одним цветом - GREY
            String.format(
                    "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": %d, \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\", \"color\": [\"%s\"] }",
                    firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color[1]
            ),
            // С двумя цветами - BLACK и GREY
            String.format(
                    "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": %d, \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\", \"color\": [\"%s\", \"%s\"] }",
                    firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color[0], color[1]
            ),
            // Без указания цвета
            String.format(
                    "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": %d, \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\" }",
                    firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment
            )
    };


}

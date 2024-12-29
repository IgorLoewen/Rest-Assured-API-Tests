package ru.praktikumservices.data;

public class Data {

    // Основные данные для курьера
    public static final String LOGIN = "lognапsdf1234";
    public static final String PASSWORD = "1234";
    public static final String FIRST_NAME = "Игорь";
    public static final String LAST_NAME = "Лёвэн";
    public static final String ADDRESS = "пер. Сивцев Вражек 3/18";
    public static final int METRO_STATION = 4;
    public static final String PHONE = "+7 800 355 35 35";
    public static final int RENT_TIME = 5;
    public static final String DELIVERY_DATE = "2020-06-06";
    public static final String COMMENT = "I´ll be back.";
    public static final String[] COLOR = {"BLACK", "GREY"};



    // Валидное тело для создания курьера
    public static final String VALID_COURIER_REQUEST_BODY = String.format(
            "{ \"login\": \"%s\", \"password\": \"%s\", \"firstName\": \"%s\" }",
            LOGIN, PASSWORD, FIRST_NAME
    );

    // Пустые тела для создания курьера. Разные варианты наборов для теста
    public static final String[] INVALID_COURIER_REQUEST_BODIES = {
            String.format("{ \"password\": \"%s\", \"firstName\": \"%s\" }", PASSWORD, FIRST_NAME), // Пустой логин
            String.format("{ \"login\": \"%s\", \"firstName\": \"%s\" }", LOGIN, FIRST_NAME),       // Путой пароль
            "{ \"login\": \"\", \"password\": \"\" }"                                            // Пустые поля
    };


    // Валидное тело для логина курьера
    public static final String LOGIN_REQUEST_BODY = String.format(
            "{ \"login\": \"%s\", \"password\": \"%s\" }",
            LOGIN, PASSWORD
    );

    // Отсутствие обязательнех полей для тестирования
    public static final String[] MISSING_REQUIRED_FIELDS_REQUEST_BODIES = {
            String.format("{ \"password\": \"%s\", \"firstName\": \"%s\" }", PASSWORD, FIRST_NAME), // Нет поля "login"
            String.format("{ \"login\": \"%s\", \"firstName\": \"%s\" }", LOGIN, FIRST_NAME),       // Нет поля "password"
            String.format("{ \"firstName\": \"%s\" }", FIRST_NAME)                                  // Нет полей "login" и "password"
    };

    // Набор данных полей с одинаковым логином и другие данные в полях разные
    public static final String[] DUPLICATE_LOGIN_REQUEST_BODIES = {
            String.format("{ \"login\": \"%s\", \"password\": \"12345\", \"firstName\": \"Test1\" }", LOGIN),
            String.format("{ \"login\": \"%s\", \"password\": \"123456\", \"firstName\": \"Test2\" }", LOGIN),
            String.format("{ \"login\": \"%s\", \"password\": \"1234\", \"firstName\": \"Test3\" }", LOGIN)
    };

    // набор параметров с пустыми полями для логина
    public static final String[] EMPTY_LOGIN_BODIES = {
            String.format("{ \"password\": \"%s\", \"login\": \"\" }", PASSWORD), // Пустой логин
            String.format("{ \"login\": \"%s\", \"password\": \"\" }", LOGIN),   // Пустой пароль
            "{ \"login\": \"\", \"password\": \"\" }"                           // Оба поля пустые
    };

    // Тела запросов с правильным логином и неправильным паролем, и наоборот
    public static final String[] INVALID_CREDENTIALS_BODIES = {
            String.format("{ \"login\": \"%s\", \"password\": \"wrongPassword\" }", LOGIN), // Правильный логин, но неправильный пароль
            String.format("{ \"login\": \"wrongLogin\", \"password\": \"%s\" }", PASSWORD)  // Неправильный логин, но правильный пароль
    };

    // Валидное тело для заказа
    public static final String CREATE_ORDER_BODY = String.format(
            "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": %d, \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\", \"color\": [\"%s\", \"%s\"] }",
            FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, COLOR[0], COLOR[1]
    );

    // Валидные тела для создания заказа с различными вариантами цвета
    public static final String[] CREATE_ORDER_WITH_OPTIONAL_DIFFERENT_COLORS = {
            // С одним цветом - BLACK
            String.format(
                    "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": %d, \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\", \"color\": [\"%s\"] }",
                    FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, COLOR[0]
            ),
            // С одним цветом - GREY
            String.format(
                    "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": %d, \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\", \"color\": [\"%s\"] }",
                    FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, COLOR[1]
            ),
            // С двумя цветами - BLACK и GREY
            String.format(
                    "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": %d, \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\", \"color\": [\"%s\", \"%s\"] }",
                    FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, COLOR[0], COLOR[1]
            ),
            // Без указания цвета
            String.format(
                    "{ \"firstName\": \"%s\", \"lastName\": \"%s\", \"address\": \"%s\", \"metroStation\": %d, \"phone\": \"%s\", \"rentTime\": %d, \"deliveryDate\": \"%s\", \"comment\": \"%s\" }",
                    FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT
            )
    };


}

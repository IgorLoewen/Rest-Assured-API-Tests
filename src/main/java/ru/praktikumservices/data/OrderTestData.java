package ru.praktikumservices.data;

import ru.praktikumservices.models.OrderModel;

import java.util.Arrays;
import java.util.List;

public class OrderTestData {

    // Основные данные для заказа
    public static final String FIRST_NAME = "Игорь";
    public static final String LAST_NAME = "Лёвэн";
    public static final String ADDRESS = "пер. Сивцев Вражек 3/18";
    public static final int METRO_STATION = 4;
    public static final String PHONE = "+7 800 355 35 35";
    public static final int RENT_TIME = 5;
    public static final String DELIVERY_DATE = "2020-06-06";
    public static final String COMMENT = "I´ll be back.";
    public static final String[] COLOR = {"BLACK", "GREY"};

    // Валидное тело для заказа
    public static OrderModel getValidOrder() {
        return new OrderModel(
                FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE,
                RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR)
        );
    }

    // Валидные тела для создания заказа с различными вариантами цвета
    public static List<OrderModel> getOrdersWithOptionalColors() {
        return Arrays.asList(
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR[0])),
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR[1])),
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR)),
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, null)
        );
    }
}

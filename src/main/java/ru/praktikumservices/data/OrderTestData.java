package ru.praktikumservices.data;

import ru.praktikumservices.models.OrderModel;

import java.util.Arrays;
import java.util.List;

public class OrderTestData {

    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/api/v1";

    // Main data for the order
    public static final String FIRST_NAME = "Igor";
    public static final String LAST_NAME = "Löwen";
    public static final String ADDRESS = "Großflecken 50A";
    public static final int METRO_STATION = 4;
    public static final String PHONE = "0049 2342 34834";
    public static final int RENT_TIME = 5;
    public static final String DELIVERY_DATE = "2024-06-06";
    public static final String COMMENT = "I´ll be back.";
    public static final String[] COLOR = {"BLACK", "GREY"};

    // Valid body for the order
    public static OrderModel getValidOrder() {
        return new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR));
    }

    // Valid bodies for creating an order with different color options
    public static List<OrderModel> getOrdersWithOptionalColors() {
        return Arrays.asList(
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR[0])),
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR[1])),
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR)),
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, null)
        );
    }
}

package ru.praktikumservices.tests;

import io.restassured.response.Response;
import org.junit.Test;
import ru.praktikumservices.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static ru.praktikumservices.data.Data.*;

public class CreateCourierTest extends TestsSetUp {

    private final CourierSteps courierSteps = new CourierSteps();

    @Test // Курьера можно создать
          // запрос возвращает правильный код ответа
          // успешный запрос возвращает ok: true
          // курьер может авторизоваться;
          // успешный запрос возвращает id.
    public void courierCanBeCreatedLoggedAndDeleted() {
        Response createResponse = courierSteps.createCourier(VALID_COURIER_REQUEST_BODY);
        createResponse.then().statusCode(201).body("ok", equalTo(true));
        Response loginResponse = courierSteps.loginCourier(LOGIN_REQUEST_BODY);
        Integer courierId = courierSteps.extractCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);
    }

    @Test // Нельзя создать двух одинаковых курьеров
          // если создать пользователя с логином, который уже есть, возвращается ошибка.
          // курьер может авторизоваться;
          // успешный запрос возвращает id.
    public void courierCannotBeCreatedTwice() {
        Response firstCreateResponse = courierSteps.createCourier(VALID_COURIER_REQUEST_BODY);
        firstCreateResponse.then().statusCode(201).body("ok", equalTo(true));
        Response secondCreateResponse = courierSteps.createCourier(VALID_COURIER_REQUEST_BODY);
        secondCreateResponse.then().statusCode(409).body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
        Response loginResponse = courierSteps.loginCourier(LOGIN_REQUEST_BODY);
        Integer courierId = courierSteps.extractCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);
    }

    @Test // Нельзя создать курьера без обязательных полей
          // для авторизации нужно передать все обязательные поля;
          // чтобы создать курьера, нужно передать в ручку все обязательные поля;
          // если одного из полей нет, запрос возвращает ошибку;
    public void courierCannotBeCreatedWithoutRequiredFields() {
        for (String body : INVALID_COURIER_REQUEST_BODIES) {
            Response response = courierSteps.createCourier(body);
            response.then().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
        }
    }

    @Test // если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
    public void loginUnavailableRegister(){
    Response loginUnavailableRegister = courierSteps.loginCourier(LOGIN_REQUEST_BODY);
    loginUnavailableRegister.then().statusCode(404).body("message", equalTo("Учетная запись не найдена"));
    }





}

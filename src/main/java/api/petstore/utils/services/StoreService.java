package api.petstore.utils.services;

import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.store.StoreOrder;

import static io.restassured.RestAssured.given;

public class StoreService extends RestService {

    public StoreService(String userSession) {
        super(userSession);
    }

    @Override
    protected String getBasePath() {
        return "/store/order/";
    }

    public StoreOrder createOrder(StoreOrder orderRequest) {
        return given()
                .body(orderRequest)
                .when()
                .post()
                .then().log().all()
                .extract().as(StoreOrder.class);
    }

    public<T> T getOrder(Long id, Class<T> clazz) {
        return given()
                .when()
                .get(id.toString())
                .then().log().all()
                .extract().as(clazz);
    }

    public ApiResponse deleteOrder(Long id) {
        return given()
                .when()
                .delete(id.toString())
                .then().log().all()
                .extract().as(ApiResponse.class);
    }

}

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
        return given().spec(REQ_SPEC)
                .body(orderRequest)
                .when()
                .post()
                .then().log().all()
                .extract().as(StoreOrder.class);
    }

    public StoreOrder getOrder(Long id) {
        return given().spec(REQ_SPEC)
                .when()
                .get(id.toString())
                .then().log().all()
                .extract().as(StoreOrder.class);
    }

    public ApiResponse deleteOrder(Long id) {
        return given().spec(REQ_SPEC)
                .when()
                .delete(id.toString())
                .then().log().all()
                .extract().as(ApiResponse.class);
    }

}

package api.petstore.utils.services;

import api.petstore.pojo.InfoMessage;
import api.petstore.pojo.pet.Pet;
import api.petstore.pojo.store.StoreOrder;

import static io.restassured.RestAssured.given;

public class StoreService extends RestService {
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

    public StoreOrder getOrder(Long id) {
        return given()
                .when()
                .get(id.toString())
                .then().log().all()
                .extract().as(StoreOrder.class);
    }

    public InfoMessage deleteOrder(Long id) {
        return given()
                .when()
                .delete(id.toString())
                .then().log().all()
                .extract().as(InfoMessage.class);
    }

}

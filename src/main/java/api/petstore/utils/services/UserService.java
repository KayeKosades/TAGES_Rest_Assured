package api.petstore.utils.services;

import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.store.StoreOrder;
import api.petstore.pojo.user.User;

import static io.restassured.RestAssured.given;

public class UserService extends RestService {

    public UserService(String userSession) {
        super(userSession);
    }

    @Override
    protected String getBasePath() { return "/user/"; }


    public ApiResponse createUser(User createUserRequest) {
        return given().spec(REQ_SPEC)
                .body(createUserRequest)
                .when()
                .post()
                .then().log().all()
                .extract().as(ApiResponse.class);
    }

}

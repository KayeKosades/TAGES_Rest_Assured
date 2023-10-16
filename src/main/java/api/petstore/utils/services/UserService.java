package api.petstore.utils.services;

import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.pet.Pet;
import api.petstore.pojo.store.StoreOrder;
import api.petstore.pojo.user.User;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserService extends RestService {

    public UserService(String userSession) {
        super(userSession);
    }

    @Override
    protected String getBasePath() { return "/user/"; }


    public ApiResponse createUser(User createUserRequest) {
        return given()
                .body(createUserRequest)
                .when()
                .post()
                .then().log().all()
                .extract().as(ApiResponse.class);
    }

    public<T> T getUser(String username, Class<T> clazz) {
        return given()
                .when()
                .get(username)
                .then().log().all()
                .extract().as(clazz);
    }

    public ApiResponse createUserWithList(List<User> userList) {
        return given()
                .body(userList)
                .when()
                .post("createWithList")
                .then().log().all()
                .extract().as(ApiResponse.class);
    }

    public ApiResponse updateUser(User userRequest, String userName) {
        return given()
                .body(userRequest)
                .when()
                .put(userName)
                .then().log().all()
                .extract().as(ApiResponse.class);
    }

    public ApiResponse deleteUser(String userName) {
        return given()
                .when()
                .delete(userName)
                .then().log().all()
                .extract().as(ApiResponse.class);
    }
}

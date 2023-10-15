package api.petstore.utils;

import api.petstore.pojo.ApiResponse;
import api.petstore.utils.services.PetService;
import api.petstore.utils.services.StoreService;
import api.petstore.utils.services.UserService;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;

import static io.restassured.RestAssured.given;

public class RestApi {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    private static PetService pet;
    private static StoreService store;
    private static UserService user;

    String userSession;
    boolean isLogged;
    private RestApi(String userSession) {
        this.userSession = userSession;

        pet = new PetService(userSession);
        store = new StoreService(userSession);
        user = new UserService(userSession);

        isLogged = true;
    }

    public static RestApi loginAs(String login, String password) {
        ApiResponse successLoggedResponse = given()
                .baseUri(BASE_URL)
                .basePath("/user")
                .when()
                .get("/login?username=%s&password=%s".formatted(login, password))
                .then().log().all()
                .extract().as(ApiResponse.class);
        assert(successLoggedResponse.getCode()==200);
        String successMessage = successLoggedResponse.getMessage();
        assert(successMessage.matches("^logged in user session:\\d+$"));

        String userSession = successMessage.split(":")[1];
        return new RestApi(userSession);
    }

    public Object logout() {
        ApiResponse successLogoutResponse = given()
                .baseUri(BASE_URL)
                .basePath("/user")
                .when()
                .get("/logout")
                .then().log().all()
                .extract().as(ApiResponse.class);
        assert(successLogoutResponse.getCode()==200);
        assert(successLogoutResponse.getMessage().equals("ok"));
        return null;
    }


    public PetService pet() {
        return pet;
    }

    public StoreService store() {
        return store;
    }

    public UserService user() {
        return user;
    }

}

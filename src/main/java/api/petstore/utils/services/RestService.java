package api.petstore.utils.services;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public abstract class RestService {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    protected RequestSpecification REQ_SPEC;
    protected ResponseSpecification RES_SPEC;

    protected String userSession;

    protected abstract String getBasePath();

    public RestService(String userSession) {
        this.userSession = userSession;
        REQ_SPEC = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(getBasePath())
                .setContentType(ContentType.JSON)
                .build();
        installSpecification();
    }

    public void setResponseSpecOK200() {
        RES_SPEC = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        installSpecification();
    }

    public void setResponseSpecError404() {
        RES_SPEC = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
        installSpecification();
    }

    public void setResponseSpecError400() {
        RES_SPEC = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
        installSpecification();
    }

    public void setResponseSpecError500() {
        RES_SPEC = new ResponseSpecBuilder()
                .expectStatusCode(500)
                .build();
        installSpecification();
    }

    public void installSpecification() {
        RestAssured.requestSpecification = REQ_SPEC;
        RestAssured.responseSpecification = RES_SPEC;
    }
}

package api.petstore.utils.services;

import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.pet.Pet;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetService extends RestService {

    public PetService(String userSession) {
        super(userSession);
    }

    @Override
    protected String getBasePath() {
        return "/pet/";
    }

    //добавление питомца
    public Pet createPet(Pet petRequest) {
        return given().spec(REQ_SPEC)
                .body(petRequest)
                .when()
                .post()
                .then().log().all()
                .extract().as(Pet.class);
    }

    public Pet getPet(Long id) {
        return given().spec(REQ_SPEC)
                .when()
                .get(id.toString())
                .then().log().all()
                .extract().as(Pet.class);
    }

    public ApiResponse deletePet(Long id) {
        return given().spec(REQ_SPEC)
                .when()
                .delete(id.toString())
                .then().log().all()
                .extract().as(ApiResponse.class);
    }

    public List<Pet> findPetByStatus(String status) {
        return given().spec(REQ_SPEC)
                .when()
                .get("findByStatus?status="+status)
                .then().log().all()
                .extract().body().jsonPath().getList(".", Pet.class);
    }
}

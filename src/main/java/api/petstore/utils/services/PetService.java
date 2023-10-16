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
        return given()
                .body(petRequest)
                .when()
                .post()
                .then().log().all()
                .extract().as(Pet.class);
    }

    //получение питомца по id и возвращение либо питомца, либо ошибки
    public <T> T getPet(Long id, Class<T> clazz) {
        return given()
                .when()
                .get(id.toString())
                .then().log().all()
                .extract().as(clazz);
    }

    //изменение питомца по id
    public Pet updatePet(Pet petRequest) {
        return given()
                .body(petRequest)
                .when()
                .put()
                .then().log().all()
                .extract().as(Pet.class);
    }

    //удаление питомца по id
    public ApiResponse deletePet(Long id) {
        return given()
                .when()
                .delete(id.toString())
                .then().log().all()
                .extract().as(ApiResponse.class);
    }

    public List<Pet> findPetByStatus(String status) {
        return given()
                .when()
                .get("findByStatus?status="+status)
                .then().log().all()
                .extract().body().jsonPath().getList(".", Pet.class);
    }
}

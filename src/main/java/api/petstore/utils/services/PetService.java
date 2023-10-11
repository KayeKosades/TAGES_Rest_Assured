package api.petstore.utils.services;

import api.petstore.pojo.InfoMessage;
import api.petstore.pojo.pet.Pet;

import static io.restassured.RestAssured.given;

public class PetService extends RestService {
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

    public Pet getPet(Long id) {
        return given()
                .when()
                .get(id.toString())
                .then().log().all()
                .extract().as(Pet.class);
    }

    public InfoMessage deletePet(Long id) {
        return given()
                .when()
                .delete(id.toString())
                .then().log().all()
                .extract().as(InfoMessage.class);
    }

}

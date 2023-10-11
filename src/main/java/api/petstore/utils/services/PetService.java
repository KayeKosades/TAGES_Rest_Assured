package api.petstore.utils.services;

import api.petstore.pojo.pet.Pet;

import static io.restassured.RestAssured.given;

public class PetService extends RestService {
    @Override
    protected String getBasePath() {
        return "/pet";
    }

    public Pet createPet(Pet petRequest) {
        return given()
                .body(petRequest)
                .when()
                .post()
                .then().log().all()
                .extract().as(Pet.class);
    }
}

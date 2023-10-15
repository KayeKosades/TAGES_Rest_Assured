package api.petstore.pet;


import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.pet.Pet;
import api.petstore.utils.RestApi;
import api.petstore.utils.generators.PetGenerator;
import api.petstore.utils.services.PetService;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetTests {

    private static RestApi api;
    private static Pet validPet;

    @BeforeAll
    public static void setup() {
        validPet = new PetGenerator().generateValidObject();
        api = RestApi.loginAs("petTestsLogin", "petTestsPassword");
    }

    //Позитивные тесты:
    @Test
    @Order(1)
    //Добавление питомца с валидными данными
    public void createPetTest() {
        api.pet().setResponseSpecOK200();
        Pet successCreatedPet = api.pet().createPet(validPet);

        assertThat(validPet).isEqualTo(successCreatedPet);
    }

    @Test
    @Order(2)
    public void getPetTest() {
        api.pet().setResponseSpecOK200();
        Pet successGottenPet = api.pet().getPet(validPet.getId());
        assertThat(validPet).isEqualTo(successGottenPet);
    }

    @Test
    @Order(3)
    public void findPetByStatusTest() {
        api.pet().setResponseSpecOK200();
        List<Pet> foundPets = api.pet().findPetByStatus(validPet.getStatus());
        assertThat(foundPets.contains(validPet));
    }

    @Test
    @Order(4)
    public void deletePetTest() {
        api.pet().setResponseSpecOK200();
        ApiResponse successDeletedPetMessage = api.pet().deletePet(validPet.getId());
        assertThat(successDeletedPetMessage.getCode()).isEqualTo(200);
        assertThat(successDeletedPetMessage.getMessage()).isEqualTo(validPet.getId().toString());
    }


}

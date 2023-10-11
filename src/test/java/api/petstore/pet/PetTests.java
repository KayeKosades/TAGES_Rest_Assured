package api.petstore.pet;


import api.petstore.pojo.pet.Pet;
import api.petstore.utils.services.PetService;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import static api.petstore.utils.PetGenerator.getValidPet;
import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetTests {

    private static PetService petApi;
    private static Pet validPet;

    @BeforeAll
    public static void setup() {
        validPet = getValidPet();
        petApi = new PetService();
    }

    //Позитивные тесты:
    @Test
    @Order(1)
    //Добавление питомца с валидными данными
    public void addValidPetTest() throws Exception {
        petApi.setResponseSpecOK200();
        Pet successCreatedPet = petApi.createPet(validPet);

        assertThat(validPet).isEqualTo(successCreatedPet);
    }

    @Test
    @Order(2)
    public void getValidPetTest() {
        petApi.setResponseSpecOK200();
        Pet successGottenPet = petApi.getPet(validPet.getId());
        assertThat(validPet).isEqualTo(successGottenPet);
    }

    @Test
    @Order(3)
    public void deletePetTest() {
        petApi.setResponseSpecOK200();
        Long id = validPet.getId();

    }

}
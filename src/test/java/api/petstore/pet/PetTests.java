package api.petstore.pet;


import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.pet.Pet;
import api.petstore.utils.RestApi;
import api.petstore.utils.generators.Generator;
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
    //Обновление существующего питомца
    public void updatePetTest() {
        api.pet().setResponseSpecOK200();

        //генерация питомца с другими тестовыми данными, но тем же id
        Long currentId = validPet.getId();
        validPet = new PetGenerator().generateValidObject();
        validPet.setId(currentId);

        Pet successUpdatedPet = api.pet().updatePet(validPet);

        assertThat(validPet).isEqualTo(successUpdatedPet);
    }

    @Test
    @Order(3)
    //получение ранее добавленного и обновленного питомца по id
    public void getPetTest() {
        api.pet().setResponseSpecOK200();
        Pet successGottenPet = api.pet().getPet(validPet.getId(), Pet.class);
        assertThat(validPet).isEqualTo(successGottenPet);
    }

    @Test
    @Order(4)
    //проверка, что при получении всех питомцев с тем же статусом,
    //что и у добавленного, добавленный тоже есть в списке
    public void findPetByStatusTest() {
        api.pet().setResponseSpecOK200();
        List<Pet> foundPets = api.pet().findPetByStatus(validPet.getStatus());
        assertThat(foundPets.contains(validPet));
    }

    @Test
    @Order(5)
    //удаление ранее созданного питомца
    public void deletePetTest() {
        api.pet().setResponseSpecOK200();
        ApiResponse successDeletedPetMessage = api.pet().deletePet(validPet.getId());
        assertThat(successDeletedPetMessage.getCode()).isEqualTo(200);
        assertThat(successDeletedPetMessage.getMessage()).isEqualTo(validPet.getId().toString());
    }

    //негативные тесты
    //
    //попытка получения ранее удалённого питомца по id
    @Test
    @Order(6)
    public void getNotExistPet() {
        api.pet().setResponseSpecError404();
        ApiResponse petNotFoundError = api.pet().getPet(validPet.getId(), ApiResponse.class);

        assertThat(petNotFoundError.getCode()).isEqualTo(1);
        assertThat(petNotFoundError.getType()).isEqualTo("error");
        assertThat(petNotFoundError.getMessage()).isEqualTo("Pet not found");
    }

    //поиск питомца по невалидному статусу
    @Test
    @Order(7)
    public void findByInvalidStatus() {
        api.pet().setResponseSpecOK200();
        List<Pet> foundPets = api.pet().findPetByStatus("dgdfgdfgdfgfd");
        assertThat(foundPets.isEmpty());
    }

    //поиск питомца по невалидному id
    @Test
    @Order(8)
    public void getPetByInvalidId() {
        api.pet().setResponseSpecError404();
        ApiResponse petNotFoundError = api.pet().getPet(-validPet.getId(), ApiResponse.class);

        assertThat(petNotFoundError.getCode()).isEqualTo(1);
        assertThat(petNotFoundError.getType()).isEqualTo("error");
        assertThat(petNotFoundError.getMessage()).isEqualTo("Pet not found");
    }


}

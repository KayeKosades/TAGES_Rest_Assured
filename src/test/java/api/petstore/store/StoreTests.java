package api.petstore.store;

import api.petstore.pojo.InfoMessage;
import api.petstore.pojo.pet.Pet;
import api.petstore.pojo.store.StoreOrder;
import api.petstore.utils.generators.OrderGenerator;
import api.petstore.utils.services.StoreService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StoreTests {
    private static StoreService storeApi;
    private static StoreOrder validOrder;

    @BeforeAll
    public static void setup() {
        validOrder = new OrderGenerator().generateValidObject();
        storeApi = new StoreService();
    }

    //Позитивные тесты:
    @Test
    @Order(1)
    //Добавление питомца с валидными данными
    public void addValidOrderTest() {
        //storeApi.setResponseSpecOK200();
        StoreOrder successCreatedOrder = storeApi.createOrder(validOrder);

        assertThat(validOrder).isEqualTo(successCreatedOrder);
    }

    @Test
    @Order(2)
    public void getValidOrderTest() {
        storeApi.setResponseSpecOK200();
        StoreOrder successGottenOrder = storeApi.getOrder(validOrder.getId());
        assertThat(validOrder).isEqualTo(successGottenOrder);
    }

    @Test
    @Order(3)
    public void deleteOrderTest() {
        storeApi.setResponseSpecOK200();
        InfoMessage successDeletedPetMessage = storeApi.deleteOrder(validOrder.getId());
        assertThat(successDeletedPetMessage.getCode()).isEqualTo(200);
        assertThat(successDeletedPetMessage.getMessage()).isEqualTo(validOrder.getId().toString());
    }
}

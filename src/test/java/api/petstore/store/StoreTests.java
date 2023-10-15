package api.petstore.store;

import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.store.StoreOrder;
import api.petstore.utils.generators.OrderGenerator;
import api.petstore.utils.services.StoreService;
import org.junit.jupiter.api.*;

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
        storeApi.setResponseSpecOK200();
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
        ApiResponse successDeletedPetMessage = storeApi.deleteOrder(validOrder.getId());
        assertThat(successDeletedPetMessage.getCode()).isEqualTo(200);
        assertThat(successDeletedPetMessage.getMessage()).isEqualTo(validOrder.getId().toString());
    }
}

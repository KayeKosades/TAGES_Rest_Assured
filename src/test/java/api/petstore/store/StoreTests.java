package api.petstore.store;

import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.store.StoreOrder;
import api.petstore.utils.RestApi;
import api.petstore.utils.generators.OrderGenerator;
import api.petstore.utils.services.StoreService;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StoreTests {
    private static RestApi api;
    private static StoreOrder validOrder;

    @BeforeAll
    public static void setup() {
        validOrder = new OrderGenerator().generateValidObject();
        api = RestApi.loginAs("storeTestsLogin", "storeTestsPassword");
    }

    //Позитивные тесты:
    @Test
    @Order(1)
    //Добавление питомца с валидными данными
    public void addValidOrderTest() {
        api.store().setResponseSpecOK200();
        StoreOrder successCreatedOrder = api.store().createOrder(validOrder);

        assertThat(validOrder).isEqualTo(successCreatedOrder);
    }

    @Test
    @Order(2)
    public void getValidOrderTest() {
        api.store().setResponseSpecOK200();
        StoreOrder successGottenOrder = api.store().getOrder(validOrder.getId());
        assertThat(validOrder).isEqualTo(successGottenOrder);
    }

    @Test
    @Order(3)
    public void deleteOrderTest() {
        api.store().setResponseSpecOK200();
        ApiResponse successDeletedPetMessage = api.store().deleteOrder(validOrder.getId());
        assertThat(successDeletedPetMessage.getCode()).isEqualTo(200);
        assertThat(successDeletedPetMessage.getMessage()).isEqualTo(validOrder.getId().toString());
    }
}

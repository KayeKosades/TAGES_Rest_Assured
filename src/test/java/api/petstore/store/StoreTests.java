package api.petstore.store;

import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.pet.Pet;
import api.petstore.pojo.store.StoreOrder;
import api.petstore.utils.RestApi;
import api.petstore.utils.generators.OrderGenerator;
import api.petstore.utils.services.StoreService;
import org.junit.jupiter.api.*;

import java.util.List;

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
    //Добавление заказа с валидными данными
    public void addValidOrderTest() {
        api.store().setResponseSpecOK200();
        StoreOrder successCreatedOrder = api.store().createOrder(validOrder);

        assertThat(validOrder).isEqualTo(successCreatedOrder);
    }

    //получение заказа по id
    @Test
    @Order(2)
    public void getValidOrderTest() {
        api.store().setResponseSpecOK200();
        StoreOrder successGottenOrder = api.store().getOrder(validOrder.getId(), StoreOrder.class);
        assertThat(validOrder).isEqualTo(successGottenOrder);
    }

    //удаление заказа по id
    @Test
    @Order(3)
    public void deleteOrderTest() {
        api.store().setResponseSpecOK200();
        ApiResponse successDeletedPetMessage = api.store().deleteOrder(validOrder.getId());
        assertThat(successDeletedPetMessage.getCode()).isEqualTo(200);
        assertThat(successDeletedPetMessage.getMessage()).isEqualTo(validOrder.getId().toString());
    }

    //негативные тесты
    //
    //попытка получения ранее удалённого заказа по id
    @Test
    @Order(4)
    public void getNotExistOrder() {
        api.store().setResponseSpecError404();
        ApiResponse orderNotFoundError = api.store().getOrder(validOrder.getId(), ApiResponse.class);

        assertThat(orderNotFoundError.getCode()).isEqualTo(1);
        assertThat(orderNotFoundError.getType()).isEqualTo("error");
        assertThat(orderNotFoundError.getMessage()).isEqualTo("Order not found");
    }

    //попытка удалить уже удаленный заказ
    @Test
    @Order(5)
    public void deleteNotExistOrder() {
        api.store().setResponseSpecError404();
        ApiResponse orderNotFoundError = api.store().deleteOrder(validOrder.getId());

        assertThat(orderNotFoundError.getCode()).isEqualTo(404);
        assertThat(orderNotFoundError.getType()).isEqualTo("unknown");
        assertThat(orderNotFoundError.getMessage()).isEqualTo("Order Not Found");
    }

    //поиск заказа по невалидному id
    @Test
    @Order(6)
    public void getPetByInvalidId() {
        api.store().setResponseSpecError404();
        ApiResponse petNotFoundError = api.store().getOrder(-validOrder.getId(), ApiResponse.class);

        assertThat(petNotFoundError.getCode()).isEqualTo(1);
        assertThat(petNotFoundError.getType()).isEqualTo("error");
        assertThat(petNotFoundError.getMessage()).isEqualTo("Order not found");
    }
}

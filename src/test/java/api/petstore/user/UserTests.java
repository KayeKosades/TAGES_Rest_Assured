package api.petstore.user;

import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.store.StoreOrder;
import api.petstore.pojo.user.User;
import api.petstore.utils.generators.OrderGenerator;
import api.petstore.utils.generators.UserGenerator;
import api.petstore.utils.services.StoreService;
import api.petstore.utils.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTests {
    private static UserService userApi;
    private static User validUser;

    @BeforeAll
    public static void setup() {
        validUser = new UserGenerator().generateValidObject();
        userApi = new UserService();
    }

    //Позитивные тесты:
    @Test
    @Order(1)
    //Добавление питомца с валидными данными
    public void addValidUserTest() {
        userApi.setResponseSpecOK200();
        ApiResponse successCreatedUserResponse = userApi.createUser(validUser);

        assertThat(successCreatedUserResponse.getCode()).isEqualTo(200);
    }


}

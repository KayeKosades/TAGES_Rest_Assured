package api.petstore.user;

import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.store.StoreOrder;
import api.petstore.pojo.user.User;
import api.petstore.utils.RestApi;
import api.petstore.utils.generators.OrderGenerator;
import api.petstore.utils.generators.UserGenerator;
import api.petstore.utils.services.StoreService;
import api.petstore.utils.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTests {
    private static RestApi api;
    private static User validUser;

    @BeforeAll
    public static void setup() {
        validUser = new UserGenerator().generateValidObject();
        api = RestApi.loginAs("userTestsLogin", "userTestsPassword");
    }

    //Позитивные тесты:
    @Test
    @Order(1)
    //Добавление питомца с валидными данными
    public void addValidUserTest() {
        api.user().setResponseSpecOK200();
        ApiResponse successCreatedUserResponse = api.user().createUser(validUser);
        assertThat(successCreatedUserResponse.getCode()).isEqualTo(200);
    }

    @Test
    @Order(2)
    //Добавление питомца с валидными данными
    public void getValidUserTest() {
        api.user().setResponseSpecOK200();
        User successGottenUser = api.user().getUser(validUser.getUsername());
        assertThat(validUser).isEqualTo(successGottenUser);
    }

    @Test
    @Order(3)
    public void createUserWithList() {
        api.user().setResponseSpecOK200();
        List<User> userList = new UserGenerator().validUserList(10);
        ApiResponse successCreatedUserListResponse = api.user().createUserWithList(userList);
        assertThat(successCreatedUserListResponse.getCode()).isEqualTo(200);

        //проверка, что пользователи из списка добавились в систему
        for(User u : userList) {
            User successGottenUser = api.user().getUser(u.getUsername());
            assertThat(u).isEqualTo(successGottenUser);
        }
    }


}

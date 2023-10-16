package api.petstore.user;

import api.petstore.pojo.ApiResponse;
import api.petstore.pojo.pet.Pet;
import api.petstore.pojo.store.StoreOrder;
import api.petstore.pojo.user.User;
import api.petstore.utils.RestApi;
import api.petstore.utils.generators.OrderGenerator;
import api.petstore.utils.generators.PetGenerator;
import api.petstore.utils.generators.UserGenerator;
import api.petstore.utils.services.StoreService;
import api.petstore.utils.services.UserService;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    //Добавление пользователя с валидными данными
    public void createUserTest() {
        api.user().setResponseSpecOK200();
        ApiResponse successCreatedUserResponse = api.user().createUser(validUser);
        assertThat(successCreatedUserResponse.getCode()).isEqualTo(200);
    }

    @Test
    @Order(2)
    //Обновление пользователя с валидными данными
    public void updateUserTest() {
        api.user().setResponseSpecOK200();

        //генерация пользователя с другими тестовыми данными, но тем же id
        String currentUserName = validUser.getUsername();

        validUser = new UserGenerator().generateValidObject();

        ApiResponse successUpdatedUser = api.user().updateUser(validUser, currentUserName);

        assertThat(successUpdatedUser.getCode()).isEqualTo(200);
        assertThat(successUpdatedUser.getType()).isEqualTo("unknown");
        assertThat(successUpdatedUser.getMessage().matches("^\\d+$"));
    }

    @Test
    @Order(3)
    //Получение ранее созданного и обновлённого пользователя
    public void getValidUserTest() {
        api.user().setResponseSpecOK200();
        User successGottenUser = api.user().getUser(validUser.getUsername(), User.class);
        assertThat(validUser).isEqualTo(successGottenUser);
    }

    //создание пользователей из массива
    @Test
    @Order(4)
    public void createUserWithListTest() {
        api.user().setResponseSpecOK200();
        List<User> userList = new UserGenerator().validUserList(10);
        ApiResponse successCreatedUserListResponse = api.user().createUserWithList(userList);
        assertThat(successCreatedUserListResponse.getCode()).isEqualTo(200);

        //проверка, что пользователи из списка добавились в систему
        for(User u : userList) {
            User successGottenUser = api.user().getUser(u.getUsername(), User.class);
            assertThat(u).isEqualTo(successGottenUser);
        }
    }

    //удаление ранее созданного пользователя
    @Test
    @Order(5)
    public void deleteUserTest() {
        api.user().setResponseSpecOK200();
        ApiResponse successDeletedUserMessage = api.user().deleteUser(validUser.getUsername());

        assertThat(successDeletedUserMessage.getCode()).isEqualTo(200);
        assertThat(successDeletedUserMessage.getType()).isEqualTo("unknown");
        assertThat(successDeletedUserMessage.getMessage()).isEqualTo(validUser.getUsername());
    }

    //негативные тесты
    //
    //попытка получения удалённого пользователя
    @Test
    @Order(6)
    public void getNotExistUser() {
        api.user().setResponseSpecError404();
        ApiResponse userNotFoundError = api.user().getUser(validUser.getUsername(), ApiResponse.class);

        assertThat(userNotFoundError.getCode()).isEqualTo(1);
        assertThat(userNotFoundError.getType()).isEqualTo("error");
        assertThat(userNotFoundError.getMessage()).isEqualTo("User not found");
    }
}

package api.petstore.utils.generators;

import api.petstore.pojo.store.StoreOrder;
import api.petstore.pojo.user.User;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class UserGenerator extends Generator {

    @Override
    public User generateValidObject() {

            Long id = randomId();
            String username = randomString(30);
            String firstName = randomString(30);;
            String lastName = randomString(30);;
            String email = randomString(30);;
            String password = randomString(30);;
            String phone = randomString(30);;
            Integer userStatus = randomInt(10000);

            return User.builder()
                    .id(id)
                    .username(username)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .phone(phone)
                    .userStatus(userStatus)
                    .build();
    }


}

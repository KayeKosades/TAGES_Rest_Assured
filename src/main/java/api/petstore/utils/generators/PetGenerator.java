package api.petstore.utils.generators;

import api.petstore.pojo.pet.Category;
import api.petstore.pojo.pet.Pet;
import api.petstore.pojo.pet.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PetGenerator extends Generator<Pet> {

    @Override
    public Pet generateValidObject() {
        Long id = randomId();
        Category category = new Category(randomId(), randomString(20));
        String name = randomString(20);
        List<String> photoUrls = Arrays.asList("https://4lapy.ru/resize/1664x1000/upload/medialibrary/270/2703fd71a17c0843c0b91bbe28c4fe0f.jpg");
        Tag tag = new Tag(randomId(), randomString(20));
        List<Tag> tags = Arrays.asList(tag);
        String status = randomStatus();

        return Pet.builder()
                .id(id)
                .category(category)
                .name(name)
                .photoUrls(photoUrls)
                .tags(tags)
                .status(status)
                .build();
    }

    private String randomStatus() {
        List<String> statuses = Arrays.asList("available", "pending", "sold");
        return statuses.get(new Random().nextInt(0, statuses.size()));
    }
}

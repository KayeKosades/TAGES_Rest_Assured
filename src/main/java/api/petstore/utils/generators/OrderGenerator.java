package api.petstore.utils.generators;

import api.petstore.pojo.store.StoreOrder;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OrderGenerator extends Generator<StoreOrder> {

    @Override
    public StoreOrder generateValidObject() {

        Long id = randomId();
        Long petId = randomId();
        Integer quantity = randomInt(100);

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .toFormatter()
                .withZone(ZoneOffset.UTC);
        OffsetDateTime shipDate = OffsetDateTime.parse(OffsetDateTime.now().format(formatter));

        String status = randomStatus();
        Boolean complete = randomBool();

        return StoreOrder.builder()
                .id(id)
                .petId(petId)
                .quantity(quantity)
                .shipDate(shipDate)
                .status(status)
                .complete(complete)
                .build();
    }

    private String randomStatus() {
        List<String> statuses = Arrays.asList("placed", "approved", "delivered");
        return statuses.get(new Random().nextInt(0, statuses.size()));
    }

}

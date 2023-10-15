package api.petstore.utils.generators;

import java.util.Random;
import java.util.UUID;

public abstract class Generator<T> {
    public abstract T generateValidObject();

    protected String randomString(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }

    protected Long randomLong(Long max) {
        return new Random().nextLong(max);
    }

    protected Integer randomInt(Integer max) {
        return new Random().nextInt(max);
    }

    protected Integer randomInt(Integer min, Integer max) { return new Random().nextInt(max-min+1); }

    protected Long randomId() {
        return randomLong(1000L);
    }

    protected Boolean randomBool() {
        return randomInt(2) == 1;
    }
}

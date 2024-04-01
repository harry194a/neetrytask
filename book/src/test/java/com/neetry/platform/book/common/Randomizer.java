package com.neetry.platform.book.common;

import org.instancio.Instancio;
import org.instancio.InstancioApi;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.instancio.support.ThreadLocalRandom;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Harutyun Badeyan
 * Date: 01.04.24
 * Time: 01:29
 */
public class Randomizer {
    public static <T> T randomObject(Class<T> clazz) {
        return of(clazz).create();
    }
    
    public static <T> List<T> randomList(Class<T> clazz) {
        return of(clazz).stream().limit(10).collect(Collectors.toList());
    }

    public static <T> InstancioApi<T> of(Class<T> clazz) {
        var settings = Settings.defaults()
                .set(Keys.STRING_FIELD_PREFIX_ENABLED, true)
                .set(Keys.BEAN_VALIDATION_ENABLED, true)
                .set(Keys.COLLECTION_MIN_SIZE, 1)
                .lock();

        return Instancio.of(clazz)
                .withSettings(settings);
    }
}

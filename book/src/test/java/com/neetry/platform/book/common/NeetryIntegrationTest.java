package com.neetry.platform.book.common;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Harutyun Badeyan
 * Date: 01.04.24
 * Time: 01:16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest()
@Import(TestDataSourceConfig.class)
@ActiveProfiles("test")
@Tag("integrationTest")
public @interface NeetryIntegrationTest {
}

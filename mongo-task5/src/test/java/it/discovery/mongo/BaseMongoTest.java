package it.discovery.mongo;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

//TODO fix issue with TestContainer not clearing database when using @SpringBootTest
@DataMongoTest
@ContextConfiguration(classes = MongoApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
public abstract class BaseMongoTest {

    @Container
    static GenericContainer mongo = new GenericContainer("mongo:6").withExposedPorts(27017);

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.port", () -> mongo.getMappedPort(27017));
    }

}

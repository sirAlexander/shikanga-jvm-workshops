package uk.co.shikanga.workshop.superheroes.hero;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HeroResourceTest {

    @Container
    private static final PostgreSQLContainer DATABASE = new PostgreSQLContainer<>("postgres:10.5")
            .withDatabaseName("heroes_database")
            .withUsername("superman")
            .withPassword("superman")
            .withExposedPorts(5432);

    @BeforeAll
    private static void configure() {
        System.setProperty("quarkus.datasource.url", DATABASE.getJdbcUrl());
    }

    @AfterAll
    private static void cleanup() {
        System.clearProperty("quarkus.datasource.url");
    }

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/heroes")
          .then()
             .statusCode(200)
             .body(is("hello hero"));
    }

}
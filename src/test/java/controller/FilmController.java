package controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;


@QuarkusTest
public class FilmController {


    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/helloworld")
                .then()
                .statusCode(200)
                .body(is("Hello from Quarkus REST"));
    }

    @Test
    public  void testGetFilm(){
        given()
                .when().get("/film/5")
                .then()
                .statusCode(200)
                .body(containsString("AFRICAN EGG"));
    }


}

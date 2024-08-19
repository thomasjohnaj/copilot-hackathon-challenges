package example;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class ArithmeticIntegrationTest {

    private static Server server;

    @BeforeAll
    static void startJetty() throws Exception {
        JsonConfig jsonConfig = JsonConfig.jsonConfig()
            .numberReturnType(JsonPathConfig.NumberReturnType.DOUBLE);

        RestAssured.config = RestAssured.config()
            .jsonConfig(jsonConfig);

        server = new Server(3000);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setWar("src/main/webapp");
        server.setHandler(context);

        server.start();
    }

    @AfterAll
    static void stopJetty() throws Exception {
        server.stop();
    }

    @Nested
    class Validation {
        @Test public void rejectsMissingOperation() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operand1=21&operand2=21");

            response.then()
                .statusCode(400)
                .body("error", equalTo("Missing operation"));
        }

        @Test public void rejectsInvalidOperation() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=foo&operand1=21&operand2=21");

            response.then()
                .statusCode(400)
                .body("error", equalTo("Invalid operation: foo"));
        }
    }

    @Nested
    class Addition {
        @Test
        public void addTwoPositiveIntegers() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=add&operand1=21&operand2=21");

            response.then()
                .statusCode(200)
                .body("result", equalTo(42.0));
        }

        @Test
        public void addZeroToInteger() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=add&operand1=21&operand2=0");

            response.then()
                .statusCode(200)
                .body("result", equalTo(21.0));
        }

        @Test
        public void addNegativeIntegerToPositiveInteger() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=add&operand1=21&operand2=-21");

            response.then()
                .statusCode(200)
                .body("result", equalTo(0.0));
        }

        @Test
        public void addTwoNegativeIntegers() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=add&operand1=-21&operand2=-21");

            response.then()
                .statusCode(200)
                .body("result", equalTo(-42.0));
        }

        @Test
        public void addIntegerToFloatingPointNumber() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=add&operand1=2.5&operand2=-5");

            response.then()
                .statusCode(200)
                .body("result", equalTo(-2.5));
        }
    }

    // TODO: add tests for subtraction


    @Nested
    class Multiplication {
        @Test
        public void multiplyTwoPositiveIntegers() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=multiply&operand1=21&operand2=21");

            response.then()
                .statusCode(200)
                .body("result", equalTo(441.0));
        }

        @Test
        public void multiplyZeroToInteger() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=multiply&operand1=21&operand2=0");

            response.then()
                .statusCode(200)
                .body("result", equalTo(0.0));
        }

        @Test
        public void multiplyNegativeIntegerToPositiveInteger() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=multiply&operand1=21&operand2=-21");

            response.then()
                .statusCode(200)
                .body("result", equalTo(-441.0));
        }

        @Test
        public void multiplyTwoNegativeIntegers() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=multiply&operand1=-21&operand2=-21");

            response.then()
                .statusCode(200)
                .body("result", equalTo(441.0));
        }

        @Test
        public void multiplyIntegerToFloatingPointNumber() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=multiply&operand1=2.5&operand2=-5");

            response.then()
                .statusCode(200)
                .body("result", equalTo(-12.5));
        }
    }

    @Nested
    class Division {
        @Test
        public void divideTwoPositiveIntegers() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=divide&operand1=21&operand2=21");

            response.then()
                .statusCode(200)
                .body("result", equalTo(1.0));
        }

        @Test
        public void divideZeroByInteger() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=divide&operand1=0&operand2=21");

            response.then()
                .statusCode(200)
                .body("result", equalTo(0.0));
        }

        @Test
        public void divideIntegerByZero() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=divide&operand1=21&operand2=0");

            response.then()
                .statusCode(400)
                .body("error", equalTo("Invalid result: Infinity"));
        }

        @Test
        public void divideNegativeIntegerByPositiveInteger() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=divide&operand1=21&operand2=-21");

            response.then()
                .statusCode(200)
                .body("result", equalTo(-1.0));
        }

        @Test
        public void divideTwoNegativeIntegers() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=divide&operand1=-21&operand2=-21");

            response.then()
                .statusCode(200)
                .body("result", equalTo(1.0));
        }

        @Test
        public void divideIntegerByFloatingPointNumber() {
            Response response = RestAssured.given()
                .when()
                .get("http://localhost:3000/arithmetic?operation=divide&operand1=2.5&operand2=-5");

            response.then()
                .statusCode(200)
                .body("result", equalTo(-0.5));
        }
    }
}

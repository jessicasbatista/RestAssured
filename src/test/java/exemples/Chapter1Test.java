package exemples;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Chapter1Test {

    /*Hamcrest Matchers mais usados:
        Equal to -> assert that usando jpath (mesmo conceito do xpath para html)
        has item -> verifica se tem determinado item em uma coleção, não usa jpath
        has size -> verifica o núemro de elementos de uma coleção*/

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    
    @BeforeClass
    public static void createRequestSpecification(){

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build();
    }

    @BeforeClass
    public static void createResponseSpecification(){

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills(){

        given().
                spec(requestSpec).
        when().
            get("us/90210").
        then().
            spec(responseSpec).
        and().
            assertThat().
            body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectCalifornia(){

        given().
                spec(requestSpec).
        when().
                get("us/90210").
        then().
                spec(responseSpec).
        and().
                assertThat().
                body("places[0].'state'", equalTo("California"));
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectContainsBeverlyHills(){

        given().
                spec(requestSpec).
        when().
                get("us/90210").
        then().
                spec(responseSpec).
        and().
                assertThat().
                body("places. 'place name'", hasItem("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_checkNumberOfPlaceNamesInResponseBody_expectOne(){

        given().
                spec(requestSpec).
        when().
            get("us/90210").
        then().
                spec(responseSpec).
        and().
            assertThat().
            body("places. 'place name'", hasSize(1));
    }
}

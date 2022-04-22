package exemples;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Chapter2Test {

    @Test
    public void requestUsZipCode90210_checkStatusCode_expectHttp200(){

        given().
        when().
              get("http://api.zippopotam.us/us/90210").
        then().
              assertThat().
              statusCode(200);
    }

    @Test
    public void requestUsZipCode90210_checkContentType_expectApplicationJson(){

        given().
               when().
               get("http://api.zippopotam.us/us/90210").
        then().
              assertThat().
              contentType(ContentType.JSON);
    }

    @Test
    public void requestUsZipCode90210_logRequestAndResponseDetails(){

        given().
               log().all().
        when().
              get("http://api.zippopotam.us/us/90210").
        then().
              log().body();
    }
}

package exemples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Chapter3Test {

    @DataProvider
    public static Object[][] zipCodeAndPlaces(){
        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"}
        };
    }

    @Test(dataProvider = "zipCodeAndPlaces")
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills(String countryCode, String zipCode, String expectedPlaceName){

        given().
               pathParam("countryCode", countryCode).
               pathParam("zipCode", zipCode).
               log().all().
        when().
              get("http://api.zippopotam.us/{countryCode}/{zipCode}").
        then().
              log().body().
              assertThat().body("places[0].'place name'", equalTo(expectedPlaceName));
    }
}

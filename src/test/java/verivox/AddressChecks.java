package verivox;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import pageObjects.GetData;
import static io.restassured.RestAssured.*;
import resources.base;

public class AddressChecks extends base {
    public static Logger log = LogManager.getLogger(base.class.getName());

    @Test(dataProvider = "getInputData")
    public void findTheCitiesForAGivenPostcode(String pin, String city) throws IOException, InterruptedException {
        RestAssured.baseURI = "https://service.verivox.de/geo/latestv2/cities/";
        JsonPath js = given().log().all().when().get(pin).then().assertThat().statusCode(200).extract().response().jsonPath();
        String cities = js.getString("Cities");
        Assert.assertEquals(cities, city);
        log.info("returns a list of cities (even for a single city)");
        log.info("Verified the 2 postcode cases with a parameterized test instead of 2 separate tests");
    }

    @DataProvider
    public Object[][] getInputData() {
        return GetData.getCitydata();
    }
}
package verivox;
import java.io.IOException;
import java.util.List;
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


public class StreetsCheck extends base {
    public static Logger log = LogManager.getLogger(base.class.getName());
    @Test(dataProvider = "getInputData")
    public void findTheStreetsForAGivenPostcode(String pin, String streets) throws IOException, InterruptedException {
        RestAssured.baseURI = "https://service.verivox.de/geo/latestv2/cities/";
        JsonPath js = given().log().all().when().get(pin).then().assertThat().statusCode(200).extract().response().jsonPath();
        String apiStreets = js.getString("Streets");
        List < String > streetsList = js.getList("Streets");
        if (pin == "10409/Berlin/Streets") {
            Assert.assertEquals(streetsList.size(), 29);
        } else if (pin == "77716/Fischerbach/Streets") {
            Assert.assertEquals(streetsList.size(), 33);
        } else if (pin == "77716/Hofstetten/Streets") {
            Assert.assertEquals(streetsList.size(), 40);
        } else if (pin == "77716/Haslach/Streets") {
            Assert.assertEquals(streetsList.size(), 120);
        }

        Assert.assertEquals(apiStreets, streets);
        log.info("returning street names with special characters and dashes displayed correctly");
        log.info("Verified the streets for each of the postcode 77716 cities");


    }
    @DataProvider
    public Object[][] getInputData() {
        return GetData.getStreetsdata();
    }
}
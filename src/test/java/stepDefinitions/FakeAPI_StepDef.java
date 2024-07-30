package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import com.app.helper.JSONParsing;
import com.app.resources.APIResources;
import com.app.resources.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FakeAPI_StepDef extends Utils {
	/* To handles response */
	ResponseSpecification resSpec = 
			new ResponseSpecBuilder()
			.expectStatusCode(200)
			.build();

	RequestSpecification resGiven;

	// To store API Response after API calls
	Response apiResponse;

	// To handle JSON Data
	JSONParsing jsonParsing;
	
	
	@Given("User has airlines endpoint")
	public void user_has_airlines_endpoint() throws IOException {
		//Handing API response 
				resSpec = new ResponseSpecBuilder()
					.expectStatusCode(200)
					.build();
				
				
				 resGiven = 
						 given()
						 .spec(fakeAPIrequestSpecification());
						 
				 
	}
	
	@When("User from FakeAPI calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String resource, String methodType) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		
		if (methodType.equalsIgnoreCase("POST")) {
			apiResponse = 
					resGiven
					.when()
						.post(resourceAPI.getResource())
					.then()
						.spec(resSpec)
						.extract()
						.response();
		}
		else if(methodType.equalsIgnoreCase("GET")) {
			apiResponse = 
					resGiven
					.when()
						.get(resourceAPI.getResource())
					.then()
						.spec(resSpec)
						.extract()
						.response();
		}
		
				
	}
	@Then("The FakeAPI API returns status code {int}")
	public void the_api_returns_status_code(Integer int1) {
		assertEquals(apiResponse.getStatusCode(), 200);	
	}
	@Then("{string} in FakeAPI response body is {string}")
	public void in_response_body_is(String key, String value) {
		assertEquals(getValueFromResponseBody(apiResponse, key), value);
	}
	
	@Then("Total {string} airlines are flying in the sky")
	public void total_airlines_are_flying_in_the_sky(String expectedAirlinesCount) {
		JsonPath jsonPath = apiResponse.jsonPath();
		System.out.println("Total Airlines : " + jsonPath.getList("$").size());
		assertTrue("Expected value is greater : ",jsonPath.getList("$").size() > Integer.valueOf(expectedAirlinesCount));

		// Extract the list of names
        List<String> names = jsonPath.getList("name");
        
	}
}

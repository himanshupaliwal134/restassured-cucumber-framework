package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import com.app.helper.JSONParsing;
import com.app.resources.APIResources;
import com.app.resources.TestDataBuilder;
import com.app.resources.Utils;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDef extends Utils{
	
	ResponseSpecification resSpec  = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.build();
	RequestSpecification resGiven;
	Response apiResponse;
	JSONParsing jsonParsing;
	static String placeID;
	TestDataBuilder placeAPIPayloadTestData = new TestDataBuilder();
		
	@Given("Add Place Payload with arguments {string} {string} {string}")
	public void add_place_payload_with_arguments(String name, String language, String address) throws IOException {
		//Payload creation
		
		//Handing API response 
		resSpec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.build();
		
		resGiven = 
		given()
			.spec(requestSpecification())
			.body(placeAPIPayloadTestData.addPlacePayload(name, language, address));
	}
	
	
	@When("User calls {string} with {string} HTTP request")
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
	@Then("The API returns status code {int}")
	public void the_api_returns_status_code(Integer int1) {
		
		assertEquals(apiResponse.getStatusCode(), 200);
		
	
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		
		jsonParsing = new JSONParsing(apiResponse.asString());
		assertEquals(getValueFromResponseBody(apiResponse, key), value);
		
		
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		placeID = getValueFromResponseBody(apiResponse, "place_id");
		resGiven = 
				given()
					.spec(requestSpecification())
					.queryParam("place_id", placeID);
					//.body("");
		user_calls_with_http_request(resource, "GET");
		assertEquals(getValueFromResponseBody(apiResponse, "name"), expectedName);
		
	}
	@Given("User has DeletePlace Payload")
	public void user_has_delete_place_payload() throws IOException {
		
		resGiven = 
		given()
	   		.spec(requestSpecification())
	   		.body(placeAPIPayloadTestData.deletePlacePayload(placeID));
	}

}

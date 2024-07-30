package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before("@deletePlace")
	public void beforeScenario() throws IOException {
		/* Get value of place_id only if it is null */
		GoogleAPI_StepDef stepDef = new GoogleAPI_StepDef();
		if (stepDef.placeID == null) {
			stepDef.add_place_payload_with_arguments("Ram", "Tamil", "Annasalai");
			stepDef.user_calls_with_http_request("ADD_PLACE_API", "POST");
			stepDef.verify_place_id_created_maps_to_using("Ram", "GET_PLACE_API");
		}
	}
	
	@After
	public void afterScenario() {
		System.out.println("THE END");
	}

}

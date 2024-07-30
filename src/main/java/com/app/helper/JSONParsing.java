package com.app.helper;

import io.restassured.path.json.JsonPath;

public class JSONParsing {

	JsonPath jsonResponse;
	public JSONParsing() {
		
	}

	public JSONParsing(String stringResponse) {
		super();
		this.jsonResponse = new JsonPath(stringResponse);
	}

	public JsonPath getJsonPathObject(String stringResponse) {
		jsonResponse = new JsonPath(stringResponse);
		return jsonResponse;
	}

	public void rawToJSON(String resString) {
		jsonResponse = new JsonPath(resString);
	}

	public String getJSONAttributeValue(String key) {
		return jsonResponse.getString(key);
	}

}

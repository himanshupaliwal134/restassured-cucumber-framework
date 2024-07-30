/**
 * 
 */
package com.app.resources;

/**
 * Enum constants are implicitly public, static, and final.
 * ENUM follows uppercase naming convention.
 */
public enum APIResources {

	/* Place API Resources */
	ADD_PLACE_API("/maps/api/place/add/json"),
	GET_PLACE_API("/maps/api/place/get/json"),
	UPDATE_PLACE_API("/maps/api/place/update/json"), 
	DELETE_PLACE_API("/maps/api/place/delete/json"),
	/* FakeAPI Resources */
	GET_ALL_AIRLINES("/airlines"),
	CREATE_AIRLINE_RECORD("/airlines"),
	READ_AIRLINE_BYID("/:id"),
	CREATE_PASSENGER_DATA("/passenger"),
	READ_PASSENGER_DATA("/passenger/:id"),
	DELETE_PASSENGER_DATA("/passenger/:id"),
	UPDATE_PASSENGER_NAME("/passenger/:id"),
	UPDATE_ALL_PASSENGER_DATA("/passenger/:id"),
	READ_ALL_PASSENGER_DATA("/passenger"); //?page=0&size=10
	
	String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}

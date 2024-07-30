/**
 * 
 */
package com.app.resources;

/**
 * Enum constants are implicitly public, static, and final.
 * ENUM follows uppercase naming convention.
 */
public enum APIResources {

	ADD_PLACE_API("/maps/api/place/add/json"),
	GET_PLACE_API("/maps/api/place/get/json"),
	UPDATE_PLACE_API("/maps/api/place/update/json"), 
	DELETE_PLACE_API("/maps/api/place/delete/json");

	String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}

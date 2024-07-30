package com.app.resources;

import java.util.ArrayList;
import java.util.List;

import com.api.pojo.AddPlace_Pojo;
import com.api.pojo.Location_AddPlace_Pojo;

public class TestDataBuilder {
	String place_ID;
	public AddPlace_Pojo addPlacePayload(String name, String language,String address) {

		AddPlace_Pojo ap = new AddPlace_Pojo();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		ap.setName(name);

		List<String> typeList = new ArrayList<String>();
		typeList.add("Shoe Park");
		typeList.add("Shop");

		ap.setTypes(typeList);

		Location_AddPlace_Pojo loc = new Location_AddPlace_Pojo();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);

		ap.setLocation(loc);
		
		return ap;
	}
	
	public String deletePlacePayload(String placeID) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeID+"\"\r\n"
				+ "}\r\n"
				+ "";
	}

}

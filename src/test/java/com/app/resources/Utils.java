package com.app.resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification reqSpec;
	public static RequestSpecification fakeAPIreqSpec;
	public RequestSpecification requestSpecification() throws IOException {
		
		/*
		 * If multiple API call logs will be logged reqSpec will be checked and new
		 * PrintStream will not be created if its not null.
		 */
		if(reqSpec == null) { 
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		reqSpec =  new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.build();
		
		return reqSpec;
		}
		return reqSpec;
	}
	
	
public RequestSpecification fakeAPIrequestSpecification() throws IOException {
		
		/*
		 * If multiple API call logs will be logged reqSpec will be checked and 
		 * new PrintStream will not be created if its not null.
		 */
		if(fakeAPIreqSpec == null) { 
		PrintStream log = new PrintStream(new FileOutputStream("FakeAPI_logging.txt"));
		fakeAPIreqSpec =  new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("fakeAPI"))
				//.addQueryParam("key", "qaclick123") Not needed
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.build();
		
		return fakeAPIreqSpec;
		}
		return fakeAPIreqSpec;
	}
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		try (InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream("global.properties")) {
            if (inputStream == null) {
                throw new IOException("Unable to find global.properties");
            }
            prop.load(inputStream);
        }
		
		
		//FileInputStream fis = new FileInputStream("/restassured-cucumber-framework/src/test/java/com/app/resources/global.properties");
		//prop.load(fis);
		return prop.getProperty(key);
	}
	
	public static String getValueFromResponseBody(Response response, String key) {
		JsonPath js = new JsonPath(response.asString());
		return js.get(key).toString();
				
	}
}

<<<<<<< HEAD
# restassured-cucumber-framework
RestAssured Demo 
=======
# restassured-cucumber-reporting
MainProject
	src/main/java
		com.api.pojo
			--->This will have payload
		com.app.helper
			--->This is for Parsing JSON response data
		com.app.payloads
			--->This is for static payload
	src/test/java
		com.app.resources
			--->This is to support any API calls   
					ENUM - To store API resources
					TestDataBuilder - To Create Dynamic Payload
					Utils
						To build RequestSpecification
						Set/update property values
						Get Specific value from ResponseBody
					global.properties
						Constant Data - Base URL etc.
		cucumber.Options
			--->This is for JUnit TestRunner
		features
			--->This is for Cucumber feature files
		stepDefinitions
			--->Glue code for cucumber feature files
				StepDefs - Glue code
				Hooks - Pre - Post activities
	
		

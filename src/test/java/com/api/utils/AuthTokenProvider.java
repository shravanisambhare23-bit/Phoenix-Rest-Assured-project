package com.api.utils;

import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.tests.userCredsPOJO;
import io.restassured.http.ContentType;
public class AuthTokenProvider { //this is utility class so we should not create object of this class 
	//so create private constructor 
private AuthTokenProvider() {
	// to restrict creation of object of class outside the class
}
	public static String getToken(Role role) {
	/*
	 I want to make a request for login Api and 	 
	 we want to extract the token and print it on console 
	*/	
		
		userCredsPOJO usercredentials = null;
	if(role == Role.FD)	{
		usercredentials  = new userCredsPOJO("iamfd", "password");
				}
	else if(role == Role.SUP)	{
		usercredentials  = new userCredsPOJO("iamsup", "password");
	}
	else if(role == Role.ENG)	{
		usercredentials  = new userCredsPOJO("iameng", "password");
	}
	else if(role == Role.QC)	{
		usercredentials  = new userCredsPOJO("iamsqa", "password");
	}
String token =	given()
	.baseUri(ConfigManager.getProperty("BASE_URI"))
	.contentType(ContentType.JSON)
	.body(usercredentials)
	.when()
	.post("login")
	.then()
	.statusCode(200)
	.body("message",Matchers.equalTo("Success") )
	.log().all()
	.extract()
	.body()
	.jsonPath()
	.getString("data.token");
System.out.println(token);
return token;
	}
}

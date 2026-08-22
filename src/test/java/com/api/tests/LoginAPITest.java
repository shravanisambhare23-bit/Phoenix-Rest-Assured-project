package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.ConfigManager;
import com.api.utils.ConfigManagerOld;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
@Test
	public void loginApiTest() throws IOException {
	   
		userCredsPOJO cred = new userCredsPOJO("iamfd","password");
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.and()
		.body(cred)
		.log().uri()
		.log().method()
		.log().body()
		.log().headers()
		
	    .when()
	    .post("login")
	    
	    .then()
	    .log().all()
	    .statusCode(200)
	    .and()
	    .time(Matchers.lessThan(2000L))
	    .and()
	    .body("message",Matchers.equalTo("Success"))
	    .and()
	    .body("data.token",Matchers.notNullValue())
	    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/LoginSchema.json"));
	}
}

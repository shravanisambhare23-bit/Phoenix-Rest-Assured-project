package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;
import java.io.IOException;

public class userDetailsAPITest {
	@Test
	public void userdetails() throws IOException {
		//ConfigManager2 configmanager = new ConfigManager2();
		Header authHeader = new Header("Authorization",AuthTokenProvider.getToken(Role.ENG));
	 given()
	 .baseUri(ConfigManager.getProperty("BASE_URI"))
	 .and()
	 .header(authHeader)
	 .accept(ContentType.JSON)
	 .when()
	 .get("userdetails")
	 
	 .then()
	 .log().all()
	 .statusCode(200)
	 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/userDetailsSchema.json"))
	 .time(Matchers.lessThan(1500L));	 
	}
}

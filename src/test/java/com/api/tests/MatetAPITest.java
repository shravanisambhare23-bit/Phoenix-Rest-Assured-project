package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MatetAPITest {
@Test

	public  void MasterApiTest() {
		// TODO Auto-generated method stub
given()
.baseUri(ConfigManager.getProperty("BASE_URI"))
.and()
.header("Authorization",AuthTokenProvider.getToken(Role.FD))
.and()
.contentType("")
// to say even though its POST request we don't want any Content type
.log().all()
.when()
.post("/master")// default content type application/url-formencoded
//it should be get request but its a bug
.then()
.statusCode(200)
.time(Matchers.lessThan(1000L))
.body("message", Matchers.equalTo("Success"))
.body("data",Matchers.notNullValue())
.body("data",Matchers.hasKey("mst_oem"))
.body("$", Matchers.hasKey("message"))
.body("$",Matchers.hasKey("data"))
.body("data.mst_oem.size()", Matchers.greaterThan(0)) // check size of JSON array
.body("data.mst_model.size()", Matchers.greaterThan(0))
.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/MasterAPISchema-FD.json"));


	}

public void InvalidTokenMasterAPITest() {
	given()
	.baseUri(ConfigManager.getProperty("BASE_URI"))
	.and()
	.header("Authorization","")
	.and()
	.contentType("")
	.log().all()
	.when()
	.post("/master")
	.then()
	.statusCode(401);
	// for empty autorization token it should return 401 status code 
}
}

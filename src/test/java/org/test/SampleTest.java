package org.test;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SampleTest {
	String id;
	@Test
	public void tc1() {
//		{
//		    "name": "morpheus",
//		    "job": "leader"
//		}
		
		HashMap m = new HashMap();
		m.put("name", "morpheus");
		m.put("job", "leader");
	
		 Response response = RestAssured.given().contentType("application/json").body(m).
		when().post("https://reqres.in/api/users");
		
		Object idValue = response.body().jsonPath().get("id");
		id = idValue.toString();
		
		response.then().statusCode(201).log().all();

	}

}

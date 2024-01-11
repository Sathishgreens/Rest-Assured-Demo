package org.test;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class Sample {
	int idValue ;
	
	@Test(priority = 1)
	public void postReq() {
		//sadsd
		//sdfggg
		//wwwwwwwww
		HashMap requestBody = new HashMap();
		requestBody.put("name", "qqqqqqq");
		requestBody.put("completed", false);
		
		
		Response post = RestAssured.given().contentType("application/json")
				.body(requestBody).post("http://localhost:3000/data");
		
		Object object = post.body().jsonPath().get("name");
		System.out.println(object);
		idValue = (int) object;
		
	}
	
	@Test(priority = 2)
	public void tc1() {
		//http://localhost:3000/data
//		RequestSpecification given = RestAssured.given();
//		Response response = given.get("http://localhost:3000/data");
		
		Response response = RestAssured.given().queryParam("id", idValue).
				get("http://localhost:3000/data");
		
		int statusCode = response.statusCode();
		
		String asPrettyString = response.body().asPrettyString();
		System.out.println(asPrettyString);
//		Object object = response.body().jsonPath().get("name");
//		
//		System.out.println(object);
			
	}
	
	@Test(priority = 3)
	public void putReq() {
		
		HashMap requestBody = new HashMap();
		requestBody.put("name", "1111111");
		requestBody.put("completed", false);
		ArrayList li = new ArrayList();
		
		li.add("Java");
		li.add("Selenium");
		requestBody.put("courses", li);
		
		Response put = RestAssured.given().contentType("application/json").body(requestBody)
		.pathParam("id", idValue).put("http://localhost:3000/data/{id}");
		
		String asPrettyString = put.body().asPrettyString();
		System.out.println(put.statusCode());
		System.out.println(asPrettyString);

	}
	

}







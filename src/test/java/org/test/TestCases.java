package org.test;

import org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import  io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TestCases {
	
	@Test
	public void postReq() {	
		//Endpoints = BaseURI + Resources + Query/Path Parameter
		//https://reqres.in/api/users?page=2
		RestAssured.baseURI = "https://reqres.in";	
		String resource = "api/users";	
		RequestSpecification requestSpecification = RestAssured.given();		
		//requestSpecification.queryParam("page", 2);		
		//How to pass payload
//		{
//		    "name": "morpheus",
//		    "job": "leader"
//		}
		requestSpecification.contentType("application/json");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "morpheus");
		jsonObject.put("job", "leader");
		
		requestSpecification.body(jsonObject.toJSONString());
		
		//to send a request
		Response response = requestSpecification.request(Method.POST,resource);
		//response = body , status code , headers , cookies , size , time
		//Validate the Results
		//print response body
		ResponseBody body = response.getBody();
		String asPrettyString = body.asPrettyString();
		System.out.println(asPrettyString);
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		//Assert the response
		//Assert.assertEquals(statusCode, 200);
		response.then().statusCode(201).body("name", Matchers.equalTo("morpheus"));
		
	}
	
	@Test
	public void putReq() {
		
		RestAssured.baseURI="https://reqres.in/";
		String resource = "api/users/2";
		
		RequestSpecification requestSpecification = RestAssured.given();
		
		//PayLoad --> HashMap
//		{
//		    "name": "morpheus",
//		    "job": "zion resident"
//		}
		HashMap payLoad = new HashMap();
		payLoad.put("name", "morpheus");
		payLoad.put("job", "zion resident");
		
		requestSpecification.contentType("application/json");
		requestSpecification.body(payLoad);
		Response response = requestSpecification.request(Method.PUT,resource);
		
		response.then().statusCode(200).body("job", Matchers.equalTo("zion resident"));
		
	}
	
	@Test
	public void delRequest() {
		RestAssured.baseURI="https://reqres.in/";
		String resource = "api/users/2";
		
		RequestSpecification requestSpecification = RestAssured.given();
		Response response = requestSpecification.request(Method.DELETE,resource);
		
		response.then().statusCode(204);
		
		
	}
	

}







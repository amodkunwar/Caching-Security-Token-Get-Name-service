package com.amod.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amod.security.controller.AuthRequest;

//@Component(value="nameservice")
@Service(value = "nameservice")
public class NameService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TokenValidate tokenValidate;

	/***
	 * First step is to get the token and then hit the getName api in order to get
	 * the name Second check the token is active or not Third use redis and store
	 * the token
	 */

	@Cacheable(value = "names")
	public String getToken() {

		System.out.println("Get Token method is called.");
		AuthRequest authRequest = new AuthRequest();
		authRequest.setPassword("pwd1");
		authRequest.setUsername("Amod");
		var headerToken = new HttpHeaders();
		headerToken.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> entityToken = new HttpEntity<>(authRequest, headerToken);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/getToken",
				entityToken, String.class);
		if (responseEntity.hasBody()) {
			return responseEntity.getBody();
		}
		return null;
	
	}

	@Cacheable(value = "names", condition = "@tokenValidate.isTokenExpired(#token)")
	public String validateAndGetToken(String token) {

		System.out.println("Get Token method is called.");
		AuthRequest authRequest = new AuthRequest();
		authRequest.setPassword("pwd1");
		authRequest.setUsername("Amod");
		var headerToken = new HttpHeaders();
		headerToken.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> entityToken = new HttpEntity<>(authRequest, headerToken);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/getToken",
				entityToken, String.class);
		if (responseEntity.hasBody()) {
			return responseEntity.getBody();
		}
		return null;
	
	}

}

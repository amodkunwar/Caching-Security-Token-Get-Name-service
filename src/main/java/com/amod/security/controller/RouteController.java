package com.amod.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

	@RequestMapping(value = "/getName" + "/**/{path:[^\\\\.]*}")
	public ResponseEntity<Object> getDefaultOptionResponse() {
		Map<String, String> response = new HashMap<>();
		response.put("error", "The resource you are trying to access was not found.");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}

package com.amod.security.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amod.security.service.NameService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GetNameController {

	private static final String TOKEN = "TOKEN";

	@Autowired
	private NameService nameService;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/getName")
	public String getName(@RequestParam String firstName, @RequestParam String lastName) {
		String body = null;
		String token = nameService.getToken();
		token = nameService.validateAndGetToken(token);
		System.out.println(token);
		if (Objects.nonNull(token)) {
			log.info("Token " + token);
			var headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("firstName", firstName);
			map.add("lastName", lastName);
			HttpEntity<Object> entity = new HttpEntity<>(map, headers);
			ResponseEntity<String> postForEntity = restTemplate.postForEntity("http://localhost:8080/products/getName",
					entity, String.class);
			if (postForEntity.hasBody()) {
				body = postForEntity.getBody();
				log.info("Response from getName api " + body);

			}
		}
		return body;
	}

	@Bean
	public ConcurrentMapCache cache() {
		return new ConcurrentMapCache(TOKEN);
	}

	@GetMapping("/test")
	@Cacheable(value = "names")
	public String getName() {
		return getFirstName();
	}

	public String getFirstName() {
		System.out.println("method called");
		return "Amod";
	}

}

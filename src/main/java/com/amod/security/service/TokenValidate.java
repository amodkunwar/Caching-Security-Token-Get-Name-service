package com.amod.security.service;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class TokenValidate {

	public boolean isTokenExpired(String token) {
		DecodedJWT jwt = JWT.decode(token);
		if (jwt.getExpiresAt().before(new Date())) {
			System.out.println("token is expired so getToken() will be invoked");
			return false;
		} else {
			System.out.println("token is not expired");
			return true;
		}
	}

	public boolean isExpire() {
		return false;
	}

}

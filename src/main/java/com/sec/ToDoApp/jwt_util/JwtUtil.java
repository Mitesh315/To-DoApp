package com.sec.ToDoApp.jwt_util;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private final String SECRET_KEY = "HelloWorld";
	private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}

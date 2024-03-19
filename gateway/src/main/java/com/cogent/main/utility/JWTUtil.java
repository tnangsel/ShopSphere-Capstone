package com.cogent.main.utility;

import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

	private final String secret = "h49AY3pldfOLSvMM3R3/ZJfNJYGJx0pY/yMd2RE4SUqf5q5JQ1mfTHTy0ENApRrO";
	
	public void validateToken(String jwtToken) {
	    try {
	        Jwts.parser()
	                .verifyWith(getSignInKey())
	                .build()
	                .parseSignedClaims(jwtToken);
	    } catch (ExpiredJwtException ex) {
	        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token has expired");
	    }  catch (MalformedJwtException ex) {
	        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid token: " + ex.getMessage());
	    } catch (JwtException ex) {
	        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid token: " + ex.getMessage());
	    }
	}

	private SecretKey getSignInKey() {
		byte[] key = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(key);
	}
	
	
}

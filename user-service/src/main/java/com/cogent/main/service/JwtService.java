package com.cogent.main.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.cogent.main.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	
	
	private final String secret = "h49AY3pldfOLSvMM3R3/ZJfNJYGJx0pY/yMd2RE4SUqf5q5JQ1mfTHTy0ENApRrO";
	
//	public void validateAdminToken(String jwtToken) {
//		String token = jwtToken.split(" ")[1];
//		jwtToken = token;
//		
//		Jwts.parser()
//			.verifyWith(getSignInKey())
//			.build()
//			.parseSignedClaims(jwtToken);
//		if(Jwts.parser()
//				.verifyWith(getSignInKey())
//				.build()
//				.parseSignedClaims(jwtToken)
//				.getPayload().getExpiration()
//				.before(new Date(System.currentTimeMillis()))) 
//			
//		userDetailsService.loadUserByUsername(token);
//		
//	}
	public boolean isValidateToken(String jwtToken) {
		try {
			Jwts.parser()
			.verifyWith(getSignInKey())
			.build()
			.parseSignedClaims(jwtToken)
			.getPayload();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
		
	}
	
	public void validateToken(String jwtToken) {
		Jwts.parser()
		.verifyWith(getSignInKey())
		.build()
		.parseSignedClaims(jwtToken)
		.getPayload();
		
	}
	
	public String generateToken(String userName) {
		return createToken(new HashMap<String, Object>(), userName);
	}
	
	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder().claims(claims)
				.subject(userName)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000*120*30))
				.signWith(getSignInKey())
				.compact();
	}
	
	private SecretKey getSignInKey() {
		byte[] key = Decoders.BASE64.decode(secret);
		
		return Keys.hmacShaKeyFor(key);
	}

	public String extractUserEmail(String jwtToken) {
		
		return extractClaims(jwtToken, Claims::getSubject);
	}

	public <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(jwtToken);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String jwtToken) {
		return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
	}


	public String generateToken(UserEntity userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	} 
	
	public String generateToken(Map<String, Object> extraClaims, UserEntity userDetails) {
		
		return Jwts.builder()
				.claims(extraClaims)
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSignInKey()).compact();
	}
	
	public boolean isTokenValid(String jwtToken, UserEntity userEntity, String role) {
		String userEmail = userEntity.getUsername();
		return (userEmail.equals(extractUserEmail(jwtToken)) 
				&& !isTokenExpired(jwtToken) 
				&& userEntity.getRoles().toString().equalsIgnoreCase(role));
	}
	
	
	private boolean isTokenExpired(String jwtToken) {
		return extractExpiration(jwtToken).before(new Date());
	}
	
	public Date extractExpiration(String jwtToken) {
		
		return extractClaims(jwtToken, Claims::getExpiration);
	}
	
	
}

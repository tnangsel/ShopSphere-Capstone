/**
 * @author: Tenzin 
 * @date : Feb 13, 2024
 */
package com.cogent.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.cogent.main.entity.TokenEntity;
import com.cogent.main.repository.TokenEntityRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LogoutService implements LogoutHandler{

	@Autowired
	private TokenEntityRepository tokenEntityRepository;
	
	@SuppressWarnings("null")
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		final String authHeader = request.getHeader("Authorization");
		final String jwtToken; 

		if(authHeader != null || authHeader.startsWith("Bearer ")) {
			
			jwtToken = authHeader.substring(7);
	
			TokenEntity storedToken = tokenEntityRepository.findByToken(jwtToken).orElse(null);
			
			if(storedToken != null) {
				
				storedToken.setExpired(true);
				storedToken.setRevoked(true);
				tokenEntityRepository.save(storedToken);
			}
		}

	}

//	public String logUserOut(String token) {
//        
//        if (token == null || token.isEmpty()) {
//            return "Token is required for logout.";
//        }
//
//        TokenEntity storedToken = tokenEntityRepository.findByToken(token).orElse(null);
//        
//        if (storedToken == null) {
//            return "Token not found.";
//        }
//        
//        storedToken.setExpired(true);
//        storedToken.setRevoked(true);
//        tokenEntityRepository.save(storedToken);
//        
//        return "Logout successful.";
//    }

}

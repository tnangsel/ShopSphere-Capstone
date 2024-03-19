package com.cogent.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cogent.main.dao.LoginResponseDao;
import com.cogent.main.dao.UserDao;
import com.cogent.main.entity.AuthRequest;
import com.cogent.main.entity.TokenEntity;
import com.cogent.main.entity.TokenType;
import com.cogent.main.entity.UserEntity;
import com.cogent.main.repository.TokenEntityRepository;
import com.cogent.main.repository.UserEntityRepository;


@Service
public class AuthService {
	@Autowired
	private UserEntityRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private TokenEntityRepository tokenEntityRepository;
	
	@Autowired
	private AuthenticationManager manager;
	
	public String addUser(UserDao userEntityDao) {
		
		UserEntity userEntity = UserEntity.builder()
				.name(userEntityDao.getName())
				.email(userEntityDao.getEmail())
				.pass(passwordEncoder.encode(userEntityDao.getPass()))
				.address(userEntityDao.getAddress())
				.roles(userEntityDao.getRoles())
				.build();
		userRepository.save(userEntity);
		
		String gJwtToken = jwtService.generateToken(userEntity.getEmail());
		
		TokenEntity tokenEntity = TokenEntity.builder()
				.expired(false)
				.revoked(false)
				.token(gJwtToken)
				.tokenType(TokenType.BEARER)
				.userEntity(userEntity)
				.build();
		tokenEntityRepository.save(tokenEntity);
		
		return "User Registration Successful!";
	}
	
	public LoginResponseDao validateLogin(AuthRequest authRequest) {
		Authentication auth = manager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authRequest.getUsername(), 
						authRequest.getPassword()));
		
		if(auth.isAuthenticated()) {
			
			Optional<UserEntity> userEntity = userRepository.findByEmail(authRequest.getUsername());
			
			//get all the tokens that corresponding the user
			List<TokenEntity> tokenEntities = tokenEntityRepository.findAllValidTokenByUserEntity(userEntity.get().getId());
			if(!tokenEntities.isEmpty()) {
				tokenEntities.stream().forEach((te) -> {
					te.setRevoked(true);
					te.setExpired(true);
				});
			}
			
			//if token already exist, set to revoked and expired the old token
			tokenEntityRepository.saveAll(tokenEntities);
			
			//generate a new token for the user and set the token expiration and revoked to set false 
			String newToken = generateToken(authRequest.getUsername());
			TokenEntity tokenEntity = TokenEntity.builder()
					.expired(false)
					.revoked(false)
					.token(newToken)
					.tokenType(TokenType.BEARER)
					.userEntity(userEntity.get())
					.build();
			tokenEntityRepository.save(tokenEntity);
			//Send the new token to the front-end
			return LoginResponseDao.builder()
					.token(newToken)
					.role(userEntity.get().getRoles().toString())
					.build();
		
		}else {
			throw new RuntimeException("Invalid");
		}
	}
	
	public String generateToken(String userName) {
		return jwtService.generateToken(userName);
	}

	
	public UserDao fetchOneUser(Integer userId, String header) {
		String token = header.split(" ")[1];
		jwtService.validateToken(token);
		Optional<UserEntity> userE = userRepository.findById(userId);
		if(!userE.isEmpty()) {
			return UserDao.builder()
				.name(userE.get().getName())
				.email(userE.get().getEmail())
				.address(userE.get().getAddress())
				.roles(userE.get().getRoles())
				.build();
		}else {
			return null;
		}
	}

	public Integer getUserIdByEmail(String userEmail) {
		return userRepository.findUserIdByEmail(userEmail);
	}

	

	
	
}

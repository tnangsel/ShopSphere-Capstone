package com.cogent.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cogent.main.dao.UserDao;
import com.cogent.main.entity.UserEntity;
import com.cogent.main.repository.UserEntityRepository;

@Service
public class AdminService {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserEntityRepository userRepository;

	public UserDao fetchOneUser(Integer userId, String header) {
		
		Optional<UserEntity> uE =  userRepository.findById(userId);
		if(uE.isPresent()) {
			return UserDao.builder()
					.name(uE.get().getName())
					.email(uE.get().getEmail())
					.address(uE.get().getAddress())
					.roles(uE.get().getRoles())
					.build();
		}else {
			return null;
		}
	}
	
	public List<UserEntity> getUserList() {
		return userRepository.findAll();
//		List<UserEntity> users = userRepository.findAll();
//        return users.stream()
//                .map(this::convertToUserEntityDaoWithoutPassword)
//                .collect(Collectors.toList());
    }

//	private UserDao convertToUserEntityDaoWithoutPassword(UserEntity userEntity) {
//        return UserDao.builder()
//        		.name(userEntity.getName())
//        		.email(userEntity.getEmail())
//        		.address(userEntity.getAddress())
//        		.roles(userEntity.getRoles())
//        		.build();
//    }
	
	public UserDao updateUserInfo(Integer userId, UserDao userDao) {
		
		Optional<UserEntity> user = userRepository.findById(userId);
		if (user.isPresent()) {
			user.get().setName(userDao.getName());
			user.get().setEmail(userDao.getEmail());
			user.get().setPass(encoder.encode(userDao.getPass()));
			user.get().setAddress(userDao.getAddress());
			user.get().setRoles(userDao.getRoles());

			UserEntity userED = userRepository.save(user.get());
			return UserDao.builder().name(userED.getName()).email(userED.getEmail())
					.pass(encoder.encode(userED.getPass())).address(userED.getAddress()).roles(userED.getRoles())
					.build();
		} else {
			return new UserDao();
		}
	}


	public String removeUser(Integer userId) {
		
		Optional<UserEntity> deleteUser = userRepository.findById(userId);
		if(deleteUser.isPresent()) {
			userRepository.delete(deleteUser.get());
			return "User with ID: " + userId + " is deleted";
		}else {
			return "User with ID: " + userId + " does not exist";
		}
	}

	
	public boolean validateHeader(String header) {
		final String jwtToken = header.substring(7);
		return jwtService.isValidateToken(jwtToken);
		
	}
	

	
}

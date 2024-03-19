/**
 * @author: Tenzin 
 * @date : Feb 12, 2024
 */
package com.cogent.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cogent.main.entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer>{
	@Query(value = "from UserEntity where email= :userEmail")
	Optional<UserEntity> findByEmail(String userEmail);

	@Query(value = "select id from UserEntity where email= :userEmail")
	Integer findUserIdByEmail(String userEmail);

}

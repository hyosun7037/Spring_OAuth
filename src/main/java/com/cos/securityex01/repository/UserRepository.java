package com.cos.securityex01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.securityex01.model.User;

// JpaRepository를 상속하면 자동으로 컴포넌트 스캔이 된다. 
public interface UserRepository extends JpaRepository<User, Integer>{ // 기본 CRUD 만들어져있음!
	
	// * Jpa Naming 전략 : 자동으로 쿼리 생성해줌
	// 1. SELECT * FROM user WHERE username = ?1 쿼리 동작 
	User findByUsername(String username);
	
//	@Query(value = "SELECT * FROM WHERE email = ?1", nativeQuery = true)
//	Optional<User> mFindEmail(String email); //m은 내가 만든 의미
	// 2. SELECT * FROM user WHERE username = ?1 AND password = ?2 쿼리 동작 
	// User findByUsernameAndPassword(String username, String password);
	
	// 이 부분 추가
	// SELECT * FROM user WHERE prover = ?1 and providerId = ?2
	Optional<User> findByProviderAndProviderId(String provider, String providerId);
	
	
    // @Query(value = "SELECT * FROM user", nativeQuery = true)
    // User find마음대로();
}

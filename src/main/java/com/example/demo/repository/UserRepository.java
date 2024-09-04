package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {
	
	@Query("SELECT DISTINCT user FROM User user "  + "INNER JOIN FETCH user.authorities AS authorities "
			+ "WHERE lower(user.email) = lower(:username)")
	User findByUsername(@Param("username") String username);

}

package com.example.demo.repository;

import com.example.demo.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @Query("select u from User u where u.fullName LIKE %:query% OR u.email LIKE %:query%")
    List<User> searchUser(@Param("query") String query);

}

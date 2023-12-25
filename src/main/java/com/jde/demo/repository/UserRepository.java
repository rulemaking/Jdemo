package com.jde.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jde.demo.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}

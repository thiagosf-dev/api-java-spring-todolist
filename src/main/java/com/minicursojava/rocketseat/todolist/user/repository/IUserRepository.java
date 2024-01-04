package com.minicursojava.rocketseat.todolist.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minicursojava.rocketseat.todolist.user.model.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

  UserModel findByUsername(String username);

}

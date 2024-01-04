package com.minicursojava.rocketseat.todolist.user.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "tb_users")
@Data
public class UserModel {

  @Id
  @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "username", length = 20, unique = true)
  private String username;

  @Column(name = "name", length = 50)
  private String name;

  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;

}

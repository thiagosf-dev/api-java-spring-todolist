package com.minicursojava.rocketseat.todolist.task.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "tb_tasks")
@Data
public class TaskModel {

  @Id
  @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
  private UUID id;

  @Column(length = 255)
  private String description;

  @Column(length = 50)
  private String title;

  @Column(length = 15)
  private String priority;

  private LocalDateTime startAt;
  private LocalDateTime endAt;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  private UUID idUser;

  public void setTitle(String title) throws Exception {
    if (title.length() > 50) {
      throw new Exception("O campo title deve ter no máximo 50 caracteres.", null);
    }
    this.title = title;
  }

}

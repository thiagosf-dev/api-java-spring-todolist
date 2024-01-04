package com.minicursojava.rocketseat.todolist.task.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minicursojava.rocketseat.todolist.task.model.TaskModel;


@Repository
public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {

  List<TaskModel> findByIdUser(UUID idUser);

}

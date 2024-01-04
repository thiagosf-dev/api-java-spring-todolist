package com.minicursojava.rocketseat.todolist.task.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minicursojava.rocketseat.todolist.task.model.TaskModel;
import com.minicursojava.rocketseat.todolist.task.repository.ITaskRepository;
import com.minicursojava.rocketseat.todolist.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/task")
public class TaskController {

  @Autowired
  ITaskRepository taskRepository;

  private static final String ID_USER = "idUser";

  @PostMapping(path = "")
  public ResponseEntity<Object> create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    var userId = request.getAttribute(TaskController.ID_USER);
    var currentDate = LocalDateTime.now();

    if (currentDate.isAfter(taskModel.getStartAt())) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("A data de início deve ser maior do que a data atual.");
    }

    if (currentDate.isAfter(taskModel.getEndAt())) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("A data de término deve ser maior do que a data atual.");
    }

    if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("A data de término deve ser maior do que a data de início.");
    }

    taskModel.setIdUser((UUID) userId);

    var task = this.taskRepository.save(taskModel);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(task);
  }

  @GetMapping("")
  public List<TaskModel> list(HttpServletRequest request) {
    var idUser = (UUID) request.getAttribute(TaskController.ID_USER);
    return this.taskRepository.findByIdUser(idUser);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Object> update(
      @RequestBody TaskModel taskModel,
      @PathVariable UUID id,
      HttpServletRequest request) {
    var idUser = (UUID) request.getAttribute(TaskController.ID_USER);

    var task = this.taskRepository.findById(id).orElse(null);

    if (task == null) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body("Tarefa inválida.");
    }

    if (!task.getIdUser().equals(idUser)) {
      return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .body("Usuário não tem permissão para alterar essa tarefa.");
    }

    Utils.copyNonNullProperties(taskModel, task);

    var taskUpdated = this.taskRepository.save(task);

    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(taskUpdated);
  }

}

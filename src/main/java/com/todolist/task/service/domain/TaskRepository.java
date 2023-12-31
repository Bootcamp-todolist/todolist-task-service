package com.todolist.task.service.domain;

import com.todolist.task.service.domain.models.Task;
import java.util.List;

public interface TaskRepository {

  void save(Task task);

  List<Task> findByCreatedByAndDeletedFalse(String user);

  Task findById(String taskId);
}

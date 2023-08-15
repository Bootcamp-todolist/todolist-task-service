package com.todolist.task.service.domain;

import com.todolist.task.service.domain.models.Task;

public interface TaskRepository {

  void createTask(Task task);
}

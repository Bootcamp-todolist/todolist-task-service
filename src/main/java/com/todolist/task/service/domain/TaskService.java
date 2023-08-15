package com.todolist.task.service.domain;

import com.todolist.task.service.domain.models.Task;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
  private final TaskRepository taskRepository;
  public void createTask(Task task) {
    taskRepository.createTask(task);
  }

  public List<Task> findByCreatedByAndDeletedFalse(String user) {
    return taskRepository.findByCreatedByAndDeletedFalse(user);
  }
}

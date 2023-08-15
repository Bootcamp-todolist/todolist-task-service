package com.todolist.task.service.domain;

import com.todolist.task.service.domain.models.Task;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
  private final TaskRepository taskRepository;
  public void save(Task task) {
    taskRepository.save(task);
  }

  public List<Task> findByCreatedByAndDeletedFalse(String user) {
    return taskRepository.findByCreatedByAndDeletedFalse(user);
  }

  public Task findById(String taskId) {
    return taskRepository.findById(taskId);
  }
}

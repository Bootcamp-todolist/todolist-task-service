package com.todolist.task.service.application;

import com.todolist.task.service.adapter.http.models.CreateTaskCommand;
import com.todolist.task.service.application.mapper.CreateTaskCommandMapper;
import com.todolist.task.service.application.mapper.TaskDTOMapper;
import com.todolist.task.service.application.models.TaskDTO;
import com.todolist.task.service.domain.TaskService;
import com.todolist.task.service.domain.enums.TaskStatus;
import com.todolist.task.service.domain.models.Task;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskApplicationService {
  private final TaskService taskService;
  public void createTask(String user, CreateTaskCommand createTaskCommand) {
    Task task = CreateTaskCommandMapper.MAPPER.toDomain(createTaskCommand, user);
    task.setStatus(TaskStatus.TODO);
    taskService.createTask(task);
  }

  public List<TaskDTO> getAllTasks(String user) {
    return TaskDTOMapper.MAPPER.toDTO(taskService.findByCreatedByAndDeletedFalse(user));
  }
}

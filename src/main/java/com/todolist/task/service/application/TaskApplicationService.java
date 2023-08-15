package com.todolist.task.service.application;

import com.todolist.task.service.adapter.http.models.CreateTaskCommand;
import com.todolist.task.service.application.mapper.CreateTaskCommandMapper;
import com.todolist.task.service.application.mapper.TaskDTOMapper;
import com.todolist.task.service.application.models.TaskDTO;
import com.todolist.task.service.domain.TaskService;
import com.todolist.task.service.domain.enums.TaskStatus;
import com.todolist.task.service.domain.models.Task;
import com.todolist.task.service.exception.BusinessException;
import com.todolist.task.service.exception.Error;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskApplicationService {

  private final TaskService taskService;

  public void createTask(String user, CreateTaskCommand createTaskCommand) {
    Task task = CreateTaskCommandMapper.MAPPER.toDomain(createTaskCommand, user);
    task.setStatus(TaskStatus.TODO);
    taskService.save(task);
  }

  public List<TaskDTO> getAllTasks(String user) {
    return TaskDTOMapper.MAPPER.toDTO(taskService.findByCreatedByAndDeletedFalse(user));
  }

  public void deleteTask(String taskId, String user) {
    Task task = taskService.findById(taskId);
    validateTask(user, task);

    task.setDeleted(true);
    task.setUpdatedBy(user);
    taskService.save(task);
  }

  private static void validateTask(String user, Task task) {
    if (Objects.isNull(task)) {
      throw new BusinessException(Error.TASK_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
    if (!Objects.equals(task.getCreatedBy(), user)) {
      throw new BusinessException(Error.NO_PERMISSION, HttpStatus.FORBIDDEN);
    }
  }
}

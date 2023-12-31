package com.todolist.task.service.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.todolist.task.service.UnitTest;
import com.todolist.task.service.adapter.http.models.CreateTaskCommand;
import com.todolist.task.service.application.models.TaskDTO;
import com.todolist.task.service.domain.TaskService;
import com.todolist.task.service.domain.enums.Priority;
import com.todolist.task.service.domain.enums.TaskStatus;
import com.todolist.task.service.domain.models.Task;
import com.todolist.task.service.exception.BusinessException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

class TaskApplicationServiceTest extends UnitTest {
  @Mock
  private TaskService taskService;

  private TaskApplicationService taskApplicationService;
  @BeforeEach
  void setUp() {
    taskApplicationService = new TaskApplicationService(taskService);
  }

  @Test
  void should_create_task_successfully() {
    String user = "user_id";
    CreateTaskCommand createTaskCommand = CreateTaskCommand.builder().description("test")
        .priority(Priority.HIGH)
        .build();
    Task task = Task.builder().description("test")
        .priority(Priority.HIGH)
        .status(TaskStatus.TODO)
        .createdBy(user)
        .build();
    ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);

    doNothing().when(taskService).save(any());

    taskApplicationService.createTask(user,createTaskCommand);

    verify(taskService).save(captor.capture());
    assertThat(captor.getValue()).usingRecursiveComparison().isEqualTo(task);

  }

  @Test
  void should_get_all_tasks() {
    String user = "user1";

    List<Task> tasks = List.of(new Task(), new Task());

    when(taskService.findByCreatedByAndDeletedFalse(user)).thenReturn(tasks);

    List<TaskDTO> taskDTOs = taskApplicationService.getAllTasks(user);

    assertEquals(tasks.size(), taskDTOs.size());
    verify(taskService).findByCreatedByAndDeletedFalse(user);
  }

  @Test
  void should_delete_task_successfully() {
    String taskId = "taskId1";
    String user = "user1";

    Task task = new Task();
    task.setCreatedBy(user);

    when(taskService.findById(taskId)).thenReturn(task);

    taskApplicationService.deleteTask(taskId, user);

    verify(taskService).findById(taskId);
    verify(taskService).save(task);

    assertTrue(task.isDeleted());
    assertEquals(user, task.getUpdatedBy());
  }

  @Test
  void should_throw_404_when_task_not_found() {
    String taskId = "taskId1";
    String user = "user1";

    when(taskService.findById(taskId)).thenReturn(null);

    assertThrows(BusinessException.class, () -> taskApplicationService.deleteTask(taskId, user));

    verify(taskService).findById(taskId);
    verify(taskService, never()).save(any());
  }
  @Test
  void should_throw_403_when_task_not_found() {
    String taskId = "taskId1";
    String user = "user1";
    Task task = new Task();
    task.setCreatedBy("differentUser");

    when(taskService.findById(taskId)).thenReturn(task);

    assertThrows(BusinessException.class, () -> taskApplicationService.deleteTask(taskId, user));

    verify(taskService).findById(taskId);
    verify(taskService, never()).save(any());
  }
}

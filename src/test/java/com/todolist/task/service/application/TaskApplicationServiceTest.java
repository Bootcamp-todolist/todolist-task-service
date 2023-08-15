package com.todolist.task.service.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import com.todolist.task.service.UnitTest;
import com.todolist.task.service.adapter.http.models.CreateTaskCommand;
import com.todolist.task.service.domain.TaskService;
import com.todolist.task.service.domain.enums.Priority;
import com.todolist.task.service.domain.enums.TaskStatus;
import com.todolist.task.service.domain.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

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

    doNothing().when(taskService).createTask(any());

    taskApplicationService.createTask(user,createTaskCommand);

    Mockito.verify(taskService).createTask(captor.capture());
    assertThat(captor.getValue()).usingRecursiveComparison().isEqualTo(task);

  }
}

package com.todolist.task.service.adapter.http;

import static com.todolist.task.service.common.Constant.USER_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.task.service.HttpControllerTest;
import com.todolist.task.service.adapter.http.models.CreateTaskCommand;
import com.todolist.task.service.application.TaskApplicationService;
import com.todolist.task.service.application.models.TaskDTO;
import com.todolist.task.service.domain.enums.Priority;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(TaskController.class)
class TaskControllerTest extends HttpControllerTest {

  @MockBean
  private TaskApplicationService taskApplicationService;

  @Test
  void should_create_task_successfully() throws Exception {
    String user = "user_id";
    CreateTaskCommand createTaskCommand = CreateTaskCommand.builder().description("test")
        .priority(Priority.HIGH)
        .build();

    doNothing().when(taskApplicationService).createTask(any(), any());

    mockMvc.perform(post("/task")
            .header(USER_ID, user)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(createTaskCommand)))
        .andExpect(status().isOk());

    verify(taskApplicationService).createTask(any(), any());
  }

  @Test
  void should_get_all_tasks() throws Exception {
    String user = "user_id";
    TaskDTO task1 = TaskDTO.builder().build();
    TaskDTO task2 = TaskDTO.builder().build();

    doReturn(List.of(task1, task2)).when(taskApplicationService).getAllTasks(user);

    mockMvc.perform(get("/task")
            .header(USER_ID, user))
        .andExpect(status().isOk());

    verify(taskApplicationService).getAllTasks(any());
  }

  @Test
  void should_delete_task() throws Exception {
    String taskId = "task123";
    String userId = "user123";

    doNothing().when(taskApplicationService).deleteTask(taskId, userId);

    mockMvc.perform(delete("/task/" + taskId)
            .header(USER_ID, userId))
        .andExpect(status().isOk());

    verify(taskApplicationService).deleteTask(taskId, userId);
  }
}

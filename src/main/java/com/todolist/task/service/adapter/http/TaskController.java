package com.todolist.task.service.adapter.http;

import static com.todolist.task.service.common.Constant.USER_ID;

import com.todolist.task.service.adapter.http.models.CreateTaskCommand;
import com.todolist.task.service.application.TaskApplicationService;
import com.todolist.task.service.application.models.TaskDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class TaskController {

  private final TaskApplicationService taskApplicationService;

  @PostMapping("/task")
  public void createTask(@RequestHeader(USER_ID) String user,
      @RequestBody CreateTaskCommand createTaskCommand) {
    taskApplicationService.createTask(user, createTaskCommand);
  }

  @GetMapping("/tasks")
  public List<TaskDTO> getAllTasks(@RequestHeader(USER_ID) String user) {
    return taskApplicationService.getAllTasks(user);
  }

  @DeleteMapping("/task/{id}")
  public void deleteTask(@PathVariable("id") String taskId,
      @RequestHeader(USER_ID) String user) {
    taskApplicationService.deleteTask(taskId, user);
  }

}

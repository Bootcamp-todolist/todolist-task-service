package com.todolist.task.service.adapter.http;

import static com.todolist.task.service.common.Constant.USER_ID;

import com.todolist.task.service.adapter.http.models.CreateTaskCommand;
import com.todolist.task.service.application.TaskApplicationService;
import com.todolist.task.service.application.models.TaskDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

  private final TaskApplicationService taskApplicationService;

  @PostMapping
  public void createTask(@RequestHeader(USER_ID) String user,
      @RequestBody CreateTaskCommand createTaskCommand) {
    taskApplicationService.createTask(user, createTaskCommand);
  }

  @GetMapping
  public List<TaskDTO> getAllTasks(@RequestHeader(USER_ID) String user) {
    return taskApplicationService.getAllTasks(user);
  }

}

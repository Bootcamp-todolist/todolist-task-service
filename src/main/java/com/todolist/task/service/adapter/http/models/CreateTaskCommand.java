package com.todolist.task.service.adapter.http.models;

import com.todolist.task.service.domain.enums.Priority;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateTaskCommand {
  private String title;
  private String description;
  private Priority priority;
  private Instant dueDate;

}

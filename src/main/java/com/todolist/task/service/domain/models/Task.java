package com.todolist.task.service.domain.models;

import com.todolist.task.service.domain.enums.Priority;
import com.todolist.task.service.domain.enums.TaskStatus;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

  private String title;
  private String description;
  private Priority priority;
  private TaskStatus status;
  private Instant dueDate;
  private boolean deleted;
  private Instant createdTime;

  private String createdBy;

  private Instant updatedTime;

  private String updatedBy;
}

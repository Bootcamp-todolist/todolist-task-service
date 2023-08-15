package com.todolist.task.service.adapter.mysql.models;

import static jakarta.persistence.EnumType.STRING;

import com.todolist.task.service.domain.enums.Priority;
import com.todolist.task.service.domain.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TaskPersistModel extends Auditable {

  @Id
  @UuidGenerator
  private String id;

  private String title;
  private String description;
  @Enumerated(STRING)
  private Priority priority;
  @Enumerated(STRING)
  private TaskStatus status;
  private Instant dueDate;
  private boolean deleted;
}

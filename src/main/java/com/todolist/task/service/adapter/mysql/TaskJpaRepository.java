package com.todolist.task.service.adapter.mysql;

import com.todolist.task.service.adapter.mysql.models.TaskPersistModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<TaskPersistModel, String> {

  List<TaskPersistModel> findByCreatedByAndDeletedFalse(String user);
}

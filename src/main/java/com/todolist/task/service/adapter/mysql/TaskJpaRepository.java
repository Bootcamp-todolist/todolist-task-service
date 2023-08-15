package com.todolist.task.service.adapter.mysql;

import com.todolist.task.service.adapter.mysql.models.TaskPersistModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<TaskPersistModel, String> {

}

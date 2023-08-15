package com.todolist.task.service.adapter.mysql;

import com.todolist.task.service.adapter.mysql.mapper.TaskPersistModelMapper;
import com.todolist.task.service.domain.TaskRepository;
import com.todolist.task.service.domain.models.Task;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

  private final TaskJpaRepository taskJpaRepository;


  @Override
  public void createTask(Task task) {
    taskJpaRepository.save(TaskPersistModelMapper.MAPPER.toPersistModel(task));
  }

  @Override
  public List<Task> findByCreatedByAndDeletedFalse(String user) {
    return TaskPersistModelMapper.MAPPER.toDomain(taskJpaRepository.findByCreatedByAndDeletedFalse(user));
  }
}

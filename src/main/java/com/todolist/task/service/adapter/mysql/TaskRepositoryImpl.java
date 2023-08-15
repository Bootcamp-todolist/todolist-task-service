package com.todolist.task.service.adapter.mysql;

import com.todolist.task.service.adapter.mysql.mapper.TaskPersistModelMapper;
import com.todolist.task.service.adapter.mysql.models.TaskPersistModel;
import com.todolist.task.service.domain.TaskRepository;
import com.todolist.task.service.domain.models.Task;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

  private final TaskJpaRepository taskJpaRepository;


  @Override
  public void save(Task task) {
    taskJpaRepository.save(TaskPersistModelMapper.MAPPER.toPersistModel(task));
  }

  @Override
  public List<Task> findByCreatedByAndDeletedFalse(String user) {
    return TaskPersistModelMapper.MAPPER.toDomain(
        taskJpaRepository.findByCreatedByAndDeletedFalse(user));
  }

  @Override
  public Task findById(String taskId) {
    TaskPersistModel persistModel = taskJpaRepository.findById(taskId).orElse(null);
    if (Objects.isNull(persistModel)) {
      return null;
    }
    return TaskPersistModelMapper.MAPPER.toDomain(persistModel);
  }
}

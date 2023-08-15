package com.todolist.task.service.adapter.mysql;

import com.todolist.task.service.adapter.mysql.mapper.TaskPersistModelMapper;
import com.todolist.task.service.domain.TaskRepository;
import com.todolist.task.service.domain.models.Task;
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
}

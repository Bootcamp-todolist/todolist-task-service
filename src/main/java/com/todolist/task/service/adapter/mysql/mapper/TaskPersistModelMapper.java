package com.todolist.task.service.adapter.mysql.mapper;

import com.todolist.task.service.adapter.mysql.models.TaskPersistModel;
import com.todolist.task.service.domain.models.Task;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TaskPersistModelMapper {

  public static final TaskPersistModelMapper MAPPER = Mappers.getMapper(
      TaskPersistModelMapper.class);


  public abstract TaskPersistModel toPersistModel(Task task);

  public abstract List<Task> toDomain(List<TaskPersistModel> taskPersistModels);
}

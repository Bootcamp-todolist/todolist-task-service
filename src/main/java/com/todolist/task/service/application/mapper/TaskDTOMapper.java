package com.todolist.task.service.application.mapper;

import com.todolist.task.service.application.models.TaskDTO;
import com.todolist.task.service.domain.models.Task;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TaskDTOMapper {

  public static final TaskDTOMapper MAPPER = Mappers.getMapper(
      TaskDTOMapper.class);

  public abstract List<TaskDTO> toDTO(List<Task> tasks);

}

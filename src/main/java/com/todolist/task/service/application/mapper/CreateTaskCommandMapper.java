package com.todolist.task.service.application.mapper;

import com.todolist.task.service.adapter.http.models.CreateTaskCommand;
import com.todolist.task.service.domain.models.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CreateTaskCommandMapper {

  public static final CreateTaskCommandMapper MAPPER = Mappers.getMapper(
      CreateTaskCommandMapper.class);


  @Mapping(target = "createdBy",source = "user")
  public abstract Task toDomain(CreateTaskCommand createTaskCommand , String user);

}

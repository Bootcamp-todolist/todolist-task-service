package com.todolist.task.service.application.mapper

import com.todolist.task.service.application.models.CreateTaskCommand
import com.todolist.task.service.domain.enums.TaskStatus
import com.todolist.task.service.domain.models.Task
import java.time.Instant

abstract class CreateTaskCommandMapper {
    companion object {
        fun toDomain(command: CreateTaskCommand,user: String): Task {
            return Task(
                description = command.description,
                title = command.title,
                dueDate = command.dueDate,
                priority = command.priority,
                status = TaskStatus.TODO,
                createdBy = user,
                createdTime = Instant.now(),
                updatedBy = user,
                updatedTime = Instant.now()

            )
        }
    }
}

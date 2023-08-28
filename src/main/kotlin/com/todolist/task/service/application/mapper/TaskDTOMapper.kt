package com.todolist.task.service.application.mapper

import com.todolist.task.service.application.models.TaskDTO
import com.todolist.task.service.domain.models.Task

abstract class TaskDTOMapper {
    companion object {
        fun toDTO(task: Task): TaskDTO {
            return TaskDTO(
                id = task.id,
                title = task.title,
                description = task.description,
                priority = task.priority,
                status = task.status,
                dueDate = task.dueDate
            )
        }
    }
}

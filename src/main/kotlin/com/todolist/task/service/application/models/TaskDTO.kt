package com.todolist.task.service.application.models

import com.todolist.task.service.domain.enums.Priority
import com.todolist.task.service.domain.enums.TaskStatus
import java.time.Instant

data class TaskDTO(
    var id: String,
    val title: String,
    val description: String,
    val priority: Priority,
    val status: TaskStatus,
    val dueDate: Instant
)

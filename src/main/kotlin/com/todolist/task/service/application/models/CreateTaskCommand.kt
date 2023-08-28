package com.todolist.task.service.application.models

import com.todolist.task.service.domain.enums.Priority
import java.time.Instant

data class CreateTaskCommand(
    val title: String,
    val description: String,
    val priority: Priority,
    val dueDate: Instant
)

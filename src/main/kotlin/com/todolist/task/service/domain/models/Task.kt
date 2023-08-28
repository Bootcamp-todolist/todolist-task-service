package com.todolist.task.service.domain.models

import com.todolist.task.service.domain.enums.Priority
import com.todolist.task.service.domain.enums.TaskStatus
import java.time.Instant

data class Task(
    val id: String = "",
    val title: String,
    val description: String,
    val priority: Priority,
    var status: TaskStatus,
    val dueDate: Instant,
    var deleted: Boolean = false,
    val createdTime: Instant,
    val createdBy: String,
    val updatedTime: Instant,
    var updatedBy: String
) {
    fun deleteTask(user: String) {
        this.deleted = true
        this.updatedBy = user
    }

}

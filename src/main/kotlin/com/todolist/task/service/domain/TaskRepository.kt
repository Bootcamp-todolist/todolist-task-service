package com.todolist.task.service.domain

import com.todolist.task.service.domain.models.Task

interface TaskRepository {
    fun save(task: Task)
    fun findByCreatedByAndDeletedFalse(user: String): List<Task>?
    fun findById(taskId: String): Task?
}

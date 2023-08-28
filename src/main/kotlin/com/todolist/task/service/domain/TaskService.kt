package com.todolist.task.service.domain

import com.todolist.task.service.domain.models.Task
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {
    fun save(task: Task) {
        taskRepository.save(task)
    }

    fun findByCreatedByAndDeletedFalse(user: String): List<Task>? {
        return taskRepository.findByCreatedByAndDeletedFalse(user)
    }

    fun findById(taskId: String): Task? {
        return taskRepository.findById(taskId)
    }
}

package com.todolist.task.service.application

import com.todolist.task.service.application.mapper.CreateTaskCommandMapper
import com.todolist.task.service.application.mapper.TaskDTOMapper
import com.todolist.task.service.application.models.CreateTaskCommand
import com.todolist.task.service.application.models.TaskDTO
import com.todolist.task.service.domain.TaskService
import com.todolist.task.service.domain.models.Task
import com.todolist.task.service.exception.BusinessException
import com.todolist.task.service.exception.ErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class TaskApplicationService(
    private val taskService: TaskService
) {
    fun createTask(user: String, createTaskCommand: CreateTaskCommand) {
        val task = CreateTaskCommandMapper.toDomain(createTaskCommand, user)
        taskService.save(task)
    }

    fun getAllTasks(user: String): List<TaskDTO>? {
        return taskService.findByCreatedByAndDeletedFalse(user)
            ?.map { task -> TaskDTOMapper.toDTO(task) }
    }

    fun deleteTask(taskId: String, user: String) {
        val task = taskService.findById(taskId)
            ?: throw BusinessException(ErrorMessage.TASK_NOT_FOUND, HttpStatus.NOT_FOUND)
        validateTask(user, task)
        task.deleteTask(user)
        taskService.save(task)
    }

    companion object {
        private fun validateTask(user: String, task: Task) {
            if (task.createdBy != user) {
                throw BusinessException(ErrorMessage.NO_PERMISSION, HttpStatus.FORBIDDEN)
            }
        }
    }
}

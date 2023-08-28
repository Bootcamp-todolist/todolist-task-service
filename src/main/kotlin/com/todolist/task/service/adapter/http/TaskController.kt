package com.todolist.task.service.adapter.http

import com.todolist.task.service.application.models.CreateTaskCommand
import com.todolist.task.service.application.TaskApplicationService
import com.todolist.task.service.application.models.TaskDTO
import com.todolist.task.service.common.Constant
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class TaskController(
    private val taskApplicationService: TaskApplicationService
) {
    @PostMapping("/task")
    fun createTask(
        @RequestHeader(Constant.USER_ID) user: String,
        @RequestBody createTaskCommand: CreateTaskCommand
    ) {
        taskApplicationService.createTask(user, createTaskCommand)
    }

    @GetMapping("/tasks")
    fun getAllTasks(@RequestHeader(Constant.USER_ID) user: String): List<TaskDTO>? {
        return taskApplicationService.getAllTasks(user)
    }

    @DeleteMapping("/task/{id}")
    fun deleteTask(
        @PathVariable("id") taskId: String,
        @RequestHeader(Constant.USER_ID) user: String
    ) {
        taskApplicationService.deleteTask(taskId, user)
    }
}

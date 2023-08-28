package com.todolist.task.service.adapter.http

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.todolist.task.service.application.TaskApplicationService
import com.todolist.task.service.application.models.CreateTaskCommand
import com.todolist.task.service.application.models.TaskDTO
import com.todolist.task.service.common.Constant.USER_ID
import com.todolist.task.service.domain.enums.Priority
import com.todolist.task.service.domain.enums.TaskStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.doNothing
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.Instant

@WebMvcTest(TaskController::class)
class TaskControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var taskApplicationService: TaskApplicationService

    @Test
    fun `should create task successfully`() {
        val user = "user_id"
        val createTaskCommand = CreateTaskCommand(
            description = "description",
            title = "title",
            dueDate = Instant.now(),
            priority = Priority.LOW
        )

        doNothing().`when`(taskApplicationService).createTask(any(), any())

        mockMvc.perform(
            MockMvcRequestBuilders.post("/task")
                .header(USER_ID, user)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    ObjectMapper()
                        .registerModule(JavaTimeModule())
                        .writeValueAsString(createTaskCommand)
                )
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
        verify(taskApplicationService).createTask(any(), any())
    }

    @Test
    fun `should get all tasks`() {
        val user = "user_id"
        val taskList = List(2) {
            TaskDTO(
                id = "id",
                description = "description",
                title = "title",
                status = TaskStatus.TODO,
                dueDate = Instant.now(),
                priority = Priority.LOW
            ) }
        doReturn(taskList).`when`(taskApplicationService).getAllTasks(user)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/tasks")
                .header(USER_ID, user)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)

        verify(taskApplicationService).getAllTasks(any())
    }

    @Test
    fun `should delete task`() {
        val taskId = "task123"
        val userId = "user123"

        doNothing().`when`(taskApplicationService).deleteTask(taskId, userId)

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/task/$taskId")
                .header(USER_ID, userId)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)

        verify(taskApplicationService).deleteTask(taskId, userId)
    }

}

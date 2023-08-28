package com.todolist.task.service.application

import com.todolist.task.service.application.models.CreateTaskCommand
import com.todolist.task.service.domain.TaskService
import com.todolist.task.service.domain.enums.Priority
import com.todolist.task.service.domain.enums.TaskStatus
import com.todolist.task.service.domain.models.Task
import com.todolist.task.service.exception.BusinessException
import com.todolist.task.service.exception.ErrorMessage
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify
import org.springframework.http.HttpStatus
import java.time.Instant

class TaskApplicationServiceTest {
    @Mock
    private lateinit var taskService: TaskService

    @InjectMocks
    private lateinit var taskApplicationService: TaskApplicationService

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

    @Test
    fun `should create task successfully`() {
        val user = "user_id"
        val createTaskCommand = CreateTaskCommand(
            description = "description",
            title = "title",
            dueDate = Instant.now(),
            priority = Priority.LOW
        )
        val task = Task(
            id = "id",
            description = "description",
            title = "title",
            status = TaskStatus.TODO,
            dueDate = Instant.now(),
            priority = Priority.LOW,
            createdTime = Instant.now(),
            createdBy = user,
            updatedTime = Instant.now(),
            updatedBy = user
        )
        val captor: ArgumentCaptor<Task> = ArgumentCaptor.forClass(Task::class.java)

        doNothing().`when`(taskService).save(any())

        taskApplicationService.createTask(user, createTaskCommand)

        verify(taskService).save(capture(captor))
        assertThat(captor.value)
            .usingRecursiveComparison()
            .ignoringFields("id", "dueDate","createdTime", "updatedTime")
            .isEqualTo(task)
    }

    @Test
    fun `should get all tasks`()
    {
        val user = "user1"
        val tasks = List(2) {
            Task(
                id = "id",
                description = "description",
                title = "title",
                status = TaskStatus.TODO,
                dueDate = Instant.now(),
                priority = Priority.LOW,
                createdTime = Instant.now(),
                createdBy = user,
                updatedTime = Instant.now(),
                updatedBy = user
            ) }

        `when`(taskService.findByCreatedByAndDeletedFalse(user)).thenReturn(tasks)

        val taskDTOs = taskApplicationService.getAllTasks(user)

        assertEquals(tasks.size, taskDTOs?.size)
        verify(taskService).findByCreatedByAndDeletedFalse(user)
    }

    @Test
    fun `should delete task successfully`()
    {
        val taskId = "taskId1"
        val user = "user1"
        val task = Task(
            id = "id",
            description = "description",
            title = "title",
            status = TaskStatus.TODO,
            dueDate = Instant.now(),
            priority = Priority.LOW,
            createdTime = Instant.now(),
            createdBy = user,
            updatedTime = Instant.now(),
            updatedBy = user
        )

        `when`(taskService.findById(taskId)).thenReturn(task)

        taskApplicationService.deleteTask(taskId, user)

        verify(taskService).findById(taskId)
        verify(taskService).save(task)
        assertTrue(task.deleted)
        assertEquals(user, task.updatedBy)
    }

    @Test
    fun `should throw 404 when task not found`()
    {
        val taskId = "taskId1"
        val user = "user1"

        `when`(taskService.findById(taskId)).thenReturn(null)

        val exception = Assertions.catchThrowable {
            taskApplicationService.deleteTask(
                taskId, user)
        }
        val businessException: BusinessException = exception as BusinessException
        assertThat(businessException.httpStatus).isEqualTo(HttpStatus.NOT_FOUND)
        assertThat(businessException.errorMessage).isEqualTo(ErrorMessage.TASK_NOT_FOUND)
    }

    @Test
    fun `should throw 403 when task not found`()
    {
        val taskId = "taskId1"
        val user = "user1"
        val task = Task(
            id = "id",
            description = "description",
            title = "title",
            status = TaskStatus.TODO,
            dueDate = Instant.now(),
            priority = Priority.LOW,
            createdTime = Instant.now(),
            createdBy = "differentUser",
            updatedTime = Instant.now(),
            updatedBy = user
        )

        `when`(taskService.findById(taskId)).thenReturn(task)

        val exception = Assertions.catchThrowable {
            taskApplicationService.deleteTask(
                taskId, user)
        }
        val businessException: BusinessException = exception as BusinessException
        assertThat(businessException.httpStatus).isEqualTo(HttpStatus.FORBIDDEN)
        assertThat(businessException.errorMessage).isEqualTo(ErrorMessage.NO_PERMISSION)
    }
}

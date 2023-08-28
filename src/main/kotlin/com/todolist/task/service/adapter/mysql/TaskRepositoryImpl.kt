package com.todolist.task.service.adapter.mysql

import com.todolist.task.service.adapter.mysql.mapper.TaskPersistModelMapper
import com.todolist.task.service.domain.TaskRepository
import com.todolist.task.service.domain.models.Task
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TaskRepositoryImpl (
    private val taskJpaRepository: TaskJpaRepository
) : TaskRepository {
    override fun save(task: Task) {
        taskJpaRepository.save(TaskPersistModelMapper.toPersistModel(task))
    }

    override fun findByCreatedByAndDeletedFalse(user: String): List<Task>? {
        return taskJpaRepository.findByCreatedByAndDeletedFalse(user)?.map {
            taskPersistModel -> TaskPersistModelMapper.toDomain(taskPersistModel)
        }
    }

    override fun findById(taskId: String): Task? {
        val persistModel = taskJpaRepository.findById(taskId).orElse(null)
        return if (Objects.isNull(persistModel)) {
            null
        } else TaskPersistModelMapper.toDomain(persistModel)
    }
}


package com.todolist.task.service.adapter.mysql

import com.todolist.task.service.adapter.mysql.models.TaskPersistModel
import org.springframework.data.jpa.repository.JpaRepository

interface TaskJpaRepository : JpaRepository<TaskPersistModel, String?> {
    fun findByCreatedByAndDeletedFalse(user: String): List<TaskPersistModel>?
}

package com.todolist.task.service.adapter.mysql.mapper

import com.todolist.task.service.adapter.mysql.models.TaskPersistModel
import com.todolist.task.service.domain.models.Task

abstract class TaskPersistModelMapper {
    companion object{
        fun toDomain(persistModel: TaskPersistModel): Task {
            return Task(
                id = persistModel.id,
                title = persistModel.title,
                description = persistModel.description,
                priority = persistModel.priority,
                status = persistModel.status,
                dueDate = persistModel.dueDate,
                deleted = persistModel.deleted,
                createdTime = persistModel.createdTime,
                createdBy = persistModel.createdBy ?: "",
                updatedTime = persistModel.updatedTime,
                updatedBy = persistModel.updatedBy ?: ""
            )
        }

        fun toPersistModel(domain: Task): TaskPersistModel {
            val adminAccountPersistModel = TaskPersistModel(
                id =            domain.id,
                title =         domain.title,
                description =   domain.description,
                priority =      domain.priority,
                status =        domain.status,
                dueDate =       domain.dueDate,
                deleted =       domain.deleted,
            )
            adminAccountPersistModel.createdTime = domain.createdTime
            adminAccountPersistModel.createdBy = domain.createdBy
            adminAccountPersistModel.updatedTime = domain.updatedTime
            adminAccountPersistModel.updatedBy = domain.updatedBy
            return adminAccountPersistModel
        }
    }
}

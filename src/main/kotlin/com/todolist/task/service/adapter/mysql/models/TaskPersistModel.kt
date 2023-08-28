package com.todolist.task.service.adapter.mysql.models

import com.todolist.task.service.domain.enums.Priority
import com.todolist.task.service.domain.enums.TaskStatus
import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.Instant

@Entity
@Table(name = "task")
data class TaskPersistModel(
    @Id @UuidGenerator
    var id: String,
    val title: String,
    val description: String,
    @Enumerated(EnumType.STRING)
    val priority: Priority,
    @Enumerated(EnumType.STRING)
    val status: TaskStatus,
    val dueDate: Instant,
    val deleted: Boolean = false
) : Auditable(){
    constructor() : this("", "", "", Priority.LOW, TaskStatus.TODO, Instant.now(), false)
}

package com.todolist.task.service.adapter.mysql.models

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class Auditable {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdTime: Instant = Instant.now()

    @LastModifiedDate
    @Column(nullable = false)
    var updatedTime: Instant = Instant.now()

    var createdBy: String? = null

    var updatedBy: String? = null
}

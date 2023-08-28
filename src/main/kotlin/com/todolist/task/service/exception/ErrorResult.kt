package com.todolist.task.service.exception

import com.todolist.task.service.exception.ErrorMessage


data class ErrorResult(
    val errorKey: String,
    val errorMsg: String
) {
    constructor(error: ErrorMessage) : this(error.name, error.value)
}


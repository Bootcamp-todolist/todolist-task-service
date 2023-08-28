package com.todolist.task.service.advice

import com.todolist.task.service.exception.BusinessException
import com.todolist.task.service.exception.ErrorResult
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalControllerExceptionHandler : ResponseEntityExceptionHandler() {
    private val log = KotlinLogging.logger {}

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<String> {
        log.error("Unexpected error: ", e)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleException(e: BusinessException): ResponseEntity<ErrorResult> {
        log.error("Client error: ", e)
        return ResponseEntity(ErrorResult(e.errorMessage), e.httpStatus)
    }
}

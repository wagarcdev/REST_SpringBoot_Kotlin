package br.com.wagarcdev.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
@RestController
class CustomizedResponseEntityExceptionHandler: ResponseEntityExceptionHandler(){

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(exception: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {

        val exceptionResponse = ExceptionResponse(
            timestamp = Date(),
            message = exception.message,
            details = request.getDescription(false)
        )

        return ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)

    }

    @ExceptionHandler(UnsupportedMathOperationException::class)
    fun handleBadRequestExceptions(exception: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {

        val exceptionResponse = ExceptionResponse(
            timestamp = Date(),
            message = exception.message,
            details = request.getDescription(false)
        )

        return ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST)

    }

}
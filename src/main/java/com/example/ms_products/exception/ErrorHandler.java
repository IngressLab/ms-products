package com.example.ms_products.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.ms_products.exception.ExceptionMessages.UNEXPECTED_ERROR;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception exception){
        log.error("UnexpectedError",exception);
        return  new ErrorResponse(UNEXPECTED_ERROR.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException exception){
        log.error("NotFoundException",exception);
        return  new ErrorResponse(exception.getMessage());
    }
    @ExceptionHandler(FileStorageException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(FileStorageException exception){
        log.error("FileStorageException",exception);
        return  new ErrorResponse(exception.getMessage());
    }

}

package ru.training.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class TrainingErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TrainingExceptionModel badRequest(TrainingBadRequestException trainingBadRequestException) {
        log.warn("Error 400: {}", trainingBadRequestException.getMessage());
        return new TrainingExceptionModel(HttpStatus.BAD_REQUEST.toString(),
                trainingBadRequestException.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public TrainingExceptionModel notFoundException(TrainingNotFoundException trainingNotFoundException) {
        log.warn("Error 404: {}", trainingNotFoundException.getMessage());
        return new TrainingExceptionModel(HttpStatus.NOT_FOUND.toString(),
                trainingNotFoundException.getMessage(), LocalDateTime.now());
    }



    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public TrainingExceptionModel integrityViolation(DataIntegrityViolationException dataIntegrityViolationException) {
        log.warn("Error 404: {}", dataIntegrityViolationException.getRootCause().toString());
        return new TrainingExceptionModel(HttpStatus.CONFLICT.toString(),
                dataIntegrityViolationException.getRootCause().toString(), LocalDateTime.now());
    }


}

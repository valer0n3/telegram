package ru.training.exceptions;

public class TrainingBadRequestException extends RuntimeException {
    public TrainingBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}

package ru.training.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static ru.training.constants.ProjectConstants.DATE_TIME_PATTERN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingExceptionModel {
    private String errorStatus;
    private String errorMessage;
    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime errorTime;
}

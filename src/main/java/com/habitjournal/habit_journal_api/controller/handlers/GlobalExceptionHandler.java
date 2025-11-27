package com.habitjournal.habit_journal_api.controller.handlers;

import com.habitjournal.habit_journal_api.service.exceptions.DuplicateHabitException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateHabitException.class)
    public ResponseEntity<String> handleDuplicateHabit(DuplicateHabitException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}

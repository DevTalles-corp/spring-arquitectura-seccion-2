package com.habitjournal.habit_journal_api.service.exceptions;

public class DuplicateHabitException extends RuntimeException {
    public DuplicateHabitException(String name) {
        super("El hábito '" + name + "' ya existe. No se permiten duplicados.");
    }
}

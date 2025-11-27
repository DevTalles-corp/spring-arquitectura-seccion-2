package com.habitjournal.habit_journal_api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class HabitResponseDTO {
    private Long id;
    private String name;
    private List<LocalDateTime> logs;
}

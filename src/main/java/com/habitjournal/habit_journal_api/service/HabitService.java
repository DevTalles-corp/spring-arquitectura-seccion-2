package com.habitjournal.habit_journal_api.service;

import com.habitjournal.habit_journal_api.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.controller.dto.HabitResponseDTO;

import java.util.List;

public interface HabitService {
    HabitResponseDTO createNewHabit(HabitRequestDTO requestDTO);
    List<HabitResponseDTO> findAllHabits();
}

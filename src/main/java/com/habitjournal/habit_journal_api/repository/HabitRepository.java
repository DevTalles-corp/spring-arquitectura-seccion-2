package com.habitjournal.habit_journal_api.repository;

import com.habitjournal.habit_journal_api.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    @Query("SELECT DISTINCT h FROM Habit h JOIN FETCH h.logEntries")
    List<Habit> findAllWithLogs();
    Optional<Habit> findByName(String name);
}

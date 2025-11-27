package com.habitjournal.habit_journal_api.data;

import com.habitjournal.habit_journal_api.model.Habit;
import com.habitjournal.habit_journal_api.model.LogEntry;
import com.habitjournal.habit_journal_api.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final HabitRepository habitRepository;

    @Override
    public void run(String... args) throws Exception {

        // Solo insertamos si la base de datos está vacía
        if (habitRepository.count() == 0) {

            // 1. Hábito Reciente y Completo
            createHabitWithLogs(
                    "Aprender Arquitectura",
                    // Logs de hoy y anteayer
                    List.of(
                            LocalDateTime.now().minusDays(2),
                            LocalDateTime.now()
                    )
            );

            // 2. Hábito con Actividad Media
            createHabitWithLogs(
                    "Hacer Ejercicio (Yoga)",
                    // Logs de hace 3 y 5 días
                    List.of(
                            LocalDateTime.now().minusDays(5),
                            LocalDateTime.now().minusDays(3)
                    )
            );

            // 3. Hábito Sin Registros (Para probar la exclusión en consultas)
            Habit habit3 = new Habit();
            habit3.setName("Beber Agua (Inactivo)");
            habitRepository.save(habit3);


            System.out.println("✅ Datos de prueba cargados: 3 Hábitos.");
        }
    }

    // --- MÉTODO AUXILIAR PARA GESTIONAR LA CREACIÓN Y VINCULACIÓN ---
    private void createHabitWithLogs(String name, List<LocalDateTime> dates) {
        Habit habit = new Habit();
        habit.setName(name);

        // Mapea la lista de fechas a objetos LogEntry y asegura la vinculación bidireccional
        List<LogEntry> logs = dates.stream()
                .map(date -> {
                    LogEntry log = new LogEntry();
                    log.setEntryDate(date);
                    log.setHabit(habit); // ¡Vinculación bidireccional obligatoria para JPA!
                    return log;
                })
                .collect(Collectors.toList());

        // Asigna la lista de logs al padre (el cascade se encarga de guardar)
        habit.getLogEntries().addAll(logs);

        // Guardamos el padre
        habitRepository.save(habit);
    }
}
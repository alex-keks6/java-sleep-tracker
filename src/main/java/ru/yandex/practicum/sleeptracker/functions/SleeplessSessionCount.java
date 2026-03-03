package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.*;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.yandex.practicum.sleeptracker.SleepClassification.isSleeplessNight;

public class SleeplessSessionCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description;

    public SleeplessSessionCount(String description) {
        this.description = description;
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult(description, (long) 0);
        }

        LocalDateTime firstDateTime = sleepingSessions.getFirst().getBeginSleepingSession();
        LocalDateTime lastDateTime = sleepingSessions.getLast().getEndSleepingSession();

        // подсчет общего количества дней измерения
        Duration sessionsPeriod = Duration.between(
                firstDateTime.toLocalTime().isBefore(LocalTime.of(12, 0))
                        ? firstDateTime : firstDateTime.plusDays(1),
                lastDateTime.toLocalTime().isBefore(firstDateTime.toLocalTime())
                        ? lastDateTime.plusDays(2) : lastDateTime.plusDays(1));

        // подсчет количества не бессонных ночей
        Set<LocalDate> normalSessionCount = sleepingSessions.stream()
                .filter(session -> !isSleeplessNight(session))
                .map(session -> session.getEndSleepingSession().toLocalDate())
                .collect(Collectors.toSet());

        return new SleepAnalysisResult(description, sessionsPeriod.toDays() - normalSessionCount.size());
    }
}

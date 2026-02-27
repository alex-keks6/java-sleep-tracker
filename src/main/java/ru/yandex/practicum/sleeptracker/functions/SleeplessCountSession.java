package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleeplessCountSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description;

    public SleeplessCountSession(String description) {
        this.description = description;
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        LocalDateTime firstDateTime = sleepingSessions.getFirst().getBeginSleepingSession();
        LocalDateTime lastDateTime = sleepingSessions.getLast().getEndSleepingSession();

        // подсчет общего количества дней измерения
        Duration periodSessions = Duration.between(
                firstDateTime.toLocalTime().isBefore(LocalTime.of(12, 0))
                        ? firstDateTime : firstDateTime.minusDays(1),
                lastDateTime);

        // подсчет количества не бессонных ночей
        Set<LocalDate> normalCountSession = sleepingSessions.stream()
                .filter(session -> !isSleeplessNight(session))
                .map(session -> session.getEndSleepingSession().toLocalDate())
                .collect(Collectors.toSet());

        return new SleepAnalysisResult(description, periodSessions.toDays() - normalCountSession.size());
    }

    public boolean isSleeplessNight(SleepingSession sleepingSession) {
        return sleepingSession.getBeginSleepingSession().toLocalTime().isAfter(LocalTime.of(6, 0))
                && sleepingSession.getBeginSleepingSession().toLocalDate()
                .equals(sleepingSession.getEndSleepingSession().toLocalDate());
    }
}

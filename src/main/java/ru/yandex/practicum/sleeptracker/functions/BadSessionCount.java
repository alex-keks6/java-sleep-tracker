package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.util.List;
import java.util.function.Function;

public class BadSessionCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description;

    public BadSessionCount(String description) {
        this.description = description;
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long badSessionCount = sleepingSessions.stream()
                .filter(session -> session.getSleepQuality() == SleepQuality.BAD)
                .count();

        return new SleepAnalysisResult(description, badSessionCount);
    }
}

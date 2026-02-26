package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.util.List;
import java.util.function.Function;

public class BadCountSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description;

    public BadCountSession(String description) {
        this.description = description;
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long badCountSession = sleepingSessions.stream()
                .filter(session -> session.getSleepQuality() == SleepQuality.BAD)
                .count();

        return new SleepAnalysisResult(description, badCountSession);
    }
}

package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AverageDurationSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description;

    public AverageDurationSession(String description) {
        this.description = description;
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long sumMinutes = sleepingSessions.stream()
                .map(session -> Duration.between(session.getBeginSleepingSession(),
                        session.getEndSleepingSession()))
                .mapToLong(Duration::toMinutes)
                .sum();

        return new SleepAnalysisResult(description, sumMinutes / sleepingSessions.size());
    }
}

package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class SessionMinDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description;

    public SessionMinDuration(String description) {
        this.description = description;
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Duration minDuration = sleepingSessions.stream()
                .map(session -> Duration.between(session.getBeginSleepingSession(),
                        session.getEndSleepingSession()))
                .min((duration1, duration2) -> (int) (duration1.toMinutes() - duration2.toMinutes()))
                .orElse(Duration.ofMinutes(0));

        return new SleepAnalysisResult(description, minDuration.toMinutes());
    }
}

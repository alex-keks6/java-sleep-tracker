package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class NumberSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description;

    public NumberSessions(String description) {
        this.description = description;
    }

    public SleepAnalysisResult apply(List<SleepingSession> list) {
        return new SleepAnalysisResult(description, list.size());
    }
}



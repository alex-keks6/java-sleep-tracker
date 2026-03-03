package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepClassification;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

import static ru.yandex.practicum.sleeptracker.SleepClassification.*;

public class SessionUserType implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description;

    public SessionUserType(String description) {
        this.description = description;
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long owlSessionCount = sleepingSessions.stream()
                .filter(SleepClassification::isOwlNight)
                .count();

        long skylarkSessionCount = sleepingSessions.stream()
                .filter(SleepClassification::isSkylarkNight)
                .count();

        long pigeonSessionCount = sleepingSessions.stream()
                .filter(session -> !isSleeplessNight(session)
                        && !isSkylarkNight(session)
                        && !isOwlNight(session))
                .count();

        // если есть сомнения, то всегда возвращаем "голубя" (по ТЗ)
        if (owlSessionCount == skylarkSessionCount
                || skylarkSessionCount == pigeonSessionCount
                || owlSessionCount == pigeonSessionCount) {
            return new SleepAnalysisResult(description, "Голубь");
        }

        long maxSessionCount = Math.max(owlSessionCount, Math.max(skylarkSessionCount, pigeonSessionCount));

        if (maxSessionCount == owlSessionCount) {
            return new SleepAnalysisResult(description, "Сова");
        } else if (maxSessionCount == skylarkSessionCount) {
            return new SleepAnalysisResult(description, "Жаворонок");
        } else {
            return new SleepAnalysisResult(description, "Голубь");
        }
    }
}
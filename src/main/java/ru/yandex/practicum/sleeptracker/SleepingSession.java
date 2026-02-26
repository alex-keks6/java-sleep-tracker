package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;

public class SleepingSession {
    private final LocalDateTime beginSleepingSession;
    private final LocalDateTime endSleepingSession;
    private final SleepQuality sleepQuality;

    public SleepingSession(LocalDateTime beginSleepingSession, LocalDateTime endSleepingSession,
                           SleepQuality sleepQuality) {
        this.beginSleepingSession = beginSleepingSession;
        this.endSleepingSession = endSleepingSession;
        this.sleepQuality = sleepQuality;
    }
}

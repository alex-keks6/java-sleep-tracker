package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    private final LocalDateTime beginSleepingSession;
    private final LocalDateTime endSleepingSession;

    public SleepingSession(LocalDateTime beginSleepingSession, LocalDateTime endSleepingSession) {
        this.beginSleepingSession = beginSleepingSession;
        this.endSleepingSession = endSleepingSession;
    }
}

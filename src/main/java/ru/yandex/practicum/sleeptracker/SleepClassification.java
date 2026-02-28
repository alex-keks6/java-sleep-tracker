package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;

public final class SleepClassification {
    public static boolean isSleeplessNight(SleepingSession sleepingSession) {
        return sleepingSession.getBeginSleepingSession().toLocalTime().isAfter(LocalTime.of(6, 0))
                && sleepingSession.getBeginSleepingSession().toLocalDate()
                .equals(sleepingSession.getEndSleepingSession().toLocalDate());
    }

    public static boolean isOwlNight(SleepingSession sleepingSession) {
        return sleepingSession.getBeginSleepingSession().toLocalTime().isAfter(LocalTime.of(23, 0))
                && sleepingSession.getEndSleepingSession().toLocalTime().isAfter(LocalTime.of(9, 0))
                && !sleepingSession.getBeginSleepingSession().toLocalDate()
                .equals(sleepingSession.getEndSleepingSession().toLocalDate());
    }

    public static boolean isSkylarkNight(SleepingSession sleepingSession) {
        return sleepingSession.getBeginSleepingSession().toLocalTime().isBefore(LocalTime.of(22, 0))
                && sleepingSession.getEndSleepingSession().toLocalTime().isBefore(LocalTime.of(7, 0))
                && !sleepingSession.getBeginSleepingSession().toLocalDate()
                .equals(sleepingSession.getEndSleepingSession().toLocalDate());
    }
}

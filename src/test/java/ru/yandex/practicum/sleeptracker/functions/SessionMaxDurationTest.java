package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class SessionMaxDurationTest {
    @Test
    public void shouldReturnMaxDuration495Minutes() {
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 15),
                LocalDateTime.of(2025, 10, 2, 7, 30), SleepQuality.GOOD));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 23, 50),
                LocalDateTime.of(2025, 10, 3, 6, 40), SleepQuality.NORMAL));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 14, 10),
                LocalDateTime.of(2025, 10, 3, 15, 0), SleepQuality.NORMAL));

        long maxDuration = 495;

        Assertions.assertEquals(maxDuration, new SessionMaxDuration("").apply(sleepingSessions).getResult());
    }

    @Test
    public void shouldReturnDefaultDuration0Minutes() {
        List<SleepingSession> sleepingSessions = new ArrayList<>();

        long maxDuration = 0;

        Assertions.assertEquals(maxDuration, new SessionMaxDuration("").apply(sleepingSessions).getResult());
    }
}
package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class SleeplessSessionCountTest {
    @Test
    public void shouldReturnSleeplessSessionCount4() {
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 11, 59),
                LocalDateTime.of(2025, 10, 1, 17, 30), SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 17, 30),
                LocalDateTime.of(2025, 10, 2, 23, 59), SleepQuality.NORMAL));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 6, 1),
                LocalDateTime.of(2025, 10, 3, 15, 0), SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 4, 13, 15),
                LocalDateTime.of(2025, 10, 5, 16, 50), SleepQuality.GOOD));

        long sleeplessSessionCount = 4;

        Assertions.assertEquals(sleeplessSessionCount, new SleeplessSessionCount("").apply(sleepingSessions)
                .getResult());
    }

    @Test
    public void shouldReturnDefaultCount0() {
        List<SleepingSession> sleepingSessions = new ArrayList<>();

        long sleeplessSessionCount = 0;

        Assertions.assertEquals(sleeplessSessionCount, new BadSessionCount("").apply(sleepingSessions)
                .getResult());
    }

    @Test
    public void shouldReturnSleeplessSessionCount0() {
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 12, 1),
                LocalDateTime.of(2025, 10, 1, 19, 30), SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 0, 0),
                LocalDateTime.of(2025, 10, 2, 7, 5), SleepQuality.NORMAL));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 6, 0),
                LocalDateTime.of(2025, 10, 3, 16, 0), SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 15, 20),
                LocalDateTime.of(2025, 10, 4, 0, 1), SleepQuality.GOOD));

        long sleeplessSessionCount = 0;

        Assertions.assertEquals(sleeplessSessionCount, new SleeplessSessionCount("").apply(sleepingSessions)
                .getResult());
    }

    @Test
    public void shouldReturnSleeplessSessionCount29() {
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 12, 1),
                LocalDateTime.of(2025, 10, 1, 19, 30), SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 0, 0),
                LocalDateTime.of(2025, 10, 2, 7, 5), SleepQuality.NORMAL));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 6, 0),
                LocalDateTime.of(2025, 10, 3, 16, 0), SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 11, 1, 15, 20),
                LocalDateTime.of(2025, 11, 1, 16, 5), SleepQuality.GOOD));

        long sleeplessSessionCount = 29;

        Assertions.assertEquals(sleeplessSessionCount, new SleeplessSessionCount("").apply(sleepingSessions)
                .getResult());
    }
}
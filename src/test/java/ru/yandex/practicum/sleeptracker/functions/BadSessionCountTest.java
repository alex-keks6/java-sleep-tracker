package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class BadSessionCountTest {
    @Test
    public void shouldReturnBadSessionCount2() {
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 15),
                LocalDateTime.of(2025, 10, 2, 7, 30), SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 23, 50),
                LocalDateTime.of(2025, 10, 3, 6, 40), SleepQuality.NORMAL));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 14, 10),
                LocalDateTime.of(2025, 10, 3, 15, 0), SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 4, 13, 15),
                LocalDateTime.of(2025, 10, 5, 16, 50), SleepQuality.GOOD));

        long badSessionCount = 2;

        Assertions.assertEquals(badSessionCount, new BadSessionCount("").apply(sleepingSessions).getResult());
    }

    @Test
    public void shouldReturnDefaultCount0() {
        List<SleepingSession> sleepingSessions = new ArrayList<>();

        long badSessionCount = 0;

        Assertions.assertEquals(badSessionCount, new BadSessionCount("").apply(sleepingSessions).getResult());
    }
}
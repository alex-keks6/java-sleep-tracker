package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class SessionUserTypeTest {
    @Test
    public void shouldReturnTypeOwl() {
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 15),
                LocalDateTime.of(2025, 10, 2, 9, 30), SleepQuality.GOOD));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 21, 50),
                LocalDateTime.of(2025, 10, 3, 6, 40), SleepQuality.NORMAL));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 59),
                LocalDateTime.of(2025, 10, 4, 15, 0), SleepQuality.NORMAL));

        String userType = "Сова";

        Assertions.assertEquals(userType, new SessionUserType("").apply(sleepingSessions).getResult());
    }

    @Test
    public void shouldReturnDefaultPigeon() {
        List<SleepingSession> sleepingSessions = new ArrayList<>();

        String userType = "Голубь";

        Assertions.assertEquals(userType, new SessionUserType("").apply(sleepingSessions).getResult());
    }

    @Test
    public void shouldReturnTypePigeon() {
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 15),
                LocalDateTime.of(2025, 10, 2, 6, 30), SleepQuality.GOOD));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 21, 50),
                LocalDateTime.of(2025, 10, 3, 6, 40), SleepQuality.NORMAL));
        sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 16, 59),
                LocalDateTime.of(2025, 10, 3, 23, 5), SleepQuality.NORMAL));

        String userType = "Голубь";

        Assertions.assertEquals(userType, new SessionUserType("").apply(sleepingSessions).getResult());
    }
}
package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;

import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {
    // путь к файлу: src/main/resources/sleep_log.txt
    public static final String SEPARATOR = ";";
    public static final String DATE_TIME_PATTERN = "dd.MM.yy HH:mm";
    public static List<SleepingSession> sleepData;
    public static List<Function<List<SleepingSession>, SleepAnalysisResult>> analyticalFunctions;

    public static void main(String[] args) {
        String sleepLogFile = args[0];
        SleepDataLoader sleepDataLoader = new SleepDataLoader(Paths.get(sleepLogFile), SEPARATOR, DATE_TIME_PATTERN);

        if (!sleepDataLoader.isCorrectFile()) {
            return;
        }

        sleepData = sleepDataLoader.takeSleepingData();

        analyticalFunctions = List.of(new SessionNumber("Количество сессий сна"),
                new SessionMinDuration("Минимальная продолжительность сессии (в минутах)"),
                new SessionMaxDuration("Максимальная продолжительность сессии (в минутах)"),
                new SessionAverageDuration("Средняя продолжительность сессии (в минутах)"),
                new BadSessionCount("Количество сессий с плохим качеством сна"),
                new SleeplessSessionCount("Количество бессонных ночей"),
                new SessionUserType("Хронотип пользователя"));

        analyticalFunctions.forEach(function -> System.out.println(function.apply(sleepData)));
    }
}
package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.NumberSessions;

import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {
    public static final String SLEEP_LOG_PATH = "src/main/resources/sleep_log.txt";
    public static final String SEPARATOR = ";";
    public static final String DATE_TIME_PATTERN = "dd.MM.yy HH:mm";
    public static List<SleepingSession> sleepData;
    public static List<Function<List<SleepingSession>, SleepAnalysisResult>> analyticalFunctions;
    public static void main(String[] args) {
        SleepDataLoader sleepDataLoader = new SleepDataLoader(SLEEP_LOG_PATH, SEPARATOR, DATE_TIME_PATTERN);
        sleepData = sleepDataLoader.takeSleepingData();

        // todo: добавление аналитических функций
        analyticalFunctions = List.of(new NumberSessions("Количество сессий сна"));

        // todo: выполнение аналитических функций
        analyticalFunctions.forEach(function -> System.out.println(function.apply(sleepData)));
    }
}
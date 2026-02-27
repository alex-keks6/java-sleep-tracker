package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;

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

        // todo: добавление подсчета бессонных ночей
        analyticalFunctions = List.of(new NumberSessions("Количество сессий сна"),
                new MinDurationSession("Минимальная продолжительность сессии (в минутах)"),
                new MaxDurationSession("Максимальная продолжительность сессии (в минутах)"),
                new AverageDurationSession("Средняя продолжительность сессии (в минутах)"),
                new BadCountSession("Количество сессий с плохим качеством сна"),
                new SleeplessCountSession("Количество бессонных ночей"));

        analyticalFunctions.forEach(function -> System.out.println(function.apply(sleepData)));
    }
}
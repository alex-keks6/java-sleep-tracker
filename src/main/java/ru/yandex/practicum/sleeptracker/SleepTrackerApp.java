package ru.yandex.practicum.sleeptracker;

import java.util.Map;

public class SleepTrackerApp {
    public static final String SLEEP_LOG_PATH = "src/main/resources/sleep_log.txt";
    public static final String SEPARATOR = ";";
    public static final String DATE_TIME_PATTERN = "dd.MM.yy HH:mm";
    public static Map<SleepingSession, SleepQuality> sleepData;
    public static void main(String[] args) {
        SleepDataLoader sleepDataLoader = new SleepDataLoader(SLEEP_LOG_PATH, SEPARATOR, DATE_TIME_PATTERN);
        sleepData = sleepDataLoader.loadSleepData();

        // todo: протестировать получение данных из файла и работать с ними.
    }
}
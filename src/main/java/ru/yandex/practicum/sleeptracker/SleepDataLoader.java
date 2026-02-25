package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SleepDataLoader {
    private final String sleepLogPath;
    private final String separator;
    private final DateTimeFormatter dateTimeFormatter;

    public SleepDataLoader(String sleepLogPath, String separator, String dateTimePattern) {
        this.sleepLogPath = sleepLogPath;
        this.separator = separator;
        dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
    }

    // В ТЗ указано, что нужно придерживаться функционального подхода,
    // но я не понял, как реализовать считывание файла с помощью стрима
    public HashMap<SleepingSession, SleepQuality> loadSleepData() {
        HashMap<SleepingSession, SleepQuality> sleepData = new HashMap<>();
        String lineData;
        int separatorIndex;

        try (Reader reader = new FileReader(sleepLogPath);
             BufferedReader bReader = new BufferedReader(reader)) {
             while (bReader.ready()) {
                 lineData = bReader.readLine();
                 separatorIndex = lineData.lastIndexOf(separator);

                 if (separatorIndex != -1) {
                     sleepData.put(createSleepingSession(lineData.substring(0, separatorIndex)),
                             createSleepQuality(lineData.substring(separatorIndex + 1)));
                 } else {
                     System.out.printf("В строке: %s\n не найден символ разделения: %s\n", lineData, separator);
                 }
             }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return sleepData;
    }

    public SleepingSession createSleepingSession(String sleepingSessionStr) {
        int separatorIndex = sleepingSessionStr.indexOf(separator);
        String beginSleepingSessionStr = sleepingSessionStr.substring(0, separatorIndex);
        String endSleepingSessionStr = sleepingSessionStr.substring(separatorIndex + 1);

        return new SleepingSession(LocalDateTime.parse(beginSleepingSessionStr, dateTimeFormatter),
                LocalDateTime.parse(endSleepingSessionStr, dateTimeFormatter));

    }

    public SleepQuality createSleepQuality(String sleepQualityStr) {
        return SleepQuality.valueOf(sleepQualityStr);
    }
}

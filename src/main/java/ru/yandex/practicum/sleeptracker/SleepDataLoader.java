package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SleepDataLoader {
    private final String sleepLogPath;
    private final String separator;
    private final DateTimeFormatter dateTimeFormatter;

    public SleepDataLoader(String sleepLogPath, String separator, String dateTimePattern) {
        this.sleepLogPath = sleepLogPath;
        this.separator = separator;
        dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
    }

    public List<SleepingSession> takeSleepingData() {
        Optional<List<String>> stringSleepData = loadSleepDataFromFile();

        return stringSleepData.map(strings -> strings.stream()
                .filter(this::isCorrectParsingSleepingSession)
                .map(this::createSleepingSession)
                .toList())
                .orElseGet(ArrayList::new);
    }

    public Optional<List<String>> loadSleepDataFromFile() {
        try (Stream<String> lines = Files.lines(Paths.get(sleepLogPath), StandardCharsets.UTF_8)) {
             return Optional.of(lines.toList());
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        return Optional.empty();
    }

    public SleepingSession createSleepingSession(String lineData){
        int separatorIndexSleepingSession = lineData.indexOf(separator);
        int separatorIndexSleepQuality = lineData.lastIndexOf(separator);

        String beginSleepingSession = lineData.substring(0, separatorIndexSleepingSession);
        String endSleepingSession = lineData.substring(separatorIndexSleepingSession + 1,
                separatorIndexSleepQuality);
        String sleepQuality = lineData.substring(separatorIndexSleepQuality + 1);

        return new SleepingSession(LocalDateTime.parse(beginSleepingSession, dateTimeFormatter),
                LocalDateTime.parse(endSleepingSession, dateTimeFormatter), SleepQuality.valueOf(sleepQuality));

    }

    public boolean isCorrectParsingSleepingSession(String lineData) {
        int separatorIndexSleepingSession = lineData.indexOf(separator);
        int separatorIndexSleepQuality = lineData.lastIndexOf(separator);

        if (separatorIndexSleepingSession == -1 || separatorIndexSleepQuality == -1
                || separatorIndexSleepingSession == separatorIndexSleepQuality) {
            System.out.println("Ошибка парсинга сессии сна.\n" +
                    "Строка: " + lineData + "\n" +
                    "Индекс разделителя начала и конца сессии сна: " + separatorIndexSleepingSession + "\n" +
                    "Индекс разделителя сессии сна и его качества: " + separatorIndexSleepQuality);
            return false;
        }
        return true;
    }
}

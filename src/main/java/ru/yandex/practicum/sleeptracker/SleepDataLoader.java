package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.enums.SleepQuality;
import ru.yandex.practicum.sleeptracker.exceptions.DateTimeIncorrectException;
import ru.yandex.practicum.sleeptracker.exceptions.DateTimeSeparatorFindException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SleepDataLoader {
    private final Path sleepLogPath;
    private final String separator;
    private final DateTimeFormatter dateTimeFormatter;

    public SleepDataLoader(Path sleepLogPath, String separator, String dateTimePattern) {
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
        try (Stream<String> lines = Files.lines(sleepLogPath, StandardCharsets.UTF_8)) {
             return Optional.of(lines.toList());
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        return Optional.empty();
    }

    public SleepingSession createSleepingSession(String lineData) {
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

        try {
            // проверка на определение разделителей
            if (separatorIndexSleepingSession == -1 || separatorIndexSleepQuality == -1
                    || separatorIndexSleepingSession == separatorIndexSleepQuality) {
                throw new DateTimeSeparatorFindException("Ошибка определения разделителей при парсинге сессии сна.\n" +
                        "Строка: " + lineData + "\n" +
                        "Индекс разделителя начала и конца сессии сна: " + separatorIndexSleepingSession + "\n" +
                        "Индекс разделителя сессии сна и его качества: " + separatorIndexSleepQuality);
            }
            // проверка на парсинг
            SleepingSession testSleepingSession = createSleepingSession(lineData);
            // проверка на корректность диапазона
            if (testSleepingSession.getBeginSleepingSession().isAfter(testSleepingSession.getEndSleepingSession())) {
                throw new DateTimeIncorrectException("Ошибка. Некорректная дата и время.\n" +
                        "Дата и время начала сна: " + testSleepingSession.getBeginSleepingSession()
                        .format(dateTimeFormatter) + "\n" +
                        "Дата и время окончания сна: " + testSleepingSession.getEndSleepingSession()
                        .format(dateTimeFormatter));
            }
        } catch (DateTimeSeparatorFindException | DateTimeIncorrectException exp) {
            System.out.println(exp.getMessage());
            return false;
        } catch (DateTimeParseException exp) {
            System.out.println("Ошибка парсинга строки в LocalDateTime.");
            System.out.println(exp.getMessage());
            return false;
        }
        return true;
    }

    public boolean isCorrectFile() {
        try {
            if (!Files.exists(sleepLogPath)) {
                throw new IOException("Файл по пути " + sleepLogPath + " не найден.");
            } else if (Files.size(sleepLogPath) == 0) {
                throw new IOException("Файл " + sleepLogPath.getFileName() + " не содержит данных.");
            }
        } catch (IOException | InvalidPathException exp) {
            System.out.println(exp.getMessage());
            return false;
        }
        return true;
    }
}

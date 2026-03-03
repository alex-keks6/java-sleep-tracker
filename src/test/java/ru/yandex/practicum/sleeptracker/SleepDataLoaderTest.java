package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class SleepDataLoaderTest {
    Path path = Paths.get("src/main/resources/test_sleep_log.txt");

    @Test
    void shouldReturnFalseBecauseFileNotExist() {
        SleepDataLoader sleepDataLoader = new SleepDataLoader(path, ";", "dd.MM.yy HH:mm");

        Assertions.assertFalse(sleepDataLoader.isCorrectFile());
    }

    @Test
    void shouldReturnFalseBecauseFileEmpty() {
        try {
            Files.createFile(path);
        } catch (IOException exp) {
            exp.printStackTrace();
        }

        SleepDataLoader sleepDataLoader = new SleepDataLoader(path, ";", "dd.MM.yy HH:mm");

        Assertions.assertFalse(sleepDataLoader.isCorrectFile());

        try {
            Files.delete(path);
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    @Test
    void shouldReturnTrue() {
        String existFilePath = "src/main/resources/sleep_log.txt";

        SleepDataLoader sleepDataLoader = new SleepDataLoader(Paths.get(existFilePath), ";",
                "dd.MM.yy HH:mm");

        Assertions.assertTrue(sleepDataLoader.isCorrectFile());
    }
}
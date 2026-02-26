package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {
    String description;
    long result;

    public SleepAnalysisResult(String description, long result) {
        this.description = description;
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public Long getResult() {
        return result;
    }

    @Override
    public String toString() {
        return description + ": " + result;
    }
}

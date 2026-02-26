package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {
    String description;
    Integer result;

    public SleepAnalysisResult(String description, Integer result) {
        this.description = description;
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public Integer getResult() {
        return result;
    }

    @Override
    public String toString() {
        return description + ": " + result;
    }
}

package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class SleeplessCountSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description;

    public SleeplessCountSession(String description) {
        this.description = description;
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long sleeplessCountSession = sleepingSessions.stream()
                .filter()
                .count();

        // todo: проверить бессонная ночь или нет, можно следующим образом:
        //  если оба времени сессии были до рассматриваемого периода или оба после, то ночь бессонная
        //  также, нужно посчитать общее количество рассматриваемых дней с помощью первой и последней записи,
        //  они все отсортированы по условию.
        //  Нужно как-то определять, какие дни "рассмотрены", так как человек может поспать несколько
        //  раз да один день.

        // todo: про граничный случай: если чел задремал первый раз до 12 дня, то это
        //  в счет предыдущей ночи, а если после, то следующей, так что ещё это реализовать
        return new SleepAnalysisResult(description, sleeplessCountSession);
    }
}

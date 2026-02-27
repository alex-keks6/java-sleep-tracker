package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

public class SleeplessCountSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description;

    public SleeplessCountSession(String description) {
        this.description = description;
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        LocalDateTime firstDateTime = sleepingSessions.getFirst().getBeginSleepingSession();
        LocalDateTime lastDateTime = sleepingSessions.getLast().getEndSleepingSession();

        // подсчет общего количества дней измерения
        Duration periodSessions = Duration.between(
                firstDateTime.toLocalTime().isBefore(LocalTime.of(12, 0))
                        ? firstDateTime : firstDateTime.minusDays(1),
                lastDateTime);

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

        // todo: алгоритм: получаем список LocalDate (можно в целом и просто количество дней),
        //  причем если первый раз чел задремал до 12, то на 1 больше
        //  затем проходимся по записям с сеансами, и если начало сеанса больше 6:00, а конец меньше 24:00,
        //  то не добавляем в список нормальных снов, иначе добавляем, причем добавляем именно LocalDate,
        //  за который случился нормальный сон, можно собирать в Set, чтобы не было дубликатов.
        return new SleepAnalysisResult(description, 0);
    }

    public boolean isSleeplessNight(SleepingSession sleepingSession) {
        
    }
}

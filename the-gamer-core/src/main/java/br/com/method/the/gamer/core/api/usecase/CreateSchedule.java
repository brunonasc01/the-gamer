package br.com.method.the.gamer.core.api.usecase;

import br.com.method.the.gamer.core.api.model.Schedule;

import java.util.Optional;

public interface CreateSchedule {
    Optional<Schedule> execute(Schedule schedule);
}

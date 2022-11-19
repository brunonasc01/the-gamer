package br.com.method.the.gamer.core.api.usecase;

import br.com.method.the.gamer.core.api.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface RetrieveSchedule {

    Optional<Schedule> execute(Schedule schedule);

    List<Schedule> execute();
}

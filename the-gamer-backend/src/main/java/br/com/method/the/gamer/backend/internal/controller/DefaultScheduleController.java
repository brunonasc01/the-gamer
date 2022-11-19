package br.com.method.the.gamer.backend.internal.controller;

import br.com.method.the.gamer.backend.api.controller.ScheduleController;
import br.com.method.the.gamer.core.api.model.Schedule;
import br.com.method.the.gamer.core.api.usecase.CreateSchedule;
import br.com.method.the.gamer.core.api.usecase.RetrieveSchedule;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DefaultScheduleController implements ScheduleController {

    private final CreateSchedule createSchedule;

    private final RetrieveSchedule retrieveSchedule;

    @Override
    public List<Schedule> createSchedules(List<Schedule> schedules) {
        return this.createSchedule.execute(schedules.get(0)).stream().toList();
    }

    @Override
    public List<Schedule> retrieveSchedules() {
        return this.retrieveSchedule.execute();
    }
}

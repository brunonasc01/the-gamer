package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Schedule;
import br.com.method.the.gamer.core.api.repository.ScheduleRepository;
import br.com.method.the.gamer.core.api.usecase.RetrieveSchedule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultRetrieveSchedule implements RetrieveSchedule {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Optional<Schedule> execute(Schedule schedule) {
        return this.scheduleRepository.findById(schedule.getId());
    }

    @Override
    public List<Schedule> execute() {
        return this.scheduleRepository.findAll();
    }
}

package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Schedule;
import br.com.method.the.gamer.core.api.repository.ScheduleRepository;
import br.com.method.the.gamer.core.api.usecase.CreateSchedule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultCreateSchedule implements CreateSchedule {
    
    private final ScheduleRepository scheduleRepository;
    
    @Override
    public Optional<Schedule> execute(Schedule schedule) {
        return Optional.of(this.scheduleRepository.save(schedule));
    }
}

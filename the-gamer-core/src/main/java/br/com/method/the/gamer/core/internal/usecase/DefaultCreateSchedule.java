package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Gamer;
import br.com.method.the.gamer.core.api.model.Schedule;
import br.com.method.the.gamer.core.api.model.ScheduleStatus;
import br.com.method.the.gamer.core.api.repository.ScheduleRepository;
import br.com.method.the.gamer.core.api.usecase.CreateSchedule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultCreateSchedule implements CreateSchedule {
    
    private final ScheduleRepository scheduleRepository;
    
    @Override
    public Optional<Schedule> execute(Gamer gamer) {
        Schedule schedule = new Schedule();
        schedule.setCreatedBy(gamer.getCreatedBy());
        schedule.setCreatedDate(LocalDateTime.now());
        schedule.setGamer(gamer);
        schedule.setDay(LocalDate.now());
        schedule.setStatus(ScheduleStatus.OPEN);
        return Optional.of(this.scheduleRepository.save(schedule));
    }
}

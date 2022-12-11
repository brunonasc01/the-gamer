package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Schedule;
import br.com.method.the.gamer.core.api.model.Task;
import br.com.method.the.gamer.core.api.usecase.RetrieveSchedule;
import br.com.method.the.gamer.core.api.usecase.ValidateTask;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultValidadeTask implements ValidateTask {

    private final RetrieveSchedule retrieveSchedule;

    @Override
    public Optional<Task> execute(Task task) {
        Optional<Schedule> schedule = this.retrieveSchedule.execute(task.getSchedule().getId());
        if(schedule.isPresent() && schedule.get().getTasks().stream().allMatch(oldTask -> this.isSlotAvailable(task, oldTask))) {
            return Optional.of(task);
        }
        return Optional.empty();
    }

    private boolean isSlotAvailable(Task newTask, Task closedTask) {
        return newTask.getStart().isAfter(closedTask.getFinish()) || newTask.getStart().isEqual(closedTask.getFinish())
                || newTask.getStart().isBefore(closedTask.getStart()) &&
                (newTask.getFinish().isBefore(closedTask.getStart()) || newTask.getFinish().isEqual(closedTask.getStart()));
    }
}

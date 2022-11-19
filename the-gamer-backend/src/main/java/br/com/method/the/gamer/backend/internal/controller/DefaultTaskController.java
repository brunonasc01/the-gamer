package br.com.method.the.gamer.backend.internal.controller;

import br.com.method.the.gamer.backend.api.controller.ScheduleController;
import br.com.method.the.gamer.backend.api.controller.TaskController;
import br.com.method.the.gamer.core.api.model.Schedule;
import br.com.method.the.gamer.core.api.model.Task;
import br.com.method.the.gamer.core.api.usecase.CreateSchedule;
import br.com.method.the.gamer.core.api.usecase.CreateTask;
import br.com.method.the.gamer.core.api.usecase.RetrieveSchedule;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DefaultTaskController implements TaskController {

    private final CreateTask createTask;

    @Override
    public List<Task> createTask(List<Task> tasks) {
        return this.createTask.execute(tasks.get(0)).stream().toList();
    }

    @Override
    public List<Task> retrieveTasks() {
        return null;
    }
}

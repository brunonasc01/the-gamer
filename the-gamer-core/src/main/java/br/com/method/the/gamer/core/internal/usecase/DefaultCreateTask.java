package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Task;
import br.com.method.the.gamer.core.api.repository.TaskRepository;
import br.com.method.the.gamer.core.api.usecase.CreateTask;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultCreateTask implements CreateTask {

    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> execute(Task task) {
        return Optional.of(taskRepository.save(task));
    }
}

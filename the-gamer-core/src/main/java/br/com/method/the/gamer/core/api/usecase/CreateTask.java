package br.com.method.the.gamer.core.api.usecase;

import br.com.method.the.gamer.core.api.model.Task;

import java.util.Optional;

public interface CreateTask {

    Optional<Task> execute(Task task);
}

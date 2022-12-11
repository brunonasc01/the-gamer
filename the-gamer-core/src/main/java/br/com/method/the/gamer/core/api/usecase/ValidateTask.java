package br.com.method.the.gamer.core.api.usecase;

import br.com.method.the.gamer.core.api.model.Task;

import java.util.Optional;

public interface ValidateTask {

    Optional<Task> execute(Task task);
}

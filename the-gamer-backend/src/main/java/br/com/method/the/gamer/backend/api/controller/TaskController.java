package br.com.method.the.gamer.backend.api.controller;

import br.com.method.the.gamer.core.api.model.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface TaskController {

    @PostMapping(path = {"/api/v1/task"})
    List<Task> createTask(@RequestBody List<Task> tasks);

    @GetMapping(path = {"/api/v1task"})
    List<Task> retrieveTasks();
}

package br.com.method.the.gamer.backend.api.controller;

import br.com.method.the.gamer.core.api.model.Schedule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface ScheduleController {

    @PostMapping(path = {"/api/v1/schedule"})
    List<Schedule> createSchedules(@RequestBody List<Schedule> schedules);

    @GetMapping(path = {"/api/v1/schedule"})
    List<Schedule> retrieveSchedules();
}

package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Schedule;
import br.com.method.the.gamer.core.api.model.Task;
import br.com.method.the.gamer.core.api.usecase.*;
import br.com.method.the.gamer.core.internal.configuration.TheGamerCoreTestConfiguration;
import br.com.method.the.gamer.core.internal.util.GamerUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, classes = {TheGamerCoreTestConfiguration.class})
class DefaultValidateTaskTest {

    @Autowired
    ValidateTask validateTask;

    @MockBean
    RetrieveSchedule retrieveSchedule;

    @Mock
    Schedule schedule;

    @BeforeEach
    void init() {
        this.schedule = new Schedule();
        schedule.setId(1000L);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime finish = start.plusMinutes(5l);
        schedule.setTasks(Arrays.asList(this.createTask(schedule, start, finish)));
        Mockito.when(this.retrieveSchedule.execute(Mockito.anyLong())).thenReturn(Optional.of(schedule));
    }

    @Transactional
    @Test
    void validateTaskSlotAvailableAfter() {
        LocalDateTime start = LocalDateTime.now();
        Task newTask = this.createTask(this.schedule, start.plusMinutes(5l), start.plusMinutes(7l));
        Assertions.assertTrue(this.validateTask.execute(newTask).isPresent());
    }

    @Transactional
    @Test
    void validateTaskSlotAvailableBefore() {
        LocalDateTime start = LocalDateTime.now();
        Task newTask = this.createTask(this.schedule, start.minusMinutes(3l), start.minusSeconds(1l));
        Assertions.assertTrue(this.validateTask.execute(newTask).isPresent());
    }

    @Transactional
    @Test
    void validateTaskSlotUnavailable() {
        LocalDateTime start = LocalDateTime.now();
        Task newTask = this.createTask(this.schedule, start.plusMinutes(1l), start.plusMinutes(2l));
        Assertions.assertTrue(this.validateTask.execute(newTask).isEmpty());
    }

    private Task createTask(Schedule schedule, LocalDateTime start, LocalDateTime finish) {
        Task task = GamerUtils.createTask(schedule);
        task.setStart(start);
        task.setFinish(finish);
        return task;
    }
}

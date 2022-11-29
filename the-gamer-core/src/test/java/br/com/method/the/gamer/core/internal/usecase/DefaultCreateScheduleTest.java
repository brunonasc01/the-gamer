package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Gamer;
import br.com.method.the.gamer.core.api.model.Schedule;
import br.com.method.the.gamer.core.api.usecase.CreateGamer;
import br.com.method.the.gamer.core.api.usecase.CreateSchedule;
import br.com.method.the.gamer.core.api.usecase.RetrieveSchedule;
import br.com.method.the.gamer.core.internal.configuration.TheGamerCoreTestConfiguration;
import br.com.method.the.gamer.core.internal.util.GamerUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, classes = {TheGamerCoreTestConfiguration.class})
class DefaultCreateScheduleTest {
    
    @Autowired
    CreateSchedule createSchedule;

    @Autowired
    CreateGamer createGamer;

    @Autowired
    RetrieveSchedule retrieveSchedule;

    @Transactional
    @Test
    void createScheduleSuccess() {
        Gamer gamer = GamerUtils.createGamer();
        Optional<Gamer> createdGamer = this.createGamer.execute(gamer);
        Optional<Schedule> createdSchedule = this.createSchedule.execute(GamerUtils.createSchedule(createdGamer.get()));
        Optional<Schedule> freshSchedule = this.retrieveSchedule.execute(createdSchedule.get().getId());
        Assertions.assertTrue(createdGamer.isPresent());
        Assertions.assertTrue(createdSchedule.isPresent());
        Assertions.assertTrue(freshSchedule.isPresent());
    }
    
}

package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Quest;
import br.com.method.the.gamer.core.api.model.QuestStatus;
import br.com.method.the.gamer.core.api.model.Schedule;
import br.com.method.the.gamer.core.api.usecase.CreateGamer;
import br.com.method.the.gamer.core.api.usecase.ProcessQuest;
import br.com.method.the.gamer.core.api.usecase.RetrieveSchedule;
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
class DefaultProcessQuestTest {

    @Autowired
    ProcessQuest processQuest;

    @Autowired
    CreateGamer createGamer;

    @MockBean
    RetrieveSchedule retrieveSchedule;

    @Mock
    Schedule schedule;

    @BeforeEach
    void init() {
        this.schedule = new Schedule();
        schedule.setId(1000L);
        schedule.setGamer(GamerUtils.createGamer());
        Mockito.when(this.retrieveSchedule.execute(Mockito.anyLong())).thenReturn(Optional.of(schedule));
    }

    @Transactional
    @Test
    void processQuest() {
        this.createGamer.execute(this.schedule.getGamer());
        LocalDateTime start = LocalDateTime.now();
        Quest newQuest = this.createQuest(this.schedule, start.minusHours(1).minusMinutes(30), start);
        Assertions.assertTrue(this.processQuest.execute(newQuest).isEmpty());
    }

    private Quest createQuest(Schedule schedule, LocalDateTime start, LocalDateTime finish) {
        Quest quest = GamerUtils.createQuest(schedule);
        quest.setStart(start);
        quest.setFinish(finish);
        quest.setStatus(QuestStatus.FINISHED);
        return quest;
    }
}

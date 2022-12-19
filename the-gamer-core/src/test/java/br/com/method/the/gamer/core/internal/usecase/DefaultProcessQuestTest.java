package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.*;
import br.com.method.the.gamer.core.api.repository.GamerRepository;
import br.com.method.the.gamer.core.api.usecase.CreateGamer;
import br.com.method.the.gamer.core.api.usecase.ProcessQuest;
import br.com.method.the.gamer.core.internal.configuration.TheGamerCoreTestConfiguration;
import br.com.method.the.gamer.core.internal.util.GamerUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
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

    @Mock
    Schedule schedule;

    @MockBean
    GamerRepository gamerRepository;

    @BeforeEach
    void init() {
        Gamer gamer = GamerUtils.createGamer();
        this.schedule = GamerUtils.createSchedule(gamer);
        schedule.setId(1000L);
        Mockito.when(gamerRepository.save(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        Mockito.when(gamerRepository.findById(Mockito.any())).thenReturn(Optional.of(GamerUtils.createGamer()));
    }

    @Transactional
    @Test
    void processQuest() {
        this.createGamer.execute(this.schedule.getGamer());
        LocalDateTime start = LocalDateTime.now();
        Quest newQuest = this.createQuest(this.schedule, start.minusHours(1).minusMinutes(30), start);
        Assertions.assertTrue(this.processQuest.execute(newQuest).isPresent());
    }

    private Quest createQuest(Schedule schedule, LocalDateTime start, LocalDateTime finish) {
        Quest quest = GamerUtils.createQuest(schedule);
        quest.setStart(start);
        quest.setFinish(finish);
        quest.setStatus(QuestStatus.FINISHED);

        int index = 1;
        for(QuestAttribute questAttribute : quest.getAttributes()) {
            questAttribute.setWeight(index * 10);
            index++;
        }

        return quest;
    }
}

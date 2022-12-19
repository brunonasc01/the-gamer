package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.*;
import br.com.method.the.gamer.core.api.repository.QuestRepository;
import br.com.method.the.gamer.core.api.usecase.ProcessGamer;
import br.com.method.the.gamer.core.api.usecase.ProcessQuest;
import br.com.method.the.gamer.core.api.usecase.RetrieveDifficult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultProcessQuest implements ProcessQuest {

    private final RetrieveDifficult retrieveDifficult;

    private final ProcessGamer processGamer;

    private final QuestRepository questRepository;

    private final static Integer BASE_LEVELUP_QUESTS = 5;

    @Override
    public Optional<Quest> execute(Quest quest) {
        if(quest.getStatus().equals(QuestStatus.FINISHED)) {
            Gamer gamer = quest.getSchedule().getGamer();
            Double questExperience = this.getExperience(gamer, quest);
            this.processGamer.execute(gamer, questExperience);
            this.questRepository.save(quest);
        }
        return Optional.of(quest);
    }

    private Map<QuestAttribute, Difficult> getAttributeDifficulties(Quest quest) {
        return quest.getAttributes().stream().collect(Collectors.toMap(Function.identity(),
                attribute -> this.getDifficult(attribute, quest)));
    }

    private Difficult getDifficult(QuestAttribute questAttribute, Quest quest) {
        Long gamerId = quest.getSchedule().getGamer().getId();
        return this.retrieveDifficult.execute(questAttribute, gamerId);
    }

    private Double getExperience(Gamer gamer, Quest quest){
        Integer gamerLevel = gamer.getLevel();
        Double questTime = Double.valueOf(Duration.between(quest.getStart(), quest.getFinish()).toMinutes()/60);
        Map<QuestAttribute, Difficult> difficulties = this.getAttributeDifficulties(quest);
        Long levelThreshold = Long.valueOf(40 * gamerLevel * gamerLevel + 360 * gamerLevel);
        Long questThreshold = Long.valueOf(BASE_LEVELUP_QUESTS * gamerLevel);
        return difficulties.keySet().stream().map(questAttribute ->
                this.calculateExperience(questAttribute, questTime, levelThreshold, questThreshold,
                        difficulties.get(questAttribute))).collect(Collectors.summingDouble(Double::doubleValue));
    }

    private Double calculateExperience(QuestAttribute questAttribute, Double questTime, Long levelThreshold, Long questThreshold, Difficult difficult) {
        Double rawExperience = (levelThreshold/questThreshold) * (difficult.getFactor().doubleValue()+questTime/2);
        log.info("QuestAttribute difficult {} totalExperience {}", difficult, rawExperience);
        Double experience = rawExperience * (questAttribute.getWeight().doubleValue()/100);
        log.info("QuestAttribute weight {} experience {}",questAttribute.getWeight(), experience);
        return experience;
    }
}

package br.com.method.the.gamer.core.internal.util;

import br.com.method.the.gamer.core.api.model.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class GamerUtils {
    
    private GamerUtils() {
        
    }
        
    public static Gamer createGamer() {
        Gamer gamer = new Gamer();
        gamer.setCreatedBy("Mathew");
        gamer.setCreatedDate(LocalDateTime.now());
        gamer.setName("Mathew");
        gamer.setExperience(1.0);
        gamer.setLevel(1);
        gamer.setAttributes(Arrays.stream(AttributeType.values())
                .map(GamerUtils::createAttribute).collect(Collectors.toSet()));
        gamer.setAchievements(Set.of(createAchievement(AchievementTier.BRONZE)));
        return gamer;
    }
        
    public static Attribute createAttribute(AttributeType attributeType) {
        Attribute attribute = new Attribute();
        attribute.setCreatedBy("Mathew");
        attribute.setCreatedDate(LocalDateTime.now());
        attribute.setType(attributeType);
        attribute.setPoints(new Random().nextInt(10));
        return attribute;
    }
    
    public static Achievement createAchievement(AchievementTier achievementTier) {
        Achievement achievement = new Achievement();
        achievement.setCreatedBy("Mathew");
        achievement.setCreatedDate(LocalDateTime.now());
        achievement.setName("Software Programmer");
        achievement.setTier(achievementTier);
        achievement.setPoints(1000l);
        return achievement;
    }

    public static Schedule createSchedule(Gamer gamer) {
        Schedule schedule = new Schedule();
        schedule.setCreatedBy(gamer.getCreatedBy());
        schedule.setCreatedDate(LocalDateTime.now());
        schedule.setGamer(gamer);
        schedule.setDay(LocalDate.now());
        schedule.setStatus(ScheduleStatus.OPEN);
        return schedule;
    }

    public static Quest createQuest(Schedule schedule) {
        Quest quest = new Quest();
        quest.setCreatedBy(schedule.getCreatedBy());
        quest.setCreatedDate(LocalDateTime.now());
        quest.setName("Quest1");
        quest.setDescription("description of quest");
        quest.setDuration(Duration.ofHours(1L).toMillis());
        quest.setDifficult(Difficult.NORMAL);
        quest.setStart(LocalDateTime.now());
        quest.setStatus(QuestStatus.OPEN);
        quest.setSchedule(schedule);
        quest.setAttributes(Arrays.stream(AttributeType.values())
                .map(GamerUtils::createQuestAttribute).collect(Collectors.toSet()));
        return quest;
    }

    public static QuestAttribute createQuestAttribute(AttributeType attributeType) {
        QuestAttribute questAttribute = new QuestAttribute();
        questAttribute.setCreatedBy("Mathew");
        questAttribute.setCreatedDate(LocalDateTime.now());
        questAttribute.setType(attributeType);
        questAttribute.setWeight(100);
        return questAttribute;
    }
}

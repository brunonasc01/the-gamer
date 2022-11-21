package br.com.method.the.gamer.core.internal.util;

import br.com.method.the.gamer.core.api.model.Achievement;
import br.com.method.the.gamer.core.api.model.AchievementTier;
import br.com.method.the.gamer.core.api.model.Attribute;
import br.com.method.the.gamer.core.api.model.AttributeType;
import br.com.method.the.gamer.core.api.model.Gamer;
import br.com.method.the.gamer.core.api.model.Schedule;
import br.com.method.the.gamer.core.api.model.ScheduleStatus;
import br.com.method.the.gamer.core.api.model.Task;
import br.com.method.the.gamer.core.api.model.TaskAttribute;
import br.com.method.the.gamer.core.api.model.TaskStatus;

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
        gamer.setExperience(1l);
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

    public static Task createTask(Schedule schedule) {
        Task task = new Task();
        task.setCreatedBy(schedule.getCreatedBy());
        task.setCreatedDate(LocalDateTime.now());
        task.setName("Task1");
        task.setDescription("description of task");
        task.setDuration(Duration.ofHours(1L).toMillis());
        task.setDifficult(1);
        task.setStart(LocalDateTime.now());
        task.setStatus(TaskStatus.OPEN);
        task.setSchedule(schedule);
        task.setAttributes(Arrays.stream(AttributeType.values())
                .map(GamerUtils::createTaskAttribute).collect(Collectors.toSet()));
        return task;
    }

    public static TaskAttribute createTaskAttribute(AttributeType attributeType) {
        TaskAttribute taskAttribute = new TaskAttribute();
        taskAttribute.setCreatedBy("Mathew");
        taskAttribute.setCreatedDate(LocalDateTime.now());
        taskAttribute.setType(attributeType);
        taskAttribute.setWeight(100);
        return taskAttribute;
    }
}

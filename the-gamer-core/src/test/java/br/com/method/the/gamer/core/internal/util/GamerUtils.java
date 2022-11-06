package br.com.method.the.gamer.core.internal.util;

import br.com.method.the.gamer.core.api.model.Achievement;
import br.com.method.the.gamer.core.api.model.AchievementTier;
import br.com.method.the.gamer.core.api.model.Attribute;
import br.com.method.the.gamer.core.api.model.AttributeType;
import br.com.method.the.gamer.core.api.model.Gamer;

import java.time.LocalDateTime;
import java.util.Arrays;
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
        gamer.setAchievements(Arrays.asList(createAchievement(AchievementTier.BRONZE)));
        return gamer;
    }
        
    public static Attribute createAttribute(AttributeType attributeType) {
        Attribute attribute = new Attribute();
        attribute.setType(attributeType);
        attribute.setPoints(0);
        return attribute;
    }
    
    public static Achievement createAchievement(AchievementTier achievementTier) {
        Achievement achievement = new Achievement();
        achievement.setName("Software Programmer");
        achievement.setTier(achievementTier);
        achievement.setPoints(1000l);
        return achievement;
    }
    
}

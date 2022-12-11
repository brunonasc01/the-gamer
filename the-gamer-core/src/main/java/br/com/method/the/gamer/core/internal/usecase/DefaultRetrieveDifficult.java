package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.*;
import br.com.method.the.gamer.core.api.usecase.RetrieveDifficult;
import br.com.method.the.gamer.core.api.usecase.RetrieveGamer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultRetrieveDifficult implements RetrieveDifficult {
    
    private final RetrieveGamer retrieveGamer;
    
    private static final Integer MULTIPLY_BY_100 = 100;
    
    @Override
    public Difficult execute(QuestAttribute questAttribute, Long gamerId) {
        Optional<Gamer> gamer = this.retrieveGamer.execute(gamerId);
        Map<AttributeType, Double> gamerAttributeWeights = this.getGamerAttributeWeights(gamer.orElseThrow());
        Double attributeWeight = gamerAttributeWeights.get(questAttribute.getType());
        //TODO definir retornar valor bruto, indice ou Enum
        return this.calculateDifficult(questAttribute, attributeWeight);
    }
    
    private Integer getGamerAttributePointsTotal(Gamer gamer) {
        return gamer.getAttributes().stream().map(Attribute::getPoints).reduce(0, Integer::sum);
    }
    
    private Map<AttributeType, Double> getGamerAttributeWeights(Gamer gamer) {
        Integer attributePointsTotal = this.getGamerAttributePointsTotal(gamer);
        return gamer.getAttributes().stream().collect(Collectors.toMap(Attribute::getType,
                attribute -> this.getWeight(attribute, attributePointsTotal)));
    } 
    
    private Double getWeight(Attribute attribute, Integer attributePointsTotal) {
        return attribute.getPoints() > 0 ? (attribute.getPoints().doubleValue()/attributePointsTotal)*MULTIPLY_BY_100 : 0d;
    }

    private Difficult calculateDifficult(QuestAttribute questAttribute, Double attributeWeight) {
        if(attributeWeight <= 0) {
           return Difficult.VERY_HARD;
        }
        Double difficultFactor = Math.sqrt(questAttribute.getWeight().doubleValue()/attributeWeight);

        log.info("Quest weight {}, Attribute weight {} Difficult {}",
                questAttribute.getWeight(), attributeWeight, difficultFactor);

        if(difficultFactor < 0.75) {
            return Difficult.VERY_EASY;
        }
        else if(difficultFactor >= 0.75 && difficultFactor < 1.0) {
            return Difficult.EASY;
        }
        else if(difficultFactor > 1.00 && difficultFactor < 1.25) {
            return Difficult.HARD;
        }
        else if(difficultFactor >= 1.25) {
            return Difficult.VERY_HARD;
        }
        return Difficult.NORMAL;
    }
}

package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Attribute;
import br.com.method.the.gamer.core.api.model.AttributeType;
import br.com.method.the.gamer.core.api.model.Gamer;
import br.com.method.the.gamer.core.api.model.TaskAttribute;
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
    
    private final Integer MULTIPLY_BY_100 = 100;
    
    @Override
    public Double execute(TaskAttribute taskAttribute, Long gamerId) {
        Optional<Gamer> gamer = this.retrieveGamer.execute(gamerId);
        Map<AttributeType, Double> gamerAttributeWeights = this.getGamerAttributeWeights(gamer.get());
        Double difficult = 125.0;
        Double attributeWeight = gamerAttributeWeights.get(taskAttribute.getType());
        if(attributeWeight > 0) {
            difficult = Math.sqrt(taskAttribute.getWeight().doubleValue()/attributeWeight)*MULTIPLY_BY_100;    
        }
        log.info("Task weight {}, Attribute {} weight {} Difficult {}",
                taskAttribute.getWeight(), taskAttribute.getType(), gamerAttributeWeights.get(taskAttribute.getType()), difficult);
        //TODO definir retornar valor bruto, indice ou Enum
        return difficult;
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
}

package br.com.method.the.gamer.core.api.usecase;

import br.com.method.the.gamer.core.api.model.Difficult;
import br.com.method.the.gamer.core.api.model.QuestAttribute;

public interface RetrieveDifficult {
    
    Difficult execute(QuestAttribute questAttribute, Long gamerId);
}

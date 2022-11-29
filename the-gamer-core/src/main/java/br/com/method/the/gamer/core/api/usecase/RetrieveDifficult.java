package br.com.method.the.gamer.core.api.usecase;

import br.com.method.the.gamer.core.api.model.Difficult;
import br.com.method.the.gamer.core.api.model.TaskAttribute;

public interface RetrieveDifficult {
    
    Difficult execute(TaskAttribute taskAttribute, Long gamerId);
}

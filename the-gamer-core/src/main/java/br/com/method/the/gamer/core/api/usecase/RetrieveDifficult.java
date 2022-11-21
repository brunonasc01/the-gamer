package br.com.method.the.gamer.core.api.usecase;

import br.com.method.the.gamer.core.api.model.Task;
import br.com.method.the.gamer.core.api.model.TaskAttribute;

public interface RetrieveDifficult {
    
    Double execute(TaskAttribute taskAttribute, Long gamerId);
}

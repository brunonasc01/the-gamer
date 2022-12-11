package br.com.method.the.gamer.core.api.usecase;

import br.com.method.the.gamer.core.api.model.Quest;

import java.util.Optional;

public interface CreateQuest {

    Optional<Quest> execute(Quest quest);
}

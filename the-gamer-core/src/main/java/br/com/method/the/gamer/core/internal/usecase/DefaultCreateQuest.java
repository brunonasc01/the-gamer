package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Quest;
import br.com.method.the.gamer.core.api.repository.QuestRepository;
import br.com.method.the.gamer.core.api.usecase.CreateQuest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultCreateQuest implements CreateQuest {

    private final QuestRepository questRepository;

    @Override
    public Optional<Quest> execute(Quest quest) {
        return Optional.of(questRepository.save(quest));
    }
}

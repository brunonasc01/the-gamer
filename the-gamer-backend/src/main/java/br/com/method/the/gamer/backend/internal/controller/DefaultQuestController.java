package br.com.method.the.gamer.backend.internal.controller;

import br.com.method.the.gamer.backend.api.controller.QuestController;
import br.com.method.the.gamer.core.api.model.Quest;
import br.com.method.the.gamer.core.api.usecase.CreateQuest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DefaultQuestController implements QuestController {

    private final CreateQuest createQuest;

    @Override
    public List<Quest> createQuest(List<Quest> quests) {
        return this.createQuest.execute(quests.get(0)).stream().toList();
    }

    @Override
    public List<Quest> retrieveQuests() {
        return null;
    }
}

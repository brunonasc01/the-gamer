package br.com.method.the.gamer.backend.api.controller;

import br.com.method.the.gamer.core.api.model.Quest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface QuestController {

    @PostMapping(path = {"/api/v1/quest"})
    List<Quest> createQuest(@RequestBody List<Quest> quests);

    @GetMapping(path = {"/api/v1/quests"})
    List<Quest> retrieveQuests();
}

package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Quest;
import br.com.method.the.gamer.core.api.model.Schedule;
import br.com.method.the.gamer.core.api.usecase.RetrieveSchedule;
import br.com.method.the.gamer.core.api.usecase.ValidateQuest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultValidadeQuest implements ValidateQuest {

    private final RetrieveSchedule retrieveSchedule;

    @Override
    public Optional<Quest> execute(Quest quest) {
        Optional<Schedule> schedule = this.retrieveSchedule.execute(quest.getSchedule().getId());
        if(schedule.isPresent() && schedule.get().getQuests().stream().allMatch(oldQuest -> this.isSlotAvailable(quest, oldQuest))) {
            return Optional.of(quest);
        }
        return Optional.empty();
    }

    private boolean isSlotAvailable(Quest newQuest, Quest closedQuest) {
        return newQuest.getStart().isAfter(closedQuest.getFinish()) || newQuest.getStart().isEqual(closedQuest.getFinish())
                || newQuest.getStart().isBefore(closedQuest.getStart()) &&
                (newQuest.getFinish().isBefore(closedQuest.getStart()) || newQuest.getFinish().isEqual(closedQuest.getStart()));
    }
}

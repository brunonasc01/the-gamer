package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Gamer;
import br.com.method.the.gamer.core.api.repository.GamerRepository;
import br.com.method.the.gamer.core.api.usecase.ProcessGamer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultProcessGamer implements ProcessGamer {

    private final GamerRepository gamerRepository;

    @Override
    public Optional<Gamer> execute(Gamer gamer, Double experience) {
        this.addExperience(gamer, experience);
        this.verifyLevelUp(gamer);
        return Optional.of(gamerRepository.save(gamer));
    }

    private void addExperience(Gamer gamer, Double experience) {
        gamer.setExperience(gamer.getExperience() + experience);
    }

    private void verifyLevelUp(Gamer gamer) {
        Integer gamerLevel = gamer.getLevel();
        Double levelThreshold = IntStream.range(1, gamerLevel).
                mapToDouble(level -> Double.valueOf(40 * level * level + 360 * level)).
                boxed().collect(Collectors.summingDouble(Double::valueOf));

        if(gamer.getExperience() < levelThreshold) {
            gamer.setLevel(++gamerLevel);
        }
    }
}

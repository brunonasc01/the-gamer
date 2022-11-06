package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Gamer;
import br.com.method.the.gamer.core.api.repository.GamerRepository;
import br.com.method.the.gamer.core.api.usecase.CreateGamer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultCreateGamer implements CreateGamer {
    
    private final GamerRepository gamerRepository;
    
    @Override
    public Optional<Gamer> execute(Gamer gamer) {
        return Optional.of(gamerRepository.save(gamer));
    }
}

package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Gamer;
import br.com.method.the.gamer.core.api.repository.GamerRepository;
import br.com.method.the.gamer.core.api.usecase.RetrieveGamer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultRetrieveGamer implements RetrieveGamer {

    private final GamerRepository gamerRepository;
    
    @Override
    public Optional<Gamer> execute(Gamer gamer) {
        return this.gamerRepository.findById(gamer.getId());
    }

    @Override
    public List<Gamer> execute() {
        return this.gamerRepository.findAll();
    }
}

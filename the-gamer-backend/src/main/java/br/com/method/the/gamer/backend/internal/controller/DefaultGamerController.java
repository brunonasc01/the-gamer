package br.com.method.the.gamer.backend.internal.controller;

import br.com.method.the.gamer.backend.api.controller.GamerController;
import br.com.method.the.gamer.core.api.model.Gamer;
import br.com.method.the.gamer.core.api.usecase.CreateGamer;
import br.com.method.the.gamer.core.api.usecase.RetrieveGamer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DefaultGamerController implements GamerController {

    private final CreateGamer createGamer;

    private final RetrieveGamer retrieveGamer;

    @Override
    public List<Gamer> createGamers(List<Gamer> gamer) {
        return this.createGamer.execute(gamer.get(0)).stream().toList();
    }

    @Override
    public List<Gamer> retrieveGamers() {
        return this.retrieveGamer.execute().stream().toList();
    }
}

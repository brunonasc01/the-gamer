package br.com.method.the.gamer.core.api.usecase;

import br.com.method.the.gamer.core.api.model.Gamer;

import java.util.List;
import java.util.Optional;

public interface RetrieveGamer {

    Optional<Gamer> execute(Long gamerId);

    List<Gamer> execute();
}

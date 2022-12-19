package br.com.method.the.gamer.core.api.usecase;

import br.com.method.the.gamer.core.api.model.Gamer;

import java.util.Optional;

public interface ProcessGamer {

    Optional<Gamer> execute(Gamer gamer, Double experience);
}

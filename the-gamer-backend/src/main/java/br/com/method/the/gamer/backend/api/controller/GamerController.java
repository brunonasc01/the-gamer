package br.com.method.the.gamer.backend.api.controller;

import br.com.method.the.gamer.core.api.model.Gamer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface GamerController {

    @PostMapping(path = {"/api/v1/gamer"})
    List<Gamer> createGamers(@RequestBody List<Gamer> gamer);

    @GetMapping(path = {"/api/v1/gamer"})
    List<Gamer> retrieveGamers();
}

package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Gamer;
import br.com.method.the.gamer.core.api.repository.GamerRepository;
import br.com.method.the.gamer.core.api.usecase.CreateGamer;
import br.com.method.the.gamer.core.internal.configuration.TheGamerCoreTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, classes = {TheGamerCoreTestConfiguration.class})
public class DefaultCreateGamerTest {
    
    @Autowired
    CreateGamer createGamer;
    
    @Autowired
    GamerRepository gamerRepository;
    
    @Test
    void createGamerSuccess() {
        Gamer gamer = new Gamer();
        gamer.setCreatedBy("Mathew");
        gamer.setCreatedDate(LocalDateTime.now());
        gamer.setName("Mathew");
        gamer.setExperience(1l);
        gamer.setLevel(1);
        Assertions.assertTrue(this.createGamer.execute(gamer).isPresent());
        Assertions.assertTrue(this.gamerRepository.findByName("Mathew").isPresent());
    }
}

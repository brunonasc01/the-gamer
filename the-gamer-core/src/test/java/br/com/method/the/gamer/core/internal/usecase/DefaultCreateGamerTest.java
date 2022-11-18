package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Gamer;
import br.com.method.the.gamer.core.api.repository.GamerRepository;
import br.com.method.the.gamer.core.api.usecase.CreateGamer;
import br.com.method.the.gamer.core.api.usecase.RetrieveGamer;
import br.com.method.the.gamer.core.internal.configuration.TheGamerCoreTestConfiguration;
import br.com.method.the.gamer.core.internal.util.GamerUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, classes = {TheGamerCoreTestConfiguration.class})
class DefaultCreateGamerTest {
    
    @Autowired
    CreateGamer createGamer;
    
    @Autowired
    RetrieveGamer retrieveGamer;

    @Transactional
    @Test
    void createGamerSuccess() {
        Gamer gamer = GamerUtils.createGamer();
        Assertions.assertTrue(this.createGamer.execute(gamer).isPresent());
        Optional<Gamer> freshGamer = this.retrieveGamer.execute(gamer);
        Assertions.assertTrue(freshGamer.isPresent());
        Assertions.assertTrue(freshGamer.get().getAchievements().size() > 0);
        Assertions.assertTrue(freshGamer.get().getAttributes().size() > 0);
    }
    
}

package br.com.method.the.gamer.core.internal.usecase;

import br.com.method.the.gamer.core.api.model.Gamer;
import br.com.method.the.gamer.core.api.model.Task;
import br.com.method.the.gamer.core.api.usecase.CreateGamer;
import br.com.method.the.gamer.core.api.usecase.RetrieveDifficult;
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

import javax.transaction.Transactional;

@ActiveProfiles(profiles = "test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class, classes = {TheGamerCoreTestConfiguration.class})
public class DefaultRetrieveDifficultTest {
    
    @Autowired
    RetrieveDifficult retrieveDifficult;

    @Autowired
    CreateGamer createGamer;
    
    @Transactional
    @Test
    void executeSuccess(){
        Gamer gamer = GamerUtils.createGamer();
        this.createGamer.execute(gamer);
        Task task = this.fillData(gamer);
        task.getAttributes().stream().forEach(taskAttribute -> {
            Assertions.assertTrue(this.retrieveDifficult.execute(taskAttribute, gamer.getId()) > 0);
        });
    }

    private Task fillData(Gamer gamer) {
        return GamerUtils.createTask(GamerUtils.createSchedule(gamer));
    }
}

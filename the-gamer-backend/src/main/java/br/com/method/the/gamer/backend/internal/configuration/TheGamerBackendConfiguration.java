package br.com.method.the.gamer.backend.internal.configuration;

import br.com.method.the.gamer.core.internal.configuration.TheGamerCoreConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ TheGamerCoreConfiguration.class })
@ComponentScan("br.com.method.the.gamer.backend")
public class TheGamerBackendConfiguration {
}

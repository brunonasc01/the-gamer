package br.com.method.the.gamer.core.internal.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({TheGamerCoreConfiguration.class})
public class TheGamerCoreTestConfiguration {
}

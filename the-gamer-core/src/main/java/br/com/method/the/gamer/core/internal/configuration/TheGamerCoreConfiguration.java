package br.com.method.the.gamer.core.internal.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("br.com.method.the.gamer.core")
@EnableJpaRepositories(basePackages = "br.com.method.the.gamer.core.api.repository")
@EnableJpaAuditing
@EntityScan({"br.com.method.the.gamer.core.api.model"})
public class TheGamerCoreConfiguration {
}

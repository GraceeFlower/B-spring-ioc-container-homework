package com.thoughtworks.capability.demospringioccontainer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Bar bar() {
        return new Bar();
    }

    @Bean
    public Foo foo() {
        return new Foo();
    }
}

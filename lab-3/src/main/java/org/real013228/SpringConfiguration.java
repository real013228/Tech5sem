package org.real013228;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.real013228")
public class SpringConfiguration {
    @Bean
    public ClassicalMusic musicBean() {
        return new ClassicalMusic();
    }
    @Bean MusicPlayer playerBean() {
        return new MusicPlayer(musicBean());
    }
}

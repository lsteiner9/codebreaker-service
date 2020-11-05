package edu.cnm.deepdive.codebreaker.configuration;

import java.security.SecureRandom;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public Random random() {
    return new SecureRandom();
  }

}

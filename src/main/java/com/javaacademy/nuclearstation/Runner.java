package com.javaacademy.nuclearstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Класс Runner.
 */
@SpringBootApplication
public class Runner {

  /**
   * Метод запуска приложения.
   *
   * @param args - вводные данные при запуске из консоли.
   */
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Runner.class, args);

    NuclearStation nuclearStation = context.getBean(NuclearStation.class);
    nuclearStation.start(3);
    context.close();
  }
}

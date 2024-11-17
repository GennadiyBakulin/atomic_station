package com.javaacademy.nuclearstation.reactordepartment;

import com.javaacademy.nuclearstation.reactordepartment.exception.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exception.ReactorWorkException;
import com.javaacademy.nuclearstation.securitydepartment.SecurityDepartment;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReactorDepartmentIT {

  @Autowired
  private ReactorDepartment reactorDepartment;
  @Autowired
  private SecurityDepartment securityDepartment;

  @Test
  @SneakyThrows
  @DisplayName("Попытка запуска работающего реактора добавило инцидент")
  void successAddAccidentWhenRunWorkingReactor() {
    int countStart = securityDepartment.getCountAccidents();
    reactorDepartment.run();
    try {
      reactorDepartment.run();
    } catch (ReactorWorkException e) {
    }
    int countEnd = securityDepartment.getCountAccidents();
    Assertions.assertEquals(1, countEnd - countStart);
  }

  @Test
  @SneakyThrows
  @DisplayName("Попытка запуска реактора сотый раз добавило инцидент")
  void successAddAccidentWhenHundredthTimeRunReactor() {
    int countStart = securityDepartment.getCountAccidents();
    try {
      for (int i = 0; i < 100; i++) {
        reactorDepartment.run();
        reactorDepartment.stop();
      }
    } catch (NuclearFuelIsEmptyException e) {
    }
    int countEnd = securityDepartment.getCountAccidents();
    Assertions.assertEquals(1, countEnd - countStart);
  }

  @Test
  @SneakyThrows
  @DisplayName("Попытка остановки не работающего реактора добавило инцидент")
  void successAddAccidentWhenStopStoppedReactor() {
    int countStart = securityDepartment.getCountAccidents();
    try {
      reactorDepartment.stop();
    } catch (ReactorWorkException e) {
    }
    int countEnd = securityDepartment.getCountAccidents();
    Assertions.assertEquals(1, countEnd - countStart);
  }
}

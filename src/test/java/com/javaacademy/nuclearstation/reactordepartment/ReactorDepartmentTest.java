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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class ReactorDepartmentTest {

  private static final long DAILY_POWER_GENERATION = 10_000_000L;

  @Autowired
  private ReactorDepartment reactorDepartment;
  @MockBean
  private SecurityDepartment securityDepartment;

  @Test
  @SneakyThrows
  @DisplayName("Успешный запуск реактора вернул суточное количество выработанной энергии")
  void whenSuccessRunReactorThenGetDailyPowerGeneration() {
    long result = reactorDepartment.run();
    Assertions.assertEquals(DAILY_POWER_GENERATION, result);
  }

  @Test
  @SneakyThrows
  @DisplayName("Запуск работающего реактора выбросило исключение")
  void whenRunNotStoppedReactorThenTrowsReactorWorkException() {
    reactorDepartment.run();
    Assertions.assertThrows(ReactorWorkException.class, () -> reactorDepartment.run(),
        "Реактор уже работает");
  }

  @Test
  @SneakyThrows
  @DisplayName("Запуск реактора в работу на сотый раз выбросило исключение")
  void whenRunAndStopReactorHundredthTimeThenTrowsNuclearFuelIsEmptyException() {
    for (int i = 0; i < 99; i++) {
      reactorDepartment.run();
      reactorDepartment.stop();
    }
    Assertions.assertThrows(NuclearFuelIsEmptyException.class, () -> reactorDepartment.run());
  }

  @Test
  @SneakyThrows
  @DisplayName("Остановка запущенного реактора не выбросило исключения")
  void successStopReactor() {
    reactorDepartment.run();
    Assertions.assertDoesNotThrow(() -> reactorDepartment.stop());
  }

  @Test
  @SneakyThrows
  @DisplayName("Остановка неработающего реактора выбросило исключение")
  void whenStoppingStoppedReactorThenTrowsReactorWorkException() {
    Assertions.assertThrows(ReactorWorkException.class, () -> reactorDepartment.stop(),
        "Реактор уже выключен");
  }
}

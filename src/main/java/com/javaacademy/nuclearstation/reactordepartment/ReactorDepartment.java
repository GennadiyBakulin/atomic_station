package com.javaacademy.nuclearstation.reactordepartment;

import com.javaacademy.nuclearstation.reactordepartment.exception.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exception.ReactorWorkException;
import com.javaacademy.nuclearstation.securitydepartment.SecurityDepartment;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Класс - Реакторный цех.
 */
@RequiredArgsConstructor
@Component
public class ReactorDepartment {

  private boolean isWorking;
  private int counterLaunch;
  private final SecurityDepartment securityDepartment;

  /**
   * Метод - запустить реактор.
   *
   * @return - возвращает суточное количество выработанной энергии.
   * @throws ReactorWorkException        - выбрасывается при попытке запуска уже работающего
   *                                     реактора.
   * @throws NuclearFuelIsEmptyException - выбрасывается каждый 100 раз запуска реактора.
   */
  public BigDecimal run() throws ReactorWorkException, NuclearFuelIsEmptyException {
    checkIsNotWorkingReactor();
    counterLaunch++;
    checkEveryHundredRuns();
    isWorking = true;
    return BigDecimal.valueOf(10_000_000);
  }

  /**
   * Метод - остановить реактор.
   *
   * @throws ReactorWorkException - выбрасывается если проверка показала, что реактор уже выключен.
   */
  public void stop() throws ReactorWorkException {
    checkIsWorkingReactor();
    isWorking = false;
  }

  /**
   * Метод - проверка на то, что реактор отключен.
   *
   * @throws ReactorWorkException - выбрасывается если проверка показала, что реактор уже работал.
   */
  private void checkIsNotWorkingReactor() throws ReactorWorkException {
    if (isWorking) {
      securityDepartment.addAccident();
      throw new ReactorWorkException("Реактор уже работает");
    }
  }

  /**
   * Метод - проверки количества запусков реактора.
   *
   * @throws NuclearFuelIsEmptyException - выбрасывается каждый 100 раз запуска реактора.
   */
  private void checkEveryHundredRuns() throws NuclearFuelIsEmptyException {
    if (counterLaunch == 100) {
      counterLaunch = 0;
      securityDepartment.addAccident();
      throw new NuclearFuelIsEmptyException();
    }
  }

  /**
   * Метод - проверка на то, что реактор работает.
   *
   * @throws ReactorWorkException - выбрасывается если проверка показала, что реактор уже выключен.
   */
  private void checkIsWorkingReactor() throws ReactorWorkException {
    if (!isWorking) {
      securityDepartment.addAccident();
      throw new ReactorWorkException("Реактор уже выключен");
    }
  }
}

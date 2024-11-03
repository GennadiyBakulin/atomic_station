package com.javaacademy.nuclearstation.reactordepartment;

import com.javaacademy.nuclearstation.reactordepartment.exception.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exception.ReactorWorkException;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

/**
 * Класс - Реакторный цех.
 */
@Component
public class ReactorDepartment {

  private boolean isWorking;
  private int counterLaunch;

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
   * Метод - проверка на то, что реактор отключен.
   *
   * @throws ReactorWorkException - выбрасывается если проверка показала, что реактор уже работал.
   */
  private void checkIsNotWorkingReactor() throws ReactorWorkException {
    if (isWorking) {
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
      throw new NuclearFuelIsEmptyException();
    }
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
   * Метод - проверка на то, что реактор работает.
   *
   * @throws ReactorWorkException - выбрасывается если проверка показала, что реактор уже выключен.
   */
  private void checkIsWorkingReactor() throws ReactorWorkException {
    if (!isWorking) {
      throw new ReactorWorkException("Реактор уже выключен");
    }
  }
}

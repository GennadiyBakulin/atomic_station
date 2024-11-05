package com.javaacademy.nuclearstation.reactordepartment;

import com.javaacademy.nuclearstation.reactordepartment.exception.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exception.ReactorWorkException;
import com.javaacademy.nuclearstation.securitydepartment.SecurityDepartment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Класс - Реакторный цех.
 */
@RequiredArgsConstructor
@Component
public class ReactorDepartment {

  private static final long DAILY_POWER_GENERATION = 10_000_000L;

  private final SecurityDepartment securityDepartment;
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
  public long run() throws ReactorWorkException, NuclearFuelIsEmptyException {
    checkIsNotWorkingReactor();
    counterLaunch++;
    checkEveryHundredRuns();
    isWorking = true;
    return DAILY_POWER_GENERATION;
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
      securityDepartment.addAccident();
      counterLaunch = 0;
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

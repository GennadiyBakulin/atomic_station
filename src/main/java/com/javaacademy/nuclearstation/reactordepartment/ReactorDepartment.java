package com.javaacademy.nuclearstation.reactordepartment;

import com.javaacademy.nuclearstation.reactordepartment.exception.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exception.ReactorWorkException;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class ReactorDepartment {

  private boolean isWorking;
  private int counterLaunch = 0;

  public BigDecimal run() throws ReactorWorkException, NuclearFuelIsEmptyException {
    checkIsNotWorkingReactor();
    counterLaunch++;
    checkEveryHundredRuns();
    isWorking = true;
    return BigDecimal.valueOf(10_000_000);
  }

  private void checkIsNotWorkingReactor() throws ReactorWorkException {
    if (isWorking) {
      throw new ReactorWorkException("Реактор уже работает");
    }
  }

  private void checkEveryHundredRuns() throws NuclearFuelIsEmptyException {
    if (counterLaunch == 100) {
      counterLaunch = 0;
      throw new NuclearFuelIsEmptyException();
    }
  }

  public void stop() throws ReactorWorkException {
    checkIsWorkingReactor();
    isWorking = false;
  }

  private void checkIsWorkingReactor() throws ReactorWorkException {
    if (!isWorking) {
      throw new ReactorWorkException("Реактор уже выключен");
    }
  }

}

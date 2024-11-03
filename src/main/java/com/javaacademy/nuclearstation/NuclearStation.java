package com.javaacademy.nuclearstation;

import static java.math.BigDecimal.ZERO;

import com.javaacademy.nuclearstation.reactordepartment.ReactorDepartment;
import com.javaacademy.nuclearstation.reactordepartment.exception.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exception.ReactorWorkException;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NuclearStation {

  private final ReactorDepartment reactorDepartment;

  private BigDecimal totalAmountOfEnergyGenerated = ZERO;
  private BigDecimal amountOfEnergyGeneratedForYear = ZERO;

  public NuclearStation(ReactorDepartment reactorDepartment) {
    this.reactorDepartment = reactorDepartment;
  }

  public void startYear() throws ReactorWorkException {
    log.info("Атомная станция начала работу");
    for (int i = 0; i < 365; i++) {
      try {
        BigDecimal amountOfEnergyGeneratedPerDay = reactorDepartment.run();
        amountOfEnergyGeneratedForYear = amountOfEnergyGeneratedForYear
            .add(amountOfEnergyGeneratedPerDay);
        reactorDepartment.stop();
      } catch (NuclearFuelIsEmptyException e) {
        log.info("Внимание! Происходят работы на атомной станции! Электричества нет!");
      }
    }

    log.info("Атомная станция закончила работу. За год Выработано {} киловатт/часов",
        amountOfEnergyGeneratedForYear);
    totalAmountOfEnergyGenerated = totalAmountOfEnergyGenerated.add(amountOfEnergyGeneratedForYear);
    amountOfEnergyGeneratedForYear = ZERO;
  }
}

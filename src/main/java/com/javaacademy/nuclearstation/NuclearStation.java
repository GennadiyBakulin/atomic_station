package com.javaacademy.nuclearstation;

import static java.math.BigDecimal.ZERO;

import com.javaacademy.nuclearstation.reactordepartment.ReactorDepartment;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class NuclearStation {

  private final ReactorDepartment reactorDepartment;

  private BigDecimal totalAmountOfEnergyGenerated = ZERO;

  public NuclearStation(ReactorDepartment reactorDepartment) {
    this.reactorDepartment = reactorDepartment;
  }
}

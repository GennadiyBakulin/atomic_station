package com.javaacademy.nuclearstation.economic.franceeconomicdepartment;

import com.javaacademy.nuclearstation.data.FranceCountryData;
import com.javaacademy.nuclearstation.economic.EconomicDepartment;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile(value = "france")
@Component
@RequiredArgsConstructor
public class FranceEconomicDepartment extends EconomicDepartment {

  private static final long BILLION_KILOWATT_HOURS = 1_000_000_000L;
  private final FranceCountryData franceData;

  @Override
  public BigDecimal computeYearIncomes(long countElectricity) {
    BigDecimal income = BigDecimal.ZERO;
    int step = 0;

    while (countElectricity > 0) {
      if (countElectricity <= BILLION_KILOWATT_HOURS) {
        return income.add(computeIncomes(countElectricity, step));
      }

      income = income.add(computeIncomes(countElectricity, step));
      countElectricity -= BILLION_KILOWATT_HOURS;
      step++;
    }
    return income;
  }

  private BigDecimal computeIncomes(long countElectricity, int step) {
    return BigDecimal.valueOf(countElectricity)
        .multiply(franceData.getPrice())
        .multiply(franceData.getPercentage().pow(step));
  }
}

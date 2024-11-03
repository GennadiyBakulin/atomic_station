package com.javaacademy.nuclearstation.economic;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile(value = "france")
@Component
public class FranceEconomicDepartment extends EconomicDepartment {

  private static final long BILLION_KILOWATT_HOURS = 1_000_000_000L;

  @Value("${france.price}")
  BigDecimal price;
  @Value("${france.percentage}")
  BigDecimal percentage;

  @Override
  BigDecimal computeYearIncomes(long countElectricity) {
    BigDecimal income = BigDecimal.ZERO;

    if (countElectricity <= BILLION_KILOWATT_HOURS) {
      return income.add(BigDecimal.valueOf(countElectricity)
          .multiply(price));
    }

    return computeYearIncomes(countElectricity, 0);

//    while (countElectricity > 0) {
//      if (countElectricity <= BILLION_KILOWATT_HOURS) {
//        return income.add(BigDecimal.valueOf(countElectricity)
//            .multiply(price)
//            .multiply(percentage.pow(step)));
//      }
//
//      income = income.add(BigDecimal.valueOf(BILLION_KILOWATT_HOURS)
//          .multiply(price)
//          .multiply(percentage.pow(step))
//      );
//      countElectricity -= BILLION_KILOWATT_HOURS;
//      step++;
//    }
//    return income;
  }

  BigDecimal computeYearIncomes(long countElectricity, int step) {

    if (countElectricity <= BILLION_KILOWATT_HOURS) {
      return BigDecimal.valueOf(countElectricity)
          .multiply(price)
          .multiply(percentage.pow(step)
          );
    }

    return BigDecimal.valueOf(BILLION_KILOWATT_HOURS)
        .multiply(price)
        .multiply(percentage.pow(step))
        .add(computeYearIncomes(countElectricity - BILLION_KILOWATT_HOURS, ++step));
  }
}

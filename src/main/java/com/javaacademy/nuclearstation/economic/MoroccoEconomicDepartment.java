package com.javaacademy.nuclearstation.economic;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile(value = "morocco")
@Component
public class MoroccoEconomicDepartment extends EconomicDepartment {

  private static final long FIVE_BILLION_KILOWATT_HOURS = 5_000_000_000L;
  @Value("${morocco.price-base}")
  BigDecimal priceBase;
  @Value("${morocco.price-increased}")
  BigDecimal priceIncreased;

  @Override
  BigDecimal computeYearIncomes(long countElectricity) {

    if (countElectricity <= FIVE_BILLION_KILOWATT_HOURS) {
      return BigDecimal.valueOf(countElectricity).multiply(priceBase);
    }

    return BigDecimal.valueOf(FIVE_BILLION_KILOWATT_HOURS)
        .multiply(priceBase)
        .add(
            BigDecimal.valueOf(countElectricity - FIVE_BILLION_KILOWATT_HOURS)
                .multiply(priceIncreased));
  }
}

package com.javaacademy.nuclearstation.economic.moroccoeconomicdepartment;

import com.javaacademy.nuclearstation.data.MoroccoData;
import com.javaacademy.nuclearstation.economic.EconomicDepartment;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile(value = "morocco")
@Component
@RequiredArgsConstructor
public class MoroccoEconomicDepartment extends EconomicDepartment {

  private static final long FIVE_BILLION_KILOWATT_HOURS = 5_000_000_000L;
  private final MoroccoData moroccoData;

  @Override
  public BigDecimal computeYearIncomes(long countElectricity) {

    if (countElectricity <= FIVE_BILLION_KILOWATT_HOURS) {
      return BigDecimal.valueOf(countElectricity).multiply(moroccoData.getPriceBase());
    }

    return BigDecimal.valueOf(FIVE_BILLION_KILOWATT_HOURS)
        .multiply(moroccoData.getPriceBase())
        .add(
            BigDecimal.valueOf(countElectricity - FIVE_BILLION_KILOWATT_HOURS)
                .multiply(moroccoData.getPriceIncreased()));
  }
}

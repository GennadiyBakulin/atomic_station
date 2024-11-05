package com.javaacademy.nuclearstation.economic.morocco;

import com.javaacademy.nuclearstation.countrydata.MoroccoCountryData;
import com.javaacademy.nuclearstation.economic.EconomicDepartment;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Класс - MoroccoEconomicDepartment.
 */
@Profile(value = "morocco")
@Component
@RequiredArgsConstructor
public class MoroccoEconomicDepartment extends EconomicDepartment {

  private static final long FIVE_BILLION_KILOWATT_HOURS = 5_000_000_000L;
  private final MoroccoCountryData moroccoData;

  /**
   * Абстрактный метод расчета дохода от произведенной энергии.
   *
   * @param countElectricity - количество произведенной электроэнергии.
   * @return - возвращает доход полученный от произведенной электроэнергии.
   */
  @Override
  public BigDecimal computeYearIncomes(long countElectricity) {

    if (countElectricity <= FIVE_BILLION_KILOWATT_HOURS) {
      return computeIncomes(countElectricity, moroccoData.getPrice());
    }

    return computeIncomes(FIVE_BILLION_KILOWATT_HOURS, moroccoData.getPrice())
        .add(
            computeIncomes(countElectricity - FIVE_BILLION_KILOWATT_HOURS,
                moroccoData.getPriceIncreased()));
  }

  /**
   * Метод выполняет произведение количества переданной электроэнергии на её стоимость.
   *
   * @param countElectricity - количество произведенной электроэнергии.
   * @param price            - стоимость электроэнергии.
   * @return - произведение количества переданной электроэнергии на стоимость.
   */
  private BigDecimal computeIncomes(long countElectricity, BigDecimal price) {
    return BigDecimal.valueOf(countElectricity).multiply(price);
  }
}

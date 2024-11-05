package com.javaacademy.nuclearstation.economic.france;

import com.javaacademy.nuclearstation.countrydata.FranceCountryData;
import com.javaacademy.nuclearstation.economic.EconomicDepartment;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Класс FranceEconomicDepartment.
 */
@Profile(value = "france")
@Component
@RequiredArgsConstructor
public class FranceEconomicDepartment extends EconomicDepartment {

  private static final long BILLION_KILOWATT_HOURS = 1_000_000_000L;
  private final FranceCountryData franceData;

  /**
   * Абстрактный метод расчета дохода от произведенной энергии.
   *
   * @param countElectricity - количество произведенной электроэнергии.
   * @return - возвращает доход полученный от произведенной электроэнергии.
   */
  @Override
  public BigDecimal computeYearIncomes(long countElectricity) {
    BigDecimal income = BigDecimal.ZERO;
    int indexNextBillionKilowatt = 0;

    while (countElectricity > 0) {
      if (countElectricity <= BILLION_KILOWATT_HOURS) {
        return income.add(computeIncomes(countElectricity, indexNextBillionKilowatt));
      }

      income = income.add(computeIncomes(BILLION_KILOWATT_HOURS, indexNextBillionKilowatt));
      countElectricity -= BILLION_KILOWATT_HOURS;
      indexNextBillionKilowatt++;
    }
    return income;
  }

  /**
   * Метод выполняет произведение количества переданной электроэнергии на её стоимость.
   *
   * @param countElectricity         - количество произведенной электроэнергии.
   * @param indexNextBillionKilowatt - индекс очередного миллиарда киловатт/часа (начинается с 0).
   * @return - произведение количества переданной электроэнергии на стоимость.
   */
  private BigDecimal computeIncomes(long countElectricity, int indexNextBillionKilowatt) {
    return BigDecimal.valueOf(countElectricity)
        .multiply(franceData.getPrice())
        .multiply(franceData.getPercentage().pow(indexNextBillionKilowatt));
  }
}

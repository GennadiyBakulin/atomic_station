package com.javaacademy.nuclearstation.economic.morocco;

import com.javaacademy.nuclearstation.economic.EconomicDepartment;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

  @Value("${price}")
  private BigDecimal price;
  @Value("${price-increased}")
  private BigDecimal priceIncreased;

  /**
   * Абстрактный метод расчета дохода от произведенной энергии.
   *
   * @param countElectricity - количество произведенной электроэнергии.
   * @return - возвращает доход полученный от произведенной электроэнергии.
   */
  @Override
  public BigDecimal computeYearIncomes(long countElectricity) {

    if (countElectricity <= FIVE_BILLION_KILOWATT_HOURS) {
      return computeIncomes(countElectricity, price);
    }

    return computeIncomes(FIVE_BILLION_KILOWATT_HOURS, price)
        .add(
            computeIncomes(countElectricity - FIVE_BILLION_KILOWATT_HOURS,
                priceIncreased));
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

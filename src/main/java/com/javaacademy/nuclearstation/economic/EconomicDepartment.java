package com.javaacademy.nuclearstation.economic;

import java.math.BigDecimal;

/**
 * Абстрактный класс - EconomicDepartment.
 */
public abstract class EconomicDepartment {

  /**
   * Абстрактный метод расчета дохода от произведенной энергии.
   *
   * @param countElectricity - количество произведенной электроэнергии.
   * @return - возвращает доход полученный от произведенной электроэнергии.
   */
  public abstract BigDecimal computeYearIncomes(long countElectricity);
}

package com.javaacademy.nuclearstation.securitydepartment;

import com.javaacademy.nuclearstation.NuclearStation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Класс - Отдел безопасности.
 */
@RequiredArgsConstructor
@Component
public class SecurityDepartment {

  private final NuclearStation nuclearStation;
  private int accidentCountPeriod;

  /**
   * Метод - увеличивает количество инцидентов за период на 1.
   */
  public void addAccident() {
    accidentCountPeriod++;
  }

  /**
   * Метод получения инцидентов за период.
   *
   * @return - количество инцидентов за все время.
   */
  public int getCountAccidents() {
    return this.accidentCountPeriod;
  }

  /**
   * Метод сброс счетчика инцидентов.
   * <p>
   * Прибавляет инциденты из отдела безопасности в количество инцидентов за все время
   * внутри атомной станции. Ставит 0 в поле accidentCountPeriod.
   */
  public void reset() {
    nuclearStation.incrementAccident(accidentCountPeriod);
    accidentCountPeriod = 0;
  }
}

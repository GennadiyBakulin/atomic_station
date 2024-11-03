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
}

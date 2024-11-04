package com.javaacademy.nuclearstation;

import com.javaacademy.nuclearstation.economic.EconomicDepartment;
import com.javaacademy.nuclearstation.reactordepartment.ReactorDepartment;
import com.javaacademy.nuclearstation.reactordepartment.exception.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.reactordepartment.exception.ReactorWorkException;
import com.javaacademy.nuclearstation.securitydepartment.SecurityDepartment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Класс - Атомная станция.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class NuclearStation {

  private final SecurityDepartment securityDepartment;
  private final ReactorDepartment reactorDepartment;
  private final EconomicDepartment economicDepartment;

  private final Environment env;

  private long totalAmountOfEnergyGenerated;
  private int accidentCountAllTime;

  /**
   * Метод - запуска годового цикла работы станции.
   */
  private void startYear() {
    long amountOfEnergyGeneratedForYear = 0L;
    log.info("Атомная станция начала работу");
    for (int i = 0; i < 365; i++) {
      try {
        long amountOfEnergyGeneratedPerDay = reactorDepartment.run();
        amountOfEnergyGeneratedForYear += amountOfEnergyGeneratedPerDay;
        reactorDepartment.stop();
      } catch (NuclearFuelIsEmptyException e) {
        log.info("Внимание! Происходят работы на атомной станции! Электричества нет!");
      } catch (ReactorWorkException e) {
        log.info(e.getMessage());
      }
    }
    log.info("Атомная станция закончила работу. За год Выработано {} киловатт/часов",
        amountOfEnergyGeneratedForYear);
    log.info("Доход за год составил {} {}",
        economicDepartment.computeYearIncomes(amountOfEnergyGeneratedForYear),
        env.getActiveProfiles()[0].equals("france") ? env.getProperty("france.currency")
            : env.getProperty("morocco.currency"));
    totalAmountOfEnergyGenerated += amountOfEnergyGeneratedForYear;
    log.info("Количество инцидентов за год: {}", securityDepartment.getCountAccidents());
    securityDepartment.reset();
  }

  /**
   * Метод - запускает в работу атомную станцию в количестве лет указанных в аргументе метода.
   *
   * @param year - количество лет работы станции.
   */
  public void start(int year) {
    if (env.getActiveProfiles()[0].equals("france")) {
      log.info("Действие происходит в стране: {}", env.getProperty("france.name-country"));
    }
    if (env.getActiveProfiles()[0].equals("morocco")) {
      log.info("Действие происходит в стране: {}", env.getProperty("morocco.name-country"));
    }

    for (int i = 0; i < year; i++) {
      startYear();
    }
    log.info("Количество инцидентов за всю работу станции: {}", accidentCountAllTime);
  }

  /**
   * Метод - увеличивает поле accidentCountAllTime количество инцидентов на count.
   *
   * @param count - количество новых инцидентов.
   */
  public void incrementAccident(int count) {
    this.accidentCountAllTime += count;
  }
}

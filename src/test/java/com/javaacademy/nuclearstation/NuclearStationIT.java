package com.javaacademy.nuclearstation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class NuclearStationIT {

  @Autowired
  private NuclearStation nuclearStation;

  @ParameterizedTest
  @CsvFileSource(resources = "/nuclearstation/nuclear-station.csv", numLinesToSkip = 1, delimiterString = ";")
  @DisplayName("Успешный расчет количества выработанной энергии и количества инцидентов за все время")
  void whenStartNuclearStationAnyCountYearsThenGetIncomesAndTotalEnergy(int countYears,
      long expectedTotalEnergy, int expectedTotalAccident) {
    nuclearStation.start(countYears);
    long resultTotalEnergy = nuclearStation.getTotalAmountOfEnergyGenerated();
    int resultCountAccidentAllTime = nuclearStation.getCountAccidentAllTime();
    Assertions.assertEquals(expectedTotalEnergy, resultTotalEnergy);
    Assertions.assertEquals(expectedTotalAccident, resultCountAccidentAllTime);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/nuclearstation/increment-accident.csv", numLinesToSkip = 1, delimiterString = ";")
  @DisplayName("Успешное увеличение счетчика инцидентов за все время")
  void successIncrementAccident(int count, int expected) {
    int countStartAccident = nuclearStation.getCountAccidentAllTime();
    nuclearStation.incrementAccident(count);
    int countEndAccident = nuclearStation.getCountAccidentAllTime();
    Assertions.assertEquals(expected, countEndAccident - countStartAccident);
  }
}

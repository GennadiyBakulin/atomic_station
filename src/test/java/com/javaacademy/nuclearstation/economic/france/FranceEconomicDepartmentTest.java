package com.javaacademy.nuclearstation.economic.france;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("france")
class FranceEconomicDepartmentTest {

  @Autowired
  private FranceEconomicDepartment franceEconomicDepartment;

  @ParameterizedTest
  @CsvFileSource(resources = "/france-compute-year-incomes.csv", numLinesToSkip = 1, delimiterString = ";")
  void successComputeYearIncomes(long countElectricity, BigDecimal expected) {
    BigDecimal result = franceEconomicDepartment.computeYearIncomes(countElectricity);
    Assertions.assertEquals(expected, result);
  }
}
package com.javaacademy.nuclearstation.economic.morocco;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("morocco")
class MoroccoEconomicDepartmentTest {

  @Autowired
  private MoroccoEconomicDepartment moroccoEconomicDepartment;

  @ParameterizedTest
  @CsvFileSource(resources = "/morocco-compute-year-incomes.csv", numLinesToSkip = 1, delimiterString = ";")
  void successComputeYearIncomes(long countElectricity, BigDecimal expected) {
    BigDecimal result = moroccoEconomicDepartment.computeYearIncomes(countElectricity);
    Assertions.assertEquals(expected, result);
  }
}
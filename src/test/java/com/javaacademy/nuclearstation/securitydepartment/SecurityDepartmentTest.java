package com.javaacademy.nuclearstation.securitydepartment;

import com.javaacademy.nuclearstation.NuclearStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SecurityDepartmentTest {

  @Autowired
  private SecurityDepartment securityDepartment;
  @MockBean
  private NuclearStation nuclearStation;

  @Test
  @DisplayName("Добавление инцидента за период")
  void successAddAccident() {
    Assertions.assertEquals(0, securityDepartment.getCountAccidents());
    securityDepartment.addAccident();
    Assertions.assertEquals(1, securityDepartment.getCountAccidents());
    securityDepartment.addAccident();
    Assertions.assertEquals(2, securityDepartment.getCountAccidents());
  }

  @Test
  @DisplayName("Сброс счетчика инцидентов до нуля")
  void successReset() {
    securityDepartment.addAccident();
    securityDepartment.addAccident();
    securityDepartment.addAccident();
    securityDepartment.reset();
    int result = securityDepartment.getCountAccidents();
    Assertions.assertEquals(0, result);
  }
}
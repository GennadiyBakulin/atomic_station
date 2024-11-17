package com.javaacademy.nuclearstation.securitydepartment;

import com.javaacademy.nuclearstation.NuclearStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SecurityDepartmentIT {

  @Autowired
  private SecurityDepartment securityDepartment;
  @Autowired
  private NuclearStation nuclearStation;

  @Test
  @DisplayName("Увеличение счетчика инцидентов за все время работы станции")
  void successReset() {
    securityDepartment.addAccident();
    securityDepartment.addAccident();
    securityDepartment.addAccident();
    int startCountAllAccident = nuclearStation.getCountAccidentAllTime();
    securityDepartment.reset();
    int endCountAllAccident = nuclearStation.getCountAccidentAllTime();
    Assertions.assertEquals(3, endCountAllAccident - startCountAllAccident);
  }
}

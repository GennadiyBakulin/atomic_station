package com.javaacademy.nuclearstation.securitydepartment;

import com.javaacademy.nuclearstation.NuclearStation;
import org.junit.jupiter.api.Assertions;
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
  void successAddAccident() {
    Assertions.assertEquals(0, securityDepartment.getCountAccidents());
    securityDepartment.addAccident();
    Assertions.assertEquals(1, securityDepartment.getCountAccidents());
    securityDepartment.addAccident();
    Assertions.assertEquals(2, securityDepartment.getCountAccidents());
  }

  @Test
  void successReset() {
    securityDepartment.reset();
    Assertions.assertEquals(0, securityDepartment.getCountAccidents());
  }
}
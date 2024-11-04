package com.javaacademy.nuclearstation.data;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "france")
@Configuration
@Getter
@Setter
public class FranceData {

  private String nameCountry;
  private BigDecimal price;
  private BigDecimal percentage;
  private String currency;

}

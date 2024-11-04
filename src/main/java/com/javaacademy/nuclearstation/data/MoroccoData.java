package com.javaacademy.nuclearstation.data;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "morocco")
@Configuration
@Getter
@Setter
public class MoroccoData {

  private String nameCountry;
  private BigDecimal priceBase;
  private BigDecimal priceIncreased;
  private String currency;
}

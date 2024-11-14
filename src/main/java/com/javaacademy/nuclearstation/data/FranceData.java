package com.javaacademy.nuclearstation.data;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("france")
@ConfigurationProperties
@Component
@Getter
@Setter
public class FranceData extends Data {

  private BigDecimal price;
  private BigDecimal percentage;
}
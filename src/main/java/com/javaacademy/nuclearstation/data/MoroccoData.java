package com.javaacademy.nuclearstation.data;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("morocco")
@ConfigurationProperties
@Component
@Getter
@Setter
public class MoroccoData extends Data {

  private BigDecimal price;
  private BigDecimal priceIncreased;
}

package com.javaacademy.nuclearstation.data;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@ConfigurationProperties(prefix = "morocco")
@Configuration
@Getter
@Setter
@Profile("morocco")
public class MoroccoCountryData extends CountryDataBasic {

  private BigDecimal priceIncreased;
}

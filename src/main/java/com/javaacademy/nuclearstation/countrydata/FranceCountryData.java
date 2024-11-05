package com.javaacademy.nuclearstation.countrydata;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("france")
@ConfigurationProperties(prefix = "france")
@Component
@Getter
@Setter
public class FranceCountryData extends CountryDataBasic {

  private BigDecimal percentage;
}

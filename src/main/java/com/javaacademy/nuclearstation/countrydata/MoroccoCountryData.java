package com.javaacademy.nuclearstation.countrydata;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("morocco")
@ConfigurationProperties(prefix = "morocco")
@Component
@Getter
@Setter
public class MoroccoCountryData extends CountryDataBasic {

  private BigDecimal priceIncreased;
}

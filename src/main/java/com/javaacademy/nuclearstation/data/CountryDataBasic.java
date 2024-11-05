package com.javaacademy.nuclearstation.data;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDataBasic {

  private String nameCountry;
  private BigDecimal price;
  private String currency;
}

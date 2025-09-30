package com.znaji.core_lab.util;

import java.math.BigDecimal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderAmountConverter implements Converter<String, BigDecimal> {

        @Override
        public BigDecimal convert(String source) {
                // TODO: Add locale-aware parsing and error handling.
                return new BigDecimal(source);
        }
}

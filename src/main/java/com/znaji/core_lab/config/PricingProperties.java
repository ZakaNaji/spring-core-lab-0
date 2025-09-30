package com.znaji.core_lab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PricingProperties {

        private final double discountPercentage;

        public PricingProperties(@Value("#{${lab.discount:0.05} * 100}") double discountPercentage) {
                this.discountPercentage = discountPercentage;
        }

        public double discountPercentage() {
                return discountPercentage;
        }
}

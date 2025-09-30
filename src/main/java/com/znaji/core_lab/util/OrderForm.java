package com.znaji.core_lab.util;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class OrderForm {

        @NotBlank
        private String customerName;

        @Email
        private String customerEmail;

        @DecimalMin("0.0")
        private String amount;

        public String getCustomerName() {
                return customerName;
        }

        public void setCustomerName(String customerName) {
                this.customerName = customerName;
        }

        public String getCustomerEmail() {
                return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
                this.customerEmail = customerEmail;
        }

        public String getAmount() {
                return amount;
        }

        public void setAmount(String amount) {
                this.amount = amount;
        }
}

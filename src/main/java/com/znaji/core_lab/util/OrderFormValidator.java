package com.znaji.core_lab.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrderFormValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
                return OrderForm.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
                OrderForm form = (OrderForm) target;
                if (form.getCustomerName() != null && form.getCustomerName().equalsIgnoreCase("error")) {
                        errors.rejectValue("customerName", "forbidden", "Customer name cannot be 'error'");
                }
                // TODO: enforce numeric amount parsing and add BindingResult demonstration in controller layer.
        }
}

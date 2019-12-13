package com.mitrais.onlinetest.registrationapp.validation;
/*
 * Dear Maintainer,
 *
 * When I wrote this code, only I and God knew what it was. Now, only God knows!
 *
 * So, If you're done, trying to 'optimize' this routine (and failed).
 * Please, increment the following counter as a warning to the next guy:
 * total_hours_wasted_here: 999;
 *
 * Sincerely Yours, Hooman
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMobileNumberValidator implements ConstraintValidator<UniqueMobileNumber, String> {
    @Autowired
    private ApplicationContext applicationContext;

    private UniqueMobileNumberValidation service;

    private String fieldName;

    public UniqueMobileNumberValidator() {
    }

    @Override
    public void initialize(UniqueMobileNumber unique) {
        Class<? extends UniqueMobileNumberValidation> clazz = unique.service();
        this.fieldName = unique.fieldName();
        try {
            this.service = this.applicationContext.getBean(clazz);
        } catch(Exception ex) {
            // Cant't initialize service which extends UniqueUsernameValidator
        }
    }

    @Override
    public boolean isValid(String o, ConstraintValidatorContext context) {
        try {
            if (this.service !=null && o != null) {
                return this.service.isMobileNumberUnique(o, this.fieldName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}

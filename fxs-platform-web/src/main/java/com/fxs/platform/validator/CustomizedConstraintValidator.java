package com.fxs.platform.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Charles
 *
 */
public class CustomizedConstraintValidator implements ConstraintValidator<CustomizedConstraint, Object> {

	@Override
	public void initialize(CustomizedConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return true;
	}
}

package com.nesrux.jmfood.core.validation.validators;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

import com.nesrux.jmfood.core.validation.annotations.ValorZeroIncluiDescricao;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
	this.descricaoField = constraintAnnotation.descricaoField();
	this.descricaoField = constraintAnnotation.descricaoField();
	this.valorField = constraintAnnotation.valorField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
	boolean valido = true;

	try {
	    BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(value.getClass(), valorField)
		    .getReadMethod().invoke(value);
	    String descricao = (String) BeanUtils.getPropertyDescriptor(value.getClass(), descricaoField).getReadMethod()
		    .invoke(value);

	    if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
		valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
	    }
	} catch (Exception e) {
	    throw new ValidationException();
	}

	return valido;
    }

}

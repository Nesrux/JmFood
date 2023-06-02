package com.nesrux.jmfood.core.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import com.nesrux.jmfood.core.validation.annotations.FileSize;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private DataSize maxSize;

	@Override
	public void initialize(FileSize constraintAnnotation) {
		this.maxSize = DataSize.parse(constraintAnnotation.max());
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

		return file == null || file.getSize() <= this.maxSize.toBytes();

	}

}

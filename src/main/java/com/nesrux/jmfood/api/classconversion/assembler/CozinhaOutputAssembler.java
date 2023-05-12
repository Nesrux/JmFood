package com.nesrux.jmfood.api.classconversion.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaOutputAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

}

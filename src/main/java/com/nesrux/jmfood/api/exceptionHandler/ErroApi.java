package com.nesrux.jmfood.api.exceptionHandler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErroApi {
	private Integer status;
	private String type;
	private String title;
	private String detail;
}

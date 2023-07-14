package com.nesrux.jmfood.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
//classe que intecepita toadas as requisições para os controllers V
public class ApiDeprecationHandler extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getRequestURI().startsWith("/v2/")) {
			response.addHeader("X-JmfoodDeprecated", "Essa versão da api esta depreciada,"
					+ " e deixará de funcionar a partir de 31/12/2023 "
					+ "use a versão mais antiga da Api");
			
			response.setStatus(HttpStatus.GONE.value());
					return false;
		}

		return true;
	}

}

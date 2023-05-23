package com.nesrux.jmfood.core.squiggly;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>{

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		 factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "[]"));		
	}
	/*Permite que o tomcat aceite como parammetro de requisição o caractere especial []*/

}

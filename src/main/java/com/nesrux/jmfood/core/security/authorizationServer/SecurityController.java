package com.nesrux.jmfood.core.security.authorizationServer;

import org.springframework.stereotype.Controller;

@Controller
public class SecurityController {
	
	public String login() {
		return "pages/login";
	}
}

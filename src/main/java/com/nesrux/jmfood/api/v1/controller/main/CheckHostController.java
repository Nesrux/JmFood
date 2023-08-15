package com.nesrux.jmfood.api.v1.controller.main;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckHostController {
	@GetMapping("/check")
	public String checkHost() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress() + "__" +
				InetAddress.getLocalHost().getHostName();
	}
}

package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestrictedController {
	
	@GetMapping("/restricted")
	public String checkAccess() {
		return "Accesed";
	}

}

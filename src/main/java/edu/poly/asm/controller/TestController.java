package edu.poly.asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping("ok")
	public String ok() {
		return "auth/test";
	}
}

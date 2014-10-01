package me.rolandawemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PagesController {

	@RequestMapping({"/"})
	public String index() {
		return "index";
	}
}

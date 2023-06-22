package com.shashitest.UserServiceWS.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResources {

	private MessageSource messageSource;
	public TestResources(MessageSource messageSource)
	{
		this.messageSource=messageSource;
	}
	// to get diffrent language depends on request header, en, fr
	@GetMapping("/hello-internationalize")
	public String sayHelloInternationalize()
	{
		Locale local = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", local);
	}
}

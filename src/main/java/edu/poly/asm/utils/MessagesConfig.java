package edu.poly.asm.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessagesConfig {
	@Bean("messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasenames(
				"classpath:message/product",
				"classpath:message/user",
				"classpath:message/order",
				"classpath:message/orderdetail",
				"classpath:message/category");
		ms.setDefaultEncoding("utf-8");
		return ms;
	}
}

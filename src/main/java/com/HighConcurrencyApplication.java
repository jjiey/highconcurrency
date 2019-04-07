package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
public class HighConcurrencyApplication extends WebMvcConfigurationSupport {

	public static void main(String[] args) {
		SpringApplication.run(HighConcurrencyApplication.class, args);
	}

	// 添加Filter
	@Bean
	public FilterRegistrationBean httpFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new HttpFilter()); // 配置Filter
		registrationBean.addUrlPatterns("/threadLocal/*"); // 拦截的url
		return registrationBean;
	}

	// 添加Interceptor
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}
}

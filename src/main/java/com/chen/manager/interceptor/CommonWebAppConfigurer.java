package com.chen.manager.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CommonWebAppConfigurer implements WebMvcConfigurer {

	/**
	 * 不被拦截的url
	 */
	@Value("${unCheckUrl.name}")
	private String name;

	/**
	 * 文件上传路径
	 */
	@Value("${file.upload.path}")
	private String fileUploadPath;

	/**
	 * 文件资源路径
	 */
	@Value("${file.resource.handler}")
	private String fileResourceHandler;
	
	/**
	 * 文件资源路径
	 */
	@Value("${file.resource.location}")
	private String fileResourceLocation;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		InterceptorRegistration r1 = registry.addInterceptor(
				commonInterceptor()).addPathPatterns("/**");
		for (String string : name.split(",")) {
			// excludePathPatterns 用户排除拦截
			r1.excludePathPatterns(string);
		}
		// super.addInterceptors(registry);
	}

	@Bean
	public CommonInterceptor commonInterceptor() {
		return new CommonInterceptor();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(fileResourceHandler).addResourceLocations(
				fileResourceLocation);
	}

}
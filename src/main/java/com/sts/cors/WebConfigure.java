package com.sts.cors;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfigure extends WebMvcAutoConfiguration {

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // Allow CORS for all paths
				.allowedOrigins("http://localhost:5173","banking-two-kappa.vercel.app\r\n"
						+ "") // Allow requests from this origin
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*").allowCredentials(true);
	}

}

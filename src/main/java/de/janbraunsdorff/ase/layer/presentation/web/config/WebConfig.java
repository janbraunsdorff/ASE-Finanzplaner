package de.janbraunsdorff.ase.layer.presentation.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private LoggingInterceptor intercepter;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.intercepter)
          .addPathPatterns("/**");
    }
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
    }

}

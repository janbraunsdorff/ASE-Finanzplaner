package de.janbraunsdorff.ase.layer.presentation.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        registry.addMapping("/**");
    }
}

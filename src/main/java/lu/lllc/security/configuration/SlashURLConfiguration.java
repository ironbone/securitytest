package lu.lllc.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SlashURLConfiguration implements WebMvcConfigurer {
	@Override
	  public void configurePathMatch(PathMatchConfigurer configurer) {
	      configurer.setUseTrailingSlashMatch(false);
	  }

}

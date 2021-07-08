package com.enzen.waterMdm.config;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.EnableWebMvcConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;

@Configuration
public class MvcConfig extends EnableWebMvcConfiguration {

    public MvcConfig(ObjectProvider<WebMvcProperties> mvcPropertiesProvider,
			ObjectProvider<WebMvcRegistrations> mvcRegistrationsProvider, ListableBeanFactory beanFactory) {
		super(mvcPropertiesProvider, mvcRegistrationsProvider, beanFactory);
	}

	@Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseRegisteredSuffixPatternMatch(false);
        configurer.setUseSuffixPatternMatch(false);
    }
}
package org.i18ntest.i18ntest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    /**
     * 기본 Locale 설정
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver locale = new SessionLocaleResolver();
        locale.setDefaultLocale(Locale.KOREA); // ko_KR
        return locale;
    }

    // [GET] /say/hello?lang=ko_KR
    // [GET] /say/hello?lang=en_US
    // 전달 받은 lang 값에 따라서 locale 설정
    private LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor locale = new LocaleChangeInterceptor();
        locale.setParamName("lang");
        return locale;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}

package com.saspes.proxy;

import com.saspes.proxy.filter.ApiRequestDecorator;
import com.saspes.proxy.filter.ApiTokenAccessFilter;
import com.saspes.proxy.filter.ForwardHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class UiApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }

    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/index.html", "/home.html", "/", "/login").permitAll()
                .anyRequest().authenticated()
                .antMatchers(HttpMethod.POST).authenticated()
                .and()
                .logout()
                .and()
                .addFilterBefore(new ApiTokenAccessFilter(tokenServices), AbstractPreAuthenticatedProcessingFilter.class);
        // @formatter:on
    }

    @Bean
    public ApiRequestDecorator apiRequestDecorator() {
        return new ApiRequestDecorator();
    }

    @Bean
    public ForwardHeaderFilter forwardHeaderFilter() {
        return new ForwardHeaderFilter();
    }
}

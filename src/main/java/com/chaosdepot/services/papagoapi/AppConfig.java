package com.chaosdepot.services.papagoapi;

import com.chaosdepot.services.papagoapi.utilities.WebDriverKiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * App configuration.
 */
@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {
    /**
     * Get web driver kill class.
     *
     * @return web driver kill
     */
    @Bean
    public WebDriverKiller taskKill() {
        return new WebDriverKiller();
    }

    /**
     * Setup user info.
     *
     * @param auth auth manager
     * @throws Exception auth exception
     */
    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user")
            .password("{noop}pass")
            .roles("USER", "ADMIN");
    }

    /**
     * Setup http security.
     *
     * @param http http security
     * @throws Exception http security exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .httpBasic().and().authorizeRequests()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
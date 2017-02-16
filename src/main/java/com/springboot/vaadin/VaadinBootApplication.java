package com.springboot.vaadin;

import com.springboot.vaadin.ui.custom.CustomAuthenticationProvider;
import com.vaadin.spring.internal.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.vaadin.spring.events.annotation.EnableVaadinEventBus;
import org.vaadin.spring.security.annotation.EnableVaadinSharedSecurity;

//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableVaadinEventBus
public class VaadinBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaadinBootApplication.class, args);
    }

    @Bean
    static VaadinSessionScope vaadinSessionScope() {
        return new VaadinSessionScope();
    }



    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
    @EnableVaadinSharedSecurity
    static class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        CustomAuthenticationProvider customAuthenticationProvider;

        @Autowired
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(customAuthenticationProvider);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {


            http.exceptionHandling()
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"));
            http.csrf().disable();
            http.formLogin().disable();
            http.authorizeRequests().antMatchers("/**").anonymous().antMatchers("/vaadinServlet/UIDL/**")
                    .permitAll().antMatchers("/vaadinServlet/HEARTBEAT/**").permitAll().anyRequest().authenticated();
//            http.logout().addLogoutHandler(new VaadinSessionClosingLogoutHandler()).logoutUrl("/logout")
//                    .logoutSuccessUrl("/login?logout").permitAll();

        }

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/VAADIN/**");
        }
    }
}

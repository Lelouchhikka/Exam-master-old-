package org.itstep.exam.config;

import org.itstep.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true,securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/**").access("hasRole('ROLE_USER')")
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/index").failureUrl("/loginError");

//        http.authorizeRequests().antMatchers("/css/**","/js/**").permitAll().antMatchers("/")
//                .permitAll();
//        http        .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//        http.formLogin().loginProcessingUrl("/auth").permitAll().loginPage("/login").permitAll()
//                .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/").permitAll();
//        http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/login").permitAll();
    }


    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    // ?????????????? ????????????????????????????, admin ?? user

}

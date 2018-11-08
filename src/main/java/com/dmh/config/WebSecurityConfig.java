package com.dmh.config;

import com.dmh.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//扩展SpringSecurity配置需要继承此类

    @Bean
    UserDetailsService customUserService(){//注册UserDetailsService的bean
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());//添加自定义的userDetailsService认证
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers("/register").permitAll() // 注册请求按钮不需要验证
                .antMatchers("/static/**","/images/**").permitAll()//静态资源允许访问
                .antMatchers("/zhuce").permitAll() // 注册请求页面不需要验证
                .anyRequest().authenticated()//所有的请求需要认证即登陆后才能访问
                .and().formLogin().loginPage("/sign_in").loginProcessingUrl("/login")
                .defaultSuccessUrl("/home",true)
                .failureUrl("/sign_in?error").permitAll() //登录页面可任意访问
                .and()
                .logout().logoutSuccessUrl("/sign_in").permitAll()//注销请求可任意访问
                .and().csrf().disable();
    }
}

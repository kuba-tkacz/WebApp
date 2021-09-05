package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test").password("test").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index", "/personList") // mamy dostęp na ROLE_USER tylko do tych wymienionych endpointow
                .hasAnyAuthority("ROLE_USER") // definiujemy role dla powyższych dostępów
                .antMatchers("/technology", "/tasks") // mamy dostęp na ROLE_ADMIN tylko do tych wymienionych endpointow
                .hasAnyAuthority("ROLE_ADMIN") // definiujemy role dla powyższych dostępów
                .and()
                .csrf().disable() // wyłączamy zabezpieczenie CSRF w celu użycia np. postmana
                .headers().frameOptions().disable()
                .and()
                .formLogin() // wskazujemy teraz, że będziemy konfigurować formularz logowania
                .loginPage("/login") // wskazujemy endpoint w którym bedzie odbywać się autoryzacja
                .usernameParameter("username") // nadajemy nazwę jaka będzie jako name w inpucie w polu login formularzu
                .passwordParameter("password")// nadajemy nazwę jaka będzie jako name w inpucie w polu hasło formularzu
                .failureForwardUrl("/login?error")// co się stanie w momencie błędnego wpisania loginu lub hasła
                .defaultSuccessUrl("/index") // co się stanie w momencie prawidłowego wpisania loginu i hasła
                .and()
                .logout()
                .logoutSuccessUrl("/login"); // wskazujemy endpoint na który nas przekieruje kiedy się wylogujemy
    }
}

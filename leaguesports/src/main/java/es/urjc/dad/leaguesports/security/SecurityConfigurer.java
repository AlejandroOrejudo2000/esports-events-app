package es.urjc.dad.leaguesports.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.urjc.dad.leaguesports.services.UserService;

@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Value("${security.user}") private String user;
    @Value("${security.pass}") private String pass;

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        // String encodedPassword = encoder.encode("pass");
        auth.inMemoryAuthentication().withUser(user).password("{bcrypt}"+pass).roles("USER");
        auth.userDetailsService(userService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests().anyRequest().permitAll();

        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/private");
        http.formLogin().failureUrl("/loginerror");

        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/private");

        http.csrf().disable();
    }
    
}

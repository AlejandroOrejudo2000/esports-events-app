package es.urjc.dad.leaguesports.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Value("${security.user}") private String user;
    @Value("${security.pass}") private String pass;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        // PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // String encodedPassword = encoder.encode("pass");
        auth.inMemoryAuthentication().withUser(user).password("{bcrypt}"+pass).roles("USER");
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

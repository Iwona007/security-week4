package pl.iwona.securityencoderweek4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.iwona.securityencoderweek4.encoder.AlgorytmImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("*").permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new AlgorytmImpl();
    }
}

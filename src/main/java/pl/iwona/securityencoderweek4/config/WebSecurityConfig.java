package pl.iwona.securityencoderweek4.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.iwona.securityencoderweek4.encoder.AlgorytmImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private UserDetailsService userDetailsService;
//    private DataSource dataSource;
//    private PasswordEncoder passwordEncoder;
//
//    public WebSecurityConfig(UserDetailsService userDetailsService, DataSource dataSource, PasswordEncoder passwordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.dataSource = dataSource;
//        this.passwordEncoder = passwordEncoder;
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("*").permitAll();
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/signup").permitAll()
//                .antMatchers("/register").permitAll()
//                .and()
//                .formLogin().loginPage("/login").defaultSuccessUrl("/user").permitAll()
//                .and()
//                .tokenValiditySeconds(86400)
//                .and()
//                .logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/stranger");
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
        return new AlgorytmImpl();
    }
}

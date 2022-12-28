package com.example.springforumapp.security.config;

import com.example.springforumapp.security.JWTFilter;
import com.example.springforumapp.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UsersService usersService;
    private final JWTFilter jwtFilter;

    @Autowired
    public SecurityConfig(UsersService usersService, JWTFilter jwtFilter) {
        this.usersService = usersService;
        this.jwtFilter = jwtFilter;
    }


    protected  void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/profile","/api/activate").authenticated()
                .antMatchers(HttpMethod.POST,"/api/boards/{boardName}").authenticated()
                .antMatchers("/**").permitAll()
                .and()
//                .formLogin().loginPage("/auth/login")
//                .loginProcessingUrl("/login_auth")
//                .defaultSuccessUrl("/",true)
//                .failureUrl("/auth/login?error")
//                .and()
//                .logout().logoutUrl("/auth/logout").logoutSuccessUrl("/")
//                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception  {
        auth.userDetailsService(usersService)
                .passwordEncoder(getPasswordEncoder());
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
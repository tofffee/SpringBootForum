package com.example.springforumapp.security.config;

import com.example.springforumapp.security.JWTFilter;
import com.example.springforumapp.security.UserDetailsServiceImpl;
import com.example.springforumapp.security.oAuth2.CustomOAuth2UserService;
import com.example.springforumapp.security.oAuth2.OAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final JWTFilter jwtFilter;

    protected  void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .authorizeRequests()

                .antMatchers("/api/activate").authenticated()
                .antMatchers(HttpMethod.GET,"/user/authuserinfo").authenticated()
                .antMatchers(HttpMethod.POST,"/api/boards/{boardName}",
                                                        "/api/upload/image",
                                                        "/api/boards/{boardName}/{publicationId}").authenticated()
                .antMatchers(HttpMethod.DELETE,"/api/boards/{boardName}/{publicationId}").authenticated()

                .antMatchers(HttpMethod.POST,"/api/boards").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/boards","/api/boards/{id}","/api/admin/delete/users/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/boards/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH,"/api/admin/grant/users/{id}","/api/admin/ungrant/users/{id}").hasRole("ADMIN")
                .antMatchers("/**").permitAll()

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception  {
        auth.userDetailsService(userDetailsService)
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
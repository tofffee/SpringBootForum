package com.example.springforumapp.security.config;

import com.example.springforumapp.security.JWTFilter;
import com.example.springforumapp.security.oAuth2.CustomOAuth2UserService;
import com.example.springforumapp.security.oAuth2.OAuth2LoginSuccessHandler;
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
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final JWTFilter jwtFilter;

    @Autowired
    public SecurityConfig(UsersService usersService, CustomOAuth2UserService customOAuth2UserService, OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler, JWTFilter jwtFilter) {
        this.usersService = usersService;
        this.customOAuth2UserService = customOAuth2UserService;
        this.oAuth2LoginSuccessHandler = oAuth2LoginSuccessHandler;
        this.jwtFilter = jwtFilter;
    }

    protected  void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .authorizeRequests()

                .antMatchers("/api/activate").authenticated()
                .antMatchers(HttpMethod.POST,"/api/boards/{boardName}").authenticated()
                .antMatchers(HttpMethod.DELETE,"/api/boards/{boardName}/{publicationId}").authenticated()

                .antMatchers(HttpMethod.POST,"/api/boards").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/boards","/api/boards/{id}","/api/admin/delete/users/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/boards/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH,"/api/admin/grant/users/{id}","/api/admin/ungrant/users/{id}").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
//                .and()
//                .formLogin().loginPage("/auth/login")
//                .loginProcessingUrl("/login_auth")
//                .defaultSuccessUrl("/",true)
//                .failureUrl("/auth/login?error")
//                .and()
//                .logout().logoutUrl("/auth/logout").logoutSuccessUrl("/")
              //  .and()
//                .oauth2Login()
//                    .userInfoEndpoint()
//                    .userService(customOAuth2UserService)
//                    .and()
//                    .successHandler(oAuth2LoginSuccessHandler);
                .and()
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
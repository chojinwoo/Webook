package com.webook.config;

import com.webook.person.domain.Person;
import com.webook.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by bangae1 on 2016-11-26.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.webook"})
public class SpingSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PersonService personService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(new String[]{"/css/*", "/js/**/*", "/books/*", "/font/*", "/fonts/*", "/img/*", "/js/*"});
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().antMatchers("/").permitAll().antMatchers("/admin").hasRole("ADMIN")
                .and().csrf().disable()
                .formLogin().loginPage("/").loginProcessingUrl("/login").permitAll().usernameParameter("id").passwordParameter("password").defaultSuccessUrl("/")
                .failureHandler(customAuthenticationFailureHandler()).successForwardUrl("/").permitAll()
                .and().logout().deleteCookies("JSESSIONID","remember-me").logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll()
                .and().rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(1209600).rememberMeParameter("remember-me")
                .and().sessionManagement().maximumSessions(2).expiredUrl("/");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        AuthenticationFailureHandler fail = new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                System.out.println("login Filed ::: " + exception.getMessage());
                response.sendRedirect("/");
            }
        };
        return fail;
    }

    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        AuthenticationSuccessHandler suss = new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                HttpSession session = request.getSession();
                System.out.println("Login Success ::: " + authentication.getDetails());
                RequestDispatcher rd = request.getRequestDispatcher("/");
                Person person = (Person) authentication.getPrincipal();
                session.setAttribute("username", person.getUsername());
                session.setAttribute("authorities", authentication.getAuthorities());
                response.setStatus(HttpServletResponse.SC_OK);
                rd.forward(request, response);

            }
        };
        return suss;
    }

}

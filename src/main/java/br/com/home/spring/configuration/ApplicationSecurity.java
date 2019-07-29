package br.com.home.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@ImportResource("/WEB-INF/applicationContext.xml")
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /*Configuracoes de acesso*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                /*Autoriza requisicoes para os antMatchers informados*/
                .authorizeRequests()
                .antMatchers("/faces/login/**").permitAll()
                .antMatchers("/").permitAll()

                /*todas as outras requisicoes deve ser autenticadas*/
                .anyRequest().authenticated()

                /*urls de login e logout devem ser permitidas*/
                .and()
                    .formLogin()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/faces/login")
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/faces/logout"))
                    .permitAll();
    }

    /*Ignora a protecao de urls informadas*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**")
        .antMatchers("/css/**")
        .antMatchers("/javax.faces.resource/**");
    }

    /*Valida se o usuario existe e tem as devidas permissoes para acessar o recurso*/
    /*Uma opcao e utilzar o codificador de senhas para compara-las com o banco*/
    /*Codificadores: MD-5, SHA1, SHA1-256, SHA1-512, BCrypt*/
    /*O uso do BCrypt e recomendado pela documentacao do Spring Security*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}


package br.com.home.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*Registra filtro na FilterChainProxy e fica ouvindo as requisicoes*/
@Configuration
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    /*Carrega classe de configuracao de acesso ao inicializar o filtro*/
    public SecurityInitializer(){
        super(ApplicationSecurity.class);
    }
}
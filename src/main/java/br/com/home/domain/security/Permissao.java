package br.com.home.domain.security;

import org.springframework.security.core.GrantedAuthority;

/*Implementar GrantedAuthority permite que o Spring Security possa pegar o nome da permissao*/
public class Permissao implements GrantedAuthority {

    private String nome;

    public Permissao(){
    }

    public Permissao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return nome;
    }
}

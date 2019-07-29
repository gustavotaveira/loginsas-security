package br.com.home.dao;

import br.com.home.domain.security.Permissao;
import br.com.home.domain.security.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Classe responsavel por validar se um usuario existe ou nao*/

@Repository
public class UsuarioDao implements UserDetailsService {

    private static List<Usuario> usuarios = new ArrayList<Usuario>();
    private static String ROLE_ADMIN = "ADMIN";
    private static String ROLE_USER = "USER";

    static {
        usuarios.add(new Usuario("gustavo", "123456",
                Arrays.asList(new Permissao(ROLE_ADMIN))));
        usuarios.add(new Usuario("waldeson", "654321",
                Arrays.asList(new Permissao(ROLE_USER))));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for(Usuario usuario : usuarios){
            if(usuario.getUsername().equals(username)){
                return usuario;
            }
        }
        throw new  UsernameNotFoundException("Username: "+username+" nao encontrado");
    }
}

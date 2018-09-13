package br.com.itexto.springforum.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.itexto.springforum.dao.DAOPermissaoUsuario;
import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.PermissaoUsuario;
import br.com.itexto.springforum.entidades.Usuario;

@Service
public class AuthenticationUserDetailsService implements UserDetailsService{
	
	@Autowired
	private DAOUsuario daoUsuario;

	@Autowired
	private DAOPermissaoUsuario daoPermissao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		Usuario usuario = daoUsuario.getUsuario(username);
		
		if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		List<PermissaoUsuario> listaPerfis = daoPermissao.getPermissoesUsuario(usuario);

        for (PermissaoUsuario usuarioPerfil : listaPerfis) {
            grantedAuthorities.add(new SimpleGrantedAuthority(usuarioPerfil.getAuthority()));
        }
		
		return new UserDetailsImpl(usuario, grantedAuthorities);
	}

}

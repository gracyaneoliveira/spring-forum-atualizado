package br.com.itexto.springforum.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.itexto.springforum.dao.DAOPermissaoUsuario;
import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.PermissaoUsuario;
import br.com.itexto.springforum.entidades.Usuario;

/**
 * Exemplo de authentication provider
 * 
 * @author kicolobo
 */
public class SFAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private DAOUsuario daoUsuario;

	@Autowired
	private DAOPermissaoUsuario daoPermissao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth;
		
		String username = token.getName();
		String senha = token.getCredentials() != null ? token.getCredentials().toString() : null;
		
		Usuario usuario = getDaoUsuario().getUsuario(username);
		
		if (usuario == null) {
			return null;
		}
		
		if(!bCryptPasswordEncoder.matches(senha, usuario.getHashSenha())) {
			return null;
		}
		
		List<PermissaoUsuario> permissoes = getDaoPermissao().getPermissoesUsuario(usuario);
		SFAuthentication resultado = new SFAuthentication(usuario, permissoes);
		resultado.setAuthenticated(usuario != null);
		return resultado;
	}

	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	public DAOUsuario getDaoUsuario() {
		return daoUsuario;
	}
	
	public void setDaoUsuario(DAOUsuario dao) {
		daoUsuario = dao;
	}
	
	
	public DAOPermissaoUsuario getDaoPermissao() {
		return daoPermissao;
	}
	
	public void setDaoPermissao(DAOPermissaoUsuario dao) {
		daoPermissao = dao;
	}
}

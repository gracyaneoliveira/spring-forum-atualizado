package br.com.itexto.springforum.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.itexto.springforum.dao.DAOPermissaoUsuario;
import br.com.itexto.springforum.entidades.PermissaoUsuario;
import br.com.itexto.springforum.entidades.Usuario;

@Repository("daoPermissaoUsuario")
public class HBPermissaoUsuario implements DAOPermissaoUsuario {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<PermissaoUsuario> getPermissoesUsuario(Usuario usuario) {
		if (usuario == null) {
			return new ArrayList<PermissaoUsuario>();
		}
		Query<PermissaoUsuario> query = sessionFactory.getCurrentSession().createQuery("from PermissaoUsuario pu where pu.usuario = ?0", PermissaoUsuario.class);
		query.setParameter(0, usuario);
		return query.list();
	}

	public void addRole(String role, Usuario usuario) {
		if (role != null && usuario != null) {
			PermissaoUsuario permissao = new PermissaoUsuario();
			permissao.setRole(role);
			permissao.setUsuario(usuario);
			sessionFactory.getCurrentSession().saveOrUpdate(permissao);
		}
		
	}

}

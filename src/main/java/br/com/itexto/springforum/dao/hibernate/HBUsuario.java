package br.com.itexto.springforum.dao.hibernate;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Usuario;

@Repository("daoUsuario")
@Transactional(propagation=Propagation.SUPPORTS)
public class HBUsuario extends HBDAO<Usuario> implements DAOUsuario{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Usuario getUsuario(String login, String senha) {
		Query<Usuario> query = getSession().createQuery("from Usuario u where u.login = ?0 and u.hashSenha = ?1", Usuario.class);
		query.setParameter(0, login);
//		query.setString(1, DigestUtils.sha256Hex(senha));
		query.setParameter(1, bCryptPasswordEncoder.encode(senha));
		return (Usuario) query.uniqueResult();				   
	}

	public Usuario getUsuario(String login) {
		Query<Usuario> query = getSession().createQuery("from Usuario u where u.login = ?0", Usuario.class);
		query.setParameter(0, login);
		return (Usuario) query.uniqueResult();
	}

	@Override
	protected Class<Usuario> getClazz() {
		return Usuario.class;
	}

}

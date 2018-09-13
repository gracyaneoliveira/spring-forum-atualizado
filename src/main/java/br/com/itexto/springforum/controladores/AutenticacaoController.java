package br.com.itexto.springforum.controladores;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Usuario;

/**
 * Estamos usando a autenticacao feita com spring-security
 * @author FAMÍLIA
 *
 */
@Deprecated
@Controller
public class AutenticacaoController {

	@Autowired
	private DAOUsuario daoUsuario;
	
	@RequestMapping(value = "/loginFalho")
	public String loginFalho() {
		return "loginFalho";
	}

	/**
	 * Mapeamento de autenticação.
	 * Reparem que neste ponto definimos que apenas POST será aceito no processo
	 * de autenticação
	 * 
	 * Para lidar com sessões, tudo o que precisamos fazer é incluir na chamada do método um atributo do tipo
	 * HttpSession. 
	 * @param login
	 * @param senha
	 * @param sessao
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("login") String login, 
						@RequestParam("senha") String senha, 
						HttpSession sessao) {
		Usuario usuario = daoUsuario.getUsuario(login, senha);
		if (usuario == null) {
			return "loginFalho";
		} else {
			usuario.setUltimoLogin(new Date());
			daoUsuario.persistir(usuario);
			sessao.setAttribute("usuario", usuario);
			/**
			 * Esta instrução diz ao Spring MVC que deve ser feito
		     * um redirecionamento para a visualização cujo nome lógico é / —
			 * ou seja, a raiz do projeto, que no caso é a ação index do
			 * HomeController
			 */
			return "redirect:/";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}

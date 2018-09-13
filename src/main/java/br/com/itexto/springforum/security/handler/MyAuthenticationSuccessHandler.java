package br.com.itexto.springforum.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Usuario;
import br.com.itexto.springforum.security.SFAuthentication;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	@Autowired
	private DAOUsuario daoUsuario;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
        //do some logic here if you want something to be done whenever
        //the user successfully logs in.
 
        HttpSession session = request.getSession();

        /**
         * Usar com implementação do AuthenticationProvider
         */
        SFAuthentication authUser = (SFAuthentication) SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("usuario", (Usuario)authUser.getDetails());

        /**
         * Usar com implementação do UserDetailsService
         */
//        UserDetailsImpl authUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        session.setAttribute("usuario", authUser.getUsuario());
        
 
        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);
 
        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        response.sendRedirect(session.getServletContext().getContextPath()+ "/");
	}
	
	public DAOUsuario getDaoUsuario() {
		return daoUsuario;
	}
	
	public void setDaoUsuario(DAOUsuario dao) {
		daoUsuario = dao;
	}
}

package br.com.webjsf;

import java.sql.SQLException;

import br.com.webjsf.model.dao.LoginDao;
import br.com.webjsf.model.entity.Login;

public class Principal {
	public static void main(String[] args) throws SQLException {
		// TODO Testes unitários serão realizados aqui
		String login = "admin";
		LoginDao negocio = new LoginDao();
		Login osh = negocio.recuperarLogin(login);
		
		System.out.println(osh);
	}

}

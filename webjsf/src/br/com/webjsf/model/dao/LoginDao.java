package br.com.webjsf.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.webjsf.model.entity.Login;

public class LoginDao extends AbastractDao {
	public LoginDao() {
		super.configuraConexao();
	}

	public Login recuperarLogin(String login) throws SQLException {
		Login loginRetorno = null;

		this.comandoSql = conexao
				.prepareStatement("SELECT id_login, login, senha from public.login  WHERE lower(login) = lower('" + login + "')");

		ResultSet resultadoSql = comandoSql.executeQuery();

		while (resultadoSql.next()) {
			loginRetorno = new Login();

			loginRetorno.setIdLogin(resultadoSql.getLong("id_Login"));
			loginRetorno.setLogin(resultadoSql.getString("login"));
			loginRetorno.setSenha(resultadoSql.getString("senha"));
		}

		return loginRetorno;
	}
}

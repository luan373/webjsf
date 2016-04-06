package br.com.webjsf.controller.bean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import br.com.webjsf.model.dao.LoginDao;
import br.com.webjsf.model.entity.Login;

@ManagedBean
public class LoginBean {

	private LoginDao loginDao = null;

	public LoginBean() {
		this.loginDao = new LoginDao();
	}

	private String login;
	private String senha;
	private String validacao = "";

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getValidacao() {
		return validacao;
	}

	public String setValidacao(String validacao) {
		return this.validacao = validacao;
	}

	public String validaLogin() throws SQLException {
		Login user = null;
		try {
			user = this.loginDao.recuperarLogin(login);

			if (login == null) {
				return setValidacao("Usu�rio inexistente");
			}

			if (!isSenhaCorreta(user, senha)) {
				return setValidacao("Senha incorreta");
			}

		} catch (SQLException e) {
			return e.getMessage();
		}
		return "Foi";
	}

	private boolean isSenhaCorreta(Login login, String senha) {

		boolean senhaCorreta = false;

		if (login.getSenha().toLowerCase().equals(senha.toLowerCase())) {
			senhaCorreta = true;
		}

		return senhaCorreta;
	}

}

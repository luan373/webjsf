package br.com.webjsf.model.entity;

public class Login {
	private long idLogin;
	private String login;
	private String senha;

	public long getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(long idLogin) {
		this.idLogin = idLogin;
	}

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

	@Override
	public String toString() {
		return "login [idLogin=" + idLogin + ", login=" + login + ", senha=" + senha + "]";
	}

}

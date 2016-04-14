package br.com.webjsf.model.entity;

import java.util.Date;

public class Paciente {
	private long idPaciente;
	private String nome;
	private String telefone;
	private String email;
	private String diagnostico;
	private Date registro;

	public long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "paciente [idPaciente=" + idPaciente + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email
				+ ", diagnostico=" + diagnostico + ", registro=" + registro + "]";
	}

}

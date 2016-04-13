package br.com.webjsf.controller.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import br.com.webjsf.model.dao.PacienteDao;
import br.com.webjsf.model.entity.Paciente;

@ManagedBean(name = "pacienteBean")
// @RequestScoped
@SessionScoped
public class PacienteBean {

	private PacienteDao pacienteDao = null;

	public PacienteBean() {
		this.pacienteDao = new PacienteDao();
	}

	// @ManagedProperty(value="#{param.idPaciente}")
	private String idPaciente;
	private String nome;
	private String telefone;
	private String email;
	private String diagnostico;
	private String registro;
	private List<Paciente> listaPaciente = null;

	private String validacao = "";

	public PacienteDao getPacienteDao() {
		return pacienteDao;
	}

	public void setPacienteDao(PacienteDao pacienteDao) {
		this.pacienteDao = pacienteDao;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
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

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getValidacao() {
		return validacao;
	}

	public void setValidacao(String validacao) {
		this.validacao = validacao;
	}

	public List<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public String validaPaciente() {
		Paciente paciente = new Paciente();

		try {
			if (nome.equals("") || telefone.equals("") || email.equals("") || diagnostico.equals("")) {
				setValidacao("Preencha os campos obrigatórios");
			} else {
				paciente.setNome(nome);
				// paciente.setTelefone(Integer.parseInt(telefone));
				paciente.setTelefone(33736517);
				paciente.setEmail(email);
				paciente.setDiagnostico(diagnostico);

				if (idPaciente.equals(null)) {
					this.pacienteDao.inserir(paciente);
				} else {
					paciente.setIdPaciente(Integer.parseInt(idPaciente));
					this.pacienteDao.alterar(paciente);
				}

			}
		} catch (Exception e) {
			e.getMessage();
		}

		return validacao;
	}

	public void pesquisaPaciente() throws SQLException {
		Paciente paciente = new Paciente();
		List<Paciente> pacienteLista = new ArrayList<Paciente>();

		paciente.setNome(nome);
		paciente.setEmail(email);

		pacienteLista = this.pacienteDao.pesquisar(paciente);

		this.listaPaciente = pacienteLista;
	}

	public String direcionaPagina() {
		return "pacienteFormulario";
	}

	public String getaddTituloPagina() {
		if (idPaciente == null || idPaciente == "") {
			return "Cadastrar Paciente";
		}
		return "Alterar Paciente";
	}

}

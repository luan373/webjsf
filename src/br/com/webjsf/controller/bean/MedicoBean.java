package br.com.webjsf.controller.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.webjsf.model.dao.MedicoDao;
import br.com.webjsf.model.entity.Medico;

@ManagedBean(name="medicoBean")
@RequestScoped
public class MedicoBean {
	private MedicoDao medicoDao = null;
	
	public MedicoBean(){
		this.medicoDao = new MedicoDao();
	}
	
	@ManagedProperty(value="#{param.idMedico}")
	private String idMedico;
	private String nome;
	private String crm;
	private String especialidade;
	private String email;
	private String telefone;
	private List<Medico> listaMedico = null;
	
	private String validacao = "";

	public MedicoDao getMedicoDao() {
		return medicoDao;
	}

	public void setMedicoDao(MedicoDao medicoDao) {
		this.medicoDao = medicoDao;
	}

	public String getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(String idMedico) {
		this.idMedico = idMedico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Medico> getListaMedico() {
		return listaMedico;
	}

	public void setListaMedico(List<Medico> listaMedico) {
		this.listaMedico = listaMedico;
	}

	public String getValidacao() {
		return validacao;
	}

	public void setValidacao(String validacao) {
		this.validacao = validacao;
	}
	
	public String validaMedico() {
		Medico medico = new Medico();
		
		try {
			if (nome.equals("") || crm.equals("") || especialidade.equals("") || email.equals("") || telefone.equals("")) {
				setValidacao("Preencha os campos obrigatórios");
			} else {
				medico.setNome(nome);
				medico.setCrm(crm);
				medico.setEspecialidade(especialidade);
				medico.setEmail(email);
				//medico.setTelefone(telefone);
				
				this.medicoDao.inserir(medico);
			}
		} catch (Exception e){
			return e.getMessage();
		}
		
		return validacao;		
	}
	
	public String getTituloPagina() {
		if (idMedico == null || idMedico == "") {
			return "Cadastrar Médico";
		}		
		return "Alterar Médico";
	}
	
}

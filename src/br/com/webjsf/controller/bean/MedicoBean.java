package br.com.webjsf.controller.bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.webjsf.model.dao.MedicoDao;
import br.com.webjsf.model.entity.Medico;

@ManagedBean(name = "medicoBean")
@SessionScoped
public class MedicoBean {
	private MedicoDao medicoDao = null;

	public MedicoBean() {
		this.medicoDao = new MedicoDao();
	}

//	@ManagedProperty(value = "#{param.idMedico}")
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

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

		try {
			if (nome.equals("") || crm.equals("") || especialidade.equals("") || email.equals("")
					|| telefone.equals("")) {
				setValidacao("Preencha os campos obrigatórios");
			} else {
				medico.setNome(nome);
				medico.setCrm(crm);
				medico.setEspecialidade(especialidade);
				medico.setEmail(email);

				telefone = telefone.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
				medico.setTelefone(telefone);

				if (idMedico == null) {
					this.medicoDao.inserir(medico);

					ec.redirect(ec.getRequestContextPath() + "/medico/medicoPesquisa.xhtml");
				} else {
					medico.setIdMedico(Integer.parseInt(idMedico));
					this.medicoDao.alterar(medico);
					ec.redirect(ec.getRequestContextPath() + "/medico/medicoPesquisa.xhtml");
				}
			}
		} catch (Exception e) {
			return e.getMessage();
		}

		return validacao;
	}

	public void recuperaMedico() throws NumberFormatException, SQLException {
		if (idMedico == null) {
			return;
		}

		Medico medico = new Medico();

		medico = this.medicoDao.recuperaMedico(Integer.parseInt(idMedico));

		nome = medico.getNome();
		email = medico.getEmail();
		crm = medico.getCrm();
		especialidade = medico.getEspecialidade();
		telefone = medico.getTelefone();
	}

	public void excluiMedico() throws NumberFormatException, SQLException, IOException {
		this.medicoDao.deletar(Integer.parseInt(idMedico));

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/medico/medicoPesquisa.xhtml");
	}

	public void pesquisaMedico() throws SQLException {
		Medico medico = new Medico();

		List<Medico> medicoLista = new ArrayList<Medico>();

		medico.setNome(nome);
		medico.setEspecialidade(especialidade);

		medicoLista = this.medicoDao.pesquisar(medico);

		this.listaMedico = medicoLista;
	}
	
	public void direcionaPagina() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/medico/medicoFormulario.xhtml");
	}

	public String getTituloPagina() {
		if (idMedico == null || idMedico == "") {
			return "Cadastrar Médico";
		}
		return "Alterar Médico";
	}

	public void deixaNulo() {
		setIdMedico(null);
		setNome(null);
		setTelefone(null);
		setEmail(null);
		setCrm(null);
		setEspecialidade(null);
		setValidacao(null);
	}

}

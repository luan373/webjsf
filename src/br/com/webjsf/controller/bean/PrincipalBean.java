package br.com.webjsf.controller.bean;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.webjsf.model.dao.PrincipalDao;

@ManagedBean(name = "principalBean")
public class PrincipalBean {

	private PrincipalDao principalDao = null;

	public PrincipalBean() {
		this.principalDao = new PrincipalDao();
	}

	public String getNotificacao() throws SQLException {
		int nrNotificacao = this.principalDao.notificacao();
		if (nrNotificacao == 0) {
			return "Não há consulta agendadas para hoje";
		}
		if (nrNotificacao == 1) {
			return "Há " + nrNotificacao + " consulta agendada para hoje";
		} else {
			return "Há " + nrNotificacao + " consultas agendadas para hoje";
		}
	}
}

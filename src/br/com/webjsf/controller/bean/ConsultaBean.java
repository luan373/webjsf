package br.com.webjsf.controller.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.webjsf.controller.util.UtilComum;
import br.com.webjsf.model.dao.ConsultaDao;
import br.com.webjsf.model.entity.Consulta;
import br.com.webjsf.model.entity.Medico;
import br.com.webjsf.model.entity.Paciente;

@ManagedBean(name = "consultaBean")
@ViewScoped
public class ConsultaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3308412338640094495L;

	private ConsultaDao consultaDao = null;

	public ConsultaBean() {
		this.consultaDao = new ConsultaDao();
	}

	private ScheduleModel eventModel;

	private Consulta consulta;
	private UtilComum utilComum;
	private List<Consulta> listaConsulta;
	private List<Medico> medicos;
	private List<Paciente> pacientes;

	String pacienteSelecionado;
	String statusSelecionado;
	String medicoSelecionado;

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Consulta> getListaConsulta() {
		return listaConsulta;
	}

	public void setListaConsulta(List<Consulta> listaConsulta) {
		this.listaConsulta = listaConsulta;
	}

	@PostConstruct
	public void inicializar() {
		consulta = new Consulta();

		eventModel = new DefaultScheduleModel();

		try {
			listaConsulta = this.consultaDao.todasConsultas();
		} catch (SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro no sql."));
		}

		for (Consulta con : listaConsulta) {
			DefaultScheduleEvent cvt = new DefaultScheduleEvent();

			
			try {
				cvt.setEndDate(con.getDataConsulta());
				cvt.setStartDate(con.getDataConsulta());
				cvt.setData(con.getIdConsulta());
				String nomePaciente = consultaDao.recuperaPaciente(con.getIdPaciente());
				cvt.setTitle(nomePaciente);
				
				if(con.getStatus().equals("Agendada")){
					cvt.setStyleClass("emp1");
				} else{
					cvt.setStyleClass("emp2");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

			eventModel.addEvent(cvt);
		}
	}

	public void quandoSelecionado(SelectEvent selectEvent) {
		ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

		for (Consulta con : listaConsulta) {
			if (con.getIdConsulta() == (Long) event.getData()) {
				consulta = con;
				break;
			}
		}
	}

	public void quandoNovo(SelectEvent selectEvent) {
		ScheduleEvent event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());

		consulta = new Consulta();
		consulta.setDataConsulta(new java.sql.Date(event.getStartDate().getTime()));

	}

	public void salvar() throws SQLException {
		if (consulta.getIdConsulta() == 0) {
			this.consultaDao.salvar(consulta);
			inicializar();

			consulta = new Consulta();
		} else {
			this.consultaDao.atualizar(consulta);
			inicializar();
		}

	}

	public void quandoMovido(ScheduleEntryMoveEvent event) throws SQLException {
		List<Consulta> listaConsulta1;
		listaConsulta1 = this.consultaDao.todasConsultas();
		
		for (Consulta con : listaConsulta1) {
			if (con.getIdConsulta() == (Long) event.getScheduleEvent().getData()) {
				
				Calendar c = Calendar.getInstance(); 
				c.setTime(con.getDataConsulta()); 
				c.add(Calendar.DATE, event.getDayDelta());
				//c.getTime();
							
				con.setDataConsulta(c.getTime());
				
				consulta = con;
				try {
					this.consultaDao.atualizar(con);
					inicializar();
				} catch (SQLException e) {
					e.printStackTrace();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao salvar evento", "Erro: " + e.getMessage()));
				}
				break;
			}
		}
	}

	public void quandoRedimensionado(ScheduleEntryResizeEvent event) throws SQLException {
		for (Consulta con : listaConsulta) {
			if (con.getIdConsulta() == (Long) event.getScheduleEvent().getData()) {
				consulta = con;

				this.consultaDao.atualizar(consulta);
				inicializar();
				break;
			}

		}
	}

	/*
	 * Aqui vem os médotos de agendamento e recuperação de Pacientes e Médicos
	 */

	public List<Medico> onRecuperaMedico() throws SQLException {
		List<Medico> medicoLista = this.consultaDao.recuperaMedicos();

		return medicoLista;
	}

	public List<Paciente> onRecuperaPaciente() throws SQLException {
		List<Paciente> pacienteLista = this.consultaDao.recuperaPacientes();

		return pacienteLista;
	}

}
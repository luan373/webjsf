package br.com.webjsf.model.entity;

import java.util.Date;

public class Consulta {
	private long idConsulta;
	private long idMedico;
	private long idPaciente;
	private String status;
	private Date dataConsulta;
	private Date horaInicioConsulta;
	private Date horaFimConsulta;

	public long getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(long idConsulta) {
		this.idConsulta = idConsulta;
	}

	public long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(long idMedico) {
		this.idMedico = idMedico;
	}

	public long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Date getHoraInicioConsulta() {
		return horaInicioConsulta;
	}

	public void setHoraInicioConsulta(Date horaInicioConsulta) {
		this.horaInicioConsulta = horaInicioConsulta;
	}

	public Date getHoraFimConsulta() {
		return horaFimConsulta;
	}

	public void setHoraFimConsulta(Date horaFimConsulta) {
		this.horaFimConsulta = horaFimConsulta;
	}

	@Override
	public String toString() {
		return "Consulta [idConsulta=" + idConsulta + ", idMedico=" + idMedico + ", idPaciente=" + idPaciente
				+ ", status=" + status + ", dataConsulta=" + dataConsulta + ", horaInicioConsulta=" + horaInicioConsulta
				+ ", horaFimConsulta=" + horaFimConsulta + "]";
	}

}

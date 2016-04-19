package br.com.webjsf.controller.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import br.com.webjsf.model.dao.MedicoDao;
import br.com.webjsf.model.dao.PacienteDao;
import br.com.webjsf.model.entity.Medico;
import br.com.webjsf.model.entity.Paciente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/*import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;*/

@ManagedBean(name = "exportBean")
public class ExportBean {

	Paciente paciente = null;
	Medico medico = null;

	@ManagedProperty(value = "#{paciente.idPaciente}")
	private String idPaciente;
	private String idMedico;

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(String idMedico) {
		this.idMedico = idMedico;
	}

	public String exportarPDF() throws NumberFormatException, SQLException, JRException {
		if (idPaciente != "") {
			paciente = new Paciente();
			PacienteDao negocio = new PacienteDao();
			List<Paciente> lista = new ArrayList<Paciente>();

			paciente = negocio.recuperaPaciente(Integer.parseInt(idPaciente));

			lista.add(paciente);

			

			/*
			 * JasperReport report =
			 * JasperCompileManager.compileReport("pdf/paciente.jrxml");
			 * 
			 * JasperPrint print = JasperFillManager.fillReport(report, null,
			 * new JRBeanCollectionDataSource(lista));
			 * 
			 * JasperExportManager.exportReportToPdfFile(print,
			 * "pdf/RelatorioPaciente.pdf");
			 */
		} else {
			medico = new Medico();
			MedicoDao negocio = new MedicoDao();
			List<Medico> lista = new ArrayList<Medico>();

			medico = negocio.recuperaMedico(Integer.parseInt(idMedico));

			JasperReport report = JasperCompileManager.compileReport("pdf/medico.jrxml");

			JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));

			JasperExportManager.exportReportToPdfFile(print, "pdf/RelatorioMedico.pdf");
		}

		return null;
	}
}

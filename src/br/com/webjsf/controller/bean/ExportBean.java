package br.com.webjsf.controller.bean;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.webjsf.model.dao.MedicoDao;
import br.com.webjsf.model.dao.PacienteDao;
import br.com.webjsf.model.entity.Medico;
import br.com.webjsf.model.entity.Paciente;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

@ManagedBean(name = "exportBean")
@ViewScoped
public class ExportBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8433925447829372426L;

	Paciente paciente = null;
	Medico medico = null;

	@ManagedProperty(value = "#{paciente.idPaciente}")
	private String idPaciente;
	private String idMedico;

	private String caminhoRelatorio;
	private FacesContext context;

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

	public void exportarPDF(int id, String entidade) throws NumberFormatException, SQLException, JRException, IOException {
		String caminho;
		
		if (entidade.equals("paciente")) {
			paciente = new Paciente();
			PacienteDao negocio = new PacienteDao();
			List<Paciente> lista = new ArrayList<Paciente>();
			
			caminho = "/jasper/paciente.jrxml";
			
			paciente = negocio.recuperaPaciente(id);
			lista.add(paciente);
			
			geraPDF(lista, caminho);
			
		} else {
			medico = new Medico();
			MedicoDao negocio = new MedicoDao();
			List<Medico> lista = new ArrayList<Medico>();
			
			caminho = "/jasper/medico.jrxml";
			
			medico = negocio.recuperaMedico(id);	
			lista.add(medico);
			
			geraPDF(lista, caminho);
		}
			
					
	}
	
	@SuppressWarnings("rawtypes")
	public void geraPDF(List lista, String caminho) throws IOException, JRException{
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();   
	    ServletOutputStream servletOutputStream = response.getOutputStream();
	    
	   
	    context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
	    caminhoRelatorio = servletContext.getRealPath(caminho);
	    
		JasperReport report = JasperCompileManager.compileReport(caminhoRelatorio);
				 
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
				 
	    byte[] bytes = JasperExportManager.exportReportToPdf(print);	    
				    
	    response.setContentType("application/octet-stream");
	    response.setHeader("Content-disposition", "filename=\"exportacao.pdf\""); //nome que vc quer dar ao arquivo
	    response.setContentLength(bytes.length);

	    //sem essas linhas abaixo não funciona, não roda,  da pau hehehe
	    servletOutputStream.write(bytes, 0, bytes.length);
	    servletOutputStream.flush();
	    servletOutputStream.close();
	    FacesContext.getCurrentInstance().renderResponse();
	    FacesContext.getCurrentInstance().responseComplete();
	}

}

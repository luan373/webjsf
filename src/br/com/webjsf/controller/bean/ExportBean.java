package br.com.webjsf.controller.bean;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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

	public String exportarPDF() throws NumberFormatException, SQLException, JRException, IOException {
		if (idPaciente != "") {
			paciente = new Paciente();
			PacienteDao negocio = new PacienteDao();
			List<Paciente> lista = new ArrayList<Paciente>();

			paciente = negocio.recuperaPaciente(Integer.parseInt(idPaciente));
			lista.add(paciente);

			String caminho = "/jasper/paciente.jrxml";
			context = FacesContext.getCurrentInstance();
			ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
			caminhoRelatorio = servletContext.getRealPath(caminho);
			String nomeArquivo = "todosClientes";
			enviarPdf(lista, nomeArquivo);

			/*
			 * try {
			 * 
			 * // variaveis de contexto da aplicação
			 * 
			 * FacesContext fc = FacesContext.getCurrentInstance();
			 * ExternalContext ec = fc.getExternalContext(); ServletContext
			 * servletContext = (ServletContext) ec.getContext(); // recupera o
			 * jasper String caminhoArquivoJasper =
			 * servletContext.getRealPath("") + "\\Jasper\\paciente.jasper"; //
			 * sendo que é a pasta WebContent entao fica...
			 * WebContent\Jasper\paciente.jasper ec.responseReset(); // prepara
			 * a exportacao ec.responseReset(); // Reseta todo o conteudo que
			 * seria enviado (todo html prontoserá apagado, pq agora eh pdf)
			 * 
			 * ec.setResponseContentType("application/pdf"); // seta o tipo de
			 * mime retornado
			 * 
			 * String fileName = "Impresso.pdf";
			 * ec.setResponseHeader("Content-Disposition",
			 * "attachment; filename=\"" + fileName + "\""); // definindo o nome
			 * do arquivo que será baixado.
			 * 
			 * OutputStream output = ec.getResponseOutputStream();
			 * 
			 * JRBeanCollectionDataSource jrds = new
			 * JRBeanCollectionDataSource(lista); JasperPrint printer =
			 * JasperFillManager.fillReport(caminhoArquivoJasper, null, jrds);
			 * 
			 * // exportanto para o output
			 * JasperExportManager.exportReportToPdfStream(printer, output);
			 * 
			 * fc.responseComplete(); } catch (Exception e) { e.getMessage();
			 * System.out.println(e); }
			 */

			/*
			 * JasperReport report =
			 * JasperCompileManager.compileReport("jasper/paciente.jrxml");
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

	@SuppressWarnings("deprecation")
	private void enviarPdf(List<Paciente> lista, String nomeArquivo) {
		// Carrega o xml de definição do relatório
		try {
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			// Configura o response para suportar o relatório

			response.setContentType(nomeArquivo + "/pdf");

			response.addHeader("Content-disposition", "attachment; filename=\"" + nomeArquivo + ".pdf\"");

			// Exporta o relatório
			JasperReport report = JasperCompileManager.compileReport(caminhoRelatorio);
			JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));

			JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
			// Salva o estado da aplicação no contexto do JSF
			
			context.getApplication().getStateManager().saveView(context);
			// Fecha o stream do response
			context.responseComplete();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

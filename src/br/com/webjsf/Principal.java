package br.com.webjsf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.webjsf.model.entity.Paciente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Principal {
	public static void main(String[] args) throws SQLException, JRException {
		System.out.println("Gerando Relatório...");

		List<Paciente> lista = new ArrayList<Paciente>();

		Paciente paciente = new Paciente();

		Date date = new Date();

		paciente.setDiagnostico("Dodói");
		paciente.setEmail("luan@gmail.com");
		paciente.setIdPaciente(2);
		paciente.setNome("Luan");
		paciente.setRegistro(date);
		paciente.setTelefone("33736517");

		lista.add(paciente);

		JasperReport report = JasperCompileManager.compileReport("pdf/paciente.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));

		// exportacao do relatorio para outro formato, no caso PDF
		JasperExportManager.exportReportToPdfFile(print, "pdf/RelatorioClientes.pdf");

		System.out.println("Relatório gerado.");
	}
}

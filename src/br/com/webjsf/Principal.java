package br.com.webjsf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import br.com.webjsf.model.dao.ConsultaDao;
import br.com.webjsf.model.dao.PrincipalDao;
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
		PrincipalDao principalDao = new PrincipalDao();
		
		int oi = principalDao.notificacao();
		System.out.println(oi);
	}
}

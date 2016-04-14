package br.com.webjsf.controller.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class UtilComum {
	public String dataAtualFormatada() {
		Date data = new Date(System.currentTimeMillis());
		String dt = new SimpleDateFormat("dd/MM/yyyy").format(data);

		return dt;
	}
	
	public Date dataAtualCompleta() {
		Date data = new Date(System.currentTimeMillis());
		return data;
	}
}

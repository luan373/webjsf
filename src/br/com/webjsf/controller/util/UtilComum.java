package br.com.webjsf.controller.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	public static java.sql.Time recuperaSqlTimestamp(Date data) {
		return new java.sql.Time(data.getTime());
	}
	
	public Date dataLegal (Date date, int dateDelta){
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, dateDelta);	
		
		return c.getTime();
		
	}
	
}

package br.com.webjsf;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Principal {
	public static void main(String[] args) throws SQLException {
		Date data = new Date(System.currentTimeMillis());
		String dt = new SimpleDateFormat("dd/MM/yyyy").format(data);
		System.out.println(dt);
	}

}

package br.com.webjsf.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class AbastractDao {
	protected Connection conexao = null;
	protected PreparedStatement comandoSql = null;
	protected ResultSet resultadoSql = null;

	protected void configuraConexao() {
		try {
			Class.forName("org.postgresql.Driver");
			this.conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/marc_consulta", "postgres", "9903");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

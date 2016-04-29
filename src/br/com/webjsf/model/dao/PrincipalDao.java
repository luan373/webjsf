package br.com.webjsf.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrincipalDao extends AbastractDao {

	public PrincipalDao() {
		super.configuraConexao();
	}

	public Integer notificacao() throws SQLException {
		this.comandoSql = conexao.prepareStatement(
				"SELECT COUNT(data_consulta) FROM public.consulta where data_consulta = CURRENT_DATE;");

		ResultSet resultSet = comandoSql.executeQuery();
		while (resultSet.next()) {
			int notificacao = resultSet.getInt("count");
			return notificacao;
		}
		return 0;
	}
}

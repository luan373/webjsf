package br.com.webjsf.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.webjsf.controller.util.UtilComum;
import br.com.webjsf.model.entity.Consulta;
import br.com.webjsf.model.entity.Medico;
import br.com.webjsf.model.entity.Paciente;

public class ConsultaDao extends AbastractDao {

	public ConsultaDao() {
		super.configuraConexao();
	}

	public List<Medico> recuperaMedicos() throws SQLException {
		List<Medico> listaRetorno = new ArrayList<Medico>();

		Medico medicoRetorno = null;

		this.comandoSql = conexao.prepareStatement("SELECT id_medico, nome FROM public.medico;");

		ResultSet resultadoSql = comandoSql.executeQuery();

		while (resultadoSql.next()) {
			medicoRetorno = new Medico();

			medicoRetorno.setIdMedico(resultadoSql.getLong("id_medico"));
			medicoRetorno.setNome(resultadoSql.getString("nome"));

			listaRetorno.add(medicoRetorno);
		}

		return listaRetorno;
	}

	public List<Paciente> recuperaPacientes() throws SQLException {
		List<Paciente> listaRetorno = new ArrayList<Paciente>();

		Paciente pacienteRetorno = null;

		this.comandoSql = conexao.prepareStatement("SELECT id_paciente, nome FROM public.paciente;");
		ResultSet resutladoSql = comandoSql.executeQuery();

		while (resutladoSql.next()) {
			pacienteRetorno = new Paciente();

			pacienteRetorno.setIdPaciente(resutladoSql.getLong("id_paciente"));
			pacienteRetorno.setNome(resutladoSql.getString("nome"));

			listaRetorno.add(pacienteRetorno);
		}

		return listaRetorno;
	}

	public void salvar(Consulta consulta) throws SQLException {
		String sql = "INSERT INTO public.consulta(id_medico, id_paciente, data_consulta, hora_inicio_consulta, hora_fim_consulta, status) VALUES (?, ?, ?, ?, ?, ?);";

		comandoSql = conexao.prepareStatement(sql);

		comandoSql.setLong(1, consulta.getIdMedico());
		comandoSql.setLong(2, consulta.getIdPaciente());
		comandoSql.setDate(3, new java.sql.Date(consulta.getDataConsulta().getTime()));
		
		
		comandoSql.setTime(4, UtilComum.recuperaSqlTimestamp(consulta.getHoraInicioConsulta()));
		comandoSql.setTime(5, UtilComum.recuperaSqlTimestamp(consulta.getHoraFimConsulta()));
		
		comandoSql.setString(6, consulta.getStatus());

		comandoSql.execute();

	}

	public void atualizar(Consulta consulta) throws SQLException {
		String sql = "UPDATE public.consulta SET id_medico=?, id_paciente=?, data_consulta=?, hora_inicio_consulta=?, hora_fim_consulta=?, status=? WHERE id_consulta=?;";

		comandoSql = conexao.prepareStatement(sql);

		comandoSql.setLong(1, consulta.getIdMedico());
		comandoSql.setLong(2, consulta.getIdPaciente());
		comandoSql.setDate(3, new java.sql.Date(consulta.getDataConsulta().getTime()));
		comandoSql.setTime(4, UtilComum.recuperaSqlTimestamp(consulta.getHoraInicioConsulta()));
		comandoSql.setTime(5, UtilComum.recuperaSqlTimestamp(consulta.getHoraFimConsulta()));
		comandoSql.setString(6, consulta.getStatus());
		comandoSql.setLong(7, consulta.getIdConsulta());

		comandoSql.execute();

	}

	public List<Consulta> todasConsultas() throws SQLException {
		List<Consulta> consultas = new ArrayList<Consulta>();

		String sql = "SELECT * FROM public.consulta;";

		comandoSql = conexao.prepareStatement(sql);

		ResultSet rs = comandoSql.executeQuery();

		while (rs.next()) {
			Consulta c = new Consulta();

			c.setIdConsulta(rs.getLong(1));
			c.setIdMedico(rs.getLong(2));
			c.setIdPaciente(rs.getLong(3));
			c.setDataConsulta(rs.getDate(4));
			c.setHoraInicioConsulta(rs.getTime(5));
			c.setHoraFimConsulta(rs.getTime(6));
			c.setStatus(rs.getString(7));

			consultas.add(c);
		}

		return consultas;
		
	}

	public String recuperaPaciente(long l) throws SQLException {
		String sql = "SELECT nome FROM public.paciente where id_paciente = ?;";
		
		comandoSql = conexao.prepareStatement(sql);
		
		comandoSql.setLong(1, l);
		
		ResultSet resultSet = comandoSql.executeQuery();
		
		while(resultSet.next()){
		String nomePaciente = resultSet.getString("nome");		
		return nomePaciente;
		}
		
		return null;
		
	}
}

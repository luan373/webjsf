package br.com.webjsf.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.webjsf.controller.util.UtilComum;
import br.com.webjsf.model.entity.Paciente;

public class PacienteDao extends AbastractDao {
	public PacienteDao() {
		super.configuraConexao();
	}

	UtilComum utilComum = new UtilComum();

	public void inserir(Paciente paciente) throws SQLException {
		comandoSql = this.conexao.prepareStatement(
				"INSERT INTO public.paciente(nome, telefone, email, diagnostico, registro)    VALUES ('"
						+ paciente.getNome() + "', " + paciente.getTelefone() + ", '" + paciente.getEmail() + "', '"
						+ paciente.getDiagnostico() + "', '" + utilComum.dataAtualCompleta() + "');");

		comandoSql.execute();
	}

	public void alterar(Paciente paciente) throws SQLException {
		comandoSql = this.conexao.prepareStatement("UPDATE public.paciente SET nome='" + paciente.getNome()
				+ "', telefone=" + paciente.getTelefone() + " , email='" + paciente.getEmail() + "', diagnostico= '"
				+ paciente.getDiagnostico() + "' WHERE " + paciente.getIdPaciente() + ";");

		comandoSql.execute();
	}

	public void deletar(int idPaciente) throws SQLException {
		comandoSql = this.conexao.prepareStatement("DELETE FROM public.paciente WHERE " + idPaciente + ";");

		comandoSql.execute();
	}

	public Paciente recuperaPaciente(int idPaciente) throws SQLException {
		Paciente pacienteRetorno = null;

		this.comandoSql = conexao.prepareStatement(
				"SELECT id_paciente, nome, telefone, email, diagnostico, registro FROM public.paciente WHERE id_paciente = "
						+ idPaciente + " ;");
		ResultSet resutladoSql = comandoSql.executeQuery();

		while (resutladoSql.next()) {
			pacienteRetorno = new Paciente();

			pacienteRetorno.setIdPaciente(resutladoSql.getLong("id_paciente"));
			pacienteRetorno.setNome(resutladoSql.getString("nome"));
			pacienteRetorno.setTelefone(resutladoSql.getInt("telefone"));
			pacienteRetorno.setEmail(resutladoSql.getString("email"));
			pacienteRetorno.setDiagnostico(resutladoSql.getString("diagnostico"));
			pacienteRetorno.setRegistro(resutladoSql.getDate("registro"));
		}

		return pacienteRetorno;
	}

	public List<Paciente> pesquisar(Paciente pacienteCriterio) throws SQLException {
		List<Paciente> listaRetorno = new ArrayList<Paciente>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT id_paciente, nome, telefone, email, diagnostico, registro FROM public.paciente where");

		if (pacienteCriterio.getNome() != null && !pacienteCriterio.getNome().equals("")) {
			sql.append(" nome = '" + pacienteCriterio.getNome() + "' AND ");
		}

		if (pacienteCriterio.getEmail() != null && !pacienteCriterio.getEmail().equals("")) {
			sql.append(" email = '" + pacienteCriterio.getEmail() + "' AND ");
		}

		sql.append(" 1 = 1 ");

		this.comandoSql = conexao.prepareStatement(sql.toString());
		ResultSet resultadoSql = comandoSql.executeQuery();

		while (resultadoSql.next()) {
			Paciente pacienteRetorno = new Paciente();

			pacienteRetorno.setIdPaciente(resultadoSql.getLong(1));
			pacienteRetorno.setNome(resultadoSql.getString(2));
			pacienteRetorno.setTelefone(resultadoSql.getInt(3));
			pacienteRetorno.setEmail(resultadoSql.getString(4));
			pacienteRetorno.setDiagnostico(resultadoSql.getString(5));
			pacienteRetorno.setRegistro(resultadoSql.getDate(6));

			listaRetorno.add(pacienteRetorno);
		}

		return listaRetorno;

	}
}

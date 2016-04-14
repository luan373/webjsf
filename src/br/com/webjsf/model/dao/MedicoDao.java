package br.com.webjsf.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.webjsf.model.entity.Medico;

public class MedicoDao extends AbastractDao {
	public MedicoDao() {
		super.configuraConexao();
	}

	public void inserir(Medico medico) throws SQLException {
		comandoSql = this.conexao
				.prepareStatement("INSERT INTO public.medico(nome, crm, especialidade, email, telefone) VALUES ('"
						+ medico.getNome() + "', '" + medico.getCrm() + "', '" + medico.getEspecialidade() + "', '"
						+ medico.getEmail() + "', " + medico.getTelefone() + ");");

		comandoSql.execute();
	}

	public void alterar(Medico medico) throws SQLException {
		comandoSql = this.conexao.prepareStatement("UPDATE public.medico SET nome='" + medico.getNome() + "', crm='"
				+ medico.getCrm() + "', especialidade='" + medico.getEspecialidade() + "', email='" + medico.getEmail()
				+ "', telefone=" + medico.getTelefone() + " WHERE id_medico = " + medico.getIdMedico() + " ;");

		comandoSql.execute();
	}

	public void deletar(int idMedico) throws SQLException {
		comandoSql = this.conexao.prepareStatement("DELETE FROM public.medico WHERE id_medico = " + idMedico + ";");

		comandoSql.execute();
	}

	public Medico recuperaMedico(int idMedico) throws SQLException {
		Medico medicoRetorno = null;

		this.comandoSql = conexao.prepareStatement(
				"SELECT id_medico, nome, crm, especialidade, email, telefone FROM public.medico WHERE id_medico = "
						+ idMedico + " ;");

		ResultSet resultadoSql = comandoSql.executeQuery();

		while (resultadoSql.next()) {
			medicoRetorno = new Medico();

			medicoRetorno.setIdMedico(resultadoSql.getLong("id_medico"));
			medicoRetorno.setNome(resultadoSql.getString("nome"));
			medicoRetorno.setCrm(resultadoSql.getString("crm"));
			medicoRetorno.setEspecialidade(resultadoSql.getString("especialidade"));
			medicoRetorno.setEmail(resultadoSql.getString("email"));
			medicoRetorno.setTelefone(resultadoSql.getInt("telefone"));
		}

		return medicoRetorno;
	}
	
	public List<Medico> pesquisar(Medico medicoCriterio) throws SQLException {
		List<Medico> listaRetorno = new ArrayList<Medico>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT id_medico, nome, crm, especialidade, email, telefone FROM public.medico where");
		
		if (medicoCriterio.getNome() != null && !medicoCriterio.getNome().equals("")){
			sql.append(" nome  = '" + medicoCriterio.getNome() + "' AND ");
		}
		
		if(medicoCriterio.getEspecialidade() != null && !medicoCriterio.getEspecialidade().equals("")){
			sql.append(" especialidade = '" + medicoCriterio.getEspecialidade() + "' AND ");
		}
		
		sql.append(" 1 = 1 ");
		
		this.comandoSql = conexao.prepareStatement(sql.toString());
		ResultSet resultadoSql = comandoSql.executeQuery();
		
		while (resultadoSql.next()){
			Medico medicoRetorno = new Medico();
			
			medicoRetorno.setIdMedico(resultadoSql.getLong("idMedico"));
			medicoRetorno.setNome(resultadoSql.getString("nome"));
			medicoRetorno.setCrm(resultadoSql.getString("crm"));
			medicoRetorno.setEspecialidade(resultadoSql.getString("especialidade"));
			medicoRetorno.setEmail(resultadoSql.getString("email"));
			medicoRetorno.setTelefone(resultadoSql.getInt("telefone"));
			
			listaRetorno.add(medicoRetorno);
		}
		
		return listaRetorno;		
	}
}

package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.farmacia.domain.Fornecedor;
import br.com.farmacia.factory.ConexaoBD;

public class FornecedorDAO {
	public void salvar(Fornecedor f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao)");
		sql.append("VALUES (?)");
		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}
	
	public void excluir(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ?");
		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
	}

	public void editar(Fornecedor f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ?");
		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
	}
	
	public Fornecedor buscaPorCodigo(Fornecedor f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		ResultSet resultado = comando.executeQuery();
		Fornecedor fornecedor = null;
		if(resultado.next()){
			fornecedor = new Fornecedor();
			fornecedor.setCodigo(resultado.getInt("codigo"));
			fornecedor.setDescricao(resultado.getString("descricao"));
		}
		return fornecedor;
	}
	
	public ArrayList<Fornecedor> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC");

		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedor> lista = new ArrayList<Fornecedor>();
		Fornecedor fornecedor = null;
		while(resultado.next()){
			fornecedor = new Fornecedor();
			fornecedor.setCodigo(resultado.getInt("codigo"));
			fornecedor.setDescricao(resultado.getString("descricao"));
			lista.add(fornecedor);
		}
		return lista;
	}
	
	public ArrayList<Fornecedor> buscarPorDescricao(Fornecedor f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");

		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%"+f.getDescricao()+"%");

		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedor> lista = new ArrayList<Fornecedor>();
		Fornecedor fornecedor = null;
		while(resultado.next()){
			fornecedor = new Fornecedor();
			fornecedor.setCodigo(resultado.getInt("codigo"));
			fornecedor.setDescricao(resultado.getString("descricao"));
			lista.add(fornecedor);
		}
		return lista;
	}
}

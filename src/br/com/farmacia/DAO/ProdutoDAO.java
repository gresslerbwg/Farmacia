package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedor;
import br.com.farmacia.domain.Produto;
import br.com.farmacia.factory.ConexaoBD;

public class ProdutoDAO {
	public void salvar(Produto p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos ");
		sql.append("(descricao, preco, quantidade, fornecedores_codigo)");
		sql.append("VALUES (?,?,?,?)");
		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setInt(3, p.getQuantidade());
		comando.setInt(4, p.getFornecedor().getCodigo());
		comando.executeUpdate();
	}
	
	public void excluir(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ?");
		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		comando.executeUpdate();
	}
	
	public void editar(Produto p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo = ? ");
		sql.append("WHERE codigo = ?");
		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFornecedor().getCodigo());
		comando.setLong(5, p.getCodigo());
		comando.executeUpdate();
	}
	
	public Produto buscaPorCodigo(Produto p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, preco, quantidade, fornecedores_codigo ");
		sql.append("FROM produtos ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());

		ResultSet resultado = comando.executeQuery();
		Produto produto = null;
		if(resultado.next()){
			produto = new Produto();
			produto.setCodigo(resultado.getInt("codigo"));
			produto.setDescricao(resultado.getString("descricao"));
			produto.setQuantidade(resultado.getInt("quantidade"));
			produto.setPreco(resultado.getDouble("preco"));
			Fornecedor f = new Fornecedor();
			f.setCodigo(resultado.getInt("fornecedores_codigo"));
			produto.setFornecedor(f);
		}
		return produto;
	}
	
	public ArrayList<Produto> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo as pcodigo, p.descricao as pdescricao, p.preco, p.quantidade, f.codigo as fcodigo, f.descricao as fdescricao ");
		sql.append("FROM produtos p ");
		sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo");

		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();
		ArrayList<Produto> lista = new ArrayList<Produto>();
		Produto produto = null;
		Fornecedor fornecedor = null; 
		while(resultado.next()){
			produto = new Produto();
			produto.setCodigo(resultado.getInt("pcodigo"));
			produto.setDescricao(resultado.getString("pdescricao"));
			produto.setQuantidade(resultado.getInt("quantidade"));
			produto.setPreco(resultado.getDouble("preco"));
			fornecedor = new Fornecedor();
			fornecedor.setCodigo(resultado.getInt("fcodigo"));
			fornecedor.setDescricao(resultado.getString("fdescricao"));
			produto.setFornecedor(fornecedor);
			lista.add(produto);
		}
		return lista;
	}
	
	public ArrayList<Produto> buscarPorDescricao(Produto p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, preco, quantidade, fornecedores_codigo ");
		sql.append("FROM produtos ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");

		Connection conexao = ConexaoBD.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%"+p.getDescricao()+"%");

		ResultSet resultado = comando.executeQuery();
		ArrayList<Produto> lista = new ArrayList<Produto>();
		Produto produto = null;
		while(resultado.next()){
			produto = new Produto();
			produto.setCodigo(resultado.getInt("codigo"));
			produto.setDescricao(resultado.getString("descricao"));
			produto.setQuantidade(resultado.getInt("quantidade"));
			produto.setPreco(resultado.getDouble("preco"));
			Fornecedor f = new Fornecedor();
			f.setCodigo(resultado.getInt("fornecedores_codigo"));
			produto.setFornecedor(f);
			lista.add(produto);
		}
		return lista;
	}
}

package br.com.farmacia.teste;

import br.com.farmacia.domain.Produto;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedor;

public class ProdutoDAOTeste {
	
	@Test
	@Ignore
	public void TesteSalvar() throws SQLException{
		Produto p1 = new Produto();
		p1.setDescricao("Dipirona");
		p1.setPreco(2.99);
		p1.setQuantidade(50);
		Fornecedor f = new Fornecedor();
		f.setCodigo(8);
		p1.setFornecedor(f);
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.salvar(p1);
	}
	
	@Test
	@Ignore
	public void TesteListar() throws SQLException{
		ArrayList<Produto> lista = null;
		ProdutoDAO pdao = new ProdutoDAO();
		lista = pdao.listar();
		assertEquals(true, lista!=null);
	}
	
	@Test
	@Ignore
	public void TesteExcluir() throws SQLException{
		Produto p = new Produto();
		p.setCodigo(7);
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.excluir(p);
	}
	
	@Test
	@Ignore
	public void TesteEditar() throws SQLException{
		Produto p = new Produto();
		Fornecedor f = new Fornecedor();
		f.setCodigo(8);
		p.setCodigo(6);
		p.setDescricao("Paracetamol");
		p.setQuantidade(20);
		p.setPreco(3.5);
		p.setFornecedor(f);
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.editar(p);
	}
}

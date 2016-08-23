package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedorDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedor;
import br.com.farmacia.domain.Produto;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name="MBProduto")
@ViewScoped
public class ProdutoBean {
	private Produto produto;
	private ArrayList<Produto> produtos;
	private ArrayList<Produto> produtosFiltrado;
	private ArrayList<Fornecedor> comboFornecedor;
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setProdutos(ArrayList<Produto>produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutosFiltrado(ArrayList<Produto> produtosFiltrado) {
		this.produtosFiltrado = produtosFiltrado;
	}
	
	public ArrayList<Produto> getProdutosFiltrado() {
		return produtosFiltrado;
	}
	
	public ArrayList<Fornecedor> getComboFornecedor() {
		return comboFornecedor;
	}

	public void setComboFornecedor(ArrayList<Fornecedor> comboFornecedor) {
		this.comboFornecedor = comboFornecedor;
	}

	@PostConstruct
	public void preparaPesquisa(){
		ProdutoDAO pdao = new ProdutoDAO();
		try {
			produtos =  pdao.listar();
		} catch (SQLException e) {
			JSFUtil.addMsgErro("Não foi possível listar os produtos!");
			e.printStackTrace();
		}
	}
	
	public void preparaNovo(){
		produto = new Produto();
		FornecedorDAO fdao = new FornecedorDAO();
		try {
			comboFornecedor = fdao.listar();
		} catch (SQLException e) {
			JSFUtil.addMsgErro("Não foi possível listar os fornecedores!");
			e.printStackTrace();
		}
	}
	
	public void novo() throws SQLException{
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.salvar(produto);
		produtos =  pdao.listar();
		JSFUtil.addMsgSucesso("Registro adicionado com sucesso!");
	}
	
	public void excluir(){
		try{
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.excluir(produto);
			produtos = fdao.listar();
			JSFUtil.addMsgSucesso("Registro excluído com sucesso!");
		}catch(SQLException e){
			JSFUtil.addMsgErro("Registro não foi excluído! Verifique se não há produtos associados!");
			e.printStackTrace();
		}
	}
}

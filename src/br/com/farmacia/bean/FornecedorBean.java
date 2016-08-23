package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedorDAO;
import br.com.farmacia.domain.Fornecedor;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name="MBFornecedor")
@ViewScoped
public class FornecedorBean {
	
	private Fornecedor fornecedor;
	private ArrayList<Fornecedor> fornecedores;
	private ArrayList<Fornecedor> fornecedoresFiltrado;
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public ArrayList<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public void setFornecedoresFiltrado(ArrayList<Fornecedor> fornecedoresFiltrado) {
		this.fornecedoresFiltrado = fornecedoresFiltrado;
	}
	
	public ArrayList<Fornecedor> getFornecedoresFiltrado() {
		return fornecedoresFiltrado;
	}
	
	@PostConstruct
	public void preparaPesquisa(){
		try {
			FornecedorDAO fdao = new FornecedorDAO();
			fornecedores =  fdao.listar();
		} catch (SQLException e) {
			JSFUtil.addMsgErro("Não foi possível listar os fornecedores!");
			e.printStackTrace();
		}
	}
	
	public void preparaNovo(){
		fornecedor = new Fornecedor();
	}

	public void novo() throws SQLException{
		FornecedorDAO fdao = new FornecedorDAO();
		fdao.salvar(fornecedor);
		fornecedores =  fdao.listar();
		JSFUtil.addMsgSucesso("Registro adicionado com sucesso!");
	}
	
	public void excluir(){
		try{
			FornecedorDAO fdao = new FornecedorDAO();
			fdao.excluir(fornecedor);
			fornecedores = fdao.listar();
			JSFUtil.addMsgSucesso("Registro excluído com sucesso!");
		}catch(SQLException e){
			JSFUtil.addMsgErro("Registro não foi excluído! Verifique se não há produtos associados!");
			e.printStackTrace();
		}
	}
	
	public void editar(){
		try{
		FornecedorDAO fdao = new FornecedorDAO();
		fdao.editar(fornecedor);
		fornecedores =  fdao.listar();
		JSFUtil.addMsgSucesso("Registro alterado com sucesso!");
		}catch(SQLException e){
			JSFUtil.addMsgErro("Registro não alterado!");
			e.printStackTrace();
		}
	}
}

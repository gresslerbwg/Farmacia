package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	private static final String USUARIO = "root"; 
	private static final String SENHA = "vision01..";
	private static final String URL = "jdbc:mysql://localhost:3306/sistema";
	
	public static Connection conectar() throws SQLException{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	
//	public static void main(String[] args) {
//		try {
//			@SuppressWarnings("unused")
//			Connection conexao = ConexaoBD.conectar();
//			System.out.println("Conectado ao banco de dados com sucesso!");
//		} catch (SQLException e) {
//			System.out.println("Conexão com banco de dados falhou!");
//			e.printStackTrace();
//		}
//	}
}

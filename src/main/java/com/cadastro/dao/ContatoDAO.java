package com.cadastro.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cadastro.modelo.Contatos;


public class ContatoDAO {

	Connection conexao;

	public ContatoDAO(Connection conexao) {
		this.conexao = new Conexao().conectar();
	}
	
	public void criarTabela() {
		PreparedStatement ps = null;
		String criarTabela = "CREATE TABLE contatos ("
				+ "id SERIAL PRIMARY KEY, "
				+ "nome varchar(50), "
				+ "email varchar(50), "
				+ "endereco varchar(50), "
				+ "dataNascimento DATE ) ";
		try{
		    ps = conexao.prepareStatement(criarTabela);
			ps.executeUpdate();
			System.out.println("Tabela criada com sucesso" );
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Conexao.fecharConexaoStatement(ps);
		}
	}

	
	public void inserir(Contatos obj) {
		PreparedStatement ps = null;
		String inserir = "INSERT INTO contatos (nome, email, endereco, dataNascimento) VALUES (?, ?, ?, ?)";
		try {
			ps = conexao.prepareStatement(inserir);
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getEmail());
			ps.setString(3, obj.getEndereco());
			Date dataFormatada = Date.valueOf(obj.getDataNascimento());
			ps.setDate(4, dataFormatada);

			int cadastrado = ps.executeUpdate();
			System.out.println("Usuário cadastrado: " + cadastrado);
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Conexao.fecharConexaoStatement(ps);
		}
	}

	public void deletar(Contatos obj) {
		
		PreparedStatement ps = null;
		String deletar = "DELETE FROM contatos WHERE id=?";
		try {
			ps = conexao.prepareStatement(deletar);
			ps.setLong(1, obj.getId());

			int deletado = ps.executeUpdate();
			System.out.println("Usuário deletado: " + deletado);
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Conexao.fecharConexaoStatement(ps);
		}

	}

	
	public List<Contatos> buscarLista() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String listar = "SELECT * FROM contatos ORDER BY nome";
		List<Contatos> lista = new ArrayList<>();

		try {
			ps = conexao.prepareStatement(listar);
			rs = ps.executeQuery();

			while (rs.next()) {
				Contatos contatos = new Contatos();
				contatos.setId(rs.getLong(1));
				contatos.setNome(rs.getString(2));
				contatos.setEmail(rs.getString(3));
				contatos.setEndereco(rs.getString(4));
				Date data = rs.getDate(5);
				LocalDate localDate = data.toLocalDate();
				contatos.setDataNascimento(localDate);
				lista.add(contatos);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Conexao.fecharConexaoStatement(ps);
			Conexao.fecharConexaoResultSet(rs);

		}
		return lista;
	}

	public void mostarContato(Contatos id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String exibir = "SELECT * FROM contatos WHERE id=?";
		try {
			ps = conexao.prepareStatement(exibir);
			ps.setLong(1, id.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				id.setId(rs.getLong(1));
				id.setNome(rs.getString(2));
				id.setEmail(rs.getString(3));
				id.setEndereco(rs.getString(4));
				Date data = rs.getDate(5);
				LocalDate localDate = data.toLocalDate();
				id.setDataNascimento(localDate);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Conexao.fecharConexaoStatement(ps);
			Conexao.fecharConexaoResultSet(rs);

		}
	}

	public void editar(Contatos id) {
		PreparedStatement ps = null;
		String atualizar = "UPDATE contatos SET nome=?, email=?, endereco=?, dataNascimento=? WHERE id=? ";
		try {
			ps = conexao.prepareStatement(atualizar);
			ps.setString(1, id.getNome());
			ps.setString(2, id.getEmail());
			ps.setString(3, id.getEndereco());
			LocalDate localDate = id.getDataNascimento();
			Date data = Date.valueOf(localDate);
			ps.setDate(4, data);
			ps.setLong(5, id.getId());
			
			int atualizado = ps.executeUpdate();
			System.out.println("Usuário atualizado: " + atualizado);
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Conexao.fecharConexaoStatement(ps);
		}
	}
	
}

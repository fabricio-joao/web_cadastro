package com.cadastro.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cadastro.dao.ContatoDAO;
import com.cadastro.dao.Conexao;
import com.cadastro.modelo.Contatos;


@WebServlet("/adicionaContato")
public class MeuServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	ContatoDAO dao = new ContatoDAO(Conexao.conectar()) ;  
	Contatos cadastro = new Contatos();
    
    public MeuServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//dao.criarTabela();
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String data = request.getParameter("dataNascimento");
		
		//conversão de de string para LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(data, formatter);
		System.out.println(id);
		System.out.println(nome);
		System.out.println(email);
		System.out.println(endereco);
		System.out.println(data); 
		
		cadastro.setNome(nome);
		cadastro.setEmail(email); 
		cadastro.setEndereco(endereco);
		cadastro.setDataNascimento(localDate);
		
		dao.inserir(cadastro);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

<%@page import="com.cadastro.dao.Conexao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cadastro.modelo.Contatos"%>
<%@ page import="com.cadastro.dao.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
ContatoDAO dao = new ContatoDAO(Conexao.conectar());
List<Contatos> lista = dao.buscarLista();
for(Contatos contatos: lista){%>
<li><%=contatos.getNome()%>, <%=contatos.getEmail()%>:
<%=contatos.getEndereco()%></li>
<%} %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/menu.css"/>
</head>
<body>

<ul id="menu">
   
   <li><a href="#">Cadastro</a>
      <ul>
      	<li><a href="gabarito_aluno.jsp">Gabarito Aluno</a></li>
      	<li><a href="gabarito_correto.jsp">Gabarito Correto</a></li>
      	<li><a href="turma.jsp?acao=null">Cadastro Turma</a></li>
      	<li><a href="semestre.jsp?acao=null">Cadastro Semestre</a></li>
      </ul>
   </li>
   
   <li><a href="relatorio.jsp?acao=null">Relatório</a></li>
   <li><a href="lista_correto.jsp?acao=null">Gabarito Correto</a></li>
   <li><a href="lista_aluno.jsp?acao=null&editar=null">Gabarito Aluno</a></li>
 
</ul>

</body>
</html>
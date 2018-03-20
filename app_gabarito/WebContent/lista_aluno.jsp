<%@page import="br.util.RetornaDiretorio"%>
<%@page import="br.dao.GabaritoAlunoDAO"%>
<%@page import="br.modelo.Turma"%>
<%@page import="br.dao.GabaritoTurmaDAO"%>
<%@page import="br.modelo.Semestre"%>
<%@page import="br.dao.GabaritoSemestreDAO"%>
<%@page import="br.modelo.Aluno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="br.modelo.Correto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.dao.GabaritoCorretoDAO"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SAG Gabarito Correto</title>
<link rel="stylesheet" type="text/css" href="css/gabarito_aluno.css?<%=((int)(Math.random() * 10000))%>">
<link rel="stylesheet" type="text/css" href="css/formulario.css?<%=((int)Math.random()*1000)%>"/>
<script language="javascript">
function confirmaExclusao(aURL) {
    if(confirm('Você tem certeza que deseja excluir?')) {
      location.href = aURL;
    }
  }
</script>
</head>
<body>

<%
	List<Aluno> listaAluno = new ArrayList<Aluno>();
	GabaritoAlunoDAO daoAluno = new GabaritoAlunoDAO();
	Aluno alunoAlter = new Aluno();

	if (request.getParameter("acao").equals("delete")) {
		daoAluno.deleta(request.getParameter("codigo"));
		out.println("<script>alert('Turma Excluida com Sucesso');</script>");
	} 
	if(request.getParameter("acao").equals("alterar")){
		alunoAlter = daoAluno.getAluno(request.getParameter("codigo"));
	}
	if(request.getParameter("editar").equals("true")){
		daoAluno.updateNome(request.getParameter("matricula"), request.getParameter("nome"));
	}
%>


<div id="tudo">
	
	<div id="topo"><jsp:include page="topo.jsp" /></div>
	<div id="menu"><jsp:include page="menu.jsp" /></div>
	
	<div id="turma">
		<form name="filtro" method="post"action="lista_aluno.jsp?acao=filtrar&dados=turma&editar=null">
			<label> Turma: <select name="turma"	style="width: 70px;">
					<option value="">--</option>
					<%
						GabaritoTurmaDAO daoTurma = new GabaritoTurmaDAO();
						List<Turma> listaTurma = new ArrayList<Turma>();
						listaTurma = daoTurma.getFiltraSemestre(RetornaDiretorio.SEMESTRE);
						for (Turma tur : listaTurma) {
					%>
					<option value="<%=tur.getSigla()%>"><%=tur.getSigla()%></option>
					<%
						}
					%>
			</select>
			<input type="submit" value="Filtrar" class="btnAluno" />
			</label> 
		</form>
	</div>

	<%
	if(request.getParameter("acao").equals("filtrar")){
		
	
	%>
	<div id="excluir" class="listar">
		<fieldset>
		<legend>Lista de Alunos</legend>
		<table>
		<tr>
			<td width="70"><h2>Pos. Leitura</h2></td>
			<td width="70"><h2>Turma</h2></td>
			<td width="200"><h2>Matricula</h2></td>
			<td width="600"><h2>Nome</h2></td>
			<td width="10"><!-- Alterar --></td>
			<td width="10" style="border-right: 1px dashed #ccc;"><!-- Excluir --></td>
		</tr>
		<%
			listaAluno = daoAluno.getFiltroSemestreTurma(RetornaDiretorio.SEMESTRE, request.getParameter("turma"));
			if (listaAluno.isEmpty() || listaAluno == null) {
				out.println("<font color=\"red\">Nenhuma Gabarito foi encontrada.</font>");
			} else {
				for (Aluno aluno : listaAluno) {
		%>
				<tr>
				<td><h1><%=aluno.getPosicao_leitura()%>°</h1></td>
				<td><h1><%=aluno.getTurma()%></h1></td>
				<td><h1><%=aluno.getMatricula()%></h1></td>
				<td><h1><%=aluno.getNome()%></h1></td>
				<td width="50" style="border-right: 1px dashed #ccc;">
					<a	href="lista_aluno.jsp?acao=alterar&editar=null&codigo=<%=aluno.getId()%>" title="Alterar">
					<center><img src="imagens/alterar.gif" width="24" height="24" /></center></a>
				</td>
				<td width="50" style="border-right: 1px dashed #ccc;">
					<a	href="lista_aluno.jsp?acao=delete&editar=null&codigo=<%=aluno.getId()%>" title="Alterar">
					<center><img src="imagens/delete.gif" width="24" height="24" /></center></a>
				</td>
			</tr>
			<%
				}
				}
			%>
		</table>
		</fieldset>
	</div>
	<%} else if(request.getParameter("acao").equals("alterar")){ %>
	<div id="abaCadastrar">
	<fieldset>
	<legend>Alterar Aluno</legend>
	<form name="alter_aluno" method="post" action="lista_aluno.jsp?acao=null&editar=true">
		<input type="hidden" name="matricula" maxlength="8" size="20" value="<%=alunoAlter.getMatricula()%>" />
		<label>
			<span>Matrícula</span>
			<input type="text" name="matriculaD" maxlength="8" disabled="disabled" size="20" value="<%=alunoAlter.getMatricula()%>" />
		</label> 
		
		<label>
			<span>Turma</span>
			<input type="text" name="turma" size="10" maxlength="10" disabled="disabled" value="<%=alunoAlter.getTurma()%>" /> 
		</label> 
		
		<label>
			<span>Nome do Aluno</span>
			<input type="text" name="nome" size="100" maxlength="50" value="<%=alunoAlter.getNome().trim()%>"/> 
		</label> 
		
		<input type="submit" value="Salvar" class="btn"/>
	</form>
	</fieldset>
</div>
	<%} %>
</div>

</body>
</html>
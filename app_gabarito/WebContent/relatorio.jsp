<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="br.dao.GabaritoSemestreDAO"
	import="java.util.List"
	import="br.modelo.Semestre"
	import="java.util.ArrayList"
	import="br.dao.GabaritoTurmaDAO"
	import="br.modelo.Turma"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SAG - Relatório Gabarito</title>
<link rel="stylesheet" type="text/css" href="css/relatorio.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
</head>
<body>

<div id="tudo">
	<div id="topo"><jsp:include page="topo.jsp" /></div>
	<div id="menu"><jsp:include page="menu.jsp" /></div>
	
	<div id="meio">
		<div id="filtro">
			<form name="filtro" method="post" action="relatorio.jsp?acao=filtrar&dados=semestre">
				<label>
					Semestre:
					<select name="semestre" style="width: 70px;">
						<option value="">--</option>
						<%
						GabaritoSemestreDAO daoSemestre = new GabaritoSemestreDAO();
						List<Semestre> listaSemestre = new ArrayList<Semestre>();
						listaSemestre = daoSemestre.getTodosSemestre();
						for(Semestre sem : listaSemestre){
						%>
						<option value="<%=sem.getSemeste()%>"><%=sem.getSemeste()%></option>
						<%} %>
					</select>
				</label>
				<input type="submit" value="Filtrar" class="btn" />
			</form>
		</div>
		
		<div id="conteudo" class="listar">
			<%
			if(request.getParameter("acao").equals("filtrar")){
				GabaritoTurmaDAO daoTurma = new GabaritoTurmaDAO();
				List<Turma> listaTurma = new ArrayList<Turma>();
				listaTurma = daoTurma.getFiltraSemestre(request.getParameter("semestre"));
			%>
			<fieldset>
			<legend>Relatório dos Gabaritos por Turma</legend>
			<table>
			<tr>
				<td width="100"><h2>Turma</h2></td>
				<td width="200"><h2>Curso</h2></td>
				<td width="70"><h2>Semestre</h2></td>
				<td width="10" style="border-right: 1px dashed #ccc;"><!-- Alterar --></td>
			</tr>
			<%
			if(listaTurma.isEmpty()){
				out.println("<font color=\"red\">Nenhuma Turma cadastrada neste semestre.</font>");
			}else{
				for(Turma tur : listaTurma){
			%>
					<tr>
					<td><h1><%=tur.getSigla()%></h1></td>
					<td><h1><%=tur.getCurso()%></h1></td>
					<td><h1><%=tur.getPeriodo()%></h1></td>
					<td width="50" style="border-right: 1px dashed #ccc;">
						<a	href="relatorio_detalhado.jsp?semestre=<%=tur.getPeriodo()%>&turma=<%=tur.getSigla()%>" title="Alterar">
						<center><img src="imagens/lupa.gif" width="24" height="24" /></center></a>
					</td>
				</tr>
				<%
					}
				}
				%>
			</table>
			</fieldset>
			<%} %>
		</div>
	
	</div>
</div>
</body>
</html>
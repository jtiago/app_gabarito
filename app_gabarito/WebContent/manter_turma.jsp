<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="java.util.List"
	import="br.modelo.Turma"
	import="java.util.ArrayList"
	import="br.dao.GabaritoTurmaDAO"
%>


<%@page import="br.dao.GabaritoSemestreDAO"%>
<%@page import="br.modelo.Semestre"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/formulario.css"/>
<link rel="stylesheet" type="text/css" href="css/table.css"/>

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
List<Turma> listaTurma = new ArrayList<Turma>();
GabaritoTurmaDAO dao = new GabaritoTurmaDAO();
if(request.getParameter("acao").equals("inserir")){
	if(request.getParameter("curso") != null){
		if(request.getParameter("curso").length() <= 0 
			|| request.getParameter("sigla").length() <= 0
			|| request.getParameter("periodo").length() <= 0 ){
	    out.println("<script>alert('Cadastro não efetuado - Existe Campo Vazio!');</script>");   
		} else {
			try{
				dao.insert(request.getParameter("curso"),request.getParameter("sigla").toUpperCase(),request.getParameter("periodo"));
				out.println("<script>alert('Turma Cadastrada com Sucesso');</script>");
			}catch(Exception e){
				out.println("Um Exceção JDBC: "+e);
			}
		}
	}
}else if(request.getParameter("acao").equals("update")){
	dao.alteraTurma(request.getParameter("codigo"),request.getParameter("sigla").toUpperCase(),
			request.getParameter("curso"),request.getParameter("periodo"));
	out.println("<script>alert('Turma "+request.getParameter("sigla")+" Alterada com Sucesso');</script>");
	//response.sendRedirect("turma.jsp?acao=alterar");
}else if(request.getParameter("acao").equals("delete")){
	dao.deletaTurma(request.getParameter("codigo"));
	out.println("<script>alert('Turma Excluida com Sucesso');</script>");
}
%>
<div id="tudo">
<!-- INICIO ABA CADASTRAR -->
<%if(request.getParameter("acao").equals("cadastrar")){ %>
<div id="abaCadastrar">
	<fieldset>
	<legend>Cadastrar Turma</legend>
	<form name="cad_turma" method="post" action="manter_turma.jsp?acao=inserir">
		<label>
			<span>Nome da Turma:</span>
			<input type="text" name="sigla" maxlength="50" size="20" />
		</label> 
		
		<label>
			<span>Curso:</span>
			<input type="text" name="curso" size="40" /> 
		</label> 
		
		<label>
			<span>Semestre:</span>
			<select name="periodo">
				<option value="">--</option>
				<%
				GabaritoSemestreDAO daoSemestre = new GabaritoSemestreDAO();
				List<Semestre> listaSemestre = new ArrayList<Semestre>();
				listaSemestre = daoSemestre.getTodosSemestre();
				for(Semestre sem :  listaSemestre){
				%>
				<option value="<%=sem.getSemeste()%>"><%=sem.getSemeste()%></option>
				<%}%>
			</select>
		</label>  
		
		<input type="submit" value="Cadastar" class="btn"/>
	</form>
	</fieldset>
</div>
<!-- FIM ABA CADASTRAR -->
<%}else if(request.getParameter("acao").equals("alterar")){ %>

<!-- INICIO ABA ALTERAR -->
<div id="abaAlterar" class="listar">
	<fieldset>
	<legend>Alterar Turma</legend>
	<table>
	<tr>
		<td width="200"><h2>Curso</h2></td>
		<td width="100"><h2>Sigla</h2></td>
		<td width="70"><h2>Período</h2></td>
		<td width="10" style="border-right: 1px dashed #ccc;"><!-- Alterar --></td>
	</tr>
	<%
	listaTurma = dao.getTodasTurmas();
	if(listaTurma.isEmpty()){
		out.println("<font color=\"red\">Nenhuma Matrícula foi encontrada.</font>");
	}else{
		for(Turma tur : listaTurma){
	%>
			<tr>
			<td><h1><%=tur.getCurso()%></h1></td>
			<td><h1><%=tur.getSigla()%></h1></td>
			<td><h1><%=tur.getPeriodo()%></h1></td>
			<td width="50" style="border-right: 1px dashed #ccc;">
				<a	href="manter_turma.jsp?acao=alterando&codigo=<%=tur.getId()%>" title="Alterar">
				<center><img src="imagens/alterar.gif" width="24" height="24" /></center></a>
			</td>
		</tr>
		<%
			}
		}
		%>
	</table>
	</fieldset>
</div>
<!-- FIM ABA ALTERAR -->
<%}else if(request.getParameter("acao").equals("excluir")){ %>

<!-- INICIO ABA EXCLUIR-->
<div id="abaExcluir" class="listar">
	<fieldset>
	<legend>Excluir Turma</legend>
	<table>
	<tr>
		<td width="200"><h2>Curso</h2></td>
		<td width="100"><h2>Sigla</h2></td>
		<td width="70"><h2>Período</h2></td>
		<td width="10" style="border-right: 1px dashed #ccc;"><!-- Alterar --></td>
	</tr>
	<%
	listaTurma = dao.getTodasTurmas();
	if(listaTurma.isEmpty()){
		out.println("<font color=\"red\">Nenhuma Matrícula foi encontrada.</font>");
	}else{
		for(Turma tur : listaTurma){
	%>
			<tr>
			<td><h1><%=tur.getCurso()%></h1></td>
			<td><h1><%=tur.getSigla()%></h1></td>
			<td><h1><%=tur.getPeriodo()%></h1></td>
			<td width="50" style="border-right: 1px dashed #ccc;">
				<a	href="javascript:confirmaExclusao('manter_turma.jsp?acao=delete&codigo=<%=tur.getId()%>')" title="Excluir">
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
<!-- FIM ABA EXCLUIR-->
<%}else if(request.getParameter("acao").equals("alterando")){ 
	listaTurma = dao.getTurma(request.getParameter("codigo"));
	for(Turma turma : listaTurma){
%>

<!-- INICIO ABA ALTERANDO -->
<div id="abaAlt">
	<form name="cad_turma" method="post" action="manter_turma.jsp?acao=update&codigo=<%=turma.getId()%>">
	<fieldset>
	<legend>Alterando Turma</legend>
		<label>
			<span>Nome da Turma:</span>
			<input type="text" name="sigla" maxlength="50" size="20" value="<%=turma.getSigla()%>" />
		</label> 
		
		<label>
			<span>Curso:</span>
			<input type="text" name="curso" size="50" value="<%=turma.getCurso()%>" /> 
		</label>
		
		<label>
			<span>Período Letivo:</span>
			<select name="periodo">
				<option value="<%=turma.getPeriodo()%>"><%=turma.getPeriodo()%></option>
				<%
				GabaritoSemestreDAO daoSemestre = new GabaritoSemestreDAO();
				List<Semestre> listaSemestre = new ArrayList<Semestre>();
				listaSemestre = daoSemestre.getFiltroSemestre(turma.getPeriodo());
				for(Semestre sem :  listaSemestre){
				%>
				<option value="<%=sem.getSemeste()%>"><%=sem.getSemeste()%></option>
				<%}%>
			</select>
		</label> 
		
		<input type="submit" value="Alterar" class="btn"/>
	</fieldset>
	</form>
</div>
<!-- FIM ABA ALTERANDO -->
<%}}%>
</div>
</body>
</html>
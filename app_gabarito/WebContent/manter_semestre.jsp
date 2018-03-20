<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="br.dao.GabaritoSemestreDAO"
	import="java.util.List"
	import="java.util.ArrayList"
	import="br.modelo.Semestre"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
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

function somente_numero(campo){  
var digits="0123456789"  
var campo_temp   
    for (var i=0;i<campo.value.length;i++){  
        campo_temp=campo.value.substring(i,i+1)   
        if (digits.indexOf(campo_temp)==-1){  
            campo.value = campo.value.substring(0,i);  
        }  
    }  
}   
</script>  
</head>
<body>
<%
GabaritoSemestreDAO dao = new GabaritoSemestreDAO();
List<Semestre> listaSemestre = new ArrayList<Semestre>();
if(request.getParameter("acao").equals("inserir")){
	if(request.getParameter("semestre") != null){
		if(request.getParameter("semestre").length() <= 0 
	|| request.getParameter("dataabertura").length() <= 0
	|| request.getParameter("dataencerramento").length() <= 0 ){
	    out.println("<script>alert('Cadastro não efetuado - Existe Campo Vazio!');</script>");   
		} else {
	try{
		dao.insert(request.getParameter("semestre"),request.getParameter("dataabertura"),request.getParameter("dataencerramento"));
		out.println("<script>alert('Semestre Cadastrada com Sucesso');</script>");
	}catch(Exception e){
		out.println("Um Exceção JDBC: "+e);
	}
		}
	}
}else if(request.getParameter("acao").equals("update")){
	dao.alteraSemestre(request.getParameter("codigo"),request.getParameter("semestre"),
	request.getParameter("dataabertura"),request.getParameter("dataencerramento"));
	out.println("<script>alert('Semestre "+request.getParameter("semestre")+" Alterada com Sucesso');</script>");
	//response.sendRedirect("turma.jsp?acao=alterar");
}else if(request.getParameter("acao").equals("delete")){
	dao.deletaSemestre(request.getParameter("codigo"));
	out.println("<script>alert('Semestre Excluida com Sucesso');</script>");
}
%>
<div id="tudo">
<!-- INICIO ABA CADASTRAR -->
<%if(request.getParameter("acao").equals("cadastrar")){ %>
<div id="abaCadastrar">
	<fieldset>
	<legend>Cadastrar Semestre</legend>
	<form name="cad_turma" method="post" action="manter_semestre.jsp?acao=inserir">
		<label>
			<span>Semestre:</span>
			<input type="text" name="semestre" maxlength="6" size="8" />
		</label> 
		
		<label>
			<span>Data de Abertura</span>
			<input type="text" name="dataabertura" size="10" maxlength="10" /> 
		</label> 
		
		<label>
			<span>Data de Encerramento</span>
			<input type="text" name="dataencerramento" size="10" maxlength="10" /> 
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
	<legend>Alterar Semestre</legend>
	<table>
	<tr>
		<td width="100"><h2>Semestre</h2></td>
		<td width="300"><h2>Data de Abertura</h2></td>
		<td width="300"><h2>Data de Encerramento</h2></td>
		<td width="10" style="border-right: 1px dashed #ccc;"><!-- Alterar --></td>
	</tr>
	<%
	listaSemestre = dao.getTodosSemestre();
	if(listaSemestre.isEmpty()){
		out.println("<font color=\"red\">Nenhum Semestre foi encontrado.</font>");
	}else{
		for(Semestre sem : listaSemestre){
	%>
			<tr>
			<td><h1><%=sem.getSemeste()%></h1></td>
			<td><h1><%=sem.getDataabertura()%></h1></td>
			<td><h1><%=sem.getDataencerramento()%></h1></td>
			<td width="50" style="border-right: 1px dashed #ccc;">
				<a	href="manter_semestre.jsp?acao=alterando&codigo=<%=sem.getId()%>" title="Alterar">
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
	<legend>Excluir Semestre</legend>
	<table>
	<tr>
		<td width="100"><h2>Semestre</h2></td>
		<td width="300"><h2>Data de Abertura</h2></td>
		<td width="300"><h2>Data de Encerramento</h2></td>
		<td width="10" style="border-right: 1px dashed #ccc;"><!-- Alterar --></td>
	</tr>
	<%
	listaSemestre = dao.getTodosSemestre();
	if(listaSemestre.isEmpty()){
		out.println("<font color=\"red\">Nenhum Semestre foi encontrado.</font>");
	}else{
		for(Semestre sem : listaSemestre){
	%>
			<tr>
			<td><h1><%=sem.getSemeste()%></h1></td>
			<td><h1><%=sem.getDataabertura()%></h1></td>
			<td><h1><%=sem.getDataencerramento()%></h1></td>
			<td width="50" style="border-right: 1px dashed #ccc;">
				<a	href="javascript:confirmaExclusao('manter_semestre.jsp?acao=delete&codigo=<%=sem.getId()%>')" title="Excluir">
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
	listaSemestre = dao.getSemestre(request.getParameter("codigo"));
	for(Semestre sem : listaSemestre){
%>

<!-- INICIO ABA ALTERANDO -->
<div id="abaAlt">
	<form name="cad_turma" method="post" action="manter_semestre.jsp?acao=update&codigo=<%=sem.getId()%>">
	<fieldset>
	<legend>Alterando Semestre</legend>
		<label>
			<span>Semestre:</span>
			<input type="text" name="semestre" maxlength="8" size="8" value="<%=sem.getSemeste()%>" />
		</label> 
		
		<label>
			<span>Data de Abertura:</span>
			<input type="text" name="dataabertura" size="50" value="<%=sem.getDataabertura()%>" /> 
		</label>
		
		<label>
			<span>Data de Encerramento:</span>
			<input type="text" name="dataencerramento" size="50" value="<%=sem.getDataencerramento()%>" /> 
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
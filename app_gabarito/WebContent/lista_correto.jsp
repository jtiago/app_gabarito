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
<link rel="stylesheet" type="text/css" href="css/gabarito_correto.css?<%=((int)(Math.random()*10000))%>">
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
List<Correto> listaCorreto = new ArrayList<Correto>();
GabaritoCorretoDAO daoCorreto = new GabaritoCorretoDAO();
if(request.getParameter("acao").equals("delete")){
	daoCorreto.deleta(request.getParameter("codigo"));
	out.println("<script>alert('Turma Excluida com Sucesso');</script>");
}
%>


<div id="tudo">
	<div id="topo"><jsp:include page="topo.jsp" /></div>
	<div id="menu"><jsp:include page="menu.jsp" /></div>
	
</div>

<div id="excluir" class="listar">
	<fieldset>
	<legend>Excluir Gabarito</legend>
	<table>
	<tr>
		<td width="200"><h2>Turma</h2></td>
		<td width="100"><h2>Semestre</h2></td>
		<td width="70"><h2>Qant. Resposta</h2></td>
		<td width="10" style="border-right: 1px dashed #ccc;"><!-- Alterar --></td>
	</tr>
	<%
	listaCorreto = daoCorreto.getTodos();
	if(listaCorreto.isEmpty()){
		out.println("<font color=\"red\">Nenhuma Gabarito foi encontrada.</font>");
	}else{
		for(Correto correto : listaCorreto){
	%>
			<tr>
			<td><h1><%=correto.getTurma()%></h1></td>
			<td><h1><%=correto.getSemestre()%></h1></td>
			<td><h1><%=correto.getQntresposta()%></h1></td>
			<td width="50" style="border-right: 1px dashed #ccc;">
				<a	href="javascript:confirmaExclusao('lista_correto.jsp?acao=delete&codigo=<%=correto.getId()%>')" title="Alterar">
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

</body>
</html>
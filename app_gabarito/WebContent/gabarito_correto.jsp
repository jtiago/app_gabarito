<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="br.reader.LeituraArquivo"
	import="br.dao.GabaritoTurmaDAO"
	import="java.util.List"
	import="br.modelo.Turma"
	import="java.util.ArrayList"
%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.dao.GabaritoSemestreDAO"%>
<%@page import="br.modelo.Semestre"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulário de Arquivamento</title>
<link rel="stylesheet" type="text/css" href="css/formulario.css"/>
<style type="text/css">
body{background:url(imagens/barra_fundo.jpg) top left repeat-x fixed;}
</style>
</head>
<body>

<jsp:useBean id="upload" scope="page" class="br.upload.ArmazenaGabaritos" />
<jsp:setProperty name="upload" property="extensoesPermitidas" value="dat"/>

<div id="topo">	<jsp:include page="topo.jsp" /></div>
<div id="menu">	<jsp:include page="menu.jsp" /></div>
<%
	if(upload.doFilePost(request,application)){
	out.println("<center><font color=\"red\"><strong>Arquivo Armazenado com Sucesso!</strong></font></center>");
	
	if(upload.getListMatNaoEncontradas().isEmpty()){
		out.println("<center><font color=\"red\">Todas matrículas foram cadastradas com sucesso!</font></center>");	
	}else{
		out.println("<center><font color=\"red\">Não foi possível encontrar a(s) Matrícula(s) :</font></center>");
		for(String lista : upload.getListMatNaoEncontradas())
	out.println("<center><font color=\"red\">"+lista+"</font></center>");
	out.println();
	}
}else if(upload.getErro() != null){
	out.println("<center><font color=\"red\"><strong>"+upload.getErro()+"</strong></font></center>");
}
%>

<div id="meio">
	<fieldset style="width:500px;">
	<legend>Arquivar Gabarito Correto</legend>
	
	<form name="armazena" method="post" action="gabarito_correto.jsp" enctype="multipart/form-data">
		<input type="hidden" name="tipo" value="correto"/>
		<label>
			<span>Turma:</span>
			<select name="turma" style="width: 100px;">
				<option value="">--</option>
				<%
				GabaritoTurmaDAO daoTurma = new GabaritoTurmaDAO();
				List<Turma> listaTurma = new ArrayList<Turma>();
				listaTurma = daoTurma.getFiltraSemestre("2017.2");
				for(Turma turma : listaTurma){
				%>
				<option value="<%=turma.getSigla()%>"><%=turma.getSigla()%></option>
				<%
					}
				%>
			</select>
		</label> 
		
		<label>
			<span>Semestre:</span>
			<select name="semestre">
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
		
		<label>
			<span>Quantidade de Questões:</span>
			<select name="qtn_questoes" style="width: 50px;">
				<option value="">--</option>
				<%
				for(int i=40;i<=160;i+=10){
				%>
				<option value="<%=i%>"><%=i%></option>
				<%} %>
			</select>
		</label>  
		
		<label>
			<span>Gabarito dos Alunos:</span>
			<input name="gabarito_alunos" type="file" size="50"  /> 
		</label> 
		
		<input type="submit" value="Arquivar" class="btn" />
	
	</form>
	
	</fieldset>
</div>

</body>
</html>
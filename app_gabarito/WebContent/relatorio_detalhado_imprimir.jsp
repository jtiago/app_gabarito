<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="br.dao.GabaritoAlunoDAO"
	import="java.util.List"
	import="br.modelo.Aluno"
	import="java.util.ArrayList"
	import="br.dao.GabaritoCorretoDAO"
	import="br.modelo.Correto"
	import="java.text.NumberFormat"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SAG - Relatório Gabarito</title>
<link rel="stylesheet" type="text/css" href="css/relatorio_imprimir.css?<%=((int)Math.random()*1000)%>">
<link rel="stylesheet" type="text/css" href="css/table.css?<%=((int)Math.random()*1000)%>">

<style type="text/css">
	table {border-collapse: collapse;border:solid 2px #DCDCDC;}
	tr:nth-child(2n+1){background: #DCDCDC;}
</style>

</head>
<body>
<div id="tudo">
	<br/>
	<h2>Pauta de Resultado da Tuma <%=request.getParameter("turma")%></h2>
	<div class="relatorio">
	<%
	GabaritoAlunoDAO daoAluno = new GabaritoAlunoDAO();
	List<Aluno> listaAluno = new ArrayList<Aluno>();
	listaAluno = daoAluno.getFiltroSemestreTurma(
			request.getParameter("semestre"),request.getParameter("turma"));
	
	GabaritoCorretoDAO daoCorreto = new GabaritoCorretoDAO();
	String resposta = daoCorreto.getFiltroSemestreTurma(
			request.getParameter("semestre"),request.getParameter("turma"));
	%>
	<table border="0">
		<tr>
			<td width="30"><h1>Nº</h1></td>
			<td width="60"><h1>TURMA</h1></td>
			<td width="50"><h1>MATRICULA</h1></td>
			<td width="370"><h1>ALUNO(A)</h1></td>
			<td width="80"><h1>QT AC</h1></td>
			<td width="45"><h1>%</h1></td>
			<td width="40"><h1>CONCEITO</h1></td>
			<td width="60"><h1>PS LT</h1></td>
		
		</tr>
	
	<%
	int qntQuestao = 0;
	int avalia = 0;
	String qt_ac = null;
	String conceito = null;
	int numero = 1;
		if(listaAluno.isEmpty()){
			out.println("<font color=\"red\">Nenhuma Gabarito Aluno desta turma foi cadastrado!</font>");
		}else{
			if(resposta.length() > 0){
				for(Aluno aluno : listaAluno){
					for(int i = 0; i < aluno.getResposta().length(); i++){
						
						if(resposta.charAt(i) == aluno.getResposta().charAt(i)){
							avalia++;
						}
						qntQuestao++;
					}
					%>
				
					<tr>
						<td><h2><%=numero++%></h2></td>
						<td><h2><%=aluno.getTurma()%></h2></td>
						<td><h2><%=aluno.getMatricula()%></h2></td>
						<td><h3><%=aluno.getNome()%></h3></td>
						<td><h2><%=avalia+"/"+qntQuestao%></h2></td>
						<td><h2><%
							NumberFormat nuberFormat = NumberFormat.getInstance();
							nuberFormat.setMaximumFractionDigits(2);
							qt_ac = nuberFormat.format(avalia/Double.parseDouble(aluno.getQtdresposta())*100);
							out.println(qt_ac);
							
							%>
						</h2></td>
						<td><h2>
						<%
							qt_ac = qt_ac.replaceAll(",", "\\.");
							if(Double.parseDouble(qt_ac) >= 60.0){
								conceito = "H";
							} else{
								conceito = "<b>NH</b>";
							}
							out.println(conceito);
							%>
						</h2></td>
						<td><h2><%=aluno.getPosicao_leitura()%>º</h2></td>
					</tr>
				<%
				avalia = 0;qntQuestao=0;
				}
			}else{
				out.println("<font color=\"red\">Nenhuma Gabarito Correto desta turma foi cadastrado!</font>");
			}
		}
	%>
	</table>	
	</div>
</div>

</body>
</html>
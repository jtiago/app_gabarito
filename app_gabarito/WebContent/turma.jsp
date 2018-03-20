<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro da Turma</title>
<link rel="stylesheet" type="text/css" href="css/cadastro.css?<%=((int)Math.random()*10000)%>"/>
<link rel="stylesheet" href="css/lavalamp.css?<%=((int)Math.random()*10000)%>" type="text/css" media="screen"/>
<script type="text/javascript" src="js/lavalamp/jquery-1.2.3.min.js"></script>
<script type="text/javascript" src="js/lavalamp/jquery.easing.min.js"></script>
<script type="text/javascript" src="js/lavalamp/jquery.lavalamp.min.js"></script>

<script type="text/javascript">
$(function() {
     $("#1, #2, #3").lavaLamp({
      fx: "backout",
                speed: 700,
                click: function(event, menuItem) {
                    return true;
                }
            });
        });
</script>

<script type='text/javascript'>
//Defini o tamanho do iframe de forma dinamica
function iframeAutoHeight(quem){
    //by Micox - elmicox.blogspot.com - elmicox.com - webly.com.br  
    if(navigator.appName.indexOf("Internet Explorer")>-1){ //ie sucks
        var func_temp = function(){
            var val_temp = quem.contentWindow.document.body.scrollHeight + 15
            quem.style.height = val_temp + "px";
        }
        setTimeout(function() { func_temp() },100) //ie sucks
    }else{
        var val = quem.contentWindow.document.body.parentNode.offsetHeight + 15
        quem.style.height= val + "px";
    }    
}
</script>

</head>
<body>
<div id="tudo">

	<div id="topo"><jsp:include page="topo.jsp" /></div>
	<div id="menu"><jsp:include page="menu.jsp" /></div>

	<div id="menu_cadastro">
		<ul class="lavaLampBottomStyle" id="3">
            <li><a href="manter_turma.jsp?acao=cadastrar" target="turma">Cadastrar</a></li>
            <li><a href="manter_turma.jsp?acao=alterar" target="turma">Alterar</a></li>
            <li><a href="manter_turma.jsp?acao=excluir" target="turma">Excluir</a></li>
    	</ul>
	</div>

	<div id="iframe">
		<iframe width="780" onload='iframeAutoHeight(this)' frameborder="0" 
			scrolling="auto" name="turma" src="manter_turma.jsp?acao=cadastrar"></iframe>
	</div>

</div>

</body>
</html>
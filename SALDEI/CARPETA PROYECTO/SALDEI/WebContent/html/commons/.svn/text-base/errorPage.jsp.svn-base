<%@ include file="library.jsp" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><bean:message key="errorpage.title"/></title>
<script language="Javascript">
	function mostrar(){
		document.getElementById("body").style.visibility="visible";
	}
	
	function ocultar(){
		document.getElementById("body").style.visibility="hidden";
	}
	
	function showBody(){
		if(document.forms[0].btnShow.value == 'Mostrar>>'){
			document.forms[0].btnShow.value = '<<Ocultar';
			mostrar();
		}else{
			document.forms[0].btnShow.value = 'Mostrar>>';
			ocultar();
		}
	}
</script> 
</head>
<body onload="ocultar();">
<form>
<bean:message key="errorpage.lbl.legend"/>
<input type="button" value="Regresar" onclick="history.back();"/>&nbsp;&nbsp;<input type="button" name="btnShow" id="btnShow" onclick="showBody();" value="Mostrar>>"/><br/>
<div align="left" id="body">
	<br/>
	<% String error =  (String) request.getAttribute("commonsError");
		if(error != null)
			out.println(error);
		else
			out.println("Ha ocurrido un error al tratar de realizar la operacion");
	%>
</div>
</form>
</body>
</html:html>
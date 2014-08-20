<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<html:html>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
<script type="text/javascript">
	function imprimir(){
		document.getElementById("btnBack").style.display='none'; 
		document.getElementById("btnPrint").style.display='none'; 
		window.print() ;
	}
</script>
</head>
<body>
<div align="left">
<input type="button" id="btnBack" name="btnBack" onclick="history.back();" value="<< Regresar" styleClass="ButtonText"/>&nbsp;<input type="button" name="btnPrint" id="btnPrint" onclick="imprimir();" value="Imprimir" styleClass="ButtonText"/>
</div>
<logic:present name="listasBuenos" scope="request">
	<display:table name="listasBuenos"  excludedParams="*" sort="list" 
							 requestURI="${ctx}/cdatos.do?cmd=verCorrectos" id="dspListBuenos" defaultsort="2">
 		<display:caption><font color='white'><bean:message key="cargaDatos.display.list.buenos"/></font><br/><br/></display:caption>
		<display:column property="codMateria" title="Codigo Materia"/>
		<display:column property="nombreMateria" title="Materia"/>
		<display:column property="unidadesValorativas" title="UV"/>
		<display:column property="seccionMateria" title="Seccion"/>
		<display:column property="carnetEstudiante" title="Carnet"/>
		<display:column property="primerApellido" title="Apellido"/>		
		<display:column property="primerNombre" title="Nombre"/>		
		<display:column property="email" title="Correo"/>	

	</display:table>
</logic:present>

<logic:present name="listasMalas" scope="request">
	<display:table name="listasMalas"  excludedParams="*" sort="list" 
							 requestURI="${ctx}/cdatos.do?cmd=verIncorrectos" id="dspListMalos" >
 		<display:caption><font color='white'><bean:message key="cargaDatos.display.list.malos"/></font><br/><br/></display:caption>		
 		<display:column property="error" title="Error" sortable="true" headerClass="sortable"  group="1" />
 		<display:column property="correlativo" title="Num Fila"/>
		<display:column property="codMateria" title="Codigo Materia"/>
		<display:column property="nombreMateria" title="Materia"/>
		<display:column property="unidadesValorativas" title="UV"/>
		<display:column property="seccionMateria" title="Seccion"/>
		<display:column property="carnetEstudiante" title="Carnet"/>
		<display:column property="primerApellido" title="Apellido"/>		
		<display:column property="primerNombre" title="Nombre"/>		
		<display:column property="email" title="Correo"/>
	</display:table>
</logic:present>
</body>
</html:html>
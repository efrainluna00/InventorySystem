<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<%@ page import="com.saldei.web.services.administracion.CarreraServices" %>
<html:html>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
<%
	CarreraServices services = new CarreraServices();
	request.setAttribute("facultad", services.getFacultad());
%>
<script type="text/javascript" src="${ctx}/js/calendar/calendar.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar_helper.js"></script>
<script type="text/javascript">
	function verCalen1(){
			Calendar.setup({
		    inputField : "fechainicial",
	    	ifFormat   : "%d/%m/%Y",
		    button     : "btnFecha1"
		});
	}	
	function verCalen2(){
			Calendar.setup({
		    inputField : "fechafinal",
	    	ifFormat   : "%d/%m/%Y",
		    button     : "btnFecha2"
		});
	}
	function cleanForm(){
		document.forms[0].nombreCarrera.value = '';
		document.forms[0].nomCarreraHid.value = '';
		document.forms[0].idCarrera.value = '';
		document.forms[0].idFacultad.value = 'Seleccione';
		document.forms[0].cmd.value = 'limpiar';
		document.forms[0].submit();
	}	
	function modificar(jsNombre, jsHid, jsFacultad,jsPlanEstudio,jsFechaIni,jsFechaFin){
		document.forms[0].nombreCarrera.value = jsNombre;
		document.forms[0].nomCarreraHid.value = jsHid;		
		jQuery.NiceJForms.selectValor('idFacultad', 0, jsFacultad);
		document.forms[0].idCarrera.value = jsHid;
	    document.forms[0].btnSave.disabled = true;
	    document.forms[0].planestudio.value=jsPlanEstudio;
	    document.forms[0].fechainicial.value=jsFechaIni;
	    document.forms[0].fechafinal.value=jsFechaFin;
	    document.forms[0].btnNuevasFechas.disabled=false;
	    document.forms[0].btnSave.disabled=true;
   		jQuery.NiceJForms.cambio('btnSave');
	   }	
	function saveFunction(){
		var c = confirm('Desea guardar los cambios?');
		if(c){
			document.forms[0].nombreCarrera.value = '';
			document.forms[0].nomCarreraHid.value = '';
			document.forms[0].idCarrera.value = '';
			document.forms[0].idFacultad.value = 'Seleccione';
			document.forms[0].cmd.value='save';
			document.forms[0].submit();
		}
	}
	function saveOutFunction(){
		if(!Validar()){
			alert('Debe ingresar todos los campos obligatorios');
		}else{
			if (document.forms[0].fechafinal.value != ''){
				if(Comparar_Fecha(document.forms[0].fechainicial.value,  document.forms[0].fechafinal.value)){	
					document.forms[0].cmd.value='save';
					document.forms[0].submit();
				}
				else
					alert("Las fechas de Vigencias no son validas");			
			}
			else{
				document.forms[0].cmd.value='save';
				document.forms[0].submit();
			}
		}
	}
	function Validar(){
		if (document.forms[0].nombreCarrera.value == '')
			return false;
		if (document.forms[0].idFacultad.value == 'Seleccione')
			return false;
		if (document.forms[0].idCarrera.value == '')
			return false;
		if (document.forms[0].fechainicial.value == '')
			return false;
		return true; 	
	}	
	function updateFunction(){
		var c = confirm('Desea cancelar los cambios?');
		if(c){
			document.forms[0].cmd.value='update';
			document.forms[0].submit();
		}
	}
	function deleteFunction(){
		var c = confirm('Desea eliminar el registro?');
		if(c){
			document.forms[0].cmd.value='delete';
			document.forms[0].submit();
		}
	}
	function updateOutFunction(){
	if(!Validar()){
			alert('Debe ingresar todos los campos obligatorios');
		}else{
			if (document.forms[0].fechafinal.value != ''){
				if(Comparar_Fecha(document.forms[0].fechainicial.value,  document.forms[0].fechafinal.value)){	
					document.forms[0].cmd.value='update';
					document.forms[0].btnSave.disabled = false;
					document.forms[0].submit();
				}
				else
					alert("Las fechas de Vigencias no son validas");	
			}
			else{
				document.forms[0].cmd.value='update';
				document.forms[0].btnSave.disabled = false;
				document.forms[0].submit();
			}				
		}
	}
	function changeAction(){			
		document.forms[0].cmd.value='changeAction';
		document.forms[0].submit();
	}
	function desabButtons(){
		document.forms[0].btnSave.disabled = false;
		if(document.forms[0].btnChange.value == 'Activar'){	
			document.forms[0].botonEliminar.disabled = false;					
		}			
		else{		
			document.forms[0].botonEliminar.disabled=true;
		}
	}
	function nuevasFechas(auto,email,pass){
		var idCarrera=document.forms[0].idCarrera.value;
		var planEstudio=document.forms[0].planestudio.value;
		link = "Vigencia.do?cmd=inicio&idCarrera=" + idCarrera;
		link = link + "&planEstudio=" + planEstudio;
		open(link,"ventana","toolbar=no,directories=no,menubar=no, noresize,width=600,height=300");
	}
	
	function Comparar_Fecha(String1, String2) {
		// Si los dias y los meses llegan con un valor menor que 10 
		// Se concatena un 0 a cada valor dentro del string 
		if (String1.substring(1,2)=="/") {
		String1="0"+String1
		}
		if (String1.substring(4,5)=="/"){
		String1=String1.substring(0,3)+"0"+String1.substring(3,9)
		}
		
		if (String2.substring(1,2)=="/") {
		String2="0"+String2
		}
		if (String2.substring(4,5)=="/"){
		String2=String2.substring(0,3)+"0"+String2.substring(3,9)
		}
		
		dia1=String1.substring(0,2);
		mes1=String1.substring(3,5);
		anyo1=String1.substring(6,10);
		dia2=String2.substring(0,2);
		mes2=String2.substring(3,5);
		anyo2=String2.substring(6,10);
		
		
		if (dia1 == "08") // parseInt("08") == 10 base octogonal
		dia1 = "8";
		if (dia1 == '09') // parseInt("09") == 11 base octogonal
		dia1 = "9";
		if (mes1 == "08") // parseInt("08") == 10 base octogonal
		mes1 = "8";
		if (mes1 == "09") // parseInt("09") == 11 base octogonal
		mes1 = "9";
		if (dia2 == "08") // parseInt("08") == 10 base octogonal
		dia2 = "8";
		if (dia2 == '09') // parseInt("09") == 11 base octogonal
		dia2 = "9";
		if (mes2 == "08") // parseInt("08") == 10 base octogonal
		mes2 = "8";
		if (mes2 == "09") // parseInt("09") == 11 base octogonal
		mes2 = "9";
		
		dia1=parseInt(dia1);
		dia2=parseInt(dia2);
		mes1=parseInt(mes1);
		mes2=parseInt(mes2);
		anyo1=parseInt(anyo1);
		anyo2=parseInt(anyo2);
				
		if (anyo1>anyo2)
		{
		return false;
		}
		
		if ((anyo1==anyo2) && (mes1>mes2))
		{
		return false;
		}
		if ((anyo1==anyo2) && (mes1==mes2) && (dia1>dia2))
		{
		return false;
		}		
		return true;
	}
	function isNumberKey(evt){
		var charCode = (evt.which) ? evt.which : event.keyCode         
		if (charCode > 31 && (charCode < 48 || charCode > 57))            
			return false;          
		return true;
	}	
	function soloNumeros(){
		var key=window.event.keyCode;
		if (key < 48 || key > 57){
			window.event.keyCode=0;
		}
	}	
	$(document).ready(function(){$.NiceJForms.build()});
</script>
</head>
<body onload="document.forms[0].btnSave.disabled = false;">
<html:form action="/mtoCarr" styleClass="niceform" >
<html:hidden property="cmd" value=""/>
	<div id="wrapper">
		<div id="header">
			<table>
				<tr>
					<td style="width: 25px; background-color: #000000; text-align: center; vertical-align: middle;">
						<img src="${ctx}/images/login/SALDEI.png"/>	</td>
					<td style="width: 825px; text-align: center; vertical-align: middle;"> 
						<img src="${ctx}/images/uca/labos.PNG" />	</td>
					<td style="width: 100px; background-color: #000000;">
						<table>
							<tr>
								<td style=" vertical-align: middle; text-align: center;" colspan="2"> <img src="${ctx}/images/login/User1.png" alt="<%= user %>"/>	</td>
							</tr>		
							<tr>
								<td style="font-size:14px; font-weight:bold; vertical-align: middle;"> Usuario: 	 </td>
								<td style="font-size:18px; color:#439FD7; font-weight:bold; vertical-align: middle;"> <%= user %>	 </td>
							</tr>
							<tr>
								<td style="font-size:18px; color:#439FD7; font-weight:bold; vertical-align: middle;">  <a href='/SALDEI/common.do?cmd=bienvenida'>    <img src="${ctx}/images/login/HomePage1.png" alt="Inicio"/></a></td>
								<td style="font-size:18px; color:#439FD7; font-weight:bold; vertical-align: middle;">  <a href='/SALDEI/common.do?cmd=inicio'>    <img src="${ctx}/images/login/Shutdown2.png" alt="Salir SALDEI"/></a></td>
							</tr>							
						</table>
					</td>
				</tr>				
			</table>    		    	
		    <div id="menu"> <%= menu %> </div>
		</div>
		<div id="container"><br/> <br/><br/>
			<div id="titule"><bean:message key="carrera.lbl.titulo"/></div><br/>
			<table>
				<tr>
					<td align="center">
					<fieldset>
						<logic:present name="modulesCarrera" scope="session">
							<legend><%= (String) request.getSession().getAttribute("modulesCarrera") %></legend>
						</logic:present><br/>
						<table align="center">
							<tr>
								<td align="left">ID Carrera: <bean:message key="commons.msg.*"/></td>
								<td align="center">	<html:text property="idCarrera" size="10" value="${requestScope.carrFormJsp.idCarrera}" maxlength="4" onkeypress="return isNumberKey(event)"/>
								<html:hidden property="nomCarreraHid" value="${requestScope.carrFormJsp.idCarrera}"/></td>
							</tr>		
							<tr>
								<td align="left">Nombre Carrera: <bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="nombreCarrera" size="25" value="${requestScope.carrFormJsp.nombreCarrera}" maxlength="50"/>	</td>
							</tr>					
							<tr>
								<td align="left">Facultad: <bean:message key="commons.msg.*"/></td>
								<td align="left">
									<html:select styleId="idFacultad"  property="idFacultad" value="${requestScope.carrFormJsp.idFacultad}" styleClass="width_300">
										<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
										<html:options collection="facultad" property="idMulticode" labelProperty="descripcion"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="left">Plan de Estudio: <bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="planestudio" size="10" value="${requestScope.carrFormJsp.planestudio}" maxlength="4" onkeypress="return isNumberKey(event)"/></td>
							</tr>					
							<tr>						
								<td align="left">Fecha Inicial: <bean:message key="commons.msg.*"/></td>
								<td align="left"><input type="text" name="fechainicial" id="fechainicial" readonly="readonly" size="10" value="${requestScope.carrFormJsp.fechainicial}" /><input type="button" value="....." id="btnFecha1" onmousedown="verCalen1()" class="ButtonText"/></td>
							</tr>	
							<tr>						
								<td align="left">Fecha Final:</td>
								<td align="left"><input type="text" name="fechafinal" id="fechafinal" readonly="readonly" size="10" value="${requestScope.carrFormJsp.fechafinal}"/><input type="button" value="....." id="btnFecha2" onmousedown="verCalen2()" class="ButtonText"/></td>
							</tr>	
							<tr align="center">					
								<td align=""right"" colspan="2"><bean:message key="commons.msg.obligatorio"/><br/><br/></td>			
							</tr>			
							<tr>
								<td align="center" colspan="2">
									<%= (String) request.getSession().getAttribute("primeroCarrera") %>
									<%= (String) request.getSession().getAttribute("segundoCarrera") %>
									<input type="submit" onclick="cmd.value='find'"  value='<%= (String) request.getSession().getAttribute("terceroCarrera") %>' class="ButtonText"/>
									<input type="button" value='<%= (String) request.getSession().getAttribute("cuartoCarrera") %>' class="ButtonText" onclick="cleanForm();"/>
									<html:submit value="<%= (String) request.getSession().getAttribute("quintoCarrera") %>" onclick="cmd.value='changeAction'" styleClass="ButtonText"/>
									<html:button property="btnNuevasFechas"  styleId="btnNuevasFechas"  disabled="true"  onclick="nuevasFechas();" >Nuevas Fechas de Vigencia</html:button>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<logic:present name="cfMsg" scope="request">
										<logic:equal value="vacio" name="cfMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/></logic:equal>
										<logic:equal value="numCodigo" name="cfMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="laboratorio.lbl.numCodigo"/></logic:equal>
										<logic:equal value="exitoSave" name="cfMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></logic:equal>
										<logic:equal value="errorSave" name="cfMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></logic:equal>	
										<logic:equal value="vacioUpdate" name="cfMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar.lista"/></logic:equal>
										<logic:equal value="exitoUpdate" name="cfMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></logic:equal>
										<logic:equal value="errorUpdate" name="cfMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></logic:equal>	
										<logic:equal value="error" name="cfMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/></logic:equal>	
										<logic:equal value="saveAllExito" name="cfMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.guardarTodos.exito"/></logic:equal>
										<logic:equal value="saveAllNull" name="cfMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.guardarTodos.error"/></logic:equal>
										<logic:equal value="vacioLista" name="cfMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.lbl.listas.vacio"/></logic:equal>	
									</logic:present>
								</td>
							</tr>
						</table>
					</fieldset>			
					</td>
				</tr>
			</table>					
		</div>
		<div id="leftcolumn"> 
			<logic:present name="listCarreraActivas" scope="session">
			<div id="cap"><bean:message key="carrera.lbl.display.activo"/> </div>
				<display:table name="sessionScope.listCarreraActivas" pagesize="10" id="dispalyListCarreraAct" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/carrera.jsp" sort="list" defaultsort="2">
					<display:column property="idCarrera" title="ID" 		   style="width:15%; text-align:center;" sortable="true" />
					<display:column property="nombre"    title="Nombre" 	   style="width:25%; text-align:left;"   sortable="true" />
					<display:column property="facultad"  title="Facultad" 	   style="width:35%; text-align:left;"   sortable="true" />
					<display:column property="accion"    title="Acci&oacute;n" style="width:25%; text-align:center;"                 />				
				</display:table>
			</logic:present>		
		</div>
		<div id="rightcolumn">
			<logic:present name="listCarreraInactivas" scope="session">
					<div id="cap">		<bean:message key="carrera.lbl.display.inactivo"/> </div>
				<display:table name="sessionScope.listCarreraInactivas" pagesize="10" id="dispalyListCarreraInac" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/carrera.jsp" sort="list" defaultsort="2">
					<display:column property="idCarrera" title="ID" 		   style="width:15%; text-align:center;" sortable="true" />
					<display:column property="nombre"    title="Nombre" 	   style="width:25%; text-align:left;"   sortable="true" />
					<display:column property="facultad"  title="Facultad" 	   style="width:35%; text-align:left;"   sortable="true" />
					<display:column property="accion"    title="Acci&oacute;n" style="width:25%; text-align:center;"                 />				
				</display:table>
			</logic:present>
		</div>
		<div id="footer">
			<img src="${ctx}/images/login/foot.PNG"/><br/>
			Version 1.0
		</div>
	</div>		
</html:form>	
</body>
</html:html>
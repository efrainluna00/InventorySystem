<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<html:html>
<head>
<script type="text/javascript">
	function cleanForm(){
		document.forms[0].idLaboratorio.value = '';
		document.forms[0].nombreLaboratorio.value = '';
		document.forms[0].abrevLaboratorio.value = '';
		document.forms[0].numFilas.value = '';
		document.forms[0].numColumnas.value = '';
		document.forms[0].cmd.value = 'limpiar';
		document.forms[0].submit();
	}	
	function modificar(jsId, jsNombre, jsAbrev, jsFilas, jsColumnas){
		document.forms[0].idLaboratorio.value = jsId;
		document.forms[0].nombreLaboratorio.value = jsNombre;
		document.forms[0].abrevLaboratorio.value = jsAbrev;
		document.forms[0].numFilas.value = jsFilas;
		document.forms[0].numColumnas.value = jsColumnas;
		document.forms[0].btnSave.disabled = true;
		jQuery.NiceJForms.cambio('btnSave');
	}
	function saveFunction(){
		var c = confirm('Desea guardar los cambios?');
		if(c){
			document.forms[0].idLaboratorio.value = '';
			document.forms[0].nombreLaboratorio.value = '';
			document.forms[0].abrevLaboratorio.value = '';
			document.forms[0].numFilas.value = '';
			document.forms[0].numColumnas.value = '';
			document.forms[0].cmd.value='save';
			document.forms[0].submit();
		}
	}	
	function saveOutFunction(){
		if(!Validar()){
			alert('Debe ingresar todos los campos obligatorios');
		}else{
		document.forms[0].cmd.value='save';
		document.forms[0].submit();
		}
	}
	function Validar(){
		if (document.forms[0].nombreLaboratorio.value == '')
			return false;
		if (document.forms[0].abrevLaboratorio.value == 'Seleccione')
			return false;
			if (document.forms[0].numFilas.value == '')
			return false;
		if (document.forms[0].numColumnas.value == 'Seleccione')
			return false;
		return true; 	
	}	
	function updateFunction(){
		var c = confirm('Desea cancelar los cambios?');
		if(c){
			document.forms[0].idLaboratorio.value = '';
			document.forms[0].nombreLaboratorio.value = '';
			document.forms[0].abrevLaboratorio.value = '';
			document.forms[0].numFilas.value = '';
			document.forms[0].numColumnas.value = '';
			document.forms[0].cmd.value='update';
			document.forms[0].submit();
		}
	}	
	function updateOutFunction(){
		if(!Validar()){
			alert('Debe ingresar todos los campos obligatorios');
		}else{
		document.forms[0].cmd.value='update';
		document.forms[0].btnSave.disabled = false;
		document.forms[0].submit();			
		}
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
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<body onload="document.forms[0].btnSave.disabled = false;">
<html:form action="/mtoLab" styleClass="niceform" >
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
		<div id="container"><br/> <br/>
			<div id="titule"><bean:message key="laboratorioMto.titulo"/></div><br/>
			<table>
				<tr>
					<td align="center">
					<fieldset>
						<logic:present name="modulesLaboratorio" scope="session">
							<legend><%= (String) request.getSession().getAttribute("modulesLaboratorio") %></legend>
						</logic:present>	
						<table align="center">
							<tr>
								<td align="left"><bean:message key="commons.lbl.nombre"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="nombreLaboratorio" size="25" value="${requestScope.labFormJsp.nombreLaboratorio}" maxlength="50"/> 
								<html:hidden property="idLaboratorio" value="${requestScope.labFormJsp.nombreLaboratorio}"/> </td>
							</tr>					
							<tr>
								<td align="left"><bean:message key="medidaMto.abreviatura"/></td>
								<td align="center"><html:text property="abrevLaboratorio" size="7" value="${requestScope.labFormJsp.abrevLaboratorio}" maxlength="10"/></td>
							</tr>						
							<tr>
								<td align="left"><bean:message key="laboratorio.lbl.numFilas"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="numFilas" size="7" value="${requestScope.labFormJsp.numFilas}" maxlength="2" onkeypress="return isNumberKey(event)"/></td>
							</tr>
							<tr>
								<td align="left"><bean:message key="laboratorio.lbl.numColumnas"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="numColumnas" size="7" value="${requestScope.labFormJsp.numColumnas}" maxlength="2" onkeypress="return isNumberKey(event)"/></td>
							</tr>
							<tr align="center">					
								<td align=""right"" colspan="2"><bean:message key="commons.msg.obligatorio"/><br/><br/></td>			
							</tr>
							<tr>
								<td align="center" colspan="2">
									<%= (String) request.getSession().getAttribute("primeroLab") %>
									<%= (String) request.getSession().getAttribute("segundoLab") %>
									<input type="submit" onclick="cmd.value='find'"  value='<%= (String) request.getSession().getAttribute("terceroLab") %>' class="ButtonText"/>
									<input type="button" value='<%= (String) request.getSession().getAttribute("cuartoLab") %>' class="ButtonText" onclick="cleanForm();"/>
									<html:submit value="<%= (String) request.getSession().getAttribute("quintoLab") %>" onclick="cmd.value='changeAction'" styleClass="ButtonText"/>
									<html:submit value="Generar Reporte" onclick="cmd.value='generarReporte'" styleClass="ButtonText"/>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<logic:present name="laboMsg" scope="request">
										<logic:equal value="vacio" name="laboMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/></logic:equal>
										<logic:equal value="numCodigo" name="laboMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="laboratorio.lbl.numCodigo"/></logic:equal>
									    <logic:equal value="exitoSave" name="laboMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></logic:equal>
										<logic:equal value="errorSave" name="laboMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></logic:equal>	
										<logic:equal value="vacioUpdate" name="laboMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar.lista"/></logic:equal>
										<logic:equal value="exitoUpdate" name="laboMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></logic:equal>
										<logic:equal value="errorUpdate" name="laboMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></logic:equal>	
										<logic:equal value="error" name="laboMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/></logic:equal>	
										<logic:equal value="saveAllExito" name="laboMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.guardarTodos.exito"/></logic:equal>
										<logic:equal value="saveAllNull" name="laboMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.guardarTodos.error"/></logic:equal>
										<logic:equal value="vacioLista" name="laboMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.lbl.listas.vacio"/></logic:equal>	
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
			<logic:present name="listLabActivas" scope="session">
				<div id="cap"><bean:message key="laboratorio.lbl.legend.activos"/></div>
				<display:table name="sessionScope.listLabActivas" pagesize="10" id="dispalyListLaboratorioAct" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/laboratorios.jsp" sort="list" defaultsort="1">
					<display:column property="nombreLaboratorio" title="Nombre"        style="width:45%; text-align:center;" sortable="true" />
					<display:column property="abrevLaboratorio"  title="Abreviatura"   style="width:15%; text-align:center;" sortable="true" />
					<display:column property="numFilas" 		 title="Filas"         style="width:10%; text-align:center;" sortable="true" />
					<display:column property="numColumnas"  	 title="Columnas"      style="width:10%; text-align:center;" sortable="true" />
					<display:column property="accion" 			 title="Acci&oacute;n" style="width:20%; text-align:center;"                 />
				</display:table>
			</logic:present>
		</div>
		<div id="rightcolumn">
			<logic:present name="listLabInactivas" scope="session">
				<div id="cap"><bean:message key="laboratorio.lbl.legend.inactivos"/></div>
				<display:table name="sessionScope.listLabInactivas" pagesize="10" id="dispalyListLaboratorioInac" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/laboratorios.jsp" sort="list" defaultsort="1">
					<display:column property="nombreLaboratorio" title="Nombre"        style="width:45%; text-align:center;" sortable="true" />
					<display:column property="abrevLaboratorio"  title="Abreviatura"   style="width:15%; text-align:center;" sortable="true" />
					<display:column property="numFilas" 		 title="Filas"         style="width:10%; text-align:center;" sortable="true" />
					<display:column property="numColumnas"  	 title="Columnas"      style="width:10%; text-align:center;" sortable="true" />
					<display:column property="accion" 			 title="Acci&oacute;n" style="width:20%; text-align:center;"                 />
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
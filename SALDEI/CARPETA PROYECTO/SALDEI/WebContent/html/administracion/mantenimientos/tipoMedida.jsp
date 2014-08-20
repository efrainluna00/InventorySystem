<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/commons.jsp" %>
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<html:html>
<head>
<script type="text/javascript">
	function modificar(jsNombre){
		document.forms[0].nombre.value = jsNombre;
		document.forms[0].codigo.value = jsNombre;
		document.forms[0].btnSave.disabled = true;
		jQuery.NiceJForms.cambio('btnSave');
	}	
	function cleanForm(){
		document.forms[0].nombre.value = '';
		document.forms[0].cmd.value = 'limpiar';
		document.forms[0].submit();
	}	
	function saveFunction(){
		var c = confirm('Desea guardar los cambios?');
		if(c){
			document.forms[0].nombre.value = '';
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
		if (document.forms[0].nombre.value == '')
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
	function updateOutFunction(){
	if(!Validar()){
			alert('Debe ingresar todos los campos obligatorios');
		}else{
			document.forms[0].btnSave.disabled = false;
			document.forms[0].cmd.value='update';
			document.forms[0].submit();
		}
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
	function deleteFunction(){
		var c = confirm('Desea eliminar el registro?');
		if(c){
			document.forms[0].cmd.value='delete';
			document.forms[0].submit();
		}
	}	
	$(document).ready(function(){$.NiceJForms.build()});	
</script>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<body onload="desabButtons();">
<html:form action="/mtoTipMed" styleClass="niceform">
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
		<div id="container"><br/><br/>
			<div id="titule"><bean:message key="tipoMedidaMto.titulo"/></div><br/>
			<table>
				<tr>
					<td>
					<fieldset>			
						<logic:present name="modulesTipoMedida" scope="session">
							<legend><%= (String) request.getSession().getAttribute("modulesTipoMedida") %></legend>
						</logic:present><br/>	
						<table align="center">
							<tr>
								<td align="left"><bean:message key="tipoMedidaMto.nombre"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="nombre" size="40" value="${requestScope.tipoMedidaFormJsp.nombre}" maxlength="60"/>
												   <html:hidden property="codigo"/>	</td>
							</tr>
							<tr align="center">					
								<td align=""right"" colspan="2"><bean:message key="commons.msg.obligatorio"/><br/><br/></td>			
							</tr>
							<tr>
								<td align="center" colspan="2">
									<%= (String) request.getSession().getAttribute("primeroTipoMed") %>
									<%= (String) request.getSession().getAttribute("segundoTipoMed") %>
									<input type="submit" onclick="cmd.value='find'"  value='<%= (String) request.getSession().getAttribute("terceroTipoMed") %>' class="ButtonText"/>
									<input type="button" value='<%= (String) request.getSession().getAttribute("cuartoTipoMed") %>' class="ButtonText" onclick="cleanForm();"/>
									<input type="submit" name="btnChange" id="btnChange" value="<%= (String) request.getSession().getAttribute("quintoTipoMed") %>" onclick="cmd.value='changeAction'" class="ButtonText"/>
									<html:submit value="Generar Reporte" onclick="cmd.value='generarReporte'" styleClass="ButtonText"/>
									<input type="button" value="Eliminar" class="ButtonText" onclick="deleteFunction();" name="botonEliminar" id="botonEliminar"/>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">	
									<logic:present name="mtipMsg" scope="request">
										<logic:equal value="exitoSave" name="mtipMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></logic:equal>
										<logic:equal value="errorSave" name="mtipMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></logic:equal>
										<logic:equal value="errorUpdate" name="mtipMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.obligatorio"/></logic:equal>				
										<logic:equal value="vacioUpdate" name="mtipMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/></logic:equal>
										<logic:equal value="exitoUpdate" name="mtipMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></logic:equal>
										<logic:equal value="vacio" name="mtipMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/></logic:equal>	
										<logic:equal value="saveAllExito" name="mtipMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.guardarTodos.exito"/></logic:equal>
										<logic:equal value="saveAllNull" name="mtipMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.guardarTodos.error"/></logic:equal>
										<logic:equal value="error" name="mtipMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/></logic:equal>
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
			<logic:present name="listTipoMedidaActivas" scope="session">
				<div id="cap"><bean:message key="tipoMedidaMto.activos"/></div>
				<display:table name="sessionScope.listTipoMedidaActivas" pagesize="10" id="dispalyListTipoMedAct" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/tipoMedida.jsp" sort="list" defaultsort="1">
					<display:column property="nombre" title="Nombre" 		style="width:80%; text-align:center;" sortable="true" />
					<display:column property="accion" title="Acci&oacute;n" style="width:20%; text-align:center;" 				   />
				</display:table>
			</logic:present>
		</div>
		<div id="rightcolumn">
			<logic:present name="listTipoMedidaInactivas" scope="session">
				<div id="cap"><bean:message key="tipoMedidaMto.inactivos"/></div>
				<display:table name="sessionScope.listTipoMedidaInactivas" pagesize="10" id="dispalyListTipoMedInac" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/tipoMedida.jsp" sort="list" defaultsort="1">
					<display:column property="nombre" title="Nombre" 		style="width:80%; text-align:center;" sortable="true" />
					<display:column property="accion" title="Acci&oacute;n" style="width:20%; text-align:center;" 				   />
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
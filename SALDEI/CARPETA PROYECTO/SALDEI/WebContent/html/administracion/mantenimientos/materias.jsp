<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/commons.jsp" %>
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp"  %>
<html:html>
<head>
<script type="text/javascript">
	function cleanForm(){
		document.forms[0].codigo.value = '';
		document.forms[0].nombre.value = '';
		document.forms[0].desc.value = '';
		document.forms[0].uv.value = '';
		document.forms[0].cmd.value = 'limpiar';
		document.forms[0].submit();
	}	
	function modificar(jsCodigo, jsNombre, jsDesc, jsUv) {
		document.forms[0].codigo.value = jsCodigo;
		document.forms[0].nombre.value = jsNombre;
		document.forms[0].desc.value = jsDesc;
		document.forms[0].codigoHidden.value = jsCodigo;
		document.forms[0].uv.value = jsUv;
		jQuery.NiceJForms.cambio('btnSave');
		estBtn();
	}
	function saveFunction(){
		var c = confirm('Desea guardar los cambios?');
		if(c){
			document.forms[0].codigo.value = '';
			document.forms[0].nombre.value = '';
			document.forms[0].desc.value = '';
			document.forms[0].uv.value = '';
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
	function updateFunction(){
		var c = confirm('Desea cancelar los cambios?');
		if(c){
			document.forms[0].cmd.value='update';
			document.forms[0].submit();
		}
	}	
	function Validar(){
		if (document.forms[0].codigo.value == '')
			return false;
		if (document.forms[0].nombre.value == '')
			return false;
		if (document.forms[0].uv.value == '')
			return false;			
		return true; 	
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
	function estBtn(){
		document.forms[0].btnSave.disabled = true;
		document.forms[0].btnSave.readonly = 'readonly';
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
<html:form action="/mtoMat"  styleClass="niceform">
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
			<div id="titule"> <bean:message key="materiasMto.titulo"/></div><br/>
			<table>
				<tr>
					<td>
					<fieldset>			
						<logic:present name="modulesMateria" scope="session">
							<legend><%= (String) request.getSession().getAttribute("modulesMateria") %></legend>
						</logic:present>
						<table align="center">
							<tr>
								<td align="left"><bean:message key="commons.lbl.codigo"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="codigo" size="6" maxlength="6" value="${requestScope.materiaFormJsp.codigo}" onkeypress="return isNumberKey(event)"/>
							                       <html:hidden property="codigoHidden" value="<%= (String) request.getAttribute("codigo") %>"/> </td>
							</tr>					
							<tr>
								<td align="left"><bean:message key="materiasMto.nombre"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="nombre" size="35" value="${requestScope.materiaFormJsp.nombre}" maxlength="75"/></td>
							</tr>						
							<tr>
								<td align="left"><bean:message key="materiasMto.descripcion"/></td>
								<td align="center"><html:textarea property="desc" value="${requestScope.materiaFormJsp.desc}" cols="36" rows="6" /></td>
							</tr>
							<tr>
								<td align="left"><bean:message key="materiasMto.lbl.unidades.valorativas"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="uv" size="6" maxlength="1" value="${requestScope.materiaFormJsp.uv}" onkeypress="return isNumberKey(event)"/></td>
							</tr>
							<tr align="center">					
								<td align=""right"" colspan="2"><bean:message key="commons.msg.obligatorio"/><br/><br/></td>			
							</tr>
							<tr>
								<td align="center" colspan="2">
									<%= (String) request.getSession().getAttribute("primeroMat") %>
									<%= (String) request.getSession().getAttribute("segundoMat") %>
									<input type="submit" onclick="cmd.value='find'"  value='<%= (String) request.getSession().getAttribute("terceroMat") %>' class="ButtonText"/>
									<input type="button" value='<%= (String) request.getSession().getAttribute("cuartoMat") %>' class="ButtonText" onclick="cleanForm();"/>
									<html:submit value="<%= (String) request.getSession().getAttribute("quintoMat") %>" onclick="cmd.value='changeAction'" styleClass="ButtonText"/>
									<html:submit value="Generar Reporte" onclick="cmd.value='generarReporte'" styleClass="ButtonText"/>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<logic:present name="matMsg" scope="request">
										<logic:equal value="vacio" name="matMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/></logic:equal>
										<logic:equal value="exitoSave" name="matMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></logic:equal>
										<logic:equal value="errorSave" name="matMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></logic:equal>	
										<logic:equal value="vacioUpdate" name="matMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar.lista"/></logic:equal>
										<logic:equal value="exitoUpdate" name="matMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></logic:equal>
										<logic:equal value="errorUpdate" name="matMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></logic:equal>	
										<logic:equal value="numCodigo" name="matMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="materiasMto.numeroCodigo"/></logic:equal>
										<logic:equal value="error" name="matMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/></logic:equal>	
										<logic:equal value="saveAllExito" name="matMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.guardarTodos.exito"/></logic:equal>
										<logic:equal value="saveAllNull" name="matMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.guardarTodos.error"/></logic:equal>
										<logic:equal value="vacioLista" name="matMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.lbl.listas.vacio"/></logic:equal>	
									</logic:present>
								</td>
							<tr>
						</table>
					</fieldset>			
					</td>
				</tr>
			</table>
		</div>
		<div id="leftcolumn"> 
			<logic:present name="listMatActivas" scope="session">
				<div id="cap"><bean:message key="materiasMto.lbl.activos"/></div>
				<display:table name="sessionScope.listMatActivas" pagesize="10" id="dispalyListMatAct" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/materias.jsp" sort="list" defaultsort="2">
					<display:column property="codigo" 		title="C&oacute;digo"        style="width:10%; text-align:center;" sortable="true" />
					<display:column property="nombre" 		title="Nombre" 				 style="width:30%; text-align:center;" sortable="true" />
					<display:column property="descripcion" 	title="Descripci&oacute;n"   style="width:35%; text-align:left;"   sortable="true" />
					<display:column property="uv" 			title="Unidades Valorativas" style="width:5%;  text-align:center;" sortable="true" />
					<display:column property="accion" 		title="Acci&oacute;n"        style="width:20%; text-align:center;" 				  />
				</display:table>
			</logic:present>
		</div>
		<div id="rightcolumn">
			<logic:present name="listMatInactivas" scope="session">
				<div id="cap"><bean:message key="materiasMto.lbl.inactivos"/></div>
				<display:table name="sessionScope.listMatInactivas" pagesize="10" id="dispalyListMatInac" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/materias.jsp" sort="list" defaultsort="2">
					<display:column property="codigo" 		title="C&oacute;digo"        style="width:10%; text-align:center;" sortable="true" />
					<display:column property="nombre" 		title="Nombre" 				 style="width:30%; text-align:center;" sortable="true" />
					<display:column property="descripcion" 	title="Descripci&oacute;n"   style="width:35%; text-align:left;"   sortable="true" />
					<display:column property="uv" 			title="Unidades Valorativas" style="width:5%;  text-align:center;" sortable="true" />
					<display:column property="accion" 		title="Acci&oacute;n"        style="width:20%; text-align:center;" 				  />
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
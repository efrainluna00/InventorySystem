<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<html:html>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
<script type="text/javascript">
	function cleanForm(){	
		document.forms[0].codigo.value = '';
		document.forms[0].codigoHid.value = '';
		document.forms[0].nombre.value = '';		
		document.forms[0].desc.value = '';
		document.forms[0].estado.value = 'Seleccione';
		document.forms[0].cmd.value='inicio';
		document.forms[0].submit();
	}
	function deleteElem() 
	{
		action = confirm("");
		if (action) {
			document.forms[0].cmd.value = 'guardarCambios';
			document.forms[0].submit();			
		}
	}
		function save(){
		if( document.forms[0].btnSave.value == 'Guardar'){
			if(!Validar()){
				alert ("Debe de Ingresar los Datos validos");
			}
			else{
				document.forms[0].cmd.value = 'save';
				document.forms[0].submit();
			}
		}
		else{
			action = confirm("¿Desea guardar los cambios?");
			if(action){
				document.forms[0].cmd.value = 'guardarCambios';
				document.forms[0].submit();
			}
		}
	}
	function update(){
		if( document.forms[0].btnUpdate.value == 'Actualizar'){
			if(!Validar()){
				alert ("Debe de Ingresar los Datos validos");
			}
			else{
				document.forms[0].cmd.value = 'update';
				document.forms[0].submit();
			}
		}else{
			action = confirm("¿Desea cancelar los cambios?");
			if(action){
				document.forms[0].cmd.value = 'cancelarCambios';
				document.forms[0].submit();
			}
		}
	}
	function find(){
		if( document.forms[0].btnSave.value == 'Guardar'){
			document.forms[0].cmd.value = 'findActualizar';
			document.forms[0].submit();
		}
		else{
			document.forms[0].cmd.value = 'findRemover';
			document.forms[0].submit();
		}	
	}	
	function clean(){
		document.forms[0].codigo.value = '';
		document.forms[0].codigoHid.value = '';
		document.forms[0].nombre.value = '';		
		document.forms[0].desc.value = '';
		document.forms[0].cmd.value = 'limpiar';
		document.forms[0].submit();
	}
	function Validar(){
		if (document.forms[0].nombre.value == '')
			return false;
		if( document.forms[0].desc.value == '')
			return false;			
		return true; 	
	}	
	
	function remove(){
		if( document.forms[0].btnRem.value == 'Activar'){
			document.forms[0].cmd.value = 'remover';
			document.forms[0].submit();
		} 
		if( document.forms[0].btnRem.value == 'Modificar'){
			document.forms[0].cmd.value = 'inicio';
			document.forms[0].submit();
		}
	}		
	function modificar(jsCodigo, jsNombre, jsDesc, jsEstado){
		document.forms[0].codigo.value = jsCodigo;
		document.forms[0].codigoHid.value = jsCodigo;
		document.forms[0].nombre.value = jsNombre;		
		document.forms[0].desc.value = jsDesc;
		document.forms[0].estado.value = jsEstado;
		document.forms[0].btnSave.disabled = true;
		jQuery.NiceJForms.cambio('btnSave');
	}	
		$(document).ready(function(){$.NiceJForms.build()});
	</script>
</head>
<body onload="document.forms[0].btnSave.disabled = false;">
<html:form action="/perfil"  styleClass="niceform"  >
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
			<div id="titule"> <bean:message key="perfilMto.lbl.titulo"/> </div><br/>
			<table>
				<tr>
					<td align="center">
					<fieldset>
						<logic:present name="modulesPerfil" scope="session">
							<legend><%=(String) request.getSession().getAttribute("modulesPerfil") %></legend>
						</logic:present>
						<table align="center">
							<tr>						
								<td><html:hidden property="codigoHid" value="<%= (String)request.getAttribute("codigo")%>"/></td>
								<td><html:hidden property="codigo"    value="<%= (String)request.getAttribute("codigo")%>"/></td>						
							</tr>					
							<tr>
								<td align="center"><bean:message key="perfilMto.lbl.nombre"/><bean:message key="commons.msg.*" /></td>
								<td align="center"><html:text  property="nombre" value="<%= (String)request.getAttribute("nombre")%>" size="25" maxlength="50"/></td>
							</tr>						
							<tr>
								<td align="center"><bean:message key="perfilMto.lbl.descripcion"/><bean:message key="commons.msg.*" /></td>								
								<td align="center"><html:textarea property="desc" value="<%= (String) request.getAttribute("desc") %>" rows="8" cols="26"/>
								<html:hidden property="estado" value="<%=(String)request.getAttribute("estado")%>"/>
								</td>
							</tr>
							<tr align="right">					
								<td align=""right"" colspan="2"> <bean:message key="commons.msg.obligatorio"/><br/><br/></td>			
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input type="button" id="btnSave"    name="btnSave"    onclick="save();"   value= "<%=(String) request.getSession().getAttribute("btnSave") %>"/>
									<input type="button" id="btnUpdate"  name="btnUpdate"  onclick="update();" value= "<%=(String) request.getSession().getAttribute("btnUpdate") %>"/>
									<input type="button" id="btnFind"    name="btnFind"    onclick="find();"   value= "<%=(String) request.getSession().getAttribute("btnFind") %>"/>
									<input type="button" id="btnClean"   name="btnClean"   onclick="clean();"  value= "<%=(String) request.getSession().getAttribute("btnClean") %>"/>
									<input type="button" id="btnRem"     name="btnRem"     onclick="remove();" value= "<%=(String) request.getSession().getAttribute("btnRem") %>"/>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<logic:present scope="request" name="ccMsg">
										<logic:equal value="vacio" name="ccMsg"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.msg.obligatorio"/></center></logic:equal>
										<logic:equal value="exito" name="ccMsg"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></center></logic:equal>
										<logic:equal value="exitoUpd" name="ccMsg"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></center></logic:equal>
										<logic:equal value="errorSave" name="ccMsg"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></center></logic:equal>
										<logic:equal value="errorUpdate" name="ccMsg"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></center></logic:equal>	
										<logic:equal value="nocambio" name="ccMsg"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></center></logic:equal>	
									    <logic:equal value="copia" name="ccMsg"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></center></logic:equal>
									    <logic:equal value="exitoAll" name="ccMsg"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.guardarTodos.exito"/></center></logic:equal>	
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
			<logic:present scope="session" name="ccListActives">
				<div id="cap"><b><bean:message key="perfilMto.msg.activos"/></b></div>
				<display:table name="sessionScope.ccListActives" pagesize="10" sort="list" excludedParams="*" id="listaActivos" defaultsort="1">
					<display:column property="nombre" title="Nombre"      		 style="width:40%; text-align:left;" sortable="true"/>
					<display:column property="desc"   title="Descripci&oacute;n" style="width:40%; text-align:left;" sortable="true"/>									
					<display:column property="accion" title="Acci&oacute;n"      style="width:20%; text-align:center;"/>								
				</display:table>
			</logic:present>
		</div>
		<div id="rightcolumn">
			<logic:present scope="session" name="ccListInactives">
				<div id="cap"><b><bean:message key="perfilMto.msg.inactivos"/></b></div>
				<display:table name="sessionScope.ccListInactives" pagesize="10" sort="list" excludedParams="*" id="listaInactivos" defaultsort="1">
					<display:column property="nombre" title="Nombre"       		  style="width:40%; text-align:left;" sortable="true"/>
					<display:column property="desc"   title="Descripci&oacute;n"  style="width:40%; text-align:left;" sortable="true"/>																	
					<display:column property="accion" title="Acci&oacute;n"       style="width:20%; text-align:center;"/>								
				</display:table>
			</logic:present>
		</div>
		<div id="footer">
			<img src="${ctx}/images/login/foot.PNG"/><br/>
			Version 1.0
		</div>
	</div>	
</body>
</html:form>
</html:html>
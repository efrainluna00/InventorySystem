<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/commons.jsp" %>
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<html:html>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
<script type="text/javascript">
	function modificar(Codigo, Nombre, Desc,Estado){
		document.forms[0].codigo.value = Codigo;
		document.forms[0].nombre.value = Nombre;
		document.forms[0].desc.value = Desc;
		document.forms[0].codigoHid.value = Codigo;	
		document.forms[0].estado.value=Estado;
		document.forms[0].btnSave.disabled = true;
		jQuery.NiceJForms.cambio('btnSave');
	}	
	function cleanForm(){	
		document.forms[0].codigo.value = '';
		document.forms[0].codigoHid.value = '';
		document.forms[0].nombre.value = '';		
		document.forms[0].desc.value = '';
		document.forms[0].estado.value = 'Seleccione';
		document.forms[0].cmd.value='inicio';
		document.forms[0].submit();
	}
	function deleteElem() {
		action = confirm("");
		if (action) {
			document.forms[0].cmd.value = 'guardarCambios';
			document.forms[0].submit();			
		}
	}
	function save(){
		if( document.forms[0].btnSave.value == 'Guardar'){
		if(!Validar()){
			alert('Debe ingresar todos los campos obligatorios');
		}else{
				document.forms[0].cmd.value = 'save';
				document.forms[0].submit();
			}
		}
		else{
			action = confirm("Desea guardar los cambios?");
			if (action) {
				document.forms[0].cmd.value = 'guardarCambios';
				document.forms[0].submit();
			}
		}
	}	
	function update(){
		if( document.forms[0].btnUpdate.value == 'Actualizar'){
		if(!Validar()){
			alert('Debe ingresar todos los campos obligatorios');
		}else{
			    document.forms[0].btnSave.disabled = true;
				document.forms[0].cmd.value = 'update';
				document.forms[0].submit();
			}
		}else{
			action = confirm("Desea cancelar estos cambios?");
			if (action) {
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
	function ValidarReg(){
		if (document.forms[0].nombre.value == '')
			return false;
		if (document.forms[0].desc.value == '')
			return false;
		return true; 	
	}		
	function Validar(){
		if (document.forms[0].nombre.value == '')
			return false;		
		return true; 	
	}		
	function remove(){
		if( document.forms[0].btnRem.value == 'Activar'){
			document.forms[0].cmd.value = 'remover';
			document.forms[0].submit();
		} 
		if( document.forms[0].btnRem.value == 'Modificar'){
			document.forms[0].cmd.value = 'modificar';
			document.forms[0].submit();
		}
	}	
	function desabButtons(){
		document.forms[0].btnSave.disabled = false;
		if(document.forms[0].btnRem.value == 'Activar'){	
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
</head>
<body onload="desabButtons();">
<html:form action="/tipoMulticodeMto" styleClass="niceform">
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
			<div id="titule"> <bean:message key="tipoMulticodeMto.titulo"/></div><br/>
			<table>
				<tr>
					<td>
					<fieldset>			
						<logic:present name="modules" scope="session">
							<legend> <%= (String) request.getSession().getAttribute("modules") %></legend>
						</logic:present><br/>
						<table align="center">
							<tr>						
								<td><html:hidden property="codigoHid" value="<%= (String)request.getAttribute("codigo")%>"/></td>
								<td><html:hidden property="codigo" value="<%= (String)request.getAttribute("codigo")%>"/></td>						
							</tr>					
							<tr>
								<td align="center"><bean:message key="commons.lbl.nombre"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="nombre" value="${requestScope.tipoMulticodeFormJsp.nombre}" maxlength="15" size="25"/></td>
							</tr>						
							<tr>
								<td align="center"><bean:message key="commons.lbl.descripcion"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:textarea property="desc" value="${requestScope.tipoMulticodeFormJsp.desc}" cols="26" rows="8" /></td>
								
							</tr>					
							<tr>
								<td><html:hidden property="estado" value="<%=(String)request.getAttribute("estado")%>"/></td>						
							</tr>
							<tr align="center">					
								<td align=""right"" colspan="2"><bean:message key="commons.msg.obligatorio"/><br/><br/></td>			
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input type="button" id="btnSave"    name="btnSave"    onclick="save();"   value= "<%=(String) request.getSession().getAttribute("btnSave") %>"/>
									<input type="button" id="btnUpdate"  name="btnUpdate"  onclick="update();" value= "<%=(String) request.getSession().getAttribute("btnUpdate") %>"/>
									<input type="button" id="btnFind"    name="btnFind"    onclick="find();"   value= "<%=(String) request.getSession().getAttribute("btnFind") %>"/>
									<input type="button" id="btnClean"   name="btnClean"   onclick="clean();"  value= "<%=(String) request.getSession().getAttribute("btnClean") %>"/>
									<input type="button" id="btnRem"     name="btnRem"     onclick="remove();" value= "<%=(String) request.getSession().getAttribute("btnRem") %>"/>
									<html:submit value="Generar Reporte" onclick="cmd.value='generarReporte'"/>
									<input type="button" id="botonEliminar"     name="botonEliminar" value="Eliminar"    onclick="deleteFunction();" />
								</td>
							</tr>
							<tr>	
								<td align="center" colspan="2">					
									<logic:present scope="request" name="ccMsg">
										<logic:equal value="vacio" name="ccMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/></logic:equal>
										<logic:equal value="exitoSaveAll" name="ccMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.guardarTodos.exito"/></logic:equal>
										<logic:equal value="exito" name="ccMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></logic:equal>
										<logic:equal value="exitoUpd" name="ccMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></logic:equal>
										<logic:equal value="errorSave" name="ccMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></logic:equal>
										<logic:equal value="errorUpdate" name="ccMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></logic:equal>	
										<logic:equal value="nocambio" name="ccMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar.lista"/></logic:equal>	
										<logic:equal value="copia" name="ccMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></logic:equal>	
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
			<div id="cap"><bean:message key="tipoMulticodeMto.lbl.activos"/></div>
			<display:table name="sessionScope.ccListActives" sort="list" pagesize="10" id="listTipoMultiCodeActive" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/tipoMulticode.jsp" defaultsort="1">
					<display:column property="nombre" title="Nombre" 			 style="width:40%; text-align:left;" sortable="true" />
					<display:column property="desc"   title="Descripci&oacute;n" style="width:40%; text-align:left;" sortable="true" />
					<display:column property="accion" title="Acci&oacute;n"      style="width:20%; text-align:center;" 			    />										
			</display:table>		
	</logic:present>
		</div>
		<div id="rightcolumn">
			<logic:present scope="session" name="ccListInactives">		
				<div id="cap"><bean:message key="tipoMulticodeMto.lbl.inactivos"/></div>
				<display:table name="sessionScope.ccListInactives" sort="list" pagesize="10" id="listTipoMulticodeInactivos" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/tipoMulticode.jsp" defaultsort="1">
					<display:column property="nombre" title="Nombre" 			 style="width:40%; text-align:left;" sortable="true" />
					<display:column property="desc"   title="Descripci&oacute;n" style="width:40%; text-align:left;" sortable="true" />
					<display:column property="accion" title="Acci&oacute;n"      style="width:20%; text-align:center;" 			    />							
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
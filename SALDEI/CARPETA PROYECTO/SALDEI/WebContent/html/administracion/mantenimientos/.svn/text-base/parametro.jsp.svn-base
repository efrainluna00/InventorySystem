<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/commons.jsp" %>
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<html:html>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
<script type="text/javascript">
	function cleanForm(){
		document.forms[0].valor.value = '';
		document.forms[0].nombre.value = '';
		document.forms[0].descripcion.value = '';		
		document.forms[0].cmd.value = 'limpiar';
		document.forms[0].submit();
	}	
	function saveReg(){
		if(!Validar()){
			alert('Debe ingresar todos los campos obligatorios');
		}else{
			document.forms[0].cmd.value = 'save';
			document.forms[0].submit();
		}
	}	
	function updateReg(){
		if(!Validar()){
			alert('Debe ingresar todos los campos obligatorios');
		}else{
			document.forms[0].cmd.value = 'update';
			document.forms[0].submit();
		}
	}	
	function Validar(){
		if (document.forms[0].valor.value == '')
			return false;
		if (document.forms[0].nombre.value == '')
			return false;
		return true; 	
	}	
	function modificar(jsNombre, jsDesc, jsValor, jsCodigo)
	{
		document.forms[0].valor.value = jsValor;
		document.forms[0].nombre.value = jsNombre;
		document.forms[0].descripcion.value = jsDesc;
		document.forms[0].codigo.value = jsCodigo;
		document.forms[0].btnSave.disabled = true;
		jQuery.NiceJForms.cambio('btnSave');
	}	
	function borrar(){
		var c = confirm('Esta seguro que desea eliminar el registro?');
		if(c){
			document.forms[0].cmd.value = 'delete';
			document.forms[0].submit();
		}
	}
	$(document).ready(function(){$.NiceJForms.build()});		
</script>	
</head>
<body onload="document.forms[0].btnSave.disabled = false;">
<html:form action="/mtoParam" styleClass="niceform">
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
			<div id="titule"> <bean:message key="parametro.lbl.legend"/></div><br/>
			<table>
				<tr>
					<td>
					<fieldset>
						<table align="center">
							<tr>
								<td align="left"><bean:message key="commons.lbl.nombre"/><bean:message key="commons.msg.*"/><html:hidden property='codigo'/></td>
								<td align="center"><html:text property="nombre" size="25" maxlength="15" value="${requestScope.parametroFormJsp.nombre}"/></td>
							</tr>					
							<tr>
								<td align="left"><bean:message key="perfilMto.lbl.descripcion"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:textarea property="descripcion" value="${requestScope.parametroFormJsp.descripcion}" cols="26" rows="8"/></td>								
							</tr>						
							<tr>
								<td align="left"><bean:message key="parametro.lbl.valor"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="valor" size="25" maxlength="20" value="${requestScope.parametroFormJsp.valor}"/> </td>
							</tr>
							<tr align="center">					
								<td align=""right"" colspan="2"><bean:message key="commons.msg.obligatorio"/><br/><br/></td>			
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input type="button" onclick="saveReg();"  value='Guardar' name='btnSave'  id= 'btnSave' class="ButtonText"/>
									<input type="button" onclick="updateReg();"  value='Actualizar' class="ButtonText"/>
									<input type="submit" onclick="cmd.value='find'"  value='Mostrar' class="ButtonText"/>
									<input type="button" onclick="borrar();"  value='Eliminar' class="ButtonText"/>
									<input type="button" value='Limpiar' class="ButtonText" onclick="cleanForm();"/>
									<html:submit value="Generar Reporte" onclick="cmd.value='generarReporte'" styleClass="ButtonText"/>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<logic:present name="paramMsg" scope="request">
										<logic:equal value="vacio" name="paramMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/></logic:equal>	
										<logic:equal value="exitoSave" name="paramMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></logic:equal>
										<logic:equal value="errorSave" name="paramMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></logic:equal>
										<logic:equal value="error" name="paramMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/></logic:equal>	
										<logic:equal value="errorUpdate" name="paramMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar.lista"/></logic:equal>	
										<logic:equal value="exitoUpdate" name="paramMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></logic:equal>		
										<logic:equal value="errorUpdateE" name="paramMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></logic:equal>
										<logic:equal value="listVacias" name="paramMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.lbl.listas.vacio"/></logic:equal>
									</logic:present>
								</td>
							<tr>
						</table>
					</fieldset>			
					</td>
				</tr>
			</table>
		</div>
		<div id="column">
			<logic:present name="displayListParametro" scope="session">
				<display:table name="sessionScope.displayListParametro" pagesize="10" id="dispalyTagListParam" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/parametro.jsp" sort="list" defaultsort="1">
					<display:column property="nombre" 		title="Nombre" 			 style="width:30%; text-align:left;"   sortable="true" />
					<display:column property="descripcion" 	title="Descrip&oacute;n" style="width:30%; text-align:left;"   sortable="true" />
					<display:column property="valor" 		title="Valor" 		     style="width:20%; text-align:center;" sortable="true" />
					<display:column property="accion" 		title="Acci&oacute;n"    style="width:20%; text-align:center;"                />
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
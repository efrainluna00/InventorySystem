<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/commons.jsp" %>
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ page import="com.saldei.web.services.administracion.MedidaServices" %>
<html:html>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
<%
	MedidaServices services = new MedidaServices();
	request.setAttribute("listTipoMedidasIntoMed", services.getTipoMedida());
%>
<script type="text/javascript">
	function cleanForm(){
		document.forms[0].abrev.value = '';
		document.forms[0].nombre.value = '';
		document.forms[0].factor.value = '';
		jQuery.NiceJForms.selectValor('tipo', 0, 'Seleccione');
		document.forms[0].codigoHidden.value = '';
		document.forms[0].cmd.value = 'limpiar';
		document.forms[0].submit();
	}	
	function modificar(jsNombre, jsAbrev, jsFactor, jsTipo, jsCodigo){
		document.forms[0].abrev.value = jsAbrev;
		document.forms[0].nombre.value = jsNombre;
		if(jsFactor != '' || jsFactor != null)
			document.forms[0].factor.value = jsFactor;
		if(jsTipo != '' || jsTipo != 'null')
			jQuery.NiceJForms.selectValor('tipo', 0, jsTipo);
		else
			jQuery.NiceJForms.selectValor('tipo', 0, jsTipo);	
		document.forms[0].codigoHidden.value = jsCodigo;
	    document.forms[0].btnSave.disabled = true;
   		jQuery.NiceJForms.cambio('btnSave');
	}	
	function saveFunction(){
		var c = confirm('Desea guardar los cambios?');
		if(c){
			document.forms[0].abrev.value = '';
			document.forms[0].nombre.value = '';
			document.forms[0].factor.value = '';
			jQuery.NiceJForms.selectValor('tipo', 0, 'Seleccione');
			document.forms[0].codigoHidden.value = '';
			document.forms[0].cmd.value='save';
			document.forms[0].submit();
		}
	}	
	function Validar(){
		if (document.forms[0].abrev.value == '')
			return false;
		if (document.forms[0].tipo.value == 'Seleccione')
			return false;
		if (document.forms[0].nombre.value == '')
			return false;
		return true; 	
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
			document.forms[0].cmd.value='update';
			document.forms[0].btnSave.disabled = false;
			document.forms[0].submit();
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
	$(document).ready(function(){$.NiceJForms.build()});	
</script>	
</head>
<body onload="desabButtons();">
<html:form action="/mtoMed"  styleClass="niceform">
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
			<div id="titule"> <bean:message key="medidaMto.titulo"/></div><br/>
			<table>
				<tr>
					<td>
					<fieldset>			
						<logic:present name="modulesMedida" scope="session">
							<legend><%= (String) request.getSession().getAttribute("modulesMedida") %></legend>
						</logic:present><br/>	
						<table align="center">
							<tr>
								<td align="left"><bean:message key="commons.lbl.nombre"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="nombre" size="25" maxlength="40" value="${requestScope.medidaFormJsp.nombre}"/>
												<html:hidden property="codigoHidden" value="<%= (String) request.getAttribute("codigo") %>"/> </td>
							</tr>					
							<tr>
								<td align="left"><bean:message key="medidaMto.abreviatura"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:text property="abrev" size="9" maxlength="10" value="${requestScope.medidaFormJsp.abrev}"/></td>
							</tr>						
							<tr>
								<td align="left"><bean:message key="medidaMto.factor"/></td>
								<td align="center"><html:text property="factor" size="9" maxlength="15" value="${requestScope.medidaFormJsp.factor}"/></td>
							</tr>
							<tr>
								<td align="left"><bean:message key="medidaMto.tipo"/><bean:message key="commons.msg.*"/></td>
								<td>
									<html:select styleId="tipo"  property="tipo" value="${requestScope.medidaFormJsp.tipo}" >
										<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
										<html:options collection="listTipoMedidasIntoMed" property="idTipoMedida" labelProperty="nomTipoMedida"/>
									</html:select>								
								</td>	
							</tr>
							<tr align="center">					
								<td align=""right"" colspan="2"><bean:message key="commons.msg.obligatorio"/><br/><br/></td>			
							</tr>
							<tr>
								<td align="center" colspan="2">
									<%= (String) request.getSession().getAttribute("primeroMed") %>
									<%= (String) request.getSession().getAttribute("segundoMed") %>
									<input type="submit" onclick="cmd.value='find'"  value='<%= (String) request.getSession().getAttribute("terceroMed") %>' class="ButtonText"/>
									<input type="button" value='<%= (String) request.getSession().getAttribute("cuartoMed") %>' class="ButtonText" onclick="cleanForm();"/>
									<html:button value="<%= (String) request.getSession().getAttribute("quintoMed") %>" onclick="changeAction();" property="btnChange" styleClass="ButtonText"/>
									<html:submit value="Generar Reporte" onclick="cmd.value='generarReporte'" styleClass="ButtonText"/>
									<input type="button" value="Eliminar"  id="botonEliminar" name="botonEliminar" class="ButtonText" onclick="deleteFunction();" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<logic:present name="medMsg" scope="request">
										<logic:equal value="errorUpdate" name="medMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></logic:equal>				
										<logic:equal value="listasVacias" name="medMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.lbl.listas.vacio"/></logic:equal>
										<logic:equal value="vacioUpdate" name="medMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar.lista"/></logic:equal>
										<logic:equal value="exitoUpdate" name="medMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></logic:equal>
										<logic:equal value="vacio" name="medMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/></logic:equal>	
										<logic:equal value="exitoSave" name="medMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></logic:equal>
										<logic:equal value="errorSave" name="medMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></logic:equal>
										<logic:equal value="error" name="medMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/></logic:equal>
										<logic:equal value="saveAllExito" name="medMsg"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.guardarTodos.exito"/></logic:equal>
										<logic:equal value="saveAllNull" name="medMsg"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.guardarTodos.error"/></logic:equal>
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
			<logic:present name="listMedidaActivas" scope="session">
				<div id="cap"><bean:message key="medidaMto.lbl.inactivos"/></div>
				<display:table name="sessionScope.listMedidaActivas" pagesize="10" id="dispalyListMedAct" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/medida.jsp" sort="list"  defaultsort="1">
					<display:caption class="cap" id="cap"><bean:message key="medidaMto.lbl.activos"/></display:caption>
					<display:column property="nombre" title="Nombre" 		style="width:30%; text-align:left;"   sortable="true" />
					<display:column property="abrev"  title="Abrev"  		style="width:10%; text-align:center;" sortable="true" />
					<display:column property="factor" title="Factor" 		style="width:10%; text-align:center;" sortable="true" />
					<display:column property="tipo"   title="Tipo"   		style="width:30%; text-align:left;"   sortable="true" />
					<display:column property="accion" title="Acci&oacute;n" style="width:20%; text-align:center;" />
				</display:table>
			</logic:present>
		</div>
		<div id="rightcolumn">
			<logic:present name="listMedidaInactivas" scope="session">
				<div id="cap"><bean:message key="medidaMto.lbl.inactivos"/></div>
				<display:table name="sessionScope.listMedidaInactivas" pagesize="10" id="dispalyListMedInac" excludedParams="*" requestURI="${ctx}/html/administracion/mantenimientos/medida.jsp" sort="list" defaultsort="1">
					<display:column property="nombre" title="Nombre" 		style="width:30%; text-align:left;"   sortable="true" />
					<display:column property="abrev"  title="Abrev"  		style="width:10%; text-align:center;" sortable="true" />
					<display:column property="factor" title="Factor" 		style="width:10%; text-align:center;" sortable="true" />
					<display:column property="tipo"   title="Tipo"   		style="width:30%; text-align:left;"   sortable="true" />
					<display:column property="accion" title="Acci&oacute;n" style="width:20%; text-align:center;" />
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
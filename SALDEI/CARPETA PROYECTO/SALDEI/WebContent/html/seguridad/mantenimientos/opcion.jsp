<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>

<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ page import="com.saldei.web.services.seguridad.OpcionServices" %>
<html:html>
<head>

<script type="text/javascript">
	function cleanForm(){
		document.forms[0].codigo.value = '';
		document.forms[0].nombre.value  = '';
		document.forms[0].desc.value = '';
		document.forms[0].url.value = '';
		document.forms[0].orden.value = '';
		document.forms[0].metodo.value = '';		
		document.forms[0].padre.value = 'Seleccione';
		document.forms[0].separador.value = 'Seleccione';
		document.forms[0].cmd.value = 'cleanAction';
		document.forms[0].submit();
	}	
	function modificar(jsPk, jsNombre, jsDesc, jsUrl, jsOrden, jsMetodo, jsEstado, jsPadre, jsSeparador){
		document.forms[0].codigo.value = jsPk;
		document.forms[0].nombre.value  = jsNombre;
		document.forms[0].desc.value = jsDesc;
		document.forms[0].url.value = jsUrl;
		document.forms[0].orden.value = jsOrden;
		document.forms[0].metodo.value = jsMetodo;
		jQuery.NiceJForms.selectValor('padre', 1, jsPadre);
		jQuery.NiceJForms.selectValor('separador', 0, jsSeparador);
		document.forms[0].btnSave.disabled = true;
		jQuery.NiceJForms.cambio('btnSave');
	}
	
	function confirmar(flag){
		if(flag == true){
			var c = confirm('Desea guardar todos los cambios?');
	    	if(c){
	    		document.forms[0].cmd.value = 'updateAll';
	    		document.forms[0].submit();
	    	}
	    }else{
	     		document.forms[0].cmd.value = 'save';
	    		document.forms[0].submit();
    		
		}
	    
	}
	
	function guardarSave(){
		if(document.forms[0].btnSave.value == 'Guardar'){
				document.forms[0].cmd.value = 'save';
	    		document.forms[0].submit();
		}else{
			var c = confirm('Desea guardar los cambios?');
	    	if(c){
	    		document.forms[0].cmd.value = 'save';
	    		document.forms[0].submit();
	    	}			
		}
	}
	
	function cancelarCambiosReg(){
		if(document.forms[0].btnUpdate.value == 'Actualizar'){
				document.forms[0].cmd.value = 'update';
	    		document.forms[0].submit();
		}else{
			var c = confirm('Desea cancelar los cambios?');
	    	if(c){
	    		document.forms[0].cmd.value = 'update';
	    		document.forms[0].submit();
	    	}			
		}
	}
	
	function changeButtons(){

				documento.forms[0].cmd.value = 'changeAction';
				document.forms[0].submit();
	}
	
	
	$(document).ready(function(){$.NiceJForms.build()});
		
</script>
<%
	OpcionServices services = new OpcionServices();
	request.setAttribute("opciones",services.getOpciones());
	String menu  = (String) session.getAttribute("Menu");
	String user  = (String) session.getAttribute("user"); 
%>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>

<body onload="document.forms[0].btnSave.disabled = false;" >
<html:form action="/opcion" styleClass="niceform" >
<html:hidden property="cmd" value=""/>
	<div id="wrapper">
		<div id="header">
			<table>
				<tr ><td style="width: 25px; background-color: #000000; text-align: center; vertical-align: middle;">
					<img src="${ctx}/images/login/SALDEI.png"/>
				</td>
				<td style="width: 825px; text-align: center; vertical-align: middle;"> 
					<img src="${ctx}/images/uca/labos.PNG" />
				</td>
				
				<td style="width: 100px; background-color: #000000;">
				<table >
					<tr>
						<td style=" vertical-align: middle; text-align: center;" colspan="2"> <img src="${ctx}/images/login/User1.png" alt="<%= user %>"/></td>
					</tr>		
					<tr>
						<td style="font-size:14px; font-weight:bold; vertical-align: middle;"> Usuario: 	 </td>
						<td style="font-size:18px; color:#439FD7; font-weight:bold; vertical-align: middle;">  <%= user %></td>
					</tr>
					<tr>
						<td style="font-size:18px; color:#439FD7; font-weight:bold; vertical-align: middle;">  <a href='/SALDEI/common.do?cmd=bienvenida'>    <img src="${ctx}/images/login/HomePage1.png" alt="Inicio"/></a></td>
						<td style="font-size:18px; color:#439FD7; font-weight:bold; vertical-align: middle;">  <a href='/SALDEI/common.do?cmd=inicio'>    <img src="${ctx}/images/login/Shutdown2.png" alt="Salir SALDEI"/></a></td>
					</tr>
				
				</table>
				</td>
				</tr>
				
			</table>
	    	
	    		    	
	    	<div id="menu">
	    		<%= menu %> 
	    	</div>

	    </div>
		<div id="container"><br/><br/>
			<div id="titule"> <bean:message key="opcionMto.lbl.titulo"/> </div><br/>
		<table>
		<tr>
			<td >
				<fieldset>			
				<logic:present name="modulesOpcion" scope="session">
					<legend><%= (String) request.getSession().getAttribute("modulesOpcion") %></legend>
				</logic:present>	
				<br/>
					<table align="center">
					<tr>
						<td align="left"><bean:message key="opcionMto.lbl.nombre"/><bean:message key="commons.msg.*"/></td>
						<td align="center"><html:hidden property="codigo"  value="<%= (String) request.getAttribute("codigox") %>"/><html:text property="nombre" value="<%= (String) request.getAttribute("nombre") %>"  maxlength="50" size="25"/></td>
					</tr>						
					<tr>
						<td align="left"><bean:message key="opcionMto.lbl.descripcion"/><bean:message key="commons.msg.*"/></td>						
						<td align="center"><html:textarea property="desc" value="<%= (String) request.getAttribute("descx") %>" cols="26" rows="8" /></td>
					</tr>
					<tr>
						<td align="left"><bean:message key="opcionMto.lbl.url"/><bean:message key="commons.msg.*"/></td>
						<td align="center"><html:textarea property="url" value="<%= (String) request.getAttribute("urlx") %>" cols="26" rows="8"/></td>						
					</tr>
					<tr>
						<td align="left"><bean:message key="opcionMto.lbl.orden"/><bean:message key="commons.msg.*"/></td>
						<td align="center"><html:text property="orden" value="<%= (String) request.getAttribute("ordenx") %>"  maxlength="5" size="12"/></td>
					</tr>
					<tr>
						<td align="left"><bean:message key="opcionMto.lbl.metodo"/><bean:message key="commons.msg.*"/></td>
						<td align="center"><html:text property="metodo" value="<%= (String) request.getAttribute("metodox") %>"  maxlength="25" size="12"/></td>
					</tr>
					<tr>
						<td align="left"><bean:message key="opcionMto.lbl.separador"/><bean:message key="commons.msg.*"/></td>
						<td align="left">
							<html:select styleId="separador"  property="separador" styleClass="width_200">
								<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
								<html:option value="S"><bean:message key="commons.sel.si"/></html:option>
								<html:option value="N"><bean:message key="commons.sel.no"/></html:option>
							</html:select>
						</td>
					</tr>								
					<tr>
						<td align="left"><bean:message key="opcionMto.lbl.padre"/></td>
						<td align="left">
							<html:select  styleId="padre"  property="padre" styleClass="width_200">
								<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
								<html:options collection="opciones" labelName="idOpcion" labelProperty="nomOpcion" property="idOpcion"/>
							</html:select>
						</td>
					</tr>					
					<tr align="right">					
						<td align=""right"" colspan="2">		
							<bean:message key="commons.msg.obligatorio"/><br/><br/>			
						</td>			
					</tr>
					<tr>
						<td align="center" colspan="2">
							<input type="button" id="btnSave"  name="btnSave" onclick="guardarSave();" value='<%= (String) request.getSession().getAttribute("primero") %>' class="ButtonText" />
							<input type="button" onclick="cancelarCambiosReg();"  value='<%= (String) request.getSession().getAttribute("segundo") %>' class="ButtonText" id="btnUpdate" name="btnUpdate"/>
							<input type="submit" onclick="cmd.value='find'"  value='<%= (String) request.getSession().getAttribute("tercero") %>' class="ButtonText"/>
							<input type="button" value='<%= (String) request.getSession().getAttribute("cuarto") %>' class="ButtonText" onclick="cleanForm();"/>&nbsp;
							<html:submit value="<%= (String) request.getSession().getAttribute("quinto") %>" onclick="cmd.value='changeAction'" styleClass="ButtonText" />
						</td>
					</tr>
					<tr>
					<td align="center" colspan="2">					
						<logic:present name="opMsg" scope="request">
							<logic:equal value="vacio" name="opMsg" scope="request"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/></logic:equal>
							<logic:equal value="notNumber" name="opMsg" scope="request"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="opcionMto.error.orden"/></logic:equal>
							<logic:equal value="vacioDml" name="opMsg" scope="request"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></logic:equal>	
							<logic:equal value="exito" name="opMsg" scope="request"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></logic:equal>	
							<logic:equal value="exitoUpdate" name="opMsg" scope="request"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></logic:equal>
							<logic:equal value="errorSave" name="opMsg" scope="request"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></logic:equal>
							<logic:equal value="errorUpdate" name="opMsg" scope="request"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></logic:equal>
							<logic:equal value="error" name="opMsg" scope="request"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/></logic:equal>
						</logic:present>		</td>			
					</tr>
					</table>
				</fieldset>			
			</td>
		</tr>
	</table>
	<br/><br/>
		</div>
		<div id="leftcolumn"> 
			<logic:present scope="session" name="allOpcionActivo">
				<div id="cap"><bean:message key="opcionMto.lbl.activos"/></div>
				<display:table name="sessionScope.allOpcionActivo" pagesize="10" sort="list" id="listOpcionesActivas" defaultsort="1">
					<display:column property="nombre" title="Nombre" sortable="true" style="width:20%; text-align:center;"/>
					<display:column property="padre"  title="Padre"  sortable="true" style="width:15%; text-align:left;"/>
					<display:column property="url"    title="URL"    sortable="true" style="width:30%; text-align:center;"/>
					<display:column property="orden"  title="Orden"  sortable="true" style="width:5%; text-align:center;"/>
					<display:column property="delete" title="Accion"                 style="width:30%; text-align:center; "/>
				</display:table>
			</logic:present>
		</div>
		<div id="rightcolumn">
			<logic:present scope="session" name="allOpcionInactivo">
				<div id="cap"><bean:message key="opcionMto.lbl.inactivas"/></div>
				<display:table name="sessionScope.allOpcionInactivo" pagesize="10" sort="list" id="listOpcionesInactivas" defaultsort="1">
					<display:column property="nombre" title="Nombre" sortable="true" style="width:20%; text-align:center;"/>
					<display:column property="padre"  title="Padre"  sortable="true" style="width:15%; text-align:left;"/>
					<display:column property="url"    title="URL"    sortable="true" style="width:30%; text-align:center;"/>
					<display:column property="orden"  title="Orden"  sortable="true" style="width:5%; text-align:center;"/>
					<display:column property="delete" title="Accion"                 style="width:30%; text-align:center; "/>
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
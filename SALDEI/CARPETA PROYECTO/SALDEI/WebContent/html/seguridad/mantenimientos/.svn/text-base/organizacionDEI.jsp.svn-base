<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<%@ page import="java.util.List;" %>
<script type="text/javascript" src="${ctx}/js/calendar/calendar.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar_helper.js"></script>

<script type="text/javascript">

	function verCalen(field, btn){
			Calendar.setup({
		    inputField : field,
	    	ifFormat   : "%d/%m/%Y",
		    button     : btn
			});
	}
	
	
	function deleteElem() {
		action = confirm("¿Desea guardar los cambios?");
		if (action) {
			document.forms[0].cmd.value = 'save';
			document.forms[0].submit();			
		}
	}
	function Select(){
		document.forms[0].cmd.value = 'find';
		document.forms[0].submit();				
	}
	
	function cancelElem() {
		action = confirm("¿Desea cancelar los cambios?");
		if (action) {
			document.forms[0].cmd.value = 'find';
			document.forms[0].submit();			
		}
	}
	
function obtener(num){
	if(document.forms[0].fechaIni.value == '' || document.forms[0].fechaFin.value == ''){
			alert('La fecha de Inicio y la de Fin de Vigencia  son obligatorios');
		}else{
		document.forms[0].idobt.value = num;
		document.forms[0].cmd.value='Add';
		document.forms[0].submit();
	}
}
	
function obtenerRemove(num){
	document.forms[0].idobt.value = num;
	document.forms[0].cmd.value='Remove';
	document.forms[0].submit();
}
	
    $(document).ready(function(){$.NiceJForms.build()});
	
</script>

<html:html>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
<%
	List lstUser = (List) request.getSession().getAttribute("usuarios");
	request.setAttribute("users",lstUser);
%>
</head>
<body>
<html:form action="/orgDEI"  styleClass="niceform">
<html:hidden property="cmd" value=""/>
	<div id="wrapper">
		<div id="header">
			<table>
				<tr>
					<td style="width: 25px; background-color: #000000; text-align: center; vertical-align: middle;">
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
	    	<div id="menu"><%= menu %></div>
	    	</div>
	    	<div id="container"><br/><br/>
				<div id="titule"><bean:message key="orgDEI.lbl.titulo"/></div><br/>
				<table>
					<tr>
						<td>
						<fieldset>
						<br/>
							<table>
								<tr>
									<td align="center"><bean:message key="login.lbl.usr"/></td>
									<td align="left">
										<html:select styleId="orgDEI" property="usr" onchange="Select()" styleClass="width_110">
											<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
											<html:options collection="users" property="idUsuario" labelProperty="primerNom"/>
										</html:select>
										<input type="hidden" name="idobt" id="idobt"/>
									</td>
									<td align="	">
	  							    	<html:button value="Mostrar" styleClass="ButtonText"  property="btnMostrar" onclick="Select();"/>
									</td>
								</tr>
								<tr>
									<td align="left">Fecha Inicio de Vigencia: </td>
									<td align="left">
										<input type="text" name="fechaIni" id="fechaIni" size="12"/>										
									</td>
									<td align="left"><input type="button" value="....." id="btnFecha" onmousedown="verCalen('fechaIni', 'btnFecha' )" class="ButtonText"/></td>
								</tr>
								<tr>
									<td align="left">Fecha Fin de Vigencia: </td>
									<td align="left">
										<input type="text" name="fechaFin" id="fechaFin"  size="12" />
									</td>
									<td align="left"><input type="button" value="....." id="btnFechaFin" onmousedown="verCalen('fechaFin', 'btnFechaFin' )" class="ButtonText"/></td>
								</tr>							
								<tr align="center">
									<logic:present name="MapCargo" scope="session">
										<td colspan="3"><html:button value="Guardar los Cambios" styleClass="ButtonText"  property="btnDelete"  onclick="deleteElem()"/>	
										<html:button   value="Cancelar los Cambios" styleClass="ButtonText"  property="btnCancell" onclick="cancelElem();"/></td>
									</logic:present>	
								</tr>
								<tr align="center">
									<td align="center" colspan="3">
									</td>		
								</tr>
								<tr>
									<td align="center" colspan="2">	
										<logic:present scope="request" name="orgDEI">
										<logic:equal value="vacio"     name="orgDEI"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio.Sel"/></logic:equal>
										<logic:equal value="exito"     name="orgDEI"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardarCambios"/></logic:equal>
										<logic:equal value="errorSave" name="orgDEI"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardarCambios"/></logic:equal>	
										</logic:present>
									</td>
								</tr>	
							</table>
					</fieldset>
					</td>
				</tr>
				
			</table>					
			</div>
			<div id="rightcolumn">	
     			<logic:present name="MapCargo" scope="session">
						<div id="cap"><b><bean:message key="orgDEI.msg.no.asignados"/></b></div>
						<display:table name="sessionScope.MapCargo" pagesize="10" sort="list"  defaultsort="1" defaultorder="ascending" excludedParams="*" id="tableMapCargo">
							<display:column property="nombreCargo"  title="Nombre de cargo"      sortable="true" style="width:35%; text-align:left;"/>
							<display:column property="descripcion"  title="Descripci&oacute;n de cargo" sortable="true" style="width:45%; text-align:left;"/>						
							<display:column property="accion"       title="Acci&oacute;n" 		                 style="width:20%; text-align:center;"/>
						</display:table>
				</logic:present>					
			</div>	
			<div id="leftcolumn"> 				
				<logic:present name="MapCargoUser" scope="session" >
						<div id="cap"><b><bean:message key="orgDEI.msg.asignados"/></b></div>
						<display:table name="sessionScope.MapCargoUser" pagesize="10" sort="list" defaultsort="1" defaultorder="ascending" excludedParams="*" id="tableMapCargoUser">
							<display:column property="nombreCargof" 	title="Nombre de cargo"         sortable="true" style="width:30%; text-align:left;"/>
							<display:column property="descripcionf"     title="Descripci&oacute;n de cargo"   sortable="true" style="width:30%; text-align:left;"/>
							<display:column property="fechaInif" 		title="Fecha Inicio Vigencia"  sortable="true" headerClass="sortable" style="width:10%; text-align:center;"/>
							<display:column property="fechaFinf" 		title="Fecha Fin Vigencia"     sortable="true" headerClass="sortable" style="width:10%; text-align:center;"/>
							<display:column property="accionf"          title="Acci&oacute;n"  				style="width:20%; text-align:center;"/>
						</display:table>					
					</div>
				</logic:present>
			</div>
	    	<div id="footer">
				<img src="${ctx}/images/login/foot.PNG"/><br/>
			Version 1.0
			</div>
	    
</html:form>
</body>
</html:html>
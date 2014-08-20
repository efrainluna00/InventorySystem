<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<%@ page import="java.util.List;" %>
<script type="text/javascript">

	function saveChange(){
		action = confirm("¿Desea Realizar los Cambios?");
		if (action) {
			document.forms[0].cmd.value = 'save'
			document.forms[0].submit();			
		}
	}

	function verPerfil(){
			document.forms[0].cmd.value = 'find';
			document.forms[0].submit();
	}
	
	function show(){
		action = confirm("¿Desea Cancelar los Cambios?");
		if (action) {
			document.forms[0].cmd.value = 'find';
			document.forms[0].submit();
		}
	}
	$(document).ready(function(){$.NiceJForms.build()});
</script>

<html:html>
<%
	List lstUser = (List) request.getSession().getAttribute("usuarios");
	request.setAttribute("users",lstUser);
%>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<body>
<html:form action="/asgPerfil"  styleClass="niceform"  >
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
			<div id="titule"> <bean:message key="asignarPerfil.lbl.titulo"/> </div><br/>
			<table>
				<tr>
					<td align="center">
					<fieldset>
						<table align="center">
							<tr align="center">
								<td><bean:message key="login.lbl.usr"/></td>
								<td>
									<html:select styleId="asignarPerfil"  property="usr" onchange="verPerfil();" styleClass="width_180"  >
										<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
										<html:options collection="users" property="idUsuario" labelProperty="primerNom"/>
									</html:select>
								</td>
								<td>
								 	<html:button  value="Mostrar Perfil" styleClass="ButtonText"  property="btnMos" onclick="verPerfil();"/>
								</td>
							</tr>
							<tr align="center">
								<logic:present name="mapPerfil" scope="session">
									<td colspan="3" style="text-align: center;"><html:button value="Guardar los Cambios"  styleClass="ButtonText"  property="btnDelete"  onclick="saveChange()"/>	
											       <html:button  value="Cancelar los Cambios" styleClass="ButtonText"  property="btnCancell" onclick="show()"/></td>
								</logic:present>								
							</tr>
							<tr>
								<td colspan="3">
									<logic:present scope="request"     name="msgAsgPer">
										<logic:equal value="vacio"     name="msgAsgPer"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio.Sel"/></logic:equal>
										<logic:equal value="exito"     name="msgAsgPer"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardarCambios"/></logic:equal>
										<logic:equal value="errorSave" name="msgAsgPer"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardarCambios"/></logic:equal>
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
			<logic:present name="mapPerfil" scope="session">
				<div id="cap" ><bean:message key="asgPerfil.msg.no.asignados"/></div> 
				<display:table name="sessionScope.mapPerfil" pagesize="10" sort="list" excludedParams="*" id="listaActivos" defaultsort="2" defaultorder="ascending" >
					<display:column property="nombre" title="Nombre Perfil"             style="width:35%; text-align:left;" sortable="true" defaultorder="ascending"/>
					<display:column property="desc"   title="Descripci&oacute;n Perfil" style="width:45%; text-align:left;" sortable="true"/>
					<display:column property="accion" title="Acci&oacute;n" 			style="width:20%; text-align:left;" sortable="false"/>
				</display:table>
			</logic:present>
		</div>
		<div id="leftcolumn"> 
			<logic:present name="mapUserPerfil" scope="session" >
				<div id="cap" ><bean:message key="asgPerfil.msg.asignados"/></div> 
				<display:table name="sessionScope.mapUserPerfil" pagesize="10" sort="list" defaultsort="2" defaultorder="ascending" excludedParams="*" id="listaInactivos">
					<display:column property="nombre" title="Nombre Perfil"              style="width:35%; text-align:left;" sortable="true" defaultorder="ascending"/>
					<display:column property="desc"   title="Descripci&oacute;n Perfil"  style="width:45%; text-align:left;" sortable="true"/>
					<display:column property="accion" title="Acci&oacute;n" 			 style="width:20%; text-align:left;" sortable="false"/>
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
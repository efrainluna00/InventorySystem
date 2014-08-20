
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/html/commons/commons.jsp" %>
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>

<%
	request.setAttribute("perfiles", request.getSession().getAttribute("per"));
%>

<html:html>
<head>
<script type="text/javascript">
	
	function deleteElem(){
		action = confirm("¿Desea Realizar los Cambios?");
		if (action) {
			document.forms[0].cmd.value = 'save';
			document.forms[0].submit();			
		}
	}
	
	function cancelElem(){
		action = confirm("¿Desea Cancelar los Cambios?");
		if (action) {
			document.forms[0].cmd.value = 'find';
			document.forms[0].submit();			
		}
	}
	
	function Select(){
		document.forms[0].cmd.value = 'find';
		document.forms[0].submit();				
	}
	
	$(document).ready(function(){$.NiceJForms.build()});
	
</script>
<title><bean:message key="commons.lbl.titulo"/> - <bean:message key= "opcPerfil.lbl.titulo"/></title>
</head>
<body>
<html:form action="/opcPerfil" styleClass="niceform" >
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
			<div id="titule"><bean:message key= "opcPerfil.lbl.titulo"/></div><br/>	
			<table align="center">
				<tr align="center">
					<td>
						<fieldset>
							<table align="center">
								<tr align="left">
									<td> <bean:message key="perfilMto.sel.perfil"/></td>
									<td>
										<html:select styleId="mySelect1"  property="perfil"  onchange="Select()" styleClass="width_200">
											<html:option value="Seleccione">Seleccione</html:option>
											<html:options collection="perfiles" property="idPerfil" labelProperty="nomPerfil"/>
										</html:select>								
									</td>	
									<td>
									<html:button  styleClass="ButtonText"  property="btnVer" onclick="Select();"> Mostrar</html:button> 
									</td>						
								</tr>		
								<logic:present name="mapOpc" scope="session">
								<tr  align="center"  >							
										<td colspan="3" style="text-align:center;"><html:button styleClass="ButtonText"  property="btnDelete"   onclick="deleteElem()"><bean:message key="commons.btn.guardarCambios"/></html:button> 
										<html:button  styleClass="ButtonText"  property="btnCancelar" onclick=" cancelElem();"> <bean:message key="commons.btn.cancelar"/></html:button> </td>
								</tr>
								</logic:present>	
								<tr>
									<td colspan="3">
										<logic:present scope="request" name="opcPer">
											<logic:equal value="vacio" name="opcPer"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio.Sel"/></logic:equal>
											<logic:equal value="exito" name="opcPer"><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardarCambios"/></logic:equal>
											<logic:equal value="errorSave" name="opcPer"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardarCambios"/></logic:equal>	
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
			<logic:present name="mapOpc" scope="session">
				<div id="cap"><bean:message key="opcPerfil.msg.no.asignados"/></div>
					<display:table name="sessionScope.mapOpc" pagesize="10" sort="list" defaultsort="1" defaultorder="ascending" excludedParams="*" id="tablemapOpc">
						<display:column property="nombreOpcion"  title="Nombre de opci&oacute;n"      sortable="true" style="width:35%"/>
						<display:column property="descripcion"   title="Descripci&oacute;n de opci&oacute;n" sortable="true" style="width:45%"/>
						<display:column property="accion"        title="Acci&oacute;n" 						       style="width:20%"/>
					</display:table>

		</logic:present>		
		</div>
		<div id="leftcolumn">
			<logic:present name="mapOpcPerfil" scope="session" >
				<div id="cap"><bean:message key="opcPerfil.msg.asignados"/></div>
				<display:table name="sessionScope.mapOpcPerfil" pagesize="10" sort="list" defaultsort="1" defaultorder="ascending" excludedParams="*" id="tablemapOpcPerfil">
				<display:column property="nombreOpcion" 	title="Nombre de opci&oacute;n"      sortable="true" style="width:35%;" />
				<display:column property="descripcion"      title="Descripci&oacute;n de opci&oacute;n" sortable="true" style="width:45%;"/>
				<display:column property="accion"           title="Acci&oacute;n"    						      style="width:20%;"/>
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
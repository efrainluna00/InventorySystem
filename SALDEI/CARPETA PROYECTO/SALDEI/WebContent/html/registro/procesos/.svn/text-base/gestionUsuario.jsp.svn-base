<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<%@ page import="com.saldei.web.services.registro.GestionUsuarioServices" %>
<html:html>
<head>
<script type="text/javascript">
	function saveAll(){
		if(document.forms[0].usuario.value == 'Seleccione' || document.forms[0].accion.value == 'Seleccione')
			alert('Debe ingresar todos los campos');
		else{
			var c = confirm('Esta seguro que desea realizar esta operacion?');
			if(c){
				document.forms[0].cmd.value='saveAll';
				document.forms[0].submit();
			}
		}
	}	
	function cancelAll(){
		var c = confirm('Desea cancelar los cambios?');
		if(c){
			document.forms[0].cmd.value='cancelAll';
			document.forms[0].submit();
		}
	}	
	function changeUsuario(){
		document.forms[0].cmd.value='changeUsuario';
		document.forms[0].submit();
	}	
	function limpiar(){
		document.forms[0].usuario.value = 'Seleccione';
		document.forms[0].accion.value = 'Seleccione';
		document.forms[0].cmd.value='inicio';
		document.forms[0].submit();
	}	
	$(document).ready(function(){$.NiceJForms.build()});
<%
	GestionUsuarioServices services = new GestionUsuarioServices();
	request.setAttribute("listUser", services.getUser());
%>
</script>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<body>
<html:form action='/gestUser' styleClass="niceform"  >
<html:hidden property="cmd" value=''/>
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
			<div id="titule"> <bean:message key='gestionUsuario.legend'/> </div><br/>
			<table align='center'>
				<tr align='center'>
					<td>
					<fieldset>
					<br/>
						<table align='center'>
							<tr>
								<td align='left'><bean:message key='gestionUsuario.lbl.usuario'/>&nbsp;</td>
								<td align='center'>
									<html:select property="usuario" styleId="usuario" styleClass="width_100" onchange="changeUsuario();">
										<html:option value="Seleccione"><bean:message key='commons.sel.seleccione'/></html:option>
										<html:options collection="listUser" labelName="nombreCompleto" labelProperty="nombreCompleto" name="selectUser" property="idUsuario"/>
									</html:select>									
								</td>
								<td align="center">
									<html:button styleClass="ButtonText" onclick="changeUsuario();" property="btnver" value="Mostrar"/>
								</td>
							</tr>
							<tr>
								<td align='left'>Acci&oacute;n: </td>
								<td align='left' colspan="2">
									<html:select property="accion" styleId="accion" styleClass="width_100">
										<html:option value="Seleccione"><bean:message key='commons.sel.seleccione'/></html:option>
										<html:option value="A">Activar</html:option>
										<html:option value="R">Rechazar</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td align='left' colspan="3" style="text-align: center;">
									<%//String estado = (String) request.getAttribute("estadogest"); if(estado != null) out.println("Estado: " + estado); %>
								</td>
							</tr>		
							<tr align='center'>
								<td align='center' colspan='3' style="text-align: center;">
									<html:button styleClass="ButtonText" onclick="saveAll();" property="btnsaveAll" value="Aceptar"/>
									<html:button styleClass="ButtonText"onclick="limpiar();" property="btncancelall" value="Limpiar"/>
								</td>
							</tr>
							<tr align='center'>
								<td align='center' colspan='3'>
									<logic:present name="gufMsg" scope="request">
										<logic:equal name="gufMsg" scope="request" value="error">
											<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/>
										</logic:equal>
										<logic:equal name="gufMsg" scope="request" value="noexiste">
											<img src="${ctx}/images/icons/error.gif"/>&nbsp;No se encontro el usuario
										</logic:equal>
										
										<logic:equal name="gufMsg" scope="request" value="errorMail">
											<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="gestionUsuario.lbl.error.mail"/>
										</logic:equal>
										<logic:equal name="gufMsg" scope="request" value="exito">
											<img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="gestionUsuario.lbl.exito"/>
										</logic:equal>
									</logic:present>								
								</td>
							</tr>	
						</table>
					</fieldset>
					</td>
				</tr>
			</table>
		</div>
			<logic:present name="estadogest" scope="session">
				<display:table name="sessionScope.estadogest" pagesize="10" id="dispalyListGestionUsuario" >
					<display:column property="idUsuario"  title="Id Usuario"/>				
					<display:column property="primerApe" title="Primer Apellido" />
					<display:column property="primerNom"    title="Nombre"/>
					<display:column property="email"    title="Correo" />				
					<display:column property="estUsuario"    title="Estado" />									
				</display:table>
			</logic:present>		
		<div id="footer">
			<img src="${ctx}/images/login/foot.PNG"/><br/>
			Version 1.0
		</div>
		</div>
		
</body>
</html:form>
</html:html>
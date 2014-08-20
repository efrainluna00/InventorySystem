<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<%@ page import="com.saldei.web.services.registro.AsignarMateriaEstudianteService" %>
<html:html>
<head>
<script type="text/javascript">
	function save(){
		var c = confirm('Desea guardar los cambios?');
		if(c){
			document.forms[0].cmd.value='save';
			document.forms[0].submit();
		}
	}		
	function cancelar(){
		var c = confirm('Desea cancelar los cambios?');
		if(c){
			document.forms[0].cmd.value='find';
			document.forms[0].submit();
		}
	}
	function mostrar(){
		if( document.forms[0].materia.value == 'Seleccione'){
			alert('Debe seleccionar la materia');
		}
		else{
			document.forms[0].cmd.value = 'find';
			document.forms[0].submit();
		}		
	}
	$(document).ready(function(){$.NiceJForms.build()});
</script>
<% AsignarMateriaEstudianteService service=new AsignarMateriaEstudianteService();
	request.setAttribute("materias",service.getMateriaCiclo());
%>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<body>
<html:form action="/asignarMateriaEstudiante" styleClass="niceform"  >
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
			<div id="titule"> <bean:message key="asgMatEst.lbl.titulo"/> </div><br/>
			<table>
				<tr>
					<td >
					<fieldset>			
						<table align="center">	
							<tr>													
								<td align="left">Materia: </td>
								<td align="left"> <html:select property="materia" styleId="materia" styleClass="width_185">
									<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
									<html:options collection="materias" labelName="element1" labelProperty="element2" name="selectMateria" property="element1"/>
								</html:select><html:hidden property="carnetid" value=""/>	</td>
							</tr>
							<tr>
								<td align="left">Carnet:</td>
								<td align="left"><html:text property="carnet" size="20" maxlength="10"/></td>
							</tr>							
							<tr>
								<td align="left">Primer apellido:</td>
								<td align="left"><html:text property="apellido" size="20" maxlength="20"/></td>
							</tr>							
							<tr align="center">
								<td colspan="2" style="text-align: center;">								
									<input type="button" value="Mostrar" onclick="mostrar();"/>
									<input type="button" value="Guardar Cambios" onclick="save();"/>
   									<input type="button" value="Cancelar Cambios" onclick="cancelar();"/>
								</td>
							</tr>
							<tr align="center">
								<td colspan="2">
									<logic:present name="asigMateriaEstudiante" scope="request">
										<logic:equal name="asigMateriaEstudiante" scope="request" value="error">
											<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/>
										</logic:equal>
										<logic:equal name="asigMateriaEstudiante" scope="request" value="exito">
											<img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.guardarTodos.exito"/>
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
		<div id="leftcolumn"> 	
			<logic:present name="mapEstInscrito" scope="session">
				<div id="cap"><b>Estudiantes Inscritos</b></div>
				<display:table name="sessionScope.mapEstInscrito" pagesize="10" id="lista0DisplayTag" excludedParams="*" requestURI="${ctx}/html/registro/procesos/asignarMateriaEstudiante.jsp"  defaultsort="2">
					<display:column property="carnet"    title="Carnet"        style="width:20%; text-align:left;" sortable="true"/>
					<display:column property="apellidos" title="Apellido"      style="width:30%; text-align:left;" sortable="true"/>
					<display:column property="nombres"   title="Nombre"        style="width:30%; text-align:left;" sortable="true"/>
					<display:column property="accion"    title="Acci&oacute;n" style="width:20%; text-align:center;" />
				</display:table>
			</logic:present>
		</div>
		<div id="rightcolumn">
			<logic:present name="mapEstNoInscrito" scope="session">
				<div id="cap"><b>Estudiantes no Inscritos</b></div>
				<display:table name="sessionScope.mapEstNoInscrito" pagesize="10" id="lista1DisplayTag" excludedParams="*" requestURI="${ctx}/html/registro/procesos/asignarMateriaEstudiante.jsp"  defaultsort="2">
					<display:column property="carnet"    title="Carnet"        style="width:20%; text-align:left;" sortable="true"/>
					<display:column property="apellidos" title="Apellido"      style="width:30%; text-align:left;" sortable="true"/>
					<display:column property="nombres"   title="Nombre"        style="width:30%; text-align:left;" sortable="true"/>
					<display:column property="accion"    title="Acci&oacute;n" style="width:20%; text-align:center;" />
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<%@ page import="com.saldei.web.services.registro.AsignarMateriaCicloServices" %>
<html:html>
<head>
<%
AsignarMateriaCicloServices services = new AsignarMateriaCicloServices();
request.setAttribute("ciclos", services.getMateria());
%>
<script type="text/javascript">
	function saveAll(){
		var c = confirm('Desea guardar los cambios?');
		if(c){
			document.forms[0].cmd.value='saveAll';
			document.forms[0].submit();
		}
	}
	
	function cancelAll(){
		var c = confirm('Desea cancelar los cambios?');
		if(c){
			document.forms[0].cmd.value='show';
			document.forms[0].submit();
		}
	}
	$(document).ready(function(){$.NiceJForms.build()});
</script>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<body>
<html:form action="/asigMatCiclo" styleClass="niceform"  >
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
			<div id="titule"> Asignar Materias a Ciclo </div><br/>	
			<table>
				<tr>
					<td align="center">
					<fieldset><br/>
						<table align="center">
							<tr>
								<td align="left">Ciclo: </td>
								<td align="left"> <html:select property="ciclo"  styleId="ciclo" styleClass="width_100">
										<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
										<html:options collection="ciclos" labelName="idCiclo" labelProperty="idCiclo" name="selectCiclo" property="idCiclo"/>
									</html:select>
								</td>
							</tr>
							<tr align="center">
								<td colspan="2">
									<html:submit value="Mostrar" onclick="cmd.value='show'"/>
									<html:button value="GuardarCambios" onclick="saveAll();" property="btnSaveAll"/>
									<html:button value="Cancelar Cambios"  onclick="cancelAll();" property="btnCancelAll"/>
								</td>
							</tr>
							<tr align="center">
								<td colspan="2">
									<logic:present name="asigMsg" scope="request">
										<logic:equal name="asigMsg" scope="request" value="error">
											<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/>
										</logic:equal>
										<logic:equal name="asigMsg" scope="request" value="vacio">
											<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/>
										</logic:equal>			
										<logic:equal name="asigMsg" scope="request" value="exito">
											<img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.guardarTodos.exito"/>
										</logic:equal>	
										<logic:equal name="asigMsg" scope="request" value="noData">
											<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="asigmatcat.lb.error.no.data"/>
										</logic:equal>		
									</logic:present>	
								</td>
							</tr>
						</table><br/>
					</fieldset>
					</td>
				</tr>
			</table>
		</div>
		<div id="leftcolumn"> 
			<logic:present name="lista1" scope="session">
				<div id="cap"><b>Materias Asignadas</b></div>
				<display:table name="sessionScope.lista1" pagesize="10" id="lista1DisplayTag" excludedParams="*" requestURI="${ctx}/html/registro/procesos/asigMatCiclo.jsp"  defaultsort="1">
					<display:column property="ciclo"   title="Ciclo"         	style="width:20%; text-align:center;"   sortable="true"/>			
					<display:column property="materia" title="Materia" 			style="width:40%; text-align:left;"     sortable="true"/>			
					<display:column property="seccion" title="Secci&oacute;n" 	style="width:20%; text-align:center;"   sortable="true"/>			
					<display:column property="accion" title="Acci&oacute;n"	 	style="width:20%; text-align:center;"/>			
				</display:table>
			</logic:present>
		</div>
		<div id="rightcolumn">
			<logic:present name="lista0" scope="session">
				<div id="cap"><b>Materias que pueden ser asignadas</b></div>
				<display:table name="sessionScope.lista0" pagesize="10" id="lista0DisplayTag" excludedParams="*" requestURI="${ctx}/html/registro/procesos/asigMatCiclo.jsp"  defaultsort="1">
   				   <display:column property="materia"  title="Materia" 		style="width:80%; text-align:left;"   sortable="true"/>			
					<display:column property="accion"   title="Acci&oacute;n"   style="width:40%; text-align:center;"/> 
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
	
	
	
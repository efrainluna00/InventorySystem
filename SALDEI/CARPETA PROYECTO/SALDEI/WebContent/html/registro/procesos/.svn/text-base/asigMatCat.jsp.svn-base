<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<%@ page import="com.saldei.web.services.registro.AsigMatCatServices" %>
<html:html>
<head>
<% 
AsigMatCatServices services = new AsigMatCatServices();
request.setAttribute("ciclos", services.getCiclo());
%>
<script type="text/javascript">
	function getMaterias(){
		document.forms[0].cmd.value = 'getMateriaOnChange';
		document.forms[0].submit();
	}
	function saveAll(){
		var c = confirm('Desea guardar los cambios?');
		if(c){
			document.forms[0].cmd.value = 'saveAll';
			document.forms[0].submit();
		}		
	}
	function cancelAll(){
		var c = confirm('Desea cancelar los cambios?');
		if(c){
			document.forms[0].cmd.value = 'show';
			document.forms[0].submit();
		}		
	}
	$(document).ready(function(){$.NiceJForms.build()});		
</script>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<body>
<html:form action="/asigMatCat"  styleClass="niceform"  >
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
			<div id="titule"> Asignar Materia a Catedr&aacute;tico </div><br/>
			<table>
				<tr>
					<td align="center">
					<fieldset>
						<table align="center">
							<tr>
								<td align="left">Ciclo: </td>
								<td align="left">
									<html:select property="ciclo" onchange="getMaterias();" styleId="ciclo" styleClass="width_100">
										<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
										<html:options collection="ciclos" labelName="idCiclo" labelProperty="idCiclo" 
																	   name="selectCiclo" property="idCiclo"/>
									</html:select>	</td>
								<td align="left"> <html:button property="btnN" value="Mostrar Materias" onclick="getMaterias();" styleClass="ButtonText"/></td>	
							</tr>
							<tr>
								<td align="left">Materias: </td>
								<td align="left" colspan="2" >
									<logic:notPresent name="materias" scope="session">
										<html:select property="materia" styleId="materia" styleClass="width_200">
											<html:option value="Seleccione">
												<bean:message key="commons.sel.seleccione"/>
											</html:option>
										</html:select>
									</logic:notPresent>								
									<logic:present name="materias" scope="session">
										<html:select property="materia" styleId="materia" styleClass="width_200">
											<html:option value="Seleccione">
												<bean:message key="commons.sel.seleccione"/>											
											</html:option>
											<html:options collection="materias" labelName="nombreMateria" labelProperty="nombreMateria" 
																	property="idMateria" name="lblMateria"/>
										</html:select>
									</logic:present>								
								</td>
							</tr>							
							<tr align="center">
								<td colspan="3">
									<html:submit value="Mostrar" onclick="cmd.value='show'" styleClass="ButtonText"/>
									<html:button value="GuardarCambios" onclick="saveAll();" property="btnSaveAll" styleClass="ButtonText"/>
									<html:button value="Cancelar Cambios" onclick="cancelAll();" property="btnCancelAll" styleClass="ButtonText"	/>
								</td>
							</tr>
							<tr align="center">
								<td colspan="3">
								<logic:present name="asigMsg" scope="request">
									<logic:equal name="asigMsg" scope="request" value="error">
										<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/>
									</logic:equal>
									<logic:equal name="asigMsg" scope="request" value="noData">
										<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="asigmatcat.lb.error.no.data"/>
									</logic:equal>	
									<logic:equal name="asigMsg" scope="request" value="vacio">
										<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/>
									</logic:equal>			
									<logic:equal name="asigMsg" scope="request" value="listOne">
										<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="asigmatcat.lbl.error.list.one"/>
									</logic:equal>
									<logic:equal name="asigMsg" scope="request" value="errorSave">
										<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/>
									</logic:equal>	
									<logic:equal name="asigMsg" scope="request" value="exito">
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
			<logic:present name="lista1MatCat" scope="session">
			     <div id="cap"><b>Usuarios DEI</b></div>
				<display:table name="sessionScope.lista1MatCat" pagesize="10" id="lista1DisplayTag" excludedParams="*" requestURI="${ctx}/html/registro/procesos/asigMatCat.jsp"  defaultsort="1">
					<display:caption class="cap" id="cap"></display:caption>
					<display:column property="idUsuario" 	 title="ID Usuario"    	style="width:20%; text-align:left;" sortable="true"/>
					<display:column property="nombreUsuario" title="Nombre" 		style="width:30%; text-align:left;" sortable="true"/> 	
					<display:column property="cargo"         title="Cargo" 			style="width:30%; text-align:left;" sortable="true"/>
					<display:column property="accion" 		 title="Acci&oacute;n" 	style="width:20%; text-align:left;"  />
				</display:table>
			</logic:present>
 		</div>
 		<div id="rightcolumn">
 			<logic:present name="lista0MatCat" scope="session">
     	        <div id="cap"><b>Usuarios DEI que pueden ser asignados</b></div>
				<display:table name="sessionScope.lista0MatCat" pagesize="10" id="lista0DisplayTag" excludedParams="*" requestURI="${ctx}/html/registro/procesos/asigMatCat.jsp"  defaultsort="1">
					<display:column property="idUsuario" 	 title="ID Usuario"    	style="width:20%; text-align:left;" sortable="true"/>
					<display:column property="nombreUsuario" title="Nombre" 		style="width:30%; text-align:left;" sortable="true"/> 	
					<display:column property="cargo"         title="Cargo" 			style="width:30%; text-align:left;" sortable="true"/>
					<display:column property="accion" 		 title="Acci&oacute;n" 	style="width:20%; text-align:left;"  />
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
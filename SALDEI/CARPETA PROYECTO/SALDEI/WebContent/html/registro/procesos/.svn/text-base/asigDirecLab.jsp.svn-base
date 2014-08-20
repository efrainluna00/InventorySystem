<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<%@ page import="com.saldei.web.services.registro.AsigDirecLabServices" %>
<script type="text/javascript" src="${ctx}/js/calendar/calendar.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar_helper.js"></script>
<html:html>
<head>
<%
	AsigDirecLabServices services = new AsigDirecLabServices();
	request.setAttribute("labs" , services.getLaboratorios());
%>
<script type="text/javascript">
	function verCalen(field, btn){
			Calendar.setup({
		    inputField : field,
	    	ifFormat   : "%d/%m/%Y",
		    button     : btn
			});
	}	
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
			document.forms[0].cmd.value='cancelAll';
			document.forms[0].submit();
		}
	}	
	
	function mostrar(){
		var lab = document.forms[0].laboratorio.value;				
		if(lab != ''){				
			document.forms[0].cmd.value='show';
			document.forms[0].submit();
		}	
	}	
	
	function putInHiddenIni(){
		document.forms[0].fechaIniHidden.value = document.forms[0].fechaIni.value;
	}	
	function putInHiddenFin(){
		document.forms[0].fechaFinHidden.value = document.forms[0].fechaFin.value;
	}	
	function obtener(num){
		if(document.forms[0].fechaIni.value == '' || document.forms[0].fechaFin.value == ''){
			alert('La fecha de Inicio y la de Fin de Vigencia  son obligatorios');
		}else{
		document.forms[0].idobt.value = num;
		document.forms[0].cmd.value='hash';
		document.forms[0].submit();
		}
	}
	$(document).ready(function(){$.NiceJForms.build()});	
</script>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<body>
<html:form action="/asigDirecLab" styleClass="niceform"  >

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
			<div id="titule"> <bean:message key='direcLab.lbl.legend'/> </div><br/>
			<table>
				<tr>
					<td align="center">
					<fieldset>
						<table align="center">
							<tr>
								<td align='left'><bean:message key='direcLab.lbl.nombre'/></td>
								<td align='left'><input type="hidden" name="idobt" id="idobt"/>
									<html:select property="laboratorio"  styleId="laboratorio" styleClass="width_300">
										<html:option value="Seleccione"><bean:message key='commons.sel.seleccione'/></html:option>
										<html:options collection="labs" labelName="nombreLaboratorio" labelProperty="nombreLaboratorio" 
																   name="selectLab" property="idLaboratorio"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="left">Fecha Inicio de Vigencia: </td>
								<td align="left">
									<input type="text" name="fechaIni" id="fechaIni"  onchange="putInHiddenIni();" size="20"/><input type="hidden" name="fechaIniHidden"/>									
									<input type="button" value="....." id="btnFecha" onmousedown="verCalen('fechaIni', 'btnFecha' )" class="ButtonText"/>
								</td>
							</tr>
							<tr>
								<td align="left">Fecha Fin de Vigencia: </td>
								<td align="left">
									<input type="text" name="fechaFin" id="fechaFin"  size="20" onchange="putInHiddenFin();"/><input type="hidden" name="fechaFinHidden"/>
									<input type="button" value="....." id="btnFechaFin" onmousedown="verCalen('fechaFin', 'btnFechaFin' )" class="ButtonText"/>
								</td>
							</tr>
							<tr align='center'>
								<td align='center' colspan='2'>
									<html:button styleClass="ButtonText" onclick="mostrar();" property="btnsaveAll">
										Mostrar
									</html:button>
									<html:button styleClass="ButtonText" onclick="saveAll();" property="btnsaveAll">
										<bean:message key='directLab.btn.saveAll'/>
									</html:button>
									<html:button styleClass="ButtonText"onclick="cancelAll();" property="btncancelall">
										<bean:message key='directLab.btn.cancelAll'/>
									</html:button>
								</td>
							</tr>
							<tr align='center'>
								<td align='center' colspan='2'>
								<logic:present name="aslMsg" scope="request">
									<logic:equal name="aslMsg" scope="request" value="error">
										<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.general"/>
									</logic:equal>
									<logic:equal name="aslMsg" scope="request" value="vacio">
										<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio.Sel"/>
									</logic:equal>	
									<logic:equal name="aslMsg" scope="request" value="exito">
										<img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.guardarTodos.exito"/>
									</logic:equal>
									<logic:equal name="aslMsg" scope="request" value="noData">
										<img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="asigmatcat.lb.error.no.data"/>
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
				<logic:present name="listDsigDirec" scope="session">
				     <div id="cap"><bean:message key="directLab.displaytag.asignados"/></div>
					<display:table name="sessionScope.listDsigDirec" id="lista1" sort="list" pagesize="10"  excludedParams="*" requestURI="${ctx}/html/registro/procesos/asigDirecLab.jsp" defaultsort="1">
							<display:column property="idUsuario" 	title="Usuario"  			  headerClass="sortable" style="width:20%; text-align:left;" sortable="true"/>
							<display:column property="nombre"      	title="Nombre"  			  headerClass="sortable"  style="width:30%; text-align:left;" sortable="true"/>
							<display:column property="fechaIniDto" 	title="Fecha Inicio Vigencia" headerClass="sortable"  style="width:15%; text-align:center;" sortable="true"/>
							<display:column property="fechaFinDto" 	title="Fecha Fin Vigencia"    headerClass="sortable"  style="width:15%; text-align:center;" sortable="true"/>
							<display:column property="remove" 		title="Acci&oacute;n"										  style="width:20%; text-align:center;"/>
						</display:table>
				</logic:present>
			</div>	
			<div id="rightcolumn">
				<logic:present name="listAsigDirec" scope="session">
				     <div id="cap"><bean:message key="directLab.displaytag.existentes"/></div>
					<display:table name="sessionScope.listAsigDirec" id="lista2" sort="list" pagesize="10" excludedParams="*" requestURI="${ctx}/html/registro/procesos/asigDirecLab.jsp"  defaultsort="1">
						<display:column property="idUsuariof" title="Usuario" 		   headerClass="sortable" 	style="width:20%; text-align:left;" group="1" sortable="true"/>
						<display:column property="nombref" title="Nombre"              headerClass="sortable"   style="width:30%; text-align:cleft;" sortable="true"/>
						<display:column property="labosf" title="Laboratorios a Cargo" headerClass="sortable"   style="width:30%; text-align:left;" sortable="true"/>
						<display:column property="accionf" title="Acci&oacute;n"       							style="width:20%; text-align:center;"/>
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
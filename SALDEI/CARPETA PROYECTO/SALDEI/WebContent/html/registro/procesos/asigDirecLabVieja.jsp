<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<html:html>
<head>
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
<html:form action='/asigDirecLab' styleClass="niceform"  >
<html:hidden property="cmd" value=''/>
	<table align='center'>
		<tr align='center'>
			<td>
				<fieldset>
					<legend><bean:message key='direcLab.lbl.legend'/></legend>
						<table align='center'>
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
									<html:submit styleClass="ButtonText" onclick="mostrar();" property="btnsaveAll">
										Mostrar
									</html:submit>
									<html:button styleClass="ButtonText" onclick="saveAll();" property="btnsaveAll">
										<bean:message key='directLab.btn.saveAll'/>
									</html:button>
									<html:button styleClass="ButtonText"onclick="cancelAll();" property="btncancelall">
										<bean:message key='directLab.btn.cancelAll'/>
									</html:button>
								</td>
							</tr>
						</table>
				</fieldset>
			</td>
		</tr>
	</table>
	
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
	
<table align="center">
	<tr  valign="top">
		<td>
			<logic:present name="listDsigDirec" scope="session">
						<display:table name="sessionScope.listDsigDirec" id="lista1" sort="list" pagesize="10" 
						                          excludedParams="*" requestURI="${ctx}/html/registro/procesos/asigDirecLab.jsp" defaultsort="1">
						     <display:caption><bean:message key="directLab.displaytag.asignados"/></display:caption>
							<display:column property="idUsuario" title="Usuario"  sortable="true" headerClass="sortable"/>
							<display:column property="nombre" title="Nombre"  sortable="true" headerClass="sortable"/>
							<display:column property="fechaIniDto" title="Fecha Inicio Vigencia"  sortable="true" headerClass="sortable"/>
							<display:column property="fechaFinDto" title="Fecha Fin Vigencia"  sortable="true" headerClass="sortable"/>
							<display:column property="remove" title="Accion"/>
						</display:table>
			</logic:present>	
		</td>		
		<td>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
		<td>
			<logic:present name="listAsigDirec" scope="session">
						<display:table name="sessionScope.listAsigDirec" id="lista2" sort="list" pagesize="10" 
						                          excludedParams="*" requestURI="${ctx}/html/registro/procesos/asigDirecLab.jsp"  defaultsort="1">
						     <display:caption><bean:message key="directLab.displaytag.existentes"/></display:caption>
							<display:column property="idUsuariof" title="Usuario" sortable="true" headerClass="sortable" group="1"/>
							<display:column property="nombref" title="Nombre" sortable="true" headerClass="sortable"/>
							<display:column property="labosf" title="Laboratorios a Cargo" sortable="true" headerClass="sortable" />
							<display:column property="accionf" title="Accion"/>
						</display:table>
			</logic:present>		
		</td>
	</tr>
</table>
</html:form>
</body>
</html:html>
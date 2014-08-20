<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<%@ page import="com.saldei.web.services.registro.CicloServices" %>
<html:html>
<%
	CicloServices services = new CicloServices();
	request.setAttribute("cicloActual", services.getCicloActivo());
%>
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
	function modificar(IdCiclo, NumCiclo, AnyoCiclo, FechaIni, FechaFin, Activo){	
		document.forms[0].btnSave.disabled      = true;	
		jQuery.NiceJForms.cambio('btnSave');																											
		if(IdCiclo != 'null')
			document.forms[0].idCiclo.value     = IdCiclo;
		if(NumCiclo != 'null')
			jQuery.NiceJForms.selectValor('numCiclo', 1, NumCiclo);
		if(AnyoCiclo != 'null')
			jQuery.NiceJForms.selectValor('anyoCiclo', 2, AnyoCiclo);
		if(FechaIni != 'null')
			document.forms[0].fechaIni.value   	= FechaIni;
		if(FechaFin != 'null')
			document.forms[0].fechaFin.value	= FechaFin;
		if(Activo != 'null')
			jQuery.NiceJForms.selectValor('cicloActivo', 0, Activo);
		document.forms[0].numCiclo.disabled     = true;
		document.forms[0].anyoCiclo.disabled    = true;	
	}
	function isNumberKey(evt){
		var charCode = (evt.which) ? evt.which : event.keyCode         
		if (charCode > 31 && (charCode < 48 || charCode > 57))            
			return false;          
		return true;
	}	
	
	function Comparar_Fecha(String1, String2) {
		// Si los dias y los meses llegan con un valor menor que 10 
		// Se concatena un 0 a cada valor dentro del string 
		if (String1.substring(1,2)=="/") {
		String1="0"+String1
		}
		if (String1.substring(4,5)=="/"){
		String1=String1.substring(0,3)+"0"+String1.substring(3,9)
		}
		
		if (String2.substring(1,2)=="/") {
		String2="0"+String2
		}
		if (String2.substring(4,5)=="/"){
		String2=String2.substring(0,3)+"0"+String2.substring(3,9)
		}
		
		dia1=String1.substring(0,2);
		mes1=String1.substring(3,5);
		anyo1=String1.substring(6,10);
		dia2=String2.substring(0,2);
		mes2=String2.substring(3,5);
		anyo2=String2.substring(6,10);
		
		
		if (dia1 == "08") // parseInt("08") == 10 base octogonal
		dia1 = "8";
		if (dia1 == '09') // parseInt("09") == 11 base octogonal
		dia1 = "9";
		if (mes1 == "08") // parseInt("08") == 10 base octogonal
		mes1 = "8";
		if (mes1 == "09") // parseInt("09") == 11 base octogonal
		mes1 = "9";
		if (dia2 == "08") // parseInt("08") == 10 base octogonal
		dia2 = "8";
		if (dia2 == '09') // parseInt("09") == 11 base octogonal
		dia2 = "9";
		if (mes2 == "08") // parseInt("08") == 10 base octogonal
		mes2 = "8";
		if (mes2 == "09") // parseInt("09") == 11 base octogonal
		mes2 = "9";
		
		dia1=parseInt(dia1);
		dia2=parseInt(dia2);
		mes1=parseInt(mes1);
		mes2=parseInt(mes2);
		anyo1=parseInt(anyo1);
		anyo2=parseInt(anyo2);
				
		if (anyo1>anyo2)
		{
		return false;
		}
		
		if ((anyo1==anyo2) && (mes1>mes2))
		{
		return false;
		}
		if ((anyo1==anyo2) && (mes1==mes2) && (dia1>dia2))
		{
		return false;
		} 
		
		return true;
	}	
	function save(){
		if( document.forms[0].btnSave.value == 'Guardar'){
			if(!Validar()){
				alert ("Debe de Ingresar los Datos validos");
			}
			else{
				if(Comparar_Fecha(document.forms[0].fechaIni.value,  document.forms[0].fechaFin.value)){
					document.forms[0].cmd.value = 'save';
					document.forms[0].submit();
				}	
				else
					alert("Las fechas de Ciclo no son validas");	
			}
		}
		else{
			action = confirm("¿Desea guardar los cambios?");
			if (action) {
				document.forms[0].cmd.value = 'guardarCambios';
				document.forms[0].submit();
			}	
		}
	}	
	function update(){
		if( document.forms[0].btnUpdate.value == 'Actualizar'){
			if(!Validar()){
				alert ("Debe de Ingresar los Datos validos");
			}
			else{
				if(Comparar_Fecha(document.forms[0].fechaIni.value,  document.forms[0].fechaFin.value)){
					document.forms[0].cmd.value = 'update';
					document.forms[0].submit();
				}
				else
					alert("Las fechas de Ciclo no son validas");	
			}
		}else{
			var c = confirm('¿Desea cancelar los cambios?');
			if(c){
				document.forms[0].cmd.value = 'find';
				document.forms[0].submit();
			}			
		}
	}	
	function find(){
		document.forms[0].cmd.value = 'find';
		document.forms[0].submit();	
	}	
	function clean(){
		document.forms[0].cmd.value = 'cleanSession';
		document.forms[0].submit();
	}	
	function Validar(){
		if (document.forms[0].numCiclo.value == '0')
			return false;
		if (document.forms[0].anyoCiclo.value == 'Seleccione')
			return false;
		if (document.forms[0].fechaIni.value == '')
			return false;
		if (document.forms[0].fechaFin.value == '')
			return false;
		return true; 	
	}		
	function remove(){
		if( document.forms[0].btnRem.value == 'Activar'){
			document.forms[0].cmd.value = 'remover';
			document.forms[0].submit();
		} 
		if( document.forms[0].btnRem.value == 'Modificar'){
			document.forms[0].cmd.value = 'inicio';
			document.forms[0].submit();
		}
	}
	$(document).ready(function(){$.NiceJForms.build()});
</script>
<%	request.setAttribute("anyos", request.getSession().getAttribute("anyos")); %>
<head><title><bean:message key="commons.lbl.titulo"/></title></head>
<body >
<html:form action="/ciclo" styleClass="niceform"  >
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
			<div id="titule"> <bean:message key="ciclo.lbl.titulo"/> </div><br/>
			<table>
				<tr>
					<td align="center">
					<fieldset>
						<logic:present name="modulesCiclo" scope="session">
							<legend><%= (String) request.getSession().getAttribute("modulesCiclo") %></legend>
						</logic:present><br/>
						<table align="center">
							<tr>
								<td align="left"><bean:message key="ciclo.lbl.idCiclo"/></td>
								<td align="left"><html:text property="idCiclo" styleId="idCiclo" maxlength="6" readonly="true" value="${requestScope.CicloForm.idCiclo}" size="20" /></td>
								<td align="left"><bean:message key="ciclo.lbl.activo"/></td>
								<td align="left"><html:select styleId="cicloActivo"  property="cicloActivo" value="${requestScope.CicloForm.cicloActivo}" styleClass="width_188">
											  <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
											  <html:option value="S"><bean:message key="commons.sel.si"/></html:option>
											  <html:option value="N"><bean:message key="commons.sel.no"/></html:option>
											  </html:select></td>
							</tr>			
							<tr>
								<td align="left"><bean:message key="ciclo.lbl.numero"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><html:select styleId="numCiclo"  property="numCiclo" value="${requestScope.CicloForm.numCiclo}" styleClass="width_188">
												  <html:option value="0"><bean:message key="commons.sel.seleccione"/></html:option>
												  <html:option value="1">01</html:option>
												  <html:option value="2">02</html:option>
												  <html:option value="3">03</html:option>
												  </html:select></td>
								<td align="left"><bean:message key="ciclo.lbl.anyo"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><html:select styleId="anyoCiclo" property="anyoCiclo" value="${requestScope.CicloForm.anyoCiclo}" styleClass="width_180">
												 <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
												 <html:options collection="anyos" property="element1" labelProperty="element1"/>
												 </html:select></td>												
							</tr>
							<tr>
								<td align="left"><bean:message key="ciclo.lbl.fechaIni"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><input type="text" name="fechaIni" id="fechaIni" readonly="readonly" size="20"  value="${requestScope.CicloForm.fechaIni}" /><input type="button" value="....." id="btnFecha" onmousedown="verCalen('fechaIni', 'btnFecha' )" class="ButtonText"/></td>
								<td align="left"><bean:message key="ciclo.lbl.fechaFin"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><input type="text" name="fechaFin" id="fechaFin" readonly="readonly" size="20"  value="${requestScope.CicloForm.fechaFin}" /><input type="button" value="....." id="btnFecha2" onmousedown="verCalen('fechaFin', 'btnFecha2' )" class="ButtonText"/></td>
							</tr>
							<tr align="right">					
								<td align="right" colspan="2"><bean:message key="commons.msg.obligatorio"/><br/><br/>	</td>			
								<td align="right" colspan="2" style="text-align:center;"><%= (String) request.getAttribute("cicloActual") %></td>
							</tr>
							<tr>
								<td align="left"> </td>
								<td align="center" colspan="2" style="text-align: center;">
									<input type="button" id="btnSave"    name="btnSave"     onclick="save();"       value= "<%=(String) request. getSession().getAttribute("btnSave")   %>"/>
									<input type="button" id="btnUpdate"  name="btnUpdate"  	onclick="update();"  	value= "<%=(String) request.getSession().getAttribute("btnUpdate") %>"/>
									<input type="button" id="btnFind"    name="btnFind"     onclick="find();"       value= "<%=(String) request.getSession().getAttribute("btnFind") %>"/>
									<input type="button" id="btnClean"   name="btnClean"    onclick="clean();"     	value= "<%=(String) request.getSession().getAttribute("btnClean") %>"/>
									<input type="button" id="btnRem"     name="btnRem"      onclick="remove();" 	value= "<%=(String) request.getSession().getAttribute("btnRem") %>"/>
								</td>
								<td align="left"> </td>
							</tr>
							<tr>
							<td align="left"> </td>
							<td align="center" colspan="2" style="text-align: center;">					
								<logic:present name="cicloMsg" scope="request" >
									<logic:equal value="vacio"        name="cicloMsg" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.obligatorio"/></center></logic:equal>
									<logic:equal value="exito"        name="cicloMsg" scope="request"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></center></logic:equal>
									<logic:equal value="existe"       name="cicloMsg" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="ciclo.error.existe"/></center></logic:equal>
									<logic:equal value="exitoDml"     name="cicloMsg" scope="request"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></center></logic:equal>
								    <logic:equal value="cambios"      name="cicloMsg" scope="request"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardarCambios"/></center></logic:equal>
								    <logic:equal value="errorCambios" name="cicloMsg" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardarCambios"/></center></logic:equal>
								    <logic:equal value="existeCicloA" name="cicloMsg" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="ciclo.error.cicloActivo"/></center></logic:equal>    
								</logic:present>		</td>			
							</tr>
						</table>
					</fieldset>
					</td>
				</tr>
			</table>
		</div>
		<div id="leftcolumn"> 
			<logic:present name="MapCicloA" scope="session">
				<div id="cap"><bean:message key="ciclo.lbl.activos"/></div>
				<display:table name="sessionScope.MapCicloA" pagesize="10" sort="list" excludedParams="*" id="tableMapCicloA" defaultsort="1">
					<display:column property="numCiclo"   title="N&uacute;mero" style="width:15%; text-align:center;" sortable="true"/>
					<display:column property="anyoCiclo"  title="Año Ciclo"     style="width:15%; text-align:center;" sortable="true"/>
					<display:column property="fechaIni"   title="Fecha Inicio"  style="width:25%; text-align:center;" sortable="true"/>
					<display:column property="fechaFin"   title="Fecha Fin"     style="width:25%; text-align:center;" sortable="true"/>
					<display:column property="accion"     title="Acci&oacute;n" style="width:20%; text-align:center;"/>
				</display:table>
			</logic:present>
		</div>
		<div id="rightcolumn">
			<logic:present name="MapCicloI" scope="session" >
				<div id="cap"><bean:message key="ciclo.lbl.inactivos"/></div>
				<display:table name="sessionScope.MapCicloI" pagesize="10" sort="list" excludedParams="*" id="tableMapUserI" defaultsort="1">
					<display:column property="numCiclo"   title="N&uacute;mero" style="width:15%; text-align:center;" sortable="true"/>
					<display:column property="anyoCiclo"  title="Año Ciclo"     style="width:15%; text-align:center;" sortable="true"/>
					<display:column property="fechaIni"   title="Fecha Inicio"  style="width:25%; text-align:center;" sortable="true"/>
					<display:column property="fechaFin"   title="Fecha Fin"     style="width:25%; text-align:center;" sortable="true"/>
					<display:column property="accion"     title="Acci&oacute;n" style="width:20%; text-align:center;"/>
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
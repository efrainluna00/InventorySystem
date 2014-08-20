<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<script type="text/javascript" src="${ctx}/js/calendar/calendar.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar_helper.js"></script>
<script type="text/javascript">
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
	function verCalen(field, btn){
		Calendar.setup({
	    inputField : field,
    	ifFormat   : "%d/%m/%Y",
	    button     : btn
		});
	}
	function save(){
		if(Comparar_Fecha(document.forms[0].fechaIni.value,  document.forms[0].fechaFin.value)){
			var tipo =  document.forms[0].tipo.value;
			if (tipo != '' && tipo == 'U') {
				document.forms[0].cmd.value = 'saveUsrVigencia';
				document.forms[0].submit();
			}
			if(tipo !='' && tipo == 'C'){
				document.forms[0].cmd.value = 'saveCarVigencia';
				document.forms[0].submit();		
			}
		}
		else{
			alert('Fecha inicial debe ser mayor que la fecha final');
		}
			
	}
	function clean(){
		document.forms[0].fechaIni.value = '';
		document.forms[0].fechaFin.value = '';
	}

	function verificarParametro(){
		<% String idUsuario = (String)request.getSession().getAttribute("idUsu");%>
		<% String idCarrera = (String)request.getSession().getAttribute("idCar");%>
		<% String planEstudio = (String)request.getSession().getAttribute("planE");%>
		<%if(idUsuario==null && idCarrera==null && planEstudio==null){%>
			this.window.close()
		<%}%>
	}
$(document).ready(function(){$.NiceJForms.build()});	
</script>	
<html:html>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<body onload="verificarParametro()" style="background-color: #01111d;" >
<html:form action="/Vigencia"  styleClass="niceform">
<html:hidden property="cmd" value=""/>
<div id="s"  style="background-color:#FFFFFF; ">

	<div id="titule"><bean:message key="vigencia.lbl.titulo"/></div><br/>
	<table>
		<tr>
			<td>
			<fieldset>

				<logic:present name="UsrVigencia" scope="session">
					<table align="center">
						<tr>
							<td align="left"><bean:message key="login.lbl.usr"/><bean:message key="commons.msg.*"/></td>
							<td align="left"><html:text property="idUsuario" styleId="idUsuario" maxlength="10" value="${requestScope.VigenciaForm.idUsuario}" size="10"  readonly="true"/>		</td>
						</tr>	
					</table>	
				</logic:present>	
				<logic:present name="CarVigencia" scope="session">
					<table align="center">
						<tr>
							<td align="left"><bean:message key="usuarioMto.lbl.carrera"/><bean:message key="commons.msg.*"/></td>
							<td align="left"><html:text property="idCarrera" maxlength="10" value="${requestScope.VigenciaForm.idCarrera}" size="10"  readonly="true"/></td>
						</tr>	
						<tr>
							<td align="left"><bean:message key="carrera.lbl.planEstudio"/><bean:message key="commons.msg.*"/></td>
							<td align="left"><html:text property="planEstudio" maxlength="10" value="${requestScope.VigenciaForm.planEstudio}" size="10"  readonly="true"/></td>
						</tr>	
					</table>		
				</logic:present>					
				<table>
					<tr>
						<td align="left"><bean:message key="usuarioMto.lbl.iniVigencia"/></td>
						<td align="left"><html:hidden property="tipo"  styleId="tipo" value="${requestScope.VigenciaForm.tipo}"/>	
						<input type="text" name="fechaIni" id="fechaIni" readonly="readonly" size="5" value="${requestScope.VigenciaForm.fechaIni}"/><input type="button" value="...." id="btnIniVig" onmousedown="verCalen('fechaIni' ,'btnIniVig')" class="ButtonText"/></td>
						<td align="left"><bean:message key="usuarioMto.lbl.finVigencia" /><bean:message key="commons.msg.*"/></td>
						<td align="left"><input type="text" name="fechaFin" id="fechaFin" readonly="readonly" size="5" value="${requestScope.VigenciaForm.fechaFin}"/><input type="button" value="...." id="btnFinVig" onmousedown="verCalen('fechaFin', 'btnFinVig')" class="ButtonText"/></td>
					</tr>
				</table>	
				<table>
					<tr>
						<td><html:button property="btnSave"  styleId="btnSave"  onclick="save();" > <bean:message key="vigencia.btn.Agregar"/> </html:button> </td>
						<td><html:button property="btnClean" styleId="btnClean" onclick="clean();" ><bean:message key="commons.btn.clean"/>    </html:button> </td>
					</tr>
				</table>	
				<logic:present name="vigencia" scope="request" >
					<logic:equal value="vacio"        name="vigencia" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.obligatorio"/></center></logic:equal>
					<logic:equal value="exito"        name="vigencia" scope="request"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></center></logic:equal>
					<logic:equal value="existe"       name="vigencia" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="usuarioMto.error.existe"/></center></logic:equal>
					<logic:equal value="errorSave"    name="vigencia" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></center></logic:equal>	
					<logic:equal value="validacionFecha"    name="vigencia" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="vigencia.fecha.validacion"/></center></logic:equal>	
					<logic:equal value="errorFecha"   name="vigencia" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<font color='#EE1122'>La fecha inicial debe ser mayor que la fecha actual</font></center></logic:equal>	
					<logic:equal value="errorFecha2"   name="vigencia" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<font color='#EE1122'>La fecha inicial debe ser mayor a la maxima fecha inicial registrada</font></center></logic:equal>	
					
				</logic:present>
			</fieldset>
			</td>
		</tr>
	</table>
	</div>
	<logic:present name="UsrVigencia" scope="session">
		<div id="z" style="background-color: #01111d">
		<display:table name="sessionScope.UsrVigencia" pagesize="10" sort="list" excludedParams="*" id="UserVigencia" defaultsort="1">
			<display:column property="fechaIni"   title="Fecha de Inicio" 			style="width:45%" sortable="true"/>
			<display:column property="fechaFin"   title="Fecha de Finalizacion"     style="width:35%" sortable="true"/>
		</display:table>
		</div>
	</logic:present>	
	<logic:present name="CarVigencia" scope="session">
		<div id="e" style="background-color: #01111d">
		<display:table name="sessionScope.CarVigencia" pagesize="10" sort="list" excludedParams="*" id="CarreraVigencia" defaultsort="1">
			<display:column property="fechaIni"   title="Fecha de Inicio" 			style="width:45%" sortable="true"/>
			<display:column property="fechaFin"   title="Fecha de Finalizacion"     style="width:35%" sortable="true"/>
		</display:table>
		</div>
	</logic:present>	</html:form>
</body>
</html:html>

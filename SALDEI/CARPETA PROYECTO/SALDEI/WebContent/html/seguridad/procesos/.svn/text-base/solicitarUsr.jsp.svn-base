<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<script type="text/javascript" src="${ctx}/js/calendar/calendar.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar_helper.js"></script>
<html:html>
<head>
<script type="text/javascript">
	$(document).ready(function (){  
			$("#btnClean").click(function(){
				jQuery.NiceJForms.selectValor('estadoCivil', 0, 'Seleccione');
				jQuery.NiceJForms.selectValor('sexo', 1, 'Seleccione');
				jQuery.NiceJForms.selectValor('tipo', 2, 'Seleccione');
				jQuery.NiceJForms.selectValor('oyente', 3, 'Seleccione');
				document.forms[0].primerNom.value 	 = '';
				document.forms[0].nombreRestante.value 	 = '';
				document.forms[0].primerApe.value 	 = '';
				document.forms[0].apellidoRestante.value 	 = '';
				document.forms[0].email.value 		 = '';
				document.forms[0].fechaNac.value 	 = '';
				document.forms[0].carnetEstudiante.value 	 = '';
				document.forms[0].telefonoCasa.value   = '';
				document.forms[0].telefonoCel.value    = '';
				document.forms[0].ext.value 		   = '';
				document.forms[0].comentario.value     = '';
				document.forms[0].solicitadoPor.value  = '';
				document.forms[0].codigoEmpleado.value  = '';				
			});
      });	
	
	
	function verCalen(){
		Calendar.setup({
	    inputField : "fechaNac",
    	ifFormat   : "%d/%m/%Y",
	    button     : "btnFecha"
		});
	}
	function isNumberKey(evt){
		var charCode = (evt.which) ? evt.which : event.keyCode         
		if (charCode > 31 && (charCode < 48 || charCode > 57))            
			return false;          
		return true;
	}
	
	function Validar(){	
		if(document.forms[0].primerNom.value == '')
			return false;
		if(document.forms[0].primerApe.value == '')
			return false;
		if(document.forms[0].email.value == '')
			return false;	
		if(document.forms[0].estadoCivil.value == 'Seleccione')
			return false;				
		if(document.forms[0].sexo.value == 'Seleccione')
			return false;
		if(document.forms[0].tipo.value == 'Seleccione')
			return false;
		if (document.forms[0].tipo.value == 'D'){
			if(document.forms[0].codigoEmpleado.value == '')
				return false;
		}
		if (document.forms[0].tipo.value == 'S'){
			if(document.forms[0].carnetEstudiante.value == '')
				return false;
		}
		return true;
	}
	
	
	function send(){
		if (!Validar())
			alert("Debe de Ingresar los Campos Obligatorios")
		else{
			document.forms[0].cmd.value = 'send';
			document.forms[0].submit();	
		}	
	}
	

	
	
   $(document).ready(function(){$.NiceJForms.build()});

</script>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<% request.setAttribute("estadoCivil", request.getSession().getAttribute("estCivil")); %>

<body style="background-color: #ffffff;">
<html:form action="/solUsr" styleClass="niceform">
<html:hidden property="cmd" value="solEst"/>
	<div id="titule"> <bean:message key="solicitarUsr.lbl.titulo"/></div><br/>		
	<table align="center">
		<tr align="center">
			<td>
				<fieldset>
					<table align="center">
						<tr align="left">
							<td><bean:message key="usuarioMto.lbl.primerNom"/><bean:message key="commons.msg.*"/></td>
							<td><html:text property="primerNom" size="10" maxlength="15" /></td>
							<td><bean:message key="usuarioMto.lbl.nombres"/></td>
							<td><html:text property="nombreRestante" size="10" maxlength="25" /></td>
						</tr>
						<tr align="left">
							<td><bean:message key="usuarioMto.lbl.primerApe"/><bean:message key="commons.msg.*"/></td>
							<td><html:text property="primerApe" size="10" maxlength="15" /></td>
							<td><bean:message key="usuarioMto.lbl.apellidos"/></td>
							<td><html:text property="apellidoRestante" size="10" maxlength="25" /></td>
						</tr>
						<tr align="left">
							<td><bean:message key="usuarioMto.lbl.email" /><bean:message key="commons.msg.*"/></td>
							<td><html:text property="email" size="10" maxlength="75" /></td>
							<td align="left"><bean:message key="usuarioMto.lbl.fechaNac"/></td>
							<td align="center"><input type="text" size="10" name="fechaNac" id="fechaNac" readonly="readonly"   value=""/><input type="button" value="..." id="btnFecha" onmousedown="verCalen()" class="ButtonText"/></td>
						</tr>
						<tr>
							<td align="left"><bean:message key="usuarioMto.lbl.estadoCivil"/><bean:message key="commons.msg.*"/></td>
							<td align="left"><html:select styleId="estadoCivil"  property="estadoCivil" value="${requestScope.UsuarioForm.estadoCivil}"  styleClass="width_100">
											 <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
											 <html:options collection="estadoCivil" property="idMulticode" labelProperty="codigo"/>
											 </html:select>
							</td>
							<td align="left"><bean:message key="usuarioMto.lbl.sexo" /><bean:message key="commons.msg.*"/></td>
							<td align="left"> <html:select styleId="sexo"  property="sexo" value="${requestScope.UsuarioForm.sexo}" styleClass="width_100">
											  <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
											  <html:option value="F"><bean:message key="usuarioMto.lbl.Femenino"/></html:option>
											  <html:option value="M"><bean:message key="usuarioMto.lbl.Masculino"/></html:option>
											  </html:select>						
							</td>
						</tr>					
						<tr align="left">
							<td align="left"><bean:message key="usuarioMto.lbl.telCasa"/></td>
							<td align="left"><html:text property="telefonoCasa" size="10" onkeypress="return isNumberKey(event)"  maxlength="8" value=""/></td>
							<td align="left"><bean:message key="usuarioMto.lbl.telCel"/></td>
							<td align="left"><html:text property="telefonoCel" size="10" onkeypress="return isNumberKey(event)"   maxlength="8" value=""/></td>
						</tr>
						<tr>
							<td align="left"><bean:message key="usuarioMto.lbl.tipo" /><bean:message key="commons.msg.*"/></td>
							<td align="left">
								<html:select styleId="tipo"  property="tipo"  value="${requestScope.SolForm.tipo}" styleClass="width_100">
									<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
									<html:option value="D"><bean:message key="usuarioMto.lbl.usrDei"/></html:option>
									<html:option value="E"><bean:message key="usuarioMto.lbl.usrExterno"/></html:option>
									<html:option value="S"><bean:message key="usuarioMto.lbl.usrEst"/></html:option>
								</html:select>
								<html:hidden property="tipoHidden" />
							</td>
							<td></td><td></td>
						</tr>
						<tr>
							<td align="left"><bean:message key="usuarioMto.lbl.Carnet"/></td>
							<td align="left"><html:text property="carnetEstudiante" styleId="carnetEstudiante" maxlength="10" value="${requestScope.SolForm.carnetEstudiante}" size="10"/></td>	
							<td align="left"><bean:message key="usuarioMto.lbl.oyente"/></td>
							<td align="left"> <html:select styleId="oyente"  property="oyente" value="${requestScope.SolForm.oyente}" styleClass="width_100">
										  <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
										  <html:option value="S"><bean:message key="commons.sel.si"/></html:option>
										  <html:option value="N"><bean:message key="commons.sel.no"/></html:option>
										  </html:select>
							</td>	
						</tr>	
						<tr>
							<td align="left"><bean:message key="usuarioMto.lbl.usrExtension"/></td>
							<td align="left"><html:text property="ext" styleId="ext" maxlength="10" value="${requestScope.SolForm.ext}" size="10"/></td>	
							<td align="left"><bean:message key="usuarioMto.lbl.usrDei.codigo"/></td>
							<td align="left"><html:text property="codigoEmpleado" styleId="codigoEmpleado" maxlength="10" value="${requestScope.SolForm.codigoEmpleado}" size="10"/></td>	
						</tr>						
						<tr>
							<td align="left"><bean:message key="usuarioMto.lbl.Solicitado"/></td>
							<td align="left"><html:text property="solicitadoPor" styleId="solicitadoPor" maxlength="25" value="${requestScope.SolForm.solicitadoPor}" size="10"/></td>	
							<td align="left"><bean:message key="usuarioMto.lbl.comentario"/></td>
							<td align="left">			
							<html:textarea property="comentario" styleId="comentario" rows="5" cols="21">  ${requestScope.SolForm.comentario}</html:textarea></td>	
						</tr>		
						<tr>
							<td colspan="4"><bean:message key="commons.msg.obligatorio"/><br/> </td>					
						</tr>		
						<tr align="center" >
							<td colspan="4" style="text-align:center;">
							<html:button property="btnSend"  value="Mandar Solicitud" styleClass="ButtonText" onclick="send();"/>&nbsp;
							<html:button property="btnClean" styleId="btnClean"  value="Limpiar" styleClass="ButtonText" /></td>
						</tr>	
						<tr>	
							<td colspan="4">
							<logic:present scope="request" name="solMsg">
								<logic:equal scope="request" name="solMsg" value="existe"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="usuarioMto.error.existe"/></center></logic:equal>
								<logic:equal scope="request" name="solMsg" value="vacio"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.obligatorio"/></center></logic:equal>
								<logic:equal scope="request" name="solMsg" value="exito"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="solicitarUsr.msg.exito"/></center></logic:equal>	
								<logic:equal value="errorSave"    name="usuMsg" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></center></logic:equal>	
							</logic:present><br/>
							<td>
						</tr>													
				</table>
				</fieldset>
			</td> 
		</tr>
	</table>



</html:form>
</body>
</html:html>
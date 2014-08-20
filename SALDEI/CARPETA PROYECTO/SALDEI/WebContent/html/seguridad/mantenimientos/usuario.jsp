<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<html:html>
<head>
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
	
	function modificar(IdUsuario, PrimerNom, PrimerApe, NombreRestante, ApellidoRestante, fechaNac, Direccion, Email, TelefonoCasa, 
					   TelefonoCel, TelefonoTrab, IniVigencia, FinVigencia, Sexo, EstadoCivil, Tipo, CarnetEmpleado, Ext,   
					   Comentario, IdCarrera, solicitadoPor, Oyente, CicloIngreso, Dui, Nit, Isss, Nup, AnyoIngreso, CarnteEst ){   
		// Inactivando el Boton de Guardar y el TextBox de Id Usuario
		
		//document.forms[0].idUsuario.readOnly         = true;
		jQuery.NiceJForms.cambio('btnSave');
		document.forms[0].btnSave.disabled           	= true;	
		document.forms[0].btnNuevasFechasUsu.disabled 	= false;
		document.forms[0].tipo.disabled                 = true;	
		if(Tipo=='S')
			document.forms[0].btnNuevasFechasEst.disabled = false;
		else
			document.forms[0].btnNuevasFechasEst.disabled = true;
		cleanForm();
		if(IdUsuario != 'null')
			document.forms[0].idUsuario.value        = IdUsuario;																							
		if (CarnteEst	!= 'null')
			document.forms[0].carnetEstudiante.value = CarnteEst;	
		if (CarnetEmpleado	!= 'null')
			document.forms[0].codigoEmpleado.value   = CarnetEmpleado;	
		if(PrimerNom != 'null')
			document.forms[0].primerNom.value        = PrimerNom;
		if(PrimerApe != 'null')
			document.forms[0].primerApe.value        = PrimerApe;
		if(NombreRestante != 'null')
			document.forms[0].nombreRestante.value   = NombreRestante;
		if(ApellidoRestante != 'null')
			document.forms[0].apellidoRestante.value = ApellidoRestante;
		if(fechaNac != 'null')
			document.forms[0].fechaNac.value 		 = fechaNac;
		if(Direccion != 'null')
			document.forms[0].direccionPart.value    = Direccion;
		if(Email != 'null')
			document.forms[0].email.value 				 = Email;
		if(TelefonoCasa != 'null')
			document.forms[0].telefonoCasa.value 	 = TelefonoCasa;
		if(TelefonoTrab != 'null')
			document.forms[0].telefonoTrabajo.value  = TelefonoTrab;
		if(TelefonoCel != 'null')
			document.forms[0].telefonoCel.value 	 = TelefonoCel;
		if (IniVigencia != 'null')
			document.forms[0].iniVigencia.value      = IniVigencia;
		if (FinVigencia	!= 'null')
			document.forms[0].finVigencia.value      = FinVigencia;
		if (Sexo	!= 'null')
			jQuery.NiceJForms.selectValor('sexo', 1, Sexo);
		if (EstadoCivil	!= 'null')
			jQuery.NiceJForms.selectValor('estadoCivil', 0, EstadoCivil);
		if (Tipo	!= 'null'){
			jQuery.NiceJForms.selectValor('tipo', 2, Tipo);
			document.forms[0].tipoHidden.value       = Tipo;				
		}
		else
			jQuery.NiceJForms.selectValor('tipo', 2, 'Seleccione');				
		if (Ext	!= 'null')
			document.forms[0].ext.value  			 = Ext ;	
		if (Comentario	!= 'null')
			document.forms[0].comentario.value       = Comentario;	
		if (IdCarrera	!= 'null')
			jQuery.NiceJForms.selectValor('idCarrera', 4, IdCarrera);	
		else
			jQuery.NiceJForms.selectValor('idCarrera', 4, 'Seleccione');		
		if (Oyente != 'null')
			jQuery.NiceJForms.selectValor('oyente', 3, Oyente);	
		else
			jQuery.NiceJForms.selectValor('oyente', 3, 'Seleccione');		
		if (Dui != 'null')
			document.forms[0].dui.value 		     = Dui;		
		if (Nit != 'null')
			document.forms[0].nit.value 			 = Nit;	
		if (Isss != 'null')
			document.forms[0].isss.value 			 = Isss;	
		if (Nup != 'null' )
			document.forms[0].nup.value 			 = Nup;	
		if (solicitadoPor != 'null') 
			document.forms[0].solicitadoPor.value    = solicitadoPor;
		if(CicloIngreso != 'null')
			jQuery.NiceJForms.selectValor('cicloIngreso', 5, CicloIngreso);			
		else
			jQuery.NiceJForms.selectValor('cicloIngreso', 5, 'Seleccione');			
		if(AnyoIngreso != 'null')
			jQuery.NiceJForms.selectValor('anyoIngreso', 6, AnyoIngreso);		
		else
			jQuery.NiceJForms.selectValor('anyoIngreso', 6, 'Seleccione');	
	}
	
	
	function cleanForm(){
		document.forms[0].codigoEmpleado.value = '';
		document.forms[0].carnetEstudiante.value = '';
		document.forms[0].primerNom.value = '';
		document.forms[0].primerApe.value = '';
		document.forms[0].nombreRestante.value = '';
		document.forms[0].apellidoRestante.value = '';
		document.forms[0].fechaNac.value = '';
		document.forms[0].direccionPart.value = '';
		document.forms[0].email.value = '';
		document.forms[0].telefonoCasa.value = '';
		document.forms[0].telefonoTrabajo.value = '';
		document.forms[0].telefonoCel.value = '';
		document.forms[0].iniVigencia.value = '';
		document.forms[0].finVigencia.value = '';
		document.forms[0].sexo.value      	= '';	
		document.forms[0].estadoCivil.value = '';	
		document.forms[0].tipo.value = 'Seleccione';		
		document.forms[0].ext.value = '';	
		document.forms[0].comentario.value = '';	
		document.forms[0].idCarrera.value ='Seleccione';	
		document.forms[0].oyente.value ='Seleccione';	
		document.forms[0].dui.value = '';	
		document.forms[0].nit.value =  '';	
		document.forms[0].isss.value = '';	
		document.forms[0].nup.value  = '';	
		document.forms[0].solicitadoPor.value  = '';	
		document.forms[0].cicloIngreso.value  = 'Seleccione';
		document.forms[0].anyoIngreso.value  = 'Seleccione';	
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
				if(Comparar_Fecha(document.forms[0].iniVigencia.value,  document.forms[0].finVigencia.value)){
					document.forms[0].cmd.value = 'save';
					document.forms[0].submit();
				}	
				else
					alert("Las fechas de Vigencias no son validas");	
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
				document.forms[0].cmd.value = 'update';
				document.forms[0].submit();
			}
		}else{
			document.forms[0].cmd.value = 'find';
			document.forms[0].submit();
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
		if (document.forms[0].primerNom.value == '')
			return false;
		if (document.forms[0].primerApe.value == '')
			return false;
		if (document.forms[0].email.value == '')
			return false;
		if (document.forms[0].finVigencia.value == '')
			return false;			
		if (document.forms[0].tipo.value == 'Seleccione')	
			return false; 					
		if (document.forms[0].sexo.value == 'Seleccione')	
			return false; 					
		if (document.forms[0].estadoCivil.value == 'Seleccione')	
			return false; 		
		if (document.forms[0].tipo.value == 'S'){	
			if (document.forms[0].idCarrera.value == 'Seleccione')	
				return false; 			
			if (document.forms[0].oyente.value == 'Seleccione')		
				return false; 		
			if (document.forms[0].cicloIngreso.value == 'Seleccione')		
				return false;
			if (document.forms[0].anyoIngreso.value == 'Seleccione')		
				return false;	 									
		}	
		if (document.forms[0].tipo.value == 'D'){
			if (document.forms[0].codigoEmpleado.value == '')
				return false;				
		}
		if (document.forms[0].tipo.value == 'E'){
			if (document.forms[0].comentario.value == '')
				return false;				
		}			
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
	
	function nuevasFechasUsuario(auto,email,pass)
	{
		var idUsuario	=	document.forms[0].idUsuario.value;		
		link 			= 	"http://usaldei.uca.edu.sv:8080/SALDEI/Vigencia.do?cmd=inicio&idUsuario=" + idUsuario;
		//link 			= 	"http://localhost:8080/SALDEI/Vigencia.do?cmd=inicio&idUsuario=" + idUsuario;
		open(link,"ventana","toolbar=no,directories=no,menubar=no, noresize,width=600,height=300");
	}
	
	function nuevasFechasEstudiante(auto,email,pass)
	{
		var idUsuarioEstudiante	=	document.forms[0].idUsuario.value;		
		var idCarrera 			=	document.forms[0].idCarrera.value;		

		// aca se envia el en idCarrera el id de carrera + id plan estudio
		
		//link = "http://localhost:8080/SALDEI/EstCar.do?cmd=inicio&idUsuario="+idUsuarioEstudiante;
		link = "http://usaldei.uca.edu.sv:8080/SALDEI/EstCar.do?cmd=inicio&idUsuario="+idUsuarioEstudiante;
		link = link + "&idCarrera="+idCarrera;
		open(link,"ventana","toolbar=no,directories=no,menubar=no, noresize,width=600,height=300");
	}
	$(document).ready(function(){$.NiceJForms.build()});
</script>
<% request.setAttribute("estadoCivil", request.getSession().getAttribute("estCivil")); %>
<title><bean:message key="commons.lbl.titulo"/></title></head>
<body>
<html:form action="/usuario" styleClass="niceform"  >
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
								<td style="font-size:18px; color:#439FD7; font-weight:bold; vertical-align: middle;">  <a href='/SALDEI/common.do?cmd=inicio'>    <img src="${ctx}/images/login/HomePage1.png" alt="Inicio"/></a></td>
								<td style="font-size:18px; color:#439FD7; font-weight:bold; vertical-align: middle;">  <a href='/SALDEI/common.do?cmd=inicio'>    <img src="${ctx}/images/login/Shutdown2.png" alt="Salir SALDEI"/></a></td>
							</tr>
						</table>
					</td>
				</tr>				
			</table>    		    	
		    <div id="menu"> <%= menu %> </div>
		</div>
		<div id="container"><br/><br/>
			<div id="titule"> <bean:message key="usuarioMto.lbl.titulo"/> </div><br/>
			<table>
				<tr>
					<td align="center">
					<fieldset>
						<logic:present name="modulesUsuario" scope="session">
						<legend>	<%= (String) request.getSession().getAttribute("modulesUsuario") %></legend>
						</logic:present><br/>
						<table align="center">
							<tr>
								<td align="left"><bean:message key="login.lbl.usr"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><html:text property="idUsuario" maxlength="10" value="${requestScope.UsuarioForm.idUsuario}" size="20"  readonly="true"/></td>
								<td align="left"></td>
								<td align="left"></td>
							</tr>
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.primerNom"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><html:text property="primerNom" maxlength="15" value="${requestScope.UsuarioForm.primerNom}" size="20"/></td>
								<td align="left"><bean:message key="usuarioMto.lbl.nombres"/></td>
								<td align="left"><html:text property="nombreRestante" maxlength="25" value="${requestScope.UsuarioForm.nombreRestante}" size="20"/></td>												
							</tr>
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.primerApe"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><html:text property="primerApe" maxlength="15" value="${requestScope.UsuarioForm.primerApe}" size="20"/></td>
								<td align="left"><bean:message key="usuarioMto.lbl.apellidos"/></td>
								<td align="left"><html:text property="apellidoRestante" maxlength="25" value="${requestScope.UsuarioForm.apellidoRestante}"size="20"/></td>
							</tr>
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.email"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><html:textarea property="email" styleId="email" rows="5" cols="21" value="${requestScope.UsuarioForm.email}"> </html:textarea></td>
								<td align="left"><bean:message key="usuarioMto.lbl.direccion"/></td>
								<td align="left"><html:textarea property="direccionPart" styleId="direccionPart" rows="5" cols="21" value="${requestScope.UsuarioForm.direccionPart}"> </html:textarea> </td>				
							</tr>					
							
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.fechaNac" /></td>
								<td align="left"><input type="text" name="fechaNac" id="fechaNac" readonly="readonly" size="20"  value="${requestScope.UsuarioForm.fechaNac}" /><input type="button" value="....." id="btnFecha" onmousedown="verCalen('fechaNac', 'btnFecha' )" class="ButtonText"/></td>	
								<td align="left"><bean:message key="usuarioMto.lbl.telCasa"/></td>
								<td align="left"><html:text property="telefonoCasa"   onkeypress="return isNumberKey(event)"  maxlength="8" value="${requestScope.UsuarioForm.telefonoCasa}"  size="20"/></td>
							</tr>    
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.telCel" /></td>
								<td align="left"><html:text property="telefonoCel" onkeypress="return isNumberKey(event)" maxlength="8" value="${requestScope.UsuarioForm.telefonoCel}" size="20"/></td>
								<td align="left"><bean:message key="usuarioMto.lbl.telTrab"/></td>
								<td align="left"><html:text property="telefonoTrabajo" onkeypress="return isNumberKey(event)"  maxlength="8" value="${requestScope.UsuarioForm.telefonoTrabajo}" size="20"/></td>
							</tr>
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.iniVigencia"/></td>
								<td align="left"><input type="text" name="iniVigencia" id="iniVigencia" readonly="readonly" size="20" value="${requestScope.UsuarioForm.iniVigencia}"/><input type="button" value="...." id="btnIniVig" onmousedown="verCalen('iniVigencia' ,'btnIniVig')" class="ButtonText"/></td>
								<td align="left"><bean:message key="usuarioMto.lbl.finVigencia" /><bean:message key="commons.msg.*"/></td>
								<td align="left"><input type="text" name="finVigencia" id="finVigencia" readonly="readonly" size="20" value="${requestScope.UsuarioForm.finVigencia}"/><input type="button" value="...." id="btnFinVig" onmousedown="verCalen('finVigencia', 'btnFinVig')" class="ButtonText"/></td>
							</tr>	
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.dui" /></td>
								<td align="left"><html:text property="dui" styleId="dui"  maxlength="10" value="${requestScope.UsuarioForm.dui}" size="20"/></td>
								<td align="left"><bean:message key="usuarioMto.lbl.nit" /></td>
								<td align="left"><html:text property="nit" styleId="nit" maxlength="15" value="${requestScope.UsuarioForm.nit}" size="20"/></td>	
							</tr>
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.isss" /></td>
								<td align="left"><html:text property="isss"  styleId="isss" maxlength="10" value="${requestScope.UsuarioForm.isss}" size="20"/></td>
								<td align="left"><bean:message key="usuarioMto.lbl.nup" /></td>
								<td align="left"><html:text property="nup" styleId="nup" maxlength="12" value="${requestScope.UsuarioForm.nup}" size="20"/></td>	
							</tr>			
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.estadoCivil"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><html:select styleId="estadoCivil"  property="estadoCivil" value="${requestScope.UsuarioForm.estadoCivil}"  styleClass="width_180">
												 <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
												 <html:options collection="estadoCivil" property="idMulticode" labelProperty="codigo"/>
										</html:select>
								</td>
								<td align="left"><bean:message key="usuarioMto.lbl.sexo" /><bean:message key="commons.msg.*"/></td>
								<td align="left"> <html:select styleId="sexo"  property="sexo" value="${requestScope.UsuarioForm.sexo}" styleClass="width_188">
												  <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
												  <html:option value="F"><bean:message key="usuarioMto.lbl.Femenino"/></html:option>
												  <html:option value="M"><bean:message key="usuarioMto.lbl.Masculino"/></html:option>
												  </html:select>						
								</td>
							</tr>	
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.tipo" /><bean:message key="commons.msg.*"/></td>
								<td align="left">
									<html:select styleId="tipo"  property="tipo"  value="${requestScope.UsuarioForm.tipo}" styleClass="width_188">
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
								<td align="left"><bean:message key="usuarioMto.lbl.usrExtension"/></td>
								<td align="left"><html:text property="ext" styleId="ext" maxlength="10" value="${requestScope.UsuarioForm.ext}" size="12"/></td>	
								<td></td><td></td>
							</tr>
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.Carnet"/></td>
								<td align="left"><html:text property="carnetEstudiante" styleId="carnetEstudiante" maxlength="10" value="${requestScope.UsuarioForm.carnetEstudiante}" size="20"/></td>	
								<td align="left"><bean:message key="usuarioMto.lbl.usrDei.codigo"/></td>
								<td align="left"><html:text property="codigoEmpleado" styleId="codigoEmpleado" maxlength="10" value="${requestScope.UsuarioForm.codigoEmpleado}" size="20"/></td>	
							</tr>
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.Solicitado"/></td>
								<td align="left"><html:text property="solicitadoPor" styleId="solicitadoPor" maxlength="25" value="${requestScope.UsuarioForm.solicitadoPor}" size="20"/></td>	
								<td align="left"><bean:message key="usuarioMto.lbl.comentario"/></td>
								<td align="left"><html:text property="comentario" styleId="comentario" maxlength="180" value="${requestScope.UsuarioForm.comentario}" size="20"/></td>	
							</tr>
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.oyente"/></td>
								<td align="left"> <html:select styleId="oyente"  property="oyente" value="${requestScope.UsuarioForm.oyente}" styleClass="width_188">
												  <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
												  <html:option value="S"><bean:message key="commons.sel.si"/></html:option>
												  <html:option value="N"><bean:message key="commons.sel.no"/></html:option>
												  </html:select>
								</td>
								<td align="left"><bean:message key="usuarioMto.lbl.carrera" /><bean:message key="commons.msg.*"/></td>
								<td align="left">
									<html:select styleId="idCarrera"  property="idCarrera" value="${requestScope.UsuarioForm.idCarrera}" styleClass="width_188">
										<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
										<html:options collection="carrera" property="element1" labelProperty="element2"/>
									</html:select>
								</td>
							</tr>						
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.CicloIngreso"/></td>
								<td align="left"> <html:select styleId="cicloIngreso"  property="cicloIngreso" value="${requestScope.UsuarioForm.cicloIngreso}" styleClass="width_188">
												  <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
												  <html:option value="01">01</html:option>
												  <html:option value="02">02</html:option>
												  </html:select>
								</td>
								<td align="left"><bean:message key="usuarioMto.lbl.AnyoIngreso"/></td>
								<td align="left">
									<html:select styleId="anyoIngreso"  property="anyoIngreso" value="${requestScope.UsuarioForm.anyoIngreso}" styleClass="width_188">
										<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
										<html:options collection="anyoUsrIngreso" property="element1" labelProperty="element1"/>
									</html:select>
								</td>
							</tr>
							<tr align="right">					
								<td align=""right"" colspan="4"> <bean:message key="commons.msg.obligatorio"/><br/><br/></td>			
							</tr>
							<tr>
								<td align="center" colspan="4">
									<input type="button" id="btnSave"    name="btnSave"     onclick="save();"       value= "<%=(String) request. getSession().getAttribute("btnSave")   %>"/>
									<input type="button" id="btnUpdate"  name="btnUpdate"  	onclick="update();"  	value= "<%=(String) request.getSession().getAttribute("btnUpdate") %>"/>
									<input type="button" id="btnFind"    name="btnFind"     onclick="find();"       value= "<%=(String) request.getSession().getAttribute("btnFind") %>"/>
									<input type="button" id="btnClean"   name="btnClean"    onclick="clean();"     	value= "<%=(String) request.getSession().getAttribute("btnClean") %>"/>
									<input type="button" id="btnRem"     name="btnRem"      onclick="remove();" 	value= "<%=(String) request.getSession().getAttribute("btnRem") %>"/>
									<input type="button" id="btnNuevasFechas" name="btnNuevasFechasUsu" disabled="true"  onclick="nuevasFechasUsuario();" value= "Nuevas Fechas de Vigencia"/>
									<input type="button" id="btnNuevasFechas" name="btnNuevasFechasEst"  onclick="nuevasFechasEstudiante();" value= "Nueva Carrera"/>
								</td>								
							</tr>	
							<tr>
								<td colspan="4">
									<logic:present name="usuMsg" scope="request" >
										<logic:equal value="vacio"        name="usuMsg" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.obligatorio"/></center></logic:equal>
										<logic:equal value="exito"        name="usuMsg" scope="request"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></center></logic:equal>
										<logic:equal value="existe"       name="usuMsg" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="usuarioMto.error.existe"/></center></logic:equal>
										<logic:equal value="exitoDml"     name="usuMsg" scope="request"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></center></logic:equal>
									    <logic:equal value="cambios"      name="usuMsg" scope="request"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardarCambios"/></center></logic:equal>
									    <logic:equal value="errorSave"    name="usuMsg" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></center></logic:equal>
									    <logic:equal value="errorAct"     name="usuMsg" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></center></logic:equal>
									    <logic:equal value="errorCambios" name="usuMsg" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardarCambios"/></center></logic:equal>
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
			<logic:present name="MapUserA" scope="session">
				<div id="cap" ><bean:message key="usuarioMto.lbl.activos"/></div> 
				<display:table name="sessionScope.MapUserA" pagesize="10" sort="list" excludedParams="*" id="tableMapUserA" defaultsort="1">
					<display:column property="idUsuario" 		  title="Usuario"      			    style="width:10%; text-align:left;" sortable="true" />
					<display:column property="primerNom"          title="Nombre" 				    style="width:15%; text-align:left;" sortable="true"/>
					<display:column property="primerApe" 		  title="Primer Apellido"           style="width:15%; text-align:left;" sortable="true"/>
					<display:column property="email"              title="Correo Electr&oacute;nico" style="width:40%; text-align:left;" sortable="true"/>
					<display:column property="accion"             title="Acci&oacute;n" 			style="width:20%; text-align:center;"/>
				</display:table>
			</logic:present>	
		</div>	
		<div id="rightcolumn"> 				
			<logic:present name="MapUserI" scope="session" >
				<div id="cap" ><bean:message key="usuarioMto.lbl.inactivos"/></div> 
				<display:table name="sessionScope.MapUserI" pagesize="10" sort="list" excludedParams="*" id="tableMapUserI" defaultsort="1">
						<display:column property="idUsuario" 		  title="Usuario"      			    style="width:10%; text-align:left;"  sortable="true"/>
						<display:column property="primerNom"          title="Nombre" 				    style="width:15%; text-align:left;"  sortable="true"/>
						<display:column property="primerApe" 		  title="Primer Apellido"           style="width:15%; text-align:left;"  sortable="true"/>
						<display:column property="email"              title="Correo Electr&oacute;nico" style="width:40%; text-align:left;"  sortable="true"/>
						<display:column property="accion"             title="Acci&oacute;n" 			style="width:20%; text-align:center;"/>
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


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<html:html>
<script type="text/javascript" src="${ctx}/js/calendar/calendar.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar_helper.js"></script>
<script type="text/javascript">

	function verCalen(){
		Calendar.setup({
	    inputField : "fechaNac",
    	ifFormat   : "%d/%m/%Y",
	    button     : "btnFecha"
		});
	}
	$(document).ready(function(){
		$.NiceJForms.build()
	});
	 $(document).ready(function (){  
			$("#btn").click(function(){
				jQuery.NiceJForms.selectValor('estadoCivil', 0, 'Seleccione');
				jQuery.NiceJForms.selectValor('sexo', 1, 'Seleccione');
				document.forms[0].primerNom.value 	= '';
				document.forms[0].primerApe.value 	= '';
				document.forms[0].nombreRestante.value = '';
				document.forms[0].apellidoRestante.value = '';
				document.forms[0].fechaNac.value = '';
				document.forms[0].direccionPart.value = '';
				document.forms[0].email.value = '';
				document.forms[0].telefonoCasa.value = '';
				document.forms[0].telefonoCel.value = '';
				document.forms[0].telefonoTrabajo.value = '';
				document.forms[0].isss.value = '';
				document.forms[0].dui.value = '';
				document.forms[0].nup.value = '';
				document.forms[0].nit.value = '';				
			});
      });	
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
		return true;
	}
	
	
	function saveForm(){	
		if (!Validar())
			alert("Debe de Ingresar los Campos Obligatorios");
		else{	
			action = confirm("¿Desea guardar los cambios?");
			if (action) {
				document.forms[0].cmd.value = 'update';
				document.forms[0].submit();
			}
		}		
	}
	
	function isNumberKey(evt){
		var charCode = (evt.which) ? evt.which : event.keyCode         
		if (charCode > 31 && (charCode < 48 || charCode > 57))            
			return false;          
		return true;
	}	
	function soloNumeros(){
		var key=window.event.keyCode;
		if (key < 48 || key > 57){
			window.event.keyCode=0;
		}
	}
		


</script>

<head>
<title><bean:message key="commons.lbl.titulo" /></title>
</head>
<body>
<html:form action="/modDatos" styleClass="niceform">
<html:hidden property="cmd" value=""/>
	<div id="wrapper">
		<div id="header">
			<table>
				<tr>
					<td style="width: 25px; background-color: #000000; text-align: center; vertical-align: middle;">
						<img src="${ctx}/images/login/SALDEI.png"/>
					</td>
					<td style="width: 825px; text-align: center; vertical-align: middle;"> 
						<img src="${ctx}/images/uca/labos.PNG" />
					</td>
					<td style="width: 100px; background-color: #000000;">
						<table >
							<tr>
								<td style=" vertical-align: middle; text-align: center;" colspan="2"> <img src="${ctx}/images/login/User1.png" alt="<%= user %>"/></td>
							</tr>		
							<tr>
								<td style="font-size:14px; font-weight:bold; vertical-align: middle;"> Usuario: 	 </td>
								<td style="font-size:18px; color:#439FD7; font-weight:bold; vertical-align: middle;">  <%= user %></td>
							</tr>
							<tr>
								<td style="font-size:18px; color:#439FD7; font-weight:bold; vertical-align: middle;">  <a href='/SALDEI/common.do?cmd=bienvenida'>    <img src="${ctx}/images/login/HomePage1.png" alt="Inicio"/></a></td>
								<td style="font-size:18px; color:#439FD7; font-weight:bold; vertical-align: middle;">  <a href='/SALDEI/common.do?cmd=inicio'>    <img src="${ctx}/images/login/Shutdown2.png" alt="Salir SALDEI"/></a></td>
							</tr>						
						</table>
					</td>
				</tr>				
			</table>	    		    	
	    	<div id="menu"><%= menu %></div>
	    	</div>
	    	<div id="container"><br/><br/>
				<div id="titule"><bean:message key="commons.lbl.titulo.modDatos"/></div>
				<table>
					<tr>
						<td>
						<fieldset><br/>
							<table align= "center">
								<tr>
									<td align="center"><bean:message key="usuarioMto.lbl.primerNom"/><bean:message key="commons.msg.*"/></td>													
									<td align="center"><html:text property="primerNom"  maxlength="15" disabled="false" value="${requestScope.ModDatosForm.primerNom}" size="25"/></td>															
								</tr>					
								<tr>
									<td align="center"><bean:message key="usuarioMto.lbl.primerApe"/><bean:message key="commons.msg.*"/></td>							
									<td align="center"><html:text property="primerApe" size="25" value="${requestScope.ModDatosForm.primerApe}" maxlength="15"/></td>
								</tr>						
								<tr>
									<td align="center"><bean:message key="usuarioMto.lbl.nombres"/></td>							
									<td align="center"><html:text property="nombreRestante" size="25" value="${requestScope.ModDatosForm.nombreRestante}" maxlength="25"/></td>
								</tr>
								<tr>
									<td align="center"><bean:message key="usuarioMto.lbl.apellidos"/></td>							
									<td align="center"><html:text property="apellidoRestante" size="25" value="${requestScope.ModDatosForm.apellidoRestante}" maxlength="25" /></td>
								</tr>	
								<tr>
									<td align="left"><bean:message key="usuarioMto.lbl.estadoCivil"/><bean:message key="commons.msg.*"/></td>
									<td align="left"><html:select styleId="estadoCivil"  property="estadoCivil" value="${requestScope.ModDatosForm.estadoCivil}"  styleClass="width_240">
													 <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
													 <html:options collection="estadoCivil" property="idMulticode" labelProperty="codigo"/>
													 </html:select>
									</td>
								</tr>					
								<tr>
									<td align="center"><bean:message key="usuarioMto.lbl.fechaNac"/></td>							
									<td align="center"><input type="text" name="fechaNac" id="fechaNac" readonly="readonly" size="22"  value="${requestScope.ModDatosForm.fechaNac}"/><input type="button" value="..." id="btnFecha" onmousedown="verCalen()" class="ButtonText"/></td>
								<tr>
									<td align="left"><bean:message key="usuarioMto.lbl.sexo" /><bean:message key="commons.msg.*"/></td>
									<td align="left"> <html:select styleId="sexo"  property="sexo" value="${requestScope.ModDatosForm.sexo}" styleClass="width_240">
													  <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
													  <html:option value="F"><bean:message key="usuarioMto.lbl.Femenino"/></html:option>
													  <html:option value="M"><bean:message key="usuarioMto.lbl.Masculino"/></html:option>
													  </html:select>						
									</td>
								</tr>
								<tr>
									<td align="left"><bean:message key="usuarioMto.lbl.direccion"/></td>
									<td align="left"><html:textarea property="direccionPart" styleId="direccionPart" rows="5" cols="21" value="${requestScope.ModDatosForm.direccionPart}"></html:textarea> </td>				
								</tr>												
								<tr>
									<td align="left"><bean:message key="usuarioMto.lbl.email"/><bean:message key="commons.msg.*"/></td>
									<td align="left"><html:textarea property="email" styleId="email" rows="3" cols="21" value="${requestScope.ModDatosForm.email}"> </html:textarea></td>
								
								</tr>								
								<tr>
									<td align="center"><bean:message key="usuarioMto.lbl.telCasa"/></td>							
									<td align="center"><html:text property="telefonoCasa" size="25" value="${requestScope.ModDatosForm.telefonoCasa}" onkeypress="return isNumberKey(event)" maxlength="8"/></td>
								</tr>
								<tr>
									<td align="center"><bean:message key="usuarioMto.lbl.telCel"/></td>
									<td align="center"><html:text property="telefonoCel" size="25" value="${requestScope.ModDatosForm.telefonoCel}" onkeypress="return isNumberKey(event)"  maxlength="8"/></td>
								</tr>
								<tr>
									<td align="center"><bean:message key="usuarioMto.lbl.telTrab" /></td>
									<td align="center"><html:text property="telefonoTrabajo" size="25"  value="${requestScope.ModDatosForm.telefonoTrabajo}" onkeypress="return isNumberKey(event)" maxlength="8"/></td>														
								</tr>
								<tr>
									<td align="left"><bean:message key="usuarioMto.lbl.dui" /></td>
									<td align="left"><html:text property="dui" styleId="dui"  maxlength="10" value="${requestScope.ModDatosForm.dui}" size="20"/></td>
								</tr>	
								<tr>
									<td align="left"><bean:message key="usuarioMto.lbl.nit" /></td>
									<td align="left"><html:text property="nit" styleId="nit" maxlength="15" value="${requestScope.ModDatosForm.nit}" size="20"/></td>	
								</tr>
								<tr>
									<td align="left"><bean:message key="usuarioMto.lbl.isss" /></td>
									<td align="left"><html:text property="isss"  styleId="isss" maxlength="10" value="${requestScope.ModDatosForm.isss}" size="20"/></td>
								</tr>
								<tr>
									<td align="left"><bean:message key="usuarioMto.lbl.nup" /></td>
									<td align="left"><html:text property="nup" styleId="nup" maxlength="12" value="${requestScope.ModDatosForm.nup}" size="20"/></td>	
								</tr>									
								<tr> <td> <bean:message key="commons.msg.obligatorio"/><br/></td> </tr>
								<tr>
									<td align="center" colspan="2" style="text-align:center ">
										<html:button property="btnSave" onclick="saveForm();"  value="Guardar cambios" styleClass="ButtonText" />
										<html:button property="btn" styleId="btn"    value="Limpiar" styleClass="ButtonText"/>
									</td>
								</tr>
								<tr> 
									<td align="center" colspan="2">
										<logic:present scope="request"         name="modDatos">
											<logic:equal value="vacio"         name="modDatos"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/></center></logic:equal>
											<logic:equal value="exito"         name="modDatos"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></center></logic:equal>
											<logic:equal value="errorUpdate"   name="modDatos"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></center></logic:equal>
											<logic:equal value="emailInvalido" name="modDatos"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.correo"/></center></logic:equal>
										</logic:present>	
									</td>
								</tr>
							</table>
						</fieldset>
						</td>
					</tr>
					</table>	
								
			</div>
	    	<div id="footer">
				<img src="${ctx}/images/login/foot.PNG"/><br/>
			Version 1.0
			</div>
	    </div>
</html:form>
</body>
</html:html>
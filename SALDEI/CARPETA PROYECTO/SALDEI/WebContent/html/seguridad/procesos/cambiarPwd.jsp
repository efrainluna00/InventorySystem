<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/commons.jsp" %>
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<html:html>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
<script type="text/javascript">
	function cleanForm(){
		document.forms[0].pwdActual.value  = '';
		document.forms[0].pwdNew.value     = '';
		document.forms[0].pwdConfirm.value = '';
	}		
	
	function saveForm(){
		document.forms[0].cmd.value = 'cambiarPwd';
		document.forms[0].submit();
	}
	$(document).ready(function(){$.NiceJForms.build()});
</script>
</head>
<body>
<html:form action="/cambiarPwd" styleClass="niceform">
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
			<div id="titule"> <bean:message key="cambiarPwd.lbl.titulo"/> </div><br/>
			<table align="center">
				<tr>
					<td align="center">
					<fieldset><br/>
						<table align="center">					
							<tr>
								<td align="left"><bean:message key="cambiarPwd.lbl.pwdActual"/><bean:message key="commons.msg.*"/></td>							
								<html:hidden property="pass" value=""/>
								<td align="left"><html:password property="pwdActual" size="15" maxlength="15" /></td>
							</tr>						
							<tr>
								<td align="left"><bean:message key="cambiarPwd.lbl.pwdNueva"/><bean:message key="commons.msg.*"/></td>							
								<html:hidden property="primernombre" value=""/>
								<td align="left"><html:password property="pwdNew" size="15" maxlength="15" /></td>
							</tr>
							<tr>
								<td align="left"><bean:message key="cambiarPwd.lbl.pwdReNueva" /><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:password property="pwdConfirm" size="15" maxlength="15"/></td>
							</tr>	
							<tr>
								<td colspan="2">	<bean:message key="commons.msg.obligatorio"/><br/>	</td>				
							</tr>
							<tr>
					    		<td align="center" colspan="2" style="text-align: center;">
									<html:button property="btnSave"  value="Guardar Cambios" onclick="saveForm();" styleClass="ButtonText"/>&nbsp;
									<html:button property="btnClean" value="limpiar" onclick="cleanForm();" styleClass="ButtonText" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<logic:present scope="request" name="cmbPwd">
										<logic:equal value="vacio" 	   name="cmbPwd"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.vacio"/></center></logic:equal>
										<logic:equal value="exito"     name="cmbPwd"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></center></logic:equal>
										<logic:equal value="newPswDif" name="cmbPwd"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="cambiarPwd.error.pwdDistintos"/></center></logic:equal>
										<logic:equal value="error"     name="cmbPwd"><bean:message key="commons.error.guardar"/></logic:equal>
										<logic:equal value="oldPswDif" name="cmbPwd"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="cambiarPwd.error.pwdInvalido"/></center></logic:equal>
									</logic:present>
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
</body>
</html:form>
</html:html>	
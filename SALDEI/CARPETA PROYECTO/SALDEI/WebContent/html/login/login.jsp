<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
 <style type="text/css" media="all">
      @import url("${ctx}/css/login.css");
  </style>
<% session.removeAttribute("Menu"); %>

<html:html>
<head>
<script type="text/javascript" src="${ctx}/js/jquery/dimensions.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/dimmer.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/interface.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/showBox.js"></script>

<script type="text/javascript">
	function cleanForm(){
		document.forms[0].idUsuario.value = '';
		document.forms[0].pswUsuario.value 	= '';
	}
	function Recordar(){
	 	var usr = document.forms[0].idUsuario.value;
	 	if( usr != ''){
			document.forms[0].cmd.value = 'sendEmailPwd';
			document.forms[0].submit();	
		}
		else 
			alert("Debe ingresar Usuario ");	
	}	
	function recordarPwd(){
        	document.forms[0].cmd.value = 'recordar';
			document.forms[0].submit();	
	}
	function Validar(){
		var usr = document.forms[0].idUsuario.value;
		var psw = document.forms[0].pswUsuario.value;
		if (usr != '' && psw != ''){
			document.forms[0].cmd.value = 'login';
			document.forms[0].submit();		
		}
		else{
			alert("Debe de ingresar Usuario y Contraseña");
		}
	} 
	$(document).ready(function() {
	  var $box = $('#box')
	    .wrap('<div id="box-outer"></div>');
	  $('#blind').click(function() {
	    $box.blindToggle('slow');  
	  });    
	});
</script>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<body  style="background:#000000; text-align: center;" >
	<div id="wrapper" >
		<div id="header"><br>
	    	Sistema Administrador de Laboratorios DEI 
	    </div>
	    
	    <div id="leftcolumn">
			<img src="${ctx}/images/login/Labos.png"/>
		</div>

		<div id="rightcolumn">
		
		<br><br><br><br>
		<html:form action="/login" >
		<input type="hidden" name="cmd"/>
		<fieldset>
		<br>
		<table align="center" >
			<tr align="center">
				<td align="rigth"  style="width:10%"> </td>
				<td align="right" style="color:#ffffff; width:35%"><b><bean:message key="login.lbl.usr"/></b></td>
				<td align="left" style="width:55%"></td>
			</tr> 
			<tr align="center">
				<td align="rigth"  style="width:10%"> </td>
				<td align="right" style="color:#ffffff; width:35%"></td>
				<td align="left" style="width:55%"><html:text property="idUsuario"   maxlength="10" value="" size="20"/></td>
			</tr> 
			
			<tr align="center">
				<td align="rigth"  style="width:10%"> </td>
				<td align="right" style="color:#ffffff; width:35%"><b><bean:message key="login.lbl.pwd"/></b></td>
				<td align="left" style="width:55%"></td>
			</tr>
			<tr align="center">
				<td align="rigth"  style="width:10%"> </td>
				<td align="right" style="color:#ffffff; width:35%"></td>
				<td align="left" style="width:55%"><html:password property="pswUsuario"  maxlength="15" value="" size="20"/></td>
			</tr> 
			
			<tr align="center">
				<td align=right" colspan="3" style="width:100%">
				<html:button  property= "btnLogin" value="Ingresar" onclick="Validar();"   styleClass="ButtonText"/>&nbsp;
				<html:button  property= "btnClean" value="Limpiar"  onclick="cleanForm();" styleClass="ButtonText" />										
				</td>
			</tr>			
		</table>
		
		<logic:present   scope="request" name="loginMsg">
	<logic:equal scope="request" name="loginMsg" value="vacio"   ><bean:message key="commons.error.vacio"/> </logic:equal>
	<logic:equal scope="request" name="loginMsg" value="invalido"><bean:message key="login.error.invalido"/></logic:equal>
	<logic:equal scope="request" name="loginMsg" value="inactivo"><bean:message key="login.error.inactivo"/></logic:equal>
	
	<logic:equal scope="request" name="loginMsg" value="vacioPwd"   ><font color='#EE1122'>Debe ingresar el usuario</font></logic:equal>
	<logic:equal scope="request" name="loginMsg" value="noCorreoPwd"><font color='#EE1122'>No se pudo enviar el correo, Usuario Incorrecto</font></logic:equal>
	<logic:equal scope="request" name="loginMsg" value="exitoPwd"><font color='#EE1122'><b>Se ha enviado un correo con su contraseña</b></font></logic:equal>
	<logic:equal scope="request" name="loginMsg" value="errorPwd"><bean:message key="commons.error.general"/></logic:equal>
</logic:present><br><br>
</fieldset>

		<table align="center">
			<tr align="center"><td align="center" >
				<a href='/SALDEI/solUsr.do?cmd=inicio' id='ak_sign_in' onclick="$.showBox(this.href,'SALDEI- Solicitar Usuario ', 700, 450);return false;">
			  		<img src="${ctx}/images/login/Add_User.png"/></a></td>
			  	<td align="center" > &nbsp;&nbsp; </td>	
				<td align="center" ><a href="javascript:recordarPwd()" >
			 	<img src="${ctx}/images/login/Anti-Spam.png"/></a></td>
			</tr>
			<tr align="center">
			<td align="center" style="font-size:12px; color:#FFFFFF; ">Solicitar Usuario </td>
		  	<td align="center" > &nbsp;&nbsp; </td>	
			<td align="center" style="font-size:12px; color:#FFFFFF; ">Enviar Contraseña </td>
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
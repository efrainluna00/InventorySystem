<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/commons.jsp" %>
<%@ include file="/html/commons/library.jsp"%>
<%@ include file="/html/commons/css.jsp"%>
<%@ include file="/html/commons/js.jsp"%>

<html:html>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
<script type="text/javascript" src="${ctx}/js/calendar/calendar.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="${ctx}/js/calendar/calendar_helper.js"></script>
<script type="text/javascript">

function verCalen1(){
		Calendar.setup({
	    inputField : "fechainicial",
    	ifFormat   : "%d/%m/%Y",
	    button     : "btnFecha1"
		});
	}
	function verCalen2(){
		Calendar.setup({
	    inputField : "fechafinal",
    	ifFormat   : "%d/%m/%Y",
	    button     : "btnFecha2"
		});
	}

function generarReporte(){		
	if(document.forms[0].fechainicial.value=='' && document.forms[0].fechafinal.value!=''){			
		alert('Debe ingresar ambas fechas o no ingresar fechas');
	}else
	   if(document.forms[0].fechainicial.value!='' && document.forms[0].fechafinal.value==''){			
		    alert('Debe ingresar ambas fechas o no ingresar fechas');
		}
		else{
			document.forms[0].cmd.value = 'rptSolicitud';
			document.forms[0].submit();
		}
	
}	
	$(document).ready(function(){$.NiceJForms.build()});
</script>
</head>
<body>
<html:form action="/rptSolicitudesUsuario" styleClass="niceform">
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
			<div id="titule"> Reporte de Solicitudes de Usuario</div><br/>
			<table align="center">
				<tr>
					<td align="center">
					<fieldset> 
						<table align="center">
							<tr>
								<td align="left"><b>Escoger formato:</b></td>
							</tr>
							<tr>						
								<td colspan="3" align="left">
								    <input type = "radio" name="formato" id="opcion1" value="1" checked="true"/><label for="opcion1">PDF</label><br/>
									<input type = "radio" name="formato" id="opcion2" value="2"/><label for="opcion2">EXCEL</label><br/>
								</td>						
							</tr>						
							<tr>
								<td align="left"><b>Buscar:</b></td>
							</tr>
							<tr>	
								<td align="left"><b>Fecha Inicial:</b></td>
								<td align="left"><input type="text" name="fechainicial" id="fechainicial" readonly="readonly" size="10"  /><input type="button" value="....." id="btnFecha1" onmousedown="verCalen1()" class="ButtonText"/></td>
							</tr>	
							<tr>						
								<td align="left"><b>Fecha Final:</b></td>
								<td align="left"><input type="text" name="fechafinal" id="fechafinal" readonly="readonly" size="10"  /><input type="button" value="....." id="btnFecha2" onmousedown="verCalen2()" class="ButtonText"/></td>
							</tr>
							<tr>	
		
							</tr>
							<tr>
								<td align="center" colspan="3" style="text-align: center;">
									<input type="button" id="btnRpt"    name="btnRpt"    onclick="generarReporte();"   value= "Generar Reporte"/>					
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
</body>
</html:form>
</html:html>	
				
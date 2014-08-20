<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/commons.jsp" %>
<%@ include file="/html/commons/library.jsp"%>
<%@ include file="/html/commons/css.jsp"%>
<%@ include file="/html/commons/js.jsp"%>
<%@ page import="com.saldei.web.services.registro.RptMateriaCatedraticoService" %>
<html:html>
<head>
<title><bean:message key="commons.lbl.titulo"/></title>
<script type="text/javascript">
function Validar(){
   if( document.forms[0].formato[0].checked == false && document.forms[0].formato[1].checked == false && document.forms[0].formato[2].checked == false)
       	return false;       	
   if( document.forms[0].grupo[0].checked == false && document.forms[0].grupo[1].checked == false)
       	return false;
   return true;
}
function generarReporte(){
	if(document.forms[0].cicloini.value=='Seleccione' || document.forms[0].ciclofin.value=='Seleccione'){			
		alert('Debe seleccionar el rango de ciclos');		
	}
	else{		
		if(document.forms[0].cicloini.value.substring(2,6) > document.forms[0].ciclofin.value.substring(2,6))
			alert('Rango de ciclos elegido incorrectamente')
		else if(document.forms[0].cicloini.value.substring(0,2) > document.forms[0].ciclofin.value.substring(0,2))
			alert('Rango de ciclos elegido incorrectamente')
			else{
				document.forms[0].cmd.value = 'rptMateriaCicloHistorico';
				document.forms[0].submit();
			}
	}
}
function Select(){
		document.forms[0].cmd.value = 'verMaterias';
		document.forms[0].submit();				
	}
$(document).ready(function(){$.NiceJForms.build()});


</script>

<%
	RptMateriaCatedraticoService services = new RptMateriaCatedraticoService();
	request.setAttribute("cic",services.getCiclo());
%>

</head>
<body>
<html:form action="/rptMateriaCicloHistorico" styleClass="niceform">
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
			<div id="titule">Reporte de Materias por Ciclo - Hist&oacute;rico</div><br/>
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
								    <td align='left'>Ciclo Inicial:</td>
									<td align="left">
										<html:select styleId="cicloini" property="cicloini" styleClass="width_136">	
											<html:option value="Seleccione">Seleccione</html:option>
											<html:options collection="cic" labelName="idCiclo" labelProperty="idCiclo" name="selectCiclo" property="idCiclo"/>
										</html:select>
									</td>			
								</tr>	
								<tr>	
								    <td align='left'>Ciclo Final</td>
									<td align="left">
										<html:select styleId="ciclofin" property="ciclofin" styleClass="width_136">	
											<html:option value="Seleccione">Seleccione</html:option>
											<html:options collection="cic" labelName="idCiclo" labelProperty="idCiclo" name="selectCiclo" property="idCiclo"/>
										</html:select>
									</td>			
								</tr>				
								<tr>
									<td align="center" style="text-align: center;" colspan="3">
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
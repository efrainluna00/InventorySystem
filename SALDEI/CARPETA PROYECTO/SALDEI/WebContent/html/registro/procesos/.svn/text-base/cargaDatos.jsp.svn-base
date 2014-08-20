<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp" %>
<%@ include file="/html/commons/css.jsp" %>
<%@ include file="/html/commons/js.jsp" %>
<%@ include file="/html/commons/commons.jsp" %>
<html:html>
<head>
<script type="text/javascript">
	$(document).ready(function(){$.NiceJForms.build()});
	
	function verificarArchivo(){
		if(document.forms[0].ciclo.value == 'Seleccione' || document.forms[0].carrera.value == 'Seleccione')
			alert('Debe ingresar todos los campos obligatorios');
		else{
			document.forms[0].cmd.value = 'verificacionArchivo';
			document.forms[0].submit();
		}
	}
</script>
<title><bean:message key="commons.lbl.titulo"/></title>
</head>
<body>
<html:form action="/cdatos" method="post" enctype="multipart/form-data"  styleClass="niceform">
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
			<div id="titule"> <bean:message key="cargaDatos.legend"/> </div><br/>
			<table>
				<tr>
					<td align="center">
					<fieldset><br/><br/>
						<table align="center">
							<tr>
								<td align="left"><bean:message key="cargaDatos.lbl.ruta.archivo"/><bean:message key="commons.msg.*"/></td>
								<td align="center"><html:file property="archivo"/></td>
							</tr>
							<tr>
								<td align="left"><bean:message key="commons.lbl.ciclo"/><bean:message key="commons.msg.*"/></td>
								<td align="left"> <html:select property="ciclo"  styleId="ciclo" styleClass="width_100">
										<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
										<html:options collection="ciclos" labelName="idCiclo" labelProperty="idCiclo" name="selectCiclo" property="idCiclo"/>
									</html:select></td>
							</tr>	
							<tr>
								<td align="left"><bean:message key="usuarioMto.lbl.carrera"/><bean:message key="commons.msg.*"/></td>
								<td align="left"> <html:select property="carrera"  styleId="carrera" styleClass="width_200">
										<html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
										<html:options collection="carreras" labelName="nomCarrera" labelProperty="nomCarrera" name="selectCarrera" property="id.idCarrera"/>
									</html:select></td>
							</tr>	
							<tr>
								<td align="left"> <bean:message key="commons.msg.obligatorio"/><br/> </td>
							</tr>		
							<tr>
								<td colspan="2" align="center" style="text-align: center;">
									<html:button styleClass="ButtonText" onclick="verificarArchivo();" property="btnVerArch">
										<bean:message key="cargaDatos.btn.parser.archivo"/>
									</html:button>
									<html:submit styleClass="ButtonText" onclick="cmd.value='cargarArchivo'">
										<bean:message key="cargaDatos.btn.carga.masiva"/>
									</html:submit>
									<html:submit styleClass="ButtonText" onclick="cmd.value='historial'">
										Historial de Carga
									</html:submit>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center" style="text-align: center;">
									<logic:present name="listBuenos" scope="session">
										<bean:message key="cargadatos.lbl.registros.correctos"/> <%= (String) request.getSession().getAttribute("listBuenos") %> 
									</logic:present> <br/>
									<logic:present name="listMalos" scope="session">
										<bean:message key="cargadatos.lbl.registros.incorrectos"/> <%= (String) request.getSession().getAttribute("listMalos") %>
									</logic:present>									
								</td>
							</tr>
							<logic:present name="cdfMsg" scope="request">
								<logic:equal value="exito" name="cdfMsg"><font color='#4682b4'><b>Se ha hecho la carga exitosamente</b></font></logic:equal>
								<logic:equal value="error" name="cdfMsg"><font color='#EE1122'>No se pudo realizar la carga, por favor intentelo nuevamente</font></logic:equal>
								<logic:equal value="valid" name="cdfMsg"><font color='#EE1122'>Los parametros no coinciden con los del archivo de carga</font></logic:equal>
							</logic:present>
						</table>
					</fieldset>
					</td>
				</tr>
			</table>
		</div>
		
			<logic:present name="listHistorial" scope="session">
				<display:table name="sessionScope.listHistorial" pagesize="10" id="dispalyListHistorialCarga"  sort="list" defaultsort="2">					
					<display:caption class="cap" id="cap"><b>Historial de Carga de Datos Realizadas</b></display:caption>
					<display:column property="idUsuario" title="Usuario que la Realizo "  sortable="true" />
					<display:column property="fechaAccion"    title="Fecha" 	    sortable="true" format="{0,date,dd-MM-yyyy}" />
					<display:column property="nomTabla"  title="Ciclo" 	   sortable="true" />
					<display:column property="llavePrimaria"    title="Nombre Carrera"    sortable="true" />				
				</display:table>
			</logic:present>
		
		<div id="footer">
			<img src="${ctx}/images/login/foot.PNG"/><br/>
			Version 1.0
		</div>
		</div>
		
</body>
</html:form>
</html:html>		
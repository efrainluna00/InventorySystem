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
	function modificar(idReqMat, req, tipo, idUsuario){
		jQuery.NiceJForms.cambio('btnSave');
		document.forms[0].btnSave.disabled      	= true;	
		if(idReqMat != 'null')
			document.forms[0].idReqMat.value        = idReqMat;
		if(req != 'null')
			document.forms[0].requerimiento.value   = req;
		if(tipo != 'null')
			document.forms[0].idTipo.value     		= tipo;	
		if(idUsuario != 'null')
			document.forms[0].idUsuario.value     		= idUsuario;
	}	
	function Validar(){
		if(document.forms[0].idCiclo.value   	 == '0')
			return false;
		if(document.forms[0].idMateria.value 	 == '0')	
			return false;
		if(document.forms[0].idTipo.value 		 == 'Seleccione')	
			return false;
		if(document.forms[0].requerimiento.value == '')	
			return false;
		else 
			return true;	
	}
	function Ciclo(){	
		if (document.forms[0].idCiclo.value != 'Seleccione'){	
			document.forms[0].cmd.value = 'findMateria';
			document.forms[0].submit();			
		}
	}
	function clean(){	
		document.forms[0].requerimiento.value = '';
		document.forms[0].cmd.value = 'inicio';
		document.forms[0].submit();			
	}
	function save(){		
		if(!Validar()){
			alert ("Debe de Ingresar los Datos validos");
		}
		else{
			document.forms[0].cmd.value = 'save';
			document.forms[0].submit();
		}	
	}	
	function update(){
		if(!Validar()){
			alert ("Debe de Ingresar los Datos validos");
		}
		else{
			document.forms[0].cmd.value = 'update';
			document.forms[0].submit();			
		}	
	}	
	function findReq(){	
		if(document.forms[0].idMateria.value != '0'){				
			document.forms[0].cmd.value = 'findRequerimiento';
			document.forms[0].submit();
		}else{
			alert('Debe ingresar los parametros de busqueda');
			document.forms[0].idTipo.value = 'Seleccione'
			document.forms[0].requerimiento.value = '';
		}
	}
	$(document).ready(function(){$.NiceJForms.build()});	
</script>
<%	request.setAttribute("ciclos",   request.getSession().getAttribute("ciclos"));
	request.setAttribute("materias", request.getSession().getAttribute("materias")); %>
<head><title><bean:message key="commons.lbl.titulo"/></title></head>
<body >
<html:form action="/req"  styleClass="niceform"  >
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
			<div id="titule"> <bean:message key="reque.lbl.titulo"/> </div><br/>
			<table>
				<tr>
					<td align="center">
					<fieldset>
						<table align="center">
							<tr>
								<td align="left"><bean:message key="ciclo.lbl.idCiclo"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><html:select styleId="idCiclo" property="idCiclo" value="${requestScope.RequerimientoForm.idCiclo}"  styleClass="width_90">
												 <html:option value="0"><bean:message key="commons.sel.seleccione"/></html:option>
												 <html:options collection="ciclos" property="element1" labelProperty="element1"/>
												 </html:select><input type="button" id="btnLook"    name="btnLook"     onclick="Ciclo();"       value= "Mostrar"/> </td>
								<td align="left"><bean:message key="reque.lbl.materia"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><html:select styleId="idMateria" property="idMateria" value="${requestScope.RequerimientoForm.idMateria}" styleClass="width_180">
											 <html:option value="0"><bean:message key="commons.sel.seleccione"/></html:option>
											 <html:options collection="materias" property="element1" labelProperty="element2"/>
											 </html:select> </td>
							</tr>			
							<tr>
								<td align="left"><bean:message key="reque.lbl.catedratico"/></td>
								<td align="left"><html:text property="idUsuario" styleId="idUsuario" maxlength="6" readonly="true" value="${requestScope.RequerimientoForm.idUsuario}" size="15" /></td>
								<td align="left"><bean:message key="reque.lbl.tipoHwSw"/><bean:message key="commons.msg.*"/></td>
								<td align="left"><html:select styleId="idTipo" property="idTipo" value="${requestScope.RequerimientoForm.idTipo}" styleClass="width_180">
												 <html:option value="Seleccione"><bean:message key="commons.sel.seleccione"/></html:option>
												<html:options collection="listReq" property="idMulticode" labelProperty="codigo"/>
												 </html:select></td>
							</tr>
							<tr>
								<td align="left"><bean:message key="reque.lbl.requerimiento"/><bean:message key="commons.msg.*"/></td>
								<td align="left" colspan="3"> <textarea rows="5" cols="50" id="requerimiento" name="requerimiento">${requestScope.RequerimientoForm.requerimiento}</textarea>
												 <html:hidden property="idReqMat" styleId="idReqMat" />	</td>
							</tr>
							<tr align="right">					
								<td align=""right"" colspan="4"><bean:message key="commons.msg.obligatorio"/><br/><br/>	</td>			
							</tr>
							<tr>
								<td align="left"></td>
								<td align="center" colspan="2" style="text-align:center; ">
									<input type="button" id="btnLook"    name="btnLook"     onclick="findReq();"       value= "Mostrar"/>
									<input type="submit" id="btnSave"    name="btnSave"     onclick="cmd.value='save'"       value= "<%=(String) request. getSession().getAttribute("btnSave")   %>"/>
									<input type="button" id="btnUpdate"  name="btnUpdate"  	onclick="update();"  	value= "<%=(String) request.getSession().getAttribute("btnUpdate") %>"/>
									<input type="button" id="btnClean"   name="btnClean"    onclick="clean();"     	value= "<%=(String) request.getSession().getAttribute("btnClean") %>"/>	</td>
								<td align="left"></td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<logic:present  scope="request" 	  name="ReqMsg">
										<logic:equal value="vacio"        name="ReqMsg" scope="request"><center><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.obligatorio"/></center></logic:equal>
										<logic:equal value="exito"        name="ReqMsg" scope="request"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.guardar"/></center></logic:equal>
										<logic:equal value="exitoDml"     name="ReqMsg" scope="request"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.actualizacion"/></center></logic:equal>
										<logic:equal value="exitoDel"     name="ReqMsg" scope="request"><center><img src="${ctx}/images/icons/round-valid.gif"/>&nbsp;<bean:message key="commons.msg.delete.lbl"/></center></logic:equal>
										<logic:equal value="errorSave"    name="ReqMsg" scope="request"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.guardar"/></logic:equal>
										<logic:equal value="errorUpdate"  name="ReqMsg" scope="request"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.actualizar"/></logic:equal>
										<logic:equal value="errorDel"     name="ReqMsg" scope="request"><img src="${ctx}/images/icons/error.gif"/>&nbsp;<bean:message key="commons.error.delete"/></logic:equal>
									</logic:present></td>	
							</tr>
						</table>
					</fieldset>
					</td>
				</tr>
			</table>
		</div>
		<div id="column">
			<logic:present name="mapReq" scope="session">
				<display:table name="sessionScope.mapReq" pagesize="10" sort="list" excludedParams="*" id="tablemapReq" defaultsort="1">
					<display:caption><bean:message key="reque.lbl.caption"/></display:caption> 				
					<display:column property="idTipo"     		title="Tipo"     		style="width:20%; text-align:center;"  sortable="true"/>
					<display:column property="requerimiento"    title="Requerimiento"   style="width:30%; text-align:left;  "  sortable="true"/>
					<display:column property="estReqMat"   		title="Estado"          style="width:10%; text-align:center;"  sortable="true"/>
					<display:column property="accion"     		title="Accion" 			style="width:20%; text-align:center; "  />
					<display:column property="eliminar"     	title="Eliminar" 		style="width:20%; text-align:center;" />
				</display:table>
			</logic:present>
		</div>
		<div id="footer">
			<img src="${ctx}/images/login/foot.PNG"/><br/>
			Version 1.0
		</div>
	</div>	
</body>
</html:form>
</html:html>
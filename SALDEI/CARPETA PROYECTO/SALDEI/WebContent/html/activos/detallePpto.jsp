<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>


<tiles:insert name="plantillaStandar" definition="plantillaStandar">
	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
    
    	function verCalen(field, btn){
				Calendar.setup({
			    inputField : field,
		    	daFormat   : "%yyyy-%m-%d",
			    button     : btn
				});
			}
    
    	function cerrarVentana(){
    		window.close();
    	}
    	
        function enviar(accion){
	      		var formulario= document.getElementById('formulario');
            var href = formulario.action;                        		
                    
            if(href.indexOf('?') >0 ){
              href= href.substring(0,href.indexOf('?'));
            }
            href+='?accion='+accion+'&';				
            formulario.action= href;
                          
            formulario.submit();					
	      	}
	      	
			function tableClick(codCuenta, descripcion,monto,saldoActual){
			  	$(".error").hide();	
				document.getElementById('codCuenta').value = codCuenta;
				document.getElementById('descripcion').value = descripcion;
				document.getElementById('monto').value = monto;	
				$("#montoTemp").val(monto);			
				$("#saldoActual").val(saldoActual);
				$("#btncuenta").hide();
				habilitar();
			}
			function desabilitar(){
				$('#btncuenta').hide();
				$("#descripcion").attr('class','caja_textodisable');
				$("#codCuenta").attr('class','caja_textodisable');
				$("#monto").attr('class','caja_textodisable');
				$("#descripcion").attr('size','62');
				$("#codCuenta").attr('size','12');
				$("#back").show();
				$("#insert").hide();
				$("#cancel").hide();
			}
			
			function habilitar(){
			if (document.getElementById('estadoPre').value != "I"){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));				
				$("#descripcion").attr('class','caja_textodisable');
				$("#codCuenta").attr('class','caja_textodisable');
				$("#monto").attr('class','caja_textodisable');
				$("#monto").attr('readonly','true');
				$("#descripcion").attr('size','62');
				$("#codCuenta").attr('size','12');
				
              }
			}
			var flag = "in";
	      	function pulsateMessaje(){	                        
	        	if(flag == "in"){          
	          		flag = "out";
	          		$('#mensaje').DropInRight(1000);
	          		setTimeout('pulsateMessaje()',5000);
	        	}
	        	else 
	        		if(flag == "out"){          
	          			flag = "in";
	          			$('#mensaje').DropOutRight(1000);	          			
	        	}
	      	}
	      	
	      	
	      	function pulsateMessajeE(){	                        
	        	if(flag == "in"){          
	          		flag = "out";
	          		$('#mensajeError').DropInRight(1000);
	          		setTimeout('pulsateMessajeE()',5000);
	        	}
	        	else 
	        		if(flag == "out"){          
	          			flag = "in";
	          			$('#mensajeError').DropOutRight(1000);	          			
	        	}
	      	}
	      	
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="detppto.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="detppto.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/detallePptoAction.do" styleId="formDetPpto">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="detppto.fecha_inicial" />
							:
						</th>
						<td>
							<bean:write name="detallePptoForm" property="fecha_ini" />
							<html:hidden property="fecha_ini" styleId="fecha_ini" />
						</td>
						<html:hidden property="codPresupuesto" styleId="codPresupuesto" />
						<html:hidden property="estadoPre" styleId="estadoPre" />


						<th style="text-align: left;">
							<bean:message key="detppto.fecha_final" />
							:
						</th>
						<td>
							<bean:write name="detallePptoForm" property="fecha_fin"
								scope="request" />
							<html:hidden property="fecha_fin" styleId="fecha_fin" />
						</td>

					</tr>
				</table>
				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="detppto.cuentaDesc" />
						</th>
						<td colspan="3">
							<html:text property="codCuenta" styleId="codCuenta" size="15"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<html:text property="descripcion" styleId="descripcion" size="70"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" id="btncuenta"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=cuentas&codPresupuesto='+document.getElementById('codPresupuesto').value);"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="detpresupuesto.cuenta.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="detppto.monto" />
						</th>
						<td>
							<html:hidden property="montoTemp" styleId="montoTemp" />
							<html:text property="monto" styleId="monto" size="30"
								styleClass="caja_texto_obligatorio"
								onkeydown="return soloDinero(this, event);" maxlength="21"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="detpresupuesto.monto.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="detppto.saldoActual" />
						</th>
						<td>
							<html:text property="saldoActual" styleId="saldoActual" size="30"
								styleClass="caja_textodisable" readonly="true"
								onkeydown="return soloDinero(this, event);"></html:text>
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detpresupuesto.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>

				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera"
					property="detpresupuesto.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>

				<div id="accionInsert" class="accion">
					<html:submit property="accion" styleId="insert">
						<bean:message key="opc.insert" />
					</html:submit>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>


				</div>
				<div id="accionUpdate" class="accion"
					style="display: none; width: 100%;">

					<html:submit property="accion" styleId="delete"
						onclick="
					javascript:
					if(!confirm('¿Esta seguro que desea eliminar el registro seleccionado?'))
						return false;
					">
						<bean:message key="opc.delete" />
					</html:submit>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>

				</div>
				<br />
				<hr width="85%">
				<logic:equal parameter="accion" value='Actualizar'>
					<script type="text/javascript" language="javascript">
					habilitar();
				</script>
				</logic:equal>
				<logic:equal name="detallePptoForm" property="estadoPre" value="I">
					<script type="text/javascript" language="javascript">
					 desabilitar();
				  </script>
				</logic:equal>


			</center>

			<ec:table items="listDetPresupuesto" var="mapf"
				action="${pageContext.request.contextPath}/detallePptoAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Cuentas Asociadas' view="compact" width="80%"
				tableId="detpresupuesto" autoIncludeParameters="true"
				scope="request" form="formDetPpto">
				<ec:row highlightRow="true"
					onclick="tableClick('${pageScope.mapf.codCuenta}','${pageScope.mapf.descripcion}',
									'${pageScope.mapf.monto}','${pageScope.mapf.saldoActual}');">
					<ec:column property="codCuenta" title='C&oacute;digo' />
					<ec:column property="descripcion" title='Cuenta' />
					<ec:column property="montoMoney" title='Monto'>
						${pageScope.mapf.montoMoney}
					</ec:column>
					<ec:column property="saldoActualMoney" title='Saldo Actual' />
				</ec:row>
			</ec:table>
			<input type="hidden" name="${requestScope.nombreGrid}_p"
				id="${requestScope.nombreGrid}_p"
				value="${requestScope.numeroPagina}" />
			<input type="hidden" name="${requestScope.nombreGrid}_crd"
				id="${requestScope.nombreGrid}_crd"
				value="${requestScope.cantidadPagina}" />
			<input type="hidden" name="nombreGrid"
				value="${requestScope.nombreGrid}" />
			<input type="hidden" name="numeroPagina"
				value="${requestScope.numeroPagina}" />
			<input type="hidden" name="cantidadPagina"
				value="${requestScope.cantidadPagina}" />
		</html:form>
		<BR>

		<script type="text/javascript">
			agregarPaginacion("detpresupuesto");
		</script>

	</tiles:put>

</tiles:insert>



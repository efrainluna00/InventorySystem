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
	      	
			function tableClick(codRecurso, nombreRecurso,precioUnitario, cantidad){
				$('#codRecurso').val(codRecurso);
				$('#recursoDesc').val(nombreRecurso);
				$('#precioUnitario').val(precioUnitario);
				$('#cantidad').val(cantidad);
								
				habilitar();
			}
			
			function habilitar(){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				$('#btnRecurso').hide();
				$('#recursoDesc').attr('class','caja_textodisable');
				$('#recursoDesc').attr('size','56');
				$('#codRecurso').attr('class','caja_textodisable');
				$('#codRecurso').attr('size','7');
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
	      	
		 	function enviarReporte(accion,codSolicitud){
	      	    var formulario= document.getElementById('reporte');
	            var href = formulario.action;
	            
	            $('#code').val(codSolicitud);
	                    
	            if(href.indexOf('?') >0 ){
	              href= href.substring(0,href.indexOf('?'));
	            }
	            href+='?accion='+accion+'&';				
	            formulario.action= href;
	                          
	            formulario.submit();					
	      	}

	      	

    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="aprSolCompra.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="aprSolCompra.encabezado" />
	</tiles:put>


	<tiles:put name="contenido" type="String">
		<html:form action="/repSolicitudCompraAction.do" styleId="reporte">
			<html:hidden property="codSolicitud" styleId="code" />
		</html:form>
		<html:form action="/aprSolCompraAction.do" styleId="formulario">

			<center>

				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="aprSolCompra.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="aprSolCompraForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
							<html:hidden property="estado" styleId="estado" />
						</td>
						<th style="text-align: left;">
							<bean:message key="aprSolCompra.solicitante" />
							:
						</th>
						<td>
							<bean:write name="aprSolCompraForm" property="solicitante" />
							<html:hidden property="solicitante" styleId="solicitante" />
						</td>
						<th style="text-align: left;">
							<bean:message key="aprSolCompra.estado" />
							:
						</th>
						<td>
							<bean:write name="aprSolCompraForm" property="estadoDesc" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="cotizacion.codBodega" />
							:
						</th>
						<td>
							<bean:write name="aprSolCompraForm" property="codBodega"
								scope="request" />
							<html:hidden property="codBodega" styleId="codBodega" />
						</td>
						<th style="text-align: left;">
							<bean:message key="aprSolCompra.bodega" />
							:
						</th>
						<td>
							<bean:write name="aprSolCompraForm" property="desBodega"
								scope="request" />
							<html:hidden property="desBodega" styleId="desBodega" />
						</td>

					</tr>
				</table>
				<table class="tableDefault">
					<tr>
						<th colspan="4"
							style="text-align: center; background-color: gray;">
							<bean:message key="aprSolCompra.cotizacion" />
						</th>
					</tr>
					<tr>
						<th>
							<bean:message key="aprSolCompra.codCotizacion" />
						</th>
						<td>
							<html:text property="codCotizacion" styleId="codCotizacion"
								size="15" styleClass="caja_texto_obligatorio" readonly="true"
								onchange="enviar('Agregar');"></html:text>
							<html:hidden property="urlCotizacion" styleId="urlCotizacion" />
						</td>
						<th>
							<bean:message key="aprSolCompra.proveedor" />
						</th>
						<td>
							<html:hidden property="codProveedor" styleId="codProveedor" />
							<html:text property="nombreProveedor" styleId="nombreProveedor"
								size="68" styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" id="btnCotizaciones"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=cotizaciones&tipoSolicitud='+document.getElementById('tipoSolicitud').value+'&codSolicitud='+document.forms[1].codSolicitud.value);"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="aprSolCompra.cotizacion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="aprSolCompra.comentario" />
						</th>
						<td colspan="3">
							<html:text property="comentario"
								styleClass="caja_textodisable" size="95"
								styleId="comentario"></html:text>
						</td>
					</tr>
					<tr>
						<th colspan="4"
							style="text-align: center; background-color: gray;">
							<bean:message key="aprSolCompra.presupuesto" />
						</th>
					</tr>
					<tr>
						<th>
							<bean:message key="aprSolCompra.monto" />
						</th>
						<td>
							<html:text property="monto" styleId="monto" size="15"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
						</td>
						<th>
							<bean:message key="aprSolCompra.cuenta" />
						</th>
						<td>
							<html:hidden property="codPresupuesto" styleId="codPresupuesto" />
							<html:text property="codCuenta" styleId="codCuenta"
								styleClass="caja_texto_obligatorio" readonly="true" size="15">
							</html:text>
							<html:text property="descripcion" styleId="descripcion" size="46"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" id="btnPresupuesto"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=presupuestoActivo');"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="aprSolCompra.presupuesto.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="aprSolCompra.saldo" />
						</th>
						<td>
							<html:text property="saldoActual" styleId="saldoActual" size="12"
								styleClass="caja_textodisable" readonly="true"></html:text>

						</td>
						<th>
							<bean:message key="aprSolCompra.montoActual" />
						</th>
						<td>
							<html:text property="montoActual" styleId="montoActual" size="12"
								styleClass="caja_textodisable" readonly="true" value="0"></html:text>
							&nbsp&nbsp Asignaci&oacute;n Automatica &nbsp
							<html:select styleClass="caja_texto_obligatorio"
								property="asigCompra" styleId="asigCompra">
								<html:option value="S">   SI  </html:option>
								<html:option value="N">   NO  </html:option>
							</html:select>

						</td>
					</tr>
					<tr>
						<th colspan="4"
							style="text-align: center; background-color: gray;">
							<bean:message key="aprSolCompra.comentarios" />
						</th>
					</tr>
					<tr>
						<th>
							<bean:message key="aprSolCompra.resolucion" />
						</th>
						<td colspan="3">
							<html:textarea style="background-color:LightGoldenRodYellow;"
								property="motivoRechazo" styleId="motivoRechazo" cols="95"></html:textarea>
						</td>
					</tr>
				</table>

				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="aprSolCompra.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera"
					property="aprSolCompra.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>
				<div id="accionUpdate" class="accion">
					<html:submit property="accion" styleId="aprobar">
						<bean:message key="opc.aprobar" />
					</html:submit>
					<logic:equal name="aprSolCompraForm" property="estado" value="A">
						<html:button property="accion" styleId="anularbtn"
							onclick="enviar('Anular');">
							<bean:message key="opc.anular" />
						</html:button>
					</logic:equal>
					<html:submit property="accion" styleId="rechazar">
						<bean:message key="opc.rechazar" />
					</html:submit>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>

					<logic:equal name="aprSolCompraForm" property="estado" value="A">
						<html:button property="accion" styleId="imprimirbtn"
							onclick="enviarReporte('inicio',+document.getElementById('codSolicitud').value);">
					  Imprimir Reporte
					</html:button>

					</logic:equal>
					<logic:notEqual name="aprSolCompraForm" property="urlCotizacion"
						value="">
						<html:button property="accion" styleId="cotDigital"
							onclick="abrirVentana(document.getElementById('urlCotizacion').value);">
							<bean:message key="opc.cotDigital" />
						</html:button>
					</logic:notEqual>
					<logic:notEqual name="aprSolCompraForm" property="estado" value="E">
						<script>
						$('#motivoRechazo').attr('style','background-color: #CCCCCC');
						$('#motivoRechazo').attr('readonly','true');					
					</script>
					</logic:notEqual>
				</div>
				<br />
				<hr width="85%">
				<logic:equal parameter="accion" value='Actualizar'>
					<script type="text/javascript" language="javascript">
					habilitar();
				</script>
				</logic:equal>
			</center>
		</html:form>

		<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');    					
		</script>

		<ec:table items="listCotDet" var="mapf"
			action="${pageContext.request.contextPath}/aprSolCompraAction.do?accion=Agregar"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif" 
			title='Detalle de Cotizaci&oacute;n' view="compact" width="80%"
			tableId="cotizacionesDet" autoIncludeParameters="true"
			scope="request">
			<ec:row highlightRow="true">
				<ec:column property="id.invRecurso.nombre" title='Recurso'
					width="20%" />
				<ec:column property="precioUnitario" title='Precio Unitario'
					width="20%" />
				<ec:column property="cantidad" title='Cantidad' width="20%" />
				<ec:column property="montoTotal" title='Monto' width="20%">
					${pageScope.mapf.montoTotal}
					<script type="text/javascript">
						$('#montoActual').val(($('#montoActual').val()*1)+${pageScope.mapf.montoTotal})
					</script>
				</ec:column>
			</ec:row>
		</ec:table>

		<script type="text/javascript">			
			if($('#estado').val()!='E'){
				$('#aprobar').hide();
				$('#rechazar').hide();
			}
		</script>
		<script type="text/javascript">
			agregarPaginacion("cotizacionesDet");
				$("form[@id='formulario'] textarea").keypress( function(e){
					if( $(this).attr("id")!= null && $(this).attr("onkeydown")== null )
						return caracteresValidos($(this),e);
				});
			
			$("form[@id='formulario'] textarea").change(function(e){
				if( $(this).attr("id")!= null ){
					if(!cadenaValida($(this).val()))
						$(this).val("");
				}
				});
			
			if($('#estado').val() != 'E'){
				$('#btnBodegas').hide();
				$('#btnPresupuesto').hide();
				$('#btnCotizaciones').hide();
				$('#anularbtn').hide();
			}
			
			if($('#estado').val() == 'A')
				$('#anularbtn').show();
					
		</script>

	</tiles:put>

</tiles:insert>





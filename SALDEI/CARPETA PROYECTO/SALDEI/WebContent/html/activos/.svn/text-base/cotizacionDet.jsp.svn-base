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
	      	
			function tableClick(codRecurso, nombreRecurso,precioUnitario, cantidad, monto){
			  	$(".error").hide();	
				$('#codRecurso').val(codRecurso);
				$('#nombreRecurso').val(nombreRecurso);
				$('#precioUnitario').val(precioUnitario);
				$('#cantidad').val(cantidad);				
				habilitar();
			}
			
			function habilitar(){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				$('#btnRecurso').hide();
				$('#recursoDesc').attr('class','caja_textodisable');
				$('#recursoDesc').attr('size','74');
				$('#codRecurso').attr('class','caja_textodisable');
				$('#codRecurso').attr('size','10');
				agregarMonto();
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
	      	
	      	function formatNumber(num,prefix){
				prefix = prefix || '';
				num += '';
				var splitStr = num.split('.');
				var splitLeft = splitStr[0];
				var splitRight = splitStr.length > 1 ? '.' + splitStr[1] : '';
				var regx = /(\d+)(\d{3})/;
				while (regx.test(splitLeft)) {
				splitLeft = splitLeft.replace(regx, '$1' + ',' + '$2');
				}
				return prefix + splitLeft + splitRight;
			}

		function unformatNumber(num) {
			return num.replace(/([^0-9\.\-])/g,'')*1;
		} 
		
		function agregarMonto(){
			
			if($('#iva').val() == 'SI'){
				$('#precioUnitarioIva').val($('#precioUnitario').val());
				$('#monto').val($('#precioUnitario').val()*$('#cantidad').val())
			}else{
				$('#precioUnitarioIva').val((($('#precioUnitario').val()*$('#tasaIva').val())/100)+($('#precioUnitario').val()*1));
				$('#monto').val($('#precioUnitarioIva').val()*$('#cantidad').val())
			}
		}
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="cotizacionDet.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="cotizacionDet.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/cotizacionDetAction.do">
			<center>
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="cotizacionDet.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="cotizacionDetForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolcitud" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
						</td>
						<th style="text-align: left;">
							<bean:message key="cotizacionDet.solicitante" />
							:
						</th>
						<td>
							<!--  bean:write name="usuario" property="idUsuario" scope="session" />-->
							<bean:write name="usuario" property="primerNom" scope="session"></bean:write>
							<bean:write name="usuario" property="primerApe" scope="session"></bean:write>
						</td>
						<th style="text-align: left;">
							<bean:message key="cotizacionDet.estado" />
							:
						</th>
						<td>
							<bean:write name="cotizacionDetForm" property="estadoDesc" />
							<html:hidden property="estado" styleId="estado" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="cotizacionDet.codCotizacion" />
							:
						</th>
						<td>
							<bean:write name="cotizacionDetForm" property="codCotizacion"
								scope="request" />
							<html:hidden property="codCotizacion" styleId="codCotizacion" />
						</td>
						<th style="text-align: left;">
							<bean:message key="cotizacionDet.proveedor" />
							:
						</th>
						<td>
							<bean:write name="cotizacionDetForm" property="nombreProveedor"
								scope="request" />
							<html:hidden property="codProveedor" styleId="codProveedor" />
						</td>
						<th style="text-align: left;">
							<bean:message key="cotizacionDet.bodega" />
							:
						</th>
						<td>
							<bean:write name="cotizacionDetForm" property="desBodega"
								scope="request" />
							<html:hidden property="codBodega" styleId="codBodega" />
							<html:hidden property="desBodega" styleId="desBodega" />
						</td>

					</tr>
					<tr>
						<th>
							<bean:message key="cotizacionDet.iva" />
							:
						</th>
						<td colspan="5">
							<bean:write name="cotizacionDetForm" property="iva"
								scope="request" />
							<html:hidden property="iva" styleId="iva" />
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="cotizacionDet.comentario" />
							:
						</th>
						<td colspan="5">
							<bean:write name="cotizacionDetForm" property="comentario"
								scope="request" />
						</td>
					</tr>
				</table>
				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="cotizacionDet.recurso" />
						</th>
						<td colspan="3">
							<html:text property="codRecurso" styleId="codRecurso" size="10"
								styleClass="caja_texto_obligatorio" readonly="true">
							</html:text>
							<html:text property="nombreRecurso" styleId="nombreRecurso"
								size="90" styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" id="btnRecurso"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=recursos_x_bodega_cot&codBodega='+document.getElementById('codBodega').value);"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="cotizacionDet.recurso.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="cotizacionDet.precioUnitario" />
						</th>
						<td>
							<html:text property="precioUnitario" styleId="precioUnitario"
								size="25" onchange="agregarMonto();"
								styleClass="caja_texto_obligatorio"
								onkeydown="return soloDinero(this,event);"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="cotizacionDet.precioUnitario.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>

						<th>
							<bean:message key="cotizacionDet.cantidad" />
						</th>
						<td>
							<html:text property="cantidad" styleId="cantidad" size="25"
								onchange="agregarMonto();" styleClass="caja_texto_obligatorio"
								onkeydown="return soloEnteros(this,event);"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="cotizacionDet.cantidad.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="cotizacionDet.precioIva" />
						</th>
						<td>
							<html:text property="precioUnitarioIva"
								styleId="precioUnitarioIva" size="20"
								styleClass="caja_textodisable" readonly="true"></html:text>
							<html:hidden property="tasaIva" styleId="tasaIva" />
						</td>
						<th>
							<bean:message key="cotizacionDet.monto" />
						</th>
						<td>
							<input type="text" class="caja_textodisable" id="monto" size="20" />
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="cotizacionDet.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
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
						onclick=" javascript:
					if(!confirm('¿Esta seguro que desea eliminar el registro seleccionado?'))
						return false;
					">
						<bean:message key="opc.delete" />
					</html:submit>
					<html:submit property="accion" styleId="update">
						<bean:message key="opc.update" />
					</html:submit>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>
				</div>
				<br />
				<script type="text/javascript" language="javascript">
		    	  	if($('#estado').val() != "G"){
						$('#insert').hide();
						$('#update').hide();
						$('#delete').hide();
					}
		    	</script>
				<hr width="85%">
				<logic:equal parameter="accion" value='Actualizar'>
					<script type="text/javascript" language="javascript">
					habilitar();
				</script>
				</logic:equal>

			</center>

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

			<input type="hidden" name="${requestScope.nombreGrid1}_p"
				id="${requestScope.nombreGrid1}_p"
				value="${requestScope.numeroPagina1}" />
			<input type="hidden" name="${requestScope.nombreGrid1}_crd"
				id="${requestScope.nombreGrid1}_crd"
				value="${requestScope.cantidadPagina1}" />
			<input type="hidden" name="nombreGrid1"
				value="${requestScope.nombreGrid1}" />
			<input type="hidden" name="numeroPagina1"
				value="${requestScope.numeroPagina1}" />
			<input type="hidden" name="cantidadPagina1"
				value="${requestScope.cantidadPagina1}" />



			<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
		</script>

			<ec:table items="listCotDet" var="mapf"
				action="${pageContext.request.contextPath}/cotizacionDetAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Detalle de Cotizaci&oacute;n' view="compact" width="60%"
				tableId="cotizacionesDet" autoIncludeParameters="true"
				scope="request">
				<ec:row highlightRow="true"
					onclick="tableClick('${pageScope.mapf.id.invRecurso.codRecurso}','${pageScope.mapf.id.invRecurso.nombre}',
																'${pageScope.mapf.precioUnitario}','${pageScope.mapf.cantidad}','${pageScope.mapf.precioUnitarioIva*pageScope.mapf.cantidad}');">
					<ec:column property="id.invRecurso.nombre" title='Recurso' />
					<ec:column property="pumoney" title='Precio Unitario' />
					<ec:column property="cantidad" title='Cantidad' />
					<ec:column property="momoney" title='Monto' />
				</ec:row>
			</ec:table>
		</html:form>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("cotizacionesDet");
			
			if($('#estado').val() != 'G'){				
				$('#btnRecurso').hide();
			}
			
		</script>
	</tiles:put>

</tiles:insert>




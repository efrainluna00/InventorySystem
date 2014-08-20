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
	      		var formulario= document.getElementById('formularioPadre');
            var href = formulario.action;                        		
			
			$('#archivoPDF').attr('disabled','disabled');
			                    
            if(href.indexOf('?') >0 ){
              href= href.substring(0,href.indexOf('?'));
            }
            href+='?accion='+accion+'&';				
            formulario.action= href;
            formulario.enctype = ""; 
                          
            formulario.submit();					
	      	}
	      	
			function tableClick(codCotizacion, codProveedor,nombreProveedor, urlCotizacion,
			comentario, flagDetalle, iva){
			  $(".error").hide();	
				$('input[@id="codCotizacion"]').val(codCotizacion);
				$('#codProveedor').val(codProveedor);
				$('#nombreProveedor').val(nombreProveedor);
				$('#urlCotizacion').val(urlCotizacion);
				$('#comentario').val(comentario);
				$('#flagDetalleCotizacion').val(flagDetalle);													
				$('#iva').val(iva);
								
				habilitar();
			}
			
			function habilitar(){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));				
				if($('#flagDetalleCotizacion').val() == 'false'){
					$('#btnProveedor').hide();
					$('#nombreProveedor').attr('class','caja_textodisable');
					$('#iva').attr('class','caja_textodisable');
					$('#nombreProveedor').attr('size','80');
					
										
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

	      	function enviarCotizacion(accion, tipoSolicitud,codSolicitud, codCotizacion){
	      		var formulario= document.getElementById('formulario');
	            var href = formulario.action;
	            
	            $('#tipo').val(tipoSolicitud);
	            $('#code').val(codSolicitud);
	            $('#codeCot').val(codCotizacion);
	            
	            // para que cuando regrese del formulario hijo conserve los valores seleccionados
		        $("#numeroPagina1").val($("input[@name='cotizaciones_p']").val());
		        $("#cantidadPagina1").val($("input[@name='cotizaciones_crd']").val());	        
		        $("#nombreGrid1").val('cotizaciones');
		        $("#numeroPagina0").val(document.forms[1].numeroPagina.value);
		        $("#cantidadPagina0").val(document.forms[1].cantidadPagina.value);	        
		        $("#nombreGrid0").val(document.forms[1].nombreGrid.value);
	                    
	            if(href.indexOf('?') >0 ){
	              href= href.substring(0,href.indexOf('?'));
	            }
	            href+='?accion='+accion+'&';				
	            formulario.action= href;
	                          
	            formulario.submit();					
	      	}	      	
	      	function executeTableClick(codRecurso){	      		    		
	      	  document.getElementById(codRecurso).onclick();	      			      		
	      	}
	      	
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="cotizacion.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="cotizacion.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<html:form action="/cotizacionDetAction.do" styleId="formulario">
			<html:hidden property="tipoSolicitud" styleId="tipo" />
			<html:hidden property="codSolicitud" styleId="code" />
			<html:hidden property="codCotizacion" styleId="codeCot" />
			<input type="hidden" name="numeroPagina" id="numeroPagina0" />
			<input type="hidden" name="nombreGrid" id="nombreGrid0" />
			<input type="hidden" name="cantidadPagina" id="cantidadPagina0" />
			<input type="hidden" name="numeroPagina1" id="numeroPagina1" />
			<input type="hidden" name="nombreGrid1" id="nombreGrid1" />
			<input type="hidden" name="cantidadPagina1" id="cantidadPagina1" />
		</html:form>

		<html:form action="/cotizacionAction.do" styleId="formularioPadre"
			enctype="multipart/form-data">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="cotizacion.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="cotizacionForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolcitud" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
						</td>
						<th style="text-align: left;">
							<bean:message key="cotizacion.solicitante" />
							:
						</th>
						<td>
							<!--  bean:write name="usuario" property="idUsuario" scope="session" />-->
							<bean:write name="usuario" property="primerNom" scope="session"></bean:write>
							<bean:write name="usuario" property="primerApe" scope="session"></bean:write>
						</td>
						<th style="text-align: left;">
							<bean:message key="cotizacion.estado" />
							:
						</th>
						<td>
							<bean:write name="cotizacionForm" property="estadoDesc" />
							<html:hidden property="estado" styleId="estado" />
						</td>

					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="cotizacion.codBodega" />
							:
						</th>
						<td>
							<bean:write name="cotizacionForm" property="codBodega"
								scope="request" />
							<html:hidden property="codBodega" styleId="codBodega" />
						</td>
						<th style="text-align: left;">
							<bean:message key="cotizacion.bodega" />
							:
						</th>
						<td>
							<bean:write name="cotizacionForm" property="desBodega"
								scope="request" />
							<html:hidden property="desBodega" styleId="desBodega" />
						</td>

					</tr>
				</table>
				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="cotizacion.codCotizacion" />
						</th>
						<td>
							<html:hidden property="flagDetalleCotizacion"
								styleId="flagDetalleCotizacion" />
							<html:text property="codCotizacion" styleId="codCotizacion"
								size="10" styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
						<th>
							<bean:message key="cotizacion.proveedor" />
						</th>
						<td>
							<html:hidden property="codProveedor" styleId="codProveedor" />
							<html:text property="nombreProveedor" styleId="nombreProveedor"
								size="95" styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" id="btnProveedor"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=proveedores');"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="cotizacion.proveedor.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="cotizacion.iva" />
						</th>
						<td>
							<html:select property="iva" styleId="iva"
								styleClass="caja_texto_obligatorio" style="width: 70px;">
								<html:option value="S"> SI </html:option>
								<html:option value="N"> NO </html:option>
							</html:select>

						</td>
						<th>
							<bean:message key="cotizacion.url" />
						</th>
						<td>
							<html:file property="archivoPDF" styleId="archivoPDF"
								accept="pdf" style="background-color: white;" size="65"></html:file>

						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="cotizacion.comentario" />
						</th>
						<td colspan="3">
							<html:textarea property="comentario" styleId="comentario"
								cols="110" rows="3"
								style="background-color:LightGoldenRodYellow"></html:textarea>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="suministro.descripcion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
				</table>


				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="cotizacion.mensaje.exito">
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
					<html:button property="accion" styleId="back"
						onclick="enviar('Regresar');">
						<bean:message key="opc.back" />
					</html:button>
				</div>
				<div id="accionUpdate" class="accion"
					style="display: none; width: 100%;">
					<html:submit property="accion" styleId="update">
						<bean:message key="opc.update" />
					</html:submit>
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
					<html:button property="accion" styleId="back"
						onclick="enviar('Regresar');">
						<bean:message key="opc.back" />
					</html:button>
				</div>
				<br />
				<hr width="85%">
				<script type="text/javascript" language="javascript">
		    	  	if($('#estado').val() != "G"){
						$('#insert').hide();
						$('#update').hide();
						$('#delete').hide();
					}
		    	</script>
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



			<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
		</script>

			<ec:table items="listCotizaciones" var="mapf"
				action="${pageContext.request.contextPath}/cotizacionAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Cotizaciones' view="compact" width="80%"
				tableId="cotizaciones" form="formularioPadre"
				autoIncludeParameters="yes" scope="request">
				<ec:row highlightRow="true"
					onclick="executeTableClick('lnk${pageScope.mapf.id.codCotizacion}');">
					<ec:column property="id.codCotizacion" title='Cotizaci&oacute;n'
						width="5%" />
					<ec:column property="id.actSolicitud.id.codSolicitud"
						title='Solicitud' width="10%" />
					<ec:column property="actProveedor.nombre" title='Proveedor' />
					<ec:column property="comentario" title='Descripci&oacute;n' />
					<ec:column property="link" title='Acci&oacute;n' width="10%">
										${pageScope.mapf.link}
					<input type="hidden"
							onclick="tableClick('${pageScope.mapf.id.codCotizacion}','${pageScope.mapf.actProveedor.codProveedor}',
																'${pageScope.mapf.actProveedor.nombre}','${pageScope.mapf.urlCotizacion}',
																'${pageScope.mapf.comentario}','${pageScope.mapf.detalleCotizacion}','${pageScope.mapf.iva}');"
							id="lnk${pageScope.mapf.id.codCotizacion}" />
					</ec:column>

				</ec:row>
			</ec:table>
		</html:form>
		<BR>
		<logic:present name="flagBack" scope="request">
			<script type="text/javascript" language="javascript">					
				executeTableClick('${requestScope.flagBack}');
			</script>
		</logic:present>
		<script type="text/javascript">
			agregarPaginacion("cotizaciones");
					$("form[@id='formularioPadre'] textarea").keypress( function(e){
					if( $(this).attr("id")!= null && $(this).attr("onkeydown")== null )
						return caracteresValidos($(this),e);
				});
			
			$("form[@id='formularioPadre'] textarea").change(function(e){
				if( $(this).attr("id")!= null ){
					if(!cadenaValida($(this).val()))
						$(this).val("");
				}
				});
		</script>
	</tiles:put>
</tiles:insert>
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
		    	ifFormat   : "%d/%m/%Y",
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
	      	
			function tableClick(codSolicitud,codUnidad,
								unidadDesc,estado,estadoDesc,comentario,flagCotizaciones, desBodega, codBodega){
				$(".error").hide();	
				$('input[@id="codSolicitud"]').val(codSolicitud);
				$('#codUnidad').val(codUnidad);
				$('#descripcion').val(unidadDesc);
				$('#comentario').val(comentario);				
				$('#estado').val(estado);
				$('#estadoDesc').val(estadoDesc);
				$('#bodegaDes').val(desBodega);
				$('#codBodega').val(codBodega);
				$('#flagCotizaciones').val(flagCotizaciones);
								
								
				habilitar();
			}
			
			function habilitar(){				
				if($('#estado').val() == 'G'){									
					$('#update').show();
					$('#delete').show();
					$("#comentario").attr("readonly","");
				}else{
					$('#update').hide();
					$('#delete').hide();
					$("#comentario").attr("readonly","true");
				}
										
				if($('#flagCotizaciones').val()=='false' && $('#estado').val() == 'G'){
					$('#enviarSolicitud').show();
				}else{
					$('#enviarSolicitud').hide();
				}		
				
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
																
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
	      	
	      		var flag = "in";
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
	      	
	      	function enviarSolCompra(accion, codSolicitud){
	      		var formulario= document.getElementById('formulario');
	            var href = formulario.action;
	            
	            $('#code').val(codSolicitud);
	            
	            // para que cuando regrese del formulario hijo conserve los valores seleccionados
		        $("#numeroPagina").val($("input[@name='solCompras_p']").val());
		        $("#cantidadPagina").val($("input[@name='solCompras_crd']").val());	        
		        $("#nombreGrid").val('solCompras');
	                    
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
		<bean:message key="solCompra.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="solCompra.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<html:form action="/cotizacionAction.do" styleId="formulario">
			<html:hidden property="tipoSolicitud" styleId="tipo" value="C" />
			<html:hidden property="codSolicitud" styleId="code" />
			<input type="hidden" name="numeroPagina" id="numeroPagina" />
			<input type="hidden" name="nombreGrid" id="nombreGrid" />
			<input type="hidden" name="cantidadPagina" id="cantidadPagina" />
		</html:form>

		<html:form action="/solicitudCompraAction.do"
			styleId="formularioPadre">
			<center>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="solCompra.code" />
						</th>
						<td>
							<html:hidden property="flagCotizaciones"
								styleId="flagCotizaciones" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud"
								value="C" />
							<html:text property="codSolicitud" styleId="codSolicitud"
								size="24" readonly="true" styleClass="caja_textodisable"></html:text>
						</td>
						<th>
							<bean:message key="solCompra.solicitante" />
						</th>
						<td>
							<html:hidden property="codSolicitante" styleId="codSolicitante"
								value="${sessionScope.usuario.idUsuario}" />
							<html:text property="solicitante" styleId="solicitante" size="30"
								value="${sessionScope.usuario.primerNom} ${sessionScope.usuario.primerApe}"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="solCompra.unidad" />
						</th>
						<td>
							<html:hidden property="codUnidad" styleId="codUnidad" />
							<html:text property="descripcion" styleId="descripcion" size="30"
								styleClass="caja_texto_obligatorio"></html:text>
							<input type="button" id="btnunidad"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=unidades&codSolicitante=${sessionScope.usuario.idUsuario}');"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="solCompra.unidad.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="solCompra.estado" />
						</th>
						<td>
							<html:hidden property="estado" styleId="estado" value="G" />
							<html:text property="estadoDesc" styleId="estadoDesc" size="30"
								readonly="true" value="Grabada" styleClass="caja_textodisable">
							</html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="solCompra.bodega" />
						</th>
						<td colspan="3">
							<html:text property="codBodega" styleId="codBodega" size="12"
								styleClass="caja_texto_obligatorio" readonly="true">
							</html:text>
							<html:text property="desBodega" styleId="bodegaDes" size="78"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" id="btnBodega"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=bodegas');"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="solCompra.bodega.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="solCompra.comentario" />
						</th>
						<td colspan="3">
							<html:textarea property="comentario" styleId="comentario"
								cols="80" rows="3" style="background-color:LightGoldenRodYellow"></html:textarea>
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
					header="errores.cabecera" property="solCompra.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="solCompra.mensajeError.error">
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
					<html:submit property="accion" styleId="cancelInsert">
						<bean:message key="opc.cancel" />
					</html:submit>
				</div>
				<div id="accionUpdate" class="accion" style="display: none">
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
					<html:submit property="accion" styleId="enviarSolicitud">
						<bean:message key="opc.enviarSolicitud" />
					</html:submit>
				</div>
				<br />
				<hr width="85%">
				<logic:equal parameter="accion" value='Actualizar'>
					<script type="text/javascript" language="javascript">
					habilitar();
				</script>
				</logic:equal>

			</center>

			<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

			<ec:table items="listSolCompras" var="mapf"
				action="${pageContext.request.contextPath}/solicitudCompraAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Solicitudes de Compra' view="compact" width="80%"
				tableId="solCompras" form="formularioPadre"
				autoIncludeParameters="yes" scope="request">
				<ec:row highlightRow="true"
					onclick="executeTableClick('lnk${pageScope.mapf.id.codSolicitud}');">
					<ec:column property="id.codSolicitud" title='Solicitud' />
					<ec:column property="actUnidad.descripcion" title='Unidad' />
					<ec:column property="fechaCreacion"
						title='Fecha de Creaci&oacute;n' format="dd/MM/yyyy HH:mm:ss"
						cell="date" />
					<ec:column property="comentario" title='Descripci&oacute;n' />
					<ec:column property="estadoDesc" title='Estado'
						filterCell="droplist" width="15" />
					<ec:column property="linkSolicitudCompra" title="Acci&oacute;n"
						filterable="false">
					${pageScope.mapf.linkSolicitudCompra}
					<input type="hidden"
							onclick="tableClick('${pageScope.mapf.id.codSolicitud}',									
									'${pageScope.mapf.actUnidad.codUnidad}','${pageScope.mapf.actUnidad.descripcion}',
									'${pageScope.mapf.estado}','${pageScope.mapf.estadoDesc}',
									'${pageScope.mapf.comentario}','${pageScope.mapf.cotizaciones}','${pageScope.mapf.desBodega}','${pageScope.mapf.codBodega}');"
							id="lnk${pageScope.mapf.id.codSolicitud}" />
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
			agregarPaginacion("solCompras");
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


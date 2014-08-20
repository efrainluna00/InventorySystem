<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>



<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
    
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
	      		      	
			function tableClick(codRecurso,recursoDesc,precio,cantidad,cantidadIngresada,monto){
								
				$('#codRecurso').val(codRecurso);
				$('#recursoDesc').val(recursoDesc);
				$('#precioUnitario').val(precio);
				$('#cantidad').val(cantidad-cantidadIngresada);
				$('#cantidadTotal').val(cantidad);
				$('#cantidadInicial').val(cantidad-cantidadIngresada);
				$('#monto').val(monto);
											
			}
			
			function agregarSerie(){
				var cant = $('#cantidad').val();
				var cantInicial = $('#cantidadInicial').val();
				var cantIn ;
				
				if(cant > 0 && $('#numSerie').val() != ""){ 
					$("#seriesRecurso tbody").append('<tr id = "'+$('#numSerie').val()+'" ><td style="text-align: center;" >'+$('#numSerie').val()+ ' <input type="hidden" name="numSeries" id="hd'+$('#numSerie').val()+'" value="'+$("#codRecurso").val()+','+$('#numSerie').val()+'" />  <a  href="#" onclick = "quitarSerie(\''+$('#numSerie').val()+'\');" > Eliminar </a> </td></tr>');					
					$('#cantidad').val(cant-1);										
					cantIn = cantInicial - $('#cantidad').val();
					$("#txt"+$("#codRecurso").val()).val(cantIn);
					$("#"+$("#codRecurso").val()).val($("#hd"+$("#codRecurso").val()).val()+','+cantIn);
				}
				$('#numSerie').val('');												
			}
			
			function quitarSerie(id){								
				var cant = $('#cantidad').val()
				var cantInicial = $('#cantidadInicial').val();
				var cantIn ;
				
				$("#"+id).remove();
				$("#hd"+id).remove();
				$('#cantidad').val(cant-1+2);				
				cantIn = cantInicial - $('#cantidad').val();
				$("#txt"+$("#codRecurso").val()).val(cantIn);
				$("#"+$("#codRecurso").val()).val($("#hd"+$("#codRecurso").val()).val()+','+cantIn);
																
			}
			
			function habilitar(){
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
	      	
	      	function enviarSolCompra(accion, codSolicitud, tipoSolicitud){
	      		var formulario= document.getElementById('formulario');
	            var href = formulario.action;
	            
	            $('#codSolicitud').val(codSolicitud);
	            $('#tipoSolicitud').val(tipoSolicitud);
	                    
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
		<bean:message key="ingSolCompra.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="ingSolCompra.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<center>
			<table class="tableEncabezado">
				<tr>
					<th colspan="6" style="text-align: center; background-color: gray;">
						Datos de la Solicitud
					</th>
				</tr>
				<tr>
					<th style="text-align: left;">
						<bean:message key="ingSolCompra.codSolicitud" />
						:
					</th>
					<td>
						<bean:write name="ingOrdenCompraForm" property="codSolicitud"
							scope="request" />

					</td>
					<th style="text-align: left;">
						<bean:message key="ingSolCompra.solicitante" />
						:
					</th>
					<td>
						<bean:write name="ingOrdenCompraForm" property="solicitante" />
					</td>
					<th style="text-align: left;">
						<bean:message key="ingSolCompra.estado" />
						:
					</th>
					<td>
						<bean:write name="ingOrdenCompraForm" property="estadoDesc" />

					</td>
				</tr>
			</table>

			<br>

			<html:messages id="message" footer="errores.pie"
				header="errores.cabecera" property="ingSolCompra.mensaje.exito">
				<div id="mensaje">
					<bean:write name="message" filter="false" />
				</div>
				<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
			</html:messages>
			<html:messages id="message" footer="errores.pie"
				header="errores.cabecera" property="ingSolCompra.mensaje.fallo">
				<div id="mensajeError">
					<bean:write name="message" filter="false" />
				</div>
				<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
			</html:messages>
			<div id="accionInsert" class="accion">
				<html:button property="accion" styleId="agregar"
					onclick="enviar('Agregar');">
					<bean:message key="opc.insert" />
				</html:button>
				<html:button property="accion" styleId="cancel"
					onclick="enviar('Cancelar');">
					<bean:message key="opc.cancel" />
				</html:button>
				<html:button property="accion" styleId="back"
					onclick="enviar('Regresar');">
					<bean:message key="opc.back" />
				</html:button>
			</div>
			<br />

		</center>
		<html:form action="/ingOrdenCompraAction.do" styleId="formulario">
			<html:hidden property="estado" style="estado" />
			<table>
				<tr>
					<td colspan="2">
						<center>
							<hr width="85%">
						</center>
					</td>
				</tr>
				<tr>
					<td width="50%">
						<ec:table items="listCotDet" var="mapf"
							action="${pageContext.request.contextPath}/ingOrdenCompraAction.do?accion=Enviar"
							imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
							title='Detalle de Solicitud' view="compact" width="95%"
							showStatusBar="false" tableId="solicitudDet" form="formulario"
							showPagination="false" autoIncludeParameters="yes"
							scope="request">
							<ec:row highlightRow="true"
								onclick="tableClick('${pageScope.mapf.id.invRecurso.codRecurso}','${pageScope.mapf.id.invRecurso.nombre}','${pageScope.mapf.precioUnitario}',
												'${pageScope.mapf.cantidad}','${pageScope.mapf.cantidadIngresada}','${pageScope.mapf.montoTotal}');">
								<ec:column property="id.invRecurso.nombre" title='Recurso'
									width="50%" />
								<ec:column property="precioUnitario" title='Precio Unitario'
									filterable="false" />
								<ec:column property="cantidad" title='Cantidad'
									filterable="false" />
								<ec:column property="montoTotal" title='Monto'
									filterable="false">
								${pageScope.mapf.montoTotal}												
								<script type="text/javascript">
									$('#montoActual').val(parseInt($('#montoActual').val())+${pageScope.mapf.montoTotal})
								</script>
								</ec:column>
								<ec:column property="cantidadIngresada"
									title='Cantidad Ingresada' width="15%" filterable="false" />
								<ec:column property="cantidad" title='Cantidad a Ingresar'
									width="20%" filterable="false">
									<input type="hidden"
										id="hd${pageScope.mapf.id.invRecurso.codRecurso}"
										value="${pageScope.mapf.id.invRecurso.codRecurso},${pageScope.mapf.cantidad},${pageScope.mapf.cantidadIngresada}" />
									<html:hidden property="cantidadIngresar"
										styleId="${pageScope.mapf.id.invRecurso.codRecurso}"
										value="${pageScope.mapf.id.invRecurso.codRecurso},${pageScope.mapf.cantidad},${pageScope.mapf.cantidadIngresada}" />
									<input type="text" readonly="readonly"
										class="caja_textodisable"
										id="txt${pageScope.mapf.id.invRecurso.codRecurso}" value="0"
										size="10" />
								</ec:column>
							</ec:row>
						</ec:table>
					</td>
					<td width="50%">

						<html:hidden property="codSolicitud" styleId="codSolicitud" />
						<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
						<html:hidden property="estado" styleId="estado" />
						<table class="tableDefault">
							<tr>
								<th colspan="4"
									style="text-align: center; background-color: gray;">
									Datos del Recurso
								</th>
							</tr>
							<tr>
								<th style="text-align: left;">
									<bean:message key="ingSolCompra.recurso" />
								</th>
								<td colspan="5">
									<input type="text" name="codRecurso" id="codRecurso" size="10"
										class="caja_textodisable" />
									<input type="text" name="nombreRecurso" id="recursoDesc"
										size="40" class="caja_textodisable" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th style="text-align: left;">
									<bean:message key="ingSolCompra.precioUnitario" />
								</th>
								<td>
									<input type="text" name="precioUnitario" id="precioUnitario"
										size="15" readonly="readonly" class="caja_textodisable"
										onkeydown="return soloDinero(this,event);" />
								</td>

								<th style="text-align: left;">
									<bean:message key="ingSolCompra.cantidad" />
								</th>
								<td>
									<input type="hidden" id="cantidadInicial">
									<input type="text" name="cantidadTotal" id="cantidadTotal"
										size="13" class="caja_textodisable"
										onkeydown="return soloEnteros(this,event);" />
								</td>
							</tr>
							<tr>
								<th style="text-align: left;">
									<bean:message key="ingSolCompra.monto" />
								</th>
								<td>
									<input type="text" name="monto" id="monto" size="15"
										readonly="readonly" class="caja_textodisable"
										onkeydown="return soloDinero(this,event);" />
								</td>

								<th style="text-align: left;">
									<bean:message key="ingSolCompra.cantidadPendiente" />
								</th>
								<td>
									<input type="text" name="cantidad" id="cantidad" size="13"
										class="caja_textodisable"
										onkeydown="return soloEnteros(this,event);" />
								</td>
							</tr>

							<tr>
								<th colspan="4" style="text-align: center;">
									Agregrar Series
								</th>
							</tr>
							<tr>
								<th>
									<bean:message key="ingSolCompra.serie" />
								</th>
								<td colspan="3">
									<html:text property="numSerie" styleId="numSerie" size="50"
										styleClass="caja_texto_obligatorio"
										onkeydown="return caracteresValidosSinComa(this,event);"></html:text>
									<input type="button" id="addSerie" onclick="agregarSerie();"
										value="+" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<table class="tableEncabezado" id="seriesRecurso">
										<tr>
										</tr>
									</table>
								</td>
							</tr>
						</table>


					</td>
				</tr>
			</table>
		</html:form>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("solicitudDet");
			
			if($('#estado').val() != 'A' && $('#estado').val() != 'H'){
				$('#agregar').hide();
				$("input[@name='numSerie']").attr('class','caja_textodisable');
				$("input[@name='numSerie']").attr('readonly','true');
				$('#addSerie').hide();
			}
		</script>

		<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
		</script>
	</tiles:put>

</tiles:insert>



<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>



<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
    	var est;
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
	      	
			function tableClick(codSolicitud,codPropietario,fecha_creacion,fecha_aprobacion,
			fecha_finalizacion,fecha_anulacion,rechazo,anulacion,propietario,codUnidad,
			estado,estadoNombre,fechaInicial,fechaFinal,comentario,registroModificable){
				//alert(rechazo);
				
			  	$(".error").hide();	
				$('#codSolicitud').val(codSolicitud);
				$('#codUnidad').val(codUnidad);
				$('#estado').val(estado);
				$('#comentario').val(comentario);
				$('#fecha_ini').val(fechaInicial);
				$('#fecha_fin').val(fechaFinal);
				$('#codPropietario').val(codPropietario);
				$('#fecha_aprobacion').val(fecha_aprobacion);
				$('#fecha_finalizacion').val(fecha_finalizacion);
				$('#fecha_anulacion').val(fecha_anulacion);
				$('#propietario').val(propietario);
				$('#estadoNombre').val(estadoNombre);
				if(rechazo != "")
					$('#resolucion').val(rechazo);
				else 
					$('#resolucion').val(anulacion);
				if(estado == "G"){
					habilio(registroModificable);
					$('#update').show();
					$('#delete').show();					
				}
				else{
					habilio(registroModificable);
					$('#fechaFinalbtn').hide();
					$('#fechaInicialbtn').hide();
					$('#comentario').attr('style','background-color: #CCCCCC');
					$('#comentario').attr('readonly','true');	
					
					$('#update').hide();
					$('#delete').hide();
				}
				habilitar();
		
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
	      	
	      	function enviarSolPrestamo(accion, manualCode,cpropietario,estado,estadoNombre,propietario,inicial, final,comment,ubicacion,soli){
	      		var formulario= document.getElementById('formulario');
	            var href = formulario.action;
	            
	            $('#code').val(manualCode);
	            $('#codp').val(cpropietario);
	            $('#estadoh').val(estado);
	            $('#estadon').val(estadoNombre);
	            $('#prop').val(propietario);
	            $('#coment').val(comment);
	            $('#final').val(final);				
	            $('#inicial').val(inicial);
	            $('#codu').val(ubicacion);
	            $('#codsol').val(soli);
	            
	             // para que cuando regrese del formulario hijo conserve los valores seleccionados
		        $("#numeroPagina").val($("input[@name='tsolicitudes_p']").val());
		        $("#cantidadPagina").val($("input[@name='tsolicitudes_crd']").val());	        
		        $("#nombreGrid").val('tsolicitudes');
	                    
	            if(href.indexOf('?') >0 ){
	              href= href.substring(0,href.indexOf('?'));
	            }
	            href+='?accion='+accion+'&';				
	            formulario.action= href;
	            //formulario.target = '_blank';              
	            formulario.submit();					
	      	}
	      	
	      	function habilio(r){
	      	if(r=='N'){
						$('#btnCategoria').hide();
						$('#propietario').attr("class","caja_textodisable");
						$('#propietario').attr("size","30");
						
					}
					else{
						$('#btnCategoria').show();
						$('#propietario').attr("class","caja_texto_obligatorio");
						$('#propietario').attr("size","37");
						
					}
	      	}
	        function executeTableClick(codRecurso){	      		    		
	      	  document.getElementById(codRecurso).onclick();	      			      		
	      	}
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="prestamo.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="prestamo.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/detalleSolPrestamoAction.do" styleId="formulario">
			<html:hidden property="codSolicitud2" styleId="code" />
			<html:hidden property="codPropietario2" styleId="codp" />
			<html:hidden property="comentario2" styleId="coment" />
			<html:hidden property="estado2" styleId="estadoh" />
			<html:hidden property="estadoNombre" styleId="estadon" />
			<html:hidden property="propietario2" styleId="prop" />
			<html:hidden property="fecha_ini2" styleId="inicial" />
			<html:hidden property="fecha_fin2" styleId="final" />
			<html:hidden property="codUnidad2" styleId="codu" />
			<html:hidden property="codSolicitante2" styleId="codsol" />
			<input type="hidden" name="numeroPagina" id="numeroPagina" />
			<input type="hidden" name="nombreGrid" id="nombreGrid" />
			<input type="hidden" name="cantidadPagina" id="cantidadPagina" />
		</html:form>

		<html:form action="/solPrestamoAction.do" styleId="formularioPadre">
			<center>
				<br>
				<table class="tableEncabezado">

					<tr>
						<td></td>
						<th>
							<bean:message key="prestamo.solicitud" />
						</th>
						<td>
							<html:text property="codSolicitud" styleId="codSolicitud"
								size="15" styleClass="caja_textodisable" readonly="true">
							</html:text>
						</td>

					</tr>
				</table>
				<table class="tableDefault">

					<tr>

						<th>
							<bean:message key="prestamo.solicitante" />
						</th>
						<td>
							<html:text property="codSolicitante" styleId="codSolicitante"
								value="${sessionScope.user}" readonly="true"
								styleClass="caja_textodisable" size="10"></html:text>
							<bean:write name="usuario" property="primerNom" scope="session"></bean:write>
							<bean:write name="usuario" property="primerApe" scope="session"></bean:write>
						</td>

					</tr>

					<tr>
						<th>
							<bean:message key="prestamo.fecha_ini" />
						</th>
						<td>
							<html:hidden property="codSolicitud" styleId="codSolicitud"></html:hidden>
							<html:text property="fecha_ini" styleId="fecha_ini" size="36"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" value="..." id="fechaInicialbtn"
								onmousedown="verCalen('fecha_ini', 'fechaInicialbtn' )"
								class="ButtonText" />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="prestamo.fechaIni.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="prestamo.fechaAprobacion" />
						</th>
						<td>
							<html:text property="fecha_aprobacion" styleId="fecha_aprobacion"
								readonly="true" styleClass="caja_textodisable" size="35"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="prestamo.fecha_fin" />
						</th>
						<td>

							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud"
								value="P"></html:hidden>
							<html:hidden property="estado" styleId="estado"></html:hidden>


							<html:text property="fecha_fin" styleId="fecha_fin" size="36"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" value="..." id="fechaFinalbtn"
								onmousedown="verCalen('fecha_fin', 'fechaFinalbtn' )"
								class="ButtonText" />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="prestamo.fechaIni.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="prestamo.fecha.error">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="prestamo.fechaFinalizacion" />
						</th>
						<td>
							<html:text property="fecha_finalizacion"
								styleId="fecha_finalizacion" readonly="true"
								styleClass="caja_textodisable" size="35"></html:text>
						</td>
					</tr>
					<tr>

						<th>
							<bean:message key="prestamo.solicitara" />
						</th>
						<td>

							<html:hidden property="codUnidad" styleId="codUnidad"></html:hidden>
							<html:hidden property="codPropietario" styleId="codPropietario"></html:hidden>
							<html:text property="propietario" styleId="propietario" size="36"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input id="btnCategoria" type="button"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=activos&codSolicitante='+document.getElementById('codSolicitante').value);"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="prestamo.propietario.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="prestamo.estado" />
						</th>
						<td>
							<html:text property="estadoNombre" styleId="estadoNombre"
								readonly="true" styleClass="caja_textodisable" size="35"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="prestamo.comentario" />
						</th>
						<td colspan="3">
							<html:textarea property="comentario" styleId="comentario"
								cols="100" style="background-color:LightGoldenRodYellow"
								rows="3">
							</html:textarea>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="suministro.descripcion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="prestamo.motivos" />
						</th>
						<td colspan="3">
							<html:textarea property="resolucion" styleId="resolucion"
								style="background-color: #CCCCCC;" readonly="true" cols="100"
								rows="3">
							</html:textarea>

						</td>
					</tr>

				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="prestamo.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="prestamo.mensajeError.error">
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

			<ec:table items="listSolicitud" var="mapf"
				action="${pageContext.request.contextPath}/solPrestamoAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Solicitudes' view="compact" width="80%"
				tableId="tsolicitudes" form="formularioPadre"
				autoIncludeParameters="yes" scope="request">
				<ec:row highlightRow="true"
					onclick="executeTableClick('lnk${pageScope.mapf.codSolicitud}');">
					<ec:column property="codSolicitud" title='Solicitud No.'
						width="20%" />
					<ec:column property="propietario" title='Solicitar a' />
					<ec:column property="comentario" title='Descripci&oacute;n' />
					<ec:column property="fecha_creacion"
						title='Fecha de Creaci&oacute;n' />
					<ec:column property="fecha_ini" title='Fecha de Pr&eacute;stamo' />
					<ec:column property="fecha_fin" title='Fecha de Devoluci&oacute;n' />
					<ec:column property="estadoNombre" title='Estado' />
					<ec:column property="prestamoLink" title='Acci&oacute;n'>
					${pageScope.mapf.prestamoLink}
				 <input type="hidden"
							onclick="tableClick('${pageScope.mapf.codSolicitud}','${pageScope.mapf.codPropietario}','${pageScope.mapf.fecha_creacion}','${pageScope.mapf.fecha_aprobacion}',
																'${pageScope.mapf.fecha_finalizacion}', '${pageScope.mapf.fecha_anulacion}',  '${pageScope.mapf.motivoRechazo}','${pageScope.mapf.motivoAnulacion}',
																'${pageScope.mapf.propietario}','${pageScope.mapf.codUnidad}','${pageScope.mapf.estado}','${pageScope.mapf.estadoNombre}',
																'${pageScope.mapf.fecha_ini}','${pageScope.mapf.fecha_fin}','${pageScope.mapf.comentario}','${pageScope.mapf.registroModificable}');"
							id="lnk${pageScope.mapf.codSolicitud}" />
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
			agregarPaginacion("tsolicitudes");
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


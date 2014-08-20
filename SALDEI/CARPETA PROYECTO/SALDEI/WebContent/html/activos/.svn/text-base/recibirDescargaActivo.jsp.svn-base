<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>

<tiles:insert name="plantillaStandar" definition="plantillaStandar">
	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
    	
    		function consultarHistorico(accion,mh){
    			
	      		var formulario= document.getElementById('formu');
	      		var href = formulario.action;
	      		
	      		if(mh=='S')
	      			$("#mostrarHistorico").val("N");
	      		else
	      			$("#mostrarHistorico").val("S");
	                    	                    
	            if(href.indexOf('?') >0 ){
	              href= href.substring(0,href.indexOf('?'));
	            }
	            href+='?accion='+accion;				
	            formulario.action= href;
	                          
	            formulario.submit();					
	      	}
    	
    	
    	
			function habilitar(){
				$('#accionInsert').SlideInDown('slow');
				
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

	      	  	function enviarSolDescAct(accion, tipoSolicitud, codSolicitud,correlativo,codSolicitante,
	      	  	estado,codSol2,comentario,estadoNombre,comentarioTecnico,solicitante,codBodega,resolucion){
	      		var formulario= document.getElementById('formularioDetalleSolDescAct');
	            var href = formulario.action;
	            
	            $('#sTipoSolicitud').val(tipoSolicitud);
	            $('#sCodSolicitud').val(codSolicitud);	
	            $('#correlativo').val(correlativo);				
	            $('#sCodSolicitante').val(codSolicitante);
	            $('#sEstado').val(estado);
	            $('#sCodSol2').val(codSol2);
	            $('#sComentario').val(comentario);
	            $('#sEstadoNombre').val(estadoNombre);
	            if(comentarioTecnico=='null')
	            	$('#sComentarioTecnico').val("");
	            else
	            	$('#sComentarioTecnico').val(comentarioTecnico);
	            if(resolucion=='null')
	            	$('#sMotivoRechazo').val("");
	            else
	            	$('#sMotivoRechazo').val(resolucion);
	            
	            $('#sSolicitante').val(solicitante);
	            $('#sCodBodega').val(codBodega);
	  	            	                    
	            if(href.indexOf('?') >0 ){
	              href= href.substring(0,href.indexOf('?'));
	            }
	            href+='?accion='+accion;				
	            formulario.action= href;
	                          
	            formulario.submit();					
	      	}
	      	
	   
	      	function tableClick(codSolicitud,estado,fecha_aprobacion,fecha_finalizacion,
	      	bodegaDes,comentario,resolucion,fecha_creacion,solicitante,codSolicitante){
	      	$(".error").hide();	
			document.getElementById('codSolicitud').value = codSolicitud;
			document.getElementById('codSol2').value = codSolicitud;
			document.getElementById('estado').value = estado;
			document.getElementById('fecha_aprobacion').value = fecha_aprobacion;
			document.getElementById('fecha_finalizacion').value = fecha_finalizacion;
			//document.getElementById('fecha_anulacion').value = fecha_anulacion;
			document.getElementById('comentario').value = comentario;
			$("#resolucion").text(resolucion);
			$("#fecha_creacion").val(fecha_creacion);
			$("#solicitante").text(solicitante);
			$("#bodegaDes").val(bodegaDes);
			$("#codSolicitante").val(codSolicitante);
				
			}
			
	      	
	      	function limpiarCodigo(){
	      		$("#codigoSolicitud").val(0);
	      		$("#codSol2").val("");
	      	}
	      	var jCodSolicitante;
	      	
	     	function executeTableClick(codRecurso){	      		    		
	      	   document.getElementById(codRecurso).onclick();	      			      		
	      	}
	      	
   </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="rectras.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="rectras.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<html:form action="/detRecibirDescargaActivoAction.do"
			styleId="formularioDetalleSolDescAct">
			<html:hidden property="tipoSolicitud" styleId="sTipoSolicitud"
				value='D' />
			<html:hidden property="codSolicitud" styleId="sCodSolicitud" />
			<html:hidden property="correlativo" styleId="correlativo" />
			<html:hidden property="codSolicitante" styleId="sCodSolicitante" />
			<html:hidden property="estado" styleId="sEstado" />
			<html:hidden property="codSol2" styleId="sCodSol2" />
			<html:hidden property="comentario" styleId="sComentario" />
			<html:hidden property="estadoNombre" styleId="sEstadoNombre" />
			<html:hidden property="comentarioTecnico"
				styleId="sComentarioTecnico" />
			<html:hidden property="solicitante" styleId="sSolicitante" />
			<html:hidden property="codBodega" styleId="sCodBodega" />
			<html:hidden property="motivoRechazo" styleId="sMotivoRechazo" />
		</html:form>

		<br>

		<center>

			<html:form action="/recibirDescargaActivoAction.do" styleId="formu">

				<br>
				<br>
				<table class="tableEncabezado">
					<tr>
						<th>
							<bean:message key="prestamo.solicitud" />
						</th>
						<td>

							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud"
								value='D' />
							<html:hidden property="estado" styleId="estado" value='G' />
							<script>
								jCodSolicitante = $("#codSolicitante").val();
							</script>
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:text property="codSol2" styleId="codSol2" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
					</tr>
				</table>

				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="soldesca.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>
				<br>

				<table class="tableDefault">

					<tr>

						<th>
							<bean:message key="prestamo.solicitante" />
						</th>
						<td>
							<table class="tableEncabezado" align="left" width="200px">
								<tr>
									<td>
										<div id="solicitante">
											&nbsp
										</div>
									</td>
								</tr>
							</table>
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
							<bean:message key="prestamo.codSolicitante" />
						</th>
						<td>
							<html:text property="codSolicitante" styleId="codSolicitante"
								readonly="true" styleClass="caja_textodisable" size="35"></html:text>
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
							<bean:message key="recibirdesc.bodegaDes" />
						</th>
						<td>
							<html:text property="bodegaDes" styleId="bodegaDes"
								readonly="true" styleClass="caja_textodisable" size="35"></html:text>
						</td>
						<th>
							<bean:message key="soldesca.fechaCreacion" />
						</th>
						<td>
							<html:text property="fecha_creacion" styleId="fecha_creacion"
								readonly="true" styleClass="caja_textodisable" size="35"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="prestamo.comentario" />
						</th>
						<td colspan="3">
							<html:textarea property="comentario" styleId="comentario"
								cols="100" rows="3" readonly="true"
								style="background-color: #CCCCCC;">
							</html:textarea>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="resojefe.mensaje" />
						</th>
						<td colspan="3">
							<html:textarea property="resolucion" styleId="resolucion"
								cols="100" rows="3" readonly="true"
								style="background-color: #CCCCCC;">
							</html:textarea>
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="soldesca.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<br>
				<html:hidden property="mostrarHistorico" styleId="mostrarHistorico" />
				<html:button property="accion" value=""
					onclick="consultarHistorico('Find',
							$('#mostrarHistorico').val())"
					styleId="btnHistorico">
				</html:button>
				<script>
							if($('#mostrarHistorico').val()=='N' || $('#mostrarHistorico').val()=='' )
								$("#btnHistorico").val("Mostrar historicos");
							if( $('#mostrarHistorico').val()=='S' )
								$("#btnHistorico").val("Pendientes");
								
							</script>
				<br>
				<br>
				<hr width="85%">

				<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

				<ec:table items="listaSolicitudes" var="mapf"
					action="${pageContext.request.contextPath}/recibirDescargaActivoAction.do?accion=Find"
					imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
					title='Descarga de Activos' view="compact" width="80%"
					tableId="descAct" autoIncludeParameters="yes" scope="request"
					form="formu">
					<ec:row highlightRow="true"
						onclick="executeTableClick('lnk${pageScope.mapf.codSolicitud}');">
						<ec:column property="codSolicitud" title='No Solicitud' />
						<ec:column property="fecha_creacion"
							title='Fecha de creaci&oacute;n' />
						<ec:column property="estadoDescr" title='Estado' />
						<ec:column property="solicitante" title='Solicitante' />
						<ec:column property="bodegaDes" title='Bodega de destino' />
						<ec:column property="solDescActLink" title='Acci&oacute;n'>
						${pageScope.mapf.solDescActLink}
					<input type="hidden"
								onclick="tableClick('${pageScope.mapf.codSolicitud}','${pageScope.mapf.estado}',
			'${pageScope.mapf.fecha_aprobacion}','${pageScope.mapf.fecha_finalizacion}','${pageScope.mapf.bodegaDes}',
			'${pageScope.mapf.comentario}','${pageScope.mapf.resolucion}','${pageScope.mapf.fecha_creacion}',
			'${pageScope.mapf.solicitante}', '${pageScope.mapf.codSolicitante}', '${pageScope.mapf.comentarioTecnico}'
			);"
								id="lnk${pageScope.mapf.codSolicitud}" />
						</ec:column>


					</ec:row>
				</ec:table>
			</html:form>

		</center>
		<br>
		<logic:present name="flagBack" scope="request">
			<script type="text/javascript" language="javascript">					
				executeTableClick('${requestScope.flagBack}');
			</script>
		</logic:present>
		<script type="text/javascript">
			agregarPaginacion("descAct");
		</script>

	</tiles:put>
</tiles:insert>
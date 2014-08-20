<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>

<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
		
  		
  	
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


	      	  	function enviarSolDescAct(accion, tipoSolicitud, codSolicitud,correlativo,codSolicitante,estado,codSol2,
	      	  	comentario,estadoNombre,fecha_aprobacion, fecha_finalizacion, fecha_anulacion, comentario,
	      	  	resolucion, fecha_creacion){
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
	            
	            // para que cuando regrese del formulario hijo conserve los valores seleccionados
		        $("#numeroPagina").val($("input[@name='descAct_p']").val());
		        $("#cantidadPagina").val($("input[@name='descAct_crd']").val());	        
		        $("#nombreGrid").val('descAct');
	              	            	                    
	            if(href.indexOf('?') >0 ){
	              href= href.substring(0,href.indexOf('?'));
	            }
	            href+='?accion='+accion;				
	            formulario.action= href;
	                          
	            formulario.submit();					
	      	}
	      	
	   
	      	function tableClick(codSolicitud,estado,fecha_aprobacion,fecha_finalizacion,
	      	fecha_anulacion,comentario,resolucion,fecha_creacion){
	      	
	   	
	      	$(".error").hide();
	      	$("input[@id='codSolicitud']").val(codSolicitud);
	      	$("input[@id='codSol2']").val(codSolicitud);
	      	
			//document.getElementById('codSolicitud').value = codSolicitud;
			//document.forms[0].elements[1].value = codSolicitud;
			//document.forms[1].elements[3].value = codSolicitud;
			//document.forms[1].codSol2.value = codSolicitud;
		
			$("input[@id='estado']").val(estado);
			document.getElementById('fecha_aprobacion').value = fecha_aprobacion;
			document.getElementById('fecha_finalizacion').value = fecha_finalizacion;
			//document.getElementById('fecha_anulacion').value = fecha_anulacion;
			//$("#comentario").text(comentario);
			//$("textarea[@id='comentario']").text("");
			$("textarea[@id='comentario']").val(comentario);
			$("#resolucion").text(resolucion);
			$("#fecha_creacion").val(fecha_creacion);
			
			habilitar();
			limpiarMensajes();
				if(estado=='G'){
			$("textarea[@id='comentario']").attr("readonly","");
			$("#delete").show();
			$("#update").show();
			$("#cancel").show();
			$("#insert").hide();
			$("#selectUni").hide();
			//$("#descripcion").attr("styleClass","caja_textodisable");
			//$("#descripcion").attr("size","24");
			}
			else
			{
			$("textarea[@id='comentario']").attr("readonly","true");	
			$("#delete").hide();
			$("#cancel").show();
			$("#insert").hide();
			}
			
			$("#bodegaDes").attr("class","caja_textodisable");
			$("#btnBodega").hide();
		}
			
			//$("#codSolicitud").val("");
	      	$("#delete").hide();
	      	
	      	function limpiarCodigo(){
	      		$("#codigoSolicitud").val(0);
	      		$("#codSol2").val("");
	      	}
	      	var jCodSolicitante;
	      	
	      	function limpiarMensajes(){
	      	$(".error").text("");
	      	}
	      	function executeTableClick(codRecurso){	      		    		
	      	  document.getElementById(codRecurso).onclick();	      			      		
	      	}
	      	
	 
   </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="soldesca.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="soldesca.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<html:form action="/detalleSolDescActAction.do"
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
			<input type="hidden" name="numeroPagina" id="numeroPagina" />
			<input type="hidden" name="nombreGrid" id="nombreGrid" />
			<input type="hidden" name="cantidadPagina" id="cantidadPagina" />
		</html:form>

		<br>
		<html:form action="/solDescActAction.do" styleId="formpadre">
			<br>

			<center>
				<table class="tableEncabezado">
					<tr>
						<th>
							<bean:message key="prestamo.solicitud" />
						</th>
						<td>
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud"
								value='D' />
							<html:hidden property="estado" styleId="estado" value='G' />
							<html:hidden property="codSolicitante" styleId="codSolicitante"
								value='${sessionScope.user}' />
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
										<bean:write name="usuario" property="primerNom"
											scope="session"></bean:write>
										<bean:write name="usuario" property="primerApe"
											scope="session"></bean:write>
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
								value="${sessionScope.user}" readonly="true"
								styleClass="caja_textodisable" size="35"></html:text>
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
							<bean:message key="prestamo.fechaAnulacion" />
						</th>
						<td>
							<html:text property="fecha_anulacion" styleId="fecha_anulacion"
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
								cols="100" rows="3"
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
					<tr>
						<th>
							<bean:message key="prestamo.motivos" />
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

				<div id="accionInsert" class="accion" align="center">
					<html:submit property="accion" styleId="insert">
						<bean:message key="opc.insert" />
					</html:submit>
					<html:submit property="accion" styleId="update"
						style="display:none">
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
					<html:submit property="accion" styleId="cancel"
						onclick="limpiarCodigo()">
						<bean:message key="opc.cancel" />
					</html:submit>
				</div>

				<br>

				<hr width="85%">
				<br>

			</center>

			<script>
				$("#delete").hide();
				$("#cancel").hide();
			</script>

			<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

			<ec:table items="listaSolicitudes" var="mapf"
				action="${pageContext.request.contextPath}/solDescActAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Solicitud de Traslado de Activos a Bodega' view="compact"
				width="80%" tableId="descAct" form="formpadre"
				autoIncludeParameters="yes" scope="request">
				<ec:row highlightRow="true"
					onclick="executeTableClick('lnk${pageScope.mapf.codSolicitud}');">
					<ec:column property="codSolicitud" title='No Solicitud' />
					<ec:column property="fecha_creacion"
						title='Fecha de creaci&oacute;n' />
					<ec:column property="comentario" title='Descripci&oacute;n' />
					<ec:column property="estadoDescr" title='Estado' />
					<ec:column property="solDescActLink" title='Acci&oacute;n'>
					${pageScope.mapf.solDescActLink}
				 <input type="hidden"
							onclick="tableClick('${pageScope.mapf.codSolicitud}','${pageScope.mapf.estado}',
			'${pageScope.mapf.fecha_aprobacion}','${pageScope.mapf.fecha_finalizacion}','${pageScope.mapf.fecha_anulacion}',
			'${pageScope.mapf.comentario}','${pageScope.mapf.resolucion}','${pageScope.mapf.fecha_creacion}'
			);"
							id="lnk${pageScope.mapf.codSolicitud}" />
					</ec:column>
				</ec:row>
			</ec:table>
		</html:form>
		<br>
		<logic:present name="flagBack" scope="request">
			<script type="text/javascript" language="javascript">					
				executeTableClick('${requestScope.flagBack}');
			</script>
		</logic:present>
		<script type="text/javascript">
			agregarPaginacion("descAct");
	
				$("form[@id='formpadre'] textarea").keypress( function(e){
					if( $(this).attr("id")!= null && $(this).attr("onkeydown")== null )
						return caracteresValidos($(this),e);
				});
			
			$("form[@id='formpadre'] textarea").change(function(e){
				if( $(this).attr("id")!= null ){
					if(!cadenaValida($(this).val()))
						$(this).val("");
				}
				});
				
			</script>
	</tiles:put>

</tiles:insert>

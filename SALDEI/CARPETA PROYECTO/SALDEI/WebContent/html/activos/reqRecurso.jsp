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
	      	
			function tableClick(codSolicitud,estadoDes,codUnidad,unidadDes,codBodega,bodegaDes,
			tipoSolicitud,comentario,estado,fecha_creacion,fecha_anulacion, fecha_finalizacion, fecha_aprobacion, 
			motivoRechazo, motivoAnulacion,bodegaEditable){
			  	$(".error").hide();	
				$('input[@id="codSolicitud"]').val(codSolicitud);
				$('#estadoDes').val(estadoDes);
				$('#codUnidad').val(codUnidad);
				$('#unidadDes').val(unidadDes);
				$('input[@id="codBodega"]').val(codBodega);
				$('input[@id="bodegaDes"]').val(bodegaDes);
				$('input[@id="tipoSolicitud"]').val(tipoSolicitud);
				$('#comentario').val(comentario);
				$('#estado').val(estado);
				$('#fecha_creacion').val(fecha_creacion);
			    $('#fecha_anulacion').val(fecha_anulacion);
				$('#fecha_finalizacion').val(fecha_finalizacion);
				$('#fecha_aprobacion').val(fecha_aprobacion);
				$('#motivoRechazo').val(motivoRechazo);
				$('#motivoAnulacion').val(motivoAnulacion);
				habilitar();
				if (estado == "G"){
					 $('#update').show();
					 $('#insert').show();
					 $('#delete').show();
					 modibode(bodegaEditable);
					 $('#comentario').attr("readonly","");
					
			   }else{
			    	 $('#comentario').attr("readonly","true");
			   		 $('#update').hide();
					 $('#insert').hide();
					 $('#delete').hide();
					 $('#btnUnidades').hide();
					 modibode(bodegaEditable);
			   }
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
	      	
	      	function mostrarDetRequisicion(accion, manualCode,codBodega,bodDes,tipoSol){
	      		var formulario= document.getElementById('formulario');
	            var href = formulario.action;
	            
	            $('#code').val(manualCode);
	            $('#bod').val(codBodega);
	            $('#bodDes').val(bodDes);
	            $('#tipoSol').val(tipoSol);
	            
	            // para que cuando regrese del formulario hijo conserve los valores seleccionados
		        $("#numeroPagina").val($("input[@name='solicitudes_p']").val());
		        $("#cantidadPagina").val($("input[@name='solicitudes_crd']").val());	        
		        $("#nombreGrid").val('solicitudes');
	                    
	            if(href.indexOf('?') >0 ){
	              href= href.substring(0,href.indexOf('?'));
	            }
	            href+='?accion='+accion+'&';				
	            formulario.action= href;
	                          
	            formulario.submit();					
	      	}
	      	
	      	function modibode(bodegaEditable){
	      			if(bodegaEditable=='N'){
					 		$('#btnBodega').hide();
					 	$('input[@id="codBodega"]').attr("class","caja_textodisable");
					 	$('input[@id="bodegaDes"]').attr("class","caja_textodisable");
					 	$('input[@id="bodegaDes"]').attr("size","28");
					 		}
					 		else{
					 		$('#btnBodega').show();
					 		$('input[@id="codBodega"]').attr("class","caja_texto_obligatorio");
					 		$('input[@id="bodegaDes"]').attr("class","caja_texto_obligatorio");
					 		$('input[@id="bodegaDes"]').attr("size","35");
					 		}
	      	}
	      	function executeTableClick(codRecurso){	      		    		
	      	  document.getElementById(codRecurso).onclick();	      			      		
	      	}
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="requisicion.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="requisicion.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/detReqRecursoAction.do" styleId="formulario">
			<html:hidden property="codSolicitud" styleId="code" />
			<html:hidden property="codBodega" styleId="bod" />
			<html:hidden property="bodegaDes" styleId="bodDes" />
			<html:hidden property="tipoSolicitud" styleId="tipoSol" />
			<input type="hidden" name="numeroPagina" id="numeroPagina" />
			<input type="hidden" name="nombreGrid" id="nombreGrid" />
			<input type="hidden" name="cantidadPagina" id="cantidadPagina" />
		</html:form>

		<html:form action="/reqRecursoAction.do" styleId="formpadre">
			<center>
				<br>
				<table class="tableEncabezado">
					<tr>
						<th>
							<bean:message key="requisicion.codSolicitud" />
						</th>
						<td>
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
							<html:text property="codSolicitud" styleId="codSolicitud"
								size="25" readonly="true"></html:text>
						</td>
						<th>
							<bean:message key="requisicion.fecha_creacion" />
						</th>
						<td>
							<html:text property="fecha_creacion" styleId="fecha_creacion"
								readonly="true" size="25"></html:text>
						</td>
					</tr>
				</table>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="requisicion.solicitante" />
						</th>
						<td>
							<html:text property="codSolicitante" styleId="codSolicitante"
								value="${sessionScope.user}" readonly="true" size="10"></html:text>
							<bean:write name="usuario" property="primerNom" scope="session"></bean:write>
							<bean:write name="usuario" property="primerApe" scope="session"></bean:write>
						</td>
						<th>
							<bean:message key="requisicion.estado" />
						</th>
						<td>
							<html:hidden property="estado" styleId="estado"></html:hidden>
							<html:text property="estadoDes" styleId="estadoDes"
								readonly="true" size="35" styleClass="caja_textodisable">
							</html:text>
						</td>

					</tr>
					<tr>
						<th>
							<bean:message key="requisicion.unidad" />
						</th>
						<td>
							<html:text property="codUnidad" styleId="codUnidad" size="3"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<html:text property="unidadDes" styleId="unidadDes" size="35"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input id="btnUnidades" type="button"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=unidades2&codSolicitante='+document.getElementById('codSolicitante').value);"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="requisicion.unidad.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="requisicion.fecha_aprobacion" />
						</th>
						<td>
							<html:text property="fecha_aprobacion" styleId="fecha_aprobacion"
								readonly="true" size="35" styleClass="caja_textodisable"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="requisicion.bodega" />
						</th>
						<td>
							<html:text property="codBodega" styleId="codBodega"
								styleClass="caja_texto_obligatorio" size="3" readonly="true"></html:text>
							<html:text property="bodegaDes" styleId="bodegaDes"
								readonly="true" styleClass="caja_texto_obligatorio" size="35">
							</html:text>
							<input id="btnBodega" type="button"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=bodegas');"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="requisicion.bodega.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="requisicion.fecha_finalizacion" />
						</th>
						<td>
							<html:text property="fecha_finalizacion"
								styleId="fecha_finalizacion" size="35" readonly="true"
								styleClass="caja_textodisable"></html:text>

						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="prestamo.comentario" />
						</th>
						<td colspan="3">
							<html:textarea rows="5" property="comentario"
								styleId="comentario" cols="100"
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
							<bean:message key="requisicion.motivoRechazo" />
						</th>
						<td colspan="3">
							<html:textarea property="motivoRechazo" styleId="motivoRechazo"
								readonly="true" cols="100" rows="5"
								style="background-color:#CCCCCC"></html:textarea>

						</td>
					</tr>
				</table>



				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="requisicion.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="requisicion.mensajeError.error">
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
				action="${pageContext.request.contextPath}/reqRecursoAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Requisiciones de Recursos' view="compact" width="80%"
				tableId="solicitudes" form="formpadre" autoIncludeParameters="yes"
				scope="request">
				<ec:row highlightRow="true"
					onclick="executeTableClick('lnk${pageScope.mapf.codSolicitud}');">
					<ec:column property="codSolicitud" title='Solicitud No.' />
					<ec:column property="fecha_creacion"
						title='Fecha de Creaci&oacute;n' />
					<ec:column property="comentario" title='Descripci&oacute;n' />
					<ec:column property="estadoDes" title='Estado' />
					<ec:column property="detRequisicionLink" title='Acci&oacute;n'>
									${pageScope.mapf.detRequisicionLink}
				 <input type="hidden"
							onclick="tableClick('${pageScope.mapf.codSolicitud}','${pageScope.mapf.estadoDes}','${pageScope.mapf.codUnidad}',
				                     '${pageScope.mapf.unidadDes}'  ,'${pageScope.mapf.codBodega}','${pageScope.mapf.bodegaDes}',
				                     '${pageScope.mapf.tipoSolicitud}','${pageScope.mapf.comentario}','${pageScope.mapf.estado}','${pageScope.mapf.fecha_creacion}'
				                     ,'${pageScope.mapf.fecha_anulacion}','${pageScope.mapf.fecha_finalizacion}','${pageScope.mapf.fecha_aprobacion}','${pageScope.mapf.motivoRechazo}'
				                     ,'${pageScope.mapf.motivoAnulacion}','${pageScope.mapf.bodegaEditable}');"
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
			agregarPaginacion("solicitudes");
			
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


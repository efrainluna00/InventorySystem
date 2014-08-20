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
	      		var formulario= document.getElementById('formularioDetDesc');
            var href = formulario.action;                        		
                    
            if(href.indexOf('?') >0 ){
              href= href.substring(0,href.indexOf('?'));
            }
            href+='?accion='+accion+'&';				
            formulario.action= href;
                          
            formulario.submit();					
	      	}
    	  	      	
			function tableClick(codActivo,nombreRecurso,codRecurso,correlativo,ubicacion){
			//alert(jEstado);
			$(".error").hide();	
			limpiarMensajes();
			document.getElementById('codActivo').value = codActivo;
			document.getElementById('nombreRecurso').value = nombreRecurso;
			document.getElementById('codRecurso').value = codRecurso;				
			document.getElementById('correlativo').value = correlativo;
			$("#ubicacion").val(ubicacion);
			habilitar();
			if(jEstado!='G')
				deshabilitar();
			}
			
			function habilitar(){
			if(jEstado=='G')
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				else
				$('#accionInsert').SlideInDown('slow');
			}
			
			function deshabilitar(){
				$("#nombreRecurso").attr("class","caja_textodisable");
				$("#mostrarRecursos").hide();
				$("input:submit").hide();
				$("#back").show();
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
	      	
	      	flag = "in";
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
	      	
	      		function limpiarMensajes(){
	      	$(".error").text("");
	      	}
	      	
	      	var jCodResponsable,s1,s2,jEstado,jCodSolicitud,jCodUnidad;
	      	jCodResponsable = '<%=session.getAttribute("user")%>';
	      	
	      
	      //	jEstado = $("#estado").val();
	      		//alert(jEstado);
	      	s1 = 1;
	      	s2 = "D";
	      	
	      	
    </script>
	</tiles:put>


	<tiles:put name="title" type="String">
		<bean:message key="detrecdesca.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="detrecdesca.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form styleId="formularioDetDesc"
			action="/detRecibirDescargaActivoAction.do">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="detsoldesca.numsolicitud" />
						</th>
						<td>

							<bean:write name="detRecibirDescargaActivoForm"
								property="codSol2" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:hidden property="codSol2" styleId="codSol2" />
							<html:hidden property="codBodega" styleId="codBodega" />
							<script type="text/javascript">
		             	jCodSolicitud = $("#codSolicitud").val();
		             </script>
							<html:hidden property="estado" styleId="estado"></html:hidden>
							<html:hidden property="correlativo" styleId="correlativo" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />

							<br>
						</td>


						<th style="text-align: left;">
							<bean:message key="detprestamo.solicitante" />
							:
						</th>
						<td>
							<bean:write name="detRecibirDescargaActivoForm"
								property="solicitante" />
							<html:hidden property="codSolicitante" styleId="codSolicitante" />
						</td>
					</tr>
					<tr>

						<th style="text-align: left;">
							<bean:message key="detprestamo.estado" />
							:
						</th>
						<td>
							<bean:write name="detRecibirDescargaActivoForm"
								property="estadoNombre" scope="request" />
							<html:hidden property="estadoNombre" styleId="estadoNombre" />
							<html:hidden property="estado" styleId="estado" />
							<html:hidden property="comentario" styleId="comentario" />

						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.comentario" />
							:
						</th>
						<td colspan="3">
							<logic:notEqual name="detRecibirDescargaActivoForm"
								property="comentario" value="null">
								<bean:write name="detRecibirDescargaActivoForm"
									property="comentario" scope="request" />
								<html:hidden property="comentario" styleId="comentario" />
							</logic:notEqual>

						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="resojefe.mensaje" />
							:
						</th>
						<td colspan="3">
							<logic:notEqual name="detRecibirDescargaActivoForm"
								property="motivoRechazo" value="null">
								<bean:write name="detRecibirDescargaActivoForm"
									property="motivoRechazo" scope="request" />
								<html:hidden property="motivoRechazo" styleId="motivoRechazo" />
							</logic:notEqual>

						</td>
					</tr>
				</table>



				<script> 
			       jEstado = $("#estado").val();
			      
			       </script>

				<table class="tableDefault" id="rellene">
					<tr>
						<th>
							<bean:message key="detrecibirdesc.comentarioTecnico" />
						</th>
						<td>
							<logic:equal name="detRecibirDescargaActivoForm"
								property="estado" value="A">
								<html:textarea property="comentarioTecnico"
									styleId="comentarioTecnico" cols="100" rows="3"
									style="background-color:LightGoldenRodYellow">
								</html:textarea>
							</logic:equal>
							<logic:equal name="detRecibirDescargaActivoForm"
								property="estado" value="F">
								<html:textarea property="comentarioTecnico"
									styleId="comentarioTecnico" cols="100" rows="3"
									style="background-color:#CCCCCC" readonly="true">
								</html:textarea>
							</logic:equal>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="detrecibirdesc.comentarioTecnico.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detrecibirdesc.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<br>
				<logic:equal name="detRecibirDescargaActivoForm" property="estado"
					value="A">
					<html:button property="accion" value="Recibir"
						onclick="enviar('Actualizar')">
					</html:button>
				</logic:equal>
				<html:submit property="accion" styleId="back">
					<bean:message key="opc.back" />
				</html:submit>


				<div id="accionInsert" class="accion">
					<html:submit property="accion" styleId="insert">
						<bean:message key="opc.insert" />
					</html:submit>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>


					<html:submit property="accion" styleId="enviarSolicitud"
						onclick="deshabilitar()">
						<bean:message key="opc.enviarSolicitud" />
					</html:submit>



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
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>
				</div>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detsoldesca.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detsoldesca.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>
				<br>
				<hr width="85%">
				<logic:equal parameter="accion" value='Actualizar'>
					<script type="text/javascript" language="javascript">
					habilitar();
				</script>
				</logic:equal>
				<logic:empty name="listaRecursosxSolicitud" scope="Request">
					<script type="text/javascript" language="javascript">
						$("#enviarSolicitud").hide();
					</script>
				</logic:empty>
				<logic:notEqual name="detRecibirDescargaActivoForm"
					property="estado" value="G">
					<script type="text/javascript" language="javascript">
						$("input:submit").hide();
						$("#back").show();
						</script>
				</logic:notEqual>




			</center>

			<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>
			<ec:table items="listDetDesc" var="mapf"
				action="${pageContext.request.contextPath}/detRecibirDescargaActivoAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Activos' view="compact" width="80%" tableId="detsoldescatab"
				autoIncludeParameters="yes" scope="request" form="formularioDetDesc">
				<ec:row highlightRow="true"
					onclick="tableClick('${pageScope.mapf.codActivo}','${pageScope.mapf.nombreRecurso}',
				'${pageScope.mapf.codRecurso}','${pageScope.mapf.correlativo}','${pageScope.mapf.ubicacion}');">
					<ec:column property="codActivo" title='C&oacute;digo de Activo' />
					<ec:column property="nombreRecurso" title='Nombre recurso' />
					<ec:column property="ubicacion" title='Ubicaci&oacute;n' />
				</ec:row>
			</ec:table>
		</html:form>



		<BR>
		<script type="text/javascript">
			agregarPaginacion("detsoldescatab");
				$("form[@id='formularioDetDesc'] textarea").keypress( function(e){
					if( $(this).attr("id")!= null && $(this).attr("onkeydown")== null )
						return caracteresValidos($(this),e);
				});
			
			$("form[@id='formularioDetDesc'] textarea").change(function(e){
				if( $(this).attr("id")!= null ){
					if(!cadenaValida($(this).val()))
						$(this).val("");
				}
				});
		</script>

	</tiles:put>

</tiles:insert>



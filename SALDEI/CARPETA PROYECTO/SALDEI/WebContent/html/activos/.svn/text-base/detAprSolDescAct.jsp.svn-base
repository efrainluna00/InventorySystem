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
	      	
	      	function tableClick(nombreRecurso,ubicacion,correlativo,categoria,subcat,serie,codActivo,propietario){
				$(".error").hide();			
				document.getElementById('codActivo').value = codActivo;
				document.getElementById('nombreRecurso').value = nombreRecurso;
				document.getElementById('ubicacion').value = ubicacion;
				document.getElementById('categoria').value = categoria;
				document.getElementById('subcat').value = subcat;
				document.getElementById('serie').value = serie;
				
				//habilitar();
			}
						
			function habilitar(){
				$('#accionRegresar').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
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
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="detAprDesc.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="detAprDesc.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<center>
			<html:form action="/detAprSolDescActAction.do" styleId="formulario">

				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.codSolicitud" />
							:
							<br>
						</th>
						<td>
							<bean:write name="detAprSolDescActForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<br>
						</td>

						<th style="text-align: left;">
							<bean:message key="detprestamo.solicitante" />
							:
							<br>
						</th>
						<td>
							<bean:write name="detAprSolDescActForm" property="solicitante"
								scope="request" />
							<html:hidden property="solicitante" styleId="solicitante" />
							<html:hidden property="codSolicitante" styleId="codSolicitante" />
							<br>
						</td>
					<tr>

						<th style="text-align: left;">
							<bean:message key="detprestamo.estado" />
							:
							<br>
						</th>
						<td>
							<bean:write name="detAprSolDescActForm" property="estadoNombre"
								scope="request" />
							<html:hidden property="estadoNombre" styleId="estadoNombre" />
							<html:hidden property="estado" styleId="estado" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud"
								value="D" />
							<br>
						</td>
						<th style="text-align: left;">
							<bean:message key="detAprDesc.fecha_creacion" />
							:
							<br>
						</th>
						<td>
							<bean:write name="detAprSolDescActForm" property="fecha_creacion"
								scope="request" />
							<html:hidden property="fecha_creacion" styleId="fecha_creacion" />
							<br>
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.comentario" />
							:
						</th>
						<td colspan="3">
							<bean:write name="detAprSolDescActForm" property="comentario"
								scope="request" />
							<html:hidden property="comentario" styleId="comentario" />
						</td>
					</tr>
				</table>
				<br>


				<br>
				<logic:equal name="detAprSolDescActForm" property="estado" value="E">
					<table class="tableDefault">
						<tr>
							<th>
								<bean:message key="detAprDesc.destino" />
								:
							</th>
							<td>
								<html:text property="bodegaDes" styleId="bodegaDes" size="40"
									styleClass="caja_texto_obligatorio" readonly="true"
									title="Si va a rechazar la solicitud no necesita especificar la bodega"></html:text>
								<input type="button"
									onclick="abrirVentana('ldvAction.do?xmlArchivo=bodegas');"
									value="..." />
								<html:messages id="message" footer="errores.pie"
									header="errores.cabecera"
									property="detAprDesc.destino.requerido">
									<br>
									<span class="error"> <bean:write name="message"
											filter="false" /> </span>
								</html:messages>
								<html:hidden property="codBodega" styleId="codBodega" />
							</td>

						</tr>
						<tr>
							<th>
								<bean:message key="detAprDesc.resolucion" />
								:
							</th>
							<td colspan="3">
								<html:textarea property="comentarioResolucion"
									styleId="comentarioResolucion" cols="70"></html:textarea>
								<br>
							</td>

						</tr>

					</table>
				</logic:equal>

				<logic:notEqual name="detAprSolDescActForm" property="estado"
					value="E">
					<table class="tableDefault">
						<tr>
							<th>
								<bean:message key="detAprDesc.destino" />
								:
							</th>
							<td>
								<html:text property="bodegaDes" styleId="bodegaDes" size="34"
									styleClass="caja_textodisable" readonly="true"
									title="Si va a rechazar la solicitud no necesita especificar la bodega"></html:text>
								<html:hidden property="codBodega" styleId="codBodega" />

							</td>

						</tr>
						<tr>
							<th>
								<bean:message key="detprestamo.comentario" />
								:
							</th>
							<td colspan="3">
								<html:textarea property="comentarioResolucion"
									styleId="comentarioResolucion" cols="70" readonly="true"></html:textarea>
								<br>
							</td>

						</tr>

					</table>
				</logic:notEqual>




				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detAprDesc.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detAprDesc.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>
				<div id="accionRegresar" class="accion">

					<logic:equal name="detAprSolDescActForm" property="estado"
						value="E">
						<html:button property="accion" styleId="aprobarbtn"
							onclick="enviar('Aprobar');">
							<bean:message key="opc.aprobar" />
						</html:button>
						<html:button property="accion" styleId="rechazarbtn"
							onclick="enviar('Rechazar');">
							<bean:message key="opc.rechazar" />
						</html:button>
					</logic:equal>
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>
					<logic:equal name="detAprSolDescActForm" property="estado"
						value="A">
						<html:button property="accion" styleId="anularbtn"
							onclick="
					javascript:
					if(!confirm('¿Esta seguro que desea anular la solicitud?'))
						return false;
					else enviar('Anular');
					">
							<bean:message key="opc.anular" />
						</html:button>
					</logic:equal>
				</div>

				<logic:present name="AR" scope="request">
					<script>
				  		$('#aprobarbtn').hide();
					</script>
				</logic:present>
				<br />
				<hr width="85%">
				<br>
			</html:form>
			<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

			<ec:table items="listDetDesc" var="mapf"
				action="${pageContext.request.contextPath}/detAprSolDescActAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Activos Solicitados' view="compact" width="60%"
				tableId="aprSolDesc" autoIncludeParameters="yes" scope="request">
				<ec:row highlightRow="true"
					onclick="tableClick('${pageScope.mapf.nombreRecurso}','${pageScope.mapf.ubicacion}','${pageScope.mapf.correlativo}',
																'${pageScope.mapf.categoria}','${pageScope.mapf.subcat}','${pageScope.mapf.serie}',
																'${pageScope.mapf.codActivo}','${pageScope.mapf.propietario}');">
					<ec:column property="codActivo" title='C&oacute;digo de Activo'
						width="10%" />
					<ec:column property="nombreRecurso" title='Activo' />


				</ec:row>
			</ec:table>
		</center>

		<BR>
		<script type="text/javascript">
			agregarPaginacion("aprSolDesc");
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
		</script>

	</tiles:put>

</tiles:insert>






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
	      	
      	
	      	function tableClick(nombreRecurso,cantidad,correlativo,categoria,
	      	subcat,medidaDes,codActivo,propietario,recurso){
				  $(".error").hide();			
				//document.getElementById('codActivo').value = codActivo;
				document.getElementById('nombreRecurso').value = nombreRecurso;
				document.getElementById('cantidad').value = cantidad;
				document.getElementById('categoria').value = categoria;
				document.getElementById('subcat').value = subcat;
				document.getElementById('medidaDes').value = medidaDes;
				$("#codRecurso").val(recurso);
				
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
	      	

			 	function enviarReporte(accion,codSolicitud){
	      	    var formulario= document.getElementById('reporte');
	            var href = formulario.action;
	            
	            $('#code').val(codSolicitud);
	                    
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
		<bean:message key="aprdetsolaba.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="aprdetsolaba.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/repSolAbasAction.do" styleId="reporte">
			<html:hidden property="codSolicitud" styleId="code" />

		</html:form>



		<html:form action="/detAprSolAbaAction.do" styleId="formulario">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="detAprSolAbaForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
						</td>

						<th style="text-align: left;">
							<bean:message key="detprestamo.solicitante" />
							:
						</th>
						<td>
							<bean:write name="detAprSolAbaForm" property="solicitante"
								scope="request" />
							<html:hidden property="solicitante" styleId="solicitante" />
							<html:hidden property="codSolicitante" styleId="codSolicitante" />
						</td>
					<tr>

						<th style="text-align: left;">
							<bean:message key="detprestamo.estado" />
							:
						</th>
						<td>
							<bean:write name="detAprSolAbaForm" property="estadoNombre"
								scope="request" />
							<html:hidden property="estadoNombre" styleId="estadoNombre" />
							<html:hidden property="estado" styleId="estado" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud"
								value="A" />
						</td>
						<th style="text-align: left;">
							<bean:message key="detAprDesc.fecha_creacion" />
							:
						</th>
						<td>
							<bean:write name="detAprSolAbaForm" property="fecha_creacion"
								scope="request" />
							<html:hidden property="fecha_creacion" styleId="fecha_creacion" />
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="aprdetsolaba.destino" />
							:
						</th>
						<td colspan="3">
							<bean:write name="detAprSolAbaForm" property="bodegaDes"
								scope="request" />
							<html:hidden property="bodegaDes" styleId="bodegaDes" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.comentario" />
							:
						</th>
						<td colspan="3">
							<logic:notEqual name="detAprSolAbaForm" property="comentario"
								value="null">
								<bean:write name="detAprSolAbaForm" property="comentario"
									scope="request" />
							</logic:notEqual>
							<html:hidden property="comentario" styleId="comentario" />
						</td>
					</tr>
				</table>

				<br>
				<logic:equal name="detAprSolAbaForm" property="estado" value="E">
					<table class="tableDefault">

						<tr>
							<th>
								<bean:message key="detAprDesc.resolucion" />
								:
							</th>
							<td colspan="3">
								<html:textarea property="comentarioResolucion"
									styleId="comentarioResolucion" cols="70"></html:textarea>
							</td>

						</tr>

					</table>
				</logic:equal>

				<logic:notEqual name="detAprSolAbaForm" property="estado" value="E">
					<table class="tableDefault">
						<tr>
							<th>
								<bean:message key="detAprDesc.resolucion" />
								:
								<br>
							</th>
							<td colspan="3">
								<html:textarea property="comentarioResolucion"
									styleId="comentarioResolucion" cols="70" readonly="true"></html:textarea>
								<br>
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
				<div id="accionRegresar" class="accion">

					<logic:equal name="detAprSolAbaForm" property="estado" value="E">
						<html:button property="accion" styleId="aprobarbtn"
							onclick="enviar('Aprobar');">
							<bean:message key="opc.aprobar" />
						</html:button>
						<html:button property="accion" styleId="rechazarbtn"
							onclick="enviar('Rechazar');">
							<bean:message key="opc.rechazar" />
						</html:button>
					</logic:equal>

					<logic:equal name="detAprSolAbaForm" property="estado" value="A">
						<html:button property="accion" styleId="anularbtn"
							onclick="enviar('Anular');">
							<bean:message key="opc.anular" />
						</html:button>
					</logic:equal>

					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>

					<logic:equal name="detAprSolAbaForm" property="estado" value="A">
						<html:button property="accion" styleId="imprimirbtn"
							onclick="enviarReporte('inicio',+document.getElementById('codSolicitud').value);">
					  Imprimir Reporte
					</html:button>
					</logic:equal>

				</div>


				<br />
				<hr width="85%">
				<br>

				<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

				<ec:table items="listAprDetAba" var="mapf" form="formulario"
					action="${pageContext.request.contextPath}/detAprSolAbaAction.do?accion=Find"
					imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
					title='Suministros Solicitados' view="compact" width="60%"
					tableId="suministros" autoIncludeParameters="yes" scope="request">
					<ec:row highlightRow="true"
						onclick="tableClick('${pageScope.mapf.nombreRecurso}','${pageScope.mapf.cantidad}','${pageScope.mapf.correlativo}',
																'${pageScope.mapf.categoria}','${pageScope.mapf.subcat}','${pageScope.mapf.medidaDes}',
																'${pageScope.mapf.codActivo}','${pageScope.mapf.propietario}',
																'${pageScope.mapf.codRecurso}');">
						<ec:column property="codRecurso" title='C&oacute;digo de Recurso' />
						<ec:column property="nombreRecurso" title='Recurso' />
						<ec:column property="cantidad" title='Cantidad' />



					</ec:row>
				</ec:table>
			</center>
		</html:form>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("suministros");
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






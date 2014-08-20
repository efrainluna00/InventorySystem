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
	      	
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="salSolSuministro.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="salSolSuministro.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/salSolSuministroAction.do" styleId="formulario">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="salSolSuministro.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="salSolSuministroForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
						</td>
						<th style="text-align: left;">
							<bean:message key="salSolSuministro.solicitante" />
							:
						</th>
						<td>
							<bean:write name="salSolSuministroForm" property="solicitante"
								scope="request" />
							<html:hidden property="codSolicitante" styleId="codSolicitante" />
						</td>
						<th style="text-align: left;">
							<bean:message key="salSolSuministro.estado" />
							:
						</th>
						<td>
							<bean:write name="salSolSuministroForm" property="estadoDesc" />
							<html:hidden property="estado" styleId="estado" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="salSolSuministro.unidad" />
							:
						</th>
						<td>
							<bean:write name="salSolSuministroForm" property="descripcion" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="salSolSuministro.comentario" />
							:
						</th>
						<td colspan="5">
							<bean:write name="salSolSuministroForm" property="comentario" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="salSolSuministro.resolucion" />
							:
						</th>
						<td colspan="5">
							<bean:write name="salSolSuministroForm" property="motivoRechazo" />
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="salSolSuministro.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="salSolSuministro.mensaje.fallo">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>
				<div id="accionInsert" class="accion">
					<html:button property="accion" styleId="insert"
						onclick="enviar('Agregar');">
						<bean:message key="opc.asignar" />
					</html:button>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
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

			<ec:table items="listSolDet" var="mapf"
				action="${pageContext.request.contextPath}/salSolSuministroAction.do?accion=Enviar"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Detalle de Solicitud de Suministro' view="compact"
				width="80%" tableId="detalleSolSuministro" form="formulario"
				autoIncludeParameters="yes" scope="request">
				<ec:row highlightRow="true">
					<ec:column property="codRecurso" title='C&oacute;digo' width="20%" />
					<ec:column property="nombreRecurso" title='Recurso' />
					<ec:column property="cantidad" title='Cantidad' width="15%"
						filterable="false" />
					<ec:column property="existencias" title='Existencias' width="15%"
						filterable="false" />
					<ec:column property="cantidadEntregada" title='Entregado'
						width="15%" filterable="false">
					${pageScope.mapf.cantidadEntregada}
					<html:hidden property="cantidadIngresar"
							value="${pageScope.mapf.codRecurso},${pageScope.mapf.cantidad},${pageScope.mapf.existencias},${pageScope.mapf.cantidadEntregada}"
							styleId="${pageScope.mapf.codRecurso}" />
					</ec:column>
				</ec:row>
			</ec:table>
		</html:form>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("detalleSolSuministro");
			
			if($('#estado').val() != 'A' && $('#estado').val() != 'H'){
				$('#insert').hide();
			}
			
		</script>
	</tiles:put>

</tiles:insert>





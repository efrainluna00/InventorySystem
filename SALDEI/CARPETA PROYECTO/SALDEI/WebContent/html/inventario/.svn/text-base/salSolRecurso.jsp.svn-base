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
    	function agregarSeries(){    		
    		var codigosRecursos =  $('#codigoRecurso').val();
    			codigosRecursos = codigosRecursos.split(",");    		
    		$('#series_'+codigosRecursos[0]).val(codigosRecursos[0]+','+$('#codActivo').val());
    		$('#seriesTxt_'+codigosRecursos[0]).val($('#numSerie').val());
    	
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
		<bean:message key="salSolRecurso.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="salSolRecurso.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/salSolRecursoAction.do" styleId="formulario">
			<center>
				<br />
				<table class="tableEncabezado" width="60%">
					<tr>
						<th style="text-align: left;">
							<bean:message key="salSolRecurso.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="salSolRecursoForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
						</td>
						<th style="text-align: left;">
							<bean:message key="salSolRecurso.solicitante" />
							:
						</th>
						<td>
							<bean:write name="salSolRecursoForm" property="solicitante"
								scope="request" />
							<html:hidden property="codSolicitante" styleId="codSolicitante" />
						</td>
						<th style="text-align: left;">
							<bean:message key="salSolRecurso.estado" />
							:
						</th>
						<td>
							<bean:write name="salSolRecursoForm" property="estadoDesc" />
							<html:hidden property="estado" styleId="estado" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="salSolRecurso.unidad" />
							:
						</th>
						<td>
							<bean:write name="salSolRecursoForm" property="descripcion" />
						</td>
						<th style="text-align: left;">
							<bean:message key="salSolRecurso.bodega" />
							:
						</th>
						<td>
							<html:hidden property="codBodega" styleId="codBodega" />
							<bean:write name="salSolRecursoForm" property="desBodega" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="salSolRecurso.comentario" />
							:
						</th>
						<td colspan="5">
							<bean:write name="salSolRecursoForm" property="comentario" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="salSolRecurso.resolucion" />
							:
						</th>
						<td colspan="5">
							<bean:write name="salSolRecursoForm" property="motivoRechazo" />
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="salSolRecurso.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="salSolRecurso.mensaje.fallo">
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
				</script>
			<input type="hidden" id="codActivo" onchange="agregarSeries();" />
			<input type="hidden" id="numSerie" />
			<input type="hidden" id="codigoRecurso" />
			<logic:equal name="salSolRecursoForm" property="tipoSolicitud"
				value="R">
				<ec:table items="listSolDet" var="mapf"
					action="${pageContext.request.contextPath}/salSolRecursoAction.do?accion=Enviar"
					imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
					title='Detalle de Solicitud de Recurso' view="compact" width="80%"
					tableId="detalleSolRecurso" form="formulario"
					autoIncludeParameters="yes" scope="request">
					<ec:row highlightRow="true">
						<ec:column property="codRecurso" title='C&oacute;digo' />
						<ec:column property="nombreRecurso" title='Recurso' />
						<ec:column property="cantidad" title='Cantidad' filterable="false" />
						<ec:column property="existencias" title='Existencias'
							filterable="false" />
						<ec:column property="cantidadEntregada" title='Entregado'
							filterable="false">
							${pageScope.mapf.cantidadEntregada}
							<html:hidden property="cantidadIngresar"
								value="${pageScope.mapf.codRecurso},${pageScope.mapf.cantidad},${pageScope.mapf.existencias},${pageScope.mapf.cantidadEntregada}"
								styleId="${pageScope.mapf.codRecurso}" />
						</ec:column>
						<ec:column property="seriesEntregadas" title='Series  Entregadas'
							filterable="false" />
						<ec:column property="seriesIngresadas"
							title='Series No Entregadas' width="15%" filterable="false">

							<table>
								<tr>
									<td>
										<input type="hidden" name="seriesIngresar"
											class="caja_texto_obligatorio" readonly="readonly"
											id="series_${pageScope.mapf.codRecurso}" />
										<textarea readonly="readonly" rows="2" cols="30"
											style="background-color: LightGoldenRodYellow;"
											id="seriesTxt_${pageScope.mapf.codRecurso}"> </textarea>
									</td>
									<td>
										<input type="button" id="btnseriesentregar" value="..."
											onclick="abrirVentana('ldvAction.do?xmlArchivo=series_activos&multiple=true&codBodega='+document.getElementById('codBodega').value+'&codRecurso='+${pageScope.mapf.codRecurso});" />
									</td>
								</tr>
							</table>

						</ec:column>
					</ec:row>
				</ec:table>
			</logic:equal>
			<logic:equal name="salSolRecursoForm" property="tipoSolicitud"
				value="C">
				<ec:table items="listSolDet" var="mapf"
					action="${pageContext.request.contextPath}/salSolRecursoAction.do?accion=Enviar"
					imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
					title='Detalle de Solicitud de Recurso' view="compact" width="80%"
					tableId="detalleSolRecurso" form="formulario"
					autoIncludeParameters="yes" scope="request">
					<ec:row highlightRow="true">
						<ec:column property="id.invRecurso.codRecurso"
							title='C&oacute;digo' />
						<ec:column property="id.invRecurso.nombre" title='Recurso' />
						<ec:column property="cantidad" title='Cantidad' width="10%"
							filterable="false" />
						<ec:column property="existencias" title='Existencias' width="10%"
							filterable="false" />
						<ec:column property="cantidadEntregada" title='Entregado'
							width="10%" filterable="false">
						${pageScope.mapf.cantidadEntregada}
						<html:hidden property="cantidadIngresar"
								value="${pageScope.mapf.id.invRecurso.codRecurso},${pageScope.mapf.cantidad},${pageScope.mapf.existencias},${pageScope.mapf.cantidadEntregada}"
								styleId="${pageScope.mapf.id.invRecurso.codRecurso}" />
						</ec:column>
						<ec:column property="seriesEntregadas" title='Series  Entregadas'
							width="15%" filterable="false" />
						<ec:column property="seriesIngresadas"
							title='Series No Entregadas' width="15%" filterable="false">
							<html:hidden property="seriesIngresadas"
								value="${pageScope.mapf.id.invRecurso.codRecurso},${pageScope.mapf.seriesIngresadas}"
								styleId="series${pageScope.mapf.id.invRecurso.codRecurso}" />						
						${pageScope.mapf.seriesIngresadas}						
						</ec:column>
					</ec:row>
				</ec:table>
			</logic:equal>
		</html:form>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("detalleSolRecurso");
			
			if($('#estado').val() != 'A' && $('#estado').val() != 'H' && $('#estado').val() != 'I'){
				$('#insert').hide();
				$("textarea").attr('style','background-color:gray');
				$("textarea").attr('readonly','true');
				$('#btnseriesentregar').hide();
			}
			
		</script>
	</tiles:put>

</tiles:insert>





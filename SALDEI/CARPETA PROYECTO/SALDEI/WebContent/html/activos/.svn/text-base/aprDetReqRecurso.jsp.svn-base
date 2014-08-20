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
	      	
			function tableClick(codRecurso,correlativo,nombreRecurso,cantidad){
			  $(".error").hide();	
				document.getElementById('codRecurso').value = codRecurso;
				document.getElementById('nombreRecurso').value = nombreRecurso;
				document.getElementById('cantidad').value = cantidad;
			    document.getElementById('correlativo').value = correlativo;	
			  	habilitar();
			}
			
			function habilitar(){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				$(".error").hide();	
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
	      	
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="aprDetReq.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="aprDetReq.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">


		<html:form action="/aprDetReqRecursoAction.do" styleId="formulario">
			<center>
				<br />



				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="aprDetReq.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="aprDetReqRecursoForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
							<html:hidden property="correlativo" styleId="correlativo" />
						</td>
						<th style="text-align: left;">
							<bean:message key="aprDetReq.estado" />
							:
						</th>
						<td>
							<bean:write name="aprDetReqRecursoForm" property="desEstado"
								scope="request" />
							<html:hidden property="desEstado" styleId="desEstado" />
							<html:hidden property="estado" styleId="estado" />
						</td>

					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="aprDetReq.bodega" />
							:
						</th>
						<td>
							<html:hidden property="codBodega" styleId="codBodega" />
							<bean:write name="aprDetReqRecursoForm" property="bodegaDes"
								scope="request" />
							<html:hidden property="bodegaDes" styleId="bodegaDes" />
						</td>
						<th style="text-align: left;">
							<bean:message key="aprDetReq.solicitante" />
							:
						</th>
						<td>
							<bean:write name="aprDetReqRecursoForm" property="solicitante"
								scope="request" />
							<html:hidden property="solicitante" styleId="solicitante" />

						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.comentario" />
							:
						</th>
						<td colspan="3">
							<bean:write name="aprDetReqRecursoForm" property="comentario2"
								scope="request" />

						</td>
					</tr>
				</table>
				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="requisicion.comentario" />
						</th>
						<td>
							<html:textarea property="motivoRechazo" styleId="motivoRechazo"
								cols="50"></html:textarea>

						</td>
					</tr>
				</table>

				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="aprDetReq.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<div id="accionRegresar" class="accion">

					<html:button property="accion" styleId="aprobarbtn"
						onclick="enviar('Aprobar');">
						<bean:message key="opc.aprobar" />
					</html:button>
					<html:button property="accion" styleId="rechazarbtn"
						onclick="enviar('Rechazar');">
						<bean:message key="opc.rechazar" />
					</html:button>

					<logic:equal name="aprDetReqRecursoForm" property="estado"
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
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>
				</div>
				<div id="accionUpdate" class="accion"
					style="display: none; width: 100%;">


					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>

				</div>
				<logic:notEqual name="aprDetReqRecursoForm" property="estado"
					value="E">
					<script>
						$('#aprobarbtn').hide();
						$('#rechazarbtn').hide();	
						$('#motivoRechazo').attr('style','background-color: #CCCCCC');
						$('#motivoRechazo').attr('readonly','true');						
					</script>
				</logic:notEqual>
				<br />
				<hr width="85%">
				<logic:equal parameter="accion" value='Actualizar'>
					<script type="text/javascript" language="javascript">
					habilitar();
				</script>
				</logic:equal>

			</center>


		</html:form>

		<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
		</script>

		<ec:table items="listDetRequisicion" var="mapf"
			action="${pageContext.request.contextPath}/aprDetReqRecursoAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Detalle de Recursos Requeridos' view="compact" width="80%"
			tableId="recursos" autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codRecurso}','${pageScope.mapf.correlativo}','${pageScope.mapf.nombreRecurso}','${pageScope.mapf.cantidad}');">
				<ec:column property="codRecurso" title='C&oacute;digo Recurso' />
				<ec:column property="nombreRecurso" title='Nombre' />
				<ec:column property="cantidad" title='Cantidad' />


			</ec:row>
		</ec:table>

		<BR>
		<script type="text/javascript">
			agregarPaginacion("recursos");
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






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
	      	
	      	      	
			function tableClick(nombreRecurso,ubicacion,correlativo,categoria,subcat,serie,codActivo,responsable){
				$(".error").hide();			
				document.getElementById('codActivo').value = codActivo;
				document.getElementById('nombreRecurso').value = nombreRecurso;
				document.getElementById('ubicacion').value = ubicacion;
				document.getElementById('categoria').value = categoria;
				document.getElementById('subcat').value = subcat;
				document.getElementById('serie').value = serie;
				
				habilitar();
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
		<bean:message key="arprestamo.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="arprestamo.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">


		<html:form action="/detAprSolPrestamoAction.do" styleId="formulario">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="detAprSolPrestamoForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
						</td>
						<th style="text-align: left;">
							<bean:message key="detprestamo.solicitante" />
							:
						</th>
						<td>
							<bean:write name="detAprSolPrestamoForm" property="solicitante"
								scope="request" />
							<html:hidden property="solicitante" styleId="solicitante" />
							<html:hidden property="codSolicitante" styleId="codSolicitante" />
							<html:hidden property="codUnidad" styleId="codUnidad" />
						</td>
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.estado" />
							:
						</th>
						<td>
							<bean:write name="detAprSolPrestamoForm" property="estadoNombre"
								scope="request" />
							<html:hidden property="estadoNombre" styleId="estadoNombre" />
							<html:hidden property="estado" styleId="estado" />
						</td>
						<th style="text-align: left;">
							<bean:message key="detprestamo.fecha_inicial" />
							:
						</th>
						<td>
							<bean:write name="detAprSolPrestamoForm" property="fecha_ini"
								scope="request" />
							<html:hidden property="fecha_ini" styleId="fecha_ini" />
						</td>
						<html:hidden property="tipoSolicitud" styleId="tipoSolicitud"
							value="P" />
						<th style="text-align: left;">
							<bean:message key="detprestamo.fecha_final" />
							:
						</th>
						<td>
							<bean:write name="detAprSolPrestamoForm" property="fecha_fin"
								scope="request" />
							<html:hidden property="fecha_fin" styleId="fecha_fin" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.comentario" />
							:
						</th>
						<td>
							<bean:write name="detAprSolPrestamoForm" property="comentario"
								scope="request" />
							<html:hidden property="comentario" styleId="comentario" />
						</td>
					</tr>
				</table>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="prestamo.comentario" />
						</th>
						<td colspan="3">
							<html:textarea property="motivoRechazo" styleId="motivoRechazo"
								cols="88">
							</html:textarea>
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="arprestamo.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="arprestamo.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
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
					<html:button property="accion" styleId="entregarbtn"
						onclick="enviar('Entregar');">
						<bean:message key="opc.entregar" />
					</html:button>
					<html:button property="accion" styleId="recibirbtn"
						onclick="enviar('Recibir');">
						<bean:message key="opc.recibir" />
					</html:button>
					<html:button property="accion" styleId="anularbtn"
						onclick="
					javascript:
					if(!confirm('�Esta seguro que desea anular la solicitud?'))
						return false;
					else enviar('Anular');
					">
						<bean:message key="opc.anular" />
					</html:button>
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

				<logic:notEqual name="detAprSolPrestamoForm" property="estado"
					value="E">
					<script>
						$('#aprobarbtn').hide();
						$('#rechazarbtn').hide();
						$('#recibirbtn').hide();
						$('#entregarbtn').hide();
						$('#anularbtn').hide();
					</script>
				</logic:notEqual>

				<logic:present name="DIREC" scope="request">
					<logic:equal name="detAprSolPrestamoForm" property="estado"
						value="P">
						<logic:equal name="DIREC" value="NO">
							<script>
						  	$('#aprobarbtn').show();
							$('#rechazarbtn').show();
						  	</script>
						</logic:equal>
						<logic:equal name="DIREC" value="OK">
							<script>
						  		$('#anularbtn').show();
						  	</script>
						</logic:equal>
					</logic:equal>
				</logic:present>
				<logic:equal name="detAprSolPrestamoForm" property="estado"
					value="E">
					<script>
						$('#recibirbtn').hide();
						$('#entregarbtn').hide();
						$('#anularbtn').hide();
					</script>
				</logic:equal>

				<logic:equal name="detAprSolPrestamoForm" property="estado"
					value="F">
					<script>						
						$('#entregarbtn').hide();
						$('#recibirbtn').hide();
						$('#anularbtn').hide();
					</script>
				</logic:equal>

				<logic:equal name="detAprSolPrestamoForm" property="estado"
					value="A">
					<logic:equal name="DIREC" value="NO">
						<script>
						$('#anularbtn').show();
						  	</script>
					</logic:equal>
					<logic:equal name="DIREC" value="OK">
						<script>
						$('#entregarbtn').show();
						$('#recibirbtn').hide();
						</script>
					</logic:equal>

					<script>						
						
					</script>
				</logic:equal>
				<logic:notEqual name="detAprSolPrestamoForm"
					property="codPropietario" value="${sessionScope.user}">
					<script>
				  		$('#entregarbtn').hide();
						$('#recibirbtn').hide();
					</script>
				</logic:notEqual>
				<logic:equal name="detAprSolPrestamoForm" property="estado"
					value="R">
					<script>						
						$('#aprobarbtn').hide();
						$('#rechazarbtn').hide();
						$('#anularbtn').hide();
					</script>
				</logic:equal>
				<logic:equal name="detAprSolPrestamoForm" property="estado"
					value="D">
					<script>
				  							
						$('#entregarbtn').hide();
						$('#recibirbtn').show();
						$('#anularbtn').hide();
					</script>
				</logic:equal>


				<logic:present name="AR" scope="request">
					<script>
				  		$('#aprobarbtn').hide();
					</script>
				</logic:present>
				<br />
				<hr width="85%">
				<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

				<ec:table items="listDetPrestamos" var="mapf"
					form="detAprSolPrestamoForm"
					action="${pageContext.request.contextPath}/detAprSolPrestamoAction.do?accion=Find"
					imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
					title='Recursos Solicitados' view="compact" width="60%"
					tableId="prestamos" autoIncludeParameters="yes" scope="request">
					<ec:row highlightRow="true"
						onclick="tableClick('${pageScope.mapf.nombreRecurso}','${pageScope.mapf.ubicacion}','${pageScope.mapf.correlativo}',
																'${pageScope.mapf.categoria}','${pageScope.mapf.subcat}','${pageScope.mapf.serie}',
																'${pageScope.mapf.codActivo}','${pageScope.mapf.propietario}');">
						<ec:column property="codActivo" title='C&oacute;digo Recurso'
							width="10%" />
						<ec:column property="nombreRecurso" title='Recurso' />
						<ec:column property="propietario" title='Responsable' width="10%" />

					</ec:row>
				</ec:table>
			</center>
		</html:form>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("prestamos");
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






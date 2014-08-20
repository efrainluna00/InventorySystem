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
	      	
			function tableClick(codRecurso, nombreRecurso,precioUnitario, cantidad, monto){
			  	$(".error").hide();	
				$('#codRecurso').val(codRecurso);
				$('#recursoDesc').val(nombreRecurso);
				$('#precioUnitario').val(precioUnitario);
				$('#cantidad').val(cantidad);
				$('#monto').val(monto);
				habilitar();
			}
			
			function habilitar(){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				$('#btnRecurso').hide();
				$('#recursoDesc').attr('class','caja_textodisable');
				$('#recursoDesc').attr('size','74');
				$('#codRecurso').attr('class','caja_textodisable');
				$('#codRecurso').attr('size','10');
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
	      	
	      	function agregarCantidad(codRecurso,valor){
	      		
	      		$('#'+codRecurso).val($('#hdCantidadIngresar'+codRecurso).val()+','+valor);
	      	}
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="ingSolAbas.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="ingSolAbas.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/ingAbasSuministroAction.do" styleId="formulario">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="ingSolAbas.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="ingAbasSuministroForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
						</td>
						<th style="text-align: left;">
							<bean:message key="ingSolAbas.solicitante" />
							:
						</th>
						<td>
							<bean:write name="ingAbasSuministroForm" property="solicitante"
								scope="request" />
							<html:hidden property="codSolicitante" styleId="codSolicitante" />
						</td>
						<th style="text-align: left;">
							<bean:message key="ingSolAbas.estado" />
							:
						</th>
						<td>
							<bean:write name="ingAbasSuministroForm" property="estadoDesc" />
							<html:hidden property="estado" styleId="estado" />
						</td>

					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="ingSolAbas.bodega" />
							:
						</th>
						<td>
							<html:hidden property="codBodega" styleId="codBodega" />
							<bean:write name="ingAbasSuministroForm" property="desBodega" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="ingSolAbas.comentario" />
							:
						</th>
						<td colspan="5">
							<bean:write name="ingAbasSuministroForm" property="comentario" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="ingSolAbas.resolucion" />
							:
						</th>
						<td colspan="5">
							<bean:write name="ingAbasSuministroForm" property="motivoRechazo" />
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="ingSolAbas.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="ingSolAbas.mensaje.fallo">
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
				action="${pageContext.request.contextPath}/ingAbasSuministroAction.do?accion=Enviar"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Detalle de la Solicitud' view="compact" width="80%"
				tableId="ingAbasSuministroForm" form="formulario"
				autoIncludeParameters="yes" scope="request">
				<ec:row highlightRow="true" onclick="">
					<ec:column property="codRecurso" title='C&oacute;digo' width="20%" />
					<ec:column property="nombreRecurso" title='Recurso' />
					<ec:column property="cantidad" title='Cantidad' width="15%"
						filterable="false" />
					<ec:column property="cantidadIngresada" title='Cantidad Ingresada'
						width="15%" filterable="false" />
					<ec:column property="ingresar" title='Cantidad a Ingresar'
						width="20%" filterable="false">
						<input type="hidden"
							id="hdCantidadIngresar${pageScope.mapf.codRecurso}"
							value="${pageScope.mapf.codRecurso},${pageScope.mapf.cantidad},${pageScope.mapf.cantidadIngresada}" />
						<html:hidden property="cantidadIngresar" value="${pageScope.mapf.codRecurso},${pageScope.mapf.cantidad},${pageScope.mapf.cantidadIngresada}"
							styleId="${pageScope.mapf.codRecurso}" />
						<html:text property="valoresIngresar"
							styleClass="caja_texto_obligatorio"
							onchange="agregarCantidad(${pageScope.mapf.codRecurso},this.value);"
							onkeydown="return soloEnteros(this, event);">
						</html:text>
						<html:messages id="message" footer="errores.pie"
							header="errores.cabecera"
							property="ingSolAbas.cantidad.requerido${pageScope.mapf.codRecurso}">
							<br>
							<span class="error"> <bean:write name="message"
									filter="false" /> </span>
						</html:messages>
					</ec:column>
				</ec:row>
			</ec:table>
		</html:form>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("ingAbasSuministroForm");
			
			if($('#estado').val() != 'A' && $('#estado').val() != 'H'){
				$('#insert').hide();
				$("input[@name='valoresIngresar']").attr('class','caja_textodisable');
				$("input[@name='valoresIngresar']").attr('readonly','true');
				$("input[@name='valoresIngresar']").attr('size','16');
			}
			
		</script>

	</tiles:put>

</tiles:insert>





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
	      	
			function tableClick(codRecurso, nombreRecurso,precioUnitario, cantidad){
				$('#codRecurso').val(codRecurso);
				$('#recursoDesc').val(nombreRecurso);
				$('#precioUnitario').val(precioUnitario);
				$('#cantidad').val(cantidad);
								
				habilitar();
			}
			
			function habilitar(){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				$('#btnRecurso').hide();
				$('#recursoDesc').attr('class','caja_textodisable');
				$('#recursoDesc').attr('size','56');
				$('#codRecurso').attr('class','caja_textodisable');
				$('#codRecurso').attr('size','7');
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
		<bean:message key="solCompraDet.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="solCompraDet.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/solicitudCompraDetAction.do">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="solCompraDet.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="solicitudCompraDetForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolcitud" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
						</td>
						<th style="text-align: left;">
							<bean:message key="solCompraDet.solicitante" />
							:
						</th>
						<td>
							<bean:write name="usuario" property="idUsuario" scope="session" />

						</td>
						<th style="text-align: left;">
							<bean:message key="solCompraDet.codCotizacion" />
							:
						</th>
						<td>
							<bean:write name="solicitudCompraDetForm"
								property="codCotizacion" scope="request" />
							<html:hidden property="codCotizacion" styleId="codCotizacion" />
						</td>
						<th style="text-align: left;">
							<bean:message key="solCompraDet" />
							:
						</th>
						<td>
							<bean:write name="solicitudCompraDetForm"
								property="nombreProveedor" scope="request" />
							<html:hidden property="codProveedor" styleId="codProveedor" />
						</td>

					</tr>
				</table>
				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="solCompraDet.recurso" />
						</th>
						<td colspan="3">
							<html:text property="codRecurso" styleId="codRecurso" size="10"
								styleClass="caja_texto_obligatorio">
							</html:text>
							<html:text property="nombreRecurso" styleId="recursoDesc"
								size="63" styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" id="btnRecurso"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=recursos');"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="cotizacionDet.recurso.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="cotizacionDet.precioUnitario" />
						</th>
						<td>
							<html:text property="precioUnitario" styleId="precioUnitario"
								size="30" styleClass="caja_texto_obligatorio"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="cotizacionDet.precioUnitario.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>

						<th>
							<bean:message key="cotizacionDet.cantidad" />
						</th>
						<td>
							<html:text property="cantidad" styleId="cantidad" size="30"
								styleClass="caja_texto_obligatorio"></html:text>
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="cotizacionDet.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
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
				<div id="accionUpdate" class="accion"
					style="display: none; width: 100%;">
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

		<ec:table items="listCotDet" var="mapf"
			action="${pageContext.request.contextPath}/cotizacionDetAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Detalla de Cotizacion' view="compact" width="80%"
			tableId="cotizacionesDet" autoIncludeParameters="true"
			scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.id.invRecurso.codRecurso}','${pageScope.mapf.id.invRecurso.nombre}',
																'${pageScope.mapf.precioUnitario}','${pageScope.mapf.cantidad}');">
				<ec:column property="id.invRecurso.nombre" title='Recurso'
					width="20%" />
				<ec:column property="precioUnitario" title='Precio Unitario'
					width="20%" />
				<ec:column property="cantidad" title='Cantidad' width="20%" />
			</ec:row>
		</ec:table>
		<BR>
	</tiles:put>

</tiles:insert>





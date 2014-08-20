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
			
	    function cerrarVentana(){
	    		window.close();
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
	      	
	      	      	
			function tableClick(nombreRecurso,ubicacion,categoria,subcat,serie,codActivo,responsable){
				var est= document.getElementById('estado2').value;
				$(".error").hide();					
				document.getElementById('codActivo').value = codActivo;
				document.getElementById('nombreRecurso').value = nombreRecurso;
				document.getElementById('ubicacion').value = ubicacion;
				document.getElementById('categoria').value = categoria;
				document.getElementById('subcat').value = subcat;
				document.getElementById('serie').value = serie;
				document.getElementById('propietario2').value = responsable;
				if(est != "G")
					$('#insert').hide();
				habilitar();
			}
			
			function tableClick2(nombreRecurso,ubicacion,correlativo,categoria,subcat,serie,codActivo,responsable){
				var est= document.getElementById('estado2').value;
				document.getElementById('codActivo').value = codActivo;
				document.getElementById('nombreRecurso').value = nombreRecurso;
				document.getElementById('ubicacion').value = ubicacion;
				document.getElementById('correlativo').value = correlativo;
				document.getElementById('categoria').value = categoria;
				document.getElementById('subcat').value = subcat;
				document.getElementById('serie').value = serie;
				document.getElementById('propietario2').value = responsable;
				if(est != "G")
					$('#delete').hide();				
				habilitar2();
			}
			
			function habilitar(){
				$('#accionRegresar').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
			}
			
			function habilitar2(){
				$('#accionRegresar').SlideOutDown('fast',$('#accionUpdate2').SlideInDown('slow'));
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
		<bean:message key="prestamo.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="prestamo.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/detalleSolPrestamoAction.do" styleId="formulario">
			<center>

				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="detalleSolPrestamoForm"
								property="codSolicitud2" scope="request" />
							<html:hidden property="codSolicitud2" styleId="codSolicitud2" />
						</td>

						<th style="text-align: left;">
							<bean:message key="detprestamo.solicitante" />
							:
						</th>
						<td>
							<bean:write name="usuario" property="primerNom" scope="session"></bean:write>
							<bean:write name="usuario" property="primerApe" scope="session"></bean:write>
							<html:hidden property="codSolicitante2" styleId="codSolicitante2" />
						</td>
						<th style="text-align: left;">
							<bean:message key="detprestamo.solicitara" />
							:
						</th>
						<td>
							<bean:write name="detalleSolPrestamoForm" property="propietario2"
								scope="request" />
							<html:hidden property="codPropietario2" styleId="codPropietario2" />
						</td>
					<tr>

						<th style="text-align: left;">
							<bean:message key="detprestamo.estado" />
							:
						</th>
						<td>
							<bean:write name="detalleSolPrestamoForm" property="estadoNombre"
								scope="request" />
							<html:hidden property="estadoNombre" styleId="estadoNombre" />
							<html:hidden property="estado2" styleId="estado2" />
							<html:hidden property="codUnidad2" styleId="codUnidad2" />
						</td>
						<th style="text-align: left;">
							<bean:message key="detprestamo.fecha_inicial" />
							:
						</th>
						<td>
							<bean:write name="detalleSolPrestamoForm" property="fecha_ini2"
								scope="request" />
							<html:hidden property="fecha_ini2" styleId="fecha_ini2" />
						</td>
						<html:hidden property="tipoSolicitud" styleId="tipoSolicitud"
							value="P" />

						<th style="text-align: left;">
							<bean:message key="detprestamo.fecha_final" />
							:
						</th>
						<td>
							<bean:write name="detalleSolPrestamoForm" property="fecha_fin2"
								scope="request" />
							<html:hidden property="fecha_fin2" styleId="fecha_fin2" />
						</td>

					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.comentario" />
							:
						</th>
						<td>
							<bean:write name="detalleSolPrestamoForm" property="comentario2"
								scope="request" />
							<html:hidden property="comentario2" styleId="comentario2" />
						</td>
					</tr>
				</table>
				<table class="tableDefault">

					<tr>
						<th>
							<bean:message key="detprestamo.activo" />
						</th>
						<td>
							<html:hidden property="correlativo" styleId="correlativo"></html:hidden>
							<html:text property="codActivo" styleId="codActivo" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="detpresupuesto.cuenta.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="detprestamo.recurso" />
						</th>
						<td>
							<html:text property="nombreRecurso" styleId="nombreRecurso"
								size="24" styleClass="caja_textodisable" readonly="true"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="detprestamo.monto.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="detprestamo.ubicacion" />
						</th>
						<td>
							<html:text property="ubicacion" styleId="ubicacion" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="detprestamo.monto.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="detprestamo.cat" />
						</th>
						<td>
							<html:text property="categoria" styleId="categoria" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="detprestamo.monto.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="detprestamo.subcat" />
						</th>
						<td>
							<html:text property="subcat" styleId="subcat" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="detprestamo.monto.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="detprestamo.serie" />
						</th>
						<td>
							<html:text property="serie" styleId="serie" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="detprestamo.monto.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
				</table>

				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detprestamo.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detprestamo.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>
				<div id="accionRegresar" class="accion">

					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>

					<html:button property="accion" styleId="enviarbtn"
						onclick="enviar('Enviar');">
						<bean:message key="opc.enviarSolicitud" />
					</html:button>

				</div>
				<div id="accionUpdate" class="accion"
					style="display: none; width: 100%;">

					<html:submit property="accion" styleId="insert">
						<bean:message key="opc.insert" />
					</html:submit>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>


				</div>

				<div id="accionUpdate2" class="accion"
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

				<table width="90%">
					<tr>
						<td>
							<table class="tableDefault" align="left">

								<tr>
									<th>
										<bean:message key="detprestamo.responsable" />
									</th>
									<td>
										<html:hidden property="codUnidad2" styleId="codUnidad2"></html:hidden>
										<html:hidden property="codPropietario2"
											styleId="codPropietario2"></html:hidden>
										<html:hidden property="tipoSolicitud" styleId="tipoSolicitud"
											value="P"></html:hidden>
										<html:text property="propietario2" styleId="propietario2"
											size="30" styleClass="caja_texto_obligatorio" readonly="true"
											onchange="enviar('FindUp')"></html:text>
										<logic:notPresent name="listDetPrestamos" scope="request">
											<input id="btnCategoria" type="button"
												onclick="abrirVentana('ldvAction.do?xmlArchivo=activos2&codSolicitante='+document.getElementById('codSolicitante2').value);"
												value="..." />
											<script>
											$('#enviarbtn').hide();										
										</script>
										</logic:notPresent>
										<logic:notEqual name="detalleSolPrestamoForm"
											property="estado2" value="G">
											<script>
											$('#enviarbtn').hide();										
										</script>
										</logic:notEqual>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<logic:equal parameter="accion" value='Actualizar'>
					<script type="text/javascript" language="javascript">
					habilitar();
				</script>
				</logic:equal>

			</center>

			<table>


				<tr>
					<td>
						<ec:table items="listActivos" var="mapf"
							form="detalleSolPrestamoForm"
							action="${pageContext.request.contextPath}/detalleSolPrestamoAction.do?accion=Find"
							imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
							title='Activos Disponibles' view="compact" width="80%"
							tableId="recursos" autoIncludeParameters="yes" scope="request">
							<ec:row highlightRow="true"
								onclick="tableClick('${pageScope.mapf.nombreRecurso}','${pageScope.mapf.ubicacion}',
																'${pageScope.mapf.categoria}','${pageScope.mapf.subcat}','${pageScope.mapf.serie}',
																'${pageScope.mapf.codActivo}','${pageScope.mapf.propietario}');">
								<ec:column property="codActivo" title='C&oacute;digo Recurso' />
								<ec:column property="nombreRecurso" title='Recurso' />
								<ec:column property="ubicacion" title='Ubicaci&oacute;n' />
							</ec:row>
						</ec:table>
					</td>
					<td>
						<ec:table items="listDetPrestamos" var="mapf"
							form="detalleSolPrestamoForm"
							action="${pageContext.request.contextPath}/detalleSolPrestamoAction.do?accion=Find"
							imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
							title='Activos a Prestar' view="compact" width="80%"
							tableId="prestamos" autoIncludeParameters="yes" scope="request">
							<ec:row highlightRow="true"
								onclick="tableClick2('${pageScope.mapf.nombreRecurso}','${pageScope.mapf.ubicacion}','${pageScope.mapf.correlativo}',
																'${pageScope.mapf.categoria}','${pageScope.mapf.subcat}','${pageScope.mapf.serie}',
																'${pageScope.mapf.codActivo}','${pageScope.mapf.propietario}');">
								<ec:column property="codActivo" title='C&oacute;digo Recurso' />
								<ec:column property="nombreRecurso" title='Recurso' />


							</ec:row>
						</ec:table>

					</td>
				</tr>
			</table>

			<input type="hidden" name="${requestScope.nombreGrid}_p"
				id="${requestScope.nombreGrid}_p"
				value="${requestScope.numeroPagina}" />
			<input type="hidden" name="${requestScope.nombreGrid}_crd"
				id="${requestScope.nombreGrid}_crd"
				value="${requestScope.cantidadPagina}" />
			<input type="hidden" name="nombreGrid"
				value="${requestScope.nombreGrid}" />
			<input type="hidden" name="numeroPagina"
				value="${requestScope.numeroPagina}" />
			<input type="hidden" name="cantidadPagina"
				value="${requestScope.cantidadPagina}" />

		</html:form>

		<script type="text/javascript">
			agregarPaginacion("prestamos");
			agregarPaginacion("recursos");
		</script>

		<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>
	</tiles:put>
</tiles:insert>




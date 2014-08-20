<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>


<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">

    	  	      	
			function tableClick(codActivo,nombreRecurso,codRecurso,correlativo,ubicacion){
			//alert(jEstado);
			$(".error").hide();	
			limpiarMensajes();
			document.getElementById('codActivo').value = codActivo;
			document.getElementById('nombreRecurso').value = nombreRecurso;
			document.getElementById('codRecurso').value = codRecurso;				
			document.getElementById('correlativo').value = correlativo;
			$("#ubicacion").val(ubicacion);
			$("#mostrarRecursos").hide();
			$("#nombreRecurso").attr("class","caja_textodisable");
			$("#nombreRecurso").attr("size","40");
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
				$("#nombreRecurso").attr("size","40");
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
		<bean:message key="detsoldesca.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="detsoldesca.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form styleId="formularioDetDesc"
			action="/detalleSolDescActAction.do">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="detsoldesca.numsolicitud" />
						</th>
						<td>

							<bean:write name="detalleSolDescActForm" property="codSol2" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:hidden property="codSol2" styleId="codSol2" />
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
							<bean:write name="usuario" property="primerNom" scope="session"></bean:write>
							<bean:write name="usuario" property="primerApe" scope="session"></bean:write>
							<html:hidden property="codSolicitante" styleId="codSolicitante" />
						</td>
					</tr>
					<tr>

						<th style="text-align: left;">
							<bean:message key="detprestamo.estado" />
							:
						</th>
						<td>
							<bean:write name="detalleSolDescActForm" property="estadoNombre"
								scope="request" />
							<html:hidden property="estadoNombre" styleId="estadoNombre" />
							<html:hidden property="estado" styleId="estado" />
							<html:hidden property="comentario" styleId="comentario" />

						</td>

						<th style="text-align: left;">
							<bean:message key="detprestamo.comentario" />
							:
						</th>
						<td>
							<bean:write name="detalleSolDescActForm" property="comentario"
								scope="request" />
							<html:hidden property="comentario" styleId="comentario" />
						</td>
					</tr>
				</table>



				<script> 
			       jEstado = $("#estado").val();
			      
			       </script>

				<table class="tableDefault" id="rellene">
					<tr>
						<th>
							<bean:message key="detsoldesca.codactivo" />
						</th>
						<td>
							<html:text property="codActivo" styleId="codActivo" size="40"
								styleClass="caja_textodisable" readonly="true"
								value="${detalleSolDescActForm.codActivo}"></html:text>



							<br>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="detsoldesca.activo" />
						</th>
						<td>
							<html:hidden property="codRecurso" styleId="codRecurso"></html:hidden>
							<logic:equal name="detalleSolDescActForm" property="estado"
								value="G">
								<html:text property="nombreRecurso" styleId="nombreRecurso"
									styleClass="caja_texto_obligatorio" readonly="true" size="50"></html:text>
								<input id="mostrarRecursos" type="button"
									onclick="abrirVentana('ldvAction.do?xmlArchivo=solDescActRecursos&codResponsable='+jCodResponsable+'&codSolicitud='+jCodSolicitud);"
									value="..." />
								<html:messages id="message" footer="errores.pie"
									header="errores.cabecera"
									property="detsoldesca.activo.requerido">
									<br>
									<span class="error"> <bean:write name="message"
											filter="false" /> </span>
								</html:messages>
							</logic:equal>
							<logic:notEqual name="detalleSolDescActForm" property="estado"
								value="G">
								<html:text property="nombreRecurso" styleId="nombreRecurso"
									styleClass="caja_textodisable" readonly="true" size="40"></html:text>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="detsoldesca.ubicacion" />
						</th>
						<td>
							<html:text property="ubicacion" styleId="ubicacion"
								styleClass="caja_textodisable" readonly="true" size="40"></html:text>

						</td>
					</tr>
				</table>

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

					<html:submit property="accion" styleId="enviarSolicitud"
						onclick="deshabilitar()">
						<bean:message key="opc.enviarSolicitud" />
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
				<br />
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
				<logic:notEqual name="detalleSolDescActForm" property="estado"
					value="G">
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

			<ec:table items="listaRecursosxSolicitud" var="mapf"
				action="${pageContext.request.contextPath}/detalleSolDescActAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Activos' view="compact" width="80%" tableId="detsoldescatab"
				autoIncludeParameters="yes" scope="request" form="formularioDetDesc">
				<ec:row highlightRow="true"
					onclick="tableClick('${pageScope.mapf.codActivo}','${pageScope.mapf.nombreRecurso}',
				'${pageScope.mapf.codRecurso}','${pageScope.mapf.correlativo}','${pageScope.mapf.ubicacion}');">
					<ec:column property="codActivo" title='C&oacute;digo de Activo' />
					<ec:column property="nombreRecurso" title='Nombre Recurso' />
					<ec:column property="ubicacion" title='Ubicaci&oacute;n' />
				</ec:row>
			</ec:table>

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



		<BR>
		<script type="text/javascript">
			agregarPaginacion("detsoldescatab");
		</script>
	</tiles:put>

</tiles:insert>



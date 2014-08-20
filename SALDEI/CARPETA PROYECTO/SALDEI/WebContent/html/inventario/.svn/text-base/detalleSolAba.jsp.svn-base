<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>



<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
    
    	  	      	
			function tableClick(codRecurso,nombreRecurso,cantidad,correlativo){			
				  $(".error").hide();				
				document.getElementById('codRecurso').value = codRecurso;
				document.getElementById('nombreRecurso').value = nombreRecurso;
				document.getElementById('cantidad').value = cantidad;							
				document.getElementById('correlativo').value = correlativo;
				$("#nombreRecurso").attr("class","caja_textodisable");
				$("#nombreRecurso").attr("size","32");
				$("#mostrarRecursos").hide();
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
				$("#mostrarRecursos").hide();
				$("input:submit").hide();
				$("#back").show();
				$("#cancel").show();
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
		<bean:message key="detsolaba.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="detsolaba.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form styleId="formularioDetDesc"
			action="/detalleSolAbaAction.do">
			<center>
				<br />

				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="detsoldesca.numsolicitud" />
						</th>
						<td>

							<bean:write name="detalleSolAbaForm" property="codSol2" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:hidden property="codBodega" styleId="codBodega" />
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
							<bean:write name="detalleSolAbaForm" property="estadoNombre"
								scope="request" />
							<html:hidden property="estadoNombre" styleId="estadoNombre" />
							<html:hidden property="estado" styleId="estado" />
							<html:hidden property="comentario" styleId="comentario" />

						</td>

						<th style="text-align: left;">
							<bean:message key="detsolaba.bodegaDes" />
							:
						</th>
						<td>
							<bean:write name="detalleSolAbaForm" property="bodegaDes"
								scope="request" />
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.comentario" />
							:
						</th>
						<td colspan="3">
							<logic:notEqual name="detalleSolAbaForm" property="comentario"
								value="null">
								<bean:write name="detalleSolAbaForm" property="comentario"
									scope="request" />
							</logic:notEqual>
							<html:hidden property="comentario" styleId="comentario" />
						</td>
					</tr>
				</table>
				<script type="text/javascript" language="javascript"> 
			       jEstado = $("#estado").val();
			       </script>
				<br>

				<table class="tableDefault" id="rellene">
					<tr>
						<th>
							<bean:message key="detsolaba.codRecurso" />
						</th>
						<td>
							<html:text property="codRecurso" styleId="codRecurso" size="32"
								styleClass="caja_textodisable" readonly="true"
								value="${detalleSolAbaForm.codRecurso}"></html:text>
						</td>
					</tr>
					<logic:equal name="detalleSolAbaForm" property="estado" value="G">
						<tr>
							<th>
								<bean:message key="detsolaba.recurso" />
							</th>
							<td>
								<html:text property="nombreRecurso" styleId="nombreRecurso"
									styleClass="caja_texto_obligatorio" readonly="true" size="40"></html:text>
								<input id="mostrarRecursos" type="button"
									onclick="abrirVentana('ldvAction.do?xmlArchivo=solabaRecursos&codSolicitud='+jCodSolicitud
									+'&codBodega='+$('#codBodega').val(), 900);"
									value="..." />
								<html:messages id="message" footer="errores.pie"
									header="errores.cabecera"
									property="detsolaba.recurso.requerido">
									<br>
									<span class="error"> <bean:write name="message"
											filter="false" /> </span>
								</html:messages>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="detsolaba.cantidad" />
							</th>
							<td>
								<html:text property="cantidad" styleId="cantidad" size="40"
									styleClass="caja_texto_obligatorio" maxlength="10" value=""
									onkeydown="return soloEnteros(this,event)"></html:text>
								<html:messages id="message" footer="errores.pie"
									header="errores.cabecera"
									property="detsolaba.cantidad.requerido">

									<br>
									<span class="error"> <bean:write name="message"
											filter="false" /> </span>
								</html:messages>
								<html:messages id="message" footer="errores.pie"
									header="errores.cabecera" property="detsolaba.cantidad.valida">

									<br>
									<span class="error"> <bean:write name="message"
											filter="false" /> </span>
								</html:messages>
							</td>
						</tr>
					</logic:equal>
					<logic:notEqual name="detalleSolAbaForm" property="estado"
						value="G">
						<tr>
							<th>
								<bean:message key="detsolaba.recurso" />
							</th>
							<td>
								<html:text property="nombreRecurso" styleId="nombreRecurso"
									styleClass="caja_textodisable" readonly="true" size="32"></html:text>
							</td>
						<tr>
							<th>
								<bean:message key="detsolaba.cantidad" />
							</th>
							<td>
								<html:text property="cantidad" styleId="cantidad" size="32"
									styleClass="caja_textodisable" maxlength="10" value=""></html:text>
							</td>
						</tr>
					</logic:notEqual>

				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detsolaba.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detsolaba.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>




				<div id="accionInsert" class="accion">
					<logic:equal name="detalleSolAbaForm" property="estado" value="G">
						<html:submit property="accion" styleId="insert">
							<bean:message key="opc.insert" />
						</html:submit>
					</logic:equal>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>

					<logic:equal name="detalleSolAbaForm" property="estado" value="G">
						<html:submit property="accion" styleId="enviarSolicitud"
							onclick="deshabilitar()">
							<bean:message key="opc.enviarSolicitud" />
						</html:submit>
					</logic:equal>


				</div>
				<div id="accionUpdate" class="accion"
					style="display: none; width: 100%;">
					<html:submit property="accion" styleId="update">
						<bean:message key="opc.update" />
					</html:submit>
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
				<logic:empty name="listaRecursosxSolicitud" scope="Request">
					<script type="text/javascript" language="javascript">
						$("#enviarSolicitud").hide();
					</script>
				</logic:empty>
				<logic:equal name="detalleSolAbaForm" property="estado" value="E">
					<script type="text/javascript" language="javascript">
						$("#mostrarRecursos").hide();	
						$("input:submit").hide();
						$("#back").show();
						$("#cancel").show();
						</script>
				</logic:equal>




			</center>
			<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

			<ec:table items="listaRecursosxSolicitud" var="mapf"
				action="${pageContext.request.contextPath}/detalleSolAbaAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Suministros' view="compact" width="80%" tableId="suministros"
				autoIncludeParameters="yes" scope="request" form="formularioDetDesc">
				<ec:row highlightRow="true"
					onclick="tableClick('${pageScope.mapf.codRecurso}','${pageScope.mapf.nombreRecurso}',
				'${pageScope.mapf.cantidad}','${pageScope.mapf.correlativo}');">
					<ec:column property="codRecurso"
						title='C&oacute;digo de Suministro' />
					<ec:column property="nombreRecurso" title='Nombre Suministro' />
					<ec:column property="categoria" title='Categor&iacute;a' />
					<ec:column property="subcategoria" title='Sub categor&iacute;a' />
					<ec:column property="cantidad" title='cantidad' />
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

		<script type="text/javascript">
			agregarPaginacion("suministros");
			$("form[@id='formularioDetDesc'] input[@type='text']").keypress( function(e){
					if( $(this).attr("id")!= null && $(this).attr("onkeydown")== null )
						return caracteresValidos($(this),e);
				});
			
			$("form[@id='formularioDetDesc'] input[@type='text']").change(function(e){
				if( $(this).attr("id")!= null ){
					if(!cadenaValida($(this).val()))
						$(this).val("");
				}
				});
		</script>


		<BR>
	</tiles:put>

</tiles:insert>



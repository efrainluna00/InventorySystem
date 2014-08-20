<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>




<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
    	
    	    var jCodSolicitante; 	
    	     	
			function habilitar(){
				$('#accionInsert').SlideInDown('slow');
				
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
	      	
	 

	      	  	function enviarSolAba(accion, tipoSolicitud, codSolicitud,correlativo,codSolicitante,
	      	  	estado,codSol2,comentario,codBodega,estadoNombre,bodegaDes){
	      		var formulario= document.getElementById('formularioDetalleSolDescAct');
	            var href = formulario.action;
	            
	            $('#sTipoSolicitud').val(tipoSolicitud);
	            $('#sCodSolicitud').val(codSolicitud);	
	            $('#sCorrelativo').val(correlativo);				
	            $('#sCodSolicitante').val(codSolicitante);
	            $('#sEstado').val(estado);
	            $('#sCodSol2').val(codSol2);
	            $('#sComentario').val(comentario);
				$('#sCodBodega').val(codBodega);
				$('#sEstadoNombre').val(estadoNombre);	  
				$('#sBodegaDes').val(bodegaDes);	 
				
				// para que cuando regrese del formulario hijo conserve los valores seleccionados
		        $("#numeroPagina").val($("input[@name='solaAba_p']").val());
		        $("#cantidadPagina").val($("input[@name='solaAba_crd']").val());	        
		        $("#nombreGrid").val('solaAba'); 
				       	                    
	            if(href.indexOf('?') >0 ){
	              href= href.substring(0,href.indexOf('?'));
	            }
	            href+='?accion='+accion;				
	            formulario.action= href;
	                          
	            formulario.submit();					
	      	}
	      	
	   
	      	function tableClick(codSolicitud,estado,fecha_aprobacion,fecha_finalizacion,
	      	fecha_anulacion,comentario,resolucion,fecha_creacion,codBodega,bodegaDes,tipoSolicitud
	      	,modificarBodega){
	      	  $(".error").hide();	
			$('input[@id="codSolicitud"]').val(codSolicitud);
			$('input[@id="codSol2"]').val(codSolicitud);
			$('input[@id="estado"]').val(estado);
			document.getElementById('fecha_aprobacion').value = fecha_aprobacion;
			document.getElementById('fecha_finalizacion').value = fecha_finalizacion;
			//document.getElementById('fecha_anulacion').value = fecha_anulacion;
			$('textarea[@id="comentario"]').val(comentario);
			$("#resolucion").text(resolucion);
			$("#fecha_creacion").val(fecha_creacion);
			$("input[@id='codBodega']").val(codBodega);
			$("input[@id='bodegaDes']").val(bodegaDes);
			$("#tipoSolicitud").val(tipoSolicitud);
			
			habilitar();
				if(estado=='G'){
			$("#delete").show();
			$("#cancel").show();
			$("#update").show();
			$("#insert").hide();
			$("#selectUni").hide();
			//$("#descripcion").attr("styleClass","caja_textodisable");
			//$("#descripcion").attr("size","24");
				if(estado!='G')
			$('textarea[@id="comentario"]').attr("readonly","true");
			$('textarea[@id="comentario"]').attr("styleClass","caja_textodisable");
				bodegaModi(modificarBodega);
			}
			else
			{
				bodegaModi(modificarBodega);
			$("#delete").hide();
			$("#cancel").show();
			$("#insert").hide();
			}
		}
			
			//$("#codSolicitud").val("");
	      	$("#delete").hide();
	      	
	      	var jCodSolicitante;
	      	
	      	function veri(){
	      		alert($('input[@id="codSolicitud"]').val());
	      		alert($("#tipoSolicitud").val());
	      	}
	      	
	      	function bodegaModi(modificarBodega){
	      	if(modificarBodega=='N'){
				$("#btnBodega").hide();
				$("input[@id='bodegaDes']").attr("class","caja_textodisable");
				$("input[@id='bodegaDes']").attr("size","35");
				}
				else
				{
				$("#btnBodega").show();
				$("input[@id='bodegaDes']").attr("class","caja_texto_obligatorio");
				$("input[@id='bodegaDes']").attr("size","44");
				}
	      	}
	      	function executeTableClick(codRecurso){	      		    		
	      			document.getElementById(codRecurso).onclick();	      			      		
	      	}
   </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="solaba.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="solaba.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<html:form action="/detalleSolAbaAction.do"
			styleId="formularioDetalleSolDescAct">
			<html:hidden property="tipoSolicitud" styleId="sTipoSolicitud"
				value='A' />
			<html:hidden property="codSolicitud" styleId="sCodSolicitud" />
			<html:hidden property="correlativo" styleId="sCorrelativo" />
			<html:hidden property="codSolicitante" styleId="sCodSolicitante" />
			<html:hidden property="estado" styleId="sEstado" />
			<html:hidden property="codSol2" styleId="sCodSol2" />
			<html:hidden property="comentario" styleId="sComentario" />
			<html:hidden property="codBodega" styleId="sCodBodega" />
			<html:hidden property="estadoNombre" styleId="sEstadoNombre" />
			<html:hidden property="bodegaDes" styleId="sBodegaDes" />
			<input type="hidden" name="numeroPagina" id="numeroPagina" />
			<input type="hidden" name="nombreGrid" id="nombreGrid" />
			<input type="hidden" name="cantidadPagina" id="cantidadPagina" />
		</html:form>


		<html:form action="/solicitudAbastecimientoAction.do"
			styleId="formulario">

			<br>

			<center>
				<table class="tableEncabezado">
					<tr>
						<th>
							<bean:message key="prestamo.solicitud" />
						</th>
						<td>
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud"
								value='A' />
							<html:hidden property="estado" styleId="estado" />
							<html:hidden property="codSolicitante" styleId="codSolicitante"
								value='${sessionScope.user}' />
							<script>
								jCodSolicitante = $("#codSolicitante").val();
							</script>
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:text property="codSol2" styleId="codSol2" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="soldesca.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>

				<br>

				<table class="tableDefault">

					<tr>

						<th>
							<bean:message key="prestamo.solicitante" />
						</th>
						<td>
							<table class="tableEncabezado" align="left" width="200px">
								<tr>
									<td>
										<bean:write name="usuario" property="primerNom"
											scope="session"></bean:write>
										<bean:write name="usuario" property="primerApe"
											scope="session"></bean:write>
									</td>
								</tr>
							</table>
						</td>
						<th>
							<bean:message key="prestamo.fechaAprobacion" />
						</th>
						<td>
							<html:text property="fecha_aprobacion" styleId="fecha_aprobacion"
								readonly="true" styleClass="caja_textodisable" size="35"></html:text>
						</td>

					</tr>
					<tr>
						<th>
							<bean:message key="prestamo.codSolicitante" />
						</th>
						<td>
							<html:text property="codSolicitante" styleId="codSolicitante"
								value="${sessionScope.user}" readonly="true"
								styleClass="caja_textodisable" size="35"></html:text>
							<script type="text/javascript">
									jCodSolicitante = $("input[@id='codSolicitante']").val();
								</script>
						</td>

						<th>
							<bean:message key="prestamo.fechaFinalizacion" />
						</th>
						<td>
							<html:text property="fecha_finalizacion"
								styleId="fecha_finalizacion" readonly="true"
								styleClass="caja_textodisable" size="35"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="solaba.bodegadestinodesc" />
						</th>
						<td>
							<html:text property="bodegaDes" styleId="bodegaDes"
								readonly="true" styleClass="caja_texto_obligatorio" size="44">
							</html:text>
							<input type="button" id="btnBodega"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=bodegasxResponsable&codResponsable=' + jCodSolicitante );"
								value="..." />
							<html:hidden property="codBodega" styleId="codBodega" />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="solaba.codBodega.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="soldesca.fechaCreacion" />
						</th>
						<td>
							<html:text property="fecha_creacion" styleId="fecha_creacion"
								readonly="true" styleClass="caja_textodisable" size="35"></html:text>
						</td>

					</tr>
					<tr>
						<th>
							<bean:message key="prestamo.comentario" />
						</th>
						<td colspan="3">
							<html:textarea property="comentario" styleId="comentario"
								cols="100" rows="3"
								style="background-color:LightGoldenRodYellow">
							</html:textarea>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="suministro.descripcion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					<tr />
					<tr>
						<th>
							<bean:message key="prestamo.motivos" />
						</th>
						<td colspan="3">
							<html:textarea property="resolucion" styleId="resolucion"
								cols="100" rows="3" readonly="true"
								style="background-color: #CCCCCC;">
							</html:textarea>

						</td>
					</tr>

				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="solaba.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>

				<div id="accionInsert" class="accion" align="center">
					<html:submit property="accion" styleId="insert">
						<bean:message key="opc.insert" />
					</html:submit>
					<html:submit property="accion" styleId="update"
						style="display:none">
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
				</div>

				<br>

				<hr width="85%">

				<br>

			</center>

			<script>
				$("#delete").hide();
				$("#cancel").hide();
			</script>

			<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>
			<ec:table items="listaSolAba" var="mapf"
				action="${pageContext.request.contextPath}/solicitudAbastecimientoAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Abastecimiento' view="compact" width="80%" tableId="solaAba"
				form="formulario" autoIncludeParameters="yes" scope="request">
				<ec:row highlightRow="true"
					onclick="executeTableClick('lnk${pageScope.mapf.codSolicitud}');">
					<ec:column property="codSolicitud" title='No Solicitud' />
					<ec:column property="fecha_creacion"
						title='Fecha de creaci&oacute;n' />
					<ec:column property="comentario" title='Descripci&oacute;n' />
					<ec:column property="estadoDescr" title='Estado' />
					<ec:column property="solAbaLink" title='Acci&oacute;n'>
							${pageScope.mapf.solAbaLink}
					<input type="hidden"
							onclick="tableClick('${pageScope.mapf.codSolicitud}','${pageScope.mapf.estado}',
			'${pageScope.mapf.fecha_aprobacion}','${pageScope.mapf.fecha_finalizacion}','${pageScope.mapf.fecha_anulacion}',
			'${pageScope.mapf.comentario}','${pageScope.mapf.resolucion}','${pageScope.mapf.fecha_creacion}',
			'${pageScope.mapf.codBodega}', '${pageScope.mapf.bodegaDes}','${pageScope.mapf.tipoSolicitud}',
			'${pageScope.mapf.modificarBodega}');"
							id="lnk${pageScope.mapf.codSolicitud}" />
					</ec:column>
				</ec:row>
			</ec:table>
		</html:form>
		<br>
		<logic:present name="flagBack" scope="request">
			<script type="text/javascript" language="javascript">					
				executeTableClick('${requestScope.flagBack}');
			</script>
		</logic:present>
		<script type="text/javascript">
			agregarPaginacion("solaAba");
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

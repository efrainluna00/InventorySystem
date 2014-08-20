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
		    	ifFormat   : "%d/%m/%Y",
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
	      	
			function tableClick(codActivo,codRecurso,recursoDesc,codUnidad,
								unidadDesc,codAltUca, serie,estado,estadoShow,
								estadoFisico,codEstadoActivo){
				
				$(".error").text("");
				$('#codActivo').val(codActivo);
				$('#codRecurso').val(codRecurso);
				$('#recursoDesc').val(recursoDesc);
				$('#codUnidad').val(codUnidad);
				$('#descripcion').val(unidadDesc);
				$('#codAltUca').val(codAltUca);
				$('#numSerie').val(serie);
				$('#estado').val(estado);
				$('#estadoShow').val(estadoShow);
				$('#estadoFisico').val(estadoFisico);
				$('#codEstadoActivo').val(codEstadoActivo);
								
				habilitar();
			}
			
			function habilitar(){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				//$('#codRecurso').val("");
				$('#btnrecurso').hide();
				//$('#codUnidad').val("");
				$('#btnunidad').hide();		
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
		<bean:message key="activo.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="activo.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/activoAction.do" styleId="formulario">
			<center>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="activo.code" />
						</th>
						<td>
							<html:text property="codActivo" styleId="codActivo" size="24"
								readonly="true" styleClass="caja_textodisable"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="activo.codActivo.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="activo.serie" />
						</th>
						<td>
							<html:text property="numSerie" styleId="numSerie" size="30"
								styleClass="caja_texto_obligatorio" maxlength="20"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="activo.serie.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="activo.descripcion" />
						</th>
						<td>
							<html:hidden property="codRecurso" styleId="codRecurso" />
							<html:text property="recursoDesc" styleId="recursoDesc" size="30"
								styleClass="caja_textodisable" readonly="true"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="activo.recurso.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="activo.codeUca" />
						</th>
						<td>
							<html:text property="codAltUca" styleId="codAltUca" size="30"
								styleClass="caja_texto" maxlength="15"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="activo.unidadDesc" />
						</th>
						<td>
							<html:hidden property="codUnidad" styleId="codUnidad" />
							<html:text property="unidadDesc" styleId="descripcion" size="30"
								styleClass="caja_textodisable" readonly="true"></html:text>
							
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="activo.unidad.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="activo.baja" />
						</th>
						<td>
							<html:hidden property="estado" styleId="estado" value="A" />
							<html:text property="estadoShow" styleId="estadoShow" value="No"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="activo.estado" />
						</th>
						<td>
							<html:hidden property="codEstadoActivo" styleId="codEstadoActivo" />
							<html:text property="estadoFisico" styleId="estadoFisico"
								size="30" styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=estadoActivo');"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="activo.estado.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="activo.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="activo.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>
				<div id="accionInsert" class="accion">
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>
				</div>
				<div id="accionUpdate" class="accion" style="display: none">
					<html:submit property="accion" styleId="update">
						<bean:message key="opc.update" />
					</html:submit>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
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
		<ec:table items="listActivos" var="mapf"
			action="${pageContext.request.contextPath}/activoAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Activos' view="compact" width="80%" tableId="activos"
			autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codActivo}','${pageScope.mapf.codRecurso}',
									'${pageScope.mapf.recursoDesc}','${pageScope.mapf.codUnidad}',
									'${pageScope.mapf.unidadDesc}','${pageScope.mapf.codAltUca}',
									'${pageScope.mapf.numSerie}','${pageScope.mapf.estado}',
									'${pageScope.mapf.estadoDescr}','${pageScope.mapf.estadoFisico}',
									'${pageScope.mapf.codEstadoActivo}');">
				<ec:column property="codActivo" title='C&oacute;digo' width="20%" />
				<ec:column property="recursoDesc" title='Descripci&oacute;n' />
				<ec:column property="unidadDesc" title='Unidad' />
				<ec:column property="numSerie" title='Serie' />
				<ec:column property="estadoDescr" title='Baja' />
				<ec:column property="estadoFisico" title='Estado' />
				<ec:column property="codAltUca" title='C&oacute;digo UCA' />
			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("activos");
						$("form[@id='formulario'] input[@type='text']").keypress( function(e){
					if( $(this).attr("id")!= null && $(this).attr("onkeydown")== null )
						return caracteresValidos($(this),e);
				});
			
			$("form[@id='formulario'] input[@type='text']").change(function(e){
				if( $(this).attr("id")!= null ){
					if(!cadenaValida($(this).val()))
						$(this).val("");
				}
				});
		</script>
	</tiles:put>

</tiles:insert>


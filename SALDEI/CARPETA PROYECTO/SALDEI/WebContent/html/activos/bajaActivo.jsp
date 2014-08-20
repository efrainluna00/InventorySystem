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
        if(!confirm('¿Esta seguro que desea dar de baja al activo seleccionado?'))
						return false;
						else{
							var formulario= document.getElementById('formulario');
            				var href = formulario.action;                        		
                            if(href.indexOf('?') >0 ){
              					href= href.substring(0,href.indexOf('?'));
            				}
            				href+='?accion='+accion+'&';				
            				formulario.action= href;
                            formulario.submit();
                            }					
	      				}
	      	
			function tableClick(codActivo,codRecurso,recursoDesc,codUnidad,
								unidadDesc,codAltUca, serie,responsable,estado){
								  $(".error").hide();	
				$('#codActivo').val(codActivo);
				$('#codRecurso').val(codRecurso);
				$('#recursoDesc').val(recursoDesc);
				$('#codUnidad2').val(codUnidad);
				$('#descripcion2').val(unidadDesc);
				$('#responsable2').val(responsable);
				$('#codAltUca').val(codAltUca);
				$('#numSerie').val(serie);
				$('#estado').val(estado);
				habilitar();
			}
			
		function habilitar(){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
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
		<bean:message key="bajaActivo.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="bajaActivo.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/bajaActivoAction.do" styleId="formulario">
			<center>
				<br>
				<br>
				<table class="tableEncabezado">

					<tr>
						<th>
							<bean:message key="activo.code" />
						</th>
						<td>

							<html:text property="codActivo" styleId="codActivo" size="30"
								readonly="true"></html:text>
						</td>
						<th>
							<bean:message key="activo.serie" />
						</th>
						<td>
							<html:text property="numSerie" styleId="numSerie" size="30"
								readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="activo.descripcion" />
						</th>
						<td>
							<html:hidden property="codRecurso" styleId="codRecurso" />
							<html:text property="recursoDesc" styleId="recursoDesc" size="30"
								readonly="true"></html:text>

						</td>
						<th>
							<bean:message key="activo.codeUca" />
						</th>
						<td>
							<html:text property="codAltUca" styleId="codAltUca" size="30"
								readonly="true"></html:text>
						</td>
					</tr>
				</table>
				<br>
				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="bajaActivo.motivoBaja" />
						</th>
						<td>
							<html:hidden property="codBaja" styleId="codBaja" />
							<html:text property="motivoBajaDesc" styleId="motivoBajaDesc"
								size="50" styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" id="btnunidad"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=motivoBaja');"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="bajaActivo.motivoBaja.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="bajaActivo.comentario" />
						</th>
						<td>
							<html:textarea property="motivo" styleId="motivo" cols="70"></html:textarea>
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="bajaActivo.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="bajaActivo.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>

				<div id="accionInsert" class="accion">
					<html:button property="accion" value="Dar de baja"
						onclick="enviar('Agregar')">
					</html:button>
				</div>
				<div id="accionUpdate" class="accion" style="display: none">
					<html:button property="accion" value="Dar de baja"
						onclick="enviar('Agregar')">
					</html:button>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>
				</div>

				<br />
				<hr width="85%">

			</center>
		</html:form>

		<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

		<ec:table items="listActivosBaja" var="mapf"
			action="${pageContext.request.contextPath}/bajaActivoAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Activos' view="compact" width="80%" tableId="activos"
			autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codActivo}','${pageScope.mapf.codRecurso}',
									'${pageScope.mapf.recursoDesc}','${pageScope.mapf.codUnidad}',
									'${pageScope.mapf.unidadDesc}','${pageScope.mapf.codAltUca}',
									'${pageScope.mapf.numSerie}','${pageScope.mapf.responsable}','${pageScope.mapf.estado}');">
				<ec:column property="codActivo" title='C&oacute;digo' width="10%" />
				<ec:column property="recursoDesc" title='Descripci&oacute;n' />
				<ec:column property="unidadDesc" title='Ubicaci&oacute;n' />
				<ec:column property="numSerie" title='Serie' />
				<ec:column property="codAltUca" title='Cod. UCA' />
				<ec:column property="estadoDescr" title='Estado' />
			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("activos");
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



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
	      	
	      	//tableClick('2009000006','20200001','IMPRESOR DE INYECCION HP 845 C','2','Cubiculo Lic. Alicia','2-02-15499','121551','Alicia Alvarenga','A');
	      	
			function tableClick(codActivo,codRecurso,recursoDesc,codUnidad,
								unidadDesc,codAltUca, serie,responsable,estado,codResponsable){
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
				$('#codResponsable2').val(codResponsable);
								
								
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
		<bean:message key="trasactivo.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="trasactivo.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/movTrasActivoAction.do" styleId="formulario">
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
							<bean:message key="trasactivo.unidadDesc" />
						</th>
						<td>
							<html:hidden property="codUnidad2" styleId="codUnidad2" />
							<html:hidden property="codResponsable2" styleId="codResponsable2" />
							<html:text property="descripcion2" styleId="descripcion2"
								size="25" styleClass="caja_textodisable" readonly="true"></html:text>

						</td>
						<th>
							<bean:message key="trasactivo.unidadNew" />
						</th>
						<td>
							<html:hidden property="codUnidad" styleId="codUnidad" />
							<html:hidden property="codResponsable" styleId="codResponsable" />
							<html:text property="descripcion" styleId="descripcion" size="25"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" id="btnunidad"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=activosTras&unidadActual='+document.getElementById('codUnidad2').value);"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="trasactivo.unidad.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="trasactivo.unidadResponsable" />
						</th>
						<td>
							<html:text property="responsable2" styleId="responsable2"
								size="25" styleClass="caja_textodisable" readonly="true"></html:text>

						</td>
						<th>
							<bean:message key="trasactivo.responNew" />
						</th>
						<td>
							<html:text property="responsable" styleId="responsable" size="30"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="trasactivo.unidad.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="trasactivo.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="trasactivo.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>
				<div id="accionInsert" class="accion">

				</div>
				<div id="accionUpdate" class="accion" style="display: none">

					<html:button property="accion" value="Trasladar"
						onclick="enviar('Actualizar')">
					</html:button>

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
			action="${pageContext.request.contextPath}/movTrasActivoAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Activos' view="compact" width="80%" tableId="activos"
			autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codActivo}','${pageScope.mapf.codRecurso}',
									'${pageScope.mapf.recursoDesc}','${pageScope.mapf.codUnidad}',
									'${pageScope.mapf.unidadDesc}','${pageScope.mapf.codAltUca}',
									'${pageScope.mapf.numSerie}','${pageScope.mapf.responsable}','${pageScope.mapf.estado}'
									,'${pageScope.mapf.cod_responsable}');">
				<ec:column property="codActivo" title='C&oacute;digo' width="20%" />
				<ec:column property="recursoDesc" title='Descripcion' />
				<ec:column property="unidadDesc" title='Unidad' />
				<ec:column property="numSerie" title='Serie' />
				<ec:column property="codAltUca" title='Cod. UCA' />
				<ec:column property="estadoDescr" title='Estado' />
			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("activos");
		</script>

	</tiles:put>

</tiles:insert>



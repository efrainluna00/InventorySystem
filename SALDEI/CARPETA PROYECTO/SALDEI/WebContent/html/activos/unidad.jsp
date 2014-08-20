<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>



<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
    
    	//AGRANDO LOS BOTONES
  
    
    
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
	      		
			function tableClick(codUnidad,descripcion,codTipoUnidad,codResponsable,estado,tipoUnidadDesc,responsableDescr){
			$(".error").hide();	
			document.getElementById('codUnidad').value = codUnidad;
			document.getElementById('descripcion').value = descripcion;
			document.getElementById('codTipoUnidad').value = codTipoUnidad;
			document.getElementById('codResponsable').value = codResponsable;
			document.getElementById('estado').value = estado;
			document.getElementById('tipoUnidadDesc').value = tipoUnidadDesc;
			document.getElementById('responsableDescr').value = responsableDescr;	
			//document.getElementById('fechaCreacion').value = fechaCreacion;	
			
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
		<bean:message key="uni.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="uni.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/unidadAction.do" styleId="formularioUnidad">
			<center>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="uni.codUnidad" />
						</th>
						<td>
							<html:text property="codUnidad" styleId="codUnidad" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
					</tr>


					<tr>
						<th>
							<bean:message key="uni.descripcion" />
						</th>
						<td>
							<html:hidden property="codUnidad" styleId="codUnidad"></html:hidden>
							<html:text property="descripcion" styleId="descripcion" size="30"
								styleClass="caja_texto_obligatorio" maxlength="75"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="uni.descripcion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="uni.codTipoUnidad" />
						</th>
						<td>
							<html:hidden property="codTipoUnidad" styleId="codTipoUnidad"></html:hidden>
							<html:text property="tipoUnidadDesc" styleId="tipoUnidadDesc"
								size="30" styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=unidadTipoUnidad');"
								value="..." />

							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="uni.codTipoUnidad.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>

					<tr>
						<th>
							<bean:message key="uni.codResponsable" />
						</th>
						<td>
							<html:hidden property="codResponsable" styleId="codResponsable"></html:hidden>
							<html:text property="responsableDescr" styleId="responsableDescr"
								size="30" styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=unidadResponsable');"
								value="..." />

							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="uni.codResponsable.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>

						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="uni.estado" />
						</th>
						<td>
							<html:select property="estado" styleId="estado"
								styleClass="caja_texto_obligatorio">
								<html:option value="A">Activo</html:option>
								<html:option value="I">Inactivo</html:option>
							</html:select>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="uni.codEstado.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>

						</td>
					</tr>
				</table>


				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="uni.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="uni.mensajeError.error">
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
				</div>
				<div id="accionUpdate" class="accion" style="display: none">
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
				</div>
				<br>
				<hr width="85%">
				<logic:equal parameter="accion" value='Actualizar'>
					<script type="text/javascript" language="javascript">
					habilitar();
					</script>
				</logic:equal>
				<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>
			</center>
		</html:form>
		<ec:table items="listaUnidad" var="mapf"
			action="${pageContext.request.contextPath}/unidadAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Unidad' view="compact" width="80%" tableId="unidad"
			autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codUnidad}','${pageScope.mapf.descripcion}','${pageScope.mapf.codTipoUnidad}',
				'${pageScope.mapf.codResponsable}','${pageScope.mapf.estado}','${pageScope.mapf.tipoUnidadDesc}','${pageScope.mapf.responsableDescr}');">
				<ec:column property="codUnidad" title='C&oacute;digo de unidad' />
				<ec:column property="descripcion" title='Descripci&oacute;n' />
				<ec:column property="tipoUnidadDesc" title='Tipo de unidad' />
				<ec:column property="responsableDescr" title='Responsable' />
				<ec:column property="fecha_creacion"
					title='Fecha de creaci&oacute;n' format="MM-dd-yy" />
				<ec:column property="estadoDescr" title="Estado" />
			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("unidad");
			$("form[@id='formularioUnidad'] input[@type='text']").keypress( function(e){
					if( $(this).attr("id")!= null && $(this).attr("onkeydown")== null )
						return caracteresValidos($(this),e);
				});
			
				$("form[@id='formularioUnidad'] input[@type='text']").change(function(e){
				if( $(this).attr("id")!= null ){
					if(!cadenaValida($(this).val()))
						$(this).val("");
				}
				});
		</script>
	</tiles:put>



</tiles:insert>


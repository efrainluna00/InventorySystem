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
	      	
			function tableClick(codTipoMov,descripcion,operacion,registroModificable){
			  	$(".error").hide();	
				document.getElementById('codTipoMov').value = codTipoMov;
				document.getElementById('descripcion').value = descripcion;
				document.getElementById('operacion').value = operacion;
				$("#codTipoMov").attr("class","caja_textodisable");
				$("#codTipoMov").attr("size","24");
				$("#codTipoMov").attr("readonly","true");
				if(registroModificable=='S')				
					habilitar();
				else
					habilio();
			}
			
			function habilitar(){
			$("#operacion").attr("disabled","");
			$("#descripcion").attr("class","caja_texto_obligatorio");
			$("#descripcion").attr("size","24");
			
			
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
	      	
	      	function habilio(){
	      		$("#insert").hide();
	      		$("#operacion").attr("disabled","true");
	      		$("#descripcion").attr("class","caja_textodisable");
			$("#descripcion").attr("size","24");
	      		
	      		$("#accionInsert").hide();
	      		$("#accionUpdate").hide();
	      		$('#accionInsert').SlideInDown('slow');
	      	}
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="tm.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="tm.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/tipoMovAction.do" styleId="formulario">
			<center>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="tm.codTipoMov" />
						</th>
						<td>

							<html:text property="codTipoMov" styleId="codTipoMov" size="30"
								styleClass="caja_texto_obligatorio"
								onkeydown="return soloEnteros(this,event);"
								title="Introduzca un numero entero positivo"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="tm.codTipoMov.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="tm.descripcion" />
						</th>
						<td>
							<html:text property="descripcion" styleId="descripcion" size="30"
								styleClass="caja_texto_obligatorio" maxlength="75"></html:text>

							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="tm.descripcion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="tm.operacion" />
						</th>
						<td>
							<html:select property="operacion" styleId="operacion"
								styleClass="caja_texto_obligatorio">
								<html:option value="S">
									Aumento							
								</html:option>
								<html:option value="R">
									Disminuci&oacute;n						
								</html:option>
							</html:select>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="tm.operacion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>

						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="tm.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="tm.mensajeError.error">
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

		<ec:table items="listTipoMov" var="mapf"
			action="${pageContext.request.contextPath}/tipoMovAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Tipo de Movimientos' view="compact" width="60%"
			tableId="tipoMov" autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codTipoMov}','${pageScope.mapf.descripcion}',	
				'${pageScope.mapf.operacion}','${pageScope.mapf.registroModificable}');">
				<ec:column property="codTipoMov" title='C&oacute;digo' />
				<ec:column property="descripcion" title='Descripci&oacute;n' />
				<ec:column property="operacionDescr" title='Operaci&oacute;n' />
			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("tipoMov");
			
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


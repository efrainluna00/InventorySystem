<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>



<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
    
    
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
	      		
			function tableClick(codProveedor,nombre,direccion,nit,fax,telefono,EMail,webSite,regFiscal,giro,estado,contacto){
			$(".error").hide();	
			$("#codProveedor").val(codProveedor);	
			$("#nombre").val(nombre);
			$("#direccion").val(direccion);
			$("#nit").val(nit);
			$("#fax").val(fax);
			$("#telefono").val(telefono);
			$("#EMail").val(EMail);
			$("#webSite").val(webSite);
			$("#regFiscal").val(regFiscal);
			$("#giro").val(giro);
			$("#estado").val(estado);
			$("#contacto").val(contacto);
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
		<bean:message key="proveedor.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="proveedor.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/proveedorAction.do" styleId="formularioProveedor">
			<center>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="proveedor.codigo" />
						</th>
						<td>
							<html:text property="codProveedor" styleId="codProveedor"
								styleClass="caja_textodisable" readonly="true" size="24"></html:text>
						</td>
						<th>
							<bean:message key="proveedor.nombre" />
						</th>
						<td>
							<html:hidden property="codProveedor" styleId="codProveedor" />
							<html:text property="nombre" styleId="nombre" size="30"
								styleClass="caja_texto_obligatorio" maxlength="75"></html:text>

							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="proveedor.nombre.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="proveedor.direccion" />
						</th>
						<td>
							<html:text property="direccion" styleId="direccion" size="30"
								styleClass="caja_texto" maxlength="200"></html:text>

						</td>
						<th>
							<bean:message key="proveedor.nit" />
						</th>
						<td>
							<html:text property="nit" styleId="nit" size="30"
								styleClass="caja_texto" maxlength="17"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="proveedor.fax" />
						</th>
						<td>
							<html:text property="fax" styleId="fax" size="30"
								styleClass="caja_texto" maxlength="17"></html:text>
						</td>
						<th>
							<bean:message key="proveedor.telefono" />
						</th>
						<td>
							<html:text property="telefono" styleId="telefono" size="30"
								styleClass="caja_texto" maxlength="12"></html:text>

						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="proveedor.email" />
						</th>
						<td>
							<html:text property="EMail" styleId="EMail" size="30"
								styleClass="caja_texto" maxlength="75"></html:text>
						</td>
						<th>
							<bean:message key="proveedor.website" />
						</th>
						<td>
							<html:text property="webSite" styleId="webSite" size="30"
								styleClass="caja_texto" maxlength="75"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="proveedor.regfiscal" />
						</th>
						<td>
							<html:text property="regFiscal" styleId="regFiscal" size="30"
								styleClass="caja_texto" maxlength="18"></html:text>
						</td>
						<th>
							<bean:message key="proveedor.giro" />
						</th>
						<td>
							<html:text property="giro" styleId="giro" size="30"
								styleClass="caja_texto" maxlength="60"></html:text>
						</td>
					</tr>
					<tr>

						<th>
							<bean:message key="proveedor.contacto" />
						</th>
						<td>
							<html:text property="contacto" styleId="contacto"
								styleClass="caja_texto" size="30" maxlength="150">
							</html:text>
						</td>


						<th>
							<bean:message key="proveedor.estado" />
						</th>
						<td>
							<html:select property="estado" styleId="estado"
								styleClass="caja_texto_obligatorio">
								<html:option value="A">Activo</html:option>
								<html:option value="I">Inactivo</html:option>
							</html:select>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="proveedor.estado.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>

					</tr>

				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="proveedor.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="proveedor.mensajeError.error">
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

		<ec:table items="listaProveedor" var="mapf"
			action="${pageContext.request.contextPath}/proveedorAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Proveedores' view="compact" width="80%" tableId="proveedor"
			autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codProveedor}','${pageScope.mapf.nombre}','${pageScope.mapf.direccion}','${pageScope.mapf.nit}','${pageScope.mapf.fax}',
				'${pageScope.mapf.telefono}','${pageScope.mapf.EMail}','${pageScope.mapf.webSite}','${pageScope.mapf.regFiscal}','${pageScope.mapf.giro}','${pageScope.mapf.estado}','${pageScope.mapf.contacto}');">
				<ec:column property="codProveedor"
					title='C&oacute;digo de proveedor' />
				<ec:column property="nombre" title='Descripci&oacute;n' />
				<ec:column property="telefono" title='Tel&eacute;fono' />
				<ec:column property="EMail" title='E-mail' />
				<ec:column property="estadoDescr" title="Estado"
					filterCell="droplist" width="20%" />

			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("proveedor");
			$("form[@id='formularioProveedor'] input[@type='text']").keypress( function(e){
					if( $(this).attr("id")!= null && $(this).attr("onkeydown")== null )
						return caracteresValidos($(this),e);
				});
			
			$("form[@id='formularioProveedor'] input[@type='text']").change(function(e){
				if( $(this).attr("id")!= null ){
					if(!cadenaValida($(this).val()))
						$(this).val("");
				}
				});
		</script>
	</tiles:put>

</tiles:insert>


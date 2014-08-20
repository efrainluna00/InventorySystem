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
	      	
			function tableClick(codCategoria,codSubCategoria, nombreCategoria, nombreSubCategoria){
				
				$('#codSubCategoria').val(codSubCategoria);
				$('#codCategoria').val(codCategoria);
				$('#nombreCategoria').val(nombreCategoria);
				$('#nombre').val(nombreSubCategoria);
				
				
												
				habilitar();
			}
			
			function habilitar(){
			  	$(".error").hide();	
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				$('#codSubCategoria').attr('readonly','true');
				$('#codSubCategoria').attr('class','caja_textodisable');
				$('#codSubCategoria').attr('size','24');
				$('#nombreCategoria').attr('class','caja_textodisable');
				$('#nombreCategoria').attr('size','24');
				$('#codCategoria').attr('class','caja_textodisable');											
				$('#btnCategoria').hide();
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
		<bean:message key="subcat.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="subcat.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/subcategoriaAction.do" styleId="formulario">
			<center>
				<br>
				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="subcat.categoria" />
						</th>
						<td>
							<html:text property="codCategoria" styleId="codCategoria"
								size="5" styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<html:text property="nombreCategoria" styleId="nombreCategoria"
								size="30" styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input id="btnCategoria" type="button"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=categorias');"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="subcat.codigoCat.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="subcat.codigo" />
						</th>
						<td>
							<html:text property="codSubCategoria" styleId="codSubCategoria"
								size="30" maxlength="2" styleClass="caja_texto_obligatorio"
								title="El código debe ser de longitud dos ya sean caracteres o numeros Ej. AB o 01"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="subcat.codigo.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>

							</html:messages>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="subcat.codigo.novalido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="subcat.nombre" />
						</th>
						<td>
							<html:text property="nombre" styleId="nombre" size="30"
								styleClass="caja_texto_obligatorio" maxlength="65"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="subcat.nombre.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>

				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="subcat.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="subcat.mensajeError.error">
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

			</center>
			<br>
		</html:form>
		<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

		<ec:table items="listSubCategoria" var="mapf"
			action="${pageContext.request.contextPath}/subcategoriaAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Sub Categor&iacute;as' view="compact" width="80%"
			tableId="subCategoria" autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codCategoria}','${pageScope.mapf.codSubCategoria}',
																'${pageScope.mapf.nombreCategoria}','${pageScope.mapf.nombreSubCategoria}');">
				<ec:column property="codCategoria"
					title='C&oacute;digo Categor&iacute;a' />
				<ec:column property="nombreCategoria"
					title='Nombre Categor&iacute;a' />
				<ec:column property="codSubCategoria"
					title='C&oacute;digo Sub Categor&iacute;a' width="20%" />
				<ec:column property="nombreSubCategoria"
					title='Nombre Sub Categor&iacute;a' />

			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("subCategoria");
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


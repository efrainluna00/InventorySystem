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
	      	
			function tableClick(codCategoria, nombre){
			  $(".error").hide();	
				$('#codCategoria').val(codCategoria);
				$('#nombre').val(nombre);
				$('#codCategoria').attr('readonly','true');		
																								
				habilitar();
			}
			
			function habilitar(){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				$('#codCategoria').attr('class','caja_textodisable');
				$('#codCategoria').attr('size','24');
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
		<bean:message key="cat.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="cat.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/categoriaAction.do" styleId="formulario">
			<center>
				<br>
				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="cat.codigo" />
						</th>
						<td>
							<html:text property="codCategoria" styleId="codCategoria"
								size="30" maxlength="1" styleClass="caja_texto_obligatorio"
								title="El código puede ser un caracter o numero Ej. A o 1	"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="cat.codigo.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="cat.nombre" />
						</th>
						<td>
							<html:text property="nombre" styleId="nombre" size="30"
								styleClass="caja_texto_obligatorio" maxlength="65"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="cat.nombre.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>

				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="cat.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="cat.mensajeError.error">
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
		</html:form>
		<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

		<ec:table items="listCategoria" var="mapf"
			action="${pageContext.request.contextPath}/categoriaAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Categor&iacute;as' view="compact" width="55%"
			tableId="categoria" autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codCategoria}','${pageScope.mapf.nombre}');">
				<ec:column property="codCategoria" title='C&oacute;digo' width="20%" />
				<ec:column property="nombre" title='Nombre' />
			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("categoria");
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


<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>



<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
		
		var cad;
		
	 
    	function verCalen(field, btn){
				Calendar.setup({
			    inputField : field,
		    	daFormat   : "%yyyy-%m-%d",
			    button     : btn
				});
			}
        
        function borrarSubCat(){
        	document.getElementById('codSubCategoria').value = "";
			document.getElementById('nombreSubCategoria').value = "";
        }
        
       function enviar(accion){
	      		var formulario= document.getElementById('formulario');
            var href = formulario.action;                        		
            
            alert($("input[@name='recursoTableId_p']").val());
            $("input[@name='recursoTableId_p']").val('2');
                    
            if(href.indexOf('?') >0 ){
              href= href.substring(0,href.indexOf('?'));
            }
            href+='?accion='+accion+'&';				
            formulario.action= href;
                          
            formulario.submit();					
	      	}
       
        function enviarRecurso(accion,codRecurso,nombreRecurso,categoria,subcategoria,unidadmedida
        ,abvunidadmedida){
	      	var formulario= document.getElementById('formula');
	      	$("#dCodRecurso").val(codRecurso);
	      	$("#dNombreRecurso").val(nombreRecurso);
	      	$("#dCategoria").val(categoria);
	      	$("#dSubCategoria").val(subcategoria);
	      	$("#dUnidadMedida").val(unidadmedida);
	        $("#dAbvUnidadMedida").val(abvunidadmedida);
	        // para que cuando regrese del formulario hijo conserve los valores seleccionados
	        $("#numeroPagina").val($("input[@name='recursoTableId_p']").val());
	        $("#cantidadPagina").val($("input[@name='recursoTableId_crd']").val());	        
	        $("#nombreGrid").val('recursoTableId');
	        
            var href = formulario.action;                        		
                    
            if(href.indexOf('?') >0 ){
              href= href.substring(0,href.indexOf('?'));
            }
            href+='?accion='+accion+'&';				
            formulario.action= href;
                          
            formulario.submit();					
	      	}
	      	
			function tableClick(codRecurso, nombre,codCategoria,codSubCategoria, nombreCategoria,
			nombreSubCategoria,abv_medida,nom_medida,id_medida,fecha_creacion,
			consumible,consumibleDesc,marca,modelo){
				document.getElementById('codRecurso1').value = codRecurso;
				document.getElementById('nombre').value = nombre;
				document.getElementById('codCategoria').value = codCategoria;
				document.getElementById('codSubCategoria').value = codSubCategoria;
				document.getElementById('nombreCategoria').value = nombreCategoria;
				document.getElementById('nombreSubCategoria').value = nombreSubCategoria;
				document.getElementById('codUnidadMedida').value = id_medida;
				document.getElementById('abvMedida').value = abv_medida;
				document.getElementById('nomMedida').value = nom_medida;
				document.getElementById('fechaCreacion').value = fecha_creacion;
				$("#consumible").val(consumible);
				$("#marca").val(marca);
				$("#modelo").val(modelo);
				$(".error").hide();												
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
	      	
	        function executeTableClick(codRecurso){	      		    		
	      			document.getElementById(codRecurso).onclick();	      			      		
	      	}
	      	
	      	
    </script>
	</tiles:put>



	<tiles:put name="title" type="String">
		<bean:message key="recurso.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="recurso.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<html:form action="/minimoxBodegaAction.do" styleId="formula">
			<html:hidden property="codRecurso" styleId="dCodRecurso" />
			<html:hidden property="nombreRecurso" styleId="dNombreRecurso" />
			<html:hidden property="categoria" styleId="dCategoria" />
			<html:hidden property="subcategoria" styleId="dSubCategoria" />
			<html:hidden property="unidadmedida" styleId="dUnidadMedida" />
			<html:hidden property="abvunidadmedida" styleId="dAbvUnidadMedida" />
			<html:hidden property="codBodega" styleId="dCodBodega" value="0" />
			<input type="hidden" name="numeroPagina" id="numeroPagina" />
			<input type="hidden" name="nombreGrid" id="nombreGrid" />
			<input type="hidden" name="cantidadPagina" id="cantidadPagina" />
		</html:form>

		<html:form action="/recursoAction.do" styleId="formulario">
			<center>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="recurso.codigo" />
						</th>
						<td>
							<html:text property="codRecurso" styleId="codRecurso1"
								styleClass="caja_textodisable" size="24" readonly="true"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="recurso.codigo.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>

						<th>
							<bean:message key="recurso.codUnidadMedida" />
						</th>
						<td>
							<html:hidden property="codUnidadMedida" styleId="codUnidadMedida"></html:hidden>
							<html:text property="abvMedida" styleId="abvMedida" size="5"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>

							<html:text property="nomMedida" styleId="nomMedida"
								readonly="true" styleClass="caja_texto_obligatorio" size="20"></html:text>
							<input id="btnCategoria" type="button"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=medidas');"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="recurso.codUnidadMedida.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>

						<th>
							<bean:message key="recurso.nombre" />
						</th>
						<td>
							<html:text property="nombre" styleId="nombre" size="30"
								styleClass="caja_texto_obligatorio" maxlength="65"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="recurso.nombre.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="recurso.codCategoria" />
						</th>
						<td>
							<html:text property="codCategoria" styleId="codCategoria"
								size="5" onchange="borrarSubCat()"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>

							<html:text property="nombreCategoria" styleId="nombreCategoria"
								readonly="true" size="20" styleClass="caja_texto_obligatorio"></html:text>
							<input id="btnCategoria" type="button"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=categorias2');"
								value="..." />

							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="recurso.codCategoria.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="recurso.consumible" />
						</th>
						<td>
							<html:select property="consumible" styleId="consumible"
								styleClass="caja_texto_obligatorio">
								<html:option value="N">
									No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
								</html:option>
								<html:option value="S">
									Si&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
								</html:option>
							</html:select>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="recurso.consumible.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>

						</td>
						<th>
							<bean:message key="recurso.codSubCategoria" />
						</th>
						<td>
							<html:text property="codSubCategoria" styleId="codSubCategoria"
								size="5" styleClass="caja_texto_obligatorio" readonly="true"></html:text>

							<html:text property="nombreSubCategoria"
								styleId="nombreSubCategoria" readonly="true"
								styleClass="caja_texto_obligatorio" size="20"></html:text>
							<input id="btnCategoria" type="button"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=subCategorias&codigoCat='+document.getElementById('codCategoria').value);"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="recurso.codSubCategoria.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="recurso.fechaCreacion" />
						</th>
						<td>
							<html:text property="fechaCreacion" styleId="fechaCreacion"
								styleClass="caja_textodisable" size="24" readonly="true"></html:text>
						</td>
						<th>
							<bean:message key="recurso.marca" />
						</th>
						<td>
							<html:text property="marca" styleId="marca"
								styleClass="caja_texto" size="33" maxlength="45"></html:text>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							&nbsp;
						</td>
						<th>
							<bean:message key="recurso.modelo" />
						</th>
						<td>
							<html:text property="modelo" styleId="modelo"
								styleClass="caja_texto" size="33" maxlength="45"></html:text>
						</td>
					</tr>

				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="recurso.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="recurso.mensajeError.error">
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
					<html:button property="accion" styleId="cancel"
						onclick="enviar('Cancelar');">
						<bean:message key="opc.cancel" />
					</html:button>
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
				<br />
				<hr width="85%">
				<logic:equal parameter="accion" value='Actualizar'>
					<script type="text/javascript" language="javascript">
					habilitar();
				</script>
				</logic:equal>

			</center>
			<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

			<ec:table items="listRecurso" var="mapf"
				action="${pageContext.request.contextPath}/recursoAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Recursos' view="compact" width="80%" tableId="recursoTableId"
				form="formulario" autoIncludeParameters="true" scope="request">
				<ec:row highlightRow="true"
					onclick="executeTableClick('lnk${pageScope.mapf.codRecurso}');">
					<ec:column property="codRecurso" title='C&oacute;digo' />
					<ec:column property="nombre" title='Nombre' />
					<ec:column property="nombreCategoria" title='Categor&iacute;a' />
					<ec:column property="descConsumible" title='Suministro' />
					<ec:column property="nombreSubCategoria"
						title='Sub Categor&iacute;a' />
					<ec:column property="fechaCreacion" title='Fecha creaci&oacute;n' />
					<ec:column property="linkMinimo" title='Asociar Bodegas'>
									${pageScope.mapf.linkMinimo}
					<input type="hidden"
							onclick="tableClick('${pageScope.mapf.codRecurso}','${pageScope.mapf.nombre}',
				                    '${pageScope.mapf.codCategoria}','${pageScope.mapf.codSubCategoria}',
				                    '${pageScope.mapf.nombreCategoria}','${pageScope.mapf.nombreSubCategoria}',
				                    '${pageScope.mapf.abvMedida}','${pageScope.mapf.nomMedida}',
				                    '${pageScope.mapf.codUnidadMedida}', '${pageScope.mapf.fechaCreacion}',
				                    '${pageScope.mapf.consumible}','${pageScope.mapf.descConsumible}',
				                    '${pageScope.mapf.marca}','${pageScope.mapf.modelo}');"
							id="lnk${pageScope.mapf.codRecurso}" />
					</ec:column>


				</ec:row>
			</ec:table>			

		</html:form>
		<BR>
		<logic:present name="flagBack" scope="request">
			<script type="text/javascript" language="javascript">					
				executeTableClick('${requestScope.flagBack}');
			</script>
		</logic:present>

		<script type="text/javascript">
			agregarPaginacion("recursoTableId");
				
				$("form[@id='formulario'] input[@type='text']").keypress( function(e){
					if( $(this).attr("id")!= null && $(this).attr("onkeydown")== null )
						return caracteresValidos($(this),e);
				});
			
			
			$("form[@id='formulario'] input[@type='text']").each(function(){
				if( $(this).attr("id")!= null && $(this).attr("id")!= "codCategoria"){
				//alert( $(this).attr("id") );
					$(this).change(function(){
						if( !cadenaValida($(this).val()) )
							$(this).val("");
							});
				}
			});
			
		</script>
	</tiles:put>

</tiles:insert>


<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>



<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
    
    	function agregarCantidad(){    		
    		var cadenaActivos = $('#codActivo').val();
    		cadenaActivos = cadenaActivos.split(",");    		
    		$('#cantidad').val(cadenaActivos.length-1); 
    	}
    
    	function verCalen(field, btn){
				Calendar.setup({
			    inputField : field,
		    	daFormat   : "%yyyy-%m-%d",
			    button     : btn
				});
			}
        
        	function agregarSerie(){
				var cant = $('#cantidad').val();
				
				if($('#numSerie').val() != ""){
					$("#seriesRecurso tbody").append('<tr id = "'+$('#numSerie').val()+'" ><td style="text-align: center;" >'+$('#numSerie').val()+ ' <input type="hidden" name="series" id="hd'+$('#numSerie').val()+'" value="'+$('#numSerie').val()+'" />  <a  href="#" onclick = "quitarSerie(\''+$('#numSerie').val()+'\');" > Eliminar </a> </td></tr>');													
					$('#cantidad').val(cant-1+2);					
				}
				$('#numSerie').val('');
																
			}
			
			function quitarSerie(id){
				alert(id);
				var cant = $('#cantidad').val();				
				
				$("#hd"+id+"").remove();
				$("#"+id+"").remove();		
								
				$('#cantidad').val(cant-1);
																
			}
        
       
	      	
			function tableClick(codRecurso, nombre,codCategoria,codSubCategoria,serializable,
			                   nombreCategoria,nombreSubCategoria,abv_medida,nom_medida,id_medida,fecha_creacion,
			                   consumible, descConsumible){
				document.getElementById('codRecurso').value = codRecurso;
				document.getElementById('nombre').value = nombre;
				document.getElementById('codCategoria').value = codCategoria;
				document.getElementById('codSubCategoria').value = codSubCategoria;				
				document.getElementById('nombreCategoria').value = nombreCategoria;
				document.getElementById('nombreSubCategoria').value = nombreSubCategoria;
				document.getElementById('codUnidadMedida').value = id_medida;
				document.getElementById('abvMedida').value = abv_medida;
				document.getElementById('nomMedida').value = nom_medida;				
				$("#consumible").val(consumible);
				$("#descConsumible").val(descConsumible);
				
				$(".error").hide();												
				habilitar();
			
			}
			
			function habilitarSeries(){
				$('#rowNumSeries').show();
				
				if($('#operacion').val() == 'S' && $('#codTipoMov').val() == 6 ){
					$('#btnSeriesTraslado').show();
					$('#btnAddSerie').hide();
					$('#btnSeries').hide();
					$('#numSerie').attr('readonly','true');
					$('#insert').show();
					$('#delete').hide();
					
				}
				if($('#operacion').val() == 'S' && $('#codTipoMov').val() != 6 ){
					$('#btnSeriesTraslado').hide();
					$('#btnAddSerie').show();
					$('#btnSeries').hide();
					$('#numSerie').attr('readonly','');
					$('#insert').show();
					$('#delete').hide();
					
				}
				if($('#operacion').val() == 'R'){
					$('#btnAddSerie').hide();
					$('#btnSeries').show();
					$('#numSerie').attr('readonly','true');
					$('#insert').hide();
					$('#delete').show();
				}
			}
			
			function habilitar(){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				
				$('#ajusteTable').show();
				
				if($('#consumible').val() == 'S'){
					$('#celdaCantidadTxt').show();
					$('#celdaCantidad').show();
					$('#cantidad').attr('readonly','');
					$('#celdaSeriesTxt').hide();
					$('#celdaSeries').hide();
				}else{
					$('#celdaCantidadTxt').show();					
					$('#celdaCantidad').show();
					$('#celdaSeriesTxt').show();
					$('#celdaSeries').show();
					$('#cantidad').attr('readonly','true');
					$('#cantidad').attr('class','caja_textodisable');
					$('#cantidad').attr('size','20');
				}
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
		<bean:message key="ajuste.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="ajuste.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/movAjusteInvAction.do" styleId="formulario">
			<center>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="ajuste.nombre" />
						</th>
						<td colspan="3">
							<html:text property="codRecurso" styleId="codRecurso"
								readonly="true" styleClass="caja_textodisable" size="11"></html:text>
							<html:text property="nombre" styleId="nombre" size="70"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="ajuste.codUnidadMedida" />
						</th>
						<td>
							<html:hidden property="codUnidadMedida" styleId="codUnidadMedida"></html:hidden>
							<html:text property="abvMedida" styleId="abvMedida" size="4"
								styleClass="caja_textodisable" readonly="true"></html:text>

							<html:text property="nomMedida" styleId="nomMedida"
								readonly="true" styleClass="caja_textodisable" size="20"></html:text>

						</td>
						<th>
							<bean:message key="ajuste.codCategoria" />
						</th>
						<td>
							<html:text property="codCategoria" styleId="codCategoria"
								size="5" onchange="borrarSubCat()"
								styleClass="caja_textodisable" readonly="true"></html:text>

							<html:text property="nombreCategoria" styleId="nombreCategoria"
								readonly="true" size="20" styleClass="caja_textodisable"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="ajuste.consumible" />
						</th>
						<td>
							<html:hidden property="consumible" styleId="consumible" />
							<html:text property="descConsumible" styleId="descConsumible"
								size="29" styleClass="caja_textodisable" readonly="true">
							</html:text>

						</td>
						<th>
							<bean:message key="ajuste.codSubCategoria" />
						</th>
						<td>
							<html:text property="codSubCategoria" styleId="codSubCategoria"
								size="5" styleClass="caja_textodisable" readonly="true"></html:text>

							<html:text property="nombreSubCategoria"
								styleId="nombreSubCategoria" readonly="true"
								styleClass="caja_textodisable" size="20"></html:text>
						</td>
					</tr>
				</table>
				<table class="tableDefault" id="ajusteTable" style="display: none">
					<tr>
						<th colspan="4" align="center" style="text-align: center">
							Datos del Ajuste de Inventario
						</th>
					</tr>
					<tr>
						<th>
							<bean:message key="ajuste.operacion" />
						</th>
						<td>
							<html:hidden property="codTipoMov" styleId="codTipoMov" />
							<html:hidden property="operacion" styleId="operacion"
								onchange="habilitarSeries();" />

							<html:text property="operacionDesc" styleId="operacionDesc"
								size="20" styleClass="caja_textodisable" readonly="true" />

						</td>
						<th>
							<bean:message key="ajuste.tipoMovimiento" />
						</th>
						<td>
							<html:text property="descripcion" styleId="descripcion"
								readonly="true" styleClass="caja_textodisable" size="44"></html:text>
							<input id="btnTipoMovimientos" type="button"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=tiposMovimientos');"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="ajuste.tipoMov.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="ajuste.codBodega" />
						</th>
						<td>
							<html:text property="codBodega" styleId="codBodega" size="20"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
						<th>
							<bean:message key="ajuste.bodega" />
						</th>
						<td>
							<html:text property="nombreBodega" styleId="bodegaDes" size="44"
								styleClass="caja_textodisable" readonly="true"></html:text>
							<input type="button" id="btnBodegas"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=bodegas_x_recursos&codRecurso='+document.getElementById('codRecurso').value);"
								value="..." />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="ajuste.bodega.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="ajuste.justificacion" />
						</th>
						<td colspan="3">
							<html:textarea property="comentario" styleId="comentario"
								cols="100" style="background-color: LightGoldenRodYellow;"></html:textarea>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="ajuste.justificacion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th id="celdaCantidadTxt" style="display: none;">
							<bean:message key="ajuste.cantidad" />
						</th>
						<td id="celdaCantidad" style="display: none;">
							<html:text property="cantidad" styleId="cantidad" readonly="true"
								styleClass="caja_texto_obligatorio" size="20"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="ajuste.cantidad.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th id="celdaSeriesTxt" style="display: none;">
							<bean:message key="ajuste.series" />
						</th>
						<td id="celdaSeries" style="display: none;">
							<input type="hidden" name="numSeries" id="codActivo"
								onchange="javascript: agregarCantidad();" />
							<input type="hidden" id="codigoRecurso" />
							<input name="numSeriestxt" id="numSerie" readonly="readonly"
								onkeydown="return caracteresValidosSinComa(this,event);"
								class="caja_texto_obligatorio" size="55" />
							<input id="btnSeries" type="button" style="display: none;"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=series_activos&multiple=true&codBodega='+document.getElementById('codBodega').value+'&codRecurso='+document.getElementById('codRecurso').value);"
								value="..." />
							<input id="btnSeriesTraslado" type="button"
								style="display: none;"
								onclick="abrirVentana('ldvAction.do?xmlArchivo=series_activos_traslado&multiple=true&codBodega='+document.getElementById('codBodega').value+'&codRecurso='+document.getElementById('codRecurso').value);"
								value="..." />
							<input type="button" id="btnAddSerie" style="display: none;"
								onclick="agregarSerie();" value="+" />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="ajuste.series.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr id="rowNumSeries" style="display: none;">
						<td>
							<table class="tableEncabezado" id="seriesRecurso">
								<tr>
								</tr>
							</table>
						</td>
					</tr>

				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="ajuste.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="ajuste.mensajeError.error">
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
					<html:button property="accion" styleId="insert"
						style="display: none;" onclick="enviar('Actualizar')">
						<bean:message key="opc.insert" />
					</html:button>
					<html:submit property="accion" styleId="delete"
						style="display: none;">
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
					habilitarSeries();
				</script>
				</logic:equal>
			</center>
			<script type="text/javascript" language="javascript">
			  	$('input[@name="accion"]').attr('class','claseBotonInventario');
			</script>
		</html:form>
		<ec:table items="listRecurso" var="mapf"
			action="${pageContext.request.contextPath}/movAjusteInvAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Recursos' view="compact" width="80%" tableId="recursoTableId"
			autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codRecurso}','${pageScope.mapf.nombre}',
				                    '${pageScope.mapf.codCategoria}','${pageScope.mapf.codSubCategoria}',
				                    '${pageScope.mapf.serializable}',
				                    '${pageScope.mapf.nombreCategoria}','${pageScope.mapf.nombreSubCategoria}',
				                    '${pageScope.mapf.abvMedida}','${pageScope.mapf.nomMedida}',
				                    '${pageScope.mapf.codUnidadMedida}', '${pageScope.mapf.fechaCreacion}',
				                    '${pageScope.mapf.consumible}','${pageScope.mapf.descConsumible}');">
				<ec:column property="codRecurso" title='C&oacute;digo' />
				<ec:column property="nombre" title='Nombre' />
				<ec:column property="nombreCategoria" title='Categor&iacute;a' />
				<ec:column property="descConsumible" title='Consumible' />
				<ec:column property="nombreSubCategoria"
					title='Sub Categor&iacute;a' />
				<ec:column property="fechaCreacion" title='Fecha creaci&oacute;n' />
			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("recursoTableId");
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


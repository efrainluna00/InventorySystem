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
	      	
      	//onclick="tableClick('${pageScope.mapf.codBodega}','${pageScope.mapf.lBodega}','${pageScope.mapf.minimo}');">
	      	function tableClick(codBodega,bodegaDes,minimo){
				$(".error").hide();	
				$("#codBodega").val(codBodega);
				$("#bodegaDes").val(bodegaDes);
				$("#minimo").val(minimo);
				$("#bodegaDes").attr("class","caja_textodisable");
				$("#bodegaDes").attr("size","24");
				$("#bb").hide();
				habilitar();
			}
						
			function habilitar(){
				$('#accionRegresar').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
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
		<bean:message key="minixbod.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="minixbod.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">


		<html:form action="/minimoxBodegaAction.do" styleId="formulario">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="minixbod.codRecurso" />
							:
						</th>
						<td>
							<bean:write name="minimoxBodegaForm" property="codRecurso"
								scope="request" />
							<html:hidden property="codRecurso" styleId="codRecurso" />
							<html:hidden property="codBodeguero" styleId="codBodeguero"
								value="${sessionScope.user}" />
						</td>

						<th style="text-align: left;">
							<bean:message key="minixbod.nombreRecurso" />
							:
						</th>
						<td>
							<bean:write name="minimoxBodegaForm" property="nombreRecurso"
								scope="request" />
							<html:hidden property="nombreRecurso" styleId="nombreRecurso" />
						</td>
					<tr>
						<th style="text-align: left;">
							<bean:message key="minixbod.categoria" />
							:
						</th>
						<td>
							<bean:write name="minimoxBodegaForm" property="categoria"
								scope="request" />
							<html:hidden property="categoria" styleId="categoria" />
						</td>
						<th style="text-align: left;">
							<bean:message key="minixbod.subcategoria" />
							:
						</th>
						<td>
							<bean:write name="minimoxBodegaForm" property="subcategoria"
								scope="request" />
							<html:hidden property="subcategoria" styleId="subcategoria" />
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="minixbod.unidadmedida" />
							:
						</th>
						<td>
							<bean:write name="minimoxBodegaForm" property="unidadmedida"
								scope="request" />
							<html:hidden property="unidadmedida" styleId="unidadmedida" />
						</td>
						<th>
							<bean:message key="minixbod.abvunidadmedida" />
							:
						</th>
						<td>
							<bean:write name="minimoxBodegaForm" property="abvunidadmedida"
								scope="request" />
							<html:hidden property="abvunidadmedida" styleId="abvunidadmedida" />
						</td>
					</tr>
				</table>
				<br>


				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="minixbod.bodega" />
							:
						</th>
						<td>
							<html:hidden property="codBodega" styleId="codBodega"></html:hidden>
							<html:text property="bodegaDes" styleId="bodegaDes" size="30"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>

							<logic:equal name="minimoxBodegaForm" property="isBodeguero"
								value="1">
								<input type="button"
									onclick="abrirVentana('ldvAction.do?xmlArchivo=bodegasMinimosBodeguero&codRecurso='+$('#codRecurso').val()+'&codBodeguero='+$('#codBodeguero').val());"
									value="..." id="bb" />
							</logic:equal>
							<logic:equal name="minimoxBodegaForm" property="isBodeguero"
								value="0">
								<input type="button"
									onclick="abrirVentana('ldvAction.do?xmlArchivo=bodegasMinimos&codRecurso='+$('#codRecurso').val());"
									value="..." id="bb" />
							</logic:equal>

							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="minixbod.codBodega.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="minixbod.minimo" />
						</th>
						<td>
							<html:text property="minimo" styleId="minimo" size="30"
								styleClass="caja_texto_obligatorio"
								onkeydown="return soloEnteros(this, event);"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="minixbod.minimo.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="minixbod.minimo.valido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>

				</table>

				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="minixbod.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="minixbod.mensajeError.error">
					<div id="mensajeError">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessajeE();
		    	  </script>
				</html:messages>
				<br>
				<div id="accionRegresar" class="accion">
					<html:submit property="accion" styleId="insert">
						<bean:message key="opc.insert" />
					</html:submit>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
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
				<br />
				<hr width="85%">
				<br>

				<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

				<ec:table items="listaMinimo" var="mapf" form="formulario"
					action="${pageContext.request.contextPath}/minimoxBodegaAction.do?accion=Find"
					imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
					title='Recursos Solicitados' view="compact" width="80%"
					tableId="tablaMinimos" autoIncludeParameters="yes" scope="request">
					<ec:row highlightRow="true"
						onclick="tableClick('${pageScope.mapf.codBodega}','${pageScope.mapf.bodegaDes}','${pageScope.mapf.minimo}');">
						<ec:column property="bodegaDes" title='Bodega' />
						<ec:column property="minimo" title='M&iacute;nimo' />
					</ec:row>
				</ec:table>
			</center>
			<input type="hidden" name="${requestScope.nombreGrid}_p"
				id="${requestScope.nombreGrid}_p"
				value="${requestScope.numeroPagina}" />
			<input type="hidden" name="${requestScope.nombreGrid}_crd"
				id="${requestScope.nombreGrid}_crd"
				value="${requestScope.cantidadPagina}" />
			<input type="hidden" name="nombreGrid"
				value="${requestScope.nombreGrid}" />
			<input type="hidden" name="numeroPagina"
				value="${requestScope.numeroPagina}" />
			<input type="hidden" name="cantidadPagina"
				value="${requestScope.cantidadPagina}" />
		</html:form>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("tablaMinimos");
		</script>
	</tiles:put>

</tiles:insert>




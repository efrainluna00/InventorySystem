<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>



<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
		
		
		
		
    	var est;
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
	      	
			function tableClick(codRecurso,correlativo,
			nombreRecurso,cantidad,medida){
			  	$(".error").hide();	
				document.getElementById('codRecurso').value = codRecurso;
				document.getElementById('nombreRecurso').value = nombreRecurso;
				document.getElementById('cantidad').value = cantidad;
			   	document.getElementById('correlativo').value = correlativo;
			   $('#btnSum').hide();
			   $('#codRecurso').attr("class","caja_textodisable");
			   
				$('#nombreRecurso').attr("class","caja_textodisable");
				$('#nombreRecurso').attr("size","45");
			   if(est != 'G'){
			   	$('#update').hide();
				$('#delete').hide();
				$('#cantidad').attr("class","caja_textodisable");
				
			   }
			   $("#medida").val(medida);					
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
		<bean:message key="detSum.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="detSum.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">


		<html:form action="/detSolSuministroAction.do">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="detSum.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="detSolSuministroForm" property="codSolicitud"
								scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
							<html:hidden property="correlativo" styleId="correlativo" />
						</td>
						<th style="text-align: left;">
							<bean:message key="detSum.estado" />
							:
						</th>
						<td>
							<bean:write name="detSolSuministroForm" property="desEstado"
								scope="request" />
							<html:hidden property="desEstado" styleId="desEstado" />
							<html:hidden property="estado" styleId="estado" />
							<SCRIPT>
			                	est= document.getElementById('estado').value;
			                </SCRIPT>
						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="detSum.bodega" />
							:
						</th>
						<td>
							<html:hidden property="codBodega" styleId="codBodega" />
							<bean:write name="detSolSuministroForm" property="bodegaDes"
								scope="request" />
							<html:hidden property="bodegaDes" styleId="bodegaDes" />
						</td>
						<th style="text-align: left;">
							<bean:message key="detSum.uniSol" />
							:
						</th>
						<td>
							<html:hidden property="unidadDes" styleId="unidadDes" />
							<bean:write name="usuario" property="primerNom" scope="session"></bean:write>
							<bean:write name="usuario" property="primerApe" scope="session"></bean:write>
						</td>
					</tr>

				</table>
				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="detSum.recurso" />
						</th>
						<td>
							<html:text property="codRecurso" styleId="codRecurso"
								styleClass="caja_texto_obligatorio" readonly="true" size="9"></html:text>
							<html:text property="nombreRecurso" styleId="nombreRecurso"
								size="50" readonly="true" styleClass="caja_texto_obligatorio"></html:text>
							<logic:equal name="detSolSuministroForm" property="estado"
								value="G">
								<input type="button" id="btnSum"
									onclick="abrirVentana('ldvAction.do?xmlArchivo=suministros_x_bodega&codBodega='+document.getElementById('codBodega').value
								+'&codSol='+$('#codSolicitud').val());"
									value="..." />
							</logic:equal>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="detSum.recurso.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="detSum.cantidad" />
						</th>

						<td>
							<html:text property="cantidad" styleId="cantidad"
								styleClass="caja_texto_obligatorio" size="9"
								onkeydown="return soloEnteros(this,event);"></html:text>


							<html:text property="medida" styleId="medida"
								styleClass="caja_textodisable" size="9"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="detSum.cantidad.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
				</table>


				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detSum.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="detSum.mensajeError.error">
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
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>
					<html:submit property="accion" styleId="enviar">
						<bean:message key="opc.enviarSolicitud" />
					</html:submit>
				</div>
				<div id="accionUpdate" class="accion"
					style="display: none; width: 100%;">
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
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
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
			<logic:equal name="detSolSuministroForm" property="estado" value="E">
				<script>
					 $('#update').hide();
					 $('#insert').hide();
					 $('#delete').hide();
								 
				</script>
			</logic:equal>

			<logic:notEqual name="detSolSuministroForm" property="estado"
				value="G">
				<script>
					$('#enviar').hide()
					 $('#insert').hide();
										
				</script>
			</logic:notEqual>
			<logic:empty name="listDetRequisicion" scope="Request">
				<script type="text/javascript" language="javascript">
						$("#enviar").hide();
					</script>
			</logic:empty>

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
		<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

		<ec:table items="listDetRequisicion" var="mapf"
			action="${pageContext.request.contextPath}/detSolSuministroAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Detalle de Suministros Requeridos' view="compact" width="60%"
			tableId="suministros" autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codRecurso}','${pageScope.mapf.correlativo}','${pageScope.mapf.nombreRecurso}',
				'${pageScope.mapf.cantidad}','${pageScope.mapf.medida}');">
				<ec:column property="codRecurso" title='C&oacute;digo Suministro' />
				<ec:column property="nombreRecurso" title='Nombre' />
				<ec:column property="cantidad" title='Cantidad' />


			</ec:row>
		</ec:table>

		<BR>
		<script type="text/javascript">
			agregarPaginacion("suministros");
		</script>
	</tiles:put>

</tiles:insert>






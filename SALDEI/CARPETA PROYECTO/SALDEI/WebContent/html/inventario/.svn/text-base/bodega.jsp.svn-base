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
	      	
			function tableClick(codBodega, nombre,ubicacion,estado,estadoNombre){
			  $(".error").hide();	
				$('#codBodega').val(codBodega);
				$('#nombre').val(nombre);
				$('#ubicacion').val(ubicacion);
				$('#estado').val(estado);
				$('#codBodega').attr('readonly','true');
				
													
				habilitar();
				
			}
			
			function habilitar(){
				
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				$('#codBodega').attr('class','caja_textodisable');
				$('#codBodega').attr('size','24');
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
		<bean:message key="bode.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="bode.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/bodegaAction.do" styleId="formulario">
			<center>
				<br>
				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="bode.codigo" />
						</th>
						<td>
							<html:text property="codBodega" styleId="codBodega" size="30"
								maxlength="1" styleClass="caja_texto_obligatorio"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="bode.codigo.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="bode.ubicacion" />
						</th>
						<td>
							<html:text property="ubicacion" styleId="ubicacion" size="24"
								styleClass="caja_texto"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="bode.nombre" />
						</th>
						<td>
							<html:text property="nombre" styleId="nombre" size="30"
								styleClass="caja_texto_obligatorio"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="bode.nombre.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
						<th>
							<bean:message key="bode.estado" />
						</th>
						<td>
							<html:select property="estado" styleId="estado"
								styleClass="caja_texto_obligatorio">
								<html:option value="A">
									<bean:message key="bode.activa" />
								</html:option>
								<html:option value="I">
									<bean:message key="bode.inactiva" />
								</html:option>
							</html:select>

							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="bode.estado.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>


				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="bode.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="bode.mensajeError.error">
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
					<html:submit property="accion" styleId="delete">
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
		<ec:table items="listBodega" var="mapf"
			action="${pageContext.request.contextPath}/bodegaAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Bodegas' view="compact" width="80%" tableId="bodega"
			autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codBodega}','${pageScope.mapf.nombre}',
									'${pageScope.mapf.ubicacion}','${pageScope.mapf.estado}','${pageScope.mapf.estadoNombre}');">
				<ec:column property="codBodega" title='Codigo' width="20%" />
				<ec:column property="nombre" title='Nombre' />
				<ec:column property="ubicacion" title='Ubicacion' />
				<ec:column property="estadoNombre" title='Estado' />

			</ec:row>
		</ec:table>
		<BR>
	</tiles:put>

</tiles:insert>

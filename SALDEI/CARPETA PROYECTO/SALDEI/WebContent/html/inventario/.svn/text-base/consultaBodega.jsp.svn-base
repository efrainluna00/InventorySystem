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
	      	
      	
	      	function tableClick(nombreRecurso,existencia,correlativo,categoria,
	      	subcat,medidaDes,codActivo,propietario,recurso){
						  $(".error").hide();	
				//document.getElementById('codActivo').value = codActivo;
				document.getElementById('nombreRecurso').value = nombreRecurso;
				document.getElementById('existencia').value = existencia;
				document.getElementById('categoria').value = categoria;
				document.getElementById('subcat').value = subcat;
				document.getElementById('medidaDes').value = medidaDes;
				$("#codRecurso").val(recurso);
				
				//habilitar();
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
	      	
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="aprdetsolaba.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="aprdetsolaba.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">


		<html:form action="/consultaBodegaAction.do" styleId="formulario">
			<center>
				<br />
				<table class="tableEncabezado">
					<tr>


						<th style="text-align: left;">
							<bean:message key="consultabodega.encargado" />
							:
						</th>
						<td>
							<bean:write name="usuario" property="primerNom" scope="session"></bean:write>
							<bean:write name="usuario" property="primerApe" scope="session"></bean:write>
						</td>
						<th>
							<bean:message key="consultabodega.destino" />
							:
						</th>
						<td>
							Bodega de informatica
							<html:hidden property="bodegaDes" styleId="bodegaDes" />
						</td>

					</tr>
				</table>
				<br>


				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="aprdetsolaba.recurso" />
							:
						</th>
						<td>
							<html:hidden property="correlativo" styleId="correlativo"></html:hidden>
							<html:text property="codRecurso" styleId="codRecurso" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
						<th>
							<bean:message key="detprestamo.recurso" />
						</th>
						<td>
							<html:text property="nombreRecurso" styleId="nombreRecurso"
								size="24" styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
						<th>
							<bean:message key="consultabodega.existencia" />
						</th>
						<td>
							<html:text property="existencia" styleId="existencia" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="detprestamo.cat" />
						</th>
						<td>
							<html:text property="categoria" styleId="categoria" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
						<th>

							<bean:message key="detprestamo.subcat" />
						</th>
						<td>

							<html:text property="subcat" styleId="subcat" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
						<th>
							<bean:message key="aprdetsolaba.unidad" />
						</th>
						<td>
							<html:text property="medidaDes" styleId="medidaDes" size="24"
								styleClass="caja_textodisable" readonly="true"></html:text>
						</td>
					</tr>

				</table>

				<br>


				<br />
				<hr width="85%">
				<br>
				<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>
				<ec:table items="listConsultaBodega" var="mapf" form="formulario"
					action="${pageContext.request.contextPath}/consultaBodegaAction.do?accion=Find"
					imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
					title='Recursos y Existencias' view="compact" width="80%"
					tableId="aprSolDesc" autoIncludeParameters="yes" scope="request">
					<ec:row highlightRow="true"
						onclick="tableClick('${pageScope.mapf.nombreRecurso}','${pageScope.mapf.existencia}','${pageScope.mapf.correlativo}',
																'${pageScope.mapf.categoria}','${pageScope.mapf.subcat}','${pageScope.mapf.medidaDes}',
																'${pageScope.mapf.codActivo}','${pageScope.mapf.propietario}',
																'${pageScope.mapf.codRecurso}');">
						<ec:column property="codRecurso" title='C&oacute;digo de Recurso' />
						<ec:column property="nombreRecurso" title='Recurso' />
						<ec:column property="existencia" title='existencia' />
					</ec:row>
				</ec:table>
				<br>
				<br>
				<a href="solicitudAbastecimientoAction.do?accion=Regresar"><bean:message
						key="linkInv.solicitudAbastecimiento" />
				</a>
			</center>
		</html:form>
		<BR>
	</tiles:put>

</tiles:insert>






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
		    	ifFormat   : "%d/%m/%Y",
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
	      	
	      	function executeTableClick(codPresupuesto){	      		    		
	      			document.getElementById(codPresupuesto).onclick();	      			      		
	      	}
	      	
			function tableClick(codPresupuesto, fechaInicial,fechaFinal, estado){
			  	$(".error").hide();	
				$('#codPresupuesto').val(codPresupuesto);
				$('#fechaInicial').val(fechaInicial);
				$('#fechaFinal').val(fechaFinal);
				$('#estado').val(estado);
								
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
	      	
	      	function enviarPpto(accion, manualCode, inicial, final,estado){
	      		var formulario= document.getElementById('formulario');
	            var href = formulario.action;
	            
	            $('#code').val(manualCode);
	            $('#final').val(final);				
	            $('#inicial').val(inicial);
	            $('#estadoPre').val(estado);
	            // para que cuando regrese del formulario hijo conserve los valores seleccionados
		        $("#numeroPagina").val($("input[@name='tpresupuesto_p']").val());
		        $("#cantidadPagina").val($("input[@name='tpresupuesto_crd']").val());	        
		        $("#nombreGrid").val('tpresupuesto');
	                    
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
		<bean:message key="ppto.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="ppto.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/detallePptoAction.do" styleId="formulario">
			<html:hidden property="codPresupuesto" styleId="code" />
			<html:hidden property="fecha_ini" styleId="inicial" />
			<html:hidden property="fecha_fin" styleId="final" />
			<html:hidden property="estadoPre" styleId="estadoPre" />
			<input type="hidden" name="numeroPagina" id="numeroPagina" />
			<input type="hidden" name="nombreGrid" id="nombreGrid" />
			<input type="hidden" name="cantidadPagina" id="cantidadPagina" />
		</html:form>
		<html:form action="/presupuestoAction.do" styleId="formularioPapa">
			<center>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="ppto.fecha_ini" />
						</th>
						<td>
							<html:hidden property="codPresupuesto" styleId="codPresupuesto"></html:hidden>
							<html:text property="fecha_ini" styleId="fechaInicial" size="30"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" value="..." id="fechaInicialbtn"
								onmousedown="verCalen('fechaInicial', 'fechaInicialbtn' )" />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="presupuesto.fechaIni.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="ppto.fecha_fin" />
						</th>
						<td>
							<html:text property="fecha_fin" styleId="fechaFinal" size="30"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
							<input type="button" value="..." id="fechaFinalbtn"
								onmousedown="verCalen('fechaFinal', 'fechaFinalbtn' )" />
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="presupuesto.fechaFin.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="ppto.fecha.error">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="ppto.estado" />
						</th>
						<td>
							<html:select property="estado" styleId="estado"
								styleClass="caja_texto_obligatorio">
								<html:option value="I"> Inactivo</html:option>
								<html:option value="A"> Activo </html:option>
							</html:select>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" property="ppto.estado.error">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="presupuesto.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="ppto.mensajeError.error">
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

			<ec:table items="listPresupuesto" var="mapf"
				action="${pageContext.request.contextPath}/presupuestoAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Presupuestos' view="compact" width="80%"
				tableId="tpresupuesto" form="formularioPapa"
				autoIncludeParameters="yes" scope="request">
				<ec:row highlightRow="true"
					onclick="executeTableClick('lnk${pageScope.mapf.codPresupuesto}');">
					<ec:column property="fecha_ini" title='Fecha Inicial' width="20%" />
					<ec:column property="fecha_fin" title='Fecha Final' />
					<ec:column property="estadoDescr" title='Estado' />
					<ec:column property="pptoLink" title='Acci&oacute;n'>
					${pageScope.mapf.pptoLink}
					<input type="hidden"
							onclick="tableClick('${pageScope.mapf.codPresupuesto}','${pageScope.mapf.fecha_ini}',
																'${pageScope.mapf.fecha_fin}','${pageScope.mapf.estado}');"
							id="lnk${pageScope.mapf.codPresupuesto}" />
					</ec:column>

				</ec:row>
			</ec:table>
			<BR>
		</html:form>
		<logic:present name="flagBack" scope="request">
			<script type="text/javascript" language="javascript">					
				executeTableClick('${requestScope.flagBack}');
			</script>
		</logic:present>

		<script type="text/javascript">
			agregarPaginacion("tpresupuesto");
		</script>
	</tiles:put>

</tiles:insert>


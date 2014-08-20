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
	      	
			function tableClick(idOpcion,idOpcionPadre,opcionPadre,
			nomOpcion,descOpcion,urlOpcion,orden,metodo,
				isSeparador,estadoDescr,estOpcion,visible){
			
				$(".error").text("");
				$('#idOpcion').val(idOpcion);
				$('#idOpcionPadre').val(idOpcionPadre);
				$('#opcionPadre').val(opcionPadre);
				$('#nomOpcion').val(nomOpcion);
				$('#descOpcion').val(descOpcion);
				$('#urlOpcion').val(urlOpcion);
				$('#orden').val(orden);
				$('#metodo').val(metodo);
									
				if(estOpcion=='A'){
					//$('#rdEstadoNo').attr('checked','no');
					$('#rdEstadoSi').attr('checked','yes');
					}
				else{
		
					$("#rdEstadoNo").attr("checked","yes");
					//$("#rdEstadoSi").attr("checked","no");			
				}
				if(isSeparador=='S'){
					//$('#rdSepaNo').attr('checked','no');
					$('#rdSepaSi').attr('checked','yes');
					}
				else{
					$('#rdSepaNo').attr('checked','yes');
					//$('#rdSepaSi').attr('checked','no');			
				}


			if(visible=='S'){
					//$('#rdVisibleNo').attr('checked','no');
					$('#rdVisibleSi').attr('checked','yes');
					}
				else{
					$('#rdVisibleNo').attr('checked','yes');
					//$('#rdVisibleSi').attr('checked','no');			
				}
				habilitar();
			}
			
			function habilitar(){
				$('#accionInsert').SlideOutDown('fast',$('#accionUpdate').SlideInDown('slow'));
				$('#recursoDesc').attr('class','caja_textodisable');
				$('#recursoDesc').attr('size','24');
				//$('#codRecurso').val("");
				$('#btnrecurso').hide();
				$('#descripcion').attr('class','caja_textodisable');
				$('#descripcion').attr('size','24');
				//$('#codUnidad').val("");
				$('#btnunidad').hide();		
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
		<bean:message key="mopc.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="mopc.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/mtoOpcionAction.do" styleId="formulario" >
			<center>
			<html:hidden property="idOpcion" styleId="idOpcion"/>
			<html:hidden property="idOpcionSeguridad" styleId="idOpcionSeguridad"/>
<br>				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="mopc.nomOpcion" />
						</th>
						<td>							
							<html:text property="nomOpcion" styleId="nomOpcion" size="50" 
								styleClass="caja_texto_obligatorio"></html:text>							
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" 
								property="mopc.nomOpcion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" />
								</span>
							</html:messages>
						</td>
						<th>
							<bean:message key="mopc.descOpcion"  />
						</th>
						<td>
							<html:text property="descOpcion" styleId="descOpcion" size="50"														
								styleClass="caja_texto_obligatorio"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera" 
								property="mopc.descOpcion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" />
								</span>
							</html:messages>						
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="mopc.urlOpcion" />
						</th>
						<td>							
							<html:text property="urlOpcion" styleId="urlOpcion" size="50"
								styleClass="caja_texto_obligatorio"></html:text>
							
							<html:messages  id="message" footer="errores.pie"
								header="errores.cabecera"
								property="mopc.urlOpcion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" />
								</span>
							</html:messages>
						</td>
						<th>
							<bean:message key="mopc.orden"  />
						</th>
						<td>
							<html:text property="orden" styleId="orden" size="50"														
								styleClass="caja_texto_obligatorio" onkeydown="return soloEnteros(this,event);"></html:text>
						<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="mopc.orden.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" />
								</span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="mopc.metodo"  />
						</th>
						<td>
							<html:text property="metodo" styleId="metodo" size="50"														
								styleClass="caja_texto_obligatorio"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="mopc.metodo.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" />
								</span>
							</html:messages>
						</td>
												<th>
							<bean:message key="mopc.isSeparador"  />
						</th>
						<td>
							<html:radio property="isSeparador" value="S" styleId="rdSepaSi">    Si    </html:radio>
							<html:radio property="isSeparador" value="N" styleId="rdSepaNo">    No    </html:radio>							
						</td>
					</tr>
					<tr>
					<th><bean:message key="mopc.opcionPadre" /></th>
						<td>
						<html:hidden property="idOpcionPadre" styleId="idOpcionPadre"/>
						<html:text property="opcionPadre" styleId="opcionPadre" size="40" 
						styleClass="caja_texto_disabled" readonly="true"></html:text>
						<input type="button" id="btnPadre" onclick="abrirVentana('ldvAction.do?xmlArchivo=opciones');" value="..."/>
						</td>
						<th><bean:message key="mopc.estOpcion" /></th>
						<td>
							<html:radio property="estOpcion" value="A" styleId="rdEstadoSi">      Activo    </html:radio>
							<html:radio property="estOpcion" value="I" styleId="rdEstadoNo">      Inactivo    </html:radio>	
						</td>
					
						
					</tr>
					<tr style="display:none">
					<td colspan="2"></td>
						<th><bean:message key="mopc.visible" /></th>
						<td>
							<html:radio property="visible" value="S" styleId="rdVisibleSi">      Si    </html:radio>
							<html:radio property="visible" value="N" styleId="rdVisibleNo">      No    </html:radio>	
						</td>
					
					</tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="mopc.mensaje.exito">
					<div id="mensaje" >
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message"  footer="errores.pie"
					header="errores.cabecera" property="activo.mensajeError.error">
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
				</div>
				<div id="accionUpdate" class="accion" style="display: none">
					<html:submit property="accion" styleId="update">
						<bean:message key="opc.update" />
					</html:submit>
					
					<html:button property="accion" value="Desactivar" onclick="enviar('Eliminar')">
						</html:button>
									
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>	
				</div>
				<br/>
				<hr width="85%">
				<logic:equal parameter="accion" value='Actualizar'>
					<script type="text/javascript" language="javascript">
					habilitar();
				</script>
				</logic:equal>

			</center>
			<script language="javascript">
							$("#rdSepaNo").attr("checked","yes");
							$("#rdEstadoSi").attr("checked","yes");
							$("#rdVisibleSi").attr("checked","yes");
			</script>
		</html:form>
		<ec:table items="listaUnidad" var="mapf"
			action="${pageContext.request.contextPath}/mtoOpcionAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Activos' view="compact" width="80%" tableId="opciones"
			autoIncludeParameters="yes" scope="request" >
			<ec:row highlightRow="true" onclick="tableClick('${pageScope.mapf.idOpcion}',
			'${pageScope.mapf.idOpcionPadre}','${pageScope.mapf.opcionPadre}',
			'${pageScope.mapf.nomOpcion}','${pageScope.mapf.descOpcion}','${pageScope.mapf.urlOpcion}',
				'${pageScope.mapf.orden}','${pageScope.mapf.metodo}','${pageScope.mapf.isSeparador}',
				'${pageScope.mapf.estadoDescr}','${pageScope.mapf.estOpcion}','${pageScope.mapf.visible}');">
				<ec:column property="idOpcion" title='Id Opci&oacute;n' width="20%" />
				<ec:column property="nomOpcion" title='Opci&oacute;n' width="20%" />
				<ec:column property="descOpcion" title='Descripci&oacute;n' width="20%" />
				<ec:column property="urlOpcion" title='URL' width="20%" />
				<ec:column property="estadoDescr" title='Estado' width="20%" />
				<ec:column property="visibleDescr" title='Visible' width="20%" />
			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("opciones");
			
				$("form[@id='formulario'] input[@type='text']").keypress( function(e){
					if( $(this).attr("id")!= null && $(this).attr("onkeydown")== null )
						return caracteresValidos($(this),e);
				});
			
				$("form[@id='formulario'] input[@type='text']").change(function(e){
				if( $(this).attr("id")!= null){
					if(!cadenaValida($(this).val()))
						$(this).val("");
				}
				});
				  	$('input[@name="accion"]').attr('class','claseBotonInventario');
		</script>
	</tiles:put>

</tiles:insert>




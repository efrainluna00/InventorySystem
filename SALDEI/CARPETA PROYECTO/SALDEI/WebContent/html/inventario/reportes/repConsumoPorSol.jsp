<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>



<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
    
    	function generarReporte(){
	
			document.forms[0].accion.value = 'rptMovRecurso';
			document.forms[0].submit();
	
	}
	
	 function generarReporteTarget(){
			
			document.forms[0].target = '_blank';
			document.forms[0].accion.value = 'rptMovRecurso';
			document.forms[0].submit();
	
	}
	
	$(document).ready(function(){$.NiceJForms.build()});	
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
	      	

	      	
	      	 
    	function verCalen(field, btn){
				Calendar.setup({
			    inputField : field,
		    	ifFormat   : "%d/%m/%Y",
			    button     : btn
				});
			}
	      	
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="repConSol.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="repConSol.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<html:form action="/repConsumoPorSolAction.do" styleId="formulario">
			<html:hidden property="accion" value="" />
			<center>
				<br>
				<div id="container">
					<br />
					<br />
					<table align="center">
						<tr>
							<td align="center">
								<fieldset>
									<table align="center">
										<tr>
											<th>
												<bean:message key="repConSol.director" />
											</th>
											<td>

												<html:text property="codDirector" styleId="codDirector"
													size="10" readonly="true"
													styleClass="caja_texto_obligatorio"></html:text>

												<html:text property="nombreDirector"
													styleId="nombreDirector" size="35" readonly="true"
													styleClass="caja_texto_obligatorio"></html:text>
												<input id="btnCategoria" type="button"
													onclick="abrirVentana('ldvAction.do?xmlArchivo=directores');"
													value="..." />


											</td>
										</tr>
										<tr>
											<th>
												<bean:message key="repConSol.unidad" />
											</th>
											<td>

												<html:text property="codUnidad" styleId="codUnidad"
													size="10" readonly="true"
													styleClass="caja_texto_obligatorio"></html:text>

												<html:text property="nombreUnidad" styleId="nombreUnidad"
													size="35" readonly="true"
													styleClass="caja_texto_obligatorio"></html:text>
												<input id="btnCategoria" type="button"
													onclick="abrirVentana('ldvAction.do?xmlArchivo=unidades3&codDirector='+document.getElementById('codDirector').value);"
													value="..." />


											</td>
										</tr>
										<tr>
											<th>
												<bean:message key="repConSol.fecha_ini" />
											</th>
											<td>

												<html:text property="fecha_ini" styleId="fechaInicial"
													size="30" readonly="true"
													styleClass="caja_texto_obligatorio"></html:text>
												<input type="button" value="....." id="fechaInicialbtn"
													onmousedown="verCalen('fechaInicial', 'fechaInicialbtn' )"
													class="ButtonText" />
												<html:messages id="message" footer="errores.pie"
													header="errores.cabecera"
													property="repConSol.fecha_ini.requerido">
													<br>
													<span class="error"> <bean:write name="message"
															filter="false" /> </span>
												</html:messages>
											</td>
										</tr>
										<tr>
											<th>
												<bean:message key="repConSol.fecha_fin" />
											</th>
											<td>
												<html:text property="fecha_fin" styleId="fechaFinal"
													size="30" readonly="true"
													styleClass="caja_texto_obligatorio"></html:text>
												<input type="button" value="....." id="fechaFinlbtn"
													onmousedown="verCalen('fechaFinal', 'fechaFinlbtn' )"
													class="ButtonText" />

												<html:messages id="message" footer="errores.pie"
													header="errores.cabecera"
													property="repConSol.rango_invalido">
													<div id="mensaje">
														<bean:write name="message" filter="false" />
													</div>
													<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
												</html:messages>
												<html:messages id="message" footer="errores.pie"
													header="errores.cabecera"
													property="repConSol.fecha_fin.requerido">
													<br>
													<span class="error"> <bean:write name="message"
															filter="false" /> </span>
												</html:messages>
										<tr>
											<th>
												<bean:message key="repConSol.grafico" />
											</th>
											<td>
												<html:select property="grafico" styleId="grafico"
													styleClass="caja_texto_obligatorio">
													<html:option value="S">
									Si&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
								</html:option>
													<html:option value="N">
									No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
								</html:option>

												</html:select>
											</td>
										</tr>





										<tr>
											<td align="left">
												<b>Escoger formato:</b>
											</td>
										</tr>
										<tr>
											<td colspan="3" align="left">
												<input type="radio" name="formato" id="opcion1" value="1"
													checked="true" />
												<label for="opcion1">
													PDF
												</label>
												<br />
												<input type="radio" name="formato" id="opcion2" value="2" />
												<label for="opcion2">
													EXCEL
												</label>
												<br />

											</td>
										</tr>
										<tr></tr>
										<tr>
											<td align="center" colspan="3" style="text-align: center;">
												<input type="button" id="btnRpt" name="btnRpt"
													onclick="generarReporte();" value="Generar Reporte" />


											</td>
										</tr>

									</table>
								</fieldset>
							</td>
						</tr>
					</table>

				</div>



				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="cuenta.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>

			</center>
			<html:hidden property="flagTarget" styleId="flagTarget" />
		</html:form>
		<script type="text/javascript">
			
			if($('#flagTarget').val() == 'false'){
				
				generarReporteTarget();								
			}

		</script>
		<BR>
	</tiles:put>

</tiles:insert>
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
	
	
	
	
	$(document).ready(function(){$.NiceJForms.build()});	
        
        function enviar(accion){
	      		var formulario= document.getElementById('formulario');
            var href = formulario.action;                        		
            
            document.forms[0].codSolicitud.value = document.forms[1].codSolicitud.value;
                    
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
		<bean:message key="repSolCompra.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="repSolCompra.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<html:form action="/repSolicitudCompraAction.do" styleId="formulario"
			target="_blank">
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
												<bean:message key="repSolCompra.codSolicitud" />
											</th>
											<td>
												<html:text property="codSolicitud" styleId="codSolicitud"
													styleClass="caja_texto_obligatorio" readonly="true"
													size="10"></html:text>

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
		</html:form>
		<BR>
	</tiles:put>

</tiles:insert>
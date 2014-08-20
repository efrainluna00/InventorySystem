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
	      	
			function tableClick(codRecurso,correlativo,nombreRecurso,cantidad){
			  	$(".error").hide();	
				document.getElementById('codRecurso').value = codRecurso;
				document.getElementById('nombreRecurso').value = nombreRecurso;
				document.getElementById('cantidad').value = cantidad;
			   	document.getElementById('correlativo').value = correlativo;					
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
	      	
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="aprDetSum.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="aprDetSum.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">


		<html:form action="/detAprSolSuministroAction.do" styleId="formulario">
			<center>
				<br />



				<table class="tableEncabezado">
					<tr>
						<th style="text-align: left;">
							<bean:message key="aprDetSum.codSolicitud" />
							:
						</th>
						<td>
							<bean:write name="detAprSolSuministroForm"
								property="codSolicitud" scope="request" />
							<html:hidden property="codSolicitud" styleId="codSolicitud" />
							<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
							<html:hidden property="correlativo" styleId="correlativo" />
						</td>
						<th style="text-align: left;">
							<bean:message key="aprDetSum.estado" />
							:
						</th>
						<td>
							<bean:write name="detAprSolSuministroForm" property="desEstado"
								scope="request" />
							<html:hidden property="desEstado" styleId="desEstado" />
							<html:hidden property="estado" styleId="estado" />
						</td>

					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="aprDetSum.bodega" />
							:
						</th>
						<td>
							<bean:write name="detAprSolSuministroForm" property="codBodega"
								scope="request" />
							<html:hidden property="codBodega" styleId="codBodega" />
							<bean:write name="detAprSolSuministroForm" property="bodegaDes"
								scope="request" />
							<html:hidden property="bodegaDes" styleId="bodegaDes" />
						</td>
						<th style="text-align: left;">
							<bean:message key="aprDetSum.solicitante" />
							:
						</th>
						<td>
							<bean:write name="detAprSolSuministroForm" property="solicitante"
								scope="request" />
							<html:hidden property="solicitante" styleId="solicitante" />

						</td>
					</tr>
					<tr>
						<th style="text-align: left;">
							<bean:message key="detprestamo.comentario" />
							:
						</th>
						<td colspan="3">
							<bean:write name="detAprSolSuministroForm" property="comentario2"
								scope="request" />
						</td>
					</tr>
				</table>
				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="aprDetSum.comentario" />
						</th>





						<td>
							<html:textarea property="comentario" styleId="comentario"
								cols="50"></html:textarea>

						</td>



					</tr>
				</table>

				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="aprDetSum.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<div id="accionRegresar" class="accion">

					<html:button property="accion" styleId="aprobarbtn"
						onclick="enviar('Aprobar');">
						<bean:message key="opc.aprobar" />
					</html:button>
					<html:button property="accion" styleId="rechazarbtn"
						onclick="enviar('Rechazar');">
						<bean:message key="opc.rechazar" />
					</html:button>
					<html:button property="accion" styleId="anularbtn"
						onclick="
					javascript:
					if(!confirm('¿Esta seguro que desea anular la solicitud?'))
						return false;
					else enviar('Anular');
					">



						<bean:message key="opc.anular" />
					</html:button>
					<html:submit property="accion" styleId="back">
						<bean:message key="opc.back" />
					</html:submit>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>
				</div>

				<logic:notEqual name="detAprSolSuministroForm" property="estado"
					value="E">
					<script>
						$('#aprobarbtn').hide();
						$('#rechazarbtn').hide();
							
						$('#comentario').attr('style','background-color: #CCCCCC');
						$('#comentario').attr('readonly','true');	
						$('#cancel').show();					
					</script>
				</logic:notEqual>
				<logic:equal name="detAprSolSuministroForm" property="estado"
					value="A">
					<script>
						$('#anularbtn').show();		
						$('#cancel').show();									
					</script>
				</logic:equal>
				<logic:notEqual name="detAprSolSuministroForm" property="estado"
					value="A">
					<script>
						$('#anularbtn').hide();											
					</script>
				</logic:notEqual>
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
			<ec:table items="listDetSum" var="mapf"
				action="${pageContext.request.contextPath}/detAprSolSuministroAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Suministros Solicitados' view="compact" width="60%"
				tableId="suministros" autoIncludeParameters="yes" scope="request"
				form="formulario">
				<ec:row highlightRow="true"
					onclick="tableClick('${pageScope.mapf.codRecurso}','${pageScope.mapf.correlativo}','${pageScope.mapf.nombreRecurso}','${pageScope.mapf.cantidad}');">
					<ec:column property="codRecurso" title='C&oacute;digo Recurso'
						width="10%" />
					<ec:column property="nombreRecurso" title='Nombre' />
					<ec:column property="cantidad" title='Cantidad' width="10%" />


				</ec:row>
			</ec:table>
		</html:form>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("suministros");
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






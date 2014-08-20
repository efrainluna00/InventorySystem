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
	      	
			function tableClick(codEstado,descripcion){
			  	$(".error").hide();	
				document.getElementById('codEstado').value = codEstado;	
				document.getElementById('descripcion').value = descripcion;					
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
		<bean:message key="estadoAct.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="estadoAct.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/estadoActivoAction.do" styleId="formulario">
			<center>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="estadoAct.descripcion" />
						</th>
						<td>
							<html:hidden property="codEstado" styleId="codEstado"></html:hidden>
							<html:text property="descripcion" styleId="descripcion" size="30"
								styleClass="caja_texto_obligatorio" maxlength="120"></html:text>
							<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="estadoAct.descripcion.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" /> </span>
							</html:messages>
						</td>
					</tr>
					<tr>
				</table>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="estadoAct.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="estadoAct.mensajeError.error">
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
				<br>
				<hr width="85%">



			</center>
		</html:form>
		<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>
		<ec:table items="listaEstadoActivo" var="mapf"
			action="${pageContext.request.contextPath}/estadoActivoAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Estado de Activos' view="compact" width="55%"
			tableId="motivoBaja" autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codEstado}','${pageScope.mapf.descripcion}');">
				<ec:column property="descripcion" title='Descripci&oacute;n' />
			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("motivoBaja");
				$("form[@id='formulario'] input[@type='text']").keypress( function(e){
					if( $(this).attr("id")!= null && $(this).attr("onkeydown")== null )
						return caracteresValidos($(this),e);
				});
			
			$("form[@id='formulario'] input[@type='text']").change(function(e){
				if( $(this).attr("id")!= null ){
					if(!cadenaValida($(this).val()))
						$(this).val("");
				}
				});
		</script>
	</tiles:put>

</tiles:insert>


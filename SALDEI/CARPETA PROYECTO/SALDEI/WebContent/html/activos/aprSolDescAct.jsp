<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>


<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="scripts" type="String">
		<script type="text/javascript" language="javascript">
    
     function consultarHistorico(accion,mh){
    			
	      		var formulario= document.getElementById('formula');
	      		var href = formulario.action;
	      		
	      		if(mh=='S')
	      			$("#mostrarHistorico").val("N");
	      		else
	      			$("#mostrarHistorico").val("S");
	      			
	      		if(href.indexOf('?') >0 ){
	              href= href.substring(0,href.indexOf('?'));
	            }
	            href+='?accion='+accion;				
	            formulario.action= href;
	                          
	            formulario.submit();					
	      	}
    
    
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
	      	  	
	      	function enviarDetAprSolDesc(accion, manualCode,csolicitante,estado,estadoNombre,solicitante,fecha_creacion,comentario,tipoSolicitud,comentario){
	      		var formulario= document.getElementById('formulario');
	            var href = formulario.action;
	            
	            $('#code').val(manualCode);
	            $('#cods').val(csolicitante);
	            $('#estadoh').val(estado);
	            $('#estadon').val(estadoNombre);
	            $('#sol').val(solicitante);
	            $('#dFechaCreacion').val(fecha_creacion);
	            $('#dComentario').val(comentario);
	            $('#dTipoSolicitud').val(tipoSolicitud);
	            $('#dComentario').val(comentario);
	                    
	            if(href.indexOf('?') >0 ){
	              href= href.substring(0,href.indexOf('?'));
	            }
	            href+='?accion='+accion+'&';				
	            formulario.action= href;
	                          
	            formulario.submit();					
	      	}
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="aprDesc.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="aprDesc.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<html:form action="/detAprSolDescActAction.do" styleId="formulario">
			<html:hidden property="codSolicitud" styleId="code" />
			<html:hidden property="codSolicitante" styleId="cods" />
			<html:hidden property="estado" styleId="estadoh" />
			<html:hidden property="estadoNombre" styleId="estadon" />
			<html:hidden property="solicitante" styleId="sol" />
			<html:hidden property="fecha_creacion" styleId="dFechaCreacion" />
			<html:hidden property="comentario" styleId="dComentario" />
			<html:hidden property="tipoSolicitud" styleId="dTipoSolicitud" />
			<html:hidden property="comentario" styleId="dComentario" />
		</html:form>

		<center>
			<html:form action="/aprSolDescActAction.do" styleId="formula">

				<br>
				<html:hidden property="mostrarHistorico" styleId="mostrarHistorico" />
				<html:button property="accion" value=""
					onclick="consultarHistorico('Find',
							$('#mostrarHistorico').val())"
					styleId="btnHistorico">
				</html:button>
				<script>
							if($('#mostrarHistorico').val()=='N' || $('#mostrarHistorico').val()=='' )
								$("#btnHistorico").val("Mostrar historicos");
							if( $('#mostrarHistorico').val()=='S' )
								$("#btnHistorico").val("Pendientes");
								
							</script>
				<br>

				<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

				<ec:table items="listSolDesc" var="mapf"
					action="${pageContext.request.contextPath}/aprSolDescActAction.do?accion=Find"
					imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
					title='Solicitudes' view="compact" width="60%"
					tableId="solicitudesDesc" autoIncludeParameters="yes"
					scope="request" form="formula">
					<ec:row highlightRow="true"
						onclick="tableClick('${pageScope.mapf.codSolicitud}','${pageScope.mapf.codSolicitante}','${pageScope.mapf.solicitante}',
			'${pageScope.mapf.estado}','${pageScope.mapf.estadoNombre}','${pageScope.mapf.comentario}');">
						<ec:column property="codSolicitud" title='Solicitud No.'
							width="20%" />
						<ec:column property="solicitante" title='Hecha Por' />
						<ec:column property="estadoNombre" title='Estado' />
						<ec:column property="fecha_creacion"
							title='Fecha de creaci&oacute;n' />
						<ec:column property="aprSolDescActLink" title='Acci&oacute;n' />
					</ec:row>
				</ec:table>


				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="cuenta.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
				<br>

			</html:form>

		</center>

		<BR>
		<script type="text/javascript">
			agregarPaginacion("solicitudesDesc");
		</script>

	</tiles:put>

</tiles:insert>



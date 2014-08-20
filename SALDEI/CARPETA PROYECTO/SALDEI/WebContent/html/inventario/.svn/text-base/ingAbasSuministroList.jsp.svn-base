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
	      		var formulario= document.getElementById('formulario');
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
	      	
	      	
	      	function enviarSolicitud(accion, codSolicitud, tipoSolicitud){
	      		var formulario= document.getElementById('formulario');
	            var href = formulario.action;
	            
	            $('#codSolicitud').val(codSolicitud);
	            $('#tipoSolicitud').val(tipoSolicitud);
	                    
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
		<bean:message key="ingSolAbas.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="ingSolAbas.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/ingAbasSuministroAction.do" styleId="formulario">

			<html:hidden property="codSolicitud" styleId="codSolicitud" />
			<html:hidden property="tipoSolicitud" styleId="tipoSolicitud" />
			<html:hidden property="mostrarHistorico" styleId="mostrarHistorico" />
			<br>
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
			<br>
		</html:form>

		<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

		<ec:table items="listSolAbasAprobadas" var="mapf"
			action="${pageContext.request.contextPath}/ingAbasSuministroAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Solicitudes de Abastecimiento Aprobadas' view="compact"
			width="80%" tableId="solAbasApr" autoIncludeParameters="yes"
			scope="request">
			<ec:row highlightRow="true">
				<ec:column property="id.codSolicitud" title='Solicitud' />
				<ec:column property="nombre" title='Solicitante' />
				<ec:column property="desBodega" title='Bodega' />
				<ec:column property="fechaCreacion" title='Fecha de Creaci&oacute;n'
					format="dd/MM/yyyy HH:mm:ss" cell="date" />
				<ec:column property="estadoDesc" title='Estado'
					filterCell="droplist" width="15" />
				<ec:column property="linkIngSolicitudAbas" title="Acci&oacute;n"
					filterable="false"></ec:column>
			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("solAbasApr");
		</script>

	</tiles:put>

</tiles:insert>



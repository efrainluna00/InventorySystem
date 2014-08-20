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
	      	
	      	function mostrarDetRequisicion(accion, manualCode,codBodega,bodDes,tipoSol,solicitante
	      	,motivoRechazo,comentario){
	      		var formulario= document.getElementById('formulario');
	            var href = formulario.action;
	            
	            $('#code').val(manualCode);
	            $('#sol').val(solicitante);
	            $('#bod').val(codBodega);
	            $('#bodDes').val(bodDes);
	            $('#tipoSol').val(tipoSol);
	            if(comentario!='null')
	            	$('#comentario2').val(comentario);
	            else
	            	$('#comentario2').val("");
	            if(motivoRechazo!='null')
	            	$('#motivoRechazo').val(motivoRechazo);
	            else
	            	$('#motivoRechazo').val("");
	            
	                                
	            if(href.indexOf('?') >0 ){
	              href= href.substring(0,href.indexOf('?'));
	            }
	            href+='?accion='+accion+'&';				
	            formulario.action= href;
	                          
	            formulario.submit();					
	      	}
	      	
	      	function tableClick(solicitante){
	      		$("#solicitante").val(solicitante);
	      		$("#sol").val(solicitante);
	      		$(".error").hide();	
	      	}
	      	
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="aprReq.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="aprReq.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">

		<html:form action="/aprDetReqRecursoAction.do" styleId="formulario">
			<html:hidden property="codSolicitud" styleId="code" />
			<html:hidden property="solicitante" styleId="sol" />
			<html:hidden property="codBodega" styleId="bod" />
			<html:hidden property="bodegaDes" styleId="bodDes" />
			<html:hidden property="tipoSolicitud" styleId="tipoSol" />
			<html:hidden property="motivoRechazo" styleId="motivoRechazo" />
			<html:hidden property="comentario2" styleId="comentario2" />
		</html:form>

		<html:form action="/aprReqRecursoAction.do" styleId="formula">
			<html:hidden property="solicitante" styleId="solicitante" />
			<center>
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
			</center>

			<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>

			<ec:table items="listSolicitud" var="mapf"
				action="${pageContext.request.contextPath}/aprReqRecursoAction.do?accion=Find"
				imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
				title='Requisiciones de Recursos' view="compact" width="80%"
				tableId="solicitudes" autoIncludeParameters="yes" scope="request"
				form="formula">
				<ec:row highlightRow="true"
					onclick="tableClick('${pageScope.mapf.solicitante}','${pageScope.mapf.motivoRechazo}');">
					<ec:column property="codSolicitud" title='Requisici&oacute;n No.' />
					<ec:column property="solicitante" title='Solicitante' />
					<ec:column property="fecha_creacion" title='Fecha Creaci&oacute;n' />
					<ec:column property="estadoDes" title='Estado' />
					<ec:column property="fecha_aprobacion"
						title='Fecha Aprobaci&oacute;n' />

					<ec:column property="detRequisicionLink" title='Acci&oacute;n' />

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

		</html:form>
		<script type="text/javascript">
			agregarPaginacion("solicitudes");
		</script>

		<BR>
	</tiles:put>

</tiles:insert>

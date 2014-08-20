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
	      	
			function tableClick(codSolicitud,estadoDes,codUnidad,unidadDes,codBodega,bodegaDes,tipoSolicitud,comentario,estado,fecha_creacion,fecha_anulacion, fecha_finalizacion, fecha_aprobacion, motivoRechazo, motivoAnulacion){
			  	$(".error").hide();	
				$('#codSolicitud').val(codSolicitud);
				$('#estadoDes').val(estadoDes);
				$('#codUnidad').val(codUnidad);
				$('#unidadDes').val(unidadDes);
				$('#codBodega').val(codBodega);
				$('#bodegaDes').val(bodegaDes);
				$('#tipoSolicitud').val(tipoSolicitud);
				$('#comentario').val(comentario);
				$('#estado').val(estado);
				$('#fecha_creacion').val(fecha_creacion);
			    $('#fecha_anulacion').val(fecha_anulacion);
				$('#fecha_finalizacion').val(fecha_finalizacion);
				$('#fecha_aprobacion').val(fecha_aprobacion);
				$('#motivoRechazo').val(motivoRechazo);
				$('#motivoAnulacion').val(motivoAnulacion);
				habilitar();
				if (estado == "G"){
					 $('#update').show();
					 $('#insert').show();
					 $('#delete').show();
			   }else{
			   		 $('#update').hide();
					 $('#insert').hide();
					 $('#delete').hide();
			   }
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
	      	
	      	function mostrarDetRequisicion(accion, manualCode,codBodega,bodDes,tipoSol){
	      		var formulario= document.getElementById('formulario');
	            var href = formulario.action;
	            
	            $('#code').val(manualCode);
	            $('#bod').val(codBodega);
	            $('#bodDes').val(bodDes);
	            $('#tipoSol').val(tipoSol);
	                    
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
		<bean:message key="movSuministro.titulo" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="movSuministro.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/detSolSuministroAction.do" styleId="formulario">
			<html:hidden property="codSolicitud" styleId="code" />
			<html:hidden property="codBodega" styleId="bod" />
			<html:hidden property="bodegaDes" styleId="bodDes" />
			<html:hidden property="tipoSolicitud" styleId="tipoSol" />
		</html:form>
		<script type="text/javascript" language="javascript">
					  	$('input[@name="accion"]').attr('class','claseBotonInventario');
    					//$('input[@type="button"]').attr('class','claseBotonInventario');
				</script>
		<ec:table items="listSolicitud" var="mapf"
			action="${pageContext.request.contextPath}/movSolSuministroAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='Solicitudes de Suministros' view="compact" width="80%"
			tableId="solicitudes" autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.codSolicitud}','${pageScope.mapf.estadoDes}','${pageScope.mapf.codUnidad}',
				                     '${pageScope.mapf.unidadDes}'  ,'${pageScope.mapf.codBodega}','${pageScope.mapf.bodegaDes}',
				                     '${pageScope.mapf.tipoSolicitud}','${pageScope.mapf.comentario}','${pageScope.mapf.estado}','${pageScope.mapf.fecha_creacion}'
				                     ,'${pageScope.mapf.fecha_anulacion}','${pageScope.mapf.fecha_finalizacion}','${pageScope.mapf.fecha_aprobacion}','${pageScope.mapf.motivoRechazo}'
				                     ,'${pageScope.mapf.motivoAnulacion}');">
				<ec:column property="codSolicitud" title='Solicitud No.' />
				<ec:column property="fecha_creacion" title='Fecha' />
				<ec:column property="estadoDes" title='Estado' />
				<ec:column property="detRequisicionLink" title='Accion' />

			</ec:row>
		</ec:table>
		<BR>
	</tiles:put>

</tiles:insert>


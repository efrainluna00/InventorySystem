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
	      	
	      	      		
			function tableClick(idUsuario,titulo,abvTitulo,porDefecto,
			correlativo,responsableDescr,porDefectoDescr){
			
			document.getElementById('codResponsable').value = idUsuario;
			document.getElementById('titulo').value = titulo;
			document.getElementById('abvTitulo').value = abvTitulo;
			document.getElementById('correlativo').value = correlativo;
				if(porDefecto=='S'){
				$("#rdSi").attr("checked","yes");
				
				}
				else{
				$("#rdNo").attr("checked","yes");
				}
		
			document.getElementById('responsableDescr').value = responsableDescr;
			$("#responsableDescr").attr("class","caja_textodisable");
			$("#responsableDescr").attr("size","24");
			$("#btnRespo").hide();
			$(".error").hide();
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

	   var flag = "in";
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
	      	
	      	function setearIdUsuario(){
	      		$("#idUsuario").val($("#codResponsable").val());
	      		return true;
	      	}
		
    </script>
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="uti.titulo2" />
	</tiles:put>

	<tiles:put name="encabezado" type="String">
		<bean:message key="uti.encabezado" />
	</tiles:put>

	<tiles:put name="contenido" type="String">
		<html:form action="/usuarioTituloAction.do" styleId="formularioUnidad">
			<center>

				<table class="tableDefault">
					<tr>
						<th>
							<bean:message key="uti.usuario" />
						</th>
						<td>
						
						<html:hidden property="codResponsable" styleId="codResponsable"></html:hidden>
						<html:text property="responsableDescr" styleId="responsableDescr" size="30"
								styleClass="caja_texto_obligatorio" readonly="true"></html:text>
						<input type="button" id="btnRespo" onclick="abrirVentana('ldvAction.do?xmlArchivo=unidadResponsable');" value="..."/>
						<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="uti.usuario.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" />
								</span>
							</html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="uti.titulo" />
						</th>
						<td>
							<html:text property="titulo" styleId="titulo" size="30"
								styleClass="caja_texto_obligatorio" maxlength="160"></html:text>
								<html:messages id="message" footer="errores.pie"
								header="errores.cabecera"
								property="uti.titulo.requerido">
								<br>
								<span class="error"> <bean:write name="message"
										filter="false" />
								</span>
							</html:messages>
						</td>
					</tr>
						<tr>
						<th>
							<bean:message key="uti.abreviatura" />
							
						</th>
						<td>				
				<html:text property="abvTitulo" styleId="abvTitulo" size="22"
								styleClass="caja_texto_disable"></html:text>	
									<br>	
				</td>
					</tr>
				
						<tr>
						<th>
							<bean:message key="uti.pd" />
						</th>
						<td>
						<html:radio property="porDefecto" value="S" styleId="rdSi">Si</html:radio>
						<html:radio property="porDefecto" value="N" styleId="rdNo">No</html:radio>
									
						<html:hidden property="correlativo" styleId="correlativo"></html:hidden>
						<script language="javascript">
							$("#rdNo").attr("checked","yes");
						</script>
						<br>	
						</td>
						
					</tr>
<tr>
					
				</table>
				
				
				<html:messages id="message" footer="errores.pie"
					header="errores.cabecera" property="uti.mensaje.exito">
					<div id="mensaje">
						<bean:write name="message" filter="false" />
					</div>
					<script type="text/javascript" language="javascript">
		    	  	pulsateMessaje();
		    	  </script>
				</html:messages>
					<html:messages id="message"  footer="errores.pie"
					header="errores.cabecera" property="uti.mensajeError.error">
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
					<html:submit property="accion" styleId="delete">
						<bean:message key="opc.delete" />
					</html:submit>
					<html:submit property="accion" styleId="cancel">
						<bean:message key="opc.cancel" />
					</html:submit>
				</div>
				<br>
				<hr width="85%">
				<logic:equal parameter="accion" value='Actualizar'>
					<script type="text/javascript" language="javascript">
					habilitar();
				</script>
				</logic:equal>

			</center>
		</html:form>
			<ec:table items="listaUsuarioTitulo" var="mapf"
			action="${pageContext.request.contextPath}/usuarioTituloAction.do?accion=Find"
			imagePath="${pageContext.request.contextPath}/images/table/compact/*.gif"
			title='T&iacute;tulos' view="compact" width="80%" tableId="titulos"
			autoIncludeParameters="yes" scope="request">
			<ec:row highlightRow="true"
				onclick="tableClick('${pageScope.mapf.idUsuario}','${pageScope.mapf.titulo}','${pageScope.mapf.abvTitulo}',
				'${pageScope.mapf.porDefecto}','${pageScope.mapf.correlativo}','${pageScope.mapf.responsableDescr}'
				,'${pageScope.mapf.porDefectoDescr}');">
				<ec:column property="idUsuario" title='C&oacute;digo de usuario'  />
				<ec:column property="responsableDescr" title='Usuario'  />
				<ec:column property="titulo" title='T&iacute;tulo'/>
				<ec:column property="abvTitulo" title='Abrv. T&iacute;tulo' />
                <ec:column property="porDefectoDescr" title='Opci&oacute;n por defecto'/>
           
			</ec:row>
		</ec:table>
		<BR>
		<script type="text/javascript">
			agregarPaginacion("titulos");
		</script>
	</tiles:put>

</tiles:insert>



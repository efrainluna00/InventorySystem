<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
    </script>
  </tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="login.titulo" />
	</tiles:put>	
  
	<tiles:put name="encabezado" type="String">
		<bean:message key="login.encabezado" />    
	</tiles:put>
	
	<tiles:put name="contenido" type="String">
		<html:form action="/login.do" styleId="formulario" >						
			<center>
				<table class="tableLogin"  >
					<tr>
						<th>
							<bean:message key="ppto.fecha_ini" />
						</th>
						<td>
							<html:text property="ppto_fecha_ini"></html:text>
							<html:messages id="message" footer="errores.pie"  header="errores.cabecera" property="login.username.requerido">
					          <br><span class="error" > <bean:write name="message" filter="false" /></span>
					        </html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="ppto.fecha_fin" />
						</th>
						<td>
							<html:password property="ppto_fecha_fin"></html:password>
							<html:messages id="message" footer="errores.pie" header="errores.cabecera" property="login.password.requerido">
					          <br><span class="error" > <bean:write name="message" filter="false" /> </span>
					        </html:messages>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="ppto.estado" />
						</th>
						<td>
							<html:password property="ppto_estado"></html:password>
							<html:messages id="message" footer="errores.pie" header="errores.cabecera" property="login.password.requerido">
					          <br><span class="error" > <bean:write name="message" filter="false" /> </span>
					        </html:messages>
						</td>
					</tr>
				</table>				
				<html:button  property="accion" styleId="ingresar" onclick="javascript:enviar('login');" >
					<bean:message key="login.ingresar"/>
				</html:button>
				<html:messages id="message" footer="errores.pie" header="errores.cabecera" property="login.fail.password">
		          <br><span class="error" > <bean:write name="message" filter="false" /> </span>
		        </html:messages>
		        <html:messages id="message" footer="errores.pie" header="errores.cabecera" property="login.fail.username">
		          <br><span class="error" > <bean:write name="message" filter="false" /> </span>
		        </html:messages>
				<hr width="80%"  >
			</center>
		</html:form>		
		<br>
	</tiles:put>

</tiles:insert>


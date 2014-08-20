<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>

<%@ page import="java.util.ResourceBundle,org.apache.struts.Globals,java.util.Locale;"%>


<tiles:insert definition="listaDeValores">
	<tiles:put name="base">
		<html:base />
	</tiles:put>
	<tiles:put name="title">
		<bean:message key="lov.titulo" />
	</tiles:put>

	<tiles:put name="scripts" type="string">
		<script>
  		  		  		
  		function seleccionar(){
  			var checks = $('[@name = "ck_"]');  			  			
  			var k = <bean:write name="numColumn"/>;  			
  			while(k > 0){
  				var strvalue = "";
  				var strname = "";
	  			for (i= 0; i < checks.length;i+=1){
	  				var elemento = checks[i];
	  				var valores = elemento.value;  				
	  				if(elemento.checked){  					
	  					//while(valores.length > 0){  						
	  						var value = valores.substr(0,valores.indexOf(","));	  							  					
	  						strname = value.substr(0,value.indexOf("="));
	  						strvalue = strvalue+value.substr(value.indexOf("=")+1)+',';
	  						$('[@name = "ck_"]')[i].value = valores.substr(valores.indexOf(",")+1);
	  						//opener.document.getElementById(value.substr(0,value.indexOf("="))).value = value.substr(value.indexOf("=")+1);  						
	  					//}  					  				
	  				}
	  			}
	  			k = k-1;
	  			if(strname != ''){	  			
  					opener.document.getElementById(strname).value = strvalue;
  					if(opener.document.getElementById(strname).onchange != null){
  						opener.document.getElementById(strname).onchange();
  					}
  				}
  			}
  			
  			window.close();  			
  		}
  	</script>

	</tiles:put>

	<tiles:put name="contenido" type="string">
		<br>
		<logic:equal value="true" name="multiple" scope="request">
			<input type="button" id="btnSelect" onclick="seleccionar();"
				value='Seleccionar' />
			<ec:table items="map" var="mapf"
				action="${pageContext.request.contextPath}/ldvAction.do"
				imagePath="${pageContext.request.contextPath}/images/table/*.gif"
				title='Lista de Valores - ${requestScope.nombreLista}'
				view="compact" width="80%" tableId="ldv" rowsDisplayed="5">
				<ec:row interceptor="marker" highlightRow="true">
					<ec:columns
						autoGenerateColumns="org.extremecomponents.controller.AutoGenerateColumnsImpl" />
				</ec:row>
			</ec:table>
		</logic:equal>
		<logic:notEqual value="true" name="multiple" scope="request">
			<ec:table items="map" var="mapf"
				action="${pageContext.request.contextPath}/ldvAction.do"
				imagePath="${pageContext.request.contextPath}/images/table/*.gif"
				title='Lista de Valores - ${requestScope.nombreLista}'
				view="compact" width="80%" tableId="ldv">
				<ec:row interceptor="marker" highlightRow="true">
					<ec:columns
						autoGenerateColumns="org.extremecomponents.controller.AutoGenerateColumnsImpl" />
				</ec:row>
			</ec:table>
		</logic:notEqual>
		<script type="text/javascript">
			agregarPaginacion("ldv");
		</script>
	</tiles:put>
</tiles:insert>

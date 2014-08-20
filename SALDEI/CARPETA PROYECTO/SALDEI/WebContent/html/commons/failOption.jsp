<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>


<tiles:insert name="plantillaStandar" definition="plantillaStandar">

	<tiles:put name="base">
		<html:base />
	</tiles:put>

	<tiles:put name="title" type="String">
		<bean:message key="acceso.denegado.titulo" />
	</tiles:put>
	
	<tiles:put name="encabezado" type="String">
		
	</tiles:put>
	
	<tiles:put name="contenido" type="String">
		<form>							
			<center>				
				<img src="<%=request.getContextPath()%>/images/login/User2.png" align="middle" />
				<br><span class="error" style="font-size:20pt;" > <bean:message key="acceso.denegado" /> </span>														       
				<hr width="80%"  >
			</center>
		</form>		
		<br>
	</tiles:put>

</tiles:insert>


<%@ include file="/html/commons/css.jsp" %>
<%@ page import="com.saldei.util.commons.Menu" %>
<%
	String menu      = (String) session.getAttribute("Menu");
	if (menu == null || menu.equals(""))
	{
		String strPerfil = (String) session.getAttribute("idPerfil");
		menu         =  Menu.getMenu(strPerfil);
		session.setAttribute("Menu", menu);
	}
	String user  = (String) session.getAttribute("user"); 
%>

    	
   
	
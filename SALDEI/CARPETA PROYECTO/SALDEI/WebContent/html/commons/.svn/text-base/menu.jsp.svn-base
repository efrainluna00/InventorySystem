<%@ include file="/html/commons/css.jsp" %>
<%@ page import="com.saldei.util.commons.Menu" %>
<%
	String strMenu      = (String) session.getAttribute("Menu");
	if (strMenu == null || strMenu.equals(""))
	{
		String strPerfil = (String) session.getAttribute("idPerfil");
		strMenu         =  Menu.getMenu(strPerfil);
		session.setAttribute("Menu", strMenu);
	}
%>
<br>
 <div class='sample'>
            <b class="b1"></b>
            <b class="b2"></b>
            <b class="b3"></b>
            <b class="b4"></b>
            <div class="bcontent">
<%= strMenu %> 
<br><br><br><br><br><br>
            </div>
            <b class="b4"></b>
            <b class="b3"></b>
            <b class="b2"></b>
            <b class="b1"></b>     
        </div>
       <br> 
    	
   
	
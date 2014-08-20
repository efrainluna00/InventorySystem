<%@ include file="library.jsp"%>
<%@ include file="css.jsp"%>
<div class="HeaderBar">
<table cellpadding="0" cellspacing="0" border="0" width="100%" >
	<tr>		
		<td width="34%" align="right"><fmt:formatDate value="${now}"
			pattern="dd 'de' MMMM 'de' yyyy" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td width="33%" align="left">		
		<a href="javascript:document.location = 'SignOutAction.do';" class="Links" >
		[Salir de Aplicacion]</a>
		</td>
	</tr>
</table>
</div>


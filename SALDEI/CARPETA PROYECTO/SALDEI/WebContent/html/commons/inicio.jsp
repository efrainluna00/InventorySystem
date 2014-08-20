<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/library.jsp"%>
<%@ include file="/html/commons/css.jsp"%>
<%@ include file="/html/commons/js.jsp"%>
<%@ include file="/html/commons/commons.jsp"%>
<style type="text/css" media="all">
@import url("${ctx}/css/main.css");
</style>
<html:html>
<head>

	<style type="text/css" media="all">
.warning {
	position: absolute;
	width: 100%;
	text-align: center;
	font-family: verdana, arial, helvetica, sans-serif;
	font-size: 9px;
	float: left;
	font-weight: bolder;
	margin-bottom: -85px;
	z-index: 10;
}

.mensaje {
	font-family: Helvetica;
	font-size: 10pt;
	font-weight: bolder;
	width: 50%;
	border-color: blue;
	background: url("${ctx}/images/login/fondo.png") #FFFFFF repeat-x;
	background-position: bottom;
	border: double;
	position: relative;
}
</style>
	<script type="text/javascript"
		src="${ctx}/js/jquery/jquery-1.2.3.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/js/jquery/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.kwicks.js"></script>
	<script type="text/javascript">
			$().ready(function() {
				$('.kwicks').kwicks({
					maxWidth : 398,  // required attribute
					spacing : 7
				});
			});
			
			var flag = "in";
	      	function mostrarMessaje(){	                        	        	       
	          		$('#mensaje').show("slow");
	          		
	      	}
	      	
	      	function ocultarMessaje(){	                        	        	       
	          		$('#mensaje').hide("slow");
	          		
	      	}
      	
</script>
	<title><bean:message key="commons.lbl.titulo" />
	</title>
</head>
<body onload="document.forms[0].btnSave.disabled = false;">
	<html:form action="/opcion" styleClass="niceform">
		<html:hidden property="cmd" value="" />
		<div id="wrapper">
			<div id="header">
				<table>
					<tr>
						<td
							style="width: 25px; background-color: #000000; text-align: center; vertical-align: middle;">
							<img src="${ctx}/images/login/SALDEI.png" />
						</td>
						<td
							style="width: 825px; text-align: center; vertical-align: middle;">
							<img src="${ctx}/images/uca/labos.PNG" />
						</td>

						<td style="width: 100px; background-color: #000000;">
							<table>
								<tr>
									<td style="vertical-align: middle; text-align: center;"
										colspan="2">
										<img src="${ctx}/images/login/User1.png" alt="<%=user%>" />
									</td>
								</tr>
								<tr>
									<td
										style="font-size: 14px; font-weight: bold; vertical-align: middle;">
										Usuario:
									</td>
									<td
										style="font-size: 18px; color: #439FD7; font-weight: bold; vertical-align: middle;">
										<%=user%></td>
								</tr>
								<tr>
									<td
										style="font-size: 18px; color: #439FD7; font-weight: bold; vertical-align: middle;">
										<a href='/SALDEI/common.do?cmd=bienvenida'> <img
												src="${ctx}/images/login/HomePage1.png" alt="Inicio" />
										</a>
									</td>
									<td
										style="font-size: 18px; color: #439FD7; font-weight: bold; vertical-align: middle;">
										<a href='/SALDEI/common.do?cmd=inicio'> <img
												src="${ctx}/images/login/Shutdown2.png" alt="Salir SALDEI" />
										</a>
									</td>
								</tr>

							</table>
						</td>
					</tr>
					<tr>
						<td style="height: 39px;">
							<div id="menu" style="margin-left: -6px;">
								<%=menu%>
							</div>
						</td>
					</tr>
				</table>

			</div>
			<div id="container"
				style="background-color: #000000; vertical-align: middle;">
				<logic:present name="mensajeSolPendientes" scope="request">
					<div class="warning" id="mensaje" style="display: none;">
						<center>
						<div id="btnclose" class="mensaje" style="text-align: right;">
							Solicitudes Pendientes &nbsp &nbsp
							<img src="images/icons/close.png" onclick="ocultarMessaje();" />
						</div>
						<div class="mensaje">
							<bean:write name="mensajeSolPendientes" scope="request"
								filter="false" />
						</div>
						<script type="text/javascript">
							mostrarMessaje();
						</script>
					</div>
				</logic:present>



				<table style="z-index: 2;">
					<tr>
						<td style="width: 25%;"></td>
						<td style="width: 50%;">
							<ul class="kwicks">
								<li id="kwick_meet"></li>
								<li id="kwick_kwicks"></li>
								<li id="kwick_for"></li>
								<li id="kwick_jquery"></li>
								<li id="kwick_jquery1"></li>
							</ul>
						</td>
						<td style="width: 25%;"></td>
					</tr>
				</table>


			</div>
			<div id="footer">
				<img src="${ctx}/images/login/foot.PNG" />
				<br />
				Version 1.0
			</div>
		</div>
	</html:form>
</body>
</html:html>

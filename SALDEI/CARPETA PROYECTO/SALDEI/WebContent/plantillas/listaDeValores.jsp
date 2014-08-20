<%@ include file="/html/commons/library.jsp"%>
<%@ page contentType="text/html;charset=windows-1252"%>
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=windows-1252">

		<title><tiles:getAsString name="title" /></title>

		<link href="<%=request.getContextPath()%>/css/extremecomponents.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/default.css"
			rel="stylesheet" type="text/css" />

		<script type="text/javascript" language="Javascript"
			src="<%=request.getContextPath()%>/js/jquery-1.1.4.js"></script>

		<script type="text/javascript" language="Javascript">
     	function agregarPaginacion(idTabla){	       
	      	var numeroPaginacion = $('select[@name="'+idTabla+'_rd"]').val();
    		$('select[@name="'+idTabla+'_rd"]').append('<option value="20">20</option>');
			$('select[@name="'+idTabla+'_rd"]').append('<option value="25">25</option>');
			$('select[@name="'+idTabla+'_rd"]').append('<option value="30">30</option>');
			$('select[@name="'+idTabla+'_rd"]').val(numeroPaginacion);
          }
     </script>

		<tiles:getAsString name="css" />
		<tiles:getAsString name="scripts" />
	</head>
	<body>
		<center>
			<div id="pageLov">
				<div id="bannerLov">

					<div style="background-color: #000000; width: 100%; height: 100px;">
						<center>
							<div
								style="background: url(<%=request.getContextPath()%>/ images/ banner/ fondo2 . png ) repeat-x; width: 100%; height: 90px;">
								<img src="<%=request.getContextPath()%>/images/login/SALDEI.png" />
								<br>
							</div>
						</center>
					</div>
					<br>
					<tiles:getAsString name="banner" />
				</div>
				<div id="contenidoLov">
					<div id="bodyLov">
						<br>
						<tiles:insert name="contenido" />
					</div>
				</div>
			</div>
		</center>
	</body>
</html>

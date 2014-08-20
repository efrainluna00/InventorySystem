<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ include file="/html/commons/commons.jsp"%>
<%@ include file="/html/commons/library.jsp"%>
<%@ include file="/html/commons/css.jsp"%>
<%@ include file="/html/commons/js.jsp"%>


<html:html>
<head>
	<meta http-equiv="Content-Type"
		content="text/html; charset=windows-1252" />

	<meta http-equiv="Expires" content="0">

	<meta http-equiv="Last-Modified" content="0">

	<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">

	<meta http-equiv="Pragma" content="no-cache">



	<title><tiles:getAsString name="title" />
	</title>

	<link href="<%=request.getContextPath()%>/css/extremecomponents.css"
		rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/default.css"
		rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="${ctx}/js/calendar/calendar.js"></script>
	<script type="text/javascript"
		src="${ctx}/js/calendar/lang/calendar-es.js"></script>
	<script type="text/javascript"
		src="${ctx}/js/calendar/calendar-setup.js"></script>
	<script type="text/javascript"
		src="${ctx}/js/calendar/calendar_helper.js"></script>

	<tiles:getAsString name="css" />

	<script type="text/javascript" language="Javascript"
		src="<%=request.getContextPath()%>/js/extremecomponents.js"></script>

	<script type="text/javascript" language="Javascript"
		src="<%=request.getContextPath()%>/js/interface.js"></script>

	<script type="text/javascript" language="Javascript">
	      function abrirVentana(direccion){
	        window.open(direccion,'_blank','scrollbars=yes,directories=no,resizable=yes,channelmode=no,titlebar=no,location=no, width=500,height=500','false');
	      }
	      
	      function abrirVentana(direccion,Wwidth,Wheight){
	      	Wwidth=(Wwidth==undefined)?500:Wwidth;
	        Wheight=(Wheight==undefined)?500:Wheight;	        								
	        window.open(direccion,'_blank','scrollbars=yes,directories=no,resizable=yes,channelmode=no,titlebar=no,location=no, width='+Wwidth+',height='+Wheight,'false');
	      }
 		
 		window.history.forward();
 		   
    	function agregarPaginacion(idTabla){	       
	      	var numeroPaginacion = $('select[@name="'+idTabla+'_rd"]').val();
    		$('select[@name="'+idTabla+'_rd"]').append('<option value="20">20</option>');
			$('select[@name="'+idTabla+'_rd"]').append('<option value="25">25</option>');
			$('select[@name="'+idTabla+'_rd"]').append('<option value="30">30</option>');
			$('select[@name="'+idTabla+'_rd"]').val(numeroPaginacion);
          }
        
        function soloEnteros(objeto, e){
          var keynum
          var keychar
          var numcheck
        
          if(window.event){ /*/ IE*/
            keynum = e.keyCode
          }
          else if(e.which){ /*/ Netscape/Firefox/Opera/*/
            keynum = e.which
          }
          
          if((keynum>=35 && keynum<=37) ||keynum==8||keynum==9||keynum==46||keynum==39) {
            return true;
          }
          if((keynum>=95&&keynum<=105)||(keynum>=48&&keynum<=57)){
            return true;
          }else {
            return false;
          }
        }
        
        
        function caracteresValidos(objeto, e){
          var keynum
          var keychar
          var numcheck
        
          if(window.event){ /*/ IE*/
            keynum = e.keyCode;
            if(keynum==222 || keynum== 13||keynum==9 || keynum==39 || keynum==34 ) {
	            return false;
	          }else return true;
          }
          else if(e.which){ /*/ Netscape/Firefox/Opera/*/
            keynum = e.which;
            if(keynum==222 || keynum== 13||keynum==9 || keynum==39 || keynum==34) {
	            return false;
	          }else return true;
          }                              
                    
        }
      
      function caracteresValidosSinComa(objeto, e){
          var keynum
          var keychar
          var numcheck
        
          if(window.event){ /*/ IE*/
            keynum = e.keyCode;            
            if(keynum==222 || keynum== 13||keynum==9 || keynum==39 || keynum==34 || keynum==188) {
	            return false;
	          }else return true;
          }
          else if(e.which){ /*/ Netscape/Firefox/Opera/*/
            keynum = e.which;            
            if(keynum==222 || keynum== 13||keynum==9 || keynum==39 || keynum==34 || keynum==188) {
	            return false;
	          }else return true;
          }                              
                    
        }
	  
        var objeto2;	  
        function soloDinero(objeto, e){
          var keynum
          var keychar
          var numcheck
        
          if(window.event){ /*/ IE*/
            keynum = e.keyCode
          }
          else if(e.which){ /*/ Netscape/Firefox/Opera/*/
            keynum = e.which
          }
          
          if((keynum>=35 && keynum<=37) ||keynum==8||keynum==9||keynum==46||keynum==39) {
            return true;
          }
          if(keynum==190||keynum==110||(keynum>=95&&keynum<=105)||(keynum>=48&&keynum<=57)){
            posicion = objeto.value.indexOf('.'); 
            
            if(posicion==-1) {
              return true;
            }else {                            
              if(!(keynum==190||keynum==110)){
                objeto2=objeto;
                t = setTimeout('dosDecimales()',150);
                return true;
              }else{
                objeto2=null;
                return false;
              }
            }
          }else {
            return false;
          }
        }
	
        function dosDecimales(){	
          var objeto = objeto2;
          var posicion = objeto.value.indexOf('.'); 
          var decimal = 2;
          if(objeto.value.length - posicion < decimal){
            objeto.value = objeto.value.substr(0,objeto.value.length-1);			
          }else {
            objeto.value = objeto.value.substr(0,posicion+decimal+1);			
          }
          return;
        }
        
        function cadenaValida(cad){
  			var car1,car2,car3,car4,car5;
  			car1 = String.fromCharCode(222);
  			car2 = String.fromCharCode(13);
  			car3 = String.fromCharCode(9);
  			car4 = String.fromCharCode(39);
  			car5 = String.fromCharCode(34);
  			
  				if(cad.indexOf(car1)==-1 && cad.indexOf(car2)==-1 && cad.indexOf(car3)==-1 &&
  				cad.indexOf(car4)==-1 && cad.indexOf(car5)==-1 )
  					return true;
  					else{
  						alert("La cadena contiene caracteres no validos. Favor no utilizar \",\',tab ni enter");
  						//obj.val("");
  						return false; 
  						}
  			}
        
	    </script>

	<tiles:getAsString name="scripts" />
</head>
<body>
	<center>
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
									<%=user%>
								</td>
							</tr>
							<tr>
								<td
									style="font-size: 18px; color: #439FD7; font-weight: bold; vertical-align: middle;">
									<a href='/SALDEI/common.do?cmd=bienvenida'> <img
											src="${ctx}/images/login/HomePage1.png" alt="Inicio" /> </a>
								</td>
								<td
									style="font-size: 18px; color: #439FD7; font-weight: bold; vertical-align: middle;">
									<a href='/SALDEI/common.do?cmd=inicio'> <img
											src="${ctx}/images/login/salir.png" alt="Salir SALDEI" /> </a>
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
		<div id="container">
			<div class="titulo">
				<tiles:insert name="encabezado" />
				<hr width="97%" style="margin-left: -2px; margin-top: -1px;" />
			</div>
			<tiles:insert name="contenido" />
		</div>

		<div id="footer">
			<img src="${ctx}/images/uca/Footer.png" />
			<br />
			<bean:message key="opc.versionApp" />
		</div>
	</div>
	</center>

</body>

</html:html>

/**
*	showBox- Alternativa simple para Thickbox
*	author:  WiRoCaRo
* 	project: SALDEI
 * 	Inspirado de las versiones de thickbox
 *	
**/
/**
  * Version 1.0.0
  *  @param  String  navurl     url que desplegara el iFrame
  *  @param  String  title      title of the pop up box
  *  @param  Numeric box_width	width of the box in pixels
  *  @param  Numeric box_height	height of the box in pixels
  *   
 **/

jQuery.extend({	
	showBox:function(navurl,title,box_width,box_height)
	{
	    var offset={};
	    var options ={
	    	margin:1,
	    	border:1,
	    	padding:1,
	    	scroll:0
	    };
		
	    var win_width      =$(window).width();
	    var scrollToLeft   =$(window).scrollLeft();
	    var win_height     =$(window).height();
	    var scrollToBottom =$(window).scrollTop();
	   
	    $('body').append("<div id='box_div' style='padding-top:0px;border:1px solid white;background-color:#FFF;position: absolute;z-index:1000;display:none;' ><div style='background-color:#696969;color:white;display:block;padding-top:0px;margin-top:0px;font-family:Arial;'> <b>"+title+"</b><img src='/SALDEI/images/icons/cross.png' id='close' style='position:absolute;cursor:pointer;'></div><iframe width='"+box_width+"' height='"+box_height+"'  frameborder=0 marginwidth='0' marginheight='0' scrolling='NO'  name='frmTest' src='"+navurl+"'></iframe></div>");

	    $('#box_div').css({left:(((win_width/2-box_width/2))+scrollToLeft)+'px',top:(((win_height/2-box_height/2))+scrollToBottom)+'px'});
	  
	    $('#close').click( function() {
	       // 
	       $('#box_div').Puff(500);
	       $('#box_div').remove();
	       $.dimScreenStop();
	    });
	    $.dimScreen(500, 0.7, function() {
			$('#box_div').Grow(500);
	    });
	    
		var offset = {}
	    $("#box_div").offset({ scroll: false }, offset)

  	    X_left = offset.left+box_width-16;
	    X_top  = offset.top-1;

	    $('#close').css({left:X_left,top:X_top});

	},
	
	showBoxRemove:function()
	{
	   $('#box_div').Puff(500);
	   $.dimScreenStop();
	},
	
	akModalHideAndRedirect:function(redirect_url)
	{
	    $('#box_div').Puff(500);
	    $.dimScreenStop();
	    window.location=redirect_url;
	}
});	


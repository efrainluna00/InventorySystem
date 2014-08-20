/**
 * Ajax.js
 *
 * Collection of Scripts to allow in page communication from browser to (struts) server
 * can reload part instead of full page
 *
 */
 
  var req;
  var which;
  var globalDivId;

  /**
   * Get the contents of the URL via an Ajax call
   * url - to get content from (e.g. /struts-ajax/sampleajax.do?ask=COMMAND_NAME_1) 
   * nodeToOverWrite - when callback is made
   * nameOfFormToPost - which form values will be posted up to the server as part 
   *					of the request (can be null)
   */
 /**
  * gets the contents of the form as a URL encoded String
  * suitable for appending to a url  or sending in a POST request
  * @param formName to encode
  * @return string with encoded form values , beings with &
  */ 
 function getFormAsString(formName){
 	
 	//Setup the return String
 	returnString ="";
 	
  	//Get the form values
 	formElements=document.forms[formName].elements;
 	
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value
 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 	}
 	
 	//return the values
 	return returnString; 
 } 
 


/**
  * gets the XMLHttpRequest object which one depends on the browser
  * @return XMLHttpRequest 
  */ 
 function getXMLHttpRequest(){ 
  if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
	 } else if (window.ActiveXObject) { // IE      
	    req = new ActiveXObject("Microsoft.XMLHTTP");     
	 }	
	 return req 	
 }
 
 
  /**
  * send a Post request with the form's content
  * @param url, request url
  * @param formName, to encode
  * @param divId, Div's id to be replace  
  */ 
function retrieveURLFormPost(url,formName,divId){
  parameter=getFormAsString(formName);
	  $("loadingIcon").style.visibility="visible";
  retrieveURLPost(url,parameter,divId);
}


 /**
  * send a Get request with the form's content
  * @param url, request url
  * @param formName, to encode
  * @param divId, Div's id to be replace  
  */ 
function retrieveURLFormGet(url,formName,divId){
  parameter=getFormAsString(formName);
  retrieveURLPost(url,parameter,divId);
}
 
  /**
  * gets Post request
  * @param url to request
  * @param parameters to send
  * @param divId to replace  
  */ 
 function retrieveURLPost(url,parameters,divId) {
    //Do the Ajax call
 	  $("loadingIcon").style.visibility="visible";
    globalDivId =divId;    
    req = getXMLHttpRequest();
    req.onreadystatechange = processStateChange;
    if(req){
	    try {
	      	req.open("POST", url, true); //was Post
	      	req.setRequestHeader("Content-Type", 
	                       "application/x-www-form-urlencoded");
	       
	        req.send(parameters);
	      } catch (e) {
	        alert("Problem Communicating with Server\n"+e);
	      } 
    }      
  }
  
/**
* execute Get request
* @param url to request
* @param parameters to send
* @param divId to replace  
*/ 
 function retrieveURLGet(url,parameters,divId) {
    //Do the Ajax call
    globalDivId =divId;    
    req = getXMLHttpRequest();
    req.onreadystatechange = processStateChange;
    if(req){
	    try {
	      	req.open("GET", url, true); //was Get	      	
	        req.send(parameters);
	      } catch (e) {
	        alert("Problem Communicating with Server\n"+e);
	      } 
    }      
  } 

/*
   * Set as the callback method for when XmlHttpRequest State Changes 
   * used by retrieveUrl
  */
  
  function processStateChange() {

  	  if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
        
    
        //Split the text response into Span elements

	        updateSession();
            newHtml = getNewHtml(req.responseText,globalDivId);
	        //Use these span elements to update the page
	        //replaceExistingWithNewHtml(spanElements);
	        replaceExistingDivWithNewHtml(newHtml,globalDivId);
	        $("loadingIcon").style.visibility="hidden";
      } else {
        alert("Session Finalizada con Servidor , vuelva a ingresar \n " + req.statusText);
      }
    }
  }

 
 /**
 * Splits the text into <span> elements
 * @param the text to be parsed
 * @return array of <span> elements - this array can contain nulls
 */
 function splitTextIntoSpan(textToSplit,spanId){ 
  	//Split the document
  	posicionInicial = textToSplit.search('<span id="' +spanId + '"');
  	posicionFinal = textToSplit.indexOf("</span>",posicionInicial);
  	spanText = textToSplit.substr(posicionInicial,posicionFinal-posicionInicial);
  	returnElements = new Array(1);
 	returnElements[0] =spanText;
 	return returnElements;
 } 
 
 /**
 * helper funtion to count how many open tag ('<Div') exists in the given text
 * @param the text to be parsed 
 * @return count
 */
 function countDivs(text){
 	contadorDivOpen = 0;
 	pos=text.search('<div');  	
 	while(pos>0){
 		contadorDivOpen++;
 		pos=text.indexOf("<div",pos+1);
 	}
 	return contadorDivOpen;
 
 
 }
 
 /*
  * Replace html elements in the existing (ie viewable document)
  * with new elements (from the ajax requested document)
  * WHERE they have the same name AND are <span> elements
  * @param newTextElements (output of splitTextIntoSpan)
  *					in the format <span id=name>texttoupdate
  */
 function replaceExistingWithNewHtml(newTextElements){
 
 	//loop through newTextElements
 	for ( var i=newTextElements.length-1; i>=0; --i ){
  
 		//check that this begins with <span
 		if(newTextElements[i].indexOf("<span")>-1){
 			
 			//get the name - between the 1st and 2nd quote mark
 			startNamePos=newTextElements[i].indexOf('"')+1;
 			endNamePos=newTextElements[i].indexOf('"',startNamePos);
 			name=newTextElements[i].substring(startNamePos,endNamePos);
 			
 			//get the content - everything after the first > mark
 			startContentPos=newTextElements[i].indexOf('>')+1;
 			content=newTextElements[i].substring(startContentPos);
 			
 			//Now update the existing Document with this element
 			
	 			//check that this element exists in the document
	 			if(document.getElementById(name)){
	 			
	 				//alert("Replacing Element:"+name);
	 				document.getElementById(name).innerHTML = content;
	 			} else {
	 				alert("Element: "+name+" not found in existing document");
	 			}
 		}
 	}
 }
 
  /*
  * Replace div in the existing (ie viewable document)
  * with new one (from the ajax requested document) 
  * @param newHtml (new content)
  * @param divId (id to be replace) 			
  */
 function replaceExistingDivWithNewHtml(newHtml,divId){
	//check that this element exists in the document
	if(document.getElementById(divId)){	 			
		//alert("Replacing Element:"+name);
		document.getElementById(divId).innerHTML = newHtml;
	} else {
	 	alert("Element: "+name+" not found in existing document");
	}
}
  /*
  * Get new content from Resquest Ajax 
  * @param requestText ajax request
  * @param divId id to be replace  		
  */	
function getNewHtml(requestText,divId){
	var text ="";
	try{
	    divAjax=document.createElement("div");
	    divAjax.innerHTML=requestText;		
		divs=divAjax.getElementsByTagName("div"); 	
		for (var i = 0; i < divs.length; i++) { 		
		    id = divs[i].getAttribute("id"); 
			    if ( id == divId) { 	     
			      text=divs[i].innerHTML;		     
			      break;
			}
		}	
		divAjax.innerHTML="";
		divAjax=null;
	}catch(e){
	
	}
	return text;	
}

   function updateSession() {
	  $("warningMessage").style.display = "none";
	  $("timeoutMessage").style.display = "none";
	  clearInterval(warningTimer);
	  clearInterval(timeoutTimer);
	  warningTimer = setTimeout(onWarning, WARNING_TIME);
	  timeoutTimer = setTimeout(onTimeout, TIMEOUT_TIME);
	}
     
 		


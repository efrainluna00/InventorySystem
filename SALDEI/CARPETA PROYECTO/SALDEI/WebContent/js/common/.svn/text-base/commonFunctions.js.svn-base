var defaultEmptyOK = false
var checkNiceness = true;
var digits = "0123456789";isEmpty
var lowercaseLetters = "abcdefghijklmnopqrstuvwxyzáéíóúñü"
var uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÑ"
var whitespace = " \t\n\r";
var phoneChars = "()-+ ";
var mMessage = "Error: no puede dejar este espacio vacio"
var pPrompt = "Error: ";
var pAlphanumeric = "ingrese un texto que contenga solo letras y/o numeros";
var pAlphabetic   = "ingrese un texto que contenga solo letras";
var pInteger = "ingrese un numero entero";
var pNumber = "ingrese un numero";
var pPhoneNumber = "ingrese un número de teléfono";
var pEmail = "ingrese una dirección de correo electrónico válida";
var pName = "ingrese un texto que contenga solo letras, numeros o espacios";
var pNice = "no puede utilizar comillas aqui";

function insertMode(formaControl,formaDetalle){
	controlsEnable(formaControl,false);
	detailEnable(formaDetalle,true);
	detailControlsEnable(formaDetalle,true);
	clearForm(formaDetalle)
	document.forms[formaDetalle].cmd.value="save";
	document.forms[formaDetalle].page.value='1';
}
function editMode(formaControl,formaDetalle,pkFields){
	controlsEnable(formaControl,false);
	detailEnable(formaDetalle,true);
	detailControlsEnable(formaDetalle,true);
	disablePkFields(formaDetalle,pkFields);
	document.forms[formaDetalle].cmd.value="update";
	document.forms[formaDetalle].page.value="1";
}

 
function deletedMode(formaControl,formaDetalle){
	controlsEnable(formaControl,false);
	detailEnable(formaDetalle,false);
	detailControlsEnable(formaDetalle,true);
	document.forms[formaDetalle].cmd.value="delete";
	document.forms[formaDetalle].page.value="1";
}
function findMode(formaControl,formaDetalle){
	controlsEnable(formaControl,false);
	detailEnable(formaDetalle,true);
	detailControlsEnable(formaDetalle,true);
	clearForm(formaDetalle);
	document.forms[formaDetalle].cmd.value="find";
	document.forms[formaDetalle].page.value="";
}
function enableObject(controlObject,condicion){

		if(condicion==true){ 
			
					if(controlObject.tagName == "INPUT"){
						controlObject.readOnly=false;
						if (controlObject.className!='boton'){
							controlObject.className='caja_texto'
						}
					}else if(controlObject.tagName == "TEXTAREA"){
						controlObject.readOnly=false;
						controlObject.className='textarea'
					}else if(controlObject.tagName == "SELECT"){
						controlObject.disabled=false;
					}
		}else{
		
					if(controlObject.tagName == "INPUT"){
						controlObject.readOnly=true;
						if (controlObject.className!='boton'){
							controlObject.className='caja_textodisable'
						}
					}else if(controlObject.tagName == "TEXTAREA"){
						controlObject.readOnly=false;
						controlObject.className='textarea_disable'
					}else if(controlObject.tagName == "SELECT"){
						
						controlObject.disabled=true;
					}
		}

}
function detailEnable(forma,condicion){
	formElements=document.forms[forma].elements;
	if(condicion==true){
		for ( var i=formElements.length-1; i>=0; --i ){
			if(formElements[i].tagName == "INPUT"){
				formElements[i].readOnly=false;
				if (formElements[i].className!='boton'){
					formElements[i].className='caja_texto'
				}
			}else if(formElements[i].tagName == "TEXTAREA"){
				formElements[i].readOnly=false;
				formElements[i].className='textarea'
			}else if(formElements[i].tagName == "SELECT"){
				formElements[i].disabled=false;
			}
	 	}
	}else{
		for ( var i=formElements.length-1; i>=0; --i ){
			if(formElements[i].tagName == "INPUT"){
				formElements[i].readOnly=true;
				if (formElements[i].className!='boton'){
					formElements[i].className='caja_textodisable'
				}
			}else if(formElements[i].tagName == "TEXTAREA"){
				formElements[i].readOnly=false;
				formElements[i].className='textarea_disable'
			}else if(formElements[i].tagName == "SELECT"){
				
				formElements[i].disabled=true;
			}
	 	}
	}
}
function disablePkFields(detalle,pkFields){
	var pkArray = pkFields.split(',');
	formaDetalle = document.forms[detalle];
	for(var i=0 ; i < pkArray.length ; i++ ){
			htmlElement = formaDetalle.elements[pkArray[i]];
			if(htmlElement.tagName =="INPUT"){
				htmlElement.className='caja_textodisable';
				htmlElement.readOnly=true;
			}else if(htmlElement.tagName =="TEXTAREA"){
			    htmlElement.className='textarea_disable';
	    		htmlElement.readOnly=true;
			}else if(htmlElement.tagName =="SELECT"){
				htmlElement.disabled=true;
			}
	}
}
function controlsEnable(forma,condicion){
	if(condicion==true){
		try{
			document.forms[forma].insert.disabled = false;
		}catch(e){
		
		}
		try{
			document.forms[forma].update.disabled = false;
		}catch(e){
		
		}
		try{
			document.forms[forma].deleted.disabled =false;
		}catch(e){

		}
		try{
			document.forms[forma].find.disabled = false;
		}catch(e){
		
		}
		enableRadioGroup(document.forms[forma].rows,false);
	}else{
	    try{
			document.forms[forma].insert.disabled = true;
		}catch(e){
	
		}
		try{
			document.forms[forma].update.disabled = true;
		}catch(e){
	
		}
		try{
			document.forms[forma].deleted.disabled = true;
		}catch(e){
	
		}
		try{
			document.forms[forma].find.disabled = true;
		}catch(e){
		
		}
		enableRadioGroup(document.forms[forma].rows,true);
	}
}
function detailControlsEnable(forma,condicion){
	if(condicion==true){
		    document.forms[forma].commit.disabled = false;
	        document.forms[forma].cancel.disabled = false;
	}else{
		document.forms[forma].commit.disabled = true;
	    document.forms[forma].cancel.disabled = true;
	}
}

function enableRadioGroup (radioGroup,condicion){
	try{
		for (var b = 0; b < radioGroup.length; b++)
          radioGroup[b].disabled = condicion;
    }catch(e){
    
    }

}
function getRadioChecked (radioGroup){
var codeRadio;
		for (var b = 0; b < radioGroup.length; b++){
			if (radioGroup[b].checked){
				codeRadio=radioGroup[b].value;
				break;
			}
		}
return codeRadio;
}
function clearForm (forma){
	formElements=document.forms[forma].elements;
	for ( var i=formElements.length-1; i>=0; --i ){
			if(formElements[i].tagName == "INPUT" || formElements[i].tagName == "TEXTAREA"){
				if (formElements[i].className!='boton'){
					formElements[i].value ="";
				}
	 		}
	}
}
function disableAutoCompleteForm(formName){
	if (document.forms[formName].getElementsByTagName) {
	var inputElements = document.getElementsByTagName("INPUT");
		for (i=0; inputElements[i]; i++) {
			if (inputElements[i].className && (inputElements[i].className.indexOf("disableAutoComplete") != -1)) {
				inputElements[i].setAttribute("autocomplete","off");
			}//if current input element has the disableAutoComplete class set.
		}//loop thru input elements
	}
}


function makeArray(n) {
   for (var i = 1; i <= n; i++) {
      this[i] = 0
   } 
   return this
}

function isEmpty(s)
{   return ((s == null) || (s.length == 0))
}

function isWhitespace (s)
{   var i;
    if (isEmpty(s)) return true;
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        // si el caracter en que estoy no aparece en whitespace,
        // entonces retornar falso
        if (whitespace.indexOf(c) == -1) return false;
    }
    return true;
}


function stripCharsInBag (s, bag)
{   var i;
    var returnString = "";

    // Buscar por el string, si el caracter no esta en "bag", 
    // agregarlo a returnString
    
    for (i = 0; i < s.length; i++)
    {   var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }

    return returnString;
}


function stripCharsNotInBag (s, bag)
{   var i;
    var returnString = "";
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (bag.indexOf(c) != -1) returnString += c;
    }

    return returnString;
}


function stripWhitespace (s)
{   return stripCharsInBag (s, whitespace)
}

function charInString (c, s)
{   for (i = 0; i < s.length; i++)
    {   if (s.charAt(i) == c) return true;
    }
    return false
}

function stripInitialWhitespace (s)
{   var i = 0;
    while ((i < s.length) && charInString (s.charAt(i), whitespace))
       i++;
    return s.substring (i, s.length);
}

function isLetter (c)
{
    return( ( uppercaseLetters.indexOf( c ) != -1 ) ||
            ( lowercaseLetters.indexOf( c ) != -1 ) )
}

function isDigit (c)
{   return ((c >= "0") && (c <= "9"))
}

function isLetterOrDigit (c)
{   return (isLetter(c) || isDigit(c))
}

function isInteger (s)
{   var i;
    if (isEmpty(s)) 
       if (isInteger.arguments.length == 1) return defaultEmptyOK;
       else return (isInteger.arguments[1] == true);
    
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if( i != 0 ) {
            if (!isDigit(c)) return false;
        } else { 
            if (!isDigit(c) && (c != "-") || (c == "+")) return false;
        }
    }
    return true;
}


function isNumber (s)
{   var i;
    var dotAppeared;
    dotAppeared = false;
    if (isEmpty(s)) 
       if (isNumber.arguments.length == 1) return defaultEmptyOK;
       else return (isNumber.arguments[1] == true);
    
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if( i != 0 ) {
            if ( c == "." ) {
                if( !dotAppeared )
                    dotAppeared = true;
                else
                    return false;
            } else     
                if (!isDigit(c)) return false;
        } else { 
            if ( c == "." ) {
                if( !dotAppeared )
                    dotAppeared = true;
                else
                    return false;
            } else     
                if (!isDigit(c) && (c != "-") || (c == "+")) return false;
        }
    }
    return true;
}

function isAlphabetic (s)
{   var i;

    if (isEmpty(s)) 
       if (isAlphabetic.arguments.length == 1) return defaultEmptyOK;
       else return (isAlphabetic.arguments[1] == true);
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is letter.
        var c = s.charAt(i);

        if (!isLetter(c))
        return false;
    }
    return true;
}

function isAlphanumeric (s)
{   var i;

    if (isEmpty(s)) 
       if (isAlphanumeric.arguments.length == 1) return defaultEmptyOK;
       else return (isAlphanumeric.arguments[1] == true);

    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (! (isLetter(c) || isDigit(c) ) )
        return false;
    }

    return true;
}


function isName (s)
{
    if (isEmpty(s)) 
       if (isName.arguments.length == 1) return defaultEmptyOK;
       else return (isAlphanumeric.arguments[1] == true);
    
    return( isAlphanumeric( stripCharsInBag( s, whitespace ) ) );
}

function isPhoneNumber (s)
{   var modString;
    if (isEmpty(s)) 
       if (isPhoneNumber.arguments.length == 1) return defaultEmptyOK;
       else return (isPhoneNumber.arguments[1] == true);
    modString = stripCharsInBag( s, phoneChars );
    return (isInteger(modString))
}

function isEmail (s)
{
    if (isEmpty(s)) 
       if (isEmail.arguments.length == 1) return defaultEmptyOK;
       else return (isEmail.arguments[1] == true);
    if (isWhitespace(s)) return false;
    var i = 1;
    var sLength = s.length;
    while ((i < sLength) && (s.charAt(i) != "@"))
    { i++
    }

    if ((i >= sLength) || (s.charAt(i) != "@")) return false;
    else i += 2;

    while ((i < sLength) && (s.charAt(i) != "."))
    { i++
    }

    if ((i >= sLength - 1) || (s.charAt(i) != ".")) return false;
    else return true;
}

function isNice(s)
{
        var i = 1;
        var sLength = s.length;
        var b = 1;
        while(i<sLength) {
                if( (s.charAt(i) == "\"") || (s.charAt(i) == "'" ) ) b = 0;
                i++;
        }
        return b;
}

function statBar (s)
{   window.status = s
}

function warnEmpty (theField)
{   theField.focus()
    alert(mMessage)
    statBar(mMessage)
    return false
}

function warnInvalid (theField, s)
{   theField.focus()
    theField.select()
    alert(s)
    statBar(pPrompt + s)
    return false
}


function checkField (theField, theFunction, emptyOK, s)
{   
    var msg;
    if (checkField.arguments.length < 3) emptyOK = defaultEmptyOK;
    if (checkField.arguments.length == 4) {
        msg = s;
    } else {
        if( theFunction == isAlphabetic ) msg = pAlphabetic;
        if( theFunction == isAlphanumeric ) msg = pAlphanumeric;
        if( theFunction == isInteger ) msg = pInteger;
        if( theFunction == isNumber ) msg = pNumber;
        if( theFunction == isEmail ) msg = pEmail;
        if( theFunction == isPhoneNumber ) msg = pPhoneNumber;
        if( theFunction == isName ) msg = pName;
    }
    
    if ((emptyOK == true) && (isEmpty(theField.value))) return true;

    if ((emptyOK == false) && (isEmpty(theField.value))) 
        return warnEmpty(theField);

    if ( checkNiceness && !isNice(theField.value))
        return warnInvalid(theField, pNice);

    if (theFunction(theField.value) == true) 
        return true;
    else
        return warnInvalid(theField,msg);

}

function cerrar(){
        setTimeout('window.close()',150);
}

function format (expr,decplaces){
  //raise incoming value by power of 10 times the
  //number of decimal places;round to an integer;convert to string
  var str ="" + Math.round (eval(expr)*Math.pow(10,decplaces));
  //pad small value strings with zeros to the left of rounded number
  while (str.length <=decplaces){
  str ="0" + str;
  }
  //establish location of decimal point
  var decpoint =str.length - decplaces;
  //assemble final result from:(a)the string up to the position of
  //the decimal point;(b)the decimal point;and (c)the balance
  //of the string.Return finished product.
  return str.substring(0,decpoint) + "." + str.substring(decpoint,str.length);
}

function setDataType(cValue)
{
// THIS FUNCTION CONVERTS DATES AND NUMBERS FOR PROPER ARRAY
// SORTING WHEN IN THE SORT FUNCTION
var isDate = new Date(cValue);
if (isDate == "NaN")
{
if (isNaN(cValue))
{
// THE VALUE IS A STRING, MAKE ALL CHARACTERS IN
// STRING UPPER CASE TO ASSURE PROPER A-Z SORT
cValue = cValue.toUpperCase();
return cValue;
}
else
{
// VALUE IS A NUMBER, TO PREVENT STRING SORTING OF A NUMBER
// ADD AN ADDITIONAL DIGIT THAT IS THE + TO THE LENGTH OF
// THE NUMBER WHEN IT IS A STRING
var myNum;
myNum = String.fromCharCode(48 + cValue.length) + cValue;
return myNum;
}
}
else
{
// VALUE TO SORT IS A DATE, REMOVE ALL OF THE PUNCTUATION AND
// AND RETURN THE STRING NUMBER
//BUG - STRING AND NOT NUMERICAL SORT .....
// ( 1 - 10 - 11 - 2 - 3 - 4 - 41 - 5 etc.)
var myDate = new String();
myDate = isDate.getFullYear() + " " ;
myDate = myDate + isDate.getMonth() + " ";
myDate = myDate + isDate.getDate(); + " ";
myDate = myDate + isDate.getHours(); + " ";
myDate = myDate + isDate.getMinutes(); + " ";
myDate = myDate + isDate.getSeconds();
//myDate = String.fromCharCode(48 + myDate.length) + myDate;
return myDate ;
}
}
function openWindow(url,sheight,swidth,sleft,stop) {
      var newWindow = window.open(url,"","HEIGHT="+sheight+",WIDTH="+swidth+",LEFT="+sleft+",TOP="+stop+",scrollbars=1");
}

/* Function for showing and hiding elements that use 'display:none' to hide */
function toggleDisplay(targetId) {
	if (document.getElementById) {
		target = document.getElementById(targetId);
        if (target.style.display == "none"){
			target.style.display = "";
		} else {
			target.style.display = "none";
		}
	}
}
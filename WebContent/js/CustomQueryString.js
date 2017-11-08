/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
KEY_RETURN=13;
KEY_UP=38;
KEY_DOWN=40;
KEY_LEFT=37;
KEY_RIGHT=39;
KEY_SHIFT=16;
KEY_KONTROL=17;
KEY_ALT=18;
KEY_ESC=27;
KEY_INSERT=45;
KEY_HOME=36;
KEY_END=35;
KEY_PAGEUP=33;
KEY_PAGEDOWN=34;
KEY_SCROLLLOCK=145;
KEY_PAUSE=19;
KEY_DELETE=46;
KEY_PRINTSCREEN=118;
KEY_NUMLOCK=144;
KEY_CAPSLOCK=20;
KEY_LEFT_WINDOWS=91;
KEY_RIGHT_WINDOWS=92;
KEY_CONTEXT=93;
keyCodePressed=null;
document.onkeydown=keyDown;
if((document.layers)?true:false){
    document.captureEvents(Event.KEYDOWN);
}
previousSuggestion=0;
currentSuggestion=0;
numSuggestions=0;
function CustomQueryString(evtDef)
{
    
    ajaxPartsTaglib.log.debug("hier");
    //alert("ho")
    //textbox = eval(evtDef.reqParam);
   textbox=document.getElementById("user");
    //alert(textbox.value);
    suggestionsDiv=document.getElementById("suggestions");
    if(textbox.value=="")
    {
        previousSuggestion=0;
        currentSuggestion=0;
        keyCodePressed=null;
        document.getElementById("suggestions").style.visibility="hidden";
        return;
    }
    //alert(textbox.value);
    if(keyCodePressed == KEY_RETURN)
    {
        if (suggestionsDiv.style.visibility == "hidden") {
            return;
        } else {
            //if (currentSuggestion > 0) {
            textbox.value = document.getElementById("suggestion" +  currentSuggestion).innerHTML;
            suggestionsDiv.style.visibility = "hidden";
            return false;
      //}
    }
     }
     if(keyCodePressed==KEY_UP || keyCodePressed==KEY_DOWN)
     {
        if(previousSuggestion>0)
        {
            document.getElementById("suggestion"+ previousSuggestion).style.backgroundColor="#f0f0f0";
        }
        //up arrow
        if(keyCodePressed==KEY_UP)
        {
            currentSuggestion--;
            if(currentSuggestion<1)
            {
                currentSuggestion=1;
            }
        }
        //down arrow
         if(keyCodePressed==KEY_DOWN)
        {
            currentSuggestion++;
            if(currentSuggestion>numSuggestions)
            {
                currentSuggestion=numSuggestions;
            }
        }
        previousSuggestion=currentSuggestion;
        //highlight new
        
        document.getElementById("suggestion"+currentSuggestion).style.backgroundColor="#ff0000";
        
        return false;
     
     }
     //alert(keyCodePressed);
    // if ((keyCodePressed != KEY_SHIFT) && (keyCodePressed != KEY_CONTROL) && (keyCodePressed != KEY_ALT) && (keyCodePressed != KEY_ESC) && (keyCodePressed != KEY_INSERT) && (keyCodePressed != KEY_HOME) && (keyCodePressed != KEY_END) && (keyCodePressed != KEY_PAGEUP) &&
    //  (keyCodePressed != KEY_PAGEDOWN) && (keyCodePressed != KEY_SCROLLLOCK) && (keyCodePressed != KEY_PAUSE) && (keyCodePressed != KEY_DELETE) &&
    //  (keyCodePressed != KEY_PRINTSCREEN) && (keyCodePressed != KEY_NUMLOCK) && (keyCodePressed != KEY_CAPSLOCK) && (keyCodePressed != KEY_LEFT_WINDOWS) &&
    //  (keyCodePressed != KEY_LEFT) && (keyCodePressed != KEY_RIGHT) && (keyCodePressed != KEY_RIGHT_WINDOWS) && (keyCodePressed != KEY_CONTEXT)) 
     // {
      queryString = "?enteredText=" + escape(textbox.value);
      //alert("ho1");
      ajaxPartsTaglib.ajaxRequestSender(evtDef, null, queryString, null, null);
    //}
   
  }
 function keyDown(e)
 {
    ev=(e) ? e : (window.event)? window.event : null;
    if(ev)
    {
        keyCodePressed=(ev.charCode) ? ev.charCode : ((ev.keyCode) ? ev.keyCode : ((ev.which) ? ev.whice : null));
    }
    
 }
 
    
    



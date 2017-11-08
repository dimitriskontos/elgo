function toggle()
                {
                    var timi=0;//parseInt(document.prosopiko.elements['size'].value); 
                    /*if(!(document.getElementById("first"+timi)==null))
                    {
                        
                        if(document.getElementById("first"+timi).value==="" || document.getElementById("first"+timi).value==="")
                        {
                            alert("Συμπήρωστε το προηγούμενο");
                            return;
                        }
                    }*/
                    timi=timi+1;
                    document.previlige.elements['size'].value=timi;
                    var root=document.getElementById('tef'); 
                    var row, cell,num,op,txt,div; 
                    row=document.createElement('tr');
                    //1ο κελλί
                    cell=document.createElement('td'); 
                    num = document.createElement('input'); 
                    num.setAttribute('type', 'text'); 
                    num.setAttribute('name', 'epwnymo'+timi); 
                    num.id='epwnymo'+timi;
                    num.setAttribute("onkeyup","f(" +timi + ")");
                    num.setAttribute('value',''); 
                    num.setAttribute('size','50');
                    cell.appendChild(num);
                    row.appendChild(cell);
                    //2ο κελλί
                    cell=document.createElement('td'); 
                    num = document.createElement("input"); 
                    num.setAttribute("type", "text"); 
                    num.setAttribute("name", "onoma"+timi); 
                    num.id="onoma"+timi;
                    num.setAttribute("value",""); 
                    num.setAttribute("size","50");
                    cell.appendChild(num); 
                    row.appendChild(cell);
                    //3o κελλί
                    cell=document.createElement('td'); 
                    row.appendChild(cell);
                    num = document.createElement("input"); 
                    num.setAttribute("type", "hidden");
                    num.setAttribute("name", "id_name"+timi); 
                    num.id="id_name"+timi;
                    cell.appendChild(num); 
                    root.appendChild(row);
                }			
                function f(timi)
                {
                    if(window.XMLHttpRequest)
                    {
                        xmlhttp=new XMLHttpRequest();
                        
                    }else{
                        xmlhttp=new AxtiveXObject("Microsoft.XMLHTTP");
                    }
                    xmlhttp.getResponseHeader('text/xml; charset=utf-8');
                    xmlhttp.onreadystatechange=function()
                    {
                        if((xmlhttp.readyState==4) && (xmlhttp.status==200))
                        {
                            
                            var s=xmlhttp.responseText;
                            
                            s=s.trim();
                            
                            //alert(s);
                            var res=s.split("\n");
                            var div=document.getElementById("suggestions");
                            div.style.visibility="visible";
                            div.innerHTML="";
                            var top=0;
                            for(var i=0;i<res.length;i++)
                            {
                                var tel=res[i].split(",");
                                if(!(tel[1]===undefined && tel[2]===undefined))
                                {
                                    if(div.style.visibility==="hidden")
                                        div.style.visibility="visible";
                                    //var sug=document.createElement("div");
                                    div.innerHTML=div.innerHTML+"<div align=center id=\"suggestion" + tel[0] +"\" style=\"width:300px;visibility:visible;position:relative;top" +top +";background-color:white;z-index:100\" >" +tel[2] + " " +tel[1] +"</div>";
                                    var sug=document.getElementById("suggestion" +tel[0]);
                                    sug.setAttribute("ondblclick","dbclk(" + timi + ",\"suggestion" +tel[0] +"\")");
                                    top+=20;
                                    
                                }
                            }
                    }
                };
                xmlhttp.open("GET","/test1/askname.do?enteredText="+ document.getElementById("epwnymo"+timi).value,false);
                
                xmlhttp.send();
            }          
            function dbclk(timi,id_sug)
            {
                var first=document.getElementById("epwnymo"+timi);
                var last=document.getElementById("onoma"+timi);
                var id_row=document.getElementById("id_name"+timi);
                var sug=document.getElementById(id_sug).innerHTML;
                var div=document.getElementById("suggestions");
                var res=sug.split(" ");
                var id=id_sug.substring(10);
                id_row.value=id;
                first.value=res[0];
                last.value=res[1];
                //alert(first.value + last.value);
                div.style.visibility="hidden";
                div.innerHTML="";
               
                
            }
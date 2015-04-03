//????????Flag????   
//pos ????????????,??0????????
function getValFromFlag(InStr, Flag, pos){
	var flagPos,flagPos1,tmpPos;
	var outStr;

	if(InStr!=null){
		if(InStr.length!=0){				
			//??????????????flag????
			if(InStr.substring(InStr.length-Flag.length, InStr.length) != Flag){
				InStr = InStr + Flag; //????????????,??flag????,??????????flag
			}
			
			
			if(pos+1 > StrTimes(InStr, Flag) ){ //????????????????????????????????
				
				outStr='';			
			}
			else{
				flagPos = 0;
				tmpPos =0;
				for(i=0; i<pos; i++){
					flagPos = InStr.indexOf(Flag, tmpPos+1);	
					tmpPos = flagPos;
				}
				
				flagPos1 = InStr.indexOf(Flag, flagPos+1);
				if(pos == 0)
				   outStr = InStr.substring(flagPos, flagPos1);
				else
				   outStr = InStr.substring(flagPos+1, flagPos1);					
			}				
		}
		else{
			outStr='';
		}			
	}
	else{
		outStr='';
	}   
	
       return outStr;
}

	
//????InStr????Flag????????????
//????????
function StrTimes(InStr,Flag)
{
	var iTimes=0;
	var tmpStr="",ChToStr="";
	var tmpChar="";
	
	if(InStr.length!=0)
	{
		tmpStr = InStr;
		for(i=0; i<tmpStr.length; i++){
			tmpChar = tmpStr.charAt(i);
	     		if(tmpChar==Flag){				
				iTimes++;
			}
		}		
	}
	else{
		iTimes=0;
	}
	return iTimes;
}
//??????????????????????
function  strChina(astr)
	{
		var bVal=true;
		for(var i=0; i<astr.length; i++){
		 	var ls = astr.charCodeAt(i);
			if(ls>128 || ls < 1)
			{
			 	bVal = false;
                  break;
			}	
		}
		return bVal;
	}

	
//??????????????????
function numChar(str){
	var bVal = true;
	
	for(var i=0; i<str.length; i++){
		var ls = str.charCodeAt(i);
		    
			if(ls>57 || ls < 48)
			{
			 	bVal = false;
                break;
			}	
	}
	
	return bVal;
}

//??????????
		function getDelCheckName()
		{
			var strDel = '';
			var length = document.myform.elements.length;
		
			for(i=0;i<length;i++){
			    if(document.myform.elements[i].name!='check_all'&& document.myform.elements[i].type=='checkbox'&& document.myform.elements[i].checked== true){
			         strDel = strDel + document.myform.elements[i].value + ',';
			    }
			}
		return strDel;
	}
	//??
	function selectAll()
	{
	    var state=null;
		var length = document.myform.elements.length;
		for(i=0;i<length;i++){
		    if(document.myform.elements[i].name=='check_all'&& document.myform.elements[i].type=='checkbox'){
		         state = document.myform.elements[i].checked;
		         
		         break;
		    }
		}
		for(i=0;i<length;i++){
		     if(document.myform.elements[i].type=='checkbox'){
		     	   document.myform.elements[i].checked=state;
		     }
		}
	}
	//????
	 function toPagingRow(pageCount)
	{
		 var page;
		 page = 0;
		 page = document.getElementById("pageId").value;	 
		
		 if( 0< page && page <= pageCount&& numChar(page))
		{
			window.location.href= '<%=request.getContextPath()%>/rmsService?actionCode=commonPaging&page='+page;
		}
	}
	
	
	

function LTrim(str)
{
    var whitespace = new String(" \t\n\r");
    var s = new String(str);

    if (whitespace.indexOf(s.charAt(0)) != -1)
    {
        var j=0, i = s.length;
        while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
        {
            j++;
        }
        s = s.substring(j, i);
    }
    return s;
}

function RTrim(str)
{
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
 
    if (whitespace.indexOf(s.charAt(s.length-1)) != -1)
    {
        var i = s.length - 1;
        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
        {
            i--;
        }
        s = s.substring(0, i+1);
    }
    return s;
}

function Trim(str)
{
    return RTrim(LTrim(str));
}
//??????2008-09-12??????????20080912????
function formatDate(date)
{
	var s = date.indexOf("-");
	var b = date.lastIndexOf("-") 

	var da = "";
	da = da + date.substring(0,s) + date.substring(s+1, b) + date.substring(b+1, date.length);
	
	return da;
}


function checkValue(str)
{
	var value=Trim(str);
	var cha=str.indexOf("'");
	
	if(cha!=-1 || value.length==0){
		return true;
	}
	else{
		return false;
	}
}


//iframe????????????
function SetCwinHeight(obj)
{
  var cwin=obj;
  if (document.getElementById)
  {
    if (cwin && !window.opera)
    {
      if (cwin.contentDocument && cwin.contentDocument.body.offsetHeight)
        cwin.height = cwin.contentDocument.body.offsetHeight; 
      else if(cwin.Document && cwin.Document.body.scrollHeight)
        cwin.height = cwin.Document.body.scrollHeight;
    }
  }
}
//??????????
function winopen(url, iwidth, iheight)
{
	var  WLeft =  Math.ceil((window.screen.width   -   800)   /   2   );     
  	var  WTop  =  Math.ceil((window.screen.height   -   500)   /   2   ); 
	window.open(url,'newwindow',"'width="+iwidth+",height="+iheight+",left="+WLeft+",top="+WTop+"',scrollbars=yes");	 
}

//????
function isChinese(str)
{
    var lst = /[u00-uFF]/;       
    return !lst.test(str);      
}

//??????
function strlen(str) 
{
    var strlength=0;
    for (i=0;i<str.length;i++)
    {
      if (isChinese(str.charAt(i))==true)
         strlength=strlength + 2;
      else
         strlength=strlength + 1;
    }
	return strlength;
}


//????????-?????
function isTel(str){ 
	if(""==str){
		return true;
	}
	var reg = /[^\d-]/g; 
	return str.match(reg)==null; 
} 

//????????
function isDigAlph(str){ 
	if(""==str){
		return true;
	}
	var reg = /[\W]/g;
	return str.match(reg)==null; 
} 

//???.????
function isDig(str){ 
	if(""==str){
		return true;
	}
	var reg = /[^\d.]/g; 
	return str.match(reg)==null; 
} 

function isFloat(objStr,sign,zero)
{
    var reg;    
    var bolzero;    

    if(objStr==null || objStr.length<1 || Trim(objStr)=="")
    {
        return false;
    }
    else
    {
        objStr=objStr.toString();
    }    
    
    if((sign==null)||(Trim(sign)==""))
    {
        sign="+-";
    }

    if((zero==null)||(Trim(zero)==""))
    {
        bolzero=false;
    }
    else
    {
        zero=zero.toString();
        if(zero=="0")
        {
            bolzero=true;
        }
        else
        {
            alert("??????0??????(??0)");
        }
    }

    switch(sign)
    {
        case "+-":
            //?????
            reg=/^((-?|\+?)\d+)(\.\d+)?$/;
            break;

        case "+": 
            if(!bolzero)           
            {
                //????
                reg=/^\+?(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
            }
            else
            {
                //????+0
                reg=/^\+?\d+(\.\d+)?$/;
            }
            break;

        case "-":
            if(!bolzero)
            {
                //????
                reg=/^-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
            }
            else
            {
                //????+0
                reg=/^((-\d+(\.\d+)?)|(0+(\.0+)?))$/;
            }            
            break;

        default:
            alert("??????????(??+?-)");
            return false;
            break;
    }
    
    var r=objStr.match(reg);
    if(r==null)
    {
        return false;
    }
    else
    {        
        return true;     
    }
}

<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
.BUTTON_STYLE1{
 	PADDING-TOP: 2px;
 	PADDING-LEFT: 2px;
 	PADDING-RIGHT: 2px;
 	PADDING-BOTTOM: 2px;
 	CURSOR: pointer;
 	COLOR: black;
 	FONT-SIZE: 12px;
 	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#B7D3FA);/*当GradientType=0时代表从上到下渐变，当GradientType=1时代表从左到右渐变；*/
}
.table_STYLE1{
	BORDER-BOTTOM: #0377BC 1px solid;
 	BORDER-RIGHT: #0377BC 1px solid;
 	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#B7D3FA);/*当GradientType=0时代表从上到下渐变，当GradientType=1时代表从左到右渐变；*/
}
.planlet{
   color:black;
}
.planlet1{
   color:black;
}
-->
</style>
<script type="text/javascript">
function hideinfo()
{
 document.all.info.style.display = "none";
}
function showinfo(x,value1,value2)
{ 
  var strVal = "<TABLE align='center' cellpadding='0' cellspacing='0'  border='1' class='table_STYLE1'>"
					   + "<TR>"
					   + "<TD width='60' align='right' class='planlet1'>货位：</TD>"
					   + "<TD width='80'align='left'   class='planlet'>"+value2+"</TD>"
					   + "</TR>"
					   + "<TR>"
					   + "<TD width='60' align='right' class='planlet1'>状态：</TD>"
					   + "<TD width='80' align='left'  class='planlet'>"+value1+"</TD>"
					   + "</TR>"
					   + "</TABLE>";


      var e = window.event.srcElement;
      var left,top;
      var t = e.offsetTop,    h = e.clientHeight, l = e.offsetLeft, p = e.type;
      while (e = e.offsetParent){t += e.offsetTop; l += e.offsetLeft;}
      var cw = info.clientWidth, ch = info.clientHeight;
      var dw = document.body.clientWidth, dl = document.body.scrollLeft, dt = document.body.scrollTop;
    
      if (document.body.clientHeight + dt - t - h >= ch) top = (p=="image")? t + h : t + h + 6;
      else top    = (t - dt < ch) ? ((p=="image")? t + h : t + h + 6) : t - ch;
      if (dw + dl - l >= cw) left = l; else left = (dw >= cw) ? dw - cw + dl : dl;


  document.all.info.style.position="absolute";
  document.all.info.style.left=left;
  document.all.info.style.top=top;
  document.all.info.style.display = "block";
  document.all.info.innerHTML=strVal;
}
</script>
<body>

<%
	int ipat = Integer.parseInt(request.getAttribute("platoon")==null?"0":request.getAttribute("platoon").toString());	//列
	int icol = Integer.parseInt(request.getAttribute("column")==null?"0":request.getAttribute("column").toString());	//排
	HashMap hs = (HashMap)request.getAttribute("result");
%>
<div style="width:100%; height:100%; overflow:auto;">
 <table align="center" width="100%" height="100%" border="1"  class="table_STYLE1">
   <tr height="16">
     <td nowrap width="16">P\L</td>
<%
	for(int i=0; i<icol; i++){	
%>
     <td width="16" nowrap class="BUTTON_STYLE1"><div align="center"><%=i+1%></div></td>
<%		
	}
%>
   </tr>    
<%
	for(int i=1; i <=ipat; i++){
%>
   <tr>
     <td align="right" width="16" class="BUTTON_STYLE1"><%=i%></td>
<%
		String cStatus;
		for(int j=1; j<=icol; j++){     				
			if(hs.get(String.valueOf(i)+","+String.valueOf(j)) != null){//货位存在
				cStatus = (String )hs.get(String.valueOf(i)+","+String.valueOf(j));
				if(cStatus.equals("1")){//空货位
%>
                  <td width="16" bgcolor="#EEECFB" onmousemove="showinfo(this,'空货位','<%=i%>排<%=j%>列')" onmouseout="hideinfo()"></td>
<%
				}
				else if(cStatus.equals("2")){//满货位
%>
                  <td  width="16" bgcolor="#0000CC" onmousemove="showinfo(this,'满货位','<%=i%>排<%=j%>列')" onmouseout="hideinfo()"></td>
<%
				}
				else if(cStatus.equals("3")){//入库分配
%>
                  <td width="16"  bgcolor="#FAB5B7" onmousemove="showinfo(this,'入库分配','<%=i%>排<%=j%>列')" onmouseout="hideinfo()"></td>
<%
				}
				else if(cStatus.equals("4")){//出库分配
%>
                  <td  width="16"  bgcolor="#FF0000" onmousemove="showinfo(this,'出库分配','<%=i%>排<%=j%>列')" onmouseout="hideinfo()"></td>
<%
				}
				else if(cStatus.equals("5")){//入库需分配
%>
                 <td  width="16"  bgcolor="#9FFA95" onmousemove="showinfo(this,'入库需分配','<%=i%>排<%=j%>列')" onmouseout="hideinfo()"></td>
<%
				}
				else if(cStatus.equals("6")){//出库需分配
%>
                 <td  width="16"  bgcolor="#18FF00" onmousemove="showinfo(this,'出库需分配','<%=i%>排<%=j%>列')" onmouseout="hideinfo()"></td>
<%
				}
				else{//其他状态
%>
                 <td  width="16"  bgcolor="#F6FCC4" onmousemove="showinfo(this,'其他状态','<%=i%>排<%=j%>列')" onmouseout="hideinfo()"></td>
<%				
				}
			}
			else{//货位不存在  
%>
                 <td  width="16"  bgcolor="#E4FF00"></td>
<%
			}
		}
%>
   </tr>
<%
	}
%>
 </table>
</div>
<div id="info"></div>

</body>
</html>

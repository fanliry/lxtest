<%@ page language="java" pageEncoding="GBK"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>浙江刚玉自动化立体仓储管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/menu.css" rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/menuTree.js"></script>
</head>
<script>
<!--自适应高度的div-->
function windowHeight() {
    var de = document.documentElement;
    return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}
window.onload=window.onresize=function(){
    var wh1=windowHeight();
    document.getElementById("navheight").style.height = (wh1)+"px";
}
</script>
<body  onload="spanningTree('0');">
<script>
	var strCod="";
	var nextObj=null;
	function spanningTree(code)
	{
		strCod=code;
		menuTree.getModuleTreeNodes(code, viewTree);
	}
	function viewTree(data)
	{
		var strValue="";
		
		for(var i = 0; i < data.length; i++)
		{
			var strCode = data[i].strModuleCode;		//代码
			var strName = data[i].strModuleName;		//名称
			var strUrl = data[i].strModuleUrl;			//URL
			var strFlag = data[i].strFlag;
		
			if(strFlag == '0') 
			{
				strValue = strValue + "<div class='fblod' id='s"+strCode+"' onClick=DoMenu('"+strCode+"') style='background:url(<%=request.getContextPath()%>"+strUrl+") no-repeat'> <a  href='#'>"+strName+"</a></div>";
				strValue = strValue + "<div class='ps' id='"+strCode+"' style='display:none'>";
				strValue = strValue + "</div>";
					
			}else if(strFlag == '1')//有子节点
			{
				strValue = strValue + "<div id='s"+strCode+"' class='dot' onClick=NextMenu('"+strCode+"')> <a  href='#'> "+strName+"</a></div>"
				strValue = strValue + "<div class='ps' id='"+strCode+"' style='display:none'>";
				strValue = strValue + "</div>";
				
			}else//无子节点
			{
				strValue = strValue + "<div class='f'> <a  href='<%=request.getContextPath()%>"+strUrl+"' target='conright'> "+strName+"</a></div>";
			}
		}
		var id;
		if(strCod == '0')
		{
			id = document.getElementById("nav");
		}else
		{
			id = document.getElementById(strCod);	
		}
		
		id.innerHTML = strValue;
		//nav_end.scrollIntoView();
	}

</script>
      <div class="menu" id="navheight">
        <h1>系统菜单 </h1>
        <div class="padtb8">
  			<div class=" fblod" style="background:url(<%=request.getContextPath()%>/standard/images/left_menu_0.jpg) no-repeat"> <a  href="<%=request.getContextPath()%>/standard/include/right.jsp" target='conright'>系统首页</a></div>  
  			<div  id="nav"></div>
		</div>
	  </div>   
	  <script language="javascript" type="text/javascript">
function DoMenu(vd)
{
  var ob=document.getElementById(vd);
  if(ob.style.display=="block" || ob.style.display=="")
  {
     ob.style.display="none";
     var ob2=document.getElementById('s'+vd);
  
  }
  else
  {
    ob.style.display="block";
    var ob2=document.getElementById('s'+vd);

  }
  spanningTree(vd);
}
function NextMenu(vd)
{
  var ob=document.getElementById(vd);  
  if(ob.style.display=="block")
  {
     ob.style.display="none";
     var ob2=document.getElementById('s'+vd);
     ob2.style.backgroundImage="url(<%=request.getContextPath()%>/standard/images/ico080426_open.gif)";
  }
  else
  {
     ob.style.display="block";
     var ob2=document.getElementById('s'+vd);
     ob2.style.backgroundImage="url(<%=request.getContextPath()%>/standard/images/ico080426_close.gif)";
  }
  spanningTree(vd);
}
</script>
</body>
</html>

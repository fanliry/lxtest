<%@ page contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<title>浙江刚玉自动化立体仓储管理系统</title>
<style>
body{ margin:0; padding:0; font-family:"宋体",Verdana, Arial;font-size:12px; color:#000000; text-align:center;}
.login_body{min-height:1%; line-height:150%; background:url(<%=request.getContextPath()%>/standard/images/login_bg2.jpg) repeat-x; background-color:#5cbcff; }
.box{ width:280px; height:220px; left:50%; top:50%; margin-top:-100px; margin-left:-140px; position:absolute;}
.input_st{ width:121px; _width:120px; height:18px;  border:1px solid #0d75bf;; padding-left:3px;}
.input_st2{ width:127px; _width:125px; height:21px; line-height:21px;  border:1px solid #0d75bf;; padding-left:3px;}
.banquan{ text-align:center; color:#666; margin-top:40px;}
</style>
<script language="javascript"> 
function correctPNG()  
{ 
	
	
for(var i=0; i<document.images.length; i++) 
{ 
  var img = document.images[i] 
  var imgName = img.src.toUpperCase() 
  if (imgName.substring(imgName.length-3, imgName.length) == "PNG") 
  { 
   var imgID = (img.id) ? "id='" + img.id + "' " : "" 
   var imgClass = (img.className) ? "class='" + img.className + "' " : "" 
   var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' " 
   var imgStyle = "display:inline-block;" + img.style.cssText  
   if (img.align == "left") imgStyle = "float:left;" + imgStyle 
   if (img.align == "right") imgStyle = "float:right;" + imgStyle 
   if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle   
   var strNewHTML = "<span " + imgID + imgClass + imgTitle 
   + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";" 
   + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader" 
   + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>"  
   img.outerHTML = strNewHTML 
   i = i-1 
  } 
} 
} 
window.attachEvent("onload", correctPNG); 


	function UserLogin()
	{	
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var warehouse_id = document.getElementById("warehouseid").value;

		if(username == null || username.length <1)
		{
			alert("【用户名】不能为空！");
			return false;
		}
		if(password == null || password.length <1 )
		{
			alert("【密码】不能为空！");
			return false;
		}else
		{
			//document.getElementById("password").value = password2;
		}
		if(warehouse_id == null || warehouse_id.length < 1){
			alert("【仓库】不能为空！");
			return false;
		}
		
		
		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=UserLoginAction&&flag=rf&username=" + username + "&password=" + password + "&warehouseid="+warehouse_id;
		
		window.location.href = strUrl;
	}

	//表单重置
	function resetBtn()
	{
		document.getElementById("password").value="";
	    document.getElementById("username").value="";
　　 }
</script>
</head>
<body class="login_body" onload="selectObject('', 'warehouseid', '1');">
<div class="box">
<div> <img src="<%=request.getContextPath()%>/standard/images/logo.png" width="280" height="50" /></div>
<!--div style="margin:20px auto 15px;"> <img src="images/login.png" width="170" height="35" /></div-->
 <div style=" margin:4px auto 0px; text-align:right;width:193px; background:url(<%=request.getContextPath()%>/standard/images/zi_bg.png) no-repeat; color:#FFF;"  ><span>用户名：</span>
   <input id="username" name="username" type="text"  class="input_st" value="root"/></div>
    <div style="margin:9px auto 0px; _margin:6px 2px 4px 0px; text-align:right;width:193px;background:url(<%=request.getContextPath()%>/standard/images/zi_bg.png) no-repeat;   color:#FFF;"><span>密 &nbsp;码：</span>
<input id = "password" name="password" type="password"  class="input_st" value="123456"/></div>
    <div style="margin:7px auto 0px;_margin:5px 2px 4px 0px;  text-align:right;width:193px; background:url(<%=request.getContextPath()%>/standard/images/zi_bg.png) no-repeat; color:#FFF;"><span>仓 &nbsp;库：</span>
	    <select name="warehouseid" id="warehouseid" class="input_st2" >
	      <option>---请选择---</option>
	    </select>
    </div>
<div style="margin:30px auto 0px; text-align:center;">
      <input name="input" type="image" value="登录" src="<%=request.getContextPath()%>/standard/images/btn_login.jpg" onclick="UserLogin();" />
      <input name="input2" type="image" value="取消" src="<%=request.getContextPath()%>/standard/images/btn_quxiao.jpg" onclick="resetBtn();" />
    </div>
</div>
</body>
</html>

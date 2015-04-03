<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//检查数量是否为数字
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	} 
	//确定
	function Ok(){
		var priority = document.getElementById("priority").value;
		
		if(priority == null || priority.length < 1 || !IsNum(priority) || priority > 10 || priority < 1){
			alert("【优先级】不能为空且只能为1-10的数字！");
			return null;
		}
		window.returnValue = priority;
		window.close();
	}
-->
</script>
</head>

<body>

 <table width="98%" height="25" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td class="font_006699_bold_12">当前位置：作业管理 -&gt; 设定优先级</td>
   </tr>
 </table>
 <p>
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1"><div align="right"><span class="red">*</span>优先级：</div></td>
     <td class="xx1"><input type="text" name="priority" size="15"></td>
   </tr>
   <tr>
     <td align="center" colspan="2">
       <input type="button" class="BUTTON_STYLE1" value="确定" onClick="Ok()">
       <input type="button" class="BUTTON_STYLE1" value="取消" onClick="window.close()">
     </td>
   </tr>
 </table>
 <p>
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
     <td style="color: red;font-size:12px" height="25">提示信息：优先级只能为1-10的数字，数字越小优先级越高。</td>
   </tr>
 </table>
 
</body>
</html>
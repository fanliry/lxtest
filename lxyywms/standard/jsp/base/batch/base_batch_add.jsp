<%@ page contentType="text/html; charset=GBK"%>

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
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	
	//保存
	function OnOk()
	{
		var batch_name = document.getElementById("batch_name").value;
		var length = document.getElementById("length").value;
		var use_flag = document.getElementById("use_flag").checked?"Y":"N";
		
		if(batch_name == null || batch_name.length < 1)
		{
			alert("【批次名称】不能为空！");
			return;
		}	
		if(length == null || length.length < 1 || !IsNum(length))
		{
			alert("【长度】不能为空或只能为大于0小于100的数字！");
			return;
		}	
		var backmsg = "&batch_name=" + batch_name + "&length=" + length + "&use_flag=" + use_flag;
		window.returnValue = backmsg;
		window.close();
	}
-->
</script>
</head>

<body>
  <form name="myForm" method="post" action=""> 
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="font_006699_bold_12" height="25">当前位置：基本信息 -&gt; 批次管理 -&gt; 添加批次信息</td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>批次名称：</td>
      <td class="xx1"><input type="text" name="batch_name" size="15"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>长度：</td>
      <td class="xx1"><input type="text" name="length" size="15" maxlength="2"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">启用：</td>
      <td class="xx1"><input type="checkbox" name="use_flag" class="input_checkbox" checked></td>
    </tr>
    <tr>
      <td align="center" colspan="2">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="reset" name="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
  </form>   
  
</body>
</html>

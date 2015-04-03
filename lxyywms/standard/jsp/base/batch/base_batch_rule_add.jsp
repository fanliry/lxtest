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
	var batch_id;
	var length;
	
	//检查数量是否为数字
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	
	//获取规则
 	function GetRule()
 	{
		var start_pos = document.getElementById("start_pos").value;
		var end_pos = document.getElementById("end_pos").value;
		
		if(start_pos == null || start_pos.length < 1 || !IsNum(start_pos))
		{
			alert("【开始位置】不能为空或只能为数字且必须小于【"+length+"】！");
			return;
		}	
		if(end_pos == null || end_pos.length < 1 || !IsNum(end_pos) || end_pos>length)
		{
			alert("【结束位置】不能为空或只能为数字且必须小于【"+length+"】!");
			return;
		}
		start_pos = parseInt(start_pos);
		end_pos = parseInt(end_pos);
		if(start_pos > end_pos)
		{
			alert("【开始位置】必须小于【结束位置】！");
			return;
		}
		
		var part_length = parseInt(end_pos) - parseInt(start_pos) + 1;
		
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/batch/base_batch_rule_add_select.jsp", 
			part_length, "dialogWidth=350px;dialogHeight=250px");
	   	if(result != null && result.length > 0)
	   	{
	 		document.getElementById("rule_info").value = result;
	   	}
	}
	//保存
	function Save()
	{
		var rule_name = document.getElementById("rule_name").value;
		var start_pos = document.getElementById("start_pos").value;
		var end_pos = document.getElementById("end_pos").value;
		var rule_info = document.getElementById("rule_info").value;
		
		if(rule_name == null || rule_name.length < 1)
		{
			alert("【规则名称】不能为空！");
			return;
		}
		
		if(start_pos == null || start_pos.length < 1 || !IsNum(start_pos))
		{
			alert("【开始位置】不能为空或只能为数字且必须小于【"+length+"】！");
			return;
		}	
		if(end_pos == null || end_pos.length < 1 || !IsNum(end_pos) || end_pos>length)
		{
			alert("【结束位置】不能为空或只能为数字且必须小于【"+length+"】!");
			return;
		}
		if(rule_info == null || rule_info.length < 1)
		{
			alert("【规则信息】不能为空！");
			return;
		}
		start_pos = parseInt(start_pos);
		end_pos = parseInt(end_pos);
		
		if(start_pos > end_pos)
		{
			alert("【开始位置】必须小于【结束位置】！");
			return;
		}
		var backmsg = "&batch_id=" + batch_id + "&rule_name=" + rule_name + "&start_pos=" + start_pos + "&end_pos=" + end_pos + "&rule_info=" + rule_info;
		window.returnValue = backmsg;
		window.close();
	}
	//页面登陆
	function OnLoad()
	{
		var value = window.dialogArguments;
		var tem = value.split(",");
		batch_id = tem[0];
		length = parseInt(tem[1]);
		document.getElementById("batch_id").value = batch_id;
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
     
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="font_006699_bold_12" height="25">当前位置：基本信息 -&gt; 批次管理 -&gt; 添加批次规则</td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td class="yx1" align="right">批次代码：</td>
      <td class="xx1"><input type="text" name="batch_id" size="24" class="input_readonly" readonly></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>规则名称：</td>
      <td class="xx1"><input type="text" name="rule_name" size="24"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>开始位置：</td>
      <td class="xx1"><input type="text" name="start_pos" size="24" maxlength="2"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>结束位置：</td>
      <td class="xx1"><input type="text" name="end_pos" size="24" maxlength="2"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>规则：</td>
      <td class="xx1">
        <input type="text" name="rule_info" size="20" readonly>
        <input name="moreBtn" type="button" class="button_select" value="…" onClick="GetRule()">
      </td>
    </tr>
    <tr>
      <td align="center" colspan="2">
        <input onClick="Save()" type="button" name="save" value="保存" class="BUTTON_STYLE1">
        <input onClick="window.close()" type="button" name="report" value="关闭" class="BUTTON_STYLE1">
     </td>
    </tr>
  </table>
   
</body>
</html>

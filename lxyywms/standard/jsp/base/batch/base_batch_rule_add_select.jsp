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
	function IsCLetter(str)
	{
		var reNum=/^[A-Z]*$/; 
		return(reNum.test(str)); 
	} 
	function IsLLetter(str)
	{
		var reNum=/^[a-z]*$/; 
		return(reNum.test(str)); 
	}
	function IsRight(str)
	{
		if(str.indexOf("\\") == -1)
		{
			return true;
		}
		return false;
	} 
	var length = 0;
	//确定
 	function OnOk()
 	{
		var backmsg = "";
		var check_ids = document.getElementsByName("check_id");
		var nums = document.getElementsByName("num");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				if(nums[i].value == null && nums[i].value.length < 1)
				{
					alert("第" + (i+1) + "范围不能为空！");
					return;
				}
				else if(check_ids[i].value == "1")
				{
					var aem = nums[i].value.split(",");
					for(var j=0; j<aem.length; j++)
					{
						var bem = aem[j].split("-");
						if(bem[0] == null || bem[1] == null || !IsNum(bem[0]) || !IsNum(bem[1]))
						{
							alert("【数字】范围值错误！");
							return;
						}
						backmsg += "([" + aem[j] + "])|";
					}
				}
				else if(check_ids[i].value == "2")
				{
					var aem = nums[i].value.split(",");
					for(var j=0; j<aem.length; j++)
					{
						var bem = aem[j].split("-");
						if(bem[0] == null || bem[1] == null || !IsCLetter(bem[0]) || !IsCLetter(bem[1]))
						{
							alert("【大写字母】范围值错误！");
							return;
						}
						backmsg += "([" + aem[j] + "])|";
					}
				}
				else if(check_ids[i].value == "3")
				{
					var aem = nums[i].value.split(",");
					for(var j=0; j<aem.length; j++)
					{
						var bem = aem[j].split("-");
						if(bem[0] == null || bem[1] == null || !IsLLetter(bem[0]) || !IsLLetter(bem[1]))
						{
							alert("【小写字母】范围值错误！");
							return;
						}
						backmsg += "([" + aem[j] + "])|";
					}
				}
				else
				{
					var aem = nums[i].value.split(",");
					for(var j=0; j<aem.length; j++)
					{
						//if(aem[j].length != length)
						//{
						//	alert("【特殊字符】长度不等于" + length + "！");
						//	return;
						//}
						//else 
						if(!IsRight(aem[j]))
						{
							alert("【特殊字符】中不能包含“\\”！");
							return;
						}
						backmsg += "" + aem[j] + "|";
					}
				}
			}
		}
		if(backmsg == "")
		{
			alert("请选择记录！");
			return;
		}
		window.returnValue = "^(" + backmsg.substring(0, backmsg.length-1) + ")*$";
		window.close();	
	}
	function OnLoad()
	{
		length = window.dialogArguments;
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
     
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="font_006699_bold_12" height="25">当前操作：规则设置</td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
    <tr>
      <td class="TD_LIST_TITLE1"><div class="list_title">选择</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">规则类型</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">范围</div></td>
    </tr>
    <tr>
      <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="1"></td>
      <td class="TD_LIST" align="center">数字</td>
      <td class="TD_LIST2" align="center"><input type="text" name="num" size="15" value="1-9"></td>
    </tr>
    <tr>
      <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="2"></td>
      <td class="TD_LIST" align="center">大写字母</td>
      <td class="TD_LIST2" align="center"><input type="text" name="num" size="15" value="A-Z"></td>
    </tr>
    <tr>
      <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="3"></td>
      <td class="TD_LIST" align="center">小写字母</td>
      <td class="TD_LIST2" align="center"><input type="text" name="num" size="15" value="a-z"></td>
    </tr>
    <tr>
      <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="4"></td>
      <td class="TD_LIST" align="center">特殊字符</td>
      <td class="TD_LIST2" align="center"><input type="text" name="num" size="15"></td>
    </tr>
    <tr>
      <td align="center" colspan="3">
        <input onClick="OnOk()" type="button" name="save" value="确定" class="BUTTON_STYLE1">
        <input onClick="window.close()" type="button" name="report" value="取消" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
  <table width="100%" align="center" border="0" cellspacing="0" cellpadding="10px">
    <tr>
      <td><div style="color: red; font-size:12px;">说明：范围内用“-”隔开，如“1-9”；范围间用半角“,”号隔开，如“1-3,5-7”；特殊字符中不能包含“\”，如“\C”。</div></td>
    </tr>
  </table>
 
</body>
</html>

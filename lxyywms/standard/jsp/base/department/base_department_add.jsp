<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>增加部门信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	function OnOk(){
		var shortname = document.getElementById("shortname").value;
		var departmentname = document.getElementById("departmentname").value;
		var departmenttype = document.getElementById("departmenttype").value;
		var contact = document.getElementById("contact").value;
		var phone = document.getElementById("phone").value;
		var fax = document.getElementById("fax").value;
		var address = document.getElementById("address").value;
		
		if(departmentname == null || departmentname.length <1)
		{
			alert("【部门全称】不能为空!");
			return;
		}
		if(phone!=null && phone.length>0)
		{
			if(strlen(phone) > 20){
				alert("【联系电话】过长!");
				return;
			}
		}
		
		if(fax!=null && fax.length>0)
		{
			if(strlen(fax) > 20){
				alert("【传真】过长!");
				return;
			}
		}

		var back_msg = "&shortname=" + shortname + "&departmentname=" + departmentname + "&departmenttype=" + departmenttype
			 + "&contact=" + contact + "&phone=" + phone + "&fax=" + fax + "&address=" + address;
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
		var typevalue = "bmlx";		//部门类型的值	
		selectType("", "departmenttype", typevalue);
	}
-->
</script>
</head>

<body onload="OnLoad();">

  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 部门管理 -&gt; 新增部门信息</div></td>
    </tr>
  </table>
  
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">部门简称：</td>
      <td class="xx1"><input type="text" name="shortname" maxlength="25" size="25"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>部门全称：</td>
      <td class="xx1"><input type="text" name="departmentname" maxlength="50" size="50"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">部门分类：</td>
      <td class="xx1"><select name="departmenttype"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">联系人：</td>
      <td class="xx1"><input type="text" name="contact" maxlength="10" size="10"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">联系电话：</td>
      <td class="xx1"><input type="text" name="phone" maxlength="20" size="20"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">传真：</td>
      <td class="xx1"><input type="text" name="fax" maxlength="20" size="20"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">地址：</td>
      <td class="xx1"><input type="text" name="address" maxlength="50" size="50"></td>
    </tr>
    <tr>
      <td height="27" colspan="2" align="center">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="reset" name="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
  </form>
  
</body>
</html>
<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>增加客户信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript">
<!--
	function OnOk(){
		var shortname = document.getElementById("shortname").value;
		var customername = document.getElementById("customername").value;
		var customertype = document.getElementById("customertype").value;
		var contact = document.getElementById("contact").value;
		var phone = document.getElementById("phone").value;
		var fax = document.getElementById("fax").value;
		var address = document.getElementById("address").value;
		var bank = document.getElementById("bank").value;
	    var putawayid = document.getElementById("putawayid").value;			// 上架规则ID
	    var allocationid = document.getElementById("allocationid").value;	// 分配规则ID
	    var replenishid = document.getElementById("replenishid").value;		// 补货规则ID
		var linenumber = document.getElementById("linenumber").value;
	    var pakageid = document.getElementById("package_id").value;			// 包装ID
		
		if(customername == null || customername.length <1)
		{
			alert("【客户全称】不能为空!");
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
		
		if(bank!=null && bank.length>0)
		{
			if(strlen(bank) > 50){
				alert("【开户银行】过长!");
				return;
			}
		}
		
		if(linenumber!=null && linenumber.length>0)
		{
			if(!numChar(linenumber)){
				alert("【线路号】必须是数字!");
				return;
			}
		}

		var back_msg = "&shortname=" + shortname + "&customername=" + customername + "&customertype=" + customertype
			 + "&contact=" + contact + "&phone=" + phone + "&fax=" + fax + "&address=" + address + "&bank=" + bank 
			 + "&putawayid=" + putawayid + "&allocationid=" + allocationid + "&replenishid=" + replenishid
			 + "&linenumber=" + linenumber + "&pakageid=" + pakageid;
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
			
		selectType("", "customertype", "khlx");		//客户类型
		selectRules("", "putawayid", "", "1");		//上架规则
		selectRules("", "allocationid", "",  "2");	//分配规则
		selectRules("", "replenishid", "", "3");	//补货规则
	}
-->
</script>
</head>

<body onload="OnLoad();">
  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 客户管理 -&gt; 新增客户信息</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">客户简称：</td>
      <td class="xx1" colspan="3"><input type="text" name="shortname" maxlength="25" size="25"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>客户全称：</td>
      <td class="xx1" colspan="3"><input type="text" name="customername" maxlength="50" size="50"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">客户分类：</td>
      <td class="xx1" colspan="3"><select name="customertype"><option value=""></option></select></td>
    </tr> 
    <tr>
      <td width="100px" class="yx1" align="right">联系人：</td>
      <td class="xx1"><input type="text" name="contact" maxlength="10" size="15"></td>
      <td width="100px" class="yx1" align="right">联系电话：</td>
      <td class="xx1"><input type="text" name="phone" maxlength="20" size="15"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">传真：</td>
      <td class="xx1" colspan="3"><input type="text" name="fax" maxlength="20" size="15"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">开户银行：</td>
      <td class="xx1" colspan="3"><input type="text" name="bank" maxlength="50" size="25"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">地址：</td>
      <td class="xx1" colspan="3"><input type="text" name="address" maxlength="50" size="50"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">上架规则：</td>
      <td class="xx1"><select id="putawayid" style="width:110px;"><option value="">--请选择--</option></select></td>
      <td class="yx1" align="right">分配规则：</td>
      <td class="xx1"><select id="allocationid" style="width:110px;"><option value="">--请选择--</option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">补货规则：</td>
      <td class="xx1"><select id="replenishid" style="width:110px;"><option value="">--请选择--</option></select></td>
      <td class="yx1" align="right">线路号：</td>
      <td class="xx1"><input type="text" name="linenumber" maxlength="5" size="5"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">包装：</td>
      <td class="yx" colspan="3">
     	<input type="hidden" id="package_id"><input type="text" id="package_name" size="24" readonly>
       	<input onClick="openPackage('<%=request.getContextPath()%>/standard/jsp/common/common_package.jsp','850','550');" type="button" value="…" class="button_select">
      </td>
      
    </tr>
    <tr>
      <td height="27" colspan="4" align="center">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="reset" name="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
  </form>
  
</body>
</html>
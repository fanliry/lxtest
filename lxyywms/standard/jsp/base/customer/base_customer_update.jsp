<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCustomer" %>
<%
	BaseCustomer customer = (BaseCustomer)request.getAttribute("customer");  
	String customerid = customer.getCustomerid();		// 客户编号
    String shortname = customer.getShortname();			// 客户简称
    String customername = customer.getCustomername();	// 客户全称
    String customertype = customer.getCustomertype();	// 客户分类
    String contact = customer.getContact();				// 联系人
    String phone = customer.getPhone();					// 联系电话
    String fax = customer.getFax();						// 传真
    String address = customer.getAddress();				// 地址
    String bank = customer.getBank();					// 开户银行
    String putawayid = customer.getPutawayid();			// 上架规则ID
    String allocationid = customer.getAllocationid();	// 分配规则ID
    String replenishid = customer.getReplenishid();		// 补货规则包装
	String linenumber = customer.getLinenumber();		// 线路号
	String pakageid = customer.getPakageid();			// 包装包装
    String pkgdesc = customer.getPkgdesc();				// 包装描述
%>
<html>
<head>
<title>修改客户信息</title>
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
	//保存客户信息
	function OnOk(){
		var id = document.getElementById("id").value;
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

		var back_msg = "&id=" + id + "&shortname=" + shortname + "&customername=" + customername + "&customertype=" + customertype 
			+ "&contact=" + contact + "&phone=" + phone + "&fax=" + fax + "&address=" + address + "&bank=" + bank
			+ "&putawayid=" + putawayid + "&allocationid=" + allocationid + "&replenishid=" + replenishid  
			+ "&linenumber=" + linenumber + "&pakageid=" + pakageid;
		
    	window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
	
		selectType("<%=customertype%>", "customertype", "khlx");	//客户类型的值
		
		selectRules("<%=putawayid%>", "putawayid", "", "1");		//上架规则
		selectRules("<%=allocationid%>", "allocationid", "",  "2");	//分配规则
		selectRules("<%=replenishid%>", "replenishid", "", "3");	//补货规则
	}

-->
</script>
</head>

<body onload="OnLoad();">
 
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 客户管理 -&gt; 修改客户信息</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">客户简称：</td>
      <td class="xx1" colspan="3">
     	<input type="hidden" id="id" value="<%=customerid%>">
     	<input type="text" name="shortname" maxlength="25" size="25" value="<%=shortname%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>客户全称：</td>
      <td class="xx1" colspan="3">
     	<input type="text" name="customername" maxlength="50" size="50" value="<%=customername%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">客户分类：</td>
      <td class="xx1" colspan="3"><select name="customertype"><option value=""></option></select></td>
    </tr>
    <tr>
      <td width="100px" class="yx1" align="right">联系人：</td>
      <td class="xx1"><input type="text" name="contact" maxlength="10" size="15" value="<%=contact%>"></td>
      <td width="100px" class="yx1" align="right">联系电话：</td>
      <td class="xx1"><input type="text" name="phone" maxlength="20" size="15" value="<%=phone%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">传真：</td>
      <td class="xx1" colspan="3"><input type="text" name="fax" maxlength="20" size="15" value="<%=fax%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">地址：</td>
      <td class="xx1" colspan="3"><input type="text" name="address" maxlength="50" size="50" value="<%=address%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">开户银行：</td>
      <td class="xx1" colspan="3"><input type="text" name="bank" maxlength="50" size="25" value="<%=bank%>"></td>
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
      <td class="xx1"><input type="text" name="linenumber" maxlength="5" size="5" value="<%=linenumber==null?"":linenumber%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">包装：</td>
      <td class="yx" colspan="3">
     	<input type="hidden" id="package_id" value="<%=pakageid%>">
     	<input type="text" id="package_name" size="24" readonly value="<%=pkgdesc==null?"":pkgdesc%>">
       	<input onClick="openPackage('<%=request.getContextPath()%>/standard/jsp/common/common_package.jsp','850','550');" 
       		type="button" value="…" class="button_select">
      </td>
    </tr>
    <tr>
      <td class="TD_MGR_TOP" align="center" colspan="4">
       <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
       <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>
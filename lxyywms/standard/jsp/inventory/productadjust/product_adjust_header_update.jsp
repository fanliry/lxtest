<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryTransferHeader" %>
<%
	InventoryTransferHeader adjust = null;
	if(request.getAttribute("adjust") != null)
	{
		adjust = (InventoryTransferHeader)request.getAttribute("adjust");
	}
	String strId = "";
	String strStatus = "";
	String strCustomer = "";
	String strCustomerName = "";
	String strReasonCode = "";
	String strReasonDescr = "";
	if(adjust != null)
	{
		strId = adjust.getTransferid();
		strStatus = adjust.getStatus();
		strCustomer = adjust.getCustomerid();
		strReasonCode = adjust.getReasoncode();
		strReasonDescr = adjust.getReasondiscr();	
		strCustomerName = adjust.getCustomername();
	}
	strReasonCode = strReasonCode == null ? "" : strReasonCode;
	
%>
<html>
<head>
<title>更新库存调整单</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript">
<!--
	//保存
	function saveData(id)
	{
		var strParam = "BMSService?actionCode=productAdjustAction&method=ricoExecUpdate" 
							+ "&id="+id
        					+ "&customerid="+document.getElementById("customer_id").value
        					+ "&status="+document.getElementById("status").value
        					+ "&reasoncode="+document.getElementById("reasoncode").value
							+ "&reasondiscr=" + document.getElementById("reasondiscr").value;     
        window.returnValue=strParam;
		window.close();
	}
	function checkData()
	{
		return true;
	}
	function basCustomer(obj, url, obj1)
    {
		var strUrl=url + '/jsp/selectPages/customer_select.jsp';
		var result = showModalDialog(strUrl,'','dialogWidth=650px;dialogHeight=440px');	
		if(result != null && result.length > 0)
		{
			 var tempParam = result;
	
			 var temStr = tempParam.split("|");
			 		
			 document.getElementById(obj).value = temStr[0];
			 if(obj1)
			 {
			 	document.getElementById(obj1).value = temStr[1];
			 }
		}
    }
    function OnLoad()
	{
		selectType('2', 'doctype', 'dzlx');
	}
-->
</script>
</head>

<body  onload="javascript:OnLoad();">
<form method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：货品调整单 -&gt; 更新货品调整单</div></td>
   </tr>
 </table>
 
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
   
     <td class="yx1"><div align="right">客户：</div></td>
     <td class="yx"><div align="left">
            <input type="text" name="customer_name" class="input_readonly" size="15" value="<%=strCustomerName%>"/>
     		<input type="hidden" name="customer_id" value="<%=strCustomer%>">
	        <input name="moreBtn" type="button" class="button_select" value="…" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520');" />
	 </div></td>
	 <td class="yx1" width="60"><div align="right">单证类型：</div></td>
            <td class="yx"  width="" disabled><div align="left">
               <select id="doctype" style="width:130px;">
               <option value="">--请选择--</option>
            </select></div>
     </td>
     
     
   </tr>

   
   <tr>
     <td class="yx1"><div align="right">状态：</div></td>
     <td class="yx"><div align="left">
     <select name="status" class="input_readonly" style="width:130px;">
	         <option value="0" <%=strStatus.equals("0") ? "selected" : ""%>>创建</option>
	         <option value="1" <%=strStatus.equals("1") ? "selected" : ""%>>完成</option>
	 </select>
     </div></td>
     <td  class="yx1"><div align="right">原因代码：</div></td>
     <td  class="yx"><div align="left">
     	<select name="reasoncode" style="width:130px;">
	         <option value="">--请选择--</option>
	         <option value="1" <%=strReasonCode.equals("1") ? "selected" : ""%>>正常</option>
	         <option value="2" <%=strReasonCode.equals("2") ? "selected" : ""%>>产品质量问题</option>
	    </select>  
	  </div></td>
     
   </tr>
   <tr>
     <td class="yx1"><div align="right">原因：</div></td>
     <td class="yx" colspan="3"><div align="left">
       
     		<input type="text" size="17" name="reasondiscr" value="<%=strReasonDescr == null ? "" : strReasonDescr%>">
	       
     </div></td>
   </tr>
   <tr>
     <td height="27" colspan="4" align="center" ><input name="saveBtn" type="button" class="button_add" value="&nbsp;&nbsp;保存" onClick="if(checkData()!=false){saveData('<%=strId%>');}"/>
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;重置" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
     </td>
   </tr>
 </table>
 </form>
</body>
</html>
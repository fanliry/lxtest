<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustHeader"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	InventoryAdjustHeader adjust = null;
	if(request.getAttribute("adjust") != null)
	{
		adjust = (InventoryAdjustHeader)request.getAttribute("adjust");
	}
	String strId = "";
	String strStatus = "";
	String strWarehouseid = "";
	String strAdjusttype = "";
	String strAdjusttypename = "";
	String strReasonCode = "";
	String strReasonDescr = "";
	String strWarehousename = "";
	if(adjust != null)
	{  
		strId = adjust.getAdjustid();
		strStatus = adjust.getStatus();
		strWarehousename= adjust.getWarehousename();
		strWarehouseid = adjust.getWarehouseid();
		strAdjusttype = adjust.getAdjusttype();
		strAdjusttypename = adjust.getAdjusttypename();
		strReasonCode = adjust.getReasoncode();
		strReasonDescr = adjust.getReasondiscr();	
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
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<script type="text/javascript">
<!--
	//保存
	function saveData(id)
	{
	   var strreasoncode = document.getElementById("reasoncode").value;
	   var strreasondiscr = document.getElementById("reasondiscr").value;
		if(strreasoncode == null || strreasoncode.length<1)
		{
			alert("【调整原因】不能为空");
			return;
		}
		
		var strParam = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecUpdate" 
							+ "&id="+id			
	    					+ "&reasoncode="+strreasoncode
							+ "&reasondiscr=" + strreasondiscr;   
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
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		//selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		selectType("", 'reasoncode', 'tzyy');
		//selectType("", 'adjtype', 'tzlx');
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			if(back_msg == "Y"){
				alert("操作成功！");
			}
			else{
				alert(back_msg);
				window.close();
			}
		}
	}
-->
</script>
</head>

<body onload="javascript:OnLoad();">
<form method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：库存调整单 -&gt; 更新库存调整单</div></td>
   </tr>
 </table>
 
 <table  width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td class="yx1" width="100" align="right">仓库：</td>
     <td class="yx"><select name="warehouseid" id="warehouseid" style="width:117px;" disabled="disabled"><option value="<%=strWarehouseid%>"><%=strWarehousename%></option></select></td>
	 <td class="yx1"><div align="right">状态：</div></td>
     <td class="xx1"><div align="left">
     <select name="status" class="input_readonly" style="width:130px;" disabled="disabled" >
	         <option value="0" selected="selected">新建</option>
	 </select>
     </div></td>
   </tr>
   <tr>	     
          <td class="yx1" align="right">调整类型：</td>
	      <td class="yx">
	      	<select id="adjtype" name="adjtype" style="width:117px;" disabled="disabled">
                <option value="<%=strAdjusttype %>>"><%=strAdjusttypename%></option>
              </select>
	      </td>
	      <td class="yx1" align="right">调整原因：</td>
	      <td class="xx1">
	      	<select id="reasoncode" style="width:117px;">
                <option value="">--请选择--</option>
              </select>
	      </td>

   </tr>
   <tr>
     <td class="yx1"><div align="right">原因描述：</div></td>
     <td class="xx1" colspan="3"><div align="left">
     		<input type="text" size="40" value="<%=strReasonDescr %>" name="reasondiscr">    
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
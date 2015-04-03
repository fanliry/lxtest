<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask"%>
<%
	InventoryCheckTask checkTask = (InventoryCheckTask)request.getAttribute("checkTask");
    InventoryCheckResult checkResult = (InventoryCheckResult)request.getAttribute("checkResult");
	String taskid = "";
	String lotnumber = "";//批号
	String num = "";//数量
	String proName = "";// 产品名 
	String user="";
	String checknum = "";
	String createTime="";
	String resultid="";
	if(checkTask !=null){
	lotnumber = checkTask.getLotinfo();//批号
	num = String.valueOf(checkTask.getNum());//数量
	proName = checkTask.getProductName();// 产品名
	taskid = checkTask.getTaskid();//任务id
	}
	if(checkResult != null){
	checknum = String.valueOf(checkResult.getChecknum());	
	user = checkResult.getCreateman();
	createTime = checkResult.getChecktime();
	resultid = checkResult.getCheckid();
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>
    function LockButton(){
    	var getnum = document.getElementById("resultid").value;
    	if(getnum!=""){
    		//alert(getnum);
    		parent.document.getElementById("add").disabled=true;
    		parent.document.getElementById("update").disabled=false;
    	}
    }
	//页面登陆
	function onLoad()
	{
		LockButton();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		//alert(back_msg);
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
	}
</script>

</head>
<body onload="javascript:onLoad();">

	 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP" >
        <tr>
          <td width="30%" align="right" class="yx1"  >批号：</td>
          <td width="70%"  class="yx" >
          	 <input type="text" name="lotinfo" class="input_readonly" value="<%=lotnumber%>" readonly   style="height:18px;width:100px;"/> 
          </td>
        </tr>
        <tr>  
          <td  align="right" class="yx1">品&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
          <td  class="yx">
	          <input type="text" name=product_name class="input_readonly" value="<%=proName%>"  readonly  style="height:18px;width:100px;"/>
          </td>
        </tr>
 
        <tr>
          <td align="right" class="yx1">系统数量：</td>
          <td class="yx">
          	<input type="text" name="sysnum" size="16" class="input_readonly"  readonly  style="height:18px;width:100px;" value="<%=num%>"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>盘点数量：</td>
          <td class="yx">
          	<input type="text" name="getnum" id="getnum" size="16"  style="height:18px;width:100px;" value="<%=checknum %>"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">操作人：</td>
          <td class="yx">
          	<input type="text" name="createuser" size="16"  class="input_readonly" readonly style="height:18px;width:100px;" value="<%=user%>"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">盘点时间：</td>
          <td class="yx">
          	<input type="text" name="checktime" size="16"  class="input_readonly" readonly style="height:18px;width:100px;" value="<%=createTime%>"/>
          </td>
        </tr>
        <tr>
     		<td height="5" colspan="2">
     			<input type="hidden" name="taskid" id="taskid" value="<%=taskid%>"/>
     			<input type="hidden" name="resultid" id="resultid" value="<%=resultid%>"/>
     		</td>
   		</tr>
      </table>
</body>
</html>

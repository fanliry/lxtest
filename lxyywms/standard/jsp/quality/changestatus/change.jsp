<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
%>
<html>
  <head>
    <title>浙江刚玉物流仓库管理系统</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	
	<script>
	//检查数量是否为数字
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	//检查数据是否为浮点数
	function IsFloat(ch){
		var re = /^\d+(\.\d+)?$/;
		return re.test(ch);
	}
	function IsRight(print_date){ 
		var str = /^(\d{4})-(\d{2})-(\d{2})$/;
		return(str.test(print_date));
	}
	/*锁定按钮*/
	function LockButton(){
		
		document.getElementById("add").disabled = true;
	}
	/*释放按钮*/
	function UnLockButton(){
		
		document.getElementById("add").disabled = false;
	}	
	//保存数据
	function saveData()
	{
		var details1 = document.getElementById("inventoryids").value;
		var wpzt = document.getElementById("wpzt").value;
		
		if(details1 != ""){
			details1 = details1.substring(0, details1.length);
		}
		
		if(wpzt == null || wpzt.length < 1){
			alert("请先选择【新物品状态】!");
			return;
		}
		
		var condition =details1+"&wpzt=" + wpzt;	
		 
		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryQualityAction&method=ricoExecUpdateStatusAndAddRecord";
		window.returnValue = strUrl + condition;
    	window.close();
	}
		function OnLoad()
		{
		    var obj = window.dialogArguments
			document.getElementById("inventoryids").value=obj;
			selectType('', 'wpzt', 'wpzt');
		}
	</script>
	
  </head>
  
  <body onload="javascript:OnLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：质检管理 -&gt; 状态转换 -&gt; 转换</div></td>
   </tr>
  </table>
	<FORM>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="50%" align="right" class="yx1"><input name="inventoryids" type="hidden" id="inventoryids"/><span class="red">*</span>新物品状态：</td>
          <td width="50%" class="yx"><select name="wpzt" id="wpzt"  style="width:150px;">
            <option>---请选择---</option>
          </select></td>
        </tr>
        <tr >
        	<TD colspan="2" height="20"></TD>
        </tr>
        <tr>
          <td height="27" colspan="2" align="center" ><input name="add" type="button" class="button_add" id="add" value="&nbsp;&nbsp;确定" onClick="saveData();"/>
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
	</FORM>
  </body>
</html>

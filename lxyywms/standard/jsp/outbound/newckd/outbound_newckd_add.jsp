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
		LockButton();
		//仓库ID
		var warehouseid = document.getElementById("warehouseid").value;
		//类型
		var outtype = document.getElementById("outtype").value;
		
		//货主
		//var owner_id = document.getElementById("owner_id").value;
				
		var owner_id= "";
		//客户
		var customer_id = document.getElementById("customer_id").value;
		//地址
		var address = ""; //document.getElementById("address").value;
		
		//车牌
		var vehicleno = document.getElementById("vehicleno").value;
		//车位
		var vehiclepos = document.getElementById("vehiclepos").value;
		//部门
		var departid  = document.getElementById("departid").value;
		
		//优先级
		//var inpriority = document.getElementById("inpriority").value;
		//发货月台
		var sendplatform = "";
		//集货位
		var setposition = "";
		//销售单号
		var saleno = "";

		//要求发货时间
		var requestdate  = ""; //document.getElementById("requestdate").value;
		//预期发货时间
		var expectdate  = ""; //document.getElementById("expectdate").value;	
		//单据日期
		var formdate  = document.getElementById("formdate").value;
		
		
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("【仓库】不能为空！");
			UnLockButton();
			return;
		}
		if(outtype == null || outtype.length < 1){
			alert("【出库类型】不能为空！");
			UnLockButton();
			return;
		}
		/*
		if(customer_id == null || customer_id.length < 1){
			alert("【收货人】不能为空！");
			UnLockButton();
			return;
		}*/
		
		var condition = "&out_type=" + outtype + "&warehouseid=" + warehouseid + "&vehicleno=" + vehicleno + "&vehiclepos=" + vehiclepos + "&owner_id=" + owner_id + "&customer_id=" + customer_id + "&address=" + address +  
					"&sendplatform=" + sendplatform + "&setposition=" + setposition  + "&requestdate=" + requestdate + "&expectdate=" + expectdate + "&formdate=" + formdate+ "&departid=" + departid+ "&saleno=" + saleno;
		
		//************************************************
		if(confirm("你确定保存？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&method=addRicoExec";
			window.returnValue = strUrl + condition;
    		window.close();
				
		}else{
			UnLockButton();
		}
	}
		
		
		
		
		function OnLoad()
		{
			selectObject('', 'warehouseid', '1');
			selectType('', 'outtype', 'ckdj');
			selectType('', 'vehiclepos', 'carpos');
			//selectObject('', 'departid', 'bumen');
		}
	</script>
	
  </head>
  
  <body onload="javascript:OnLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：出库管理 -&gt; 新建出库 -&gt; 新增</div></td>
   </tr>
  </table>
	<FORM>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="13%" align="right" class="yx1"  ><span class="red">*</span>仓&nbsp;&nbsp;&nbsp;&nbsp;库：</td>
          <td width="18%" class="yx" >
          	<select name="warehouseid" id="warehouseid" style="width:100px;">
            	<option>--请选择--</option>
            </select>
          
          </td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>出库类型：</td>
          <td width="20%" class="yx"><select name="outtype" id="outtype"  style="width:100px;">
            <option>---请选择---</option>
          </select></td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>单据状态：</td>
          <td width="19%" class="xx1"><select name="instatus" id="instatus" readonly="readonly"  style="width:100px;">
            <option selected="selected">开单</option>
          </select></td>
        </tr>
        <tr>
    	<td align="right" class="yx1">
			<span class="red">*</span>部&nbsp;&nbsp;&nbsp;&nbsp;门：
		</td>
		<td class="xx1">
			<input type="text" id="departname" readonly="readonly"
				class="input_readonly" style="height: 18px; width: 100px;" />
			<input type="hidden" id="departid" />
			<input name="moreBtn" type="button" class="button_select"
				value="…"
				onclick="openDepartment('<%=request.getContextPath()%>/standard/jsp/common/common_department.jsp','800','520','departid','departname');" />
		</td>
		<td align="right" class="yx1">
			<span class="red">*</span><span id="changename">客&nbsp;&nbsp;&nbsp;&nbsp;户：</span>
		</td>
		<td class="yx" colspan="3">
			<input type="text" name="customer_name" id="customer_name"
				readonly="readonly" class="input_readonly"
				style="height: 18px; width: 100px;" />
			<input type="hidden" id="customer_id" />
			<input name="moreBtn" type="button" class="button_select"
				value="…"
				onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=','800','520');" />
		</td>
<!--           <td align="right" class="yx1">收货地址：</td>
          <td class="xx1">
          	<input type="text" name="address"    style="height:18px;width:100px;"/>
          </td> -->
        </tr>
      <tr>
          <td align="right" class="yx1">单据日期：</td>
          <td class="yx">
          	<input name="formdate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
          <td align="right" class="yx1">车&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
          <td class="yx">
          		<select name="vehiclepos" style="width:100px;">
                  <option value="">--请选择--</option>
                </select>
          </td>
          <td align="right" class="yx1">车&nbsp;&nbsp;&nbsp;&nbsp;牌：</td>
          <td class="yx">
          	<input type="text" name="vehicleno"    style="height:18px;width:100px;"/>
          </td>
           <!-- <td align="right" class="yx1">货&nbsp;&nbsp;&nbsp;&nbsp;主：</td>
          <td class="yx">
          	<input type="text" name="owner_name"  readonly="readonly" class="input_readonly"  style="height:18px;width:100px;"/>
            <input type="hidden" name="owner_id" />
            <input name="moreBtn" type="button" class="button_select" value="…" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520','owner_id','owner_name');" />
           </td>-->
        </tr>  
        <!--<tr>
          <td align="right" class="yx1">发货月台：</td>
          <td class="yx">
          	<input type="text" name="sendplatform"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">集&nbsp;货&nbsp;位：</td>
          <td class="yx">
          	 <input type="text" name="setposition"   style="height:18px;width:100px;"/>
         </td>
          <td align="right" class="yx1">销售单号：</td>
          <td class="xx1" >
            <input type="text" name="saleno" size="16"   style="height:18px;width:100px;"/>
            
         </td>
        </tr>  -->
        <tr>
<%--           <td align="right" class="yx1">单据日期：</td>
          <td class="yx">
          	<input name="formdate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td> --%>
<%--           <td align="right" class="yx1">要求发货时间：</td>
          <td class="yx">
          		<input name="requestdate" type="text" size="13" value="<%=strTime%>"  onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
          <td align="right" class="yx1">预期发货时间：</td>
          <td class="xx1" >
          		<input name="expectdate" type="text" size="13" value="<%=strTime%>"  onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td> --%>
        </tr>
        
        <tr >
        	<TD colspan="6" height="20"></TD>
        </tr>
        <tr>
          <td height="27" colspan="6" align="center" ><input name="add" type="button" class="button_add" id="add" value="&nbsp;&nbsp;保存" onClick="saveData();"/>
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;重置" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
	</FORM>
  </body>
</html>

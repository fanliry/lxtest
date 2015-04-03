<%@ page contentType="text/html; charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>浙江刚玉物流仓库管理系统</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
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
		var intype = document.getElementById("intype").value;
		//货主
		var customer_id = document.getElementById("customer_id").value;
		
		//预期到货时间
		var arrivestarttime = document.getElementById("arrivestarttime").value;
		//预期到货时间
		var arriveendtime = document.getElementById("arriveendtime").value;
		//承运人
		var shipperid = document.getElementById("shipperid").value;
		
		
		//供应商
		var supplierid = document.getElementById("supplierid").value;
		//收货月台
		var receiveloc = document.getElementById("receiveloc").value;
		//海关备案号
		var customsno = document.getElementById("customsno").value;
		//用户自定义1
		var reserve1  = document.getElementById("reserve1").value;
		//用户自定义2
		var reserve2  = document.getElementById("reserve2").value;
		//备注
		var remark  = document.getElementById("remark").value;
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("【仓库】不能为空！");
			UnLockButton();
			return;
		}
		if(intype == null || intype.length < 1){
			alert("【入库类型】不能为空！");
			UnLockButton();
			return;
		}

		if(customer_id == null || customer_id.length < 1 ){
			alert("【货主】不能为空！");
			UnLockButton();
			return;
		}
		
		
		var condition = "&intype=" + intype + "&warehouseid=" + warehouseid + "&customer_id=" + customer_id + "&arrivestarttime=" + arrivestarttime + "&arriveendtime="+arriveendtime + 
					"&shipperid=" + shipperid + "&supplierid=" + supplierid + "&receiveloc=" + receiveloc + "&customsno=" + customsno + "&reserve1=" + reserve1+ "&reserve2=" + reserve2+ "&remark=" + remark;
		
		//************************************************
		if(confirm("你确定保存？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&method=addRicoExec";
			window.returnValue = strUrl + condition;
    		window.close();
				
		}else{
			UnLockButton();
		}
	}
		
		
		
		
		function OnLoad()
		{
			selectObject('', 'warehouseid', '1');
			selectType('', 'intype', 'rkdj');
		}
	</script>
	
  </head>
  
  <body onload="javascript:OnLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：入库管理 -&gt; 新建收货单 -&gt; 新增</div></td>
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
          <td width="15%" align="right" class="yx1"><span class="red">*</span>单据类型：</td>
          <td width="20%" class="yx"><select name="intype" id="intype"  style="width:100px;">
            <option>---请选择---</option>
          </select></td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>单据状态：</td>
          <td width="19%" class="xx1"><select name="instatus" id="instatus"  style="width:100px;">
            <option selected="selected">新建</option>
          </select></td>
        </tr>
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>货&nbsp;&nbsp;&nbsp;&nbsp;主：</td>
          <td class="yx">
          	<input type="text" name="customer_name"  readonly="readonly"  style="height:18px;width:100px;"/>
            <input type="hidden" name="customer_id" />
            <input name="moreBtn" type="button" class="button_select" value="…" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520');" /></td>
          <td align="right" class="yx1">预期到货时间从：</td>
          <td class="yx">
          		
          		<input name="arrivestarttime" type="text"   onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
          <td align="right" class="yx1">预期到货时间到：</td>
          <td class="xx1">
          	<input name="arriveendtime" type="text"   onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">承&nbsp;运&nbsp;人：</td>
          <td class="yx">
          	<input type="text" name="shippername" size="16" readonly="readonly"  style="height:18px;width:100px;"/>
            <input type="hidden" name="shipperid" />
            <input name="moreBtn" type="button" class="button_select" value="…" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=3','800','520','shipperid','shippername');" />
          </td>
          <td align="right" class="yx1">供&nbsp;应&nbsp;商：</td>
          <td class="yx">
          	 <input type="text" name="suppliername" size="16" readonly="readonly"  style="height:18px;width:100px;"/>
             <input type="hidden" name="supplierid" />
             <input name="moreBtn2" type="button" class="button_select" value="…" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=2','800','520','supplierid','suppliername');" />
          </td>
          <td align="right" class="yx1">收货月台：</td>
          <td class="xx1" >
            <input type="text" name="receiveloc"    style="height:18px;width:100px;"/>
            
         </td>
        </tr>
        <tr>
          <td align="right" class="yx1">海关备案号：</td>
          <td class="yx">
          	<input type="text" name="customsno"  style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">用户自定义1：</td>
          <td class="yx">
          		<input type="text" name="reserve1"  style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">用户自定义2：</td>
          <td class="xx1" ><input type="text" name="reserve2"  style="height:18px;width:100px;"/></td>
        </tr>
        <tr>
     		<td class="yx1"><div align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</div></td>
     		<td class="xx1" colspan="5"><div align="left"><textarea name="remark" cols="105" rows="3"></textarea></div></td>
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

<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptHeader" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	InboundReceiptHeader inBound = null; 
	if(request.getAttribute("invoice") != null){
		inBound = (InboundReceiptHeader)request.getAttribute("invoice"); 
	}
		String instockid = "";      //入库单据编号
        String intype = "";         //入库类型
		String warehouseid = "";    //仓库编号
		
		 String	ownerid  = "";    /*货主*/
	     String arrivestarttime = ""; /*预期到货时间(from)*/
	     String arriveendtime = "";   /*预期到货时间(to)*/
	     String shipperid = "";   /*承运人信息*/
	     String supplierid  = ""; /*供应商信息*/
	     String receiveloc = "";  /*收货月台*/
	     String customsno  = "";  /*海关备案号*/
	     String reserve1 = "";    /*预留字段1*/
	     String reserve2 = "";    /*预留字段2*/
	     String remark = "";      /*备注*/
	     String ownername = "";       //货主
	     String shippername = "";     //承运人信息
	     String suppliername  = "";   //供应商信息
		
		if(inBound != null)
		{
			instockid = inBound.getReinvoiceid();     //入库单据编号
	        intype = inBound.getIntype();         	//入库类型
	        warehouseid = inBound.getWarehouseid(); //仓库编号
		 	
		 	  ownerid  = inBound.getOwnerid();    /*货主*/
		      arrivestarttime = inBound.getArrivestarttime(); /*预期到货时间(from)*/
		      arriveendtime = inBound.getArriveendtime();   /*预期到货时间(to)*/
		      shipperid = inBound.getShipperid();   /*承运人信息*/
		      supplierid  = inBound.getSupplierid(); /*供应商信息*/
		      receiveloc = inBound.getReceiveloc();  /*收货月台*/
		      customsno  = inBound.getCustomsno();  /*海关备案号*/
		      reserve1 = inBound.getReserve1();    /*预留字段1*/
		      reserve2 = inBound.getReserve2();    /*预留字段2*/
		      remark = inBound.getRemark();      /*备注*/
		      ownername  = inBound.getOwnername();       //货主
		      shippername = inBound.getShippername();     //承运人信息
		      suppliername = inBound.getSuppliername();   //供应商信息
	    }
	
%>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
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
		//单据ID
		var invoiceid = document.getElementById("invoiceid").value;
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
		
		if(invoiceid == null || invoiceid.length < 1 ){
			alert("【收货单】不能为空！");
			UnLockButton();
			return;
		}
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
		
		var condition = "&invoiceid=" + invoiceid + "&intype=" + intype + "&warehouseid=" + warehouseid + "&customer_id=" + customer_id + "&arrivestarttime=" + arrivestarttime + "&arriveendtime="+arriveendtime + 
					"&shipperid=" + shipperid + "&supplierid=" + supplierid + "&receiveloc=" + receiveloc + "&customsno=" + customsno + "&reserve1=" + reserve1+ "&reserve2=" + reserve2+ "&remark=" + remark;
		
		//************************************************
		if(confirm("你确定保存？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&method=updateRicoExec";
			window.returnValue = strUrl + condition;
    		window.close();
				
		}else{
			UnLockButton();
		}
	}
		
		
		
		
		function OnLoad()
		{
			selectObject('<%=warehouseid%>', 'warehouseid', '1');
			selectType('<%=intype%>', 'intype', 'rkdj');
		}
	</script>
	
  </head>
  
  <body onload="javascript:OnLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：入库管理 -&gt; 新建收货单 -&gt; 修改</div></td>
   </tr>
  </table>
	<FORM>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="13%" align="right" class="yx1"><span class="red">*</span>单&nbsp;据&nbsp;号：</td>
          <td width="20%" class="yx">
          		<input type="text" name="invoiceid"  value="<%=instockid%>" readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="13%" align="right" class="yx1"  ><span class="red">*</span>仓&nbsp;&nbsp;&nbsp;&nbsp;库：</td>
          <td width="20%" class="yx" >
          	<select name="warehouseid" id="warehouseid" style="width:100px;">
            	<option>--请选择--</option>
            </select>
          
          </td>
          <td width="13%" align="right" class="yx1"><span class="red">*</span>单据类型：</td>
          <td width="21%" class="yx"><select name="intype" id="intype"  style="width:100px;">
            <option>---请选择---</option>
          </select></td>

        </tr>
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>货&nbsp;&nbsp;&nbsp;&nbsp;主：</td>
          <td class="yx">
          	<input type="text" name="customer_name" value="<%=ownername == null ? "" : ownername%>"  readonly="readonly"  style="height:18px;width:100px;"/>
            <input type="hidden" name="customer_id" value="<%=ownerid == null ? "" : ownerid%>"/>
            <input name="moreBtn" type="button" class="button_select" value="…" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520');" /></td>
          <td align="right" class="yx1">预期到货时间从：</td>
          <td class="yx">
          		
          		<input name="arrivestarttime" type="text" value="<%=arrivestarttime == null ? "" : arrivestarttime%>"   onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
          <td align="right" class="yx1">预期到货时间到：</td>
          <td class="xx1">
          	<input name="arriveendtime" type="text" value="<%=arriveendtime == null ? "" : arriveendtime%>"   onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">承&nbsp;运&nbsp;人：</td>
          <td class="yx">
          	<input type="text" name="shippername" value="<%=shippername == null ? "" : shippername%>" size="16" readonly="readonly"  style="height:18px;width:100px;"/>
            <input type="hidden" name="shipperid" value="<%=shipperid == null ? "" : shipperid%>" />
            <input name="moreBtn" type="button" class="button_select" value="…" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=3','800','520','shipperid','shippername');" />
          </td>
          <td align="right" class="yx1">供&nbsp;应&nbsp;商：</td>
          <td class="yx">
          	 <input type="text" name="suppliername" value="<%=suppliername == null ? "" : suppliername%>" size="16" readonly="readonly"  style="height:18px;width:100px;"/>
             <input type="hidden" name="supplierid" value="<%=supplierid == null ? "" : supplierid%>" />
             <input name="moreBtn2" type="button" class="button_select" value="…" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=2','800','520','supplierid','suppliername');" />
          </td>
          <td align="right" class="yx1">收货月台：</td>
          <td class="xx1" >
            <input type="text" name="receiveloc"  value="<%=receiveloc == null ? "" : receiveloc%>"   style="height:18px;width:100px;"/>
            
         </td> 
        </tr>

        <tr>
          <td align="right" class="yx1">海关备案号：</td>
          <td class="yx">
          	<input type="text" name="customsno"  value="<%=customsno == null ? "" : customsno%>" style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">用户自定义1：</td>
          <td class="yx">
          		<input type="text" name="reserve1" value="<%=reserve1 == null ? "" : reserve1%>"  style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">用户自定义2：</td>
          <td class="xx1" ><input type="text" name="reserve2" value="<%=reserve2 == null ? "" : reserve2%>"  style="height:18px;width:100px;"/></td>
        </tr>
        <tr>
     		<td class="yx1"><div align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</div></td>
     		<td class="xx1" colspan="5"><div align="left"><textarea name="remark" cols="105" rows="3"><%=remark == null ? "" : remark%></textarea></div></td>
   		</tr>
        <tr >
        	<TD colspan="6" height="20"></TD>
        </tr>
        <tr>
          <td height="27" colspan="6" align="center" ><input name="add" type="button" class="button_edit" id="add" value="&nbsp;&nbsp;保存" onClick="saveData();"/>
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;重置" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
	</FORM>
  </body>
</html>

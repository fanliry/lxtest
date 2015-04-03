
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String strTime =  StrTools.getCurrDateTime(8); 
	OutboundInvoiceHeader outBound = null; 
	OutboundInvoiceDetail outdetail = null; 
	if(request.getAttribute("invoicedetail") != null){
		outdetail = (OutboundInvoiceDetail)request.getAttribute("invoicedetail"); 
	}
	if(request.getAttribute("invoice") != null){
		outBound = (OutboundInvoiceHeader)request.getAttribute("invoice"); 
	}
	//保留小数2位
	NumberFormat nbf=NumberFormat.getInstance();      
	nbf.setMinimumFractionDigits(2);
	nbf.setMaximumFractionDigits(2);  
	
	String warehouseid = "";	//仓库ID
	String outstockdetailid = "";//出库单详细ID
    String outstockid = "";		//出库单据号
        
    String productid = "";		//物品
    String packid = "";			//包装
    String pkgunit = "";		//单位
    String productname = "";	//物品名称
    String packname = "";		//包装名称
    String pkgunitname = "";	//单位名称
    //String lotinfo = "";	   //批号
    
      
    double invoicenum = 0;		//开单数量
    double netweight = 0;		//开单净重
    double weight = 0;			//开单重量
    double volume = 0;			//开单体积
    
    double assignnum = 0;		//分配数量
    double assignnetweight = 0; //分配净重
    double assignweight = 0;    //分配重量
    double assignvolume = 0;    //分配体积  
    
    double noassignnum = 0; //未分配数量
      
      
    String customid = "";			//客户id  
    String ownerid = "";			//货主id 
    String customname = "";			//客户名称
    String ownername = "";			//货主名称
    
    String outtype = "";
    
    
         
	if(outdetail != null)
	{
	   outstockdetailid = outdetail.getOutstockdetailid();	//出库单详细ID
       outstockid = outdetail.getOutstockid();				//出库单据号
       
       productid = outdetail.getProductid();		//物品
       packid = outdetail.getPackid();				//包装
       pkgunit = outdetail.getPkgunit();			//单位
       productname = outdetail.getM_strProductName();//物品名称
       packname = outdetail.getM_strPackName();		//包装名称
       pkgunitname = outdetail.getM_strUnitName();	//单位名称 
       //lotinfo = outdetail.getLotinfo(); //批号
       strTime = outdetail.getPrintdate(); //生产日期
       
       invoicenum = outdetail.getInvoicenum();		//开单数量
       netweight = outdetail.getNetweight();		//净重
       weight = outdetail.getWeight();				//重量
       volume = outdetail.getVolume();				//体积
       
       assignnum = outdetail.getAssignnum();				 //分配数量
       assignnetweight = outdetail.getAssignnetweight();     //分配净重
       assignweight = outdetail.getAssignweight();           //分配重量
       assignvolume = outdetail.getAssignvolume();           //分配体积
       noassignnum = invoicenum-assignnum;
       
	}
	if(outBound != null){
		warehouseid = outBound.getWarehouseid();//仓库ID
		customid = outBound.getCustomerid();	//客户id  
        ownerid = outBound.getOwnerid();		//货主id
        customname = outBound.getCustomername();//客户名称
        ownername = outBound.getOwnername();	//货主名称
        outtype= outBound.getOuttype();
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
	<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendartime.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lotsearch.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
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
		
		document.getElementById("button_search").disabled = true;
		//document.getElementById("button_edit").disabled = true;
		document.getElementById("button_delete").disabled = true;
	}
	/*释放按钮*/
	function UnLockButton(){
		
		document.getElementById("button_search").disabled = false;
		//document.getElementById("button_edit").disabled = false;
		document.getElementById("button_delete").disabled = false;
	}
 	//手动分配的查询数据
	function queryData()
	{
		inum.innerHTML = "0";
		//单据ID
		var invoiceid = document.getElementById("invoiceid").value;
		//出库单详细ID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		//客户（收货人）
		var customerid = document.getElementById("customer_id").value;
		//货主
		var ownerid = "";
		//仓库ID
		var warehouseid = document.getElementById("warehouseid").value;
		//品名ID
		var productid = document.getElementById("product_id").value;	
		//库区ID
		var whAreaId = document.getElementById("whAreaId").value;
		
		//巷道ID
		var alleyid = document.getElementById("cargoAlleyId").value;
		//托盘条码
		var traycode = document.getElementById("traycode").value;
		
		//单位
		var unit = '<%=pkgunit%>';
		
		if(whAreaId == null || whAreaId.length < 1){
			alert("【库区】不能为空！");
			return;
		}
		
		var condition = "&invoiceid=" + invoiceid + "&invoicedetailid=" + invoicedetailid + "&customerid=" + customerid + "&ownerid=" + ownerid + "&warehouseid=" + warehouseid + "&productid=" + productid +"&whAreaId=" + whAreaId + "&alleyid=" + alleyid + "&traycode=" + traycode+ "&unit=" + unit;
		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=allocationAction&flag=1";	
		list.formx1.action = strUrl + condition;
		list.document.formx1.submit();	
	}
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	function saveData()
	{
		LockButton();
		//单据ID
		var invoiceid = document.getElementById("invoiceid").value;
		//单据详细ID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		if(invoiceid == null || invoiceid.length < 1){
			alert("【单据】不能为空！");
			UnLockButton();
			return;
		}
		if(invoicedetailid == null || invoicedetailid.length < 1){
			alert("【单据详细】不能为空！");
			UnLockButton();
			return;
		}
		var result = "";
		var check_ids = list.document.getElementsByName("check_id");
		var fpnums = list.document.getElementsByName("fpnum");
		var nums = list.document.getElementsByName("num");
		var noz = "<%=noassignnum%>";
		var floor = document.getElementById("floor").value;
		var tsjh = document.getElementById("tsjh").value;
		var outtype = document.getElementById("outtype").value;
		var temp = 0;
		if(floor == null || floor.length < 1){
			alert("【楼层】不能为空！");
			UnLockButton();
			return;
		}
		if(outtype == 4){ //业务领用要求输入
		  	if(!document.getElementById("tsjh").disabled && (tsjh == null || tsjh.length < 1))
			{
				alert("请输入提升机号");
				return false;
			}
		}
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				temp = temp + parseFloat(fpnums[i].value);
			        if(fpnums[i].value == null ||  !IsFloat(fpnums[i].value))
					{
						alert("第"+(i+1)+"行,数量不能为空或只能为数字！");
						UnLockButton();
						return false;
					}else if(parseFloat(fpnums[i].value)<0)
					{
						alert("第"+(i+1)+"行,数量不能小于1！");
						UnLockButton();
						return false;
					}
					else if(parseFloat(fpnums[i].value) > parseFloat(nums[i].value+1))
					{
						alert("第"+(i+1)+"行,数量不能大于"+nums[i].value+"！");
						UnLockButton();
						return false;
					}
					else if(temp > parseFloat(noz))
					{
						alert("超出"+noz+"！");
						UnLockButton();
						return false;
					}
			    var va = check_ids[i].value+","+fpnums[i].value+"]";
				result = result + va+ "|";	
			}
		}

		var condition = "&outboundid="+ invoiceid + "&detaiid=" + invoicedetailid + "&result=" + result + "&floor=" + floor + "&tsjh=" + tsjh;
		//************************************************
		if(confirm("你确定保存分配的记录？"))
		{
			b.innerHTML = parseFloat(document.getElementById("b").innerText) + temp;        //更新已分配数量
			synum.innerHTML = parseFloat(document.getElementById("synum").innerText) - temp;    //更新未分配数量
			
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=allocationAction&method=allotRicoExec";
			list.formx1.action = strUrl + condition;
			
			list.document.formx1.submit();	
			UnLockButton();	
			//queryData();
			
		}else{
			UnLockButton();
		}
	}
	//自动分配
	function updateData()
	{
		LockButton();
		//单据ID
		var invoiceid = document.getElementById("invoiceid").value;
		//单据详细ID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		
		if(invoiceid == null || invoiceid.length < 1){
			alert("【单据】不能为空！");
			UnLockButton();
			return;
		}
		
		if(invoicedetailid == null || invoicedetailid.length < 1){
			alert("【单据详细】不能为空！");
			UnLockButton();
			return;
		}
		
		var result = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				result = result + check_ids[i].value + "|";		
			}
		}
		if(result.length > 0)
		{
			result = result.substring(0, result.length-1);	
		}
		var condition = "&outboundid="+ invoiceid + "&detaiid=" + invoicedetailid + "&result=" + result ;
		
		//************************************************
		if(confirm("你确定保存分配的记录？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=allocationAction&method=outoAllotRicoExec";
			list.location.href = strUrl + condition;

				
		}else{
			UnLockButton();
		}
	}
	function viewTrans(packid,eunit,lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
	{
		selectinoutUnit(eunit, 'eunit', packid, '1');	//单位		
		setLotValueReadonly(lotid, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);//批次
					
	}
	//根据库区获得巷道的列表
	function getAlleyList(){
	
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "cargoAlleyId", whAreaId);
	}
	function getInput(){
		var floor = document.getElementById("floor").value;
		document.getElementById("tsjh").value = "";
		if(floor == "2")
		{
			document.getElementById("tsjh").disabled = false;
		}else
		{
			document.getElementById("tsjh").disabled = true;
		}
	}
  	function OnLoad()
	{	
		UnLockButton();
		selectObject('<%=warehouseid%>', 'warehouseid', '1');  //仓库
		selectAreaList('001001', 'whAreaId', '<%=warehouseid%>', "1");// 立体库区
		selectType('<%=pkgunit%>', "eunit", "punit");
		//库区
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
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
  <body onload="javascript:OnLoad();">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
       
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><input type="hidden" id="outtype" value="<%=outtype%>"><div class="font_001F56_bold_12">当前位置：出库管理 -&gt; 新建出库单 -&gt; 分配</div></td>
   </tr>
  </table>
	<FORM id="form1">
  
	  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
			<tr>
				<td width="10%" align="right" class="yx1">仓 &nbsp;&nbsp;&nbsp;&nbsp;库：</td>
				<td class="yx">
				<select id="warehouseid" style="width:100px;" class="input_readonly">
						<option value="">--请选择--</option>
				</select>
				</td>
				<td align="right" class="yx1">出库单号：</td>
				<td class="yx"><input type="text" name="invoiceid" value="<%=outstockid%>" class="input_readonly" readonly style="height:18px;width:100px;" /> <input type="hidden" name="invoicedetailid" value="<%=outstockdetailid%>" /></td>
				<td align="right" class="yx1">客&nbsp;&nbsp;户：</td>
				<td class="yx"><input type="text" name="customer_name" value="<%=customname == null ? "" : customname%>" readonly="readonly" class="input_readonly" style="height:18px;width:100px;" /> <input type="hidden" name="customer_id" value="<%=customid%>" /></td>
				<td align="right" class="yx1">单 &nbsp;&nbsp;&nbsp;&nbsp;位：</td>
				<td class="yx"><select name="eunit" id="eunit" style="width:100px;" class="input_readonly" disabled="disabled">
						<option>--请选择--</option>
				</select></td>
			</tr>
			<tr>
				<td width="10%" align="right" class="yx1">品 &nbsp;&nbsp;&nbsp;&nbsp;名：</td>
				<td class="yx"><input type="text" name="product_name" value="<%=productname%>" class="input_readonly" readonly style="height:18px;width:100px;" /> <input type="hidden" name="product_id" value="<%=productid%>" class="input_readonly" style="height:18px;width:100px;" /></td>

				<td align="right" class="yx1"><span class="red">*</span>库&nbsp;&nbsp;&nbsp;&nbsp;区：</td>
				<td class="yx"><select id="whAreaId" style="width:100px;" onChange="getAlleyList()">
						<option value="">--请选择--</option>
				</select></td>
				<td align="right" class="yx1">巷 &nbsp;&nbsp;&nbsp;&nbsp;道：</td>
				<td class="yx"><select id="cargoAlleyId" style="width:100px;"><option value="">--请选择--</option></select></td>
				<td align="right" class="yx1">托盘条码：</td>
				<td class="yx" colspan="5"><input type="text" name="traycode" size="16" value="" style="height:18px;width:100px;" /></td>
			</tr>
			<tr>
				<td width="10%" align="right" class="yx1">作业楼层：</td>
				<td class="yx"><select id="floor" onchange="getInput()">
		     		<option value="">---请选择---</option>
		     		<option value="1" selected>1楼</option>
		     		<option value="2">2楼</option>
		     	</select></td>
				<td align="right" class="yx1">车间：</td>
				<td class="yx" colspan="5"><select id="tsjh" disabled>
		     		<option value="">---请选择---</option>
		     		<option value="1">一车间</option>
		     		<option value="2">二车间</option>
		     		<option value="3">三车间</option>
		     		<option value="4">四车间</option>
		     		<option value="5">五车间</option>
		     	</select></td>
			</tr>
       </table>
       
       <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
        <tr height="25">
           <td valign="bottom">
     			<span style="color: blue;font-size:12px;font-weight:bold; ">开单数量:</span><span id="a" style="color: blue;font-size:12px;font-weight:bold; "><%=nbf.format(invoicenum)%> </span>
     			<span style="color: green;font-size:12px;font-weight:bold; ">已分配数量：</span><span id="b" style="color:green;font-size:12px;font-weight:bold;  "><%=nbf.format(assignnum)%> </span>
     			<span style="color: #A6A600;font-size:12px;font-weight:bold; ">未分配数量:</span><span id="synum" style="color: #A6A600;font-size:12px;font-weight:bold;  "><%=nbf.format(noassignnum)%></span>
     			<span style="color: red;font-size:12px;font-weight:bold; ">当前勾选:</span><span id="inum" style="color: red;font-size:12px;font-weight:bold; ">0</span>
          </td>
			<td align="right" >
            <input name="button_search" type="button" class="button_search" id="button_search" value="&nbsp;&nbsp;查询" onClick="queryData();" />
            <input name="button_delete" type="button" class="button_add" id="button_delete" value="&nbsp;&nbsp;确认" onclick="saveData();" />
            <!-- <input name="button_edit" type="button" class="button_add" id="button_edit" value="&nbsp;&nbsp;自动分配" onclick="updateData();" /> -->
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
      </FORM>

</td>
   </tr>
   <tr>
   	 <td height="10">
   	 </td>
   </tr>
   <tr>
     <td height="100%"> 
       
  <!-- ======== 库存列表开始 ======== -->
 <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/outbound/assgin/outbound_assgin_list.jsp" scrolling="auto" frameborder="0" width="100%" height="100%">  
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== 库存列表结束 ======== -->
  
     </td>
   </tr>
 </table>



  </body>
</html>

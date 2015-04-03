<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	//出库单据ID
	String invoiceid = request.getParameter("invoiceid");
	//客户ID
	String customerid = request.getParameter("customerid");
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
	
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
		
		document.getElementById("button_add").disabled = true;
		document.getElementById("button_edit").disabled = true;
		document.getElementById("button_delete").disabled = true;
	}
	/*释放按钮*/
	function UnLockButton(){
		
		document.getElementById("button_add").disabled = false;
		document.getElementById("button_edit").disabled = false;
		document.getElementById("button_delete").disabled = false;
	}
 	//保存出库单详细数据
	function saveData()
	{
		//出库单据ID
		var invoiceid = document.getElementById("invoiceid").value;
		//品名ID
		var productid = document.getElementById("productid").value;
		//产品编码
		var productCode = document.getElementById("productCode").value;
		//单位
		var eunit = document.getElementById("eunit").value;
		//出库数量
		var invoicenum = document.getElementById("invoicenum").value;
		//规格
		var spec = document.getElementById("spec").value;
		
		//逻辑库区
		var Virtualwhid = document.getElementById("Virtualwhid").value;
		//生产日期
		var indate = document.getElementById("indate").value;
		//批号
		var lotinfo = document.getElementById("lotinfo").value;
		
		
		if(invoiceid == null || invoiceid.length < 1){
			alert("【出库单】不能为空！");
			UnLockButton();
			return;
		}
		
		if(productid == null || productid.length < 1){
			alert("【品名】不能为空！");
			UnLockButton();
			return;
		}
		if(productCode == null || productCode.length < 1){
			alert("【产品编码】不能为空！");
			UnLockButton();
			return;
		}
		
		if(eunit == null || eunit.length < 1){
			alert("【单位】不能为空！");
			UnLockButton();
			return;
		}

		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("【数量】不能为空且只能为数字！");
			UnLockButton();
			return;
		}
		
		if(spec == null || spec.length < 1 ){
			alert("【规格】不能为空！");
			UnLockButton();
			return;
		}
		if(Virtualwhid == null || Virtualwhid.length < 1 ){
			alert("【逻辑库区】不能为空！");
			UnLockButton();
			return;
		}
		if(lotinfo == null || lotinfo.length < 1 ){
			alert("【批号】不能为空！");
			UnLockButton();
			return;
		}
		var condition = "&invoiceid="+ invoiceid + "&productid=" + productid +"&eunit=" + eunit + "&invoicenum="+invoicenum + 
					"&spec=" + spec +"&productcode=" + productCode+"&Virtualwhid=" + Virtualwhid+"&printdate=" + indate+"&lotinfo=" + lotinfo; 
		
		//************************************************
		if(confirm("你确定保存？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&method=addDetailRicoExec";
			window.returnValue = list.formx1.action = strUrl + condition;
			list.document.formx1.submit();
			//form1.reset();//重置		
		}else{
			UnLockButton();
		}
	}
	
	//修改出库单详细数据
	function updateData()
	{
		//出库单据详细ID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		//品名ID
		var productid = document.getElementById("productid").value;
		//单位
		var eunit = document.getElementById("eunit").value;
		//数量
		var invoicenum = document.getElementById("invoicenum").value;
		//规格
		var spec = document.getElementById("spec").value;
		
		//净重
		//var netweight = document.getElementById("netweight").value;
		//体积
		//var volume = document.getElementById("volume").value;
		//批号
		//var lotinfo = document.getElementById("lotinfo").value;
		//产品编码
		var productCode = document.getElementById("productCode").value;
		
		if(invoicedetailid == null || invoicedetailid.length < 1){
			alert("【出库单详细】不能为空！");
			UnLockButton();
			return;
		}
		
		if(productid == null || productid.length < 1){
			alert("【品名】不能为空！");
			UnLockButton();
			return;
		}
		if(productCode == null || productCode.length < 1){
			alert("【产品编码】不能为空！");
			UnLockButton();
			return;
		}
		if(spec == null && spec.length < 1 ){
			alert("【规格】不能为空！");
			UnLockButton();
			return;
		}
		if(eunit == null || eunit.length < 1){
			alert("【单位】不能为空！");
			UnLockButton();
			return;
		}

		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("【数量】不能为空且只能为数字！");
			UnLockButton();
			return;
		}
		
		
		
		var condition = "&detailid="+ invoicedetailid + "&productid=" + productid + "&eunit=" + eunit + "&invoicenum="+invoicenum + 
					"&spec=" + spec +"&productcode=" + productCode;
		
		//************************************************
		if(confirm("你确定保存？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&method=updateDetailRicoExec"; 
			list.formx1.action = strUrl + condition;
			list.document.formx1.submit(); 
			form1.reset();//重置
		}
	}
	//批次重置
	function lotreset()
	{
		document.getElementById("lotatt1").value = ""; 	//批次属性1
		document.getElementById("lotatt2").value = ""; 	//批次属性2
		document.getElementById("lotatt3").value = ""; 	//批次属性3
		document.getElementById("lotatt4").value = ""; 	//批次属性4
		document.getElementById("lotatt5").value = ""; 	//批次属性5
		document.getElementById("lotatt6").value = ""; 	//批次属性6
		document.getElementById("lotatt7").value = ""; 	//批次属性7
		document.getElementById("lotatt8").value = ""; 	//批次属性8
		document.getElementById("lotatt9").value = ""; 	//批次属性9
		document.getElementById("lotatt10").value = ""; //批次属性10
		document.getElementById("lotatt11").value = ""; //批次属性11
		document.getElementById("lotatt12").value = ""; //批次属性12
	}
	
	//删除单据详细
	function deleteData()
	{
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("请选择要作废的记录！");
			return;
		}
		//单据ID
		var invoiceid = document.getElementById("invoiceid").value;
		if(invoiceid == null || invoiceid.length < 1){
			alert("【单据】不能为空！");
			return;
		}
		//************************************************
		if(confirm("你确定删除所选记录？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&method=deleteRicoExec&flag=2";
			list.location.href = strUrl + "&invoiceid=" + invoiceid + "&detailids=" + ids;
			
			form1.reset();//重置
				
		}
	}
	function Select()
	{
		var result;
		result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp",'','dialogWidth=800px;dialogHeight=520px');
		
	    if(result != null && result.length > 0)
        {
        	result = result.split(",");
        	for(var i = 0; i < result.length; i++)
        	{
        	    
        		var cs = result[i].split("|");
			    	              //产品id        产品名           单位     
			    var checkvaleid = cs[0] + "," + cs[1] + "," + cs[6];	
			    document.getElementById("product_name").value=cs[1];
			    document.getElementById("productid").value =cs[0];
			    document.getElementById("productCode").value =cs[7];
			    document.getElementById("spec").value = cs[8];
			    
			    selectType(cs[6], "eunit", "punit");
        	}
        }
	}
	//页面登录
	function OnLoad(){
		selectAreaList("", "Virtualwhid", "", "3");
	}
 </script>
</head>
  <body onLoad="OnLoad()">
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：出库管理 -&gt; 新建出库单 -&gt; 明细</div></td>
   </tr>
  </table>
	<FORM id="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="13%" align="right" class="yx1"  ><span class="red">*</span>品&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="product_name" class="input_readonly"    style="height:18px;width:100px;"/>
          	 <input type="hidden" name="productid" value=""  style="height:18px;width:100px;"/>
          	 <input name="moreBtn3" type="button" class="button_select" value="…" onclick="Select()" />
          </td>
          <!-- <td width="15%" align="right" class="yx1"><span class="red">*</span>包&nbsp;&nbsp;&nbsp;&nbsp;装：</td>
          <td width="20%" class="yx">
              <input type="hidden" name="packid"    style="height:18px;width:100px;"/>
	          <input type="text" name="packname"  class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td> -->
          <td align="right" class="yx1">产品编码：</td>
          <td class="yx">
          		<input type="text" name="productCode" class="input_readonly" size="16" style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>出库单号：</td>
          <td width="19%" class="xx1">
	          <input type="text" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">单&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
          <td class="yx">
          		<select name="eunit" id="eunit" style="width:100px;" disabled>
	            <option>--请选择--</option>
	          </select>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>出库数量：</td>
          <td class="yx">
          	<input type="text" name="invoicenum" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">规&nbsp;&nbsp;&nbsp;&nbsp;格：</td>
          <td class="xx1" >
          	<input type="text" name="spec" size="16" class="input_readonly" style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td class="yx1" align="right"><span class="red">*</span>逻辑库区：</td>
     	  <td class="yx">
     	   <select id="Virtualwhid" style="width:117px;"><option value=""></option></select>
     	  </td>
          <td align="right" class="yx1">入库日期：</td>
          <td class="yx">
          	<input id="indate" type="text" onfocus="calendar();" class="Wdate" style="width:100px;">
          </td>
          <td align="right" class="yx1"><span class="red">*</span>进场编号：</td>
          <td class="xx1">
          	<input type="text" name="lotinfo" size="16"   style="height:18px;width:100px;"/>
          </td>
        </tr>
		<tr>
        	 <TD colspan="6" height="10">
        		<input type="hidden" name="invoicedetailid" />
        	</TD>
        </tr>
        <tr>
          <td height="27" colspan="6" align="center" ><input style="width: 80px;" name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;增加明细" onClick="saveData();" />
            <!--<input style="width: 80px;" name="button_edit" type="button" class="button_edit" id="button_edit" value="&nbsp;&nbsp;修改明细" onclick="updateData();" />  -->
            <input style="width: 80px;" name="button_delete" type="button" class="button_delete" id="button_delete" value="&nbsp;&nbsp;删除明细" onclick="deleteData();" />
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;重置" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
      </FORM>
 <table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>


 <!-- ======== 明细列表开始 ======== -->
 <table width="98%" height="420" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=4&invoiceid=<%=invoiceid%>" scrolling="auto" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== 明细列表结束 ======== -->

  </body>
</html>

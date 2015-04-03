
<%@ page contentType="text/html; charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	//收货单据ID
	String invoiceid = request.getParameter("invoiceid");
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
 	//保存数据
	function saveData()
	{
		LockButton();
		//单据ID
		var invoiceid = document.getElementById("invoiceid").value;
		//品名ID
		var productid = document.getElementById("productid").value;
		//包装
		var packageid = document.getElementById("packid").value;
		//单位
		var eunit = document.getElementById("eunit").value;
		//到货数量
		var invoicenum = document.getElementById("invoicenum").value;
		//毛重
		var weight = document.getElementById("weight").value;
		//净重
		var netweight = document.getElementById("netweight").value;
		//体积
		var volume = document.getElementById("volume").value;
		//单价
		var price = document.getElementById("price").value;
		//收货库区
		var reclocation  = document.getElementById("reclocation").value;
		//产品状态
		var skustatus  = document.getElementById("skustatus").value;
		//状态描述
		var skustatus_descr  = document.getElementById("skustatus_descr").value;
		//批次类型ID
		var lotid  = document.getElementById("lotid").value;
		
		var lotatt1  = document.getElementById("lotatt1").value; 	//批次属性1
		var lotatt2  = document.getElementById("lotatt2").value; 	//批次属性2
		var lotatt3  = document.getElementById("lotatt3").value; 	//批次属性3
		var lotatt4  = document.getElementById("lotatt4").value; 	//批次属性4
		var lotatt5  = document.getElementById("lotatt5").value; 	//批次属性5
		var lotatt6  = document.getElementById("lotatt6").value; 	//批次属性6
		var lotatt7  = document.getElementById("lotatt7").value; 	//批次属性7
		var lotatt8  = document.getElementById("lotatt8").value; 	//批次属性8
		var lotatt9  = document.getElementById("lotatt9").value; 	//批次属性9
		var lotatt10  = document.getElementById("lotatt10").value; 	//批次属性10
		var lotatt11  = document.getElementById("lotatt11").value; 	//批次属性11
		var lotatt12  = document.getElementById("lotatt12").value; 	//批次属性12		
		
		if(invoiceid == null || invoiceid.length < 1){
			alert("【单据】不能为空！");
			UnLockButton();
			return;
		}
		
		if(productid == null || productid.length < 1){
			alert("【品名】不能为空！");
			UnLockButton();
			return;
		}
		
		if(packageid == null || packageid.length < 1){
			alert("【包装】不能为空！");
			UnLockButton();
			return;
		}
		
		if(eunit == null || eunit.length < 1){
			alert("【单位】不能为空！");
			UnLockButton();
			return;
		}

		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("【到货数量】不能为空且只能为数字！");
			UnLockButton();
			return;
		}
		
		if(weight != null && weight.length > 0 && !IsFloat(weight)){
			alert("【毛重】只能为数字！");
			UnLockButton();
			return;
		}else if(weight == null || weight.length < 1){
			weight = "0";
		}
		
		if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
			alert("【净重】只能为数字！");
			UnLockButton();
			return;
		}else if(netweight == null || netweight.length < 1){
			netweight = "0";
		}	
		
		
		if(volume != null && volume.length > 0 && !IsFloat(volume)){
			alert("【体积】只能为数字！");
			UnLockButton();
			return;
		}else if(volume == null || volume.length < 1)
		{
			volume = "0";
		} 		
		

		if(price != null && price.length > 0 && !IsFloat(price)){
			alert("【价格】只能为数字！");
			UnLockButton();
			return;
		}else if(price == null || price.length < 1)
		{
			price = "0";
		}
		

		
		//批次属性值验证
		if(checkLotatt()==false){
			UnLockButton();
			return;
		}
		
		var condition = "&invoiceid="+ invoiceid + "&productid=" + productid + "&packid=" + packageid + "&eunit=" + eunit + "&invoicenum="+invoicenum + 
					"&weight=" + weight + "&netweight=" + netweight + "&volume=" + volume + "&price=" + price + "&reclocation=" + reclocation +
					"&skustatus=" + skustatus + "&skustatus_descr=" + skustatus_descr + "&lotid=" + lotid;// + 
					//"&lotatt1=" + lotatt1 + "&lotatt2=" + lotatt2 + "&lotatt3=" + lotatt3 + "&lotatt4=" + lotatt4 + 
					//"&lotatt5=" + lotatt5 + "&lotatt6=" + lotatt6 + "&lotatt7=" + lotatt7 + "&lotatt8=" + lotatt8 +
					//"&lotatt9=" + lotatt9 + "&lotatt10=" + lotatt10 + "&lotatt11=" + lotatt11 + "&lotatt12=" + lotatt12;
		
		//************************************************
		if(confirm("你确定保存？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&method=addDetailRicoExec";
			//有中文
			var msg = "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
					+ "<input type=hidden name='lotatt2' value='"+lotatt2+"'>"
					+ "<input type=hidden name='lotatt3' value='"+lotatt3+"'>"
					+ "<input type=hidden name='lotatt4' value='"+lotatt4+"'>"
					+ "<input type=hidden name='lotatt5' value='"+lotatt5+"'>"
					+ "<input type=hidden name='lotatt6' value='"+lotatt6+"'>"
					+ "<input type=hidden name='lotatt7' value='"+lotatt7+"'>"
					+ "<input type=hidden name='lotatt8' value='"+lotatt8+"'>"
					+ "<input type=hidden name='lotatt9' value='"+lotatt9+"'>"
					+ "<input type=hidden name='lotatt10' value='"+lotatt10+"'>"
					+ "<input type=hidden name='lotatt11' value='"+lotatt11+"'>"
					+ "<input type=hidden name='lotatt12' value='"+lotatt12+"'>";
			list.formx1.innerHTML = msg;
			list.formx1.action = strUrl + condition;
			list.document.formx1.submit();
			
			form1.reset();//重置		
			lotreset();	  //重置
		}else{
			UnLockButton();
		}
	}
	
	//修改数据
	function updateData()
	{
		LockButton();
		//单据详细ID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		//品名ID
		var productid = document.getElementById("productid").value;
		//包装
		var packageid = document.getElementById("packid").value;
		//单位
		var eunit = document.getElementById("eunit").value;
		//到货数量
		var invoicenum = document.getElementById("invoicenum").value;
		//毛重
		var weight = document.getElementById("weight").value;
		//净重
		var netweight = document.getElementById("netweight").value;
		//体积
		var volume = document.getElementById("volume").value;
		//单价
		var price = document.getElementById("price").value;
		
		//收货库区
		var reclocation  = document.getElementById("reclocation").value;
		//产品状态
		var skustatus  = document.getElementById("skustatus").value;
		//状态描述
		var skustatus_descr  = document.getElementById("skustatus_descr").value;
		//批次类型ID
		var lotid  = document.getElementById("lotid").value;
		
		var lotatt1  = document.getElementById("lotatt1").value; 	//批次属性1
		var lotatt2  = document.getElementById("lotatt2").value; 	//批次属性2
		var lotatt3  = document.getElementById("lotatt3").value; 	//批次属性3
		var lotatt4  = document.getElementById("lotatt4").value; 	//批次属性4
		var lotatt5  = document.getElementById("lotatt5").value; 	//批次属性5
		var lotatt6  = document.getElementById("lotatt6").value; 	//批次属性6
		var lotatt7  = document.getElementById("lotatt7").value; 	//批次属性7
		var lotatt8  = document.getElementById("lotatt8").value; 	//批次属性8
		var lotatt9  = document.getElementById("lotatt9").value; 	//批次属性9
		var lotatt10  = document.getElementById("lotatt10").value; 	//批次属性10
		var lotatt11  = document.getElementById("lotatt11").value; 	//批次属性11
		var lotatt12  = document.getElementById("lotatt12").value; 	//批次属性12
		
		if(invoicedetailid == null || invoicedetailid.length < 1){
			alert("【单据详细】不能为空！");
			UnLockButton();
			return;
		}
		
		if(productid == null || productid.length < 1){
			alert("【品名】不能为空！");
			UnLockButton();
			return;
		}
		
		if(packageid == null || packageid.length < 1){
			alert("【包装】不能为空！");
			UnLockButton();
			return;
		}
		
		if(eunit == null || eunit.length < 1){
			alert("【单位】不能为空！");
			UnLockButton();
			return;
		}

		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("【到货数量】不能为空且只能为数字！");
			UnLockButton();
			return;
		}
		
		if(weight != null && weight.length > 0 && !IsFloat(weight)){
			alert("【毛重】只能为数字！");
			UnLockButton();
			return;
		}else if(weight == null || weight.length < 1){
			weight = "0";
		}
		if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
			alert("【净重】只能为数字！");
			UnLockButton();
			return;
		}else if(netweight == null || netweight.length < 1){
			netweight = "0";
		}
		if(volume != null && volume.length > 0 && !IsFloat(volume)){
			alert("【体积】只能为数字！");
			UnLockButton();
			return;
		}else if(volume == null || volume.length < 1){
			volume = "0";
		}
		if(price != null && price.length > 0 && !IsFloat(price)){
			alert("【价格】只能为数字！");
			UnLockButton();
			return;
		}else if(price == null || price.length < 1){
			price = "0";
		}
		
		//批次属性值验证
		if(checkLotatt()==false){
			UnLockButton();
			return;
		}

		var condition = "&detailid="+ invoicedetailid + "&productid=" + productid + "&packid=" + packageid + "&eunit=" + eunit + "&invoicenum="+invoicenum + 
					"&weight=" + weight + "&netweight=" + netweight + "&volume=" + volume + "&price=" + price + "&reclocation=" + reclocation +
					"&skustatus=" + skustatus + "&skustatus_descr=" + skustatus_descr + "&lotid=" + lotid ;//+ 
					//"&lotatt1=" + lotatt1 + "&lotatt2=" + lotatt2 + "&lotatt3=" + lotatt3 + "&lotatt4=" + lotatt4 + 
					//"&lotatt5=" + lotatt5 + "&lotatt6=" + lotatt6 + "&lotatt7=" + lotatt7 + "&lotatt8=" + lotatt8 +
					//"&lotatt9=" + lotatt9 + "&lotatt10=" + lotatt10 + "&lotatt11=" + lotatt11 + "&lotatt12=" + lotatt12;//encodeURIComponent()
		
		
		//************************************************
		if(confirm("你确定保存？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&method=updateDetailRicoExec"; 
			
			//有中文
			var msg = "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
					+ "<input type=hidden name='lotatt2' value='"+lotatt2+"'>"
					+ "<input type=hidden name='lotatt3' value='"+lotatt3+"'>"
					+ "<input type=hidden name='lotatt4' value='"+lotatt4+"'>"
					+ "<input type=hidden name='lotatt5' value='"+lotatt5+"'>"
					+ "<input type=hidden name='lotatt6' value='"+lotatt6+"'>"
					+ "<input type=hidden name='lotatt7' value='"+lotatt7+"'>"
					+ "<input type=hidden name='lotatt8' value='"+lotatt8+"'>"
					+ "<input type=hidden name='lotatt9' value='"+lotatt9+"'>"
					+ "<input type=hidden name='lotatt10' value='"+lotatt10+"'>"
					+ "<input type=hidden name='lotatt11' value='"+lotatt11+"'>"
					+ "<input type=hidden name='lotatt12' value='"+lotatt12+"'>";
			list.formx1.innerHTML = msg;
			
			list.formx1.action = strUrl + condition;
			list.document.formx1.submit(); 
			form1.reset();//重置
			lotreset();	  //重置
				
		}else{
			UnLockButton();
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
		LockButton();
		
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
			UnLockButton();
			return;
		}
		
		//************************************************
		if(confirm("你确定删除所选记录？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&method=deleteRicoExec&flag=2";
			list.location.href = strUrl + "&invoiceid=" + invoiceid + "&detailids=" + ids;
			
			form1.reset();//重置
			lotreset();	  //重置
				
		}else{
			UnLockButton();
		}
	}
	
	function openProduct(url, width, height, productid, product_name) 
	{
		var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
		if(result != null && result.length > 1)
		{
			var tem = result.split("|");
			document.getElementById("productid").value = tem[0];
			document.getElementById("product_name").value = tem[1];
			
			document.getElementById("packid").value = tem[2];
			
			//显示单位
			
			
		}else
		{
			document.getElementById("package_id").value = "";
			document.getElementById("package_name").value = "";
		}	
	}
 
 
  	function OnLoad()
	{

		selectType('', 'skustatus', 'skustatus');//产品状态
	}
 </script>
</head>
  <body onload="javascript:OnLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：入库管理 -&gt; 新建收货单 -&gt; 明细</div></td>
   </tr>
  </table>
	<FORM id="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="13%" align="right" class="yx1"  ><span class="red">*</span>品&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="product_name" class="input_readonly"    style="height:18px;width:100px;"/>
          	 <input type="hidden" name="productid" value=""  style="height:18px;width:100px;"/>
          	 <input name="moreBtn3" type="button" class="button_select" value="…" onclick="openProductPacke('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520','productid','product_name', 'packid', 'packname', '1','eunit', 'lotid');" />
          </td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>包&nbsp;&nbsp;&nbsp;&nbsp;装：</td>
          <td width="20%" class="yx">
              <input type="hidden" name="packid"    style="height:18px;width:100px;"/>
	          <input type="text" name="packname"  class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>单&nbsp;据&nbsp;号：</td>
          <td width="19%" class="xx1">
	          <input type="text" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">单&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
          <td class="yx">
          		<select name="eunit" id="eunit" style="width:100px;" >
	            <option>--请选择--</option>
	          </select>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>到货数量：</td>
          <td class="yx">
          	<input type="text" name="invoicenum" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">毛&nbsp;&nbsp;&nbsp;&nbsp;重：</td>
          <td class="xx1">
          	<input type="text" name="weight" size="16"   style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">净&nbsp;&nbsp;&nbsp;&nbsp;重：</td>
          <td class="yx">
          	<input type="text" name="netweight" size="16"   style="height:18px;width:100px;"/>
            
          </td>
          <td align="right" class="yx1">体&nbsp;&nbsp;&nbsp;&nbsp;积：</td>
          <td class="yx">
          	<input type="text" name="volume" size="16"    style="height:18px;width:100px;"/>  
           </td>
          <td align="right" class="yx1">单&nbsp;&nbsp;&nbsp;&nbsp;价：</td>
          <td class="xx1" >
          	<input type="text" name="price" size="16"    style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">收货库区：</td>
          <td class="yx"><input type="text" name="reclocation"   style="height:18px;width:100px;"/></td>
          <td align="right" class="yx1">产品状态：</td>
          <td class="yx">
          		<select name="skustatus" style="width:100px;" >
	            <option >--请选择--</option>
	          </select>
          </td>
          <td align="right" class="yx1">状态描述：</td>
          <td class="xx1" ><input type="text" name="skustatus_descr"   style="height:18px;width:100px;"/></td>
        </tr>
        
        <tr>
     		<td height="5" colspan="6"><input type="hidden" name="lotid"/></td>
   		</tr>
        
        <tr>
          <td align="right" class="yx1_top"><div id="lotatt001" align="right">批次属性1：</div></td>
          <td class="yx_top"><div id="lotvalue001" align="left">
          	<input type="text" name="lotatt1" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
          <td align="right" class="yx1_top"><div id="lotatt002" align="right">批次属性2：</div></td>
          <td class="yx_top"><div id="lotvalue002" align="left">
          	<input type="text" name="lotatt2" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
          <td align="right" class="yx1_top"><div id="lotatt003" align="right">批次属性3：</div></td>
          <td class="xx1_top" ><div id="lotvalue003" align="left">
          	<input type="text" name="lotatt3" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="yx1"><div id="lotatt004" align="right">批次属性4：</div></td>
          <td class="yx"><div id="lotvalue004" align="left">
          	<input type="text" name="lotatt4" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
          <td align="right" class="yx1"><div id="lotatt005" align="right">批次属性5：</div></td>
          <td class="yx"><div id="lotvalue005" align="left">
          	<input type="text" name="lotatt5" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
          <td align="right" class="yx1"><div id="lotatt006" align="right">批次属性6：</div></td>
          <td class="xx1" ><div id="lotvalue006" align="left">
          	<input type="text" name="lotatt6" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="yx1"><div id="lotatt007" align="right">批次属性7：</div></td>
          <td class="yx"><div id="lotvalue007" align="left">
          	<input type="text" name="lotatt7" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
          <td align="right" class="yx1"><div id="lotatt008" align="right">批次属性8：</div></td>
          <td class="yx"><div id="lotvalue008" align="left">
          	<input type="text" name="lotatt8" size="16"    style="height:18px;width:100px;"/></div> 
           </td>
          <td align="right" class="yx1"><div id="lotatt009" align="right">批次属性9：</div></td>
          <td class="xx1" ><div id="lotvalue009" align="left">
          	<input type="text" name="lotatt9" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="yx1"><div id="lotatt010" align="right">批次属性10：</div></td>
          <td class="yx"><div id="lotvalue010" align="left">
          	<input type="text" name="lotatt10" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
          <td align="right" class="yx1"><div id="lotatt011" align="right">批次属性11：</div></td>
          <td class="yx"><div id="lotvalue011" align="left">
          	<input type="text" name="lotatt11" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
          <td align="right" class="yx1"><div id="lotatt012" align="right">批次属性12：</div></td>
          <td class="xx1" ><div id="lotvalue012" align="left">
          	<input type="text" name="lotatt12" size="16" style="height:18px;width:100px;"/></div>
          </td>
        </tr>
 
		<tr>
        	<TD colspan="6" height="10">
        		<input type="hidden" name="invoicedetailid" />
        	</TD>
        </tr>
        <tr>
          <td height="27" colspan="6" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;增加明细" onClick="saveData();" />
            <input name="button_edit" type="button" class="button_edit" id="button_edit" value="&nbsp;&nbsp;修改明细" onclick="updateData();" />
            <input name="button_delete" type="button" class="button_delete" id="button_delete" value="&nbsp;&nbsp;删除明细" onclick="deleteData();" />
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
 <table width="98%" height="270" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&flag=6&invoiceid=<%=invoiceid%>" scrolling="auto" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== 明细列表结束 ======== -->

  </body>
</html>

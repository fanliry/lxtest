
<%@ page contentType="text/html; charset=GBK"%>
<%
	
	//单据ID
	String invoiceid = request.getParameter("invoiceid");
	//仓库ID
	String warehouseid = request.getParameter("warehouseid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
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
		document.getElementById("button_save").disabled = true;	
	}
	/*释放按钮*/
	function UnLockButton(){
		
		document.getElementById("button_save").disabled = false;
	}
	//增加数据
	function addData()
	{
		var transid = myIframe.document.getElementById("transid").value;//收货记录号
		if(transid == null || transid.length < 1){
			alert("【收货记录】不能为空！");
			return;
		}else{
			var proudctname = myIframe.document.getElementById("product_name").value;//产品
			var packname = myIframe.document.getElementById("packname").value;//包装
			var unitname = myIframe.document.getElementById("eunit").value;//单位

			var knum = myIframe.document.getElementById("num").value;	 			//可上架数量
			var knetweight = myIframe.document.getElementById("netweight").value;	//可上架净重
			var kweight = myIframe.document.getElementById("weight").value;	 		//可上架毛重
			
			var num = myIframe.document.getElementById("renum").value;				//上架数量
			var netweight = myIframe.document.getElementById("renetweight").value;	//上架净重
			var weight = myIframe.document.getElementById("reweight").value;		//上架毛重
			
			//批次类型ID
			var lotid  = myIframe.document.getElementById("lotid").value;		
			var lotatt1  = myIframe.document.getElementById("lotatt1").value; 	//批次属性1
			var lotatt2  = myIframe.document.getElementById("lotatt2").value; 	//批次属性2
			var lotatt3  = myIframe.document.getElementById("lotatt3").value; 	//批次属性3
			var lotatt4  = myIframe.document.getElementById("lotatt4").value; 	//批次属性4
			var lotatt5  = myIframe.document.getElementById("lotatt5").value; 	//批次属性5
			var lotatt6  = myIframe.document.getElementById("lotatt6").value; 	//批次属性6
			var lotatt7  = myIframe.document.getElementById("lotatt7").value; 	//批次属性7
			var lotatt8  = myIframe.document.getElementById("lotatt8").value; 	//批次属性8
			var lotatt9  = myIframe.document.getElementById("lotatt9").value; 	//批次属性9
			var lotatt10  = myIframe.document.getElementById("lotatt10").value; //批次属性10
			var lotatt11  = myIframe.document.getElementById("lotatt11").value; //批次属性11
			var lotatt12  = myIframe.document.getElementById("lotatt12").value; //批次属性12
			
			//检查数量
			if(num == null || num.length < 1 || !IsFloat(num)){
				alert("【上架数量】不能为空且只能为数字！");
				return;
			}
			if(parseFloat(num) > parseFloat(knum)){
				alert("【上架数量(" + num + ")】不能大于收货记录的【可上架数量(" + knum + ")】！");
				return;
			}
			if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
				alert("【上架净重】只能为数字！");
				return;
			}else if(netweight == null || netweight.length < 1)
			{
				netweight = "0";
			}
			if(parseFloat(netweight) > parseFloat(knetweight)){
				alert("【上架净重(" + netweight + ")】不能大于收货记录的【可上架净重(" + knetweight + ")】！");
				return;
			}
			
			if(weight != null && weight.length > 0 && !IsFloat(weight)){
				alert("【上架毛重】只能为数字！");
				return;
			}else if(weight == null || weight.length < 1)
			{
				weight = "0";
			}
			if(parseFloat(weight) > parseFloat(kweight)){
				alert("【上架毛重(" + weight + ")】不能大于收货记录的【可上架毛重(" + kweight + ")】！");
				return;
			}
			//批次属性值验证
			if(myIframe.checkLotatt()==false){
				return;
			}
			
			//增加到表格中*******************************************
			//增加一行
			var objtb = list.document.getElementById("puttb");
			var newRow = null;	//行
			var newCell = null; //列
			
			//检查收货记录是否有重复
			for(var i=1; i < objtb.rows.length; i++)//表格的标题行不用判断
			{
				var rowtransid = objtb.rows.item(i).cells.item(0).getElementsByTagName("input")[0].value;
				if(transid == rowtransid){
					alert("【收货记录】已存在，请选删除相应收货记录再增加！");
					return;
				}
			}
			
			//增加一行
	        newRow = objtb.insertRow(objtb.rows.length);
	        newRow.setAttribute("onmouseover", function(){this.bgColor='#E2E8EA'});
			newRow.setAttribute("onmouseout", function(){this.bgColor=''});
			//行号
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute( "className", "TD_LIST1"); 
			newCell.setAttribute( "align", "center");
			newCell.innerHTML  = "<div align='center'>"+(objtb.rows.length - 1)+ "<input type='hidden' name='key' value='"+transid+"'></div>";
			//跟踪号
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute( "className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+transid+"</div>";
			//品名
	        newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+proudctname+"</div>";
			
			//包装
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+packname+"</div>";
			
			//单位
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+unitname+"</div>";
			
			//数量
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+num+"<input type='hidden' name='nu' value='"+num+"'></div>";
			
			//净重
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+netweight+"<input type='hidden' name='netwei' value='"+netweight+"'></div>";
			
			//毛重
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+weight+"<input type='hidden' name='wei' value='"+weight+"'></div>";
			
			//批次1
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt1+"<input type='hidden' name='lot1' value='"+lotatt1+"'></div>";
			
			//批次2
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt2+"<input type='hidden' name='lot2' value='"+lotatt2+"'></div>";
			
			//批次3
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt3+"<input type='hidden' name='lot3' value='"+lotatt3+"'></div>";
			
			//批次4
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt4+"<input type='hidden' name='lot4' value='"+lotatt4+"'></div>";
			
			//批次5
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt5+"<input type='hidden' name='lot5' value='"+lotatt5+"'></div>";
			
			//批次6
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt6+"<input type='hidden' name='lot6' value='"+lotatt6+"'></div>";
			
			//批次7
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt7+"<input type='hidden' name='lot7' value='"+lotatt7+"'></div>";
			
			//批次8
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt8+"<input type='hidden' name='lot8' value='"+lotatt8+"'></div>";
			
			//批次9
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt9+"<input type='hidden' name='lot9' value='"+lotatt9+"'></div>";
			
			//批次10
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt10+"<input type='hidden' name='lot10' value='"+lotatt10+"'></div>";
			
			//批次11
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt11+"<input type='hidden' name='lot11' value='"+lotatt11+"'></div>";
			
			//批次12
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt12+"<input type='hidden' name='lot12' value='"+lotatt12+"'></div>";
			
			//删除
	        newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST2");
			newCell.setAttribute( "align", "center");
			newCell.setAttribute("onclick", function (){DeleteRow(newRow)});
			newCell.innerHTML = "<input type='button' name='details' class='BUTTON_STYLE1' value='删除'>";
		
		}	
	}
	//删除行
	function DeleteRow(obj)
	{
		var tb = list.document.getElementById("puttb");
		if(tb.rows.length > 1)
		{
			tb.deleteRow(obj.rowIndex);
		}
		else
		{
			alert("页面无数据记录！");
		}
 	}

	
 	//保存数据
	function saveData(invoiceid)
	{
		LockButton();
		
		//托盘条码
		var tray_code = document.getElementById("traycode").value;
		//仓库ID
		var warehouseid = document.getElementById("warehouseid").value;
		//库区ID
		var whAreaId = document.getElementById("whAreaId").value;
		//上架方式
		var putmode = document.getElementById("putmode").value;
		
		if(tray_code != null && tray_code.length>1 && tray_code.length != 8)
		{
			alert("【托盘条码】不为8位，Y-000000 ！");
			UnLockButton();
			return;
		}
		if(putmode == null || putmode.length < 1){
			alert("【上架方式】不能为空！");
			UnLockButton();
			return;
		}
		
		
		//验证托盘是否可用
		if(tray_code != null && tray_code.length>1)
		{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isTrayCodeUse(tray_code, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
		}
		
		//if(whAreaId == null || whAreaId.length < 1){
		//	alert("【库区】不能为空！");
		//	UnLockButton();
		//	return;
		//}
		
		//有中文
		var msg = "";
		var transrows = 0;	//有几行数据
		//表格
		var objtb = list.document.getElementById("puttb");
		for(var i=1; i<objtb.rows.length; i++){
			transrows = transrows + 1;
			var transid = objtb.rows.item(i).cells.item(0).getElementsByTagName("input")[0].value;
			var num = objtb.rows.item(i).cells.item(5).getElementsByTagName("input")[0].value;
			var netweight = objtb.rows.item(i).cells.item(6).getElementsByTagName("input")[0].value;
			var weiht = objtb.rows.item(i).cells.item(7).getElementsByTagName("input")[0].value;
			
			var lotatt1 = objtb.rows.item(i).cells.item(8).getElementsByTagName("input")[0].value;
			var lotatt2 = objtb.rows.item(i).cells.item(9).getElementsByTagName("input")[0].value;
			var lotatt3 = objtb.rows.item(i).cells.item(10).getElementsByTagName("input")[0].value;
			var lotatt4 = objtb.rows.item(i).cells.item(11).getElementsByTagName("input")[0].value;
			var lotatt5 = objtb.rows.item(i).cells.item(12).getElementsByTagName("input")[0].value;
			var lotatt6 = objtb.rows.item(i).cells.item(13).getElementsByTagName("input")[0].value;
			var lotatt7 = objtb.rows.item(i).cells.item(14).getElementsByTagName("input")[0].value;
			var lotatt8 = objtb.rows.item(i).cells.item(15).getElementsByTagName("input")[0].value;
			var lotatt9 = objtb.rows.item(i).cells.item(16).getElementsByTagName("input")[0].value;
			var lotatt10 = objtb.rows.item(i).cells.item(17).getElementsByTagName("input")[0].value;
			var lotatt11 = objtb.rows.item(i).cells.item(18).getElementsByTagName("input")[0].value;
			var lotatt12 = objtb.rows.item(i).cells.item(19).getElementsByTagName("input")[0].value;
			
			msg = msg +"<input type=hidden name='"+transrows+"transid' value='"+transid+"'>"
					+ "<input type=hidden name='"+transrows+"num' value='"+num+"'>"
					+ "<input type=hidden name='"+transrows+"netweight' value='"+netweight+"'>"
					+ "<input type=hidden name='"+transrows+"weiht' value='"+weiht+"'>"
					+ "<input type=hidden name='"+transrows+"volume' value='0'>"
					+ "<input type=hidden name='"+transrows+"lotatt1' value='"+lotatt1+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt2' value='"+lotatt2+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt3' value='"+lotatt3+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt4' value='"+lotatt4+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt5' value='"+lotatt5+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt6' value='"+lotatt6+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt7' value='"+lotatt7+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt8' value='"+lotatt8+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt9' value='"+lotatt9+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt10' value='"+lotatt10+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt11' value='"+lotatt11+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt12' value='"+lotatt12+"'>";
		}
		//alert(transrows);
		//************************************************
		if(transrows > 0){
			if(confirm("你确定是否生成上架任务？"))
			{		
				var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=putawayAction";
	
				//有中文
				msg = msg + "<input type=hidden name='transrows' value='"+transrows+"'>";
				list.formx1.innerHTML = msg;
				list.formx1.action = strUrl + "&method=createPutaway&invoiceid="+ invoiceid + "&traycode=" + tray_code + "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId;
				list.document.formx1.submit();
					
			}else{
				UnLockButton();
			}
		}else{
			alert("请增加需要上架的信息！");
			UnLockButton();
		}
	}
	
	function OnLoad()
	{
		selectObject('<%=warehouseid%>', 'warehouseid', '1');// 仓库
		selectAreaList('', 'whAreaId', '<%=warehouseid%>', "1");// 库区
		//上架方式
		selectType('PL', 'putmode', 'putmode');
	}
</script>
  </head>
  
  <body onload="javascript:OnLoad();" >
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：入库管理 -&gt; 上架 -&gt; 按收货记录上架</div></td>
   </tr>
  </table>
	
 <!-- ======== 收货列表开始 ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
   <tr>
	 <td valign="top"  height="185">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/BMSService?actionCode=putawayAction&flag=1&invoiceid=<%=invoiceid%>" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== 收货列表结束 ======== -->
 <table width="98%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
            
        <tr>
          <td  align="right" class="yx1"  >托盘条码：</td>
          <td  class="yx" >
          	 <input type="text" name="traycode"    style="height:18px;width:100px;"/> 
          </td>
          <td  align="right" class="yx1">仓&nbsp;&nbsp;&nbsp;库：</td>
          <td  class="yx">
	          <select name="warehouseid"  style="width:100px;" >
	            <option>--请选择--</option>
	           </select>
          </td>
          <td  align="right" class="yx1">库&nbsp;&nbsp;&nbsp;区：</td>
          <td  class="yx">
	           <select name="whAreaId"  style="width:100px;" >
	            <option>--请选择--</option>
	           </select>
          </td>
          <td  align="right" class="yx1"><span class="red">*</span>上架方式：</td>
          <td  class="xx1">
	           <select name="putmode"  style="width:100px;" >
	            <option>--请选择--</option>
	           </select>
          </td>
        </tr>    
            
        <tr>
          <td height="25" colspan="8" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;增加" onClick="addData();"  />
            &nbsp;&nbsp;&nbsp;&nbsp;
            
            <input name="button_save" type="button" class="button_edit" id="button" value="&nbsp;&nbsp;保存上架" onclick="saveData('<%=invoiceid%>');" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
      
<table width="98%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>

 <!-- ======== 上架列表开始 ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="TABLE_LIST2">    
	<tr>
         <td valign="top" height="350" > 
					<iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/putaway/inbound_putaway_mgr_put.jsp"   width="100%" height="100%"   frameborder="0" ></iframe>
         </td>
    </tr>
 </table>
 <!-- ======== 上架列表结束 ======== -->
 

  </body>
</html>

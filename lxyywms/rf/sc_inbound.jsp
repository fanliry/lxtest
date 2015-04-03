<%@ page contentType="text/html; charset=GBK"%>
<%
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = request.getParameter("warehouseid");
	if(strWarehouseID == null  || strWarehouseID.equals("null")){
		strWarehouseID = (String)session.getAttribute("warehouseid");
	}
	
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>

<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	var sp_flag = false;
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

	
	//**********************************************************
	//保存数据
	function saveData(warehouseid)
	{
		LockButton();
		
		//托盘条码
		var traycode = document.getElementById("tray_code").value;
		//库区ID
		var whAreaId = document.getElementById("whAreaId").value;
		//上架方式
		var putmode = "";
		
		//品名
		var product_id = document.getElementById("product_id").value;
		//包装
		var pack_id = document.getElementById("pack_id").value;
		//单位
		var eunit = document.getElementById("eunit").value;
		//数量
		var num = document.getElementById("num").value;
		var netweight = document.getElementById("netweight").value;
		var weight = document.getElementById("weight").value;
		
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
		var lotatt10  = document.getElementById("lotatt10").value; //批次属性10
		var lotatt11  = document.getElementById("lotatt11").value; //批次属性11
		var lotatt12  = document.getElementById("lotatt12").value; //批次属性12
		
		if(tray_code != null && tray_code.length>1 && tray_code.length != 8)
		{
			alert("【托盘条码】不为8位，Y-000000 ！");
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
		
		if(product_id == null || product_id.length < 1){
			alert("【品名】不能为空！");
			UnLockButton();
			return;
		}
		
		//检查数量
		if(num == null || num.length < 1 || !IsFloat(num)){
				alert("【数量】不能为空且只能为数字！");
				UnLockButton();
				return;
		}
		if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
				alert("【净重】只能为数字！");
				UnLockButton();
				return;
		}else if(netweight == null || netweight.length < 1)
		{
				netweight = "0";
		}
		
		if(weight != null && weight.length > 0 && !IsFloat(weight)){
				alert("【毛重】只能为数字！");
				UnLockButton();
				return;
		}else if(weight == null || weight.length < 1)
		{
				weight = "0";
		}
		
		
		//批次属性值验证
		if(checkLotatt()==false){
			UnLockButton();
			return;
		}
		
		condition = "&productid=" + product_id + "&packid=" + pack_id + "&eunit=" + eunit + "&num=" + num + "&weight=" + weight + "&netweight=" + netweight + "&volume=0"; 
		//************************************************

			if(confirm("你确定是否生成上架任务？"))
			{		
				var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=rfJobAction";
				//有中文
				var msg = "<input type=hidden name='lotid' value='"+lotid+"'>"
						+ "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
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

				formx1.innerHTML = msg;
				formx1.action = strUrl + "&method=addricoExec&traycode=" + traycode + "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&putmode=" + putmode + condition;
				document.formx1.submit();
					
			}else{
				UnLockButton();
			}
	
	}
	
	
	
	
	
	
	

	
	//页面登陆
	function onLoad()
	{
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
		selectAreaList('', 'whAreaId', '<%=strWarehouseID%>', "1")// 库区
	}
-->
</script>
</head>

<body onload="onLoad();">
 <table id="tbb"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td height="25" bgcolor="#d9e8fc" align="center" colspan="2">
 <table width="100%">
   <tr>
     <td width="20%"></td>
     <td width="60%" align="center" class="font_006699_bold_12">RF生产入库</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">返回</a></td>
   </tr>
 </table>
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">托盘条码：</td>
     <td class="TD_ADD"><input type="text" id="tray_code" value="" class="rf_input_long" maxlength="10"> </td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">上架库区：</td>
     <td class="TD_ADD"><input type="hidden" id="warehouseid" value="<%=strWarehouseID%>">
     		<select name="whAreaId"  style="width:162px;" >
	            <option>--请选择--</option>
	           </select> 
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">品&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
     <td class="TD_ADD">
     	<input type="text" name="product_name" class="input_readonly" readonly="readonly" /> 
     	<input type="hidden" name="product_id" class="input_readonly">
     	<input name="moreBtn3" type="button" class="button_select" value="…" onclick="openProductPacke('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520','product_id','product_name', 'pack_id', 'pack_name', '1','eunit', 'lotid');" />
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">包&nbsp;&nbsp;&nbsp;&nbsp;装：</td>
     <td class="TD_ADD">
     	<input type="text" id="pack_name" class="input_readonly" readonly="readonly" maxlength="10"> 
     	<input type="hidden" name="pack_id" class="input_readonly">
     	
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">单&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
     <td class="TD_ADD">
     	<select name="eunit" id="eunit" style="width:100px;" class="input_readonly" >
	            <option>--请选择--</option>
	        </select>
     
      </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">数&nbsp;&nbsp;&nbsp;&nbsp;量：</td>
     <td class="TD_ADD"><input type="text" id="num" value="" class="rf_input_long" maxlength="10"> </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">毛&nbsp;&nbsp;&nbsp;&nbsp;重：</td>
     <td class="TD_ADD"><input type="text" id="weight" value="" class="rf_input_long" maxlength="10"> </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">净&nbsp;&nbsp;&nbsp;&nbsp;重：</td>
     <td class="TD_ADD"><input type="text" id="netweight" value="" class="rf_input_long" maxlength="10"> </td>
   </tr>
   
   <tr>
     <td class="TD_ADD" height="5" colspan="2">
     	<input type="hidden" name="lotid" value=""/>
     </td>
   </tr>
        
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt001" align="right">批次属性1：</div></td>
          <td class="TD_ADD"><div id="lotvalue001" align="left">
          	<input type="text" name="lotatt1" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
        </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt002" align="right">批次属性2：</div></td>
          <td class="TD_ADD"><div id="lotvalue002" align="left">
          	<input type="text" name="lotatt2" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
         </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt003" align="right">批次属性3：</div></td>
          <td class="TD_ADD" ><div id="lotvalue003" align="left">
          	<input type="text" name="lotatt3" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt004" align="right">批次属性4：</div></td>
          <td class="TD_ADD"><div id="lotvalue004" align="left">
          	<input type="text" name="lotatt4" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
        </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt005" align="right">批次属性5：</div></td>
          <td class="TD_ADD"><div id="lotvalue005" align="left">
          	<input type="text" name="lotatt5" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
         </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt006" align="right">批次属性6：</div></td>
          <td class="TD_ADD" ><div id="lotvalue006" align="left">
          	<input type="text" name="lotatt6" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt007" align="right">批次属性7：</div></td>
          <td class="TD_ADD"><div id="lotvalue007" align="left">
          	<input type="text" name="lotatt7" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
        </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt008" align="right">批次属性8：</div></td>
          <td class="TD_ADD"><div id="lotvalue008" align="left">
          	<input type="text" name="lotatt8" size="16"    style="height:18px;width:100px;"/></div> 
           </td>
        </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt009" align="right">批次属性9：</div></td>
          <td class="TD_ADD" ><div id="lotvalue009" align="left">
          	<input type="text" name="lotatt9" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt010" align="right">批次属性10：</div></td>
          <td class="TD_ADD"><div id="lotvalue010" align="left">
          	<input type="text" name="lotatt10" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
         </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt011" align="right">批次属性11：</div></td>
          <td class="TD_ADD"><div id="lotvalue011" align="left">
          	<input type="text" name="lotatt11" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
         </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt012" align="right">批次属性12：</div></td>
          <td class="TD_ADD" ><div id="lotvalue012" align="left">
          	<input type="text" name="lotatt12" size="16" style="height:18px;width:100px;"/></div>
          </td>
        </tr>
   
   

   <tr>
     <td align="center" height="10" colspan="2"></td>
   </tr>
   
   <tr>
     <td class="TD_ADD" align="center" colspan="2">

       <input type="button" name="add" class="BUTTON_STYLE1" value="保存" onClick="saveData('<%=strWarehouseID%>');">
     </td>
    </tr>
 </table>
 

<FORM action="" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>

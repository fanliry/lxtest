
<%@ page contentType="text/html; charset=GBK"%>
<%
	
	//单据ID
	String invoiceid = request.getParameter("invoiceid");
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
	}
	/*释放按钮*/
	function UnLockButton(){
		
		document.getElementById("button_add").disabled = false;
	}
 	//保存数据
	function saveData()
	{
		LockButton();
		//单据ID
		var invoiceid = document.getElementById("invoiceid").value;
		//单据详细ID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		
		
		//收货数量
		var invoicenum = document.getElementById("renum").value;
		//剩余收货数量
		var reinvoicenum = document.getElementById("realnum").value;	
		//收货毛重
		var weight = document.getElementById("reweight").value;
		//剩余收货毛重
		var reweight = document.getElementById("realweight").value;
		
		//收货净重
		var netweight = document.getElementById("renetweight").value;
		//剩余收货净重
		var renetweight = document.getElementById("realnetweight").value;
		//收货体积
		var volume = document.getElementById("revolume").value;
		//剩余收货体积
		var revolume = document.getElementById("realvolume").value;
		
		//拒收数量
		var rejectednum = document.getElementById("rejectednum").value;
		//冻结数量
		var holdnum = document.getElementById("holdnum").value;
		//拒收代码
		var rejectcode = document.getElementById("rejectcode").value;
		//拒收原因
		var rejectreason = document.getElementById("rejectreason").value;
		//冻结代码
		var holdcode = document.getElementById("holdcode").value;
		//冻结原因
		var holdreason = document.getElementById("holdreason").value;
		
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
		
		if(invoicedetailid == null || invoicedetailid.length < 1){
			alert("【单据详细】不能为空！");
			UnLockButton();
			return;
		}
		
		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("【收货数量】不能为空且只能为数字！");
			UnLockButton();
			return;
		}
		if(parseFloat(invoicenum) > parseFloat(reinvoicenum)){
			alert("【收货数量(" + invoicenum + ")】不能大于单据【剩余收货数量(" + reinvoicenum + ")】！");
			UnLockButton();
			return;
		}
		if(rejectednum != null && rejectednum.length > 0 && !IsFloat(rejectednum)){
			alert("【拒收数量】只能为数字！");
			UnLockButton();
			return;
		}else if(rejectednum == null || rejectednum.length < 1)
		{
			rejectednum = "0";
		}
		if(holdnum != null && holdnum.length > 0 && !IsFloat(holdnum)){
			alert("【冻结数量】只能为数字！");
			UnLockButton();
			return;
		}else if(holdnum == null || holdnum.length < 1)
		{
			holdnum = "0";
		}
		
		if((parseFloat(invoicenum) + parseFloat(rejectednum) + parseFloat(holdnum) )  == 0){ //当收货数量为0时，表示要冻结数量或拒收数量
			alert("【收货数量(" + invoicenum + ")】+ 【拒收数量(" + rejectednum + ")】 + 【冻结数量(" + holdnum + ")】只能大于【0】！");
			UnLockButton();
			return;
		}
		
		if((parseFloat(invoicenum) + parseFloat(rejectednum) + parseFloat(holdnum) )  > parseFloat(reinvoicenum)){ //当收货数量为0时，表示要冻结数量或拒收数量
			alert("【收货数量(" + invoicenum + ")】+ 【拒收数量(" + rejectednum + ")】 + 【冻结数量(" + holdnum + ")】不能大于【剩余收货数量(" + reinvoicenum + ")】！");
			UnLockButton();
			return;
		}
		
		if(weight != null && weight.length > 0 && !IsFloat(weight)){
			alert("【毛重】只能为数字！");
			UnLockButton();
			return;
		}else if(weight == null || weight.length < 1)
		{
			weight = "0";
		}
		if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
			alert("【净重】只能为数字！");
			UnLockButton();
			return;
		}else if(netweight == null || netweight.length < 1)
		{
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
		//批次属性值验证
		if(checkLotatt()==false){
			UnLockButton();
			return;
		}
		
		
		
		var details = "&invoicedetailid=" + invoicedetailid + "&num=" + invoicenum + "&weight=" + weight + "&netweight=" + netweight + "&volume=" + volume+
					 "&rejectednum=" + rejectednum + "&rejectcode=" + rejectcode + "&rejectreason=" + rejectreason + 
					 "&holdnum=" + holdnum + "&holdcode=" + holdcode + "&holdreason=" + holdreason;	

		//************************************************
		if(confirm("这次收货数量为"+invoicenum+",你确定是否收货？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=receiptAction";
			//myIframe.location.href = strUrl + "&flag=2&invoiceid="+ invoiceid + "&tspace=" + reclocation + detail;
			
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
			myIframe.formx1.innerHTML = msg;
			myIframe.formx1.action = strUrl + "&method=receipt&invoiceid="+ invoiceid + "&lotid=" + lotid + details;
			myIframe.document.formx1.submit();
			
			document.form1.reset();//重置		
			lotreset();	  //重置	
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/receipt/inbound_receipt_mgr_task.jsp";
			
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
	
	function OnLoad()
	{
		selectType('', 'rejectcode', 'rejectcode');
		selectType('', 'holdcode', 'holdcode');
	}
</script>
  </head>
  
  <body  onload="javascript:OnLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：入库管理 -&gt; 收货 -&gt; 按明细收货</div></td>
   </tr>
  </table>
	
 <!-- ======== 单据明细列表开始 ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
   <tr>
	 <td valign="top"  height="185">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/BMSService?actionCode=receiptAction&flag=1&invoiceid=<%=invoiceid%>" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== 单据明细列表结束 ======== -->
 <table width="98%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
 <!-- ======== 收货明细列表开始 ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="TABLE_LIST2">    
	<tr>
         <td valign="top"  height="185" > <!-- 如要有横着的滚动条，高度设为185即可 没有是165 -->
					<iframe id="detail" src="<%=request.getContextPath()%>/standard/jsp/inbound/receipt/inbound_receipt_mgr_task.jsp"   width="100%" height="100%"   frameborder="0" ></iframe>
         </td>
    </tr>
 </table>
 <!-- ======== 收货明细列表结束 ======== -->
 
<table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>	
	
	<FORM id="form1" name="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        
        <tr>
          <td width="13%" align="right" class="yx1"  >品&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="product_name" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td width="15%" align="right" class="yx1">包&nbsp;&nbsp;&nbsp;&nbsp;装：</td>
          <td width="20%" class="yx">
	          <input type="text" name="packname" class="input_readonly"  readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1">单&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
          <td width="19%" class="xx1">
	          <select name="eunit" id="eunit" style="width:100px;" class="input_readonly" >
	            <option>--请选择--</option>
	        </select>
          </td>
        </tr>
        <tr>
          <td width="13%" align="right" class="yx1"  >到货数量：</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="invoicenum" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td width="15%" align="right" class="yx1">已收货数量：</td>
          <td width="20%" class="yx">
	          <input type="text" name="recnum" class="input_readonly"  readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>现收货数量：</td>
          <td width="19%" class="xx1">
	          <input type="text" name="renum"   style="height:18px;width:100px;"/>
	          <input type="hidden" name="realnum" size="16"   style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>现收货净重：</td>
          <td class="yx">
          	<input type="text" name="renetweight" size="16"   style="height:18px;width:100px;"/>
          	<input type="hidden" name="realnetweight" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>现收货毛重：</td>
          <td class="yx">
          	<input type="text" name="reweight" size="16"   style="height:18px;width:100px;"/>
          	<input type="hidden" name="realweight" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>现收货体积：</td>
          <td class="xx1">
          	<input type="text" name="revolume" size="16"   style="height:18px;width:100px;"/>
          	<input type="hidden" name="realvolume" size="16"   style="height:18px;width:100px;"/>
          </td>
        </tr>
        
        <tr>
          <td  align="right" class="yx1">拒收数量：</td>
          <td  class="yx" >
          	 <input type="text" name="rejectednum"    style="height:18px;width:100px;"/> 
          </td>
          <td  align="right" class="yx1">拒收原因代码：</td>
          <td  class="yx">
	          <select name="rejectcode" id="rejectcode" style="width:100px;" >
	            <option>--请选择--</option>
	        </select>
          </td>
          <td  align="right" class="yx1">拒收原因描述：</td>
          <td  class="xx1">
	          <input type="text" name="rejectreason" value=""   style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td  align="right" class="yx1"  >冻结数量：</td>
          <td  class="yx" >
          	 <input type="text" name="holdnum"    style="height:18px;width:100px;"/> 
          </td>
          <td  align="right" class="yx1">冻结原因代码：</td>
          <td  class="yx">
	          <select name="holdcode" id="holdcode" style="width:100px;" >
	            <option>--请选择--</option>
	        </select>
          </td>
          <td  align="right" class="yx1">冻结原因描述：</td>
          <td  class="xx1">
	          <input type="text" name="holdreason" value=""   style="height:18px;width:100px;"/>
          </td>
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
        		<input type="hidden" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
        	</TD>
        </tr>
        
        
        
        <tr>
          <td height="27" colspan="6" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;收货" onClick="saveData();"  />
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;重置" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
      
 </FORM>

  </body>
</html>

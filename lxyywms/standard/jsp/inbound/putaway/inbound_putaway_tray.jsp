<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%
	//一托盘可放多少数量
	int oneTray = (Integer)request.getAttribute("onetray");//一托盘可放多少数量
	//可码托盘总数
	int iTrayNum = (Integer)request.getAttribute("traynum");//码盘的托盘总数
	//收货记录
	InboundReceiptTransaction receiptTrans = (InboundReceiptTransaction)request.getAttribute("trans");//收货记录
	
	String transid = "";		    //交易号
	String strWarehouseID = "";	 //仓库ID
    String m_strProductName = "";// 产品
    String m_strPackName = "";	// 包装
    String strPackid = "";		//包装ID
	String punit = "";          //单位
    double num = 0;            //数量
    double weight = 0;         //重量
    double netweight = 0;      //净重 
    
    String lotid = "";            	//批次类型ID 
    String lotatt1 = "";  			//批次属性1
    String lotatt2 = "";  			// 批次属性2
    String lotatt3 = "";  			// 批次属性3
    String lotatt4 = "";  			// 批次属性4
    String lotatt5 = "";  			// 批次属性5
    String lotatt6 = "";  			// 批次属性6
    String lotatt7 = "";  			// 批次属性7
    String lotatt8 = "";  			// 批次属性8
    String lotatt9 = "";  			// 批次属性9
    String lotatt10 = "";  			// 批次属性10
    String lotatt11 = "";  			// 批次属性11
    String lotatt12 = "";  			// 批次属性12  

	//保留小数2位
	NumberFormat nbf=NumberFormat.getInstance();      
	nbf.setMinimumFractionDigits(2);
	nbf.setMaximumFractionDigits(2);  

	if(receiptTrans != null)
	{
		transid = receiptTrans.getTransreceiptid();		      //交易号	
		strWarehouseID = receiptTrans.getWarehouseid();		  //仓库ID
     	m_strProductName = receiptTrans.getM_strProductName();// 产品
     	m_strPackName = receiptTrans.getM_strPackName();	  // 包装
     	strPackid = receiptTrans.getPackid();
	 	punit = receiptTrans.getEunit();           //单位
     	num = receiptTrans.getRecnum() - receiptTrans.getPucnum();         //数量   收货数量-上架数量
     	weight = receiptTrans.getReweight() - receiptTrans.getPuweight();  //重量  收货重量-上架重量
     	netweight = receiptTrans.getRenetweight() - receiptTrans.getPunetweight();//净重  收货净重-上架净重
    
		lotid = receiptTrans.getLotid();            	// 批次类型ID 
        lotatt1 = receiptTrans.getLotatt1();  			// 批次属性1
     	lotatt2 = receiptTrans.getLotatt2();  			// 批次属性2
     	lotatt3 = receiptTrans.getLotatt3();			// 批次属性3
     	lotatt4 = receiptTrans.getLotatt4();  			// 批次属性4
     	lotatt5 = receiptTrans.getLotatt5();  			// 批次属性5
     	lotatt6 = receiptTrans.getLotatt6();  			// 批次属性6
     	lotatt7 = receiptTrans.getLotatt7();  			// 批次属性7
     	lotatt8 = receiptTrans.getLotatt8();  			// 批次属性8
     	lotatt9 = receiptTrans.getLotatt9();  			// 批次属性9
     	lotatt10 = receiptTrans.getLotatt10();  		// 批次属性10
     	lotatt11 = receiptTrans.getLotatt11();  		// 批次属性11
     	lotatt12 = receiptTrans.getLotatt12();  		// 批次属性12
	}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
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
	
	/*锁定按钮*/
	function LockButton(){
		
		document.getElementById("add").disabled = true;
	}
	/*释放按钮*/
	function UnLockButton(){
		
		document.getElementById("add").disabled = false;
	}
	
	//保存
	function addData(traynum){
		LockButton();
		var transid = document.getElementById("transid").value;//收货记录号
		var whAreaId = document.getElementById("whAreaId").value;		//上架库区ID
		
		//if(whAreaId == null || whAreaId.length < 1){
		//	alert("【上架库区】不能为空！");
		//	UnLockButton();
		//	return;
		//}
		
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
		
		//有中文
		var msg = "<input type=hidden name='transid' value='"+transid+"'>"
				+ "<input type=hidden name='whAreaId' value='"+whAreaId+"'>"
				+ "<input type=hidden name='lotid' value='"+lotid+"'>"
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
		
		if(confirm("你确定码成"+traynum+"托？"))
		{		
			window.returnValue = msg;
    		window.close();	
		}else{
			UnLockButton();
		}
	}
	
	
	function viewTrans(packid,eunit,lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
	{
		//selectinoutUnit(eunit, 'eunit', packid, '1');	//单位		
		setLotValue(lotid, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);//批次
					
	}
</script>

</head>

<body onload="javascript:viewTrans('<%=strPackid%>','<%=punit%>','<%=lotid%>','<%=lotatt1%>','<%=lotatt2%>','<%=lotatt3%>','<%=lotatt4%>','<%=lotatt5%>','<%=lotatt6%>','<%=lotatt7%>','<%=lotatt8%>','<%=lotatt9%>','<%=lotatt10%>','<%=lotatt11%>','<%=lotatt12%>');selectAreaList('', 'whAreaId', '<%=strWarehouseID%>', '1');">
	 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   		<tr>
     		<td height="25"><div class="font_001F56_bold_12">当前位置：入库管理 -&gt; 上架 -&gt; 码盘</div></td>
   		</tr>
     </table>
	 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="13%" align="right" class="yx1"  >跟&nbsp;踪&nbsp;号：</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="transid" class="input_readonly" value="<%=transid%>" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td width="15%" align="right" class="yx1">品&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
          <td width="20%" class="yx">
	          <input type="text" name=product_name class="input_readonly" value="<%=m_strProductName%>"  readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1">上架库区：</td> 
          <td width="19%" class="xx1">
	          <select name="whAreaId"  style="width:100px;" >
	            <option>--请选择--</option>
	           </select> 
          </td>
        </tr>
        <tr>
     		<td height="5" colspan="6">
     			<input type="hidden" name="packname" value="<%=m_strPackName%>"/>
     			<input type="hidden" name="lotid" value="<%=lotid%>"/>
     		</td>
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
     		<td height="10" colspan="6">

     		</td>
   		</tr>
   		
   		<tr>
   		  
          <td  align="right" class="yx1_top"  >上架总数：</td>
          <td  class="yx_top" >
          	 <input type="text" name="num" class="input_readonly" value="<%=nbf.format(num)%>" readonly   style="height:18px;width:50px;"/> 
          </td>
          <td  align="right" class="yx1_top"  >可码托盘总数：</td>
          <td  class="xx1_top" >
          	 <input type="text" name="itraynum" class="input_readonly" value="<%=iTrayNum%>" readonly   style="height:18px;width:50px;"/> 
          </td>
          <td  align="right" class="yx1_top"  >托盘可放数量：</td>
          <td  class="xx1_top" >
	          <input type="text" name="onetray" class="input_readonly" value="<%=oneTray%>"  readonly  style="height:18px;width:50px;"/>
          </td>
        </tr>
   		
   		<tr>
     		<td height="10" colspan="6">

     		</td>
   		</tr>
   		 
        <tr>
          <td height="25" colspan="6" align="center" >
          <input name="add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;码盘" onClick="addData('<%=iTrayNum%>');"  />
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>

</body>
</html>

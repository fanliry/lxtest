<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%
	List ls = (List)request.getAttribute("exResultList");
	InboundReceiptTransaction receiptTrans = null;//收货记录
	
	String transid = "";		    //交易号
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
	if(ls != null && ls.size() > 0) 
	{
		receiptTrans = (InboundReceiptTransaction)ls.get(0);
	}
	if(receiptTrans != null)
	{
		transid = receiptTrans.getTransreceiptid();		      //交易号
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
	
	function viewTrans(packid,eunit,lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
	{
		selectinoutUnit(eunit, 'eunit', packid, '1');	//单位		
		setLotValue(lotid, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);//批次
					
	}
</script>

</head>

<body onload="javascript:viewTrans('<%=strPackid%>','<%=punit%>','<%=lotid%>','<%=lotatt1%>','<%=lotatt2%>','<%=lotatt3%>','<%=lotatt4%>','<%=lotatt5%>','<%=lotatt6%>','<%=lotatt7%>','<%=lotatt8%>','<%=lotatt9%>','<%=lotatt10%>','<%=lotatt11%>','<%=lotatt12%>');">

	 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add1" >
        <tr>
          <td width="13%" align="right" class="yx1"  >跟&nbsp;踪&nbsp;号：</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="transid" class="input_readonly" value="<%=transid%>" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td width="15%" align="right" class="yx1">品&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
          <td width="20%" class="yx">
	          <input type="text" name=product_name class="input_readonly" value="<%=m_strProductName%>"  readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1">单&nbsp;&nbsp;&nbsp;&nbsp;位：</td> 
          <td width="19%" class="xx1">
	          <select name="eunit" id="eunit" style="width:100px;" class="input_readonly" >
	            <option>--请选择--</option>
	        </select>
          </td>
        </tr>
        <tr>
          <td width="13%" align="right" class="yx1"  >可上架数量：</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="vnum" class="input_readonly" value="<%=nbf.format(num)%>" readonly  style="height:18px;width:100px;"/> 
          	 <input type="hidden" name="num" class="input_readonly" value="<%=num%>" readonly  style="height:18px;width:100px;"/> 
          </td>
          <td width="15%" align="right" class="yx1">可上架净重：</td>
          <td width="20%" class="yx">
	          <input type="text" name="vnetweight" class="input_readonly" value="<%=nbf.format(netweight)%>"  readonly  style="height:18px;width:100px;"/>
	          <input type="hidden" name="netweight" class="input_readonly" value="<%=netweight%>"  readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1">可上架毛重：</td>
          <td width="19%" class="xx1">
	          <input type="text" name="vweight"  class="input_readonly" value="<%=nbf.format(weight)%>"  readonly  style="height:18px;width:100px;"/>
	          <input type="hidden" name="weight"  class="input_readonly" value="<%=weight%>"  readonly  style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>现上架数量：</td>
          <td class="yx">
          	<input type="text" name="renum" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>现上架净重：</td>
          <td class="yx">
          	<input type="text" name="renetweight" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>现上架毛重：</td>
          <td class="xx1">
          	<input type="text" name="reweight" size="16"   style="height:18px;width:100px;"/>
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
      </table>
		 <!-- ======== 分页标签 ======== -->
	<%@ include file="include/page4.jsp" %>
</body>
</html>

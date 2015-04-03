<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptDetail" %>
<%
	List ls = (List)request.getAttribute("exResultList");
	InboundReceiptDetail receiptDetail = null;//收货记录 
	
	String invoiceid = "";		 //收货单ID
	String detailid = "";		 //收货单详细ID
	String strStatus = "";		 //收货单详细状态
    String m_strProductName = "";// 产品
    String m_strPackName = "";	 // 包装
    String strPackid = "";		 //包装ID
	String punit = "";           //单位
    double num = 0;              //数量
    double weight = 0;           //重量
    double netweight = 0;        //净重 
    
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
		receiptDetail = (InboundReceiptDetail)ls.get(0);
	}
	if(receiptDetail != null)
	{
		invoiceid = receiptDetail.getReinvoiceid();		 //收货单ID
	    detailid = receiptDetail.getReincoicedetailid();		 //收货单详细ID
     	m_strProductName = receiptDetail.getM_strProductName();// 产品
     	m_strPackName = receiptDetail.getM_strPackName();	  // 包装
     	strPackid = receiptDetail.getPackid();
	 	punit = receiptDetail.getEunit();           //单位
	 	//还剩收货数量 =  开单数量 - 收货数量 - 拒收货数量 - 冻结数量
     	num = receiptDetail.getInvoicenum() - receiptDetail.getRecnum() - receiptDetail.getRejectednum() - receiptDetail.getHoldnum();         //数量   收货数量-上架数量
     	weight = receiptDetail.getWeight() - receiptDetail.getReweight();  		  //重量  开单重量-收货重量
     	netweight = receiptDetail.getNetweight() - receiptDetail.getRenetweight();//净重  开单净重-收货净重
    
		lotid = receiptDetail.getLotid();            	// 批次类型ID 
        lotatt1 = receiptDetail.getLotatt1();  			// 批次属性1
     	lotatt2 = receiptDetail.getLotatt2();  			// 批次属性2
     	lotatt3 = receiptDetail.getLotatt3();			// 批次属性3
     	lotatt4 = receiptDetail.getLotatt4();  			// 批次属性4
     	lotatt5 = receiptDetail.getLotatt5();  			// 批次属性5
     	lotatt6 = receiptDetail.getLotatt6();  			// 批次属性6
     	lotatt7 = receiptDetail.getLotatt7();  			// 批次属性7
     	lotatt8 = receiptDetail.getLotatt8();  			// 批次属性8
     	lotatt9 = receiptDetail.getLotatt9();  			// 批次属性9
     	lotatt10 = receiptDetail.getLotatt10();  		// 批次属性10
     	lotatt11 = receiptDetail.getLotatt11();  		// 批次属性11
     	lotatt12 = receiptDetail.getLotatt12();  		// 批次属性12
	}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
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
		if(lotid != "" && lotid.length > 0){
			selectinoutUnit(eunit, 'eunit', packid, '1');	//单位		
			setLotValue(lotid, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);//批次	
		}
					
	}
</script>

</head>

<body onload="javascript:viewTrans('<%=strPackid%>','<%=punit%>','<%=lotid%>','<%=lotatt1%>','<%=lotatt2%>','<%=lotatt3%>','<%=lotatt4%>','<%=lotatt5%>','<%=lotatt6%>','<%=lotatt7%>','<%=lotatt8%>','<%=lotatt9%>','<%=lotatt10%>','<%=lotatt11%>','<%=lotatt12%>');">

	 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP" >
        <tr>
          <td width="30%" align="right" class="yx1"  >收货单号：</td>
          <td width="70%"  class="yx" >
          	 <input type="text" name="invoiceid" class="input_readonly" value="<%=invoiceid%>" readonly   style="height:18px;width:100px;"/> 
          	 <input type="hidden" name="detailid" value="<%=detailid%>"/>
          </td>
        </tr>
        <tr>  
          <td  align="right" class="yx1">品&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
          <td  class="yx">
	          <input type="text" name=product_name class="input_readonly" value="<%=m_strProductName%>"  readonly  style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td  align="right" class="yx1">单&nbsp;&nbsp;&nbsp;&nbsp;位：</td> 
          <td  class="xx1">
	          <select name="eunit" id="eunit" style="width:100px;" class="input_readonly" >
	            <option>--请选择--</option>
	        </select>
          </td>
        </tr>
 
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>现收货数量：</td>
          <td class="yx">
          	<input type="text" name="renum" size="16"   style="height:18px;width:100px;" value="<%=num%>"/>
          	<input type="hidden" name="num" class="input_readonly" value="<%=num%>" readonly  style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>现收货净重：</td>
          <td class="yx">
          	<input type="text" name="renetweight" size="16"   style="height:18px;width:100px;" value="<%=netweight%>"/>
          	<input type="hidden" name="netweight" class="input_readonly" value="<%=netweight%>"  readonly  style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>现收货毛重：</td>
          <td class="xx1">
          	<input type="text" name="reweight" size="16"   style="height:18px;width:100px;" value="<%=weight%>"/>
          	<input type="hidden" name="weight"  class="input_readonly" value="<%=weight%>"  readonly  style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
     		<td height="5" colspan="2">
     			<input type="hidden" name="packname" value="<%=m_strPackName%>"/>
     			<input type="hidden" name="lotid" value="<%=lotid%>"/>
     		</td>
   		</tr>
        
        <tr>
          <td align="right" class="yx1_top"><div id="lotatt001" align="right">批次属性1：</div></td>
          <td class="yx_top"><div id="lotvalue001" align="left">
          	<input type="text" name="lotatt1" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1_top"><div id="lotatt002" align="right">批次属性2：</div></td>
          <td class="yx_top"><div id="lotvalue002" align="left">
          	<input type="text" name="lotatt2" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
         </tr>
        <tr>
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
        </tr>
        <tr>
          <td align="right" class="yx1"><div id="lotatt005" align="right">批次属性5：</div></td>
          <td class="yx"><div id="lotvalue005" align="left">
          	<input type="text" name="lotatt5" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
         </tr>
        <tr>
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
        </tr>
        <tr>
          <td align="right" class="yx1"><div id="lotatt008" align="right">批次属性8：</div></td>
          <td class="yx"><div id="lotvalue008" align="left">
          	<input type="text" name="lotatt8" size="16"    style="height:18px;width:100px;"/></div> 
           </td>
        </tr>
        <tr>
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
         </tr>
        <tr>
          <td align="right" class="yx1"><div id="lotatt011" align="right">批次属性11：</div></td>
          <td class="yx"><div id="lotvalue011" align="left">
          	<input type="text" name="lotatt11" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
         </tr>
        <tr>
          <td align="right" class="yx1"><div id="lotatt012" align="right">批次属性12：</div></td>
          <td class="xx1" ><div id="lotvalue012" align="left">
          	<input type="text" name="lotatt12" size="16" style="height:18px;width:100px;"/></div>
          </td>
        </tr>
      </table>
		 <!-- ======== 分页标签 ======== -->
	<%@ include file="include/page5.jsp" %>
</body>
</html>

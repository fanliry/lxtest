<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%@ page import="java.util.List" %>
<%
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	
	HashMap<String, List> hsLot = null;	//所有要显示的批次
	List<BaseLotSet> lsLot = null;  	//显示的批次属性列表
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("shtrans");//新建收货单->显示收货单详细	
		}
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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<style>
html { overflow-y:hidden; }
</style>


</head>

<body >
 <table width="150%" height="143" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST3">
   <tr>
     <td class="TD_LIST_TITLE11" align="center" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">跟踪号</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">状态</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">包装</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">收货人</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">数量</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">净重</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">毛重</div></td>
<%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE3" align="center"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>     
     <td class="TD_LIST_TITLE21" align="center"><div class="list_title">货主</div></td>
   </tr>
<%
   	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2);  
		
		InboundReceiptTransaction receiptTrans = null; 
		
		String transid;		   //交易号
      	String productid;      //物品ID
      	String punit;          //单位
      	double num;            //数量
      	double weight;         //重量
      	double netweight;      //净重   
      	
      	String m_strProductName;// 产品
      	String m_strPackName;	// 包装
      	String receiptmanname;	// 收货人
      	String ownername;		// 货主
      	
      	String strStatusName = "";	// 状态名

		for(int i = 0; i < ls.size(); i++)
		{
			receiptTrans = (InboundReceiptTransaction)ls.get(i);
		
			transid = receiptTrans.getTransreceiptid();	//交易号
      	 	productid = receiptTrans.getProductid();    //物品ID
      	 	punit = receiptTrans.getM_strUnitName();    //单位
      	 	num = receiptTrans.getRecnum();            	//数量
      	 	weight = receiptTrans.getReweight();        //重量
      	 	netweight = receiptTrans.getRenetweight();  //净重   
      	
      	 	m_strProductName = receiptTrans.getM_strProductName();// 产品
      	 	m_strPackName = receiptTrans.getM_strPackName();	  // 包装
      	 	receiptmanname = receiptTrans.getReceiptmanname();	  // 收货人
      	 	ownername = receiptTrans.getOwnername();		      // 货主
			strStatusName = receiptTrans.getStrStatusName();
			
      	 	
		
%>
   <tr class="list_normal_tr" onmouseover="this.style.backgroundColor='#CCFF00'" onmouseout="this.style.backgroundColor=''" onclick="">
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=transid%></td>
     <td class="TD_LIST" align="center"><%=strStatusName%></td>
     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
     <td class="TD_LIST" align="center"><%=m_strPackName%></td>
     <td class="TD_LIST" align="center"><%=punit%></td>
     <td class="TD_LIST" align="center"><%=receiptmanname%></td>
     
     <td class="TD_LIST" align="center"><%=nbf.format(num)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td> 
     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td> 
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);
			field = receiptTrans.getClass().getDeclaredField(lotSet.getLotid());					
%>
	 		 <td class="TD_LIST" align="center"><%=(String)field.get(receiptTrans)%></td>
<%
		}
	}
%>	     
     <td class="TD_LIST2" align="center"><%=ownername%></td>

   </tr>

<%
	      	 
		}
		
	}else
	{
		session.removeAttribute("inboundPage2");
	}
%>
 <tr>
     <td class="TD_last_LIST" height="100%" colspan="<%=11 + iLine%>" valign="top">
     <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
     </td>
   </tr>
 </table>
 
 <!-- ======== 分页标签 ======== -->
	<%@ include file="include/page2.jsp" %>
</body>
</html>

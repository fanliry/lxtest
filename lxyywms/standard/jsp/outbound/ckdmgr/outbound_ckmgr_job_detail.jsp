<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%
   
    //表中的列排序
	OutboundInvoiceDetail exdetilz = (OutboundInvoiceDetail)request.getAttribute("exdetil");

%>
<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	
</SCRIPT>
</head>

<body>

  <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   
   <tr>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">出库单据编号</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">状态</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">生产日期</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单毛重</div></td>
     
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配毛重</div></td>
     
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">发货数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">发货毛重</div></td>
    
   </tr>
<%
	
	
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail outDetail = null;//出库单详细  
		
		String outstockdetailid;//出库单详细ID
      	String outstockid;		//出库单据编号
      	String productCode; //产品编码
      	String productid;		//品名
      	String packid;          //包装 
        String eunit;           //单位 
      	double invoicenum;		//数量
	  	double weight;			//重量
      	double netweight;		//净重
      	
      	
      	double assnum;			//分配数量
      	double assweight;		//分配重量
      	double assnetweight;	//分配净重
      	
      	double sendnum;         //发货数量
   		double snetweight;      //发货重重
    	double sweight;         //发货净量

   
        String m_strStatusName;     // 状态名
        String m_strProductName;    // 产品
      	String m_strPackName;       // 包装名称
        String m_strUnitName;       // 单位名称
        
        String printdate; //生产日期
        String lotinfo; //批号
      	
    	String traycode;            //托盘条码
	
			outDetail = exdetilz;
			
			 outstockdetailid = outDetail.getOutstockdetailid();//出库单详细ID
	      	 outstockid = outDetail.getOutstockid();			//出库单据编号
	      	 productCode = outDetail.getM_strProductCode();//产品编码
	      	 productid = outDetail.getProductid();			//品名
	      	 invoicenum = outDetail.getInvoicenum();		//数量
		  	 weight = outDetail.getWeight();				//重量
	      	 netweight = outDetail.getNetweight();			//净重
	      	 
	      	 packid = outDetail.getPackid();              	/* 包装 */
         	 eunit  = outDetail.getPkgunit();               /* 单位 */
	      	 assnum = outDetail.getAssignnum();				//分配数量
      	 	 assweight = outDetail.getAssignweight();		//分配重量
      	 	 assnetweight = outDetail.getAssignnetweight();	//分配净重

 
         	m_strStatusName = outDetail.getM_strStatusName();   // 状态名
         	m_strProductName = outDetail.getM_strProductName(); // 产品
         	
         	m_strPackName = outDetail.getM_strPackName();       //包装名称
         	m_strUnitName = outDetail.getM_strUnitName();       //单位名称 
         	
         	printdate= outDetail.getPrintdate();
         	lotinfo = outDetail.getLotinfo();
         	
         	sendnum = outDetail.getSendnum();				//发货数量
      	 	sweight = outDetail.getSweight();				//发货重量
      	 	snetweight = outDetail.getSnetweight();		//发货净重
      	 	
        	traycode = outDetail.getTraycode();            //托盘条码
 %>
	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST" align="center"><%=outstockid%></td>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=productCode%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
		     
		     <td class="TD_LIST" align="center"><%=traycode == null ? "" : traycode%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(invoicenum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(weight)%></span></td>
		     
		     
		     <td class="TD_LIST" align="center"><span style="color: green; "><%=nbf.format(assnum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: green; "><%=nbf.format(assweight)%></span></td>
		     
		     
		     <td class="TD_LIST" align="center"><%=nbf.format(sendnum)%></td>
             <td class="TD_LIST" align="center"><%=nbf.format(sweight)%></td>
             
	</tr>	
	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>   
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
	 

 
 </table>
</body>
</html>

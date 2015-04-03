<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%
	HashMap<String, List> hsLot = null;	//所有要显示的批次
	List<BaseLotSet> lsLot = null;  	//显示的批次属性列表
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newckd");//新建出库单->显示出库单详细	
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

<script>
	var oldobj = null;
	function Change(obj)
	{
		obj.style.backgroundColor = "#33FF33";
		if(oldobj != null){
			oldobj.style.backgroundColor = "";	
		}
		oldobj = obj;
	}
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#33FF33";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
</script>

</head>

<body>
 <div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="150%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">状态</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">包装</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">拣货数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">拣货毛重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">拣货净重</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单毛重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单净重</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配毛重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配净重</div></td>
<%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>
   </tr>
<%
	List ls = (List)request.getAttribute("invoicedetail");
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail outDetail = null;//出库单详细  
		
		String outstockdetailid;//出库单详细ID
      	String outstockid;		//出库单据编号
      	String productid;		//品名
      	String packid;          //包装 
        String eunit;           //单位 
      	double invoicenum;		//数量
	  	double weight;			//重量
      	double netweight;		//净重
      	
      	
      	double assnum;			//分配数量
      	double assweight;		//分配重量
      	double assnetweight;	//分配净重
      	
      	double picknum;			//拣货数量
      	double pickweight;		//拣货重量
      	double picknetweight;	//拣货净重
   
        String m_strStatusName;     // 状态名
        String m_strProductName;    // 产品
      	String m_strPackName;       // 包装名称
        String m_strUnitName;       // 单位名称
      	
		for(int i = 0; i < ls.size(); i++)
		{
			outDetail = (OutboundInvoiceDetail)ls.get(i);
			
			 outstockdetailid = outDetail.getOutstockdetailid();//出库单详细ID
	      	 outstockid = outDetail.getOutstockid();			//出库单据编号
	      	 
	      	 productid = outDetail.getProductid();			//品名
	      	 invoicenum = outDetail.getInvoicenum();		//数量
		  	 weight = outDetail.getWeight();				//重量
	      	 netweight = outDetail.getNetweight();			//净重
	      	 
	      	 packid = outDetail.getPackid();              	/* 包装 */
         	 eunit  = outDetail.getPkgunit();               /* 单位 */
	      	 assnum = outDetail.getAssignnum();				//分配数量
      	 	 assweight = outDetail.getAssignweight();		//分配重量
      	 	 assnetweight = outDetail.getAssignnetweight();	//分配净重
      	 	 
      	 	 picknum = outDetail.getPicknum();				//拣货数量
      	 	 pickweight = outDetail.getPickweight();		//拣货重量
      	 	 picknetweight = outDetail.getPicknetweight();	//拣货净重

         	m_strStatusName = outDetail.getM_strStatusName();   // 状态名
         	m_strProductName = outDetail.getM_strProductName(); // 产品
         	
         	m_strPackName = outDetail.getM_strPackName();       //包装名称
         	m_strUnitName = outDetail.getM_strUnitName();       //单位名称 
 %>
	         <tr class="list_normal_tr" onmouseover="this.style.backgroundColor='#CCFF00'" onmouseout="this.style.backgroundColor=''" onclick="Change(this)">
		     <td class="TD_LIST" align="center"><input onClick="Change(this)" type="radio" name="check_id" class="input_checkbox" value="<%=outstockdetailid%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=m_strPackName == null ? "" : m_strPackName%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     
		     <td class="TD_LIST" align="center"><span style="color: red; "><%=nbf.format(picknum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: red; "><%=nbf.format(pickweight)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: red; "><%=nbf.format(picknetweight)%></span></td>
		     
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(invoicenum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(weight)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(netweight)%></span></td>
		     
		     <td class="TD_LIST" align="center"><span style="color: green; "><%=nbf.format(assnum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: green; "><%=nbf.format(assweight)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: green; "><%=nbf.format(assnetweight)%></span></td>
		   
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);
			field = outDetail.getClass().getDeclaredField(lotSet.getLotid());					
%>
	 		 <td class="TD_LIST" align="center"><%=(String)field.get(outDetail)%></td>
<%
		}
	}
%>		     
		   </tr>	       	 
<%
	      	 
		}
		
	}
%>

   <tr>
      <td height="100%" colspan="<%=14 + iLine%>" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
  
 </table>
 </div>
</body>
</html>

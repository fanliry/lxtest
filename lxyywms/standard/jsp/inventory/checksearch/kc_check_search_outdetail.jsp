<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%
	HashMap<String, List> hsLot = null;		//所有要显示的批次
	List<BaseLotSet> lsLot = null;  		//显示的批次属性列表
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newckd");//新建出库单->显示出库单详细	
		}
	}
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

	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			
			//改变背景色
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//设置多选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
	}
	
	function OnLoad(){
	
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
 <div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table id="theTable" width="130%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">行状态</div></td> 
     <td class="TD_LIST_TITLE"><div class="list_title">包装</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
<%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单毛重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单净重</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配毛重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配净重</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">发货数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">发货毛重</div></td>
     <td class="TD_LIST_TITLE2" align="center"><div class="list_title">发货净重</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size()>0){
	
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail detail = null;	//出库单详细  
		
		String outstockdetailid;	// 出库单详细ID
      	String outstockid;			// 出库单据编号
      	String linestatus;     		// 行状态名
        String product;    			// 产品
      	String pack;       			// 包装名称
        String unit;       			// 单位名称
        
      	double invoicenum;		//数量
	  	double weight;			//重量
      	double netweight;		//净重
      	double assnum;			//分配数量
      	double assweight;		//分配重量
      	double assnetweight;	//分配净重
		double sendnum;         //发货数量
   		double snetweight;      //发货重重
    	double sweight;         //发货净量
        
		
		for(int i = 0; i < ls.size(); i++){
			detail = (OutboundInvoiceDetail)ls.get(i);
			
			outstockdetailid = detail.getOutstockdetailid();//出库单详细ID
	      	outstockid = detail.getOutstockid();			//出库单据编号
	      	linestatus = detail.getM_strStatusName();   	//状态名
         	product = detail.getM_strProductName(); 		// 产品
         	pack = detail.getM_strPackName();       		//包装名称
         	unit = detail.getM_strUnitName();       		//单位名称  
         	
	      	invoicenum = detail.getInvoicenum();		//数量
		  	weight = detail.getWeight();				//重量
	      	netweight = detail.getNetweight();			//净重
	      	
	      	assnum = detail.getAssignnum();				//分配数量
      	 	assweight = detail.getAssignweight();		//分配重量
      	 	assnetweight = detail.getAssignnetweight();	//分配净重
      	 	
      	 	sendnum = detail.getSendnum();				//发货数量
      	 	sweight = detail.getSweight();				//发货重量
      	 	snetweight = detail.getSnetweight();		//发货净重
         	
		
%>
  <!-- onclick="setSel(<%=i%>)" -->
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" >
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=linestatus==null?"":linestatus%></td>
     <td class="TD_LIST" align="center"><%=pack==null?"":pack%></td>
     <td class="TD_LIST" align="center"><%=unit==null?"":unit%></td>
 <%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = detail.getClass().getDeclaredField(lotSet.getLotid());			
%>
	 		 <td class="TD_LIST" align="center"><%=field.get(detail)==null?"":(String)field.get(detail)%></td>
<%
		}
	}
%>    
     <td class="TD_LIST" align="center"><%=nbf.format(invoicenum)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
     
      <td class="TD_LIST" align="center"><%=nbf.format(assnum)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(assweight)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(assnetweight)%></td>
     
      <td class="TD_LIST" align="center"><%=nbf.format(sendnum)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(sweight)%></td>
     <td class="TD_LIST2" align="center"><%=nbf.format(snetweight)%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="<%=14 + iLine%>" class="TD_last_LIST" valign="top">
     	<div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
     </td>
   </tr>
   </tbody> 
 </table>
 </div>
</body>
</html>
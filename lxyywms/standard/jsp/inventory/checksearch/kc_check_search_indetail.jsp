<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundInvoiceDetail" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%
	HashMap<String, List> hsLot = null;		//所有要显示的批次
	List<BaseLotSet> lsLot = null;  		//显示的批次属性列表
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newin");	//新建收货单->显示收货单详细	
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
 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">
     	<input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号</div>
     </td>
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
     <td class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">净重</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">收货单号</div></td>
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
	
		InboundInvoiceDetail detail = null;
		String instockdetailid = null;//入库单详细ID
		String product = null;		// 品名
		String linestatus = null;   //单据行状态0-新建 5-作废
		String pack = null;			// 包装
		String eunit = null;		// 单位
		double  invoicenum = 0;		// 数量
		double netweight = 0;		// 净重
		String reincoiceid = null;  // 收货单号
		
		String lotatt1;  			// 批次属性1
     	String lotatt2;  			// 批次属性2
     	String lotatt3;  			// 批次属性3
     	String lotatt4;  			// 批次属性4
     	String lotatt5;  			// 批次属性5
     	String lotatt6;  			// 批次属性6
     	String lotatt7;  			// 批次属性7
     	String lotatt8;  			// 批次属性8
     	String lotatt9;  			// 批次属性9
     	String lotatt10;  			// 批次属性10
     	String lotatt11;  			// 批次属性11
     	String lotatt12;  			// 批次属性12
		
		for(int i = 0; i < ls.size(); i++){
			detail = (InboundInvoiceDetail)ls.get(i);
			
			instockdetailid = detail.getInstockdetailid();	// 入库单详细ID
			product = detail.getM_strProductName();			// 品名
			linestatus = detail.getM_strStatusName();   	//单据行状态0-新建 5-作废
			pack = detail.getPkgdesc();				// 包装
			eunit = detail.getEunit();				// 单位
			invoicenum = detail.getInvoicenum();	// 数量
			netweight = detail.getNetweight();		// 净重
			reincoiceid = detail.getReincoiceid();	// 收货单号
			
			lotatt1 = detail.getLotatt1();  		// 批次属性1
     	 	lotatt2 = detail.getLotatt2();  		// 批次属性2
     	 	lotatt3 = detail.getLotatt3();			// 批次属性3
     	 	lotatt4 = detail.getLotatt4();  		// 批次属性4
     	 	lotatt5 = detail.getLotatt5();  		// 批次属性5
     	 	lotatt6 = detail.getLotatt6();  		// 批次属性6
     	 	lotatt7 = detail.getLotatt7();  		// 批次属性7
     	 	lotatt8 = detail.getLotatt8();  		// 批次属性8
     	 	lotatt9 = detail.getLotatt9();  		// 批次属性9
     	 	lotatt10 = detail.getLotatt10();  		// 批次属性10
     	 	lotatt11 = detail.getLotatt11();  		// 批次属性11
     	 	lotatt12 = detail.getLotatt12();  		// 批次属性12
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center" width="50px">
     	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=instockdetailid%>"><%=i+1%>
     </td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=linestatus==null?"":linestatus%></td>
     <td class="TD_LIST" align="center"><%=pack==null?"":pack%></td>
     <td class="TD_LIST" align="center"><%=eunit==null?"":eunit%></td>
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
     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
     <td class="TD_LIST2" align="center"><%=reincoiceid==null?"":reincoiceid%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="<%=8 + iLine%>" class="TD_last_LIST"></td>
   </tr>
   </tbody> 
 </table>

</body>
</html>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%
   
    //表中的列排序
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
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
	//双击行显示显示单据明细所对应的作业详细
	function upd(i){
		var check_ids = document.getElementsByName("check_id");
		var id = check_ids[i].value;
		var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	    showModalDialog(ac + "outBoundAction&method=ricoExecSearchRkd&flag=1&outstockdetailid=" + id, "", "dialogWidth=900px; dialogHeight=450px; scroll=auto");
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
</script>

</head>

<body onload="javascript:OnLoad();">
 <div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="100%" height="100%"   id="table"  border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">状态</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">进场编号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">发货数量</div></td>
    
   </tr>
<%
	
	if(ls != null && ls.size() > 0) 
	{
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
      	
		for(int i = 0; i < ls.size(); i++)
		{
			outDetail = (OutboundInvoiceDetail)ls.get(i);
			
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
 %>
	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)" >
     <td class="TD_LIST1" align="center" width="50px">
       <input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=outstockdetailid%>"><%=i+1%>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=productCode%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
		     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(invoicenum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: green; "><%=nbf.format(assnum)%></span></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(sendnum)%></td>
		   </tr>	       	 
<%
		}
	}
%>

   <tr>
      <td height="100%" colspan="10" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
  
 </table>
 </div>
</body>
</html>

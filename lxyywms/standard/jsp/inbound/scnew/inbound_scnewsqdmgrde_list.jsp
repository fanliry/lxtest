<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail" %>
<%@ page import="java.util.HashMap" %>
<%
   
    //表中的列排序
	List ls = (List)request.getAttribute("invoicedetail");
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>
	function Change(obj)
	{
		obj.style.backgroundColor = "#33FF33";
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
	function OnLoad(){
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('table',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
</script>

</head>

<body  onload="javascript:OnLoad();">
 <table width="100%" height="100%" id="table" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">产品名称</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">计划数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">绑定数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">具体批号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">物品状态</div></td>
   </tr>
<%
	ls = (List)request.getAttribute("invoicedetail");
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InboundRequestInvoiceDetail inDetail = null;//入库单明细 
		String instockdetailid;	//入库申请单明细id
      	String instockid;		//入库申请单号
      	String productid;		//品名id
      	String printdate;		//生产日期
	  	double plannum;			//计划数量
      	double bindingnum;		//绑定数量
      	String lotinfo;         //批号信息
      	String productCode;		//产品编码
      	String productName;     //产品名称
      	String productStatus;	//物品状态
        String m_strUnitName="";    //单位
      	
		for(int i = 0; i < ls.size(); i++)
		{
			 inDetail = (InboundRequestInvoiceDetail)ls.get(i);
			 instockdetailid = inDetail.getInstockdetailid();	
			 instockid = inDetail.getInstockid();	
			 productid = inDetail.getProductid();	
			 printdate = inDetail.getPrintdate();	
			 plannum = inDetail.getPlannum();	
			 bindingnum = inDetail.getBindingnum();	
			 lotinfo = inDetail.getLotinfo();	
			 productName = inDetail.getProductName();
			 productCode = inDetail.getProductCode();
			 productStatus = inDetail.getProductStatusName();
			 m_strUnitName = inDetail.getPunit();
 %>
	         <tr class="list_normal_tr" onmouseover="this.style.backgroundColor='#CCFF00'" onmouseout="this.style.backgroundColor=''" onclick="Change(this)">
		     <td class="TD_LIST" align="center"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=productCode == null ? "" : productCode%></td>
		     <td class="TD_LIST" align="center"><%=productName == null ? "" : productName%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
		     <td class="TD_LIST" align="center"><%=plannum%></td>
		     <td class="TD_LIST" align="center"><%=bindingnum%></td>  
		     <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
		     <td class="TD_LIST" align="center"><%=productStatus == null ? "" : productStatus%></td> 
		   </tr>	       	 
<%
	      	 
		}
	}
%>
   <tr>
      <td height="100%" colspan="7" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
 </table>
</body>
</html>

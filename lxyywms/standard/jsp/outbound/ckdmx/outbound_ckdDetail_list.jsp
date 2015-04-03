<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob"%>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail"%>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>
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
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){		
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   new tableSort('table',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
</script>

</head>

<body onload="javascript:OnLoad();">
 <div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="100%" height="100%"  id="table" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">物品状态</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单重量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配重量</div></td>
    
   </tr>
<%
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InoutJob job = null;	//入库申请单详细  
		InoutJobdetail jobDetail = null;
        
		
      	String productCode; //产品编码
      	String productName;//产品名称
      	String productid;		//品名id
      	String productDate;//生产日期
      	String lotInfo;//批号
      	String punit;           //单位 
      	String traycode;//托盘条码
      	String productStatus;//物品状态
      	double invoicenum;		//数量
	  	double weight;			//重量
      	
      	double assnum;			//分配数量
      	double assweight;		//分配重量
      	
		
		for(int i = 0; i < ls.size(); i++)
		{
			Object[] obj = (Object[])ls.get(i);
			job = (InoutJob)obj[0];
			jobDetail = (InoutJobdetail)obj[1];
			
			productCode =  jobDetail.getProductcode();//产品编码
			productName = jobDetail.getM_strProductName();//品名
			productDate = jobDetail.getPrintdate();//生产日期
			lotInfo = jobDetail.getLotinfo();//批号
			punit = jobDetail.getPunit();//单位
			traycode = job.getTraycode();//托盘条码
			productStatus = jobDetail.getProductStatusName();//物品状态
			invoicenum = jobDetail.getJobnum();//开单数量
			weight = jobDetail.getWeight();//重量
			assnum = jobDetail.getAssignnum();//分配数量
			assweight = jobDetail.getAssignweight();//分配重量
			
		  	
 %>
	         <tr onmouseover="this.bgColor='#CCFF00'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="<%=jobDetail.getJobdetailid()%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=productCode==null?"":productCode%></td>
		     <td class="TD_LIST" align="center"><%=productName==null?"":productName%></td>
		     <td class="TD_LIST" align="center"><%=productDate==null?"":productDate%></td>
		     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
		     <td class="TD_LIST" align="center"><%=lotInfo==null?"":lotInfo%></td>
		     <td class="TD_LIST" align="center"><%=punit==null?"":punit%></td>
		      <td class="TD_LIST" align="center"><%=productStatus==null?"":productStatus%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(invoicenum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(assnum)%></td>
		      <td class="TD_LIST" align="center"><%=nbf.format(assweight)%></td>	 	     
		   </tr>	       	 
<%
	      	 
		}
		
	}
%>

   <tr>
      <td height="100%" colspan="9" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
  
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
 </div>
</body>
</html>

<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundCrosscheck" %>



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

	function OnLoad(){
		parent.UnLockButton();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
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

	<table width="100%" height="215" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST3">
   <tr>
     <td class="TD_LIST_TITLE11" align="center" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">作业详细号</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">产品</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">复核数量</div></td>
     <td class="TD_LIST_TITLE21" align="center"><div class="list_title">&nbsp;</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		OutboundCrosscheck crosscheck = null; 

      	//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2);  
		
		String id;//ID
		String jobdetailid;	   //作业详细号
      	String productid;      //物品ID
      	String m_strProductName;// 产品 	
      	double num;            //数量

		for(int i = 0; i < ls.size(); i++)
		{
			crosscheck = (OutboundCrosscheck)ls.get(i);
			
			id = crosscheck.getCrosscheckid();
			jobdetailid = crosscheck.getJobdetailid();//作业详细号
      	 	num = crosscheck.getQtyscan();            //数量
      	 	m_strProductName = crosscheck.getM_strProductName();// 产品


 %>
	         <tr class="list_normal_tr" onmouseover="this.bgColor='#CCFF00'" onmouseout="bgColor=''">
		     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=id%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=jobdetailid%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><font color="red" style="font-weight: bold"><%=nbf.format(num)%></font></td>	     
     		<td class="TD_LIST2" align="center">&nbsp;</td>

		   </tr>	       	 
<%
	      	 
		}
		
	}else
	{
		session.removeAttribute("outboundPagereview");
	}
%>

   <tr>
     <td class="TD_last_LIST" height="100%" colspan="<%=5%>" valign="top">
     	<div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
     </td>
   </tr>
  
 </table>
 <!-- ======== 分页标签 ======== -->
	<%@ include file="include/pagereview.jsp" %>
	
	
	
</body>
</html>

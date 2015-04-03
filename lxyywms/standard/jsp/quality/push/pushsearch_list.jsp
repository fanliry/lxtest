<%@page import="com.wms3.bms.standard.entity.quality.Release"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap" %>
<%
	String strTime = StrTools.getCurrDateTime(8);
	String strWarehouseId = (String) request.getSession(false).getAttribute("warehouseid");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>浙江刚玉物流仓库管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script>	
	
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != ""){
		 	if(back_msg != "Y")
		 	{
			    alert(back_msg);
		 	}else
		 	{
		 		alert("操作成功");
		 	}
		}
	}

</script>
</head>
<body   onload="javascript:OnLoad();">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table id="ty" width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
  
   <tr height="10px">
     <td width="50" class="TD_LIST_TITLE"align="center"><div class="list_title">行号
     </div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">进厂编号</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">产品编码</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">质检单号</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">放行时间</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">操作人</div></td>
   </tr>
   <%
   		List ls = (List)request.getAttribute("exResultList");
   
   		Release stask = null;
		String Releaseid;       //放行ID
	 	String Productid;      	//物品ID
	 	String lotinfo;			//具体批号 (进厂编号)
	 	String Qualityid ;		//质检单号
	 	String createtime; 		//放行时间   时间格式：yyyy-MM-dd hh:mm:ss
	 	String opManId;    		//操作人
	 	
		//派生属性
	    String Productcode;     //产品编码
	    String ProductName;   	//产品名称
    
   		if(ls != null && ls.size() > 0)
   		{
   			for(int i = 0; i < ls.size(); i++)
   			{
   				Release release = (Release)ls.get(i);
   				lotinfo = release.getLotinfo();
   				ProductName = release.getProductName();
   				Productcode = release.getProductcode();
   				Qualityid = release.getQualityid();
   				createtime = release.getCreatetime();
   				opManId = release.getOpManId();

    %>
   <tr onclick="">
	<td class="TD_LIST1" align="center"><%=i+1%></td>
   	<td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
   	<td class="TD_LIST" align="center"><%=ProductName == null ? "" : ProductName%></td>
   	<td class="TD_LIST" align="center"><%=Productcode == null ? "" : Productcode%></td>
   	<td class="TD_LIST" align="center"><%=Qualityid == null ? "" : Qualityid%></td>
   	<td class="TD_LIST" align="center"><%=createtime == null ? "" : createtime%></td>
   	<td class="TD_LIST" align="center"><%=opManId == null ? "" : opManId%></td>

   </tr>
   <%
   			}
   		}else{
			session.removeAttribute("paging");
		}
    %>
    <tr>
     <td height="100%" colspan="20" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
 </div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>

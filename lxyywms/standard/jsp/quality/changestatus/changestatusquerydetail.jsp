<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryQualityResultDetail" %>
<%@ page import="com.ricosoft.common.dao.dataSource.EntityDAO" %>
<%@ page import="com.wms3.bms.standard.service.BMSService" %>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%
	EntityDAO dao = BMSService.getm_EntityDAO();
%>
<html>
<head>
  <title>仓储配送管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/commonHandle.js"></script>
  <script type="text/javascript">
  </script>
</head>

<body>
<%
	List list = null;
	if(request.getAttribute("exResultList") != null) 
	{
		list = (List)request.getAttribute("exResultList");
	}
%>
<form id="myform" name="myform" method="post" action="">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr class="list_title_tr">
	  <td  class="TD_LIST_TITLE"><div class="list_title">行号</div></td> 
	  <td  class="TD_LIST_TITLE"><div class="list_title">状态转换单号</div></td>	
	  <td  class="TD_LIST_TITLE"><div class="list_title">产品名</div></td>
	  <td  class="TD_LIST_TITLE"><div class="list_title">创建日期</div></td>
	  <td  class="TD_LIST_TITLE"><div class="list_title">物品新状态</div></td>
	  <td  class="TD_LIST_TITLE"><div class="list_title">物品原状态</div></td>
	  <td  class="TD_LIST_TITLE"><div class="list_title">货位号</div></td>
	  <td  class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
	</tr>
<%
    if(list!=null && !list.isEmpty()){
		InventoryQualityResultDetail detail=null;
        String strDId="";
        String strProductName="";
        String strCreatetime="";
        String strNewProductStatus="";
        String strOldProductStatus="";
        String strClientName="";
        String strCarspaceid="";
        double iProductNum;
        for(int i=0; i<list.size(); i++)
        {
             detail = (InventoryQualityResultDetail)list.get(i); 
             strDId = detail.getM_strCheckResultId();        //状态转换单号
        	 strProductName = detail.getM_strProductName();  //产品名
        	 strCreatetime = detail.getM_strPrintDate();     //创建日期
        	 strNewProductStatus = detail.getM_strNewProductStatusName();	 //新物品状态
        	 strOldProductStatus = detail.getM_strOldProductStatusName();	 //原物品状态
        	 strClientName = detail.getM_strLotNumber();    //批号
        	 strCarspaceid = detail.getM_strCarspaceid();    //货位号
        	 iProductNum = detail.getM_iProductNum();	     //数量
        	 
       	 
%>	
	<tr class="list_normal_tr" >
	  <td class="TD_LIST"><%=i+1%></td>
	  <td class="TD_LIST"><%=strDId == null ? "&nbsp;" : strDId%></td>
	  <td class="TD_LIST"><%=strProductName == null ? "&nbsp;" : strProductName%></td>
	  <td class="TD_LIST"><%=strCreatetime == null ? "&nbsp;" : strCreatetime%></td>
	  <td class="TD_LIST"><%=strNewProductStatus == null ? "&nbsp;" : strNewProductStatus%></td>
	  <td class="TD_LIST"><%=strOldProductStatus == null ? "&nbsp;" : strOldProductStatus%></td>
	  
	  <td class="TD_LIST"><%=strCarspaceid == null ? "&nbsp;" : strCarspaceid%></td>
	  <td class="TD_LIST"><%=iProductNum%></td>  
	</tr>
<%
        }
    }else
	{
		session.removeAttribute("paging");
	}
%>	
	<tr height="100%">
	  <td height="100%" colspan="9" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(list != null && list.size() == 0){%>无相关数据！<%}%></div>
	  </td>
	</tr>
  </table>
</form>
</body>
</html>

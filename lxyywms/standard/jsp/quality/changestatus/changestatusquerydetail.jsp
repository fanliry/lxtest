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
  <title>�ִ����͹���ϵͳ</title>
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
	  <td  class="TD_LIST_TITLE"><div class="list_title">�к�</div></td> 
	  <td  class="TD_LIST_TITLE"><div class="list_title">״̬ת������</div></td>	
	  <td  class="TD_LIST_TITLE"><div class="list_title">��Ʒ��</div></td>
	  <td  class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
	  <td  class="TD_LIST_TITLE"><div class="list_title">��Ʒ��״̬</div></td>
	  <td  class="TD_LIST_TITLE"><div class="list_title">��Ʒԭ״̬</div></td>
	  <td  class="TD_LIST_TITLE"><div class="list_title">��λ��</div></td>
	  <td  class="TD_LIST_TITLE"><div class="list_title">����</div></td>
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
             strDId = detail.getM_strCheckResultId();        //״̬ת������
        	 strProductName = detail.getM_strProductName();  //��Ʒ��
        	 strCreatetime = detail.getM_strPrintDate();     //��������
        	 strNewProductStatus = detail.getM_strNewProductStatusName();	 //����Ʒ״̬
        	 strOldProductStatus = detail.getM_strOldProductStatusName();	 //ԭ��Ʒ״̬
        	 strClientName = detail.getM_strLotNumber();    //����
        	 strCarspaceid = detail.getM_strCarspaceid();    //��λ��
        	 iProductNum = detail.getM_iProductNum();	     //����
        	 
       	 
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
	    <div style="color: red; margin:5px;"><%if(list != null && list.size() == 0){%>��������ݣ�<%}%></div>
	  </td>
	</tr>
  </table>
</form>
</body>
</html>

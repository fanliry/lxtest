<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage" %>

<html>
<head>
<title>��λ����-��Ʒ�б�</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
	table{
		table-layout:fixed;
	}
	td{
		word-break:keep-all;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	} 
-->
</style>
</head>
<body>
<%
	List list = null;
	String isInit = "no";//��ʶ��λ�Ƿ��Ѿ�����ʼ����Ĭ��λû��
	if(request.getAttribute("result") != null)
	{
		list = (List)request.getAttribute("result");
	}
%>
<form name="myform" method="post">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="40%"><div align="center">Ʒ�����</div></td>
      <td class="TD_LIST_TITLE" width="25%"><div align="center">������Ϣ</div></td>
      <td class="TD_LIST_TITLE" width="15%"><div align="center">����</div></td>
      <td class="TD_LIST_TITLE2" width="20%"><div align="center">��λ</div></td>
    </tr>
   <%
    if(list!=null && !list.isEmpty()){
        for(int i=0; i<list.size(); i++){
        
        	InventoryStorage storage = (InventoryStorage)list.get(i); 
        	
			String productName = storage.getProductName();	//��Ʒ���� Ʒ�����
			String lot = storage.getLotinfo(); 	//������Ϣ
			int pnum = (int)storage.getPnum(); //����
            String unit = storage.getPunitname(); //��λ
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"><%=productName==null ? "&nbsp;":productName%></td>
     <td class="TD_LIST" align="center"><%=lot==null ? "&nbsp;":lot%></td>
     <td class="TD_LIST" align="center"><%=pnum%></td>
     <td class="TD_LIST2" align="center"><%=unit==null ? "&nbsp;":unit%></td>
   </tr>
<%
        }
    }
%>
   <tr>
     <td height="100%" colspan="4" class="TD_ADD"></td>
   </tr>
 </table>
<input type="hidden" name="isInit" value="">
</form>
</body>
</html>
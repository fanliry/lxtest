<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementHeader" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementDetail" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseLotSet"%>
<%@page import="java.lang.reflect.Field"%>
<%@page import="java.util.ArrayList"%>
<%
   
    //���е�������
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">
<!--
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="100%" id="theTable" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
   	
     <td class="TD_LIST_TITLE"><div class="list_title">�к�</div></td>
    
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���̺�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     
     
     
     <td class="TD_LIST_TITLE"><div class="list_title">FM����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM��λ</div></td>
  
     <td class="TD_LIST_TITLE"><div class="list_title">TO����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO��λ</div></td>
     
     
   </tr>
<%
	
	if(ls != null && ls.size() > 0){
		InventoryMovementDetail iDetail = null;
		String strWarehouseName = null;	//�ֿ�����
		String strProductCode = null; 	//��Ʒ���
		String strProductName = null; 	//Ʒ��
		String strProductDate = null;	//��������
		String strLotNum = null;	//����
		String strTrayNum = null;	//���̺�
		String strPunit = null; //��λ
		String strCreateTime = null;	//�Ƶ�ʱ��
		String strFromWhare = null;	//FM����
		String strFromCargospace = null; //FM��λ
		
		String strToWhare = null;	//to����
		String strToCargospace = null; //to��λ
		String strCreateMan = null; 	//�Ƶ���
		
		
		for(int i = 0; i< ls.size(); i++){
			iDetail = (InventoryMovementDetail)ls.get(i);
			
			strProductCode = iDetail.getProductid();
			strProductName = iDetail.getProductName();
			strProductDate = iDetail.getProductDate();
			strLotNum = iDetail.getLotNum();
			strTrayNum = iDetail.getTrayCode();
			strPunit = iDetail.getMeter();
			
			
			strFromWhare = iDetail.getFromAreName();
			
			strFromCargospace = iDetail.getFromCargospace();
			strToWhare = iDetail.getToAreName();
			strToCargospace = iDetail.getToCargospace();
			

	
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><%=i+1%></td>
     <td class="TD_LIST"><%=strProductCode%></td>
     <td class="TD_LIST" align="center"><%=strProductName%></td>
     <td class="TD_LIST" align="center"><%=strProductDate%></td>
     <td class="TD_LIST" align="center"><%=strLotNum%></td>
     <td class="TD_LIST" align="center"><%=strTrayNum%></td>
     <td class="TD_LIST" align="center"><%=strPunit%></td>
     <td class="TD_LIST" align="center"><%=strFromWhare%></td>
     <td class="TD_LIST" align="center"><%=strFromCargospace%></td>
     <td class="TD_LIST" align="center"><%=strToWhare%></td>
     <td class="TD_LIST" align="center"><%=strToCargospace%></td>
   </tr>
<%
		}
		
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>

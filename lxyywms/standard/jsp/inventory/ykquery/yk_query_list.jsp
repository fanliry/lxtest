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
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			
			//�ı䱳��ɫ
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//���ö�ѡ���Ƿ�ѡ��
	function setSel(strId,ob){
	    
	    var obj = ob.childNodes[0].childNodes[0];
		if(!obj.checked){
			obj.checked = false;
			obj.parentNode.parentNode.style.backgroundColor = "";
			queryDetail(strId,obj.value);
		}else{
			obj.checked = true;
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
			queryDetail(strId,obj.value);
		}
	}
	//��ѯ��ϸ
	function queryDetail(strId,id){
		//alert(strId);
		parent.detail.location.href = ac + "inventoryMoveAction&method=ricoExecQuery&flag=2&id=" + strId;
	}

	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE',true);
		}
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="100%" id="theTable" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
   	
     <td class="TD_LIST_TITLE"><div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�</div></td>
     
     <td class="TD_LIST_TITLE"><div class="list_title">�ֿ�����</div></td>
    
     <td class="TD_LIST_TITLE"><div class="list_title">�Ƶ�ʱ��</div></td>
     
      <td class="TD_LIST_TITLE"><div class="list_title">���ݱ���</div></td>
     
     <td class="TD_LIST_TITLE"><div class="list_title">�Ƶ���</div></td>
     
    
     
   </tr>
<%
	
	if(ls != null && ls.size() > 0){
		
		
		InventoryMovementHeader iHeader = null;
		
		String movementId = null;
		String strWarehouseId=null;//�ֿ�ID
		String strWarehouseName = null;	//�ֿ�����
		
		String strCreateTime = null;	//�Ƶ�ʱ��
		
		String strCreateMan = null; 	//�Ƶ���
		
		
		for(int i = 0; i< ls.size(); i++){
			
			iHeader = (InventoryMovementHeader)ls.get(i);
			
			
			movementId = iHeader.getMovementid();
			strWarehouseId = iHeader.getWarehouseid();
			strWarehouseName = iHeader.getWarehouseName();
			
			strCreateTime = iHeader.getCreateTime();
			
			strCreateMan = iHeader.getCreateMan();

	
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel('<%=movementId%>',this)">
   	
      <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="<%=movementId%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=strWarehouseName %></td>
    
     <td class="TD_LIST" align="center"><%=strCreateTime %></td>
     
     <td class="TD_LIST" align="center"><%=movementId %></td>
     
     <td class="TD_LIST" align="center"><%=strCreateMan %></td>
    
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

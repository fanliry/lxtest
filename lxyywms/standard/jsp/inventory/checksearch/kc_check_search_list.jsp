<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inbound.InboundInvoiceHeader"%>
<%@page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader"%>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask"%>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult"%>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script>
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	function Detail(type, id)
	{
		window.parent.detail.location.href = strUrl + "checkQueryAction&flag=2" + "&type=" + type + "&id=" + id;
	}
	//�ı䱳��
    function Change(obj){
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}
		obj.parentNode.parentNode.style.backgroundColor = "#AFEF6F";
	}
  //��ѯ��ϸ
	function queryDetail(id){
		var flag = parent.document.getElementById("inouttype").value
		parent.detail.location.href = strUrl + "InventoryCheckSearchAction&method=ricoExecSearchDel&flag="+flag+"&instockid=" + id;
	}
	
    function OnLoad(){
		parent.page.location.reload();
    }
-->
</script>
</head>
<!-- onload="javascript:OnLoad();" -->
<body onLoad="OnLoad()">

 <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" ><div class="list_title">ѡ��</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">�ֿ�</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">���뵥��</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">�����</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">��Ʒ��</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">�̵�����</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">����Ա</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">�̵�ʱ��</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">״̬</div></td>
   </tr>
<%
	List<Object[]> objLs = (List<Object[]> )request.getAttribute("objLs");  //����̵㵥

	if(objLs != null && objLs.size() > 0)
   	{   
		InventoryCheckTask checkTask = null;
		InventoryCheckResult checkResult = null;
		Object[] obj = new Object[2];
   		String strWarehouseId = "";		    // �ֿ�
		String strCheckReq = "";			// �̵�����
		String strCheckTask = "";			// ����
		String strProductName = "";         //��Ʒ��
		String strLotinfo = "";				// ����
		double strpnum =0.0;                 //�������
		double strCheckNum =0.0;            //�̵�����
		String strOpManName = "";			// ����Ա��
		String strOpManTime = "";			// ����ʱ��
		String statusName = "";			    // ״̬
	
   		for(int i = 0; i < objLs.size(); i++)
   		{
   			obj = (Object[])objLs.get(i);
   			checkResult = (InventoryCheckResult)obj[0];  //�̵���
   			checkTask = (InventoryCheckTask)obj[1];//�̵�����
   	   		strWarehouseId = checkTask.getWarehouseid();		    // �ֿ�
   			strCheckReq = checkResult.getRequestid();			// �̵�����
   			strCheckTask = checkResult.getTaskid();			// ����
   			strProductName = checkResult.getProductName();         //��Ʒ��
   			strLotinfo = checkResult.getLotnumber();				// ����
   			strpnum =checkResult.getNum();                 //�������
   			strCheckNum =checkResult.getChecknum();            //�̵�����
   			strOpManName = checkResult.getCreateman();			// ����Ա��
   			strOpManTime =checkResult.getChecktime() ;			// ����ʱ��
   			statusName  = checkResult.getStatusName();
			
%>
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><input name="check_id" type="radio" class="input_checkbox" <%--onclick="queryDetail('<%=strWarehouseId%>');Change(this)"--%>></td>
     <td class="TD_LIST" align="center"><%=strWarehouseId == null ? "" : strWarehouseId%></td>
     <td class="TD_LIST" align="center"><%=strCheckReq == null ? "" : strCheckReq%></td>
     <td class="TD_LIST" align="center"><%=strCheckTask == null ? "" : strCheckTask%></td>
     <td class="TD_LIST" align="center"><%=strProductName == null ? "" : strProductName%></td>
     <td class="TD_LIST" align="center"><%=strLotinfo == null ? "" : strLotinfo%></td>
     <td class="TD_LIST" align="center"><%=strpnum%></td>	
     <td class="TD_LIST" align="center"><%=strCheckNum%></td>	
     <td class="TD_LIST" align="center"><%=strOpManName == null ? "" : strOpManName%></td>	
     <td class="TD_LIST" align="center"><%=strOpManTime == null ? "" : strOpManTime%></td>	
     <td class="TD_LIST" align="center"><%=statusName == null ? "" : statusName%></td>
  </tr>
<%
		}
	}		
	else{
		session.removeAttribute("paging");	
	}
%>
   <tr>
      <td height="100%" colspan="8" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%if((objLs != null && objLs.size() == 0) && (objLs!= null && objLs.size() == 0)){%>��������ݣ�<%}%></div>
      </td>
    </tr>
 </table>

</body>
</html>

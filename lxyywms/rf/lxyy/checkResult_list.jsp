<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.List"%>
<%
	
	String instockid = (String)request.getAttribute("instockid");
	
	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = request.getParameter("warehouseid");
	if(strWarehouseID == null  || strWarehouseID.equals("null")){
		strWarehouseID = (String)session.getAttribute("warehouseid");
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>


<script type="text/javascript">
<!--
//ҳ���½
function onLoad()
{
	var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
	if(back_msg != "")
	{
		if(back_msg == "Y"){
			alert("�����ɹ���");
		}else{
			alert(back_msg);
		}
	}
}
-->
</script>
</head>

<body onload="onLoad();">
<div style="width: 100%; height: 100%;   overflow:auto;">
<table width="100%" border="0" align="center" id="tb" cellpadding="0" cellspacing="0" class="MAIN_TABLE">
   <tr>  
     <td class="TD_LIST_TITLE" align="center" >�̵�����</td>  
     <td class="TD_LIST_TITLE" align="center" >�̵���</td>
     <td class="TD_LIST_TITLE" align="center" >�̵�ʱ��</td>
   </tr>
 <%  List ls = (List)request.getAttribute("exResultList");
	 if(ls != null && ls.size()>0){
			
			//����С��2λ
			NumberFormat nbf=NumberFormat.getInstance();      
			nbf.setMinimumFractionDigits(2);
			nbf.setMaximumFractionDigits(2); 
		
			InventoryCheckResult checkresult = null;
			String resultId ;			//ID
			String product;			//��Ʒ
			String lotnumber;	    //����
			String traycode;		//��������
			double num;				//�������
			double checknum;		//�̵�����
			String checktime;		//�̵�ʱ��
			String createman;		//������
			
			for(int i = 0; i < ls.size(); i++){
			
				checkresult = (InventoryCheckResult)ls.get(i);
				resultId = checkresult.getCheckid();			//ID
				product = checkresult.getProductName();		//��Ʒ
				lotnumber = checkresult.getLotnumber();	    //����
	 		    traycode = checkresult.getTraycode(); 		//��������
				num = checkresult.getNum();					//�������
				checknum = checkresult.getChecknum();		//�̵�����
				checktime = checkresult.getChecktime();		//�̵�ʱ��
				createman = checkresult.getCreateman();		//������
 %>
    <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''" >
    <td class="TD_LIST" align="center"><%=(int)checknum%></td>
    <td class="TD_LIST" align="center"><%=checktime==null?"":checktime%></td>
    <td class="TD_LIST" align="center"><%=createman==null?"":createman%></td>
   </tr>
<%
		}
	}
%> 
 </table>
 </div>
 <div id="info" style="position:absolute; display:none; background-color:#efefff; left:200px; top:200px; z-index: 2;"></div>
</body>
</html>

<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementHeader" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementDetail" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseLotSet"%>
<%@page import="java.lang.reflect.Field"%>
<%@page import="java.util.ArrayList"%>
<%
	String list_items = (String)request.getAttribute("list_items");
	int itemscount = 0;
	String[] items = {""};
	if(list_items != null){
		items = list_items.split(",");
		itemscount = items.length;
	}
	
	
	
	
	InventoryMovementHeader result = (InventoryMovementHeader)request.getAttribute("result"); 
	//String instockid = 	(String)request.getAttribute("instockid"); 

	 String movementId = null;
		String strWarehouseId=null;//�ֿ�ID
		String strWarehouseName = null;	//�ֿ�����
		
		String strCreateTime = null;	//�Ƶ�ʱ��
		
		String strCreateManid = null; 	//�Ƶ���
	 	String strCreteMan = null;
	
	if(result != null){
		movementId = result.getMovementid();
		strWarehouseId = result.getWarehouseid();
		strWarehouseName = result.getWarehouseName();
		strCreateTime = result.getCreateTime();
		strCreateManid = result.getCreateManid();
		strCreteMan = result.getCreateMan();
	}
%>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--
	
	var HKEY_Root,HKEY_Path,HKEY_Key;
	HKEY_Root="HKEY_CURRENT_USER";
	HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	
	//������ҳ��ӡ��ҳüҳ��Ϊ��
	function PageSetup_Null()     
	{
		try
		{
			var Wsh=new ActiveXObject("WScript.Shell");
			HKEY_Key="header";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
			HKEY_Key="footer";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		}
		catch(e){}
	}
	//������ҳ��ӡ��ҳüҳ��ΪĬ��ֵ
	function PageSetup_Default()
	{
	  	try
	    {
			var Wsh=new ActiveXObject("WScript.Shell");
			HKEY_Key="header";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&w&bҳ00��,&p/&P");
			HKEY_Key="footer";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&u&b&d");
	    }     
		catch(e){}
	}
	
	//��Excel����
	function openexcel()
	{
		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
		
		var classname = "com.wms3.bms.standard.action.report.inventory.InventoryYKQueryExcel";
		
		
		document.tempForm1.action = strUrl + "downFileAction&name=�ƿ��ѯ.xls&fileType=excel&classname= " + classname + "&movementId="+"<%=movementId %>";
		
		document.tempForm1.submit();
		//alert(window.opener.condition);
		//alert(window.opener.getPostCon());
	}
-->
</script>
<style media="print">
<!--
	.Noprint{display:none;}<!--�ñ���ʽ�ڴ�ӡʱ���طǴ�ӡ��Ŀ-->
	.PageNext{page-break-after: always;}<!--���Ʒ�ҳ-->
-->
</style> 
<style type="text/css">
<!--
	.style2 {
		font-size: 24px; 
		font-weight: bold; 
		font-family: "����_GB2312";
	}
-->
</style>
</head>

<body>


 <!-- ======== ���ܰ�ť��ʼ ======== -->
 <form id="myform1" name="myform1" method="post">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0" class="Noprint">
   <tr height="30">
     <td><div align="left">
       <OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0></OBJECT>
       <input type="button" name="readyBtn" value="excel����" class="BUTTON_STYLE1" onclick="openexcel();">&nbsp;
       <input type="button" name="readyBtn" value="��ӡԤ��" class="BUTTON_STYLE1" onclick="PageSetup_Null();document.all.WebBrowser.ExecWB(7,1)">&nbsp;
       <input type="button" name="setBtn" value="ҳ������" class="BUTTON_STYLE1" onclick="document.all.WebBrowser.ExecWB(8,1)">&nbsp;
       <input type="button" name="printBtn" value="��ӡ" class="BUTTON_STYLE1" onclick="PageSetup_Null();javascript:window.print()">&nbsp;
       <input type="button" name="closeBtn" value="�ر�" class="BUTTON_STYLE1" onclick="window.close()">
     </div></td>
   </tr>
 </table>
 </form>
 <!-- ======== ���ܰ�ť���� ======== -->

 <!-- ======== ���⿪ʼ ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
 	<tr>
	 <td height="40" colspan="6" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;ҩ&nbsp;ҵ&nbsp;(&nbsp;��&nbsp;��&nbsp;)&nbsp;��&nbsp;��&nbsp;��&nbsp;˾</div></td>
   </tr>
   <tr>
	 <td height="40" colspan="<%=12 + itemscount%>" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;��</div></td>
   </tr>
    <tr>
   	<td colspan="6" height="10" ></td>
   </tr>
   <tr>
	 <td height="20" colspan="8" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
   <tr>
	 <td height="20"><div align="left"  class="list_title_tdpr">�ֿ����ƣ�<%=strWarehouseName==null?"":strWarehouseName%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">�Ƶ�ʱ�䣺<%=strCreateTime==null?"":strCreateTime%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">���ݱ��룺<%=movementId==null?"":movementId%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">�Ƶ��ˣ�<%=strCreteMan==null?"":strCreteMan%></div></td>
	 
   </tr>
   
 </table>
 <!-- ======== ������� ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>

 <!-- ======== �б�ʼ ======== -->  	
  <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
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
	List ls = (List)request.getAttribute("exResultList");
   if(ls != null && ls.size() > 0){
		InventoryMovementDetail iDetail = null;
		
		String strProductCode = null; 	//��Ʒ���
		String strProductName = null; 	//Ʒ��
		String strProductDate = null;	//��������
		String strLotNum = null;	//����
		String strTrayNum = null;	//���̺�
		String strPunit = null; //��λ
		
		String strFromWhare = null;	//FM����
		String strFromCargospace = null; //FM��λ
		
		String strToWhare = null;	//to����
		String strToCargospace = null; //to��λ
		
		
		
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
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>

<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundInvoiceDetail" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%
	HashMap<String, List> hsLot = null;		//����Ҫ��ʾ������
	List<BaseLotSet> lsLot = null;  		//��ʾ�����������б�
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newin");	//�½��ջ���->��ʾ�ջ�����ϸ	
		}
	}
%>
<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
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
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
	}
	
	function OnLoad(){
	
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
		}
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">
     	<input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�</div>
     </td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��״̬</div></td> 
     <td class="TD_LIST_TITLE"><div class="list_title">��װ</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
<%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">�ջ�����</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size()>0){
	
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InboundInvoiceDetail detail = null;
		String instockdetailid = null;//��ⵥ��ϸID
		String product = null;		// Ʒ��
		String linestatus = null;   //������״̬0-�½� 5-����
		String pack = null;			// ��װ
		String eunit = null;		// ��λ
		double  invoicenum = 0;		// ����
		double netweight = 0;		// ����
		String reincoiceid = null;  // �ջ�����
		
		String lotatt1;  			// ��������1
     	String lotatt2;  			// ��������2
     	String lotatt3;  			// ��������3
     	String lotatt4;  			// ��������4
     	String lotatt5;  			// ��������5
     	String lotatt6;  			// ��������6
     	String lotatt7;  			// ��������7
     	String lotatt8;  			// ��������8
     	String lotatt9;  			// ��������9
     	String lotatt10;  			// ��������10
     	String lotatt11;  			// ��������11
     	String lotatt12;  			// ��������12
		
		for(int i = 0; i < ls.size(); i++){
			detail = (InboundInvoiceDetail)ls.get(i);
			
			instockdetailid = detail.getInstockdetailid();	// ��ⵥ��ϸID
			product = detail.getM_strProductName();			// Ʒ��
			linestatus = detail.getM_strStatusName();   	//������״̬0-�½� 5-����
			pack = detail.getPkgdesc();				// ��װ
			eunit = detail.getEunit();				// ��λ
			invoicenum = detail.getInvoicenum();	// ����
			netweight = detail.getNetweight();		// ����
			reincoiceid = detail.getReincoiceid();	// �ջ�����
			
			lotatt1 = detail.getLotatt1();  		// ��������1
     	 	lotatt2 = detail.getLotatt2();  		// ��������2
     	 	lotatt3 = detail.getLotatt3();			// ��������3
     	 	lotatt4 = detail.getLotatt4();  		// ��������4
     	 	lotatt5 = detail.getLotatt5();  		// ��������5
     	 	lotatt6 = detail.getLotatt6();  		// ��������6
     	 	lotatt7 = detail.getLotatt7();  		// ��������7
     	 	lotatt8 = detail.getLotatt8();  		// ��������8
     	 	lotatt9 = detail.getLotatt9();  		// ��������9
     	 	lotatt10 = detail.getLotatt10();  		// ��������10
     	 	lotatt11 = detail.getLotatt11();  		// ��������11
     	 	lotatt12 = detail.getLotatt12();  		// ��������12
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center" width="50px">
     	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=instockdetailid%>"><%=i+1%>
     </td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=linestatus==null?"":linestatus%></td>
     <td class="TD_LIST" align="center"><%=pack==null?"":pack%></td>
     <td class="TD_LIST" align="center"><%=eunit==null?"":eunit%></td>
 <%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = detail.getClass().getDeclaredField(lotSet.getLotid());			
%>
	 		 <td class="TD_LIST" align="center"><%=field.get(detail)==null?"":(String)field.get(detail)%></td>
<%
		}
	}
%>    
     <td class="TD_LIST" align="center"><%=nbf.format(invoicenum)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
     <td class="TD_LIST2" align="center"><%=reincoiceid==null?"":reincoiceid%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="<%=8 + iLine%>" class="TD_last_LIST"></td>
   </tr>
   </tbody> 
 </table>

</body>
</html>
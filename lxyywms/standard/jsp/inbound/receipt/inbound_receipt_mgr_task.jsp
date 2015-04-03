<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%@ page import="java.util.List" %>
<%
	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	
	HashMap<String, List> hsLot = null;	//����Ҫ��ʾ������
	List<BaseLotSet> lsLot = null;  	//��ʾ�����������б�
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("shtrans");//�½��ջ���->��ʾ�ջ�����ϸ	
		}
	}
	
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ޱ����ĵ�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<style>
html { overflow-y:hidden; }
</style>


</head>

<body >
 <table width="150%" height="143" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST3">
   <tr>
     <td class="TD_LIST_TITLE11" align="center" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">���ٺ�</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">״̬</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">��װ</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">�ջ���</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">ë��</div></td>
<%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE3" align="center"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>     
     <td class="TD_LIST_TITLE21" align="center"><div class="list_title">����</div></td>
   </tr>
<%
   	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2);  
		
		InboundReceiptTransaction receiptTrans = null; 
		
		String transid;		   //���׺�
      	String productid;      //��ƷID
      	String punit;          //��λ
      	double num;            //����
      	double weight;         //����
      	double netweight;      //����   
      	
      	String m_strProductName;// ��Ʒ
      	String m_strPackName;	// ��װ
      	String receiptmanname;	// �ջ���
      	String ownername;		// ����
      	
      	String strStatusName = "";	// ״̬��

		for(int i = 0; i < ls.size(); i++)
		{
			receiptTrans = (InboundReceiptTransaction)ls.get(i);
		
			transid = receiptTrans.getTransreceiptid();	//���׺�
      	 	productid = receiptTrans.getProductid();    //��ƷID
      	 	punit = receiptTrans.getM_strUnitName();    //��λ
      	 	num = receiptTrans.getRecnum();            	//����
      	 	weight = receiptTrans.getReweight();        //����
      	 	netweight = receiptTrans.getRenetweight();  //����   
      	
      	 	m_strProductName = receiptTrans.getM_strProductName();// ��Ʒ
      	 	m_strPackName = receiptTrans.getM_strPackName();	  // ��װ
      	 	receiptmanname = receiptTrans.getReceiptmanname();	  // �ջ���
      	 	ownername = receiptTrans.getOwnername();		      // ����
			strStatusName = receiptTrans.getStrStatusName();
			
      	 	
		
%>
   <tr class="list_normal_tr" onmouseover="this.style.backgroundColor='#CCFF00'" onmouseout="this.style.backgroundColor=''" onclick="">
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=transid%></td>
     <td class="TD_LIST" align="center"><%=strStatusName%></td>
     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
     <td class="TD_LIST" align="center"><%=m_strPackName%></td>
     <td class="TD_LIST" align="center"><%=punit%></td>
     <td class="TD_LIST" align="center"><%=receiptmanname%></td>
     
     <td class="TD_LIST" align="center"><%=nbf.format(num)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td> 
     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td> 
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);
			field = receiptTrans.getClass().getDeclaredField(lotSet.getLotid());					
%>
	 		 <td class="TD_LIST" align="center"><%=(String)field.get(receiptTrans)%></td>
<%
		}
	}
%>	     
     <td class="TD_LIST2" align="center"><%=ownername%></td>

   </tr>

<%
	      	 
		}
		
	}else
	{
		session.removeAttribute("inboundPage2");
	}
%>
 <tr>
     <td class="TD_last_LIST" height="100%" colspan="<%=11 + iLine%>" valign="top">
     <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
     </td>
   </tr>
 </table>
 
 <!-- ======== ��ҳ��ǩ ======== -->
	<%@ include file="include/page2.jsp" %>
</body>
</html>

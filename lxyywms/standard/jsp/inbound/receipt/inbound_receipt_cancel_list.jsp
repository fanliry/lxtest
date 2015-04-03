<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%
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

<script>
	var oldobj = null;
	function Change(obj)
	{
		obj.style.backgroundColor = "#33FF33";
		if(oldobj != null){
			oldobj.style.backgroundColor = "";	
		}
		oldobj = obj;
	}
	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#33FF33";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	function OnLoad(){
		parent.UnLockButton();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert(back_msg);
			}else{
				alert(back_msg);
			}
		}
	}
	
</script>

</head>

<body onload="javascript:OnLoad();">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="200%" height="260" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">״̬</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">���ٺ�</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��װ</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ջ�����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ջ�ë��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ջ�����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ϼ�����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ϼ�ë��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ϼܾ���</div></td>

<%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InboundReceiptTransaction receiptTrans = null;//�ջ���¼   
		String transid;			//�ջ���¼ID
		String inreceiptid;		//�ջ���ID
      	String productid;		//Ʒ�����
      	double num;				//����
	  	double weight;			//����
      	double netweight;		//����
      	double ptnum;			//�ϼ�����
	  	double ptweight;		//�ϼ�����
      	double ptnetweight;		//�ϼܾ���
      	
        String strStatusName = "";	// ״̬��
        String m_strProductName;    // ��Ʒ
      	
      	String m_strPackName;       //��װ����
        String m_strUnitName;       //��λ����
      	
		for(int i = 0; i < ls.size(); i++)
		{
			receiptTrans = (InboundReceiptTransaction)ls.get(i);
			transid = receiptTrans.getTransreceiptid();		//��¼ID
			inreceiptid = receiptTrans.getReinvoiceid();	//�ջ�����
	      	productid = receiptTrans.getProductid();		//Ʒ�����
	      	num = receiptTrans.getRecnum();					//����
		  	weight = receiptTrans.getReweight();			//����
	      	netweight = receiptTrans.getRenetweight();		//����
	      	
	      	ptnum = receiptTrans.getPucnum();			//�ϼ�����
	  	 	ptweight = receiptTrans.getPuweight();		//�ϼ�����
      	 	ptnetweight = receiptTrans.getPunetweight();//�ϼܾ���
	      	 
         	m_strProductName = receiptTrans.getM_strProductName(); // ��Ʒ
         	
         	m_strPackName = receiptTrans.getM_strPackName();       //��װ����
         	m_strUnitName = receiptTrans.getM_strUnitName();       //��λ����
         	
         	strStatusName = receiptTrans.getStrStatusName();	   //״̬����
			
 %>
	         <tr class="list_normal_tr" onmouseover="this.style.backgroundColor='#CCFF00'" onmouseout="this.style.backgroundColor=''" onclick="Change(this)">
		     <td class="TD_LIST" align="center"><%=i+1%><input onClick="Change(this)" type="radio" name="check_id" class="input_checkbox" value="<%=transid%>"></td>
		     <td class="TD_LIST" align="center"><%=strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=transid%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=m_strPackName == null ? "" : m_strPackName%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     
		     <td class="TD_LIST" align="center"><%=nbf.format(num)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(ptnum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(ptweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(ptnetweight)%></td>
		     
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
		   </tr>	       	 
<%
	      	 
		}
		
	}else
	{
		session.removeAttribute("inboundPageCancel");
	}
%>

   <tr>
      <td height="100%" colspan="<%=12 + iLine%>" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
  
 </table>
 <!-- ======== ��ҳ��ǩ ======== -->
	<%@ include file="include/pagecancel.jsp"  %>
</div>
 <FORM action="" method='post' name='formx1'  style='display:none'></FORM>
 
</body>
</html>
